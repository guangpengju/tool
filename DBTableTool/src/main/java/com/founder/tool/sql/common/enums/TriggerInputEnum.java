package com.founder.tool.sql.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@AllArgsConstructor
public enum TriggerInputEnum {
    TIRGGER_TIME("TIME","触发时机"),
    TRIGGER_EVENT("EVENT","触发事件"),
    TABLE("TABLE","表名"),
    DECLARE("DECLARE","变量定义"),
    BODY("BODY","主体程序"),
    ;
    private String typeName;
    private String note;

    public static TriggerInputEnum getEnum(String name){
        TriggerInputEnum[] values = TriggerInputEnum.values();
        for (TriggerInputEnum value : values) {
            if(StringUtils.equals(name, value.getTypeName())){
                return value;
            }
        }
        return null;
    }
}
