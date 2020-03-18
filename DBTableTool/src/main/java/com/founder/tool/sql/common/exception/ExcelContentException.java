package com.founder.tool.sql.common.exception;

import lombok.Getter;

/**
 * @className ExcelContentException
 * @description excel内容不合法
 * @author GPJ
 * @date 2019/7/10 17:04
 * @version 1.0
 **/
@Getter
public class ExcelContentException extends SQLToolException {
    public ExcelContentException(String sqlToolExceptionMessage) {
        super(sqlToolExceptionMessage);
    }

    public ExcelContentException(String sqlToolExceptionMessage, String sqlToolExceptionInfo) {
        super(sqlToolExceptionMessage, sqlToolExceptionInfo);
    }
}
