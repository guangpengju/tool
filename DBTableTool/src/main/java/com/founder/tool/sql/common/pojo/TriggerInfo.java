package com.founder.tool.sql.common.pojo;

import com.founder.tool.sql.common.enums.TriggerInputEnum;
import lombok.Getter;

@Getter
public class TriggerInfo extends BaseInfo{
    private TriggerInputEnum inputType;

    public TriggerInfo(String name, String data, String assist, TriggerInputEnum inputType) {
        super(name, data, assist);
        this.inputType = inputType;
    }
}
