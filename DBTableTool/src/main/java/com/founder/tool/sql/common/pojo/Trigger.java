package com.founder.tool.sql.common.pojo;

import com.alibaba.fastjson.JSON;
import com.founder.tool.sql.common.exception.ExcelContentException;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @className Trigger
 * @description TODO
 * @author GPJ
 * @date 2019/7/10 14:53
 * @version 1.0
 **/
@Getter
public class Trigger extends DBBase {
    private String tirgger_time;
    private String tirgger_event;
    private String table;

    public Trigger() {
        super("");
    }

    public Trigger(String name) {
        super(name);
    }

    public void addInfo(TriggerInfo info) {
        if(info != null && info.getInputType() != null){
            Entry entry;
            switch (info.getInputType()){
                case TIRGGER_TIME:
                    this.tirgger_time = info.getData();
                    if(StringUtils.isBlank(this.tirgger_time) || !"BEFORE,AFTER".contains(this.tirgger_time.toUpperCase())){
                        throw new ExcelContentException("trigger定义错误,触发时间类型错误!", JSON.toJSONString(info));
                    }
                    break;
                case TRIGGER_EVENT:
                    this.tirgger_event = info.getData();
                    if(StringUtils.isBlank(this.tirgger_event) || !"INSERT,UPDATE,DELETE".contains(this.tirgger_event.toUpperCase())){
                        throw new ExcelContentException("trigger定义错误,触发事件类型错误!", JSON.toJSONString(info));
                    }
                    break;
                case TABLE:
                    this.table = info.getData();
                    break;
                case DECLARE:
                    entry = new Entry(info.getData(), info.getAssist());
                    this.declare.add(entry);
                    break;
                case BODY:
                    entry = new Entry(info.getData(), info.getAssist());
                    this.body.add(entry);
                    break;
            }
        }
    }
}
