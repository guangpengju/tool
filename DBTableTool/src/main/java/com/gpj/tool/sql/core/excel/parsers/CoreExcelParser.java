package com.gpj.tool.sql.core.excel.parsers;

import com.gpj.tool.sql.core.pojo.RowInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @className DefaultCoreParser
 * @description TODO
 * @author GPJ
 * @date 2020/3/18 16:26
 * @version 1.0
 **/
@Slf4j
@Component
public class CoreExcelParser {
    protected static final String SHEET_NAME_TABLE = "table";
    protected static final String SHEET_NAME_COLUMN = "column";
    protected static final String SHEET_NAME_INDEX = "index";
    protected static final String SHEET_NAME_DATAS = "datas";

    @Autowired
    private CoreTableExcelParser tableExcelParser;

    @Autowired
    private CoreColumnExcelParser columnExcelParser;

    @Autowired
    private CoreIndexExcelParser indexExcelParser;

    public void rowParser(RowInfo rowInfo){
        try {
            // 解析表
            tableExcelParser.analysisForTableInfo(rowInfo);
            // 解析字段
            columnExcelParser.analysisForColumnInfo(rowInfo);
            // 解析索引
            indexExcelParser.analysisForIndexInfo(rowInfo);
            // 解析数据
        }catch (Exception e){
            log.error("excel解析错误:{}", e);
        }
    }
}
