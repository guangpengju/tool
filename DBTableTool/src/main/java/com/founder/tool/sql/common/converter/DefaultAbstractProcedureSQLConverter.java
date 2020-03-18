package com.founder.tool.sql.common.converter;

import com.founder.tool.sql.common.constants.InputType;
import com.founder.tool.sql.common.exception.ExcelContentException;
import com.founder.tool.sql.common.pojo.Cursor;
import com.founder.tool.sql.common.pojo.DBBase;
import com.founder.tool.sql.common.pojo.Procedure;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @className DefaultAbstractProcedureSQLConverter
 * @description 默认抽象存储过程语句转换器
 * @author GPJ
 * @date 2019/7/10 15:54
 * @version 1.0
 **/
@Slf4j
public abstract class DefaultAbstractProcedureSQLConverter extends AbstractProcedureSQLConverter {
    @Override
    protected String dropProcedure(String procedureName){
        return String.format("DROP PROCEDURE IF EXISTS %s ; \n", procedureName);
    }

    @Override
    protected String createProcedure(String procedureName, boolean replaceKeyWord){
        return String.format("CREATE %s PROCEDURE %s", replaceKeyWord?"OR REPLACE":"", procedureName).trim();
    }

    @Override
    protected String createParamSQL(Procedure procedure){
        String sql = createParamSQL(procedure.getInParams(), InputType.in_type);
        sql += createParamSQL(procedure.getInoutParams(), InputType.inout_type);
        sql += createParamSQL(procedure.getOutParam(), InputType.out_type);

        return StringUtils.isNotBlank(sql)?sql.substring(0, sql.length() - 1):"";
    }

    private String createParamSQL(List<DBBase.Entry> param, String type){
        if(param != null && !param.isEmpty()){
            return param.stream().map(entry -> paramSQL(entry, type)).collect(Collectors.joining(",")) + ",";
        }
        return "";
    }

    @Override
    protected String createDeClare(List<DBBase.Entry> deClareDatas, Map<String, Cursor> cursorDatas, boolean alone){
        String keyWords = alone?"AS \n\tDECLARE \n":"";
        String sql = "";
        if(deClareDatas != null && !deClareDatas.isEmpty()){
            sql += deClareDatas.stream().map( entry -> declareSQL(entry, alone) ).collect(Collectors.joining("; \n"));
            sql += "; \n";
        }

        if(cursorDatas != null && !cursorDatas.isEmpty()){
            sql += cursorDatas.values().stream().map(cursor -> cursorSQL(cursor, alone)).collect(Collectors.joining("; \n"));
            sql += "; \n";
        }
        return StringUtils.isNotBlank(sql)?keyWords + sql:"";
    }

    private String cursorSQL(Cursor cursor, boolean alone){
        return declareKeyWord(cursorDefineSQL(cursor.getName(), cursor.getDefine(), cursor.getEndFlg()), alone);
    }

    private String declareSQL(DBBase.Entry entry, boolean alone){
        return declareKeyWord(entry.getData() + " " + entry.getAssist(), alone);
    }

    private String declareKeyWord(String sql, boolean alone){
        return "\t" + (alone?"":"declare ") + sql;
    }

    @Override
    protected String setCursorEndFlgVal(Map<String, Cursor> cursorDatas){
        if(cursorDatas != null && !cursorDatas.isEmpty()){
            return cursorDatas.values().stream().map(this::setCursorEndFlgVal).filter(StringUtils::isNotBlank).collect(Collectors.joining(" ; \n"));
        }
        return "";
    }

    @Override
    protected String createBody(List<DBBase.Entry> datas, Map<String, Cursor> cursorDatas){
        if(datas != null && !datas.isEmpty()){
            AtomicInteger indentNum = new AtomicInteger(1);
            return datas.stream().map(entry -> {
                String data = entry.getData().trim().toLowerCase();

                if(data.matches("^.*\\b(else|end|close)\\b.*$")){
                    indentNum.getAndDecrement();
                }

                String sql = indent(indentNum.get()) + bodyHandle(indentNum, entry, cursorDatas);

                if(data.matches("^.*\\b(if|else|while|open)\\b.*$") && !data.matches("^.*\\bend\\b.*$")){
                    indentNum.getAndIncrement();
                }

                return sql;
            }).filter(StringUtils::isNotBlank)
            .filter(s -> !StringUtils.equalsIgnoreCase(s.trim(), START_KEY_WORD) && !StringUtils.equalsIgnoreCase(s.trim(), END_KEY_WORD) )
            .collect(Collectors.joining("\n"));
        }
        return null;
    }

    private String indent(int indentNum){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < indentNum; i++) {
            builder.append("\t");
        }
        return builder.toString();
    }

    private String bodyHandle(AtomicInteger indentNum, DBBase.Entry data,  Map<String, Cursor> cursorDatas){
        String sql;
        if(StringUtils.equals(CURSOR_END_FLG,data.getAssist())){
            Cursor cursor = cursorDatas.get(data.getData());
            if(cursor == null) throw new ExcelContentException("存储过程内容错误,cursor_end行需要填写正确游标名!", data.getData());
            cursor.setProcessed(true);
            sql = cursorEndFlgSQL(cursor.getName(),cursor.getEndFlg());
            indentNum.getAndIncrement();
        }else{
            sql = data.getData();
        }

        return StringUtils.isBlank(sql)?"":bodyHandle(sql);
    }

    abstract protected String paramSQL(DBBase.Entry entry, String type);

    abstract protected String cursorDefineSQL(String cursorName, String def, String endFlg);

    abstract protected String cursorEndFlgSQL(String cursorName, String endFlg);

    abstract protected String setCursorEndFlgVal(Cursor cursor);

    abstract protected String bodyHandle(String sql);
}
