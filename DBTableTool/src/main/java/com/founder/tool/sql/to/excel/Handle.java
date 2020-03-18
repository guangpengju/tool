package com.founder.tool.sql.to.excel;

import com.founder.tool.sql.custom.mysql.MysqlColumnTypeEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @className Handle
 * @description TODO
 * @author GPJ
 * @date 2019/7/16 13:45
 * @version 1.0
 **/
public interface Handle {
    void handle(XSSFWorkbook workBook, XSSFCellStyle cellStyle, String line);

    default XSSFCellStyle setbackground(XSSFCellStyle style){
        style.setFillForegroundColor(IndexedColors.BRIGHT_GREEN1.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return style;
    }

    default void createCell(XSSFSheet sheet, int rowNum, int cellNum, String data, XSSFCellStyle style){
        XSSFRow row = sheet.getRow(rowNum);
        if(row == null) row = sheet.createRow(rowNum);
        XSSFCell cell = row.createCell(cellNum);
        cell.setCellValue(data == null?null:data.trim());
        if(style != null) row.setRowStyle(style);
        if(style != null) cell.setCellStyle(style);
    }

    default String[] split(String data, String regex){
        String[] words = data.trim().split(regex);
        List<String> list = new ArrayList<>();
        for (String word : words) {
            if(StringUtils.isNotBlank(word)) list.add(word);
        }
        return list.toArray(new String[0]);
    }

    default boolean startsWith(String line, String wordStr){
        String[] words = wordStr.split(",");
        for (String word : words) {
            if(line.toUpperCase().startsWith(word.toUpperCase())) return true;
        }
        return false;
    }

    default String replace(String line, String wordStr, String target){
        String[] words = wordStr.split(",");
        for (String word : words) {
            line = line.replace(word,target);
        }
        return line;
    }

    default String subStringIndexOf(String line, String startRegex, String endRegex){
        if(StringUtils.isNotBlank(line)){
            int startIndex = StringUtils.isNotBlank(startRegex)?line.indexOf(startRegex) + startRegex.length():0;
            int endIndex = StringUtils.isNotBlank(endRegex)?line.lastIndexOf(endRegex):line.length();
            return subStringIndexOf(line, startIndex, endIndex);
        }
        return null;
    }

    default String subStringIndexOf(String line, String startRegex, int endIndex){
        if(StringUtils.isNotBlank(line)){
            int startIndex = StringUtils.isNotBlank(startRegex)?line.indexOf(startRegex) + startRegex.length():0;
            return subStringIndexOf(line, startIndex, endIndex);
        }
        return null;
    }

    default String subStringIndexOf(String line, int startIndex, String endRegex){
        if(StringUtils.isNotBlank(line)){
            int endIndex = StringUtils.isNotBlank(endRegex)?line.indexOf(endRegex):line.length();
            return subStringIndexOf(line, startIndex, endIndex);
        }
        return null;
    }

    default String subStringIndexOf(String line, int startIndex, int endIndex){
        if(StringUtils.isNotBlank(line) && startIndex >= 0 && endIndex <= line.length() && startIndex < endIndex){
            return line.substring(startIndex, endIndex);
        }
        return null;
    }

}
