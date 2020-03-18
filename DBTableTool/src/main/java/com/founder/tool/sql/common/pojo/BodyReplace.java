package com.founder.tool.sql.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @className BodyReplace
 * @description TODO
 * @author GPJ
 * @date 2019/7/18 9:45
 * @version 1.0
 **/
@Getter
@AllArgsConstructor
public class BodyReplace {
    private String matche;
    private String regex;
    private String replace;
}
