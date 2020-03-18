package com.founder.tool.sql.common.exception;

import lombok.Getter;

/**
 * @className SQLToolIOException
 * @description IO错误
 * @author GPJ
 * @date 2019/7/10 17:04
 * @version 1.0
 **/
@Getter
public class SQLToolIOException extends SQLToolException {
    public SQLToolIOException(String sqlToolExceptionMessage) {
        super(sqlToolExceptionMessage);
    }

    public SQLToolIOException(String sqlToolExceptionMessage, String sqlToolExceptionInfo) {
        super(sqlToolExceptionMessage, sqlToolExceptionInfo);
    }
}
