package com.gpj.tool.db.core.pojo;

import lombok.Getter;

import java.util.List;

/**
 * @className Data
 * @description TODO
 * @author GPJ
 * @date 2020/3/18 16:47
 * @version 1.0
 **/
@Getter
public class Data {
    private String tableName;

    private List<String> data;

    public Data(String tableName) {
        this.tableName = tableName;
    }
    
    public void addValue(String value){
        data.add(value);
    }

    public void removeValue(String value){
        if(data.contains(value)){
            data.remove(value);
        }
    }
}
