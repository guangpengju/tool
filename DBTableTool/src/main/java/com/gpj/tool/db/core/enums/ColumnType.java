package com.gpj.tool.db.core.enums;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * @className ColumnType
 * @description TODO
 * @author GPJ
 * @date 2020/3/19 11:16
 * @version 1.0
 **/
@AllArgsConstructor
public enum ColumnType {
    VC("VC"),
    C("C"),
    N("N"),
    I("I"),
    DT("DT"),
    MONEY("MONET"),
    T("T"),
    CLOB("CLOB"),
    BLOB("BLOB"),
    TEXT("TEXT");

    private String code;
    
    public static ColumnType getColumnType(String code){
        for (ColumnType value : ColumnType.values()) {
            if(StringUtils.equals(value.code, code)){
                return value;
            }
        }
        return null;
    }
}
