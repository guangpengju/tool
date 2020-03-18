package com.founder.tool.sql.custom.dm;

import com.founder.tool.sql.common.parser.DefaultAbstractColumnExcelParser;
import com.founder.tool.sql.common.pojo.ColumnType;

/**
 * @className DMColumnExcelParser
 * @description TODO
 * @author GPJ
 * @date 2019/7/12 11:17
 * @version 1.0
 **/
public class DMColumnExcelParser extends DefaultAbstractColumnExcelParser {
    @Override
    protected ColumnType getColumnType(String columnType, String dbType) {
        DMColumnTypeEnum type = DMColumnTypeEnum.getColumnType(columnType, dbType);
        return ColumnTypeEnum2ColumnType(type);
    }
}
