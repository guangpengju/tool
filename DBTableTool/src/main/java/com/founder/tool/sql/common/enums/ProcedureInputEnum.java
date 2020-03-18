package com.founder.tool.sql.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @className ProcedureInputEnum
 * @description TODO
 * @author GPJ
 * @date 2019/7/12 17:48
 * @version 1.0
 **/
@Getter
@AllArgsConstructor
public enum ProcedureInputEnum {
    IN("IN","输入参数"),
    OUT("OUT","输出参数"),
    INOUT("INOUT","输入输出参数"),
    DECLARE("DECLARE","变量定义"),
    CURSOR_DEF("CURSOR_DEF","游标"),
    CURSOR_END("CURSOR_END","游标"),
    BODY("BODY","主体程序"),
    ;
    private String typeName;
    private String note;

    public static ProcedureInputEnum getEnum(String name){
        ProcedureInputEnum[] values = ProcedureInputEnum.values();
        for (ProcedureInputEnum value : values) {
            if(StringUtils.equals(name, value.getTypeName())){
                return value;
            }
        }
        return null;
    }
}
