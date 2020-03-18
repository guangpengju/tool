package com.founder.tool.sql.custom.mysql;

import com.founder.tool.sql.common.cache.SQLInfoCache;
import com.founder.tool.sql.common.converter.AbstractCommentSQLConverter;
import com.founder.tool.sql.common.pojo.Column;
import com.founder.tool.sql.common.pojo.Table;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.OutputStream;
import java.util.List;

/**
 * @className MysqlCommentSQLConverter
 * @description mysql注释语句转换器
 * @author GPJ
 * @date 2019/7/10 15:54
 * @version 1.0
 **/
@Slf4j
public class MysqlCommentSQLConverter extends AbstractCommentSQLConverter {
    @Override
    protected String createTableCommentSQL(String tableName, String note) {
        return String.format("ALTER TABLE %s COMMENT '%s'; \n", tableName, note);
    }

    @Override
    protected String createColumnCommentSQL(Column column) {
        String type = column.getRealType();
        if(column.isEnableLen()){
            type += ("(" + column.getLen());
            if(column.isEnableAccuracy()){
                type += (" , " + column.getAccuracy());
            }
            type += ")";
        }
        return String.format("ALTER TABLE %s MODIFY COLUMN %s %s COMMENT '%s';\n", column.getTableName(), column.getColName(), type, column.getNote());
    }

}
