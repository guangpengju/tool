package com.gpj.tool.sql.store;

import com.founder.tool.sql.common.pojo.*;
import com.founder.tool.sql.common.utils.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className SQLInfoCache
 * @description sql信息缓存
 * @author GPJ
 * @date 2019/7/10 15:15
 * @version 1.0
 **/
public class TableInfo {
     // 表信息缓存
    private Map<String, Table> tableCache = new HashMap<>();

    // 列信息缓存
    private Map<String, List<Column>> columnCache = new HashMap<>();

    // 主键信息缓存
    private Map<String, List<Column>> pkCache = new HashMap<>();

    // 表与索引映射关系
    private Map<String, List<String>> tableIndexMapping = new HashMap<>();

    // 索引信息缓存
    private Map<String, List<String>> indexCache = new HashMap<>();
/************************************** 缓存存入方法 **************************************/

    public void addTable(Table table){
        if(table != null) tableCache.put(table.getTableName(), table);
    }

    public void addColumn(String tableName, Column column){
        if(StringUtils.isNotBlank(tableName) &&
                tableCache.containsKey(tableName) &&
                column != null){
            MapUtils.saveListMapping(tableName, column, columnCache);
            if(column.isPk()) MapUtils.saveListMapping(tableName, column, pkCache);
        }
    }

    public void addIndex(String tableName, String indexName, String column){
        if(StringUtils.isNotBlank(tableName) &&
                StringUtils.isNotBlank(indexName) &&
                StringUtils.isNotBlank(column) &&
                tableCache.containsKey(tableName)) {
            String indexKey = tableName + "-" + indexName;
            MapUtils.saveListMapping(tableName, indexKey, tableIndexMapping,false);
            MapUtils.saveListMapping(indexKey, column, indexCache);
        }
    }

/************************************** 缓存获取方法 **************************************/

    public List<String> getTableNames(){
        return new ArrayList<String>(tableCache.keySet());
    }

    public Table getTable(String tableName){
        return tableCache.get(tableName);
    }

    public List<Column> getColumns(String tableName){
        return columnCache.get(tableName);
    }

    public List<Column> getPK(String tableName){
        if(StringUtils.equals("category_ext", tableName)){
            System.out.println("123");
        }
        return pkCache.get(tableName);
    }

    public List<String> getIndexNames(){
        return new ArrayList<>(indexCache.keySet());
    }

    public Map<String, List<String>> getIndexs(String tableName){
        Map<String, List<String>> index = new HashMap<>();

        List<String> indexKeys = tableIndexMapping.get(tableName);
        if(indexKeys != null && !indexKeys.isEmpty()){
            for (String key : indexKeys) {
                index.put(key.substring(key.indexOf("-") + 1), indexCache.get(key));
            }
        }
        return index;
    }

/************************************** 统计信息方法 **************************************/
    public long totalTable(){
        return tableCache.size();
    }

    public long totalColumn(){
        return columnCache.entrySet().stream().mapToInt(entry -> {
            return entry.getValue().size();
        }).sum();
    }
    public long totalIndex(){
        return indexCache.size();
    }
}
