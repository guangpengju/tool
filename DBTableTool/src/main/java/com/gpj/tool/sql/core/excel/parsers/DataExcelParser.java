package com.gpj.tool.sql.core.excel.parsers;

import com.gpj.tool.sql.core.pojo.Data;
import com.gpj.tool.sql.core.pojo.RowInfo;

public interface DataExcelParser {
    default public boolean skip(RowInfo rowInfo, Data data){
        return false;
    }

    public boolean analysis(RowInfo rowInfo, Data data);
}
