package com.gpj.tool.sql.core.excel.parsers;

import com.gpj.tool.sql.core.pojo.Column;
import com.gpj.tool.sql.core.pojo.RowInfo;
import com.gpj.tool.sql.core.pojo.Table;

public interface ColumnExcelParser extends ExcelParser{

    public void analysis(RowInfo rowInfo, Column column);
}
