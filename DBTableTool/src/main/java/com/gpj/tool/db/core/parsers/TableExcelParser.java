package com.gpj.tool.db.core.parsers;

import com.gpj.tool.db.core.pojo.RowInfo;
import com.gpj.tool.db.core.pojo.Table;

public interface TableExcelParser {
    default public boolean skip(RowInfo rowInfo, Table table){
        return false;
    }

    public boolean analysis(RowInfo rowInfo, Table table);
}
