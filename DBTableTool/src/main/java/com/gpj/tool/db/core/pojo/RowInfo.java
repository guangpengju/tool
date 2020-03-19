package com.gpj.tool.db.core.pojo;

import lombok.Getter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

/**
 * @className RowInfo
 * @description TODO
 * @author GPJ
 * @date 2020/3/18 17:05
 * @version 1.0
 **/
@Getter
public class RowInfo {
    private String sheetName;
    private Row row;
    private int rowNum;

    public RowInfo(Row row) {
        this.sheetName = row.getSheet().getSheetName();
        this.row = row;
        this.rowNum = row.getRowNum();
    }

    public String getCellValue(int index){
        if(this.row == null) return ">>>";
        if(this.row.getCell(index) == null) return "";

        Cell cell = this.row.getCell(index);
        switch (cell.getCellTypeEnum()){
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return (int) cell.getNumericCellValue() + "";
            case BOOLEAN:
                return cell.getBooleanCellValue()?"1":"";
            default:
                return "";
        }
    }
}
