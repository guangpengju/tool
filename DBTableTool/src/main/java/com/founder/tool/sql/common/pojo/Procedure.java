package com.founder.tool.sql.common.pojo;

import com.founder.tool.sql.common.enums.ProcedureInputEnum;
import com.founder.tool.sql.common.utils.MapCallBack;
import com.founder.tool.sql.common.utils.MapUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className Procedure
 * @description TODO
 * @author GPJ
 * @date 2019/7/10 14:53
 * @version 1.0
 **/
@Getter
public class Procedure extends DBBase{
    private List<Entry> inParams = new ArrayList<>();
    private List<Entry> outParam = new ArrayList<>();
    private List<Entry> inoutParams = new ArrayList<>();
    private Map<String,Cursor> cursor = new HashMap<>();

    public Procedure() {
        super("");
    }

    public Procedure(String name) {
        super(name);
    }

    public void addInfo(ProcedureInfo info){
        if(info != null && info.getInputType() != null){
            Entry entry = new Entry(info.getData(), info.getAssist());
            switch (info.getInputType()){
                case IN:
                    this.inParams.add(entry);
                    break;
                case OUT:
                    this.outParam.add(entry);
                    break;
                case INOUT:
                    this.inoutParams.add(entry);
                    break;
                case DECLARE:
                    this.declare.add(entry);
                    break;
                case CURSOR_DEF:
                    MapUtils.saveEntryMapping(info.getData(), Cursor.class,cursor,(cur, isNew) -> {
                        cur.setName(info.getData());
                        cur.setDefine(info.getAssist());
                    });
                    break;
                case CURSOR_END:
                    MapUtils.saveEntryMapping(info.getData(), Cursor.class,cursor,(cur, isNew) -> {
                        if(StringUtils.isNotBlank(info.getData())) cur.setName(info.getData());
                        cur.setEndFlg(info.getAssist());
                    });
                    break;
                case BODY:
                    this.body.add(entry);
                    break;
            }

        }
    }
}