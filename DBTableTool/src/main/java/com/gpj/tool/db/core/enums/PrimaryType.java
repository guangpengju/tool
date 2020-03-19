package com.gpj.tool.db.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @className PrimaryType
 * @description TODO
 * @author GPJ
 * @date 2020/3/19 11:17
 * @version 1.0
 **/
@Getter
@AllArgsConstructor
public enum  PrimaryType {
    INSERT("INSERT","手动设置主键"),
    AUTO("AUTO","自增主键");

    private String code;
    private String name;
}
