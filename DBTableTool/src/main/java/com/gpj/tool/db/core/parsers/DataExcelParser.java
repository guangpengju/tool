package com.gpj.tool.db.core.parsers;

import com.gpj.tool.db.core.pojo.Data;
import com.gpj.tool.db.core.pojo.RowInfo;

public interface DataExcelParser {
    default public boolean skip(RowInfo rowInfo, Data data){
        return false;
    }

    public boolean analysis(RowInfo rowInfo, Data data);
}
