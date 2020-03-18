package com.founder.tool.sql.common.config;

import lombok.Getter;

@Getter
public enum  ConfigEnum {
    EXCEL_PATH("excel.path","excel文件所在路径"),
    EXCEL_FILE("excel.file","excel文件名"),
    SQL_PATH("sql.path","sql文件输出路径"),
    DB_TYPE("db.type","数据库类型"),

    ;
    private String key;
    private String note;

    private ConfigEnum(String key, String note) {
        this.key = key;
        this.note = note;
    }
}
