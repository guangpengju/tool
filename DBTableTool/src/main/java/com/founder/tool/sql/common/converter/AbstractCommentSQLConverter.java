package com.founder.tool.sql.common.converter;

import com.founder.tool.sql.common.cache.SQLInfoCache;
import com.founder.tool.sql.common.pojo.Column;
import com.founder.tool.sql.common.pojo.Table;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @className AbstractCommentSQLConverter
 * @description 抽象注释语句转换器
 * @author GPJ
 * @date 2019/7/10 15:54
 * @version 1.0
 **/
@Slf4j
public abstract class AbstractCommentSQLConverter implements SQLConverter {
    @Override
    public void convert(OutputStream out) {
        List<String> tableNames = SQLInfoCache.getTableNames();
        for (String tableName : tableNames) {
            Table table = SQLInfoCache.getTable(tableName);
            // 注释语句sql
            write(out, noteSQL("TABLE COMMENT " + tableName));
            if(log.isDebugEnabled()){
                log.debug("[{}]表及其列注释语句生成完毕!",tableName);
            }

            // 表注释语句sql
            if(StringUtils.isNotBlank(table.getNote())){
                write(out, createTableCommentSQL(tableName, table.getNote()));
            }

            // 列注释语句sql
            List<Column> columns = SQLInfoCache.getColumns(tableName);
            for (Column column : columns) {
                if(StringUtils.isNotBlank(column.getNote())){
                    write(out, createColumnCommentSQL(column));
                }
            }
        }
    }

    abstract protected String createTableCommentSQL(String tableName, String note);

    abstract protected String createColumnCommentSQL(Column column);
}
