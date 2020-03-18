package com.founder.tool.sql.common.utils;

import com.founder.tool.sql.common.exception.ExcelParserException;
import com.founder.tool.sql.common.exception.SQLToolIOException;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

/**
 * @className IOUtils
 * @description TODO
 * @author GPJ
 * @date 2019/7/11 13:44
 * @version 1.0
 **/
@Slf4j
public class IOUtils {
    public static InputStream initInputstream(String path, String fileName){
        try {
            File file = new File(path, fileName);
            return new BufferedInputStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            throw new SQLToolIOException("文件读取错误,文件不存在!", e.getMessage());
        }
    }


    public static XSSFWorkbook initExcelReader(InputStream in){
        try {
            ZipSecureFile.setMinInflateRatio(-1.0d);
            return new XSSFWorkbook(in);
        } catch (IOException e) {
            throw new ExcelParserException("excel读取错误!", e.getMessage());
        }
    }

    public static OutputStream initOutputstream(String path, String fileName){
        try {
            File dir = new File(path);
            if(!dir.exists() || dir.isDirectory()){
                if(!dir.exists()) dir.mkdirs();

                File file = new File(path, fileName);
                return new BufferedOutputStream(new FileOutputStream(file));
            }else{
                throw new SQLToolIOException("输出路径错误!", path);
            }
        } catch (FileNotFoundException e) {
            throw new SQLToolIOException("文件读取错误,文件不存在!", e.getMessage());
        }
    }

    public static void closeInputStream(InputStream in){
        try {
            if(in != null) in.close();
        } catch (IOException e) {
            log.warn("输入流关闭错误!{}", e.getMessage());
        }
    }

    public static void closeExcelReader(InputStream in, XSSFWorkbook workbook){
        try {
            if(in != null) in.close();
            if(workbook != null) workbook.close();
        } catch (IOException e) {
            log.warn("输入流关闭错误!{}", e.getMessage());
        }
    }

    public static void closeOutputStream(OutputStream out){
        try {
            if(out != null) out.close();
        } catch (IOException e) {
            log.warn("输出流关闭错误!{}", e.getMessage());
        }
    }
}
