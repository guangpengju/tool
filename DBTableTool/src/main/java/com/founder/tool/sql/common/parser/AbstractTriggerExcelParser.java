package com.founder.tool.sql.common.parser;

import com.founder.tool.sql.common.enums.SheetEnum;
import com.founder.tool.sql.common.parser.AbstractExcelParser;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @className AbstractTriggerExcelParser
 * @description 抽象excel文件trigger的sheet页解析器
 * @author GPJ
 * @date 2019/7/10 18:24
 * @version 1.0
 **/
public abstract class AbstractTriggerExcelParser extends AbstractExcelParser {
    @Override
    protected XSSFSheet chooseSheet(XSSFWorkbook workbook) {
        return workbook.getSheet(SheetEnum.TRIGGER.getType());
    }

    @Override
    protected void handle(Row row) {
        analysisTrigger(row);
    }

    abstract protected void analysisTrigger(Row row);
}
