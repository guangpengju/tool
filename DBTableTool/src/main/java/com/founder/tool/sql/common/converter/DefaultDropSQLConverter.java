package com.founder.tool.sql.common.converter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @className DefaultDropSQLConverter
 * @description TODO
 * @author GPJ
 * @date 2019/7/19 14:28
 * @version 1.0
 **/
public class DefaultDropSQLConverter extends AbstractDropSQLConverter {
    protected String defaultDropSQL(String name, String type) {
        return String.format("DROP %s IF EXISTS %s ", type, name);
    }

    @Override
    protected String dropIndexSQL(String tableName, String name, String type) {
        return defaultDropSQL(name, type);
    }

    @Override
    protected String dropTableSQL(String name, String type) {
        return defaultDropSQL(name, type);
    }

    @Override
    protected String dropProcedureSQL(String name, String type) {
        return defaultDropSQL(name, type);
    }

    @Override
    protected String dropTriggerSQL(String name, String type) {
        return defaultDropSQL(name, type);
    }
}
