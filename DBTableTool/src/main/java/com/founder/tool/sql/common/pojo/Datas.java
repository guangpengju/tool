package com.founder.tool.sql.common.pojo;

import lombok.Data;

import java.util.List;

/**
 * @className Datas
 * @description TODO
 * @author GPJ
 * @date 2019/7/10 14:48
 * @version 1.0
 **/
@Data
public class Datas {
    private String tableName;
    private List<String> columns;
    private List datas;//TODO
}
