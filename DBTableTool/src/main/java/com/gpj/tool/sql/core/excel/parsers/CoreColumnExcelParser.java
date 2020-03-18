package com.gpj.tool.sql.core.excel.parsers;

import com.gpj.tool.sql.core.exceptions.ExcelAnalysisException;
import com.gpj.tool.sql.core.pojo.Column;
import com.gpj.tool.sql.core.pojo.RowInfo;
import com.gpj.tool.sql.core.pojo.Table;
import com.gpj.tool.sql.store.InfoStore;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @className CoreColumnExcelParser
 * @description TODO
 * @author GPJ
 * @date 2020/3/18 17:07
 * @version 1.0
 **/
public class CoreColumnExcelParser {
    @Autowired
    InfoStore store;

    @Autowired
    private List<ColumnExcelParser> columnParsers;

    public void analysisForColumnInfo(RowInfo rowInfo) throws ExcelAnalysisException {
        if(StringUtils.equals(rowInfo.getSheetName(), CoreExcelParser.SHEET_NAME_COLUMN)){
            String tableName = rowInfo.getCellValue(0);
            String columnName = rowInfo.getCellValue(1);
            if(StringUtils.isNotBlank(tableName)){
                Column column = store.RegisterColumn(tableName, columnName);
                columnParsers.stream().filter(parser -> parser.skip()).forEach(parser ->{
                    parser.analysis(rowInfo, column);
                });
            }
        }
    }
}
