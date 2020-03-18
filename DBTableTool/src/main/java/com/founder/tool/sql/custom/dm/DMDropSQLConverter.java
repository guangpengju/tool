package com.founder.tool.sql.custom.dm;

import com.founder.tool.sql.common.converter.DefaultDropSQLConverter;

/**
 * @className DMDropSQLConverter
 * @description TODO
 * @author GPJ
 * @date 2019/7/19 14:52
 * @version 1.0
 **/
public class DMDropSQLConverter extends DefaultDropSQLConverter {
    @Override
    protected String dropIndexSQL(String tableName, String name, String type) {
        return dropSQL(name, type);
    }

    @Override
    protected String dropProcedureSQL(String name, String type) {
        return dropSQL(name, type);
    }

    @Override
    protected String dropTriggerSQL(String name, String type) {
        return dropSQL(name, type);
    }

    private String dropSQL(String name, String type) {
        return String.format("DROP %s %s ", type, name);
    }
}
