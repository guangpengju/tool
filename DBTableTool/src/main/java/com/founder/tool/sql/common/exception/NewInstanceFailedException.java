package com.founder.tool.sql.common.exception;

import lombok.Getter;

/**
 * @className NewInstanceFailedException
 * @description TODO
 * @author GPJ
 * @date 2019/7/10 17:04
 * @version 1.0
 **/
@Getter
public class NewInstanceFailedException extends SQLToolException {
    public NewInstanceFailedException(String message) {
        super(message);
    }

    public NewInstanceFailedException(String message, Class clazz) {
        super(message, clazz.getName());
    }

    public NewInstanceFailedException(String message, String classPath) {
        super(message, classPath);
    }

    public NewInstanceFailedException(Class clazz) {
        super("创建实例失败!", clazz.getName());
    }
}
