package com.founder.tool.sql.to.excel;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * @className SqlToExcelHandle
 * @description TODO
 * @author GPJ
 * @date 2019/7/16 9:40
 * @version 1.0
 **/
public class SqlToExcelPTHandle implements Handle{
    private static final String SHEET_PROCEDURE = "procedure";
    private static final String SHEET_TRIGGER = "trigger";

    private static int procedureIndex = 1;
    private static String procedureName;
    private static String procedureCur;
    private static List<String> procedureCursor = new ArrayList<>();

    private static int startDeclareNum= -1;
    private static int endDeclareNum= -1;

    private static int triggerIndex = 1;
    private static String triggerName;

    @Override
    public void handle(XSSFWorkbook workBook, XSSFCellStyle cellStyle, String line){
        procedureHandle(workBook.getSheet(SHEET_PROCEDURE), cellStyle, line);
        triggerHandle(workBook.getSheet(SHEET_TRIGGER), cellStyle, line);
    }

    private void procedureHandle(XSSFSheet sheet, XSSFCellStyle style, String line){
        if(startsWith(line, "--,begin")) return;

        if(startsWith(line, "CREATE DEFINER=`root`@`%` PROCEDURE")){
            line = replace(line, "CREATE DEFINER=`root`@`%` PROCEDURE", "");
            procedureName = subStringIndexOf(line,"`","`");
            out.println("正在解析存储过程[" + procedureName + "]");
            createProcedureInfo(sheet, procedureIndex++, "--", null,procedureName, "", setbackground(style));

            String[] params = subStringIndexOf(line,"(",")").split(",");
            for (String param : params) {
                String[] datas = split(param, " ");
                if(datas.length < 3 || StringUtils.equalsIgnoreCase("in",datas[0])){
                    createCell(sheet, procedureIndex++, procedureName, "IN", datas[0], datas[1]);
                }else if(StringUtils.equalsIgnoreCase("out",datas[0])){
                    createCell(sheet, procedureIndex++, procedureName, "OUT", datas[1], datas[2]);
                }else if(StringUtils.equalsIgnoreCase("inout",datas[0])){
                    createCell(sheet, procedureIndex++, procedureName, "INOUT", datas[1], datas[2]);
                }
            }
            startDeclareNum = procedureIndex;
        }else if(StringUtils.isNotBlank(procedureName) && line.contains("declare ")){
            if(line.contains(" cursor ")){
                procedureCur = subStringIndexOf(line,"declare","cursor for");
                String assist = subStringIndexOf(line, "cursor for", ";");
                createCell(sheet, procedureIndex++, procedureName, "CURSOR_DEF", procedureCur, assist);
            }else if(line.contains(" continue ")){
                String assist = subStringIndexOf(line, "for not found set", "=").trim();
                procedureCursor.add(procedureCur + "-" + assist);
                createCell(sheet, procedureIndex++, procedureName, "CURSOR_END", procedureCur, assist);
                procedureCur = null;
            }else{
                line = replace(line, "declare,;", "");
                String[] datas = split(line, " ");
                createCell(sheet, procedureIndex++, procedureName, "DECLARE", datas[0], datas[1]);
            }
            endDeclareNum = procedureIndex;
        }else if(StringUtils.isNotBlank(procedureName)){
            for (String cursor : procedureCursor) {
                if(line.contains(cursor.split("-")[1])){
                    if(line.contains("set")){
                        return;
                    }else if(line.contains("while")){
                        createCell(sheet, procedureIndex++, procedureName, "BODY", cursor.split("-")[0], "curesor_end");
                        return;
                    }
                }
            }

            if(StringUtils.equalsIgnoreCase("end", line.trim())){
                removeCurEnd(sheet);
                procedureName = null;
                procedureCur = null;
                procedureCursor.clear();
            }else{
                createCell(sheet, procedureIndex++, procedureName, "BODY", whileReplace(line.trim()), null);
            }
        }
    }

    private String whileReplace(String sql){
        sql = sql.toLowerCase();
        if(sql.matches("^\\s*\\bwhile\\b\\s+.+\\s\\bdo\\b\\s*$")){
            sql = sql.replaceAll("\\bdo\\b","loop");
        }
        if(sql.matches("^\\s*\\bend\\b\\s+\\bwhile\\b\\s*;\\s*$")){
            sql = sql.replaceAll("\\bwhile\\b","loop");
        }
        return sql;
    }

    private void triggerHandle(XSSFSheet sheet, XSSFCellStyle style, String line){
        if(startsWith(line, "--")) return;

        if(startsWith(line, "CREATE DEFINER = `root`@`%` TRIGGER")){
            line = replace(line, "CREATE DEFINER = `root`@`%` TRIGGER,FOR EACH ROW begin,ON,`", "").trim();
            String[] datas = split(line, " ");

            triggerName = datas[0];
            out.println("正在解析触发器[" + triggerName + "]");
            try {
                createProcedureInfo(sheet, triggerIndex++, "--", null,triggerName, null, setbackground(style));
                createCell(sheet, triggerIndex++, triggerName, "TIME",datas[1], null);
                createCell(sheet, triggerIndex++, triggerName, "EVENT",datas[2], null);
                createCell(sheet, triggerIndex++, triggerName, "TABLE",datas[3], null);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if(StringUtils.isNotBlank(triggerName) && line.contains("declare ")){
                line = replace(line, "declare,;", "").trim();
                String[] datas = split(line, " ");
                createCell(sheet, triggerIndex++, triggerName, "DECLARE", datas[0], datas[1]);
        }else if(StringUtils.isNotBlank(triggerName)){
            if(StringUtils.equalsIgnoreCase("end", line.trim())){
                triggerName = null;
            }else{
                createCell(sheet, triggerIndex++, triggerName, "BODY",  whileReplace(line.trim()), null);
            }
        }
    }

    /***************************************** 私有方法 ********************************************/
    private void createCell(XSSFSheet sheet, int RowNum, String name, String type, String data, String assist){
        createProcedureInfo(sheet, RowNum, name, type, data, assist, null);
    }

    private void removeCurEnd(XSSFSheet sheet){
        if(!procedureCursor.isEmpty()){
            for (int i = startDeclareNum; i <= endDeclareNum ; i++) {
                XSSFRow row = sheet.getRow(i);
                String value = row.getCell(2).getStringCellValue().trim();
                for (String cur : procedureCursor) {
                    if(StringUtils.equalsIgnoreCase(cur, value)){
                        sheet.shiftRows(i + 1, sheet.getLastRowNum(),-1);
                        procedureIndex--;
                    }
                }
            }
        }
    }

    private void createProcedureInfo(XSSFSheet sheet, int RowNum, String name, String type, String data, String assist, XSSFCellStyle style){
        createCell(sheet, RowNum, 0, name.trim(),style);
        createCell(sheet, RowNum, 1, StringUtils.isNotBlank(type)?type.trim():null, style);
        createCell(sheet, RowNum, 2, data.trim(), style);
        createCell(sheet, RowNum, 3, StringUtils.isNotBlank(assist)?assist.trim():null, style);
    }
}
