package com.founder.tool.sql.common.parser;

import com.founder.tool.sql.common.utils.LogInfoUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @className AbstractExcelParser
 * @description TODO
 * @author GPJ
 * @date 2019/7/10 18:50
 * @version 1.0
 **/
@Slf4j
public abstract class AbstractExcelParser implements ExcelParser {
    private static final String NOTE_MARK = "--";
    private static final String END_MARK = ">>>";

    private static final String CELL_VALUE_TRUE = "1";

    @Override
    public void analysis(XSSFWorkbook workbook) {
        XSSFSheet sheet = chooseSheet(workbook);

        if(sheet != null){
            for (Row row : sheet) {
                if(isEnd(row)){
                    break;
                }else if(!isFirstRow(row) && !isNote(row)){
                    handle(row);
                }
            }
        }
    }

    abstract protected XSSFSheet chooseSheet(XSSFWorkbook workbook);

    abstract protected void handle(Row row);

    protected boolean isFirstRow(Row row){
        return row.getRowNum() == 0;
    }

    protected boolean isNote(Row row){
        Cell cell = row.getCell(0);
        return cell != null && StringUtils.equals(NOTE_MARK, getCellValue(cell));
    }

    protected boolean isEnd(Row row){
        Cell cell = row.getCell(0);
        return cell != null && StringUtils.equals(END_MARK, getCellValue(cell));
    }

    protected String getStringCellValue(Row row, int index){
        Cell cell = row.getCell(index);
        if(cell == null && log.isDebugEnabled()){
            log.debug("{}值为空!!", LogInfoUtils.getCellInfo(row, index));
        }
        return cell != null ? getCellValue(cell).trim() : "";
    }

    protected Integer getIntegerCellValue(Row row, int index){
        Cell cell = row.getCell(index);
        if(cell != null && StringUtils.isNoneBlank(getCellValue(cell))){
            return Integer.valueOf(getCellValue(cell));
        }
        return null;
    }

    protected boolean getBooleanCellValue(Row row, int index){
        Cell cell = row.getCell(index);
        return cell != null && StringUtils.equals(CELL_VALUE_TRUE, getCellValue(cell));
    }

    private String getCellValue(Cell cell){
        if(cell != null){
            switch (cell.getCellTypeEnum()){
                case STRING:
                    return cell.getStringCellValue();
                case BOOLEAN:
                    return cell.getBooleanCellValue()?CELL_VALUE_TRUE:"";
                case NUMERIC:
                    return String.valueOf((int) cell.getNumericCellValue());
                default:
                    return "";
            }
        }
        return "";
    }
}
