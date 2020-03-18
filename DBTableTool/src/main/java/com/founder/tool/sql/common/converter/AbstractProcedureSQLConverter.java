package com.founder.tool.sql.common.converter;

import com.alibaba.fastjson.JSON;
import com.founder.tool.sql.common.cache.SQLInfoCache;
import com.founder.tool.sql.common.constants.InputType;
import com.founder.tool.sql.common.exception.ExcelContentException;
import com.founder.tool.sql.common.pojo.BodyReplace;
import com.founder.tool.sql.common.pojo.Cursor;
import com.founder.tool.sql.common.pojo.DBBase;
import com.founder.tool.sql.common.pojo.Procedure;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @className AbstractProcedureSQLConverter
 * @description 抽象存储过程语句转换器
 * @author GPJ
 * @date 2019/7/10 15:54
 * @version 1.0
 **/
@Slf4j
public abstract class AbstractProcedureSQLConverter implements SQLConverter {
    protected static final String CURSOR_END_FLG = "curesor_end";
    protected static final String START_KEY_WORD = "begin";
    protected static final String END_KEY_WORD = "end";

    @Override
    public void convert(OutputStream out) {
        List<String> procedureNames = SQLInfoCache.getProcedureNames();

        for (String procedureName : procedureNames) {
            Procedure procedure = SQLInfoCache.getProcedure(procedureName);
            // 注释语句sql
            write(out, noteSQL("PROCEDURE " + procedureName));
            if(log.isDebugEnabled()){
                log.debug("[{}]存储过程语句生成完毕",procedureName);
            }

            // 1.删除procedure
            boolean replaceKeyWord = useReplaceKeyWord();
            if(!replaceKeyWord){
                write(out, dropProcedure(procedureName));
            }

            if(StringUtils.isNotBlank(startDelimiter())) write(out, startDelimiter());

            // 2.创建procedure
            write(out, createProcedure(procedureName, replaceKeyWord));

            // 3.param
            write(out, "(" + createParamSQL(procedure) + ") \n");

            // 4.declare
            boolean alone = declareAlone();
            if(!alone) write(out, "BEGIN \n");
            write(out, createDeClare(procedure.getDeclare(), procedure.getCursor(), alone));

            // 5.body
            if(alone) write(out, "BEGIN \n");
            write(out, setCursorEndFlgVal(procedure.getCursor()));
            write(out, createBody(procedure.getBody(), procedure.getCursor()));
            cursorProCheck(procedure.getCursor());
            write(out, "\nEND \n");

            if(StringUtils.isNotBlank(endDelimiter())) write(out, endDelimiter());
        }
    }

    private void cursorProCheck(Map<String,Cursor> cursor){
        List<Cursor> list = cursor.values().stream().filter( cur -> !cur.isProcessed() ).collect(Collectors.toList());
        if(!list.isEmpty()) throw new ExcelContentException("存储过程内容错误,游标遍历退出条件所在行的补充数据列必须为curesor_end!", JSON.toJSONString(list));
    }

    protected String repacle(String sql, String matche, String regex, String replace){
        if(StringUtils.isNotBlank(sql) &&
                StringUtils.isNotBlank(matche) &&
                StringUtils.isNotBlank(regex) &&
                StringUtils.isNotBlank(replace) &&
                sql.matches(matche)){
            sql = sql.replaceAll(regex, replace);
        }
        return sql;
    }

    abstract protected String startDelimiter();

    abstract protected String endDelimiter();

    abstract protected String dropProcedure(String procedureName);

    abstract protected String createProcedure(String procedureName, boolean replaceKeyWord);

    abstract protected String createParamSQL(Procedure procedure);

    abstract protected String createDeClare(List<DBBase.Entry> deClareDatas, Map<String, Cursor> cursorDatas, boolean alone);
    
    abstract protected String createBody(List<DBBase.Entry> datas, Map<String, Cursor> cursorDatas);

    abstract protected String setCursorEndFlgVal(Map<String, Cursor> cursorDatas);

    abstract protected boolean declareAlone();

    abstract protected boolean useReplaceKeyWord();
}
