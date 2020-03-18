package com.founder.tool.sql.to.excel;

import com.founder.tool.sql.common.utils.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;

/**
 * @className SqlToExcel
 * @description TODO
 * @author GPJ
 * @date 2019/7/15 18:23
 * @version 1.0
 **/
public class SqlToExcel {
    public static void main(String[] args) throws Exception {
        SqlToExcel.readsql("E:\\gtmp", "xy6.xlsx", "xy60.sql",new SqlToExcelTableHandle());
        SqlToExcel.readsql("E:\\gtmp", "xy6.xlsx", "xy60-pt.sql",new SqlToExcelPTHandle());
//        SqlToExcel.readExcel("E:\\gtmp", "xy6.xlsx", "info.xlsx",new InfoHandle());
    }

    public static void readsql(String path, String excelFileName, String sqlFileName, Handle handle) throws Exception {
        InputStream in = IOUtils.initInputstream(path, excelFileName);
        XSSFWorkbook workBook = IOUtils.initExcelReader(in);

        InputStream stream = IOUtils.initInputstream(path, sqlFileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        String line = null;
        XSSFCellStyle style = workBook.createCellStyle();
        while((line = reader.readLine()) != null){
            if(StringUtils.isNotBlank(line)){
                handle.handle(workBook, style ,line.trim());
            }
        }
        FileOutputStream out = new FileOutputStream(path + "\\xy6.xlsx");
        workBook.write(out);

        reader.close();
        IOUtils.closeExcelReader(in, workBook);
        out.close();
    }

    public static void readExcel(String path, String excelFileName, String infolFileName, InfoHandle handle) throws Exception {
        InputStream excelIn = IOUtils.initInputstream(path, excelFileName);
        XSSFWorkbook excelWB = IOUtils.initExcelReader(excelIn);

        InputStream infoIn = IOUtils.initInputstream(path, infolFileName);
        XSSFWorkbook infoWB = IOUtils.initExcelReader(infoIn);

        handle.handle(excelWB, infoWB);

        FileOutputStream out = new FileOutputStream(path + "\\" + excelFileName);
        excelWB.write(out);
        out.close();

        IOUtils.closeExcelReader(excelIn, excelWB);
        IOUtils.closeExcelReader(infoIn, infoWB);
    }
}
/*
    列类型补充 D类型 date

*/