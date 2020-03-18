package com.founder.tool.sql.common.config;

import com.founder.tool.sql.common.enums.DBMoldEnum;
import com.founder.tool.sql.common.exception.ConfigRequiredException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

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
public class ConfigInfo {
    private static final String JAR_SUFFIX = "jar";
    private static final String PROPERTIES_SUFFIX = ".properties";
    private static final String CONFIG_FILE_NAME = "tool";
    private static final String CONFIG_FILE_NAME_DEBUG = "tool-debug";

    public static String projectPath;

    private static ResourceBundle resource;

    public static String getStringConfig(String name) {
        return getStringConfig(name, true);
    }

    public static String getStringConfig(String name, boolean require) {
        String config = resource.getString(name);
        if(StringUtils.isNoneBlank(config)){
            return config.replace("[JarPath]",projectPath);
        }
        if(require) throw new ConfigRequiredException(name);
        return null;
    }

    public static DBMoldEnum getDBMoldEnum(){
        return DBMoldEnum.getEnum(getStringConfig(ConfigEnum.DB_TYPE.getKey()));
    }
    
    public static void init(){
        String path = ConfigInfo.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String suffix = path.substring(path.lastIndexOf(".") + 1);
        projectPath = path.substring(0, path.lastIndexOf("/") + 1);
        if(StringUtils.equals(JAR_SUFFIX, suffix)){
            try (
                    InputStream in = new BufferedInputStream(new FileInputStream(new File(projectPath, CONFIG_FILE_NAME + PROPERTIES_SUFFIX)))
            ){
                resource = new PropertyResourceBundle(in);
            }catch (Exception e){
                log.warn("配置文件未找到,将使用默认配置文件!{}", projectPath + "/" + CONFIG_FILE_NAME + PROPERTIES_SUFFIX);
                resource = ResourceBundle.getBundle(CONFIG_FILE_NAME);
            }
        }else{
            resource = ResourceBundle.getBundle(CONFIG_FILE_NAME_DEBUG);
        }

        log.info("*********** 配置信息 ***********");
        log.info("数据库类型:    {}", getDBMoldEnum().getName());
        log.info("excel路径:    {}", getStringConfig(ConfigEnum.EXCEL_PATH.getKey()));
        log.info("sql文件路径:   {}", getStringConfig(ConfigEnum.SQL_PATH.getKey()));
        log.info("*******************************");
    }
}