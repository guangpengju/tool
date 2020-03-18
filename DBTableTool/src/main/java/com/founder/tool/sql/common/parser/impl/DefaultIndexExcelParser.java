package com.founder.tool.sql.common.parser.impl;

import com.founder.tool.sql.common.cache.SQLInfoCache;
import com.founder.tool.sql.common.parser.AbstractIndexExcelParser;
import com.founder.tool.sql.common.utils.LogInfoUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;

import java.util.ArrayList;
import java.util.List;

/**
 * @className DefaultIndexExcelParser
 * @description 默认excel的index的sheet页的解析器
 * @author GPJ
 * @date 2019/7/10 18:24
 * @version 1.0
 **/
@Slf4j
public class DefaultIndexExcelParser extends AbstractIndexExcelParser {
    private List<String> debugKey = new ArrayList<>();

    @Override
    protected void analysisIndex(Row row) {
        String tableName = getStringCellValue(row,0);
        String indexName = getStringCellValue(row,1);
        String columnName = getStringCellValue(row,2);

        if(StringUtils.isBlank(tableName) &&
                StringUtils.isBlank(indexName) &&
                StringUtils.isBlank(columnName)){
            log.warn("索引定义信息缺失!无法创建该索引!{}", LogInfoUtils.getCellInfo(row));
        }else if(log.isDebugEnabled() && !debugKey.contains(tableName + indexName)){
            log.debug("[{}]表的[{}]索引信息已解析入内存...", tableName, indexName);
            debugKey.add(tableName + indexName);
        }

        SQLInfoCache.addIndex(tableName, indexName, columnName);
    }
}
