package com.gpj.tool.db.core.io;

import com.gpj.tool.db.core.config.ToolConfig;
import com.gpj.tool.db.core.handler.DefaultExcelHandler;
import com.gpj.tool.db.core.pojo.RowInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

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
@Component
public class DefaultExcelReader {
    private static final String NOTE_MARK = "--";
    private static final String END_MARK = ">>>";

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    private ToolConfig config;

    @Autowired
    private DefaultExcelHandler excelHandler;

    public void read(){
        InputStream in = null;
        XSSFWorkbook workbook = null;
        try {
            File file = resourceLoader.getResource(config.getExcelPath()).getFile();
            in = new BufferedInputStream(new FileInputStream(file));
            ZipSecureFile.setMinInflateRatio(-1.0d);
            workbook = new XSSFWorkbook(in);
            analysis(workbook);
        } catch (Exception e) {
            log.error("excel文件读取失败:{}", e.toString(), e);
        } finally {
            try {
                if(workbook != null){
                    workbook.close();
                }
                if(in != null){
                    in.close();
                }
            } catch (IOException e) {
                log.error("read资源关闭失败:{}", e.toString(), e);
            }
        }
    }

    private void analysis(XSSFWorkbook workbook) throws Exception {
        if(workbook == null) throw new Exception("workBook is null");

        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            XSSFSheet sheet = workbook.getSheetAt(i);
            if(sheet != null){
                for (Row row : sheet) {
                    RowInfo rowInfo = new RowInfo(row);
                    if(isEnd(rowInfo)){
                        break;
                    }else if(!isNote(rowInfo) && rowInfo.getRowNum() != 0){
                        excelHandler.rowHandle(new RowInfo(row));
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

