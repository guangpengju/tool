package com.founder.tool.sql.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SheetEnum {
    TABLE("table","表信息"),
    COLUMN("column","字段信息"),
    INDEX("index","索引信息"),
    DATAS("datas","数据信息"),
    PROCEDURE("procedure","存储过程"),
    TRIGGER("trigger","触发器"),
    ;
    private String type;
    private String showName;
}
