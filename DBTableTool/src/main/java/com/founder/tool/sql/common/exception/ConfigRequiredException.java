package com.founder.tool.sql.common.exception;

/**
 * @className ConfigFileIsNoEx
 * @description TODO
 * @author GPJ
 * @date 2019/7/10 17:04
 * @version 1.0
 **/
public class ConfigRequiredException extends SQLToolException {
    public ConfigRequiredException(String key) {
        super("配置信息[" + key + "]不存在,该配置信息是必填信息不可缺失!");
    }
}
