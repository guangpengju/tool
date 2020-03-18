package com.gpj.tool.sql.core.exceptions;

import lombok.Getter;

/**
 * @className ExcelAnalysisException
 * @description excel文件读取错误
 * @author GPJ
 * @date 2019/7/10 17:04
 * @version 1.0
 **/
@Getter
public class ExcelAnalysisException extends Exception {
    public ExcelAnalysisException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
