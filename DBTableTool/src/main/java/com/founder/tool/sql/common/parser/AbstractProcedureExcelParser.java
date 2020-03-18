package com.founder.tool.sql.common.parser;

import com.founder.tool.sql.common.enums.SheetEnum;
import com.founder.tool.sql.common.parser.AbstractExcelParser;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @className AbstractProcedureExcelParser
 * @description 抽象excel文件procedure的sheet页解析器
 * @author GPJ
 * @date 2019/7/10 18:24
 * @version 1.0
 **/
public abstract class AbstractProcedureExcelParser extends AbstractExcelParser {
    @Override
    protected XSSFSheet chooseSheet(XSSFWorkbook workbook) {
        return workbook.getSheet(SheetEnum.PROCEDURE.getType());
    }

    @Override
    protected void handle(Row row) {
        analysisProcedure(row);
    }

    abstract protected void analysisProcedure(Row row);
}
