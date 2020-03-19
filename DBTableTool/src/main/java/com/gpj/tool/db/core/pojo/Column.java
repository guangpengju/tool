package com.gpj.tool.db.core.pojo;

import com.gpj.tool.db.core.annotations.ExcelOrder;
import com.gpj.tool.db.core.enums.ColumnType;
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
    @ExcelOrder(value = 1, required = true)
    private String code;

    // 字段中文名称
    @ExcelOrder(value = 8, required = true)
    private String name;

    // 必填
    @ExcelOrder(2)
    private boolean required = false;

    // 主键
    @ExcelOrder(3)
    private boolean primary = false;

    // 是否是自增主键
    private boolean autoPrimary = false;

    // 类型
    @ExcelOrder(value = 4, required = true)
    private String type;

    // 类型枚举
    private ColumnType typeEM;

    // 长度
    @ExcelOrder(5)
    private int length = -1;

    // 精度
    @ExcelOrder(6)
    private int accuracy = -1;

    private String size = "";

    // 默认值
    @ExcelOrder(7)
    private String defaultValue = "";

    // 字典值/备注
    private String remark = "";

    // 扩展属性
    private Map<String,String> attr = new LinkedHashMap<>();

    public Column(String tableName, String code) {
        this.tableName = tableName;
        this.code = code;
    }

    public void addAttr(String AttrName, String AttrValue){
        attr.put(AttrName, AttrValue);
    }

    public String getAttr(String AttrName){
        return attr.get(AttrName);
    }

}
