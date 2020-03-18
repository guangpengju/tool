package com.founder.tool.sql.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @className DBBase
 * @description TODO
 * @author GPJ
 * @date 2019/7/12 18:48
 * @version 1.0
 **/
@Getter
@Setter
public abstract class DBBase {
    protected String name;
    protected List<Entry> declare = new ArrayList<>();
    protected List<Entry> body = new ArrayList<>();

    public DBBase(String name) {
        this.name = name;
    }

    @Data
    @AllArgsConstructor
    public static class Entry<data, type> {
        String data;
        String assist;

        public boolean dataNotBlank(){
            return StringUtils.isNotBlank(this.data);
        }

        public boolean isNotBlank(){
            return StringUtils.isNotBlank(this.data) && StringUtils.isNotBlank(this.assist);
        }
    }
}
