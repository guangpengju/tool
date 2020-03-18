package com.founder.tool.sql.common.exception;

import com.founder.tool.sql.common.factory.SQLToolManagerFactory;
import lombok.Getter;

/**
 * @className ExcelParserException
 * @description excel解析异常
 * @author GPJ
 * @date 2019/7/10 17:04
 * @version 1.0
 **/
@Getter
public class ExcelParserException extends SQLToolException {
    public ExcelParserException(String sqlToolExceptionMessage) {
        super(sqlToolExceptionMessage);
    }

    public ExcelParserException(String sqlToolExceptionMessage, String sqlToolExceptionInfo) {
        super(sqlToolExceptionMessage, sqlToolExceptionInfo);
    }
}
