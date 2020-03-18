package com.gpj.tool.sql.core.pojo;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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



    public Table(String name) {
        this.name = name;
    }

    public void addAttr(String attrName, String attrValue) {
        this.attr.put(attrName, attrValue);
    }

    public Column addColumns(Column columns) {
        if(!this.columns.contains(columns)){
            this.columns.add(columns);
        }
        return columns;
    }

    public Column getColumn(String columnName){
        return this.columns.stream()
                .filter(column -> StringUtils.equals(column.getName(), columnName))
                .findFirst().orElse(null);
    }

    public void addIndexs(Index indexs) {
        this.indexs.put(indexs.getIndexName(), indexs);
    }

    public void addDatas(Data datas) {
        this.datas.add(datas);
    }
}
