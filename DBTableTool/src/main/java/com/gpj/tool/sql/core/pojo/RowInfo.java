package com.gpj.tool.sql.core.pojo;

import lombok.Getter;
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

    public RowInfo(Row row) {
        this.sheetName = row.getSheet().getSheetName();
        this.row = row;
    }

    public String getCellValue(int index){
        if(this.row == null){
            return ">>>";
        }
        return this.row.getCell(index).getStringCellValue();
    }
}
