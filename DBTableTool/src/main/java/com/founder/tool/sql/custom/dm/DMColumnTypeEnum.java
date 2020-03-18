package com.founder.tool.sql.custom.dm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@AllArgsConstructor
public enum DMColumnTypeEnum {
    DC("dm","DC","number",false,false,true, 3, 0),
    SI("dm","SI","number",false,false,true, 5, 0),
    I("dm","I","number",false,false,true, 10, 0),
    LI("dm","LI","number",false,false,true, 19, 0),

    DB("dm","DB","double",false,false,false, null, null),

    N("dm","N","number",true,true,false, 5, 2),
    DEC("dm","DEC","decimal",true,true,false, 5, 2),


    DT("dm","DT","timestamp",false,false,false, null, null),
    D("dm","D","date",false,false,false, null, null),
    T("dm","T","time",false,false,false, null, null),

    VC("dm","VC","varchar2",true,false,false, null, null),
    C("dm","C","char",true,false,false, null, null),
    BLOB("dm","BLOB","blob",false,false,false, null, null),
    TEXT("dm","TEXT","text",false,false,false, null, null),
    LBLOB("dm","LBLOB","blob",false,false,false, null, null),
    LTEXT("dm","LTEXT","text",false,false,false, null, null),
    ;

    private String typeDB;
    private String type;
    private String realType;
    private boolean enableLen;
    private boolean enableAccuracy;
    private boolean forceUseDefaultValue;
    private Integer defaultLen;
    private Integer defaultAccuracy;

    public static DMColumnTypeEnum getColumnType(String type, String typeDB){
        DMColumnTypeEnum[] values = DMColumnTypeEnum.values();

        DMColumnTypeEnum result = null;
        for (DMColumnTypeEnum value : values) {
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
