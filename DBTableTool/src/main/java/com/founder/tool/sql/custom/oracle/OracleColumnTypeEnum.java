package com.founder.tool.sql.custom.oracle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@AllArgsConstructor
public enum OracleColumnTypeEnum {
    DC("oracle","DC","number",false,false,true, 3, 0),
    SI("oracle","SI","number",false,false,true, 5, 0),
    I("oracle","I","number",false,false,true, 10, 0),
    LI("oracle","LI","number",false,false,true, 19, 0),

    DB("oracle","DB","binary_double",false,false,false, null, null),

    N("oracle","N","number",true,true,false, 5, 2),
    DEC("oracle","DEC","decimal",true,true,false, 5, 2),


    DT("oracle","DT","timestamp",false,false,false, null, null),
    D("mysql","D","timestamp",false,false,false, null, null),
    T("oracle","T","timestamp",false,false,false, null, null),

    VC("oracle","VC","varchar2",true,false,false, null, null),
    C("oracle","C","char",true,false,false, null, null),
    BLOB("oracle","BLOB","blob",false,false,false, null, null),
    TEXT("oracle","TEXT","clob",false,false,false, null, null),
    LBLOB("oracle","LBLOB","blob",false,false,false, null, null),
    LTEXT("oracle","LTEXT","clob",false,false,false, null, null),
    ;

    private String typeDB;
    private String type;
    private String realType;
    private boolean enableLen;
    private boolean enableAccuracy;
    private boolean forceUseDefaultValue;
    private Integer defaultLen;
    private Integer defaultAccuracy;

    public static OracleColumnTypeEnum getColumnType(String type, String typeDB){
        OracleColumnTypeEnum[] values = OracleColumnTypeEnum.values();

        OracleColumnTypeEnum result = null;
        for (OracleColumnTypeEnum value : values) {
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
