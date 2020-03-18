package com.founder.tool.sql.common.converter;

import com.founder.tool.sql.common.cache.SQLInfoCache;
import com.founder.tool.sql.common.pojo.Column;
import com.founder.tool.sql.common.pojo.Table;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @className DefaultAbstractTableSQLConverter
 * @description 默认抽象建表语句转换器
 * @author GPJ
 * @date 2019/7/10 15:54
 * @version 1.0
 **/
@Slf4j
public abstract class DefaultAbstractTableSQLConverter extends AbstractTableSQLConverter {
    @Override
    protected String tableStartSQL(String tableName){
        return String.format("DROP TABLE IF EXISTS %s ;\nCREATE TABLE %s  ( \n", tableName, tableName);
    }
    @Override
    protected String tableEndSQL(String tableName){
        Table table = SQLInfoCache.getTable(tableName);
        String attr = table.getAttribute();
        return String.format(") %s ;\n", createTableAttributeSQL(attr));
    }


    @Override
    protected String createColumnSQL(Column column, boolean last){
        StringBuilder builder = new StringBuilder();
        builder.append(column.getColName()).append(" ");
        builder.append(" ").append(convertColumnTypeSql(column)).append(" ");
        if(column.isRequire()){
            builder.append(" NOT NULL ");
        }else{
            builder.append(" DEFAULT NULL ");
        }

        if(last){
            return builder.append(" \n").toString();
        }else{
            return builder.append(", \n").toString();
        }
    }

    private String convertColumnTypeSql(Column column){
        String sql = column.getRealType();
        if(column.isEnableLen()){
            sql += ("(" + column.getLen());
            if(column.isEnableAccuracy()){
                sql += (" , " + column.getAccuracy());
            }
            sql += ")";
        }
        return sql;
    }

    @Override
    protected String createPKSQL(String tableName, String colName){
        return String.format("ALTER TABLE %s ADD CONSTRAINT PK_%s PRIMARY KEY(%s); \n", tableName, tableName.toUpperCase(),colName);
    }

    @Override
    protected String createIndexSQL(String tableName, Map.Entry<String, List<String>> index){
        String indexSQL = String.join(",", index.getValue());
        return String.format("CREATE INDEX %s on %s(%s); \n", index.getKey().toUpperCase(), tableName, indexSQL);
    }

/************************************** 以下为子类必须实现的方法 **************************************/
    abstract protected String createTableAttributeSQL(String attribute);
}
