package com.gpj.tool.db.mysql;

import com.gpj.tool.db.core.parsers.ColumnExcelParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @className MysqlConfig
 * @description TODO
 * @author GPJ
 * @date 2020/3/19 12:17
 * @version 1.0
 **/
@Configuration
public class MysqlConfig {
    @Bean
    public ColumnExcelParser mysqlColumnExcelParser(){
        return new MysqlColumnExcelParser();
    }

    @Bean
    public MysqlTemplate mysqlTemplate(){
        return new MysqlTemplate();
    }
}
