package com.founder.tool.sql.custom.mysql;

import com.founder.tool.sql.common.parser.DefaultAbstractColumnExcelParser;
import com.founder.tool.sql.common.pojo.ColumnType;
import com.founder.tool.sql.custom.dm.DMColumnTypeEnum;

/**
 * @className DMColumnExcelParser
 * @description TODO
 * @author GPJ
 * @date 2019/7/12 11:17
 * @version 1.0
 **/
public class MysqlColumnExcelParser extends DefaultAbstractColumnExcelParser {
    @Override
    protected ColumnType getColumnType(String columnType, String dbType) {
        MysqlColumnTypeEnum type = MysqlColumnTypeEnum.getColumnType(columnType, dbType);
        return ColumnTypeEnum2ColumnType(type);
    }
}
