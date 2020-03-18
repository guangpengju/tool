package com.founder.tool.sql.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @className Table
 * @description TODO
 * @author GPJ
 * @date 2019/7/10 14:35
 * @version 1.0
 **/
@Data
@AllArgsConstructor
public class Table {
    // 表名
    private String tableName;

    // 中文名
    private String showName;

    // 说明
    private String note;

    // schema
    private String schema;

    // 备注
    private String remark;

    // 属性
    private String attribute;
}
