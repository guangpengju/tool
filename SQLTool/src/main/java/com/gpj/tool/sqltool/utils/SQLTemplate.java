package com.gpj.tool.sqltool.utils;

import com.gpj.tool.sqltool.constant.SQLToolConstant;
import java.io.UnsupportedEncodingException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SQLTemplate {
    
    public static String getDirectoryPath(){
        String path = SQLTemplate.class .getProtectionDomain().getCodeSource().getLocation().getPath();

        if(path.endsWith(".jar")){
            path = path.substring(0, path.lastIndexOf("/") + 1);
        }
        try{
            return java.net.URLDecoder.decode (path, "utf-8");
        }catch(UnsupportedEncodingException e){
//           log.error("获取jar包所在路径错误:{}",path);
            log.error("err:{}",path);
        }
        return "E:/";
    }
    
    public static String getSQLTemplatePath(){
        StringBuffer path = new StringBuffer(getDirectoryPath());
        path.append(SQLToolConstant.FILE_NAME);
        path.delete(0,1);
        log.info("获取到配置文件路径为:{}",path.toString());
        return path.toString();
    }
}
