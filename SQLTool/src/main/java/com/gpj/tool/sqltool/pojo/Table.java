package com.gpj.tool.sqltool.pojo;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Table {
    private String tableName;
    private String tableRemark;
    private List<TableCol> cols;
}
