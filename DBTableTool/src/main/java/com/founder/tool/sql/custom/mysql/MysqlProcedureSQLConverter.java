package com.founder.tool.sql.custom.mysql;

import com.founder.tool.sql.common.converter.DefaultAbstractProcedureSQLConverter;
import com.founder.tool.sql.common.pojo.BodyReplace;
import com.founder.tool.sql.common.pojo.Cursor;
import com.founder.tool.sql.common.pojo.DBBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @className DMProcedureSQLConverter
 * @description TODO
 * @author GPJ
 * @date 2019/7/15 15:11
 * @version 1.0
 **/
public class MysqlProcedureSQLConverter extends DefaultAbstractProcedureSQLConverter {
    @Override
    protected boolean declareAlone() {
        return false;
    }

    @Override
    protected boolean useReplaceKeyWord() {
        return false;
    }

    @Override
    protected String startDelimiter() {
        return "delimiter ;; \n";
    }

    @Override
    protected String endDelimiter() {
        return ";; \ndelimiter ; \n";
    }

    @Override
    protected String paramSQL(DBBase.Entry entry, String type) {
        return type + " " + entry.getData() + " " +  entry.getAssist();
    }

    @Override
    protected String cursorDefineSQL(String cursorName, String def, String endFlg) {
        return String.format("%s CURSOR FOR %s ; \n\tdeclare continue handler for not found set %s = 1", cursorName, def, endFlg);
    }

    @Override
    protected String setCursorEndFlgVal(Cursor cursor) {
        return cursor != null?String.format("\tset %s = 0 ; \n", cursor.getEndFlg()):"";
    }

    @Override
    protected String cursorEndFlgSQL(String cursorName, String endFlg) {
        return String.format("while %s = 0 do", endFlg);
    }

    @Override
    protected String bodyHandle(String sql) {
        sql = repacle(sql, "^\\s*while\\b.+\\bloop\\s*$","\\bloop\\b","do");
        sql = repacle(sql, "^\\s*end\\s+loop\\s*;\\s*$","\\bloop\\b","while");
        return sql;
    }
}
