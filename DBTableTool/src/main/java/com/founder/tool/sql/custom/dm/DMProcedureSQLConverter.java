package com.founder.tool.sql.custom.dm;

import com.founder.tool.sql.common.converter.DefaultAbstractProcedureSQLConverter;
import com.founder.tool.sql.common.pojo.Cursor;
import com.founder.tool.sql.common.pojo.DBBase;

import java.util.Map;

/**
 * @className DMProcedureSQLConverter
 * @description TODO
 * @author GPJ
 * @date 2019/7/15 15:11
 * @version 1.0
 **/
public class DMProcedureSQLConverter extends DefaultAbstractProcedureSQLConverter {
    @Override
    protected boolean declareAlone() {
        return true;
    }

    @Override
    protected boolean useReplaceKeyWord() {
        return true;
    }

    @Override
    protected String startDelimiter() {
        return null;
    }

    @Override
    protected String endDelimiter() {
        return null;
    }

    @Override
    protected String paramSQL(DBBase.Entry entry, String type) {
        return entry.getData() + " " + type + " " + entry.getAssist();
    }

    @Override
    protected String cursorDefineSQL(String cursorName, String def, String endFlg) {
        return String.format("CURSOR %s IS %s", cursorName, def);
    }

    @Override
    protected String setCursorEndFlgVal(Cursor cursor) {
        return null;
    }

    @Override
    protected String cursorEndFlgSQL(String cursorName, String endFlg) {
        return "while " + cursorName + "%FOUND loop";
    }

    @Override
    protected String bodyHandle(String sql) {
        return sql;
    }
}
