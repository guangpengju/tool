package com.founder.tool.sql.common.parser.impl;

import com.founder.tool.sql.common.cache.SQLInfoCache;
import com.founder.tool.sql.common.parser.AbstractTableExcelParser;
import com.founder.tool.sql.common.pojo.Table;
import com.founder.tool.sql.common.utils.LogInfoUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;

/**
 * @className DefaultTableExcelParser
 * @description 默认excel的table的sheet页的解析器
 * @author GPJ
 * @date 2019/7/10 18:24
 * @version 1.0
 **/
@Slf4j
public class DefaultTableExcelParser extends AbstractTableExcelParser {
    @Override
    protected void analysisTable(Row row) {
        SQLInfoCache.addTable(analysisRow2Table(row));
    }

    /**
     * 解析excel行对象,获取table信息
     * @param row
     * @return
     */
    private Table analysisRow2Table(Row row){
        String tableName = getStringCellValue(row, 0);
        if(StringUtils.isBlank(tableName)){
            log.warn("表名为空,相关表创建失败!{}", LogInfoUtils.getCellInfo(row, 0));
        }else if(log.isDebugEnabled()){
            log.debug("[{}]表信息已解析入内存",tableName);
        }
        return new Table(
                getStringCellValue(row,0),
                getStringCellValue(row,1),
                getStringCellValue(row,2),
                getStringCellValue(row,3),
                getStringCellValue(row,4),
                getStringCellValue(row,5)
        );
    }


}
