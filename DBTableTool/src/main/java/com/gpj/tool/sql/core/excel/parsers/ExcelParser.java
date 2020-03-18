package com.gpj.tool.sql.core.excel.parsers;

import com.gpj.tool.sql.core.pojo.Column;
import com.gpj.tool.sql.core.pojo.RowInfo;

public interface ExcelParser {

    default public boolean skip(){
        return false;
    }
}
