package com.founder.tool.sql.common.parser;

import com.founder.tool.sql.common.enums.SheetEnum;
import com.founder.tool.sql.common.parser.AbstractExcelParser;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @className AbstractTableExcelParser
 * @description 抽象excel文件table的sheet页解析器
 * @author GPJ
 * @date 2019/7/10 18:10
 * @version 1.0
 **/
public abstract class AbstractTableExcelParser extends AbstractExcelParser {
    @Override
    protected XSSFSheet chooseSheet(XSSFWorkbook workbook) {
        return workbook.getSheet(SheetEnum.TABLE.getType());
    }

    @Override
    protected void handle(Row row) {
        analysisTable(row);
    }

    abstract protected void analysisTable(Row row);
}
