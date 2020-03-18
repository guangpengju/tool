package com.founder.tool.sql.custom.mysql;

import com.founder.tool.sql.common.converter.DefaultDropSQLConverter;

/**
 * @className MysqlDropSQLConverter
 * @description TODO
 * @author GPJ
 * @date 2019/7/19 14:52
 * @version 1.0
 **/
public class MysqlDropSQLConverter extends DefaultDropSQLConverter {
    @Override
    protected String dropIndexSQL(String tableName, String name, String type) {
        return String.format("DROP %s %s ON %s", type, name, tableName);
    }
}
