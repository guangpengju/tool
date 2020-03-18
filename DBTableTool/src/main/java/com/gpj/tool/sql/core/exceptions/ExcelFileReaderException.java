package com.gpj.tool.sql.core.exceptions;

import com.founder.tool.sql.common.exception.SQLToolException;
import lombok.Getter;

/**
 * @className ExcelFileReaderException
 * @description excel文件读取错误
 * @author GPJ
 * @date 2019/7/10 17:04
 * @version 1.0
 **/
@Getter
public class ExcelFileReaderException extends Exception {
    public ExcelFileReaderException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
