package com.founder.tool.sql.to.excel;

import com.founder.tool.sql.custom.mysql.MysqlColumnTypeEnum;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.*;

import static java.lang.System.out;

/**
 * @className SqlToExcelHandle
 * @description TODO
 * @author GPJ
 * @date 2019/7/16 9:40
 * @version 1.0
 **/
public class SqlToExcelTableHandle implements Handle{
    private static final String SHEET_TABLE = "table";
    private static final String SHEET_COLUMN = "column";
    private static final String SHEET_INDEX = "index";

    private static int tableCounter = 1;

    private static int columnCounter = 1;
    private static int columnPKStart = -1;
    private static int columnPKEnd = -1;
    private static String columnTableName;

    private static int indexCounter = 1;
    private static final String INDEX_ORDER_ASC = "ASC";
    private static final String INDEX_ORDER_DESC = "DESC";

    private String tmpTableName;

    @Override
    public void handle(XSSFWorkbook workBook, XSSFCellStyle cellStyle, String line){
        line = line.replace("`","");
        tableHandle(workBook.getSheet(SHEET_TABLE), line);
        columnHandle(workBook.getSheet(SHEET_COLUMN), cellStyle, line);
        indexHandle(workBook.getSheet(SHEET_INDEX), cellStyle, line);
    }

    private void tableHandle(XSSFSheet sheet, String line){
        if(startsWith(line, "CREATE TABLE")){
            line = replace(line,"CREATE TABLE,(","").trim();
            out.println("创建表[" + line + "]");

            int rowNum = tableCounter++;
            createCell(sheet,rowNum,0,line,null);
            createCell(sheet,rowNum,4,"ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic",null);
        }
    }

    private void columnHandle(XSSFSheet sheet, XSSFCellStyle style, String line){
        if(startsWith(line, "DROP,create index,--,),insert,INDEX,UNIQUE INDEX")) return;

        if(startsWith(line, "PRIMARY KEY")){
            line = subStringIndexOf(line,"(", ")");
            String[] pks = split(line, ",");
            if(columnPKEnd > 0){
                for (int i = columnPKStart; i < columnPKEnd ; i++) {
                    String val = sheet.getRow(i).getCell(1).getStringCellValue();
                    for (String pk : pks) {
                        if(StringUtils.equalsIgnoreCase(pk.trim(), val.trim())){
                            createCell(sheet, i, 3, "1", null);
                        }
                    }
                }
            }
        }else if(startsWith(line, "CREATE TABLE")){
            columnTableName = replace(line,"CREATE TABLE,(","").trim();

            int rowNum = columnCounter++;
            createCell(sheet, rowNum, 0, "--", setbackground(style));
            createCell(sheet, rowNum, 1, columnTableName, setbackground(style));
            columnPKStart = columnCounter;
            columnPKEnd = -1;
        }else{
            String[] words = split(line," ");

            int rowNum = columnCounter++;
            columnPKEnd = columnCounter;

            createCell(sheet, rowNum, 0, columnTableName, null);// 表名
            createCell(sheet, rowNum, 1, words[0], null);// 字段名称
            if(line.contains("NOT NULL")) createCell(sheet, rowNum, 2, "1", null);// 必填
//                createCell(sheet, rowNum, 3, columnTableName, null);// 主键

            // 类型
            String typeIn = line.contains("(")?words[1].substring(0, words[1].indexOf("(")):words[1];
            String type = typeConverter(typeIn.replace(",",""));
            if(StringUtils.isNotBlank(type)){
                createCell(sheet, rowNum, 4, type, null);
            }

            // 长度,精度
            if(line.contains("(") && line.contains(")")){
                String len = line.substring(line.indexOf("(") + 1,line.indexOf(")"));
                if(StringUtils.isNotBlank(len)){
                    String[] lens = len.split(",");
                    createCell(sheet, rowNum, 5, lens[0], null);// 长度
                    if(lens.length > 1){
                        createCell(sheet, rowNum, 6, lens[1], null);// 精度
                    }
                }
            }

            // 属性
            if(line.contains("utf8")){
                createCell(sheet, rowNum, 10, "CHARACTER SET utf8 COLLATE utf8_general_ci", null);// 属性
            }
        }
    }

    private void indexHandle(XSSFSheet sheet, XSSFCellStyle style, String line){
        if(startsWith(line, "CREATE TABLE")) {
            line = replace(line, "CREATE TABLE,(", "").trim();
            tmpTableName = line;
        }

        if(startsWith(line, "INDEX") || startsWith(line, "UNIQUE INDEX")){
            try {
//                line = replace(line, "create index ,;", "");
                if(startsWith(line, "INDEX")){
                    line = line.substring(line.indexOf("INDEX") + "INDEX".length(), line.indexOf("USING"));
                }else{
                    line = line.substring(line.indexOf("UNIQUE INDEX") + "UNIQUE INDEX".length(), line.indexOf("USING"));
                }
                line = replace(line, " on ,(,)", "|");

                String[] words = split(line.trim(), "\\|");
                if(words.length > 1){
                    String indexName = words[0];
                    String tableName = tmpTableName;

                    String[] columns = words[1].split(",");

                    int rowNoteNum = indexCounter++;
                    createCell(sheet, rowNoteNum, 0, "--", setbackground(style));
                    createCell(sheet, rowNoteNum, 1, indexName.trim().toUpperCase(), setbackground(style));

                    for (String column : columns) {
                        int rowNum = indexCounter++;
                        createCell(sheet, rowNum, 0, tableName.trim().toLowerCase(), null);
                        createCell(sheet, rowNum, 1, indexName.trim().toUpperCase(), null);
                        createCell(sheet, rowNum, 2, column.trim().toLowerCase(), null);
                        createCell(sheet, rowNum, 3, INDEX_ORDER_ASC, null);
                    }
                }else{
                    throw new RuntimeException("错误:" + line);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /***************************************** 私有方法 ********************************************/
    private String typeConverter(String type){
        MysqlColumnTypeEnum[] values = MysqlColumnTypeEnum.values();
        for (MysqlColumnTypeEnum value : values) {
            if(StringUtils.equals(value.getRealType(), type.trim().toLowerCase())){
                return value.getType();
            }
        }
        if(StringUtils.equalsIgnoreCase("clob", type)){
            return "TEXT";
        }
        throw new RuntimeException("类型找不到[" + type + "]");
    }
}
