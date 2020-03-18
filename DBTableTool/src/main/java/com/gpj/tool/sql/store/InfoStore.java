package com.gpj.tool.sql.store;


import com.gpj.tool.sql.core.exceptions.ExcelAnalysisException;
import com.gpj.tool.sql.core.pojo.Column;
import com.gpj.tool.sql.core.pojo.Data;
import com.gpj.tool.sql.core.pojo.Index;
import com.gpj.tool.sql.core.pojo.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className InfoStore
 * @description TODO
 * @author GPJ
 * @date 2020/3/18 17:16
 * @version 1.0
 **/
public class InfoStore {
    private Map<String, Table> tables = new HashMap<>();
    private List<String> columnKeys = new ArrayList<>();
    private List<String> indexKeys = new ArrayList<>();

    public Map<String, Table> getTables() {
        return tables;
    }

    public Table RegisterTable(String tableName){
        if(tables.containsKey(tableName)){
            return tables.get(tableName);
        }else{
            return tables.put(tableName, new Table(tableName));
        }
    }

    public void RemoveTable(String tableName){
        tables.remove(tableName);
    }

    public Column RegisterColumn(String tableName, String columnName) throws ExcelAnalysisException {
        if(columnKeys.contains(tableName + "&&" + columnName)){
            return tables.get(tableName).getColumn(columnName);
        }else{
            columnKeys.add(tableName + "&&" + columnName);
            if(tables.containsKey(tableName)){
                return tables.get(tableName).addColumn(new Column(tableName, columnName));
            }else{
                return tables.put(tableName, new Table(tableName)).addColumn(new Column(tableName, columnName));
            }
        }
    }

    public void RemoveColumn(Column column){
        if(columnKeys.contains(column.getTableName() + "&&" + column.getName())){
            tables.get(column.getTableName()).removeColumn(column);
        }
    }

    public Index RegisterIndex(String tableName, String indexName){
        if(indexKeys.contains(tableName + "&&" + indexName)){
            return tables.get(tableName).getIndex(indexName);
        }else{
            indexKeys.add(tableName + "&&" + indexName);
            if(tables.containsKey(tableName)){
                return tables.get(tableName).addIndex(new Index(tableName, indexName));
            }else{
                return tables.put(tableName, new Table(tableName)).addIndex(new Index(tableName, indexName));
            }
        }
    }

    public Data RegisterData(String tableName) {
        if(tables.containsKey(tableName)){
            return tables.get(tableName).addData(new Data(tableName));
        }else{
            return tables.put(tableName, new Table(tableName)).addData(new Data(tableName));
        }
    }

    public void RemoveData(Data data){
        tables.get(data.getTableName()).removeData(data);
    }

}
