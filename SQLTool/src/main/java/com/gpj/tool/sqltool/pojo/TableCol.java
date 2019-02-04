package com.gpj.tool.sqltool.pojo;

import com.gpj.tool.sqltool.enums.ColsType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TableCol {
    private String table;
    private String code;
    private ColsType type;
    private boolean isNotNull;
    private boolean isPK;
    private int length;
    private int accuracy;
    private String remark;
}
