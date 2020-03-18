package com.founder.tool.sql.common.parser.impl;

import com.founder.tool.sql.common.cache.SQLInfoCache;
import com.founder.tool.sql.common.enums.ProcedureInputEnum;
import com.founder.tool.sql.common.exception.ExcelParserException;
import com.founder.tool.sql.common.parser.AbstractProcedureExcelParser;
import com.founder.tool.sql.common.pojo.ProcedureInfo;
import com.founder.tool.sql.common.utils.LogInfoUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;

import java.util.ArrayList;
import java.util.List;

/**
 * @className DefaultProcedureExcelParser
 * @description 默认excel的procedure的sheet页的解析器
 * @author GPJ
 * @date 2019/7/10 18:24
 * @version 1.0
 **/
@Slf4j
public class DefaultProcedureExcelParser extends AbstractProcedureExcelParser {
    private List<String> debugKey = new ArrayList<>();

    @Override
    protected void analysisProcedure(Row row) {
        SQLInfoCache.addProcedureInfo(analysisRow2ProcedureInfo(row));
    }

    /**
     * 解析excel行对象,获取table信息
     * @param row
     * @return
     */
    private ProcedureInfo analysisRow2ProcedureInfo(Row row){
        String value = getStringCellValue(row, 1);
        ProcedureInputEnum inputEnum = ProcedureInputEnum.getEnum(value);

        ProcedureInfo info = new ProcedureInfo(
                getStringCellValue(row, 0),
                getStringCellValue(row, 2),
                getStringCellValue(row, 3),
                inputEnum
        );
        return procedureInfoCheck(info, row);
    }

    private ProcedureInfo procedureInfoCheck(ProcedureInfo info, Row row){
        if(StringUtils.isBlank(info.getName())){
            throw new ExcelParserException("存储过程定义信息缺失,存储过程名列不可为空!", LogInfoUtils.getCellInfo(row));
        }else if(info.getInputType() == null){
            throw new ExcelParserException("存储过程定义信息错误,类型列定义不合法!", LogInfoUtils.getCellInfo(row));
        }else if(StringUtils.isBlank(info.getData())){
            throw new ExcelParserException("存储过程定义信息缺失,数据列不可为空!", LogInfoUtils.getCellInfo(row));
        }else if(info.getInputType() != ProcedureInputEnum.BODY && StringUtils.isBlank(info.getAssist())){
            throw new ExcelParserException("存储过程定义信息缺失,类型为" + info.getInputType().getTypeName() + "时,补充数据列不可为空!", LogInfoUtils.getCellInfo(row));
        }

        if(info.getInputType() == ProcedureInputEnum.BODY){
            whileBodySQlCheck(info.getData(), row);
        }

        if(log.isDebugEnabled() && !debugKey.contains(info.getName())){
            log.debug("[{}]触发器信息已解析入内存...", info.getName());
            debugKey.add(info.getName());
        }
        return info;
    }

    private void whileBodySQlCheck(String sql, Row row){
        String data = sql.toLowerCase();
        if(data.matches("^\\s*while\\b.+$") &&
                !data.matches("^\\s*while\\b.+\\bloop\\s*$")){
            throw new ExcelParserException("存储过程定义信息缺失,BODY类型行,数据列内容[" + sql + "]不合法,while循环需要使用loop标记!", LogInfoUtils.getCellInfo(row));
        }else if(data.matches("^\\s*end\\b.+$") &&
                !data.matches("^\\s*end\\s+if\\s*;\\s*$") &&
                !data.matches("^\\s*end\\s+loop\\s*;\\s*$")){
            throw new ExcelParserException("存储过程定义信息缺失,BODY类型行,数据列内容[" + sql + "]不合法,结束循环需要使用loop标记!", LogInfoUtils.getCellInfo(row));
        }
    }
}
