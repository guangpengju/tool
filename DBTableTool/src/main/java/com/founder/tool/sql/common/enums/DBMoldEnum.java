package com.founder.tool.sql.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@AllArgsConstructor
public enum DBMoldEnum {
    DM("dm","达梦数据库","com.founder.tool.sql.custom.dm"),
    MYSQL("mysql","Mysql","com.founder.tool.sql.custom.mysql"),
    ORACLE("oracle","Oracle","com.founder.tool.sql.custom.oracle"),
    POSTGRESQL("pg","postgresql","com.founder.tool.sql.custom.postgresql"),

    DB2("db2","DB2","com.founder.tool.sql.custom.db2"),//目前不支持
    ;
    private String type;
    private String name;
    private String handelPackage;

    public static DBMoldEnum getEnum(String type){
        DBMoldEnum[] values = DBMoldEnum.values();
        for (DBMoldEnum value : values) {
            if(StringUtils.equals(type, value.getType())){
                return value;
            }
        }
        return null;
    }
}
