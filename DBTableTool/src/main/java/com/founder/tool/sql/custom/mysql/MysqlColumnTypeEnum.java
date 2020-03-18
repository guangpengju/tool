package com.founder.tool.sql.custom.mysql;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@AllArgsConstructor
public enum MysqlColumnTypeEnum {
    DC("mysql","DC","tinyint",false,false,false, null, null),
    SI("mysql","SI","smallint",false,false,false, null, null),
    I("mysql","I","int",false,false,false, null, null),
    LI("mysql","LI","bigint",false,false,false, null, null),

    DB("mysql","DB","double",false,false,false, null, null),

    N("mysql","N","decimal",true,true,false, null, 0),
    DEC("mysql","DEC","decimal",true,true,false, null, null),


    DT("mysql","DT","datetime",false,false,false, null, null),
    D("mysql","D","date",false,false,false, null, null),
    T("mysql","T","time",false,false,false, null, null),

    VC("mysql","VC","varchar",true,false,false, null, null),
    C("mysql","C","char",true,false,false, null, null),
    BLOB("mysql","BLOB","blob",false,false,false, null, null),
    TEXT("mysql","TEXT","text",false,false,false, null, null),
    LBLOB("mysql","LBLOB","longblob",false,false,false, null, null),
    LTEXT("mysql","LTEXT","longtext",false,false,false, null, null),
    ;

    private String typeDB;
    private String type;
    private String realType;
    private boolean enableLen;
    private boolean enableAccuracy;
    private boolean forceUseDefaultValue;
    private Integer defaultLen;
    private Integer defaultAccuracy;

    public static MysqlColumnTypeEnum getColumnType(String type, String typeDB){
        MysqlColumnTypeEnum[] values = MysqlColumnTypeEnum.values();

        MysqlColumnTypeEnum result = null;
        for (MysqlColumnTypeEnum value : values) {
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
