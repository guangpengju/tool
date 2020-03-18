package com.founder.tool.sql.common.parser;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @className ExcelParser
 * @description excel解析器
 * @author GPJ
 * @date 2019/7/10 15:37
 * @version 1.0
 **/
public interface ExcelParser {
    /**
     * 解析
     */
    public void analysis(XSSFWorkbook workbook);
}
