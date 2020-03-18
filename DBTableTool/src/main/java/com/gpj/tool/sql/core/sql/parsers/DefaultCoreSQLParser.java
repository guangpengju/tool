package com.gpj.tool.sql.core.sql.parsers;

import com.gpj.tool.sql.core.pojo.RowInfo;
import com.gpj.tool.sql.store.InfoStore;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @className DefaultCoreSQLParser
 * @description TODO
 * @author GPJ
 * @date 2020/3/18 22:31
 * @version 1.0
 **/
public class DefaultCoreSQLParser {
    @Autowired
    private InfoStore store;

    public void tableParser(){
        store.getTables().entrySet().stream().forEach(entry ->{
            // 按照转码模板生成--字段sql

            // 按照转码模板生成--主键sql
            // 按照转码模板生成--索引sql
            // 按照转码模板生成--表sql
        });
    }
}
