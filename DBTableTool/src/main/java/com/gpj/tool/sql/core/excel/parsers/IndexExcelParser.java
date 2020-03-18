package com.gpj.tool.sql.core.excel.parsers;

import com.gpj.tool.sql.core.pojo.Column;
import com.gpj.tool.sql.core.pojo.Index;
import com.gpj.tool.sql.core.pojo.RowInfo;

public interface IndexExcelParser extends ExcelParser {
    public void analysis(RowInfo rowInfo, Index index);
}
