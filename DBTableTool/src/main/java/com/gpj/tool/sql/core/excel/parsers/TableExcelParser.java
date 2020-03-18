package com.gpj.tool.sql.core.excel.parsers;

import com.gpj.tool.sql.core.pojo.RowInfo;
import com.gpj.tool.sql.core.pojo.Table;
import com.gpj.tool.sql.store.InfoStore;

public interface TableExcelParser  extends ExcelParser{
    public void analysis(RowInfo rowInfo, Table table);
}
