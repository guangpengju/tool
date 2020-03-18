package com.founder.tool.sql.common.utils;

import org.apache.poi.ss.usermodel.Row;

/**
 * @className LogInfoUtils
 * @description TODO
 * @author GPJ
 * @date 2019/7/17 16:55
 * @version 1.0
 **/
public class LogInfoUtils {

    public static String getCellInfo(Row row){
        return getCellInfo(row, null);
    }

    public static String getCellInfo(Row row, Integer cellIndex){
        if(cellIndex != null){
            return String.format("[%s]页[%s]行[%s]列", row.getSheet().getSheetName(), row.getRowNum() + 1, cellIndex + 1);
        }
        return String.format("[%s]页[%s]行", row.getSheet().getSheetName(), row.getRowNum() + 1);
    }
}
