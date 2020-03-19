package com.gpj.tool.db.core.parsers;

import com.gpj.tool.db.core.pojo.Column;
import com.gpj.tool.db.core.pojo.RowInfo;

public interface ColumnExcelParser {
    default public boolean skip(RowInfo rowInfo, Column column){
        return false;
    }

    public boolean analysis(RowInfo rowInfo, Column column);
}
