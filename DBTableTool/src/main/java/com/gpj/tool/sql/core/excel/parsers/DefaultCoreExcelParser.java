package com.gpj.tool.sql.core.excel.parsers;

import com.gpj.tool.sql.core.exceptions.ExcelAnalysisException;
import com.gpj.tool.sql.core.pojo.*;
import com.gpj.tool.sql.store.InfoStore;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @className DefaultCoreExcelParser
 * @description TODO
 * @author GPJ
 * @date 2020/3/18 16:26
 * @version 1.0
 **/
@Slf4j
@Component
public class DefaultCoreExcelParser {
    protected static final String SHEET_NAME_TABLE = "table";
    protected static final String SHEET_NAME_COLUMN = "column";
    protected static final String SHEET_NAME_INDEX = "index";
    protected static final String SHEET_NAME_DATAS = "datas";

    @Autowired
    private InfoStore store;

    @Autowired
    private List<TableExcelParser> tableParsers;

    @Autowired
    private List<ColumnExcelParser> columnParsers;

    @Autowired
    private List<IndexExcelParser> indexParsers;

    @Autowired
    private List<DataExcelParser> dataParsers;


    public void rowParser(RowInfo rowInfo){
        try {
            // 解析表
            analysisForTableInfo(rowInfo);
            // 解析字段
            analysisForColumnInfo(rowInfo);
            // 解析索引
            analysisForIndexInfo(rowInfo);
            // 解析数据
            analysisForDatasInfo(rowInfo);
        }catch (Exception e){
            log.error("excel解析错误:{}", e);
        }
    }

    private void analysisForTableInfo(RowInfo rowInfo){
        if(StringUtils.equals(rowInfo.getSheetName(), SHEET_NAME_TABLE)){
            String tableName = rowInfo.getCellValue(0);
            if(StringUtils.isNotBlank(tableName)){
                Table table = store.RegisterTable(tableName);

                boolean result = tableParsers.stream()
                        .filter(parser -> parser.skip(rowInfo, table))
                        .allMatch(parser -> parser.analysis(rowInfo, table) );

                if(!result){
                    store.RemoveTable(tableName);
                }
            }
        }
    }

    private void analysisForColumnInfo(RowInfo rowInfo) throws ExcelAnalysisException {
        if(StringUtils.equals(rowInfo.getSheetName(), SHEET_NAME_COLUMN)){
            String tableName = rowInfo.getCellValue(0);
            String columnName = rowInfo.getCellValue(1);
            if(StringUtils.isNotBlank(tableName)){
                Column column = store.RegisterColumn(tableName, columnName);

                boolean result = columnParsers.stream()
                        .filter(parser -> parser.skip(rowInfo, column))
                        .allMatch(parser -> parser.analysis(rowInfo, column) );

                if(!result){
                    store.RemoveColumn(column);
                }
            }
        }
    }

    private void analysisForIndexInfo(RowInfo rowInfo) {
        if(StringUtils.equals(rowInfo.getSheetName(), SHEET_NAME_INDEX)){
            String tableName = rowInfo.getCellValue(0);
            String indexName = rowInfo.getCellValue(1);
            String columnName = rowInfo.getCellValue(2);
            if(StringUtils.isNotBlank(tableName)){
                Index index = store.RegisterIndex(tableName, indexName);
                index.addIndexColumnNam(columnName);

                boolean result = indexParsers.stream()
                        .filter(parser -> parser.skip(rowInfo, index))
                        .allMatch(parser -> parser.analysis(rowInfo, index) );

                if(!result){
                    index.removeIndexColumnNam(columnName);
                }
            }
        }
    }

    private void analysisForDatasInfo(RowInfo rowInfo) {
        if(StringUtils.equals(rowInfo.getSheetName(), SHEET_NAME_DATAS)){
            String tableName = rowInfo.getCellValue(0);
            if(StringUtils.isNotBlank(tableName)){
                Data data = store.RegisterData(tableName);

                boolean result = dataParsers.stream()
                        .filter(parser -> parser.skip(rowInfo, data))
                        .allMatch(parser -> parser.analysis(rowInfo, data) );

                if(!result){
                    store.RemoveData(data);
                }
            }
        }
    }
}
