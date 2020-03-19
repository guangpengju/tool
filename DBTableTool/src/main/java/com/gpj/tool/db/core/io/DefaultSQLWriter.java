package com.gpj.tool.db.core.io;

import com.gpj.tool.db.core.store.InfoStore;
import com.gpj.tool.db.core.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @className DefaultSQLWriter
 * @description
 *   将缓存中的数据写出到sql文件中
 * @author GPJ
 * @date 2020/3/18 16:13
 * @version 1.0
 **/
@Slf4j
@Component
public class DefaultSQLWriter {
    @Autowired
    private InfoStore store;

    @Autowired
    private Template template;
    
    public void write(){
        store.getTables().entrySet().stream().forEach(entry ->{
            String sql = template.generateSQL(entry.getValue());
            System.out.println(sql);
        });
    }
}
