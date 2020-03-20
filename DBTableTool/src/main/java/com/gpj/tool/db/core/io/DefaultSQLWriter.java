package com.gpj.tool.db.core.io;

import com.gpj.tool.db.core.config.ToolConfig;
import com.gpj.tool.db.core.pojo.Table;
import com.gpj.tool.db.core.store.InfoStore;
import com.gpj.tool.db.core.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private ToolConfig config;

    @Autowired
    private Template template;
    
    public void generateSqlFile(){
        try (
            Writer writer = new BufferedWriter(new FileWriter(new File(config.getSqlDirPath(), "table.sql")));
        ){
            for (Map.Entry<String, Table> entry : store.getTables().entrySet()) {
                String sql = template.generateSQL(entry.getValue());

                String note = entry.getValue().getName();
                writer.write("\n-- -------------------" + note.replaceAll("[[^-]?]","-") + "-------------------");
                writer.write("\n-- -------------------" + note + "-------------------");
                writer.write("\n-- -------------------" + note.replaceAll("[[^-]?]","-") + "-------------------\n");
                writer.write(sql);
                writer.write("\n");
            }
        } catch (IOException e) {
            log.error("sql写出错误,{}", e.toString(), e);
        }
    }
}
