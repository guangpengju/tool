package com.gpj.tool.db.core.pojo;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @className Index
 * @description TODO
 * @author GPJ
 * @date 2020/3/18 16:47
 * @version 1.0
 **/
@Getter
public class Index {
    private String tableName;

    private String indexName;

    private List<String> columnNames = new ArrayList<>();

    public Index(String tableName, String indexName) {
        this.tableName = tableName;
        this.indexName = indexName;
    }
    
    public void addIndexColumnNam(String columnName){
        columnNames.add(columnName);
    }

    public void removeIndexColumnNam(String columnName){
        columnNames.remove(columnName);
    }
}
