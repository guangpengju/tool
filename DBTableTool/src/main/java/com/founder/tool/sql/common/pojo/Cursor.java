package com.founder.tool.sql.common.pojo;

import lombok.*;

/**
 * @className Cursor
 * @description TODO
 * @author GPJ
 * @date 2019/7/15 17:24
 * @version 1.0
 **/
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Cursor {
    private String name;
    private String endFlg;
    private String define;

    private boolean processed = false;
}
