package com.gpj.tool.sql.core.excel.parsers;

import com.gpj.tool.sql.core.pojo.RowInfo;
import com.gpj.tool.sql.core.pojo.Table;
import com.gpj.tool.sql.store.InfoStore;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @className CoreTableExcelParser
 * @description TODO
 * @author GPJ
 * @date 2020/3/18 17:07
 * @version 1.0
 **/
public class CoreTableExcelParser{
    @Autowired
    InfoStore store;

    @Autowired
    private List<TableExcelParser> tableParsers;

    public void analysisForTableInfo(RowInfo rowInfo){
        if(StringUtils.equals(rowInfo.getSheetName(), CoreExcelParser.SHEET_NAME_TABLE)){
            String tableName = rowInfo.getCellValue(0);
            if(StringUtils.isNotBlank(tableName)){
                Table table = store.RegisterTable(tableName);
                tableParsers.stream().filter(parser -> parser.skip()).forEach(parser ->{
                    parser.analysis(rowInfo, table);
                });
            }
        }
    }
}
