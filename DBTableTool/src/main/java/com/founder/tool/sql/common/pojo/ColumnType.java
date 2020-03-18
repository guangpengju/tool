package com.founder.tool.sql.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @className ColumnType
 * @description TODO
 * @author GPJ
 * @date 2019/7/12 14:38
 * @version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ColumnType {
    private String type;
    private String realType;
    private boolean enableLen;
    private boolean enableAccuracy;
    private boolean forceUseDefaultValue;
    private Integer defaultLen;
    private Integer defaultAccuracy;
}
