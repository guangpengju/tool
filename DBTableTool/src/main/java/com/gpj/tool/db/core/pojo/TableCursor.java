package com.gpj.tool.db.core.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class TableCursor {
    private String tableName;

    private Table table;

    private Object item;

    public TableCursor(Table table) {
        this.tableName = table.getName();
        this.table = table;
    }
}
