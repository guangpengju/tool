package com.founder.tool.sql.common.exception;

import lombok.Getter;

/**
 * @className SQLToolException
 * @description SQLTool异常父类
 * @author GPJ
 * @date 2019/7/10 17:04
 * @version 1.0
 **/
@Getter
public class SQLToolException extends RuntimeException {
    private String sqlToolExceptionMessage;
    private String sqlToolExceptionInfo;

    public SQLToolException(String sqlToolExceptionMessage) {
        super(sqlToolExceptionMessage);
        this.sqlToolExceptionMessage = sqlToolExceptionMessage;
    }

    public SQLToolException(String sqlToolExceptionMessage, String sqlToolExceptionInfo) {
        super(sqlToolExceptionMessage + " info --> [" + sqlToolExceptionInfo + "]");
        this.sqlToolExceptionMessage = sqlToolExceptionMessage;
        this.sqlToolExceptionInfo = sqlToolExceptionInfo;
    }
}
