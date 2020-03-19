package com.gpj.tool.db;

import com.gpj.tool.db.core.config.ToolConfig;
import com.gpj.tool.db.mysql.MysqlConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @className ToolApplication
 * @description TODO
 * @author GPJ
 * @date 2020/3/18 15:19
 * @version 1.0
 **/
public class ToolApplication {

    /**
     * TODO
     *      联合主键
     *      size
     *      defaultValue
     *      去逗号
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ToolConfig.class);
        context.register(MysqlConfig.class);
        context.refresh();
    }
}
