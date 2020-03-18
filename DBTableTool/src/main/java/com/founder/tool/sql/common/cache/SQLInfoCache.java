package com.founder.tool.sql.common.cache;

import com.founder.tool.sql.common.pojo.*;
import com.founder.tool.sql.common.utils.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @className SQLInfoCache
 * @description sql信息缓存
 * @author GPJ
 * @date 2019/7/10 15:15
 * @version 1.0
 **/
public class SQLInfoCache {
     // 表信息缓存
    private static Map<String, Table> tableCache = new HashMap<>();

    // 列信息缓存
    private static Map<String, List<Column>> columnCache = new HashMap<>();

    // 主键信息缓存
    private static Map<String, List<Column>> pkCache = new HashMap<>();

    // 表与索引映射关系
    private static Map<String, List<String>> tableIndexMapping = new HashMap<>();
    // 索引信息缓存
    private static Map<String, List<String>> indexCache = new HashMap<>();

    //存储过程信息缓存
    private static Map<String, Procedure> procedureCache = new HashMap<>();

    //触发器信息缓存
    private static Map<String, Trigger> triggerCache = new HashMap<>();

/************************************** 缓存存入方法 **************************************/

    public static void addTable(Table table){
        if(table != null) tableCache.put(table.getTableName(), table);
    }

    public static void addColumn(String tableName, Column column){
        if(StringUtils.isNotBlank(tableName) &&
                tableCache.containsKey(tableName) &&
                column != null){
            MapUtils.saveListMapping(tableName, column, columnCache);
            if(column.isPk()) MapUtils.saveListMapping(tableName, column, pkCache);
        }
    }

    public static void addIndex(String tableName, String indexName, String column){
        if(StringUtils.isNotBlank(tableName) &&
                StringUtils.isNotBlank(indexName) &&
                StringUtils.isNotBlank(column) &&
                tableCache.containsKey(tableName)) {
            String indexKey = tableName + "-" + indexName;
            MapUtils.saveListMapping(tableName, indexKey, tableIndexMapping,false);
            MapUtils.saveListMapping(indexKey, column, indexCache);
        }
    }

    public static void addProcedureInfo(ProcedureInfo info){
        if(info != null) {
            String key = info.getName();
            MapUtils.saveEntryMapping(key, Procedure.class, procedureCache, (procedure, isNewInstance) -> {
                procedure.addInfo(info);
                if(isNewInstance) procedure.setName(info.getName());
            });
        }
    }

    public static void addTriggerInfo(TriggerInfo info){
        if(info != null) {
            String key = info.getName();
            MapUtils.saveEntryMapping(key, Trigger.class, triggerCache, (trigger, isNewInstance) -> {
                trigger.addInfo(info);
                if(isNewInstance) trigger.setName(info.getName());
            });
        }
    }

/************************************** 缓存获取方法 **************************************/

    public static List<String> getTableNames(){
        return new ArrayList<String>(tableCache.keySet());
    }

    public static Table getTable(String tableName){
        return tableCache.get(tableName);
    }

    public static List<Column> getColumns(String tableName){
        return columnCache.get(tableName);
    }

    public static List<Column> getPK(String tableName){
        if(StringUtils.equals("category_ext", tableName)){
            System.out.println("123");
        }
        return pkCache.get(tableName);
    }

    public static List<String> getIndexNames(){
        return new ArrayList<>(indexCache.keySet());
    }

    public static Map<String, List<String>> getIndexs(String tableName){
        Map<String, List<String>> index = new HashMap<>();

        List<String> indexKeys = tableIndexMapping.get(tableName);
        if(indexKeys != null && !indexKeys.isEmpty()){
            for (String key : indexKeys) {
                index.put(key.substring(key.indexOf("-") + 1), indexCache.get(key));
            }
        }
        return index;
    }

    public static List<String> getProcedureNames(){
        return new ArrayList<String>(procedureCache.keySet());
    }

    public static Procedure getProcedure(String procedureName){
        return procedureCache.get(procedureName);
    }

    public static List<String> getTriggerNames(){
        return new ArrayList<String>(triggerCache.keySet());
    }

    public static Trigger getTrigger(String triggerName){

        return triggerCache.get(triggerName);
    }
/************************************** 统计信息方法 **************************************/
    public static long totalTable(){
        return tableCache.size();
    }

    public static long totalColumn(){
        return columnCache.entrySet().stream().mapToInt(entry -> {
            return entry.getValue().size();
        }).sum();
    }
    public static long totalIndex(){
        return indexCache.size();
    }
    public static long totalProcedure(){
        return procedureCache.size();
    }
    public static long totalTrigger(){
        return triggerCache.size();
    }
}
