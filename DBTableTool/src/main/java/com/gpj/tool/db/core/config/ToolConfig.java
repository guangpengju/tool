package com.gpj.tool.db.core.config;

import com.alibaba.fastjson.JSONObject;
import com.gpj.tool.db.core.io.DefaultExcelReader;
import com.gpj.tool.db.core.io.DefaultSQLWriter;
import com.gpj.tool.db.core.store.InfoStore;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @className ToolConfig
 * @description TODO
 * @author GPJ
 * @date 2019/7/10 14:58
 * @version 1.0
 **/
@Slf4j
@Getter
@Configuration
@ComponentScan("com.gpj.tool.db.core")
@PropertySource("classpath:tool.properties")
public class ToolConfig {
    @Value("${excel.file:classpath:db.xlsx}")
    private String excelPath;

    @Value("${sql.path:F:\\sql}")
    private String sqlDirPath;

    @Bean
    public SmartLifecycle coreActuator(DefaultExcelReader reader, DefaultSQLWriter writer, InfoStore store){
        return new SmartLifecycle(){
            @Override
            public void start() {
                reader.read();
                System.out.println(JSONObject.toJSONString(store));
                writer.write();
            }

            @Override
            public void stop() {

            }

            @Override
            public boolean isRunning() {
                return false;
            }

            @Override
            public boolean isAutoStartup() {
                return true;
            }
        };
    }

    /*
        log.info("*********** 配置信息 ***********");
        log.info("数据库类型:    {}", getDBMoldEnum().getName());
        log.info("excel路径:    {}", getStringConfig(ConfigEnum.EXCEL_PATH.getKey()));
        log.info("sql文件路径:   {}", getStringConfig(ConfigEnum.SQL_PATH.getKey()));
        log.info("*******************************");
    }*/
}