package com.gpj.tool.sql.core.io;

import com.gpj.tool.sql.core.config.ConfigInfo;
import com.gpj.tool.sql.core.excel.parsers.DefaultCoreExcelParser;
import com.gpj.tool.sql.core.exceptions.ExcelFileReaderException;
import com.gpj.tool.sql.core.pojo.RowInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;

/**
 * @className DefaultExcelReader
 * @description
 *   读取Excel文件内容转存入缓存
 * @author GPJ
 * @date 2020/3/18 15:55
 * @version 1.0
 **/
@Slf4j
public class DefaultExcelReader {
    private static final String NOTE_MARK = "--";
    private static final String END_MARK = ">>>";

    @Autowired
    private ConfigInfo config;

    @Autowired
    private DefaultCoreExcelParser excelParser;

    public void read(){
        InputStream in = null;
        XSSFWorkbook workbook = null;
        try {
            File file = new File(config.getExcelPath(), config.getExcelName());
            in = new BufferedInputStream(new FileInputStream(file));
            ZipSecureFile.setMinInflateRatio(-1.0d);
            workbook = new XSSFWorkbook(in);
            analysis(workbook);
        } catch (IOException | ExcelFileReaderException e) {
            log.error("excel文件读取失败:{}", e);
        }
    }

    private void analysis(XSSFWorkbook workbook) throws ExcelFileReaderException {
        if(workbook == null) throw new ExcelFileReaderException("workBook is null");

        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            XSSFSheet sheet = workbook.getSheetAt(i);
            if(sheet != null){
                for (Row row : sheet) {
                    RowInfo rowInfo = new RowInfo(row);
                    if(!isEnd(rowInfo) && !isNote(rowInfo)){
                        excelParser.rowParser(new RowInfo(row));
                    }
                }
            }
        }
    }

    private boolean isEnd(RowInfo rowInfo){
        return StringUtils.equals(END_MARK, rowInfo.getCellValue(0));
    }

    private boolean isNote(RowInfo rowInfo){
        return StringUtils.equals(NOTE_MARK, rowInfo.getCellValue(0));
    }
}

