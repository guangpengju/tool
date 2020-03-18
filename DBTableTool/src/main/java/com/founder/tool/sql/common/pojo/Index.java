package com.founder.tool.sql.common.pojo;

import lombok.Data;

import java.util.List;

/**
 * @className Column
 * @description TODO
 * @author GPJ
 * @date 2019/7/10 14:39
 * @version 1.0
 **/
@Data
public class Index {
    // 表名
    private String tableName;

    // 索引名
    private String indexName;

    // 字段名称
    private List<String> colName;
}
