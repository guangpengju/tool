package com.gpj.tool.sql.store;


import com.gpj.tool.sql.core.exceptions.ExcelAnalysisException;
import com.gpj.tool.sql.core.pojo.Column;
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
    
    public Table RegisterTable(String tableName){
        if(tables.containsKey(tableName)){
            return tables.get(tableName);
        }else{
            return tables.put(tableName, new Table(tableName));
        }
    }

    public Column RegisterColumn(String tableName, String columnName) throws ExcelAnalysisException {
        if(columnKeys.contains(tableName + "&&" + columnName)){
            if(tables.containsKey(tableName)){
                return tables.get(tableName).getColumn(columnName);
            }else{
                throw new ExcelAnalysisException("获取列不存在!");
            }
        }else{
            if(tables.containsKey(tableName)){
                return tables.get(tableName).addColumns(new Column(columnName));
            }else{
                return tables.put(tableName, new Table(tableName)).addColumns(new Column(columnName));
            }
        }
    }
}
