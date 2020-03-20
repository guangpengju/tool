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
     * 实现原理:
     *   通过ToolConfig注册coreActuator(继承SmartLifecycle),实现spring启动回电方法,实现程序入口
     *   通过DefaultExcelReader实现Excel读入
     *   通过DefaultExcelHandler调用各类ExcelParser,实现excel解析
     *   通过TemplateHandler解析Template自定义实现类转义定义的模板,实现sql语句生成
     *   通过DefaultSQLWriter将sql写出
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ToolConfig.class);
        context.register(MysqlConfig.class);
        context.refresh();
    }
}
