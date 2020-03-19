package com.gpj.tool.db.core.handler;

import com.gpj.tool.db.core.parsers.ColumnExcelParser;
import com.gpj.tool.db.core.parsers.DataExcelParser;
import com.gpj.tool.db.core.parsers.IndexExcelParser;
import com.gpj.tool.db.core.parsers.TableExcelParser;
import com.gpj.tool.db.core.pojo.*;
import com.gpj.tool.db.core.store.InfoStore;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @className DefaultExcelParserHandler
 * @description TODO
 * @author GPJ
 * @date 2020/3/18 16:26
 * @version 1.0
 **/
@Slf4j
@Component
public class DefaultExcelHandler {
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


    public void rowHandle(RowInfo rowInfo){
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
            log.error("excel解析错误:{} -->{sheetName:{},rowNum:{}}", e.toString(), rowInfo.getSheetName(), rowInfo.getRow().getRowNum(),e);
        }
    }

    private void analysisForTableInfo(RowInfo rowInfo) throws Exception {
        if(StringUtils.equals(rowInfo.getSheetName(), SHEET_NAME_TABLE)){
            String tableName = rowInfo.getCellValue(0);
            if(StringUtils.isNotBlank(tableName)){
                Table table = store.RegisterTable(tableName);

                boolean result = tableParsers.stream()
                        .filter(parser -> !parser.skip(rowInfo, table))
                        .allMatch(parser -> parser.analysis(rowInfo, table) );

                if(!result){
                    store.RemoveTable(tableName);
                }
            }else{
                throw new Exception("表信息解析失败,表名为空");
            }
        }
    }

    private void analysisForColumnInfo(RowInfo rowInfo) throws Exception {
        if(StringUtils.equals(rowInfo.getSheetName(), SHEET_NAME_COLUMN)){
            String tableName = rowInfo.getCellValue(0);
            String columnName = rowInfo.getCellValue(1);

            if(StringUtils.isNotBlank(tableName) && StringUtils.isNotBlank(columnName)){
                Column column = store.RegisterColumn(tableName, columnName);

                boolean result = columnParsers.stream()
                        .filter(parser -> !parser.skip(rowInfo, column))
                        .allMatch(parser -> parser.analysis(rowInfo, column) );

                if(!result){
                    store.RemoveColumn(column);
                }
            }else{
                throw new Exception("列信息解析失败,表名或列名为空");
            }
        }
    }

    private void analysisForIndexInfo(RowInfo rowInfo) throws Exception {
        if(StringUtils.equals(rowInfo.getSheetName(), SHEET_NAME_INDEX)){
            String tableName = rowInfo.getCellValue(0);
            String indexName = rowInfo.getCellValue(1);
            String columnName = rowInfo.getCellValue(2);
            if(StringUtils.isNotBlank(tableName)
                    && StringUtils.isNotBlank(indexName)
                    && StringUtils.isNotBlank(columnName)){
                Index index = store.RegisterIndex(tableName, indexName);
                index.addIndexColumnNam(columnName);

                boolean result = indexParsers.stream()
                        .filter(parser -> !parser.skip(rowInfo, index))
                        .allMatch(parser -> parser.analysis(rowInfo, index) );

                if(!result){
                    index.removeIndexColumnNam(columnName);
                }
            }else{
                throw new Exception("表信息解析失败,表名、列名、索引名为空");
            }
        }
    }

    private void analysisForDatasInfo(RowInfo rowInfo) throws Exception {
        if(StringUtils.equals(rowInfo.getSheetName(), SHEET_NAME_DATAS)){
            String tableName = rowInfo.getCellValue(0);
            if(StringUtils.isNotBlank(tableName)){
                Data data = store.RegisterData(tableName);

                boolean result = dataParsers.stream()
                        .filter(parser -> !parser.skip(rowInfo, data))
                        .allMatch(parser -> parser.analysis(rowInfo, data) );

                if(!result){
                    store.RemoveData(data);
                }
            }else{
                throw new Exception("表信息解析失败,表名为空");
            }
        }
    }
}
