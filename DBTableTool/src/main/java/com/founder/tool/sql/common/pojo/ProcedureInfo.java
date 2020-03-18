package com.founder.tool.sql.common.pojo;

import com.founder.tool.sql.common.enums.ProcedureInputEnum;
import lombok.Getter;

/**
 * @className ProcedureInfo
 * @description TODO
 * @author GPJ
 * @date 2019/7/12 17:42
 * @version 1.0
 **/
@Getter
public class ProcedureInfo extends BaseInfo{
    private ProcedureInputEnum inputType;

    public ProcedureInfo(String name, String data, String assist, ProcedureInputEnum inputType) {
        super(name, data, assist);
        this.inputType = inputType;
    }
}