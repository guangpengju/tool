package com.founder.tool.sql.common.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @className SQLConverterException
 * @description sql转化异常
 * @author GPJ
 * @date 2019/7/10 17:04
 * @version 1.0
 **/
@Getter
public class SQLConverterException extends RuntimeException {
    private String message;
    private String info;

    public SQLConverterException(String message) {
        super("sql转化失败:" + message);
        this.message = message;
    }

    public SQLConverterException(String message, String info) {
        super("sql转化失败:" + message + "! --> " + info);
        this.message = message;
        this.info = info;
    }
}
