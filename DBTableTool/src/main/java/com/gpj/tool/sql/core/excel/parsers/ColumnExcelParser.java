package com.gpj.tool.sql.core.excel.parsers;

import com.gpj.tool.sql.core.pojo.Column;
import com.gpj.tool.sql.core.pojo.RowInfo;

public interface ColumnExcelParser {
    default public boolean skip(RowInfo rowInfo, Column column){
        return false;
    }

    public boolean analysis(RowInfo rowInfo, Column column);
}
