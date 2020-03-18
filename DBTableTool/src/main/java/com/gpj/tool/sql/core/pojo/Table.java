package com.gpj.tool.sql.core.pojo;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @className Table
 * @description TODO
 * @author GPJ
 * @date 2020/3/18 16:46
 * @version 1.0
 **/
@Getter
public class Table {
    private String name;

    private Map<String, String> attr = new HashMap<>();

    private List<Column> columns = new ArrayList();

    private Map<String, Index> indexs = new HashMap<>();

    private List<Data> datas = new ArrayList<>();

    private int primaryNum = 0;

    public Table(String name) {
        this.name = name;
    }

    public void addAttr(String attrName, String attrValue) {
        this.attr.put(attrName, attrValue);
    }

    public Column addColumn(Column column) {
        if(!columns.contains(column)){
            columns.add(column);
            if(column.isPrimary()){
                this.primaryNum++;
            }
        }
        return column;
    }

    public void removeColumn(Column column) {
        if(columns.contains(column)){
            columns.remove(column);
            if(column.isPrimary()){
                this.primaryNum--;
            }
        }
    }

    public Column getColumn(String columnName){
        return this.columns.stream()
                .filter(column -> StringUtils.equals(column.getName(), columnName))
                .findFirst().orElse(null);
    }

    public Index addIndex(Index indexs) {
        this.indexs.put(indexs.getIndexName(), indexs);
        return indexs;
    }

    public Index getIndex(String indexName){
        final Map.Entry<String, Index> indexEntry = this.indexs.entrySet().stream()
                .filter(entry -> StringUtils.equals(entry.getKey(), indexName))
                .findFirst().orElse(null);

        if(indexEntry != null){
            return indexEntry.getValue();
        }
        return null;
    }

    public void addDatas(Data datas) {
        this.datas.add(datas);
    }

    public Data addData(Data data) {
        if(!datas.contains(data)){
            datas.add(data);
        }
        return data;
    }

    public void removeData(Data data) {
        if(datas.contains(data)){
            datas.remove(data);
        }
    }
}
