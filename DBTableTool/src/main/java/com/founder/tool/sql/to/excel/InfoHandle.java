package com.founder.tool.sql.to.excel;

import com.founder.tool.sql.common.utils.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @className InfoHandle
 * @description TODO
 * @author GPJ
 * @date 2019/7/17 15:13
 * @version 1.0
 **/
public class InfoHandle {
    private static Map<String, String> tableInfo = new HashMap<>();
    private static Map<String, String> colInfo = new HashMap<>();

    public void handle(XSSFWorkbook excelWB, XSSFWorkbook infoWB){
        XSSFCellStyle style = excelWB.createCellStyle();

        infoParser(infoWB.getSheet("info"));
        tableInfo(excelWB.getSheet("table"), style);
        columnInfo(excelWB.getSheet("column"), style);
    }

    private void columnInfo(XSSFSheet sheet, XSSFCellStyle style){
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            String val = getStringCellValue(sheet, i, 0).trim().toLowerCase();
            if(StringUtils.equals("--", val)){
                String tableCode = getStringCellValue(sheet, i, 1).trim().toLowerCase();
                if(tableInfo.containsKey(tableCode)){
                    String tableName = tableInfo.get(tableCode);
                    sheet.getRow(i).getCell(1).setCellValue(tableName);
                }
            }else {
                String colCode = getStringCellValue(sheet, i, 1).trim().toLowerCase();
                String key = val + "-" + colCode;
                if (colInfo.containsKey(key)) {
                    String colName = colInfo.get(key);
                    createCell(sheet, i, 7, colName, null);
                }
            }
        }
    }

    private void tableInfo(XSSFSheet sheet, XSSFCellStyle style){
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            String tableCode = getStringCellValue(sheet, i, 0).trim().toLowerCase();
            if(tableInfo.containsKey(tableCode)){
                String tableName = tableInfo.get(tableCode);
                createCell(sheet, i, 1, tableName, null);
                createCell(sheet, i, 2, tableName, null);
            }
        }
    }
    
    private void infoParser(XSSFSheet sheet){
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            String tablCode = getStringCellValue(sheet, i, 0);
            String tableName = getStringCellValue(sheet, i, 1);
            String colCode = getStringCellValue(sheet, i, 2);
            String colName = getStringCellValue(sheet, i, 3);

            putMap(tablCode, tableName, tableInfo);
            putMap(tablCode + "-" + colCode, colName, colInfo);
        }
    }

    private String getStringCellValue(XSSFSheet sheet, int rowNum, int cellNum){
        return sheet.getRow(rowNum).getCell(cellNum).getStringCellValue();
    }

    private void putMap(String key, String val, Map<String, String> map){
        if(!map.containsKey(key)) map.put(key.trim().toLowerCase(), val);
    }

    private void createCell(XSSFSheet sheet, int rowNum, int cellNum, String data, XSSFCellStyle style){
        XSSFRow row = sheet.getRow(rowNum);
        if(row == null) row = sheet.createRow(rowNum);
        XSSFCell cell = row.createCell(cellNum);
        cell.setCellValue(data);
        if(style != null) row.setRowStyle(style);
        if(style != null) cell.setCellStyle(style);
    }

    private XSSFCellStyle setbackground(XSSFCellStyle style){
        style.setFillForegroundColor(IndexedColors.BRIGHT_GREEN1.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return style;
    }

}
