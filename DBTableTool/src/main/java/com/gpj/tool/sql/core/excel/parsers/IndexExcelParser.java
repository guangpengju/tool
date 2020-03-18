package com.gpj.tool.sql.core.excel.parsers;

import com.gpj.tool.sql.core.pojo.Index;
import com.gpj.tool.sql.core.pojo.RowInfo;

public interface IndexExcelParser {
    default public boolean skip(RowInfo rowInfo, Index index){
        return false;
    }

    public boolean analysis(RowInfo rowInfo, Index index);
}
