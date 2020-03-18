package com.gpj.tool.sql.core.excel.parsers;

import com.gpj.tool.sql.core.pojo.RowInfo;
import com.gpj.tool.sql.core.pojo.Table;

public interface TableExcelParser {
    default public boolean skip(RowInfo rowInfo, Table table){
        return false;
    }

    public boolean analysis(RowInfo rowInfo, Table table);
}
