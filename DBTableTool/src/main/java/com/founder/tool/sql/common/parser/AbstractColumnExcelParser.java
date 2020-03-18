package com.founder.tool.sql.common.parser;

import com.founder.tool.sql.common.enums.SheetEnum;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @className AbstractColumnExcelParser
 * @description 抽象excel文件column的sheet页解析器
 * @author GPJ
 * @date 2019/7/10 18:23
 * @version 1.0
 **/
public abstract class AbstractColumnExcelParser extends AbstractExcelParser {
    @Override
    protected XSSFSheet chooseSheet(XSSFWorkbook workbook) {
        return workbook.getSheet(SheetEnum.COLUMN.getType());
    }

    @Override
    protected void handle(Row row) {
        analysisColumn(row);
    }

    abstract protected void analysisColumn(Row row);
}
