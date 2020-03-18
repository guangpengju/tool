package com.founder.tool.sql.custom.dm;

import com.founder.tool.sql.common.converter.AbstractCommentSQLConverter;
import com.founder.tool.sql.common.pojo.Column;

/**
 * @className DMCommentSQLConverter
 * @description 达梦注释语句转换器
 * @author GPJ
 * @date 2019/7/19 10:51
 * @version 1.0
 **/
public class DMCommentSQLConverter extends AbstractCommentSQLConverter {
    @Override
    protected String createTableCommentSQL(String tableName, String note) {
        return String.format("COMMENT ON TABLE %s IS '%s'; \n", tableName, note);
    }

    @Override
    protected String createColumnCommentSQL(Column column) {
        return String.format("COMMENT ON COLUMN %s.\"%s\" IS '%s';\n", column.getTableName(), column.getColName(), column.getNote());
    }
}
