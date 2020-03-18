package com.gpj.tool.sql.core.pojo;

import com.gpj.tool.sql.core.enums.ColumnType;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @className Column
 * @description TODO
 * @author GPJ
 * @date 2020/3/18 16:47
 * @version 1.0
 **/
@Setter
@Getter
public class Column {
    // 表名
    private String tableName;

    // 字段名称
    private String code;

    // 字段中文名称
    private String name;

    // 必填
    private boolean required;

    // 主键
    private boolean primary;

    // 类型
    private String type;

    // 长度
    private int length;

    // 精度
    private int accuracy;

    // 默认值
    private String defaultValue;

    // 字典值/备注
    private String remark;

    // 扩展属性
    private Map<String,Object> attr = new LinkedHashMap<>();

    public Column(String tableName, String code) {
        this.tableName = tableName;
        this.code = code;
    }
}
