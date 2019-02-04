package com.gpj.tool.sqltool.utils;

import com.gpj.tool.sqltool.enums.ColsType;
import com.gpj.tool.sqltool.pojo.Table;
import com.gpj.tool.sqltool.pojo.TableCol;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Slf4j
public class FileHandler {
    
    public static List<Table> readExcel(String path){
        InputStream inputStream = null;
        XSSFWorkbook workbook = null;
        try{
            inputStream = new FileInputStream(path);
            workbook = new XSSFWorkbook(inputStream);
            Map<String, Table> tableMap = readTableSheet(workbook);
            Map<String, List<TableCol>> colsMap = readColsSheet(workbook);
            List<Table> tables = creatTables(tableMap, colsMap);
            log.info("解析出数据可设计文件成功,共创建【{}】张表",tables.size());
            if(tables != null && !tables.isEmpty()){
                return tables;
            }
        } catch (IOException e) {
            log.error("excel读取错误:{}",e.toString());
        } finally{
            if(inputStream != null){
                try{
                    inputStream.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(workbook != null){
                try{
                    workbook.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    
    public static void writeSQL(String dirPath,List<Table> tables){
        OutputStreamWriter out = null;
        XSSFWorkbook workbook = null;
        try{

            out = new OutputStreamWriter(new FileOutputStream(dirPath + "db_init.sql"),"utf-8");
            for(Table table : tables){
                String sql = Table2SQL(table);
                out.append(sql);
            }
        } catch (IOException e) {
            log.error("excel读取错误:{}",e.toString());
        } finally{
            if(out != null){
                try{
                    out.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        
    }
    
    
    private static Map<String, Table> readTableSheet(XSSFWorkbook workbook){
        Map<String,Table> tableMap = new HashMap<>();
        
        XSSFSheet tableSheet = workbook.getSheet("table");
        for(Row row : tableSheet){
            if(row.getRowNum() == 0) continue;
            if(row.getRowNum() == 25){
                System.out.println("");
            }
            String tableName = getColStringValue(row,0);
            if(StringUtils.equals(">>>>",tableName)){
                break;
            }else if(StringUtils.isNotBlank(tableName)){
                String tableRemark = getColStringValue(row,1);
                tableMap.put(tableName,new Table(tableName,tableRemark,null));
            }
        }
        return tableMap;
    }

    private static Map<String,List<TableCol>> readColsSheet(XSSFWorkbook workbook){
        Map<String,List<TableCol>> colsMap = new HashMap<>();

        XSSFSheet tableSheet = workbook.getSheet("col");
        for(Row row : tableSheet){
            String tableName = getColStringValue(row,0);
            if(row.getRowNum() == 0 || StringUtils.equals("--", tableName)) continue;
            if(StringUtils.equals(">>>>",tableName)){
                break;
            }else if(StringUtils.isNotBlank(tableName)){
                TableCol col = new TableCol(tableName,getColStringValue(row,1), 
                        ColsType.getColsType(getColStringValue(row,4)),
                        getColIntegerValue(row,2) == 1,  getColIntegerValue(row,3) == 1,
                        getColIntegerValue(row,5), getColIntegerValue(row,6),getColStringValue(row,7));
                if(colsMap.containsKey(tableName)){
                    List<TableCol> cols = colsMap.get(tableName);
                    cols.add(col);
                }else{
                    List<TableCol> cols = new ArrayList<>();
                    cols.add(col);
                    colsMap.put(tableName,cols);
                }
            }
        }
        return colsMap;
    }
    
    private static List<Table> creatTables(Map<String,Table> tableMap, Map<String,List<TableCol>> colsMap){
        List<Table> tables = new ArrayList<>();
        for(String key : tableMap.keySet()){
            List<TableCol> cols = colsMap.get(key);
            if(cols != null && !cols.isEmpty()){
                Table table = tableMap.get(key);
                table.setCols(cols);
                tables.add(table);
            }
        }
        return tables;
    }
    
    private static String getColStringValue(Row row, int index){
        if(row.getCell(index) != null){
            row.getCell(index).setCellType(Cell.CELL_TYPE_STRING);
            return row.getCell(index).getStringCellValue();
        }
        return null;
    }

    private static Integer getColIntegerValue(Row row, int index){
        if(row.getCell(index) != null){
            row.getCell(index).setCellType(Cell.CELL_TYPE_STRING);
            String val = row.getCell(index).getStringCellValue();
            if(StringUtils.isNotBlank(val)){
                return Integer.valueOf(val);
            }
        }
        return -1;
    }
    
    
    private static String Table2SQL(Table table){
        StringBuffer sql = new StringBuffer();
        List<TableCol> cols = table.getCols();
        
        if(cols != null && !cols.isEmpty()){
            sql.append("DROP TABLE ").append(table.getTableName()).append("; \n");
            sql.append("CREATE TABLE `").append(table.getTableName()).append("` (\n");
            StringBuffer pk = new StringBuffer("PRIMARY KEY (");
            boolean havePK = false;
            for(TableCol col : cols){
                ColsType type = col.getType();
                if(!StringUtils.isNotBlank(col.getCode()) || type == null) continue;
                
                sql.append("`").append(col.getCode()).append("` ").append(" ");
                
                sql.append(type.getCode());
                int length = col.getLength() != -1?col.getLength():type.getLength();
                int accuracy = col.getAccuracy() != -1?col.getAccuracy():type.getAccuracy();
                if(length != -1){
                    sql.append("(").append(length);
                    if(accuracy != -1){
                        sql.append(",").append(accuracy);
                    }
                    sql.append(") ");
                }

                String encode = type.getEncode();
                if(StringUtils.isNotBlank(encode)){
                    sql.append(" CHARACTER SET ").append(encode).append(" ");
                }
                
                if(col.isNotNull()){
                    sql.append(" NOT NULL ");
                }else{
                    sql.append(" DEFAULT ").append(type.getDefaultVal()).append(" ");
                }
                
                String remark = col.getRemark();
                if(StringUtils.isNotBlank(remark)){
                    sql.append(" COMMENT '").append(remark).append("'");
                }
                sql.append(" ,\n");
                
                if(col.isPK()){
                    havePK = true;
                    pk.append("`").append(col.getCode()).append("`,");
                }
            }
            sql.delete(sql.length()-2,sql.length());
            if(havePK){
                pk.delete(pk.length()-1,pk.length());
                sql.append(",\n").append(pk).append(") ");
            }
            sql.append("\n) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='").append(table.getTableRemark()).append("';\n\n");
            return sql.toString();
        }
        return null;
    }
}
