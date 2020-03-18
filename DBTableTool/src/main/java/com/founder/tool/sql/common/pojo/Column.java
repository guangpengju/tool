package com.founder.tool.sql.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @className Column
 * @description TODO
 * @author GPJ
 * @date 2019/7/10 14:39
 * @version 1.0
 **/
@Data
@AllArgsConstructor
@ToString
public class Column {
    // 表名
    private String tableName;

    // 字段名称
    private String colName;

    // 必填
    private boolean require;

    // 主键
    private boolean pk;

    // 字段类型
    private String type;

    // 真实字段类型
    private String realType;

    // 长度
    private Integer len;

    // 是否允许设置长度
    private boolean enableLen;

    // 精度
    private Integer accuracy;

    // 是否允许设置精度
    private boolean enableAccuracy;

    // 注释
    private String note;

    // 字典值
    private String dic;

    // 备注
    private String remark;

    // 属性
    private String attribute;

}
