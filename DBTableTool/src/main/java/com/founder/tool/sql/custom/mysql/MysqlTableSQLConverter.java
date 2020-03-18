package com.founder.tool.sql.custom.mysql;

import com.founder.tool.sql.common.converter.DefaultAbstractTableSQLConverter;

/**
 * @className DMTableSQLConverter
 * @description 达梦数据库建表语句转换器
 * @author GPJ
 * @date 2019/7/10 15:54
 * @version 1.0
 **/
public final class MysqlTableSQLConverter extends DefaultAbstractTableSQLConverter{
    @Override
    protected String createTableAttributeSQL(String attribute) {
        return attribute;
    }
}
