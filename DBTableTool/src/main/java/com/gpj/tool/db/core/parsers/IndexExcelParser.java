package com.gpj.tool.db.core.parsers;

import com.gpj.tool.db.core.pojo.Index;
import com.gpj.tool.db.core.pojo.RowInfo;

public interface IndexExcelParser {
    default public boolean skip(RowInfo rowInfo, Index index){
        return false;
    }

    public boolean analysis(RowInfo rowInfo, Index index);
}
