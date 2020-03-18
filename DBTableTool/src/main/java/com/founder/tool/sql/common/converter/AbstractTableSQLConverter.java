package com.founder.tool.sql.common.converter;

import com.founder.tool.sql.common.cache.SQLInfoCache;
import com.founder.tool.sql.common.pojo.Column;
import lombok.extern.slf4j.Slf4j;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @className AbstractTableSQLConverter
 * @description 抽象建表语句转换器
 * @author GPJ
 * @date 2019/7/10 15:54
 * @version 1.0
 **/
@Slf4j
public abstract class AbstractTableSQLConverter implements SQLConverter {
    @Override
    public void convert(OutputStream out) {
        List<String> tableNames = SQLInfoCache.getTableNames();
        for (String tableName : tableNames) {
            // 注释语句sql
            write(out, noteSQL("TABLE " + tableName));
            if(log.isDebugEnabled()){
                log.debug("[{}]建表语句生成完毕",tableName);
            }

            // 建表语句开始sql
            write(out, tableStartSQL(tableName));

            // 创建列语句sql
            List<Column> columns = SQLInfoCache.getColumns(tableName);
            for (int i = 0; i < columns.size(); i++) {
                write(out, createColumnSQL(columns.get(i), i == (columns.size() - 1)));
            }

            // 建表语句结束sql
            write(out, tableEndSQL(tableName));

            // 主键语句sql
            List<Column> pk = SQLInfoCache.getPK(tableName);
            if(pk != null && !pk.isEmpty()) write(out, PKInfoHandler(pk));

            // 索引语句sql
            Map<String, List<String>> indexs = SQLInfoCache.getIndexs(tableName);
            if(indexs != null && !indexs.isEmpty()){
                for (Map.Entry<String, List<String>> index : indexs.entrySet()) {
                    write(out,createIndexSQL(tableName, index));
                }
            }
        }
    }

    private String PKInfoHandler(List<Column> pk){
        String tableName = pk.get(0).getTableName();
        String colName = "";
        for (Column column : pk) {
            colName += (column.getColName() + ",");
        }
        return createPKSQL(tableName, colName.substring(0, colName.length() - 1));
    }

    abstract protected String tableStartSQL(String tableName);

    abstract protected String tableEndSQL(String tableName);

    abstract protected String createColumnSQL(Column column, boolean last);

    abstract protected String createPKSQL(String tableName, String colName);

    abstract protected String createIndexSQL(String tableName, Map.Entry<String, List<String>> index);
}
