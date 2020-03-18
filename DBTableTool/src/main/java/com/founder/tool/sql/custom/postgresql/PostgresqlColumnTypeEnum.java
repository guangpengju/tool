package com.founder.tool.sql.custom.postgresql;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@AllArgsConstructor
public enum PostgresqlColumnTypeEnum {
    DC("pg","DC","numeric",false,false,true, 3, 0),
    SI("pg","SI","smallint",false,false,false, null, null),
    I("pg","I","integer",false,false,false, null, null),
    LI("pg","LI","bigint",false,false,false, null, null),

    DB("pg","DB","double",false,false,false, null, null),

    N("pg","N","numeric",true,true,false, 5, 2),
    DEC("pg","DEC","decimal",true,true,false, 5, 2),


    DT("pg","DT","timestamp",false,false,false, null, null),
    D("pg","D","date",false,false,false, null, null),
    T("pg","T","time",false,false,false, null, null),

    VC("pg","VC","varchar",true,false,false, null, null),
    C("pg","C","char",true,false,false, null, null),
    BLOB("pg","BLOB","bytea",false,false,false, null, null),
    TEXT("pg","TEXT","text",false,false,false, null, null),
    LBLOB("pg","LBLOB","bytea",false,false,false, null, null),
    LTEXT("pg","LTEXT","text",false,false,false, null, null),
    ;

    private String typeDB;
    private String type;
    private String realType;
    private boolean enableLen;
    private boolean enableAccuracy;
    private boolean forceUseDefaultValue;
    private Integer defaultLen;
    private Integer defaultAccuracy;

    public static PostgresqlColumnTypeEnum getColumnType(String type, String typeDB){
        PostgresqlColumnTypeEnum[] values = PostgresqlColumnTypeEnum.values();

        PostgresqlColumnTypeEnum result = null;
        for (PostgresqlColumnTypeEnum value : values) {
            if(StringUtils.equals(type, value.getType())){
                if(value.getTypeDB().contains(typeDB)){
                    return value;
                }else if(StringUtils.isBlank(value.getTypeDB())){
                    result = value;
                }
            }
        }
        return result;
    }
}
