package com.gpj.tool.sql.core.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * @className ConfigUtils
 * @description TODO
 * @author GPJ
 * @date 2019/7/10 14:58
 * @version 1.0
 **/
@Slf4j
@Getter
@Configuration
@PropertySource("classpath:config.properties")
public class ConfigInfo {
    private String excelPath;

    private String excelName;

    /*
        log.info("*********** 配置信息 ***********");
        log.info("数据库类型:    {}", getDBMoldEnum().getName());
        log.info("excel路径:    {}", getStringConfig(ConfigEnum.EXCEL_PATH.getKey()));
        log.info("sql文件路径:   {}", getStringConfig(ConfigEnum.SQL_PATH.getKey()));
        log.info("*******************************");
    }*/
}