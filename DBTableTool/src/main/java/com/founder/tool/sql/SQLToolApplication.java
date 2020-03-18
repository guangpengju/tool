package com.founder.tool.sql;

import com.founder.tool.sql.common.cache.SQLInfoCache;
import com.founder.tool.sql.common.config.ConfigInfo;
import com.founder.tool.sql.common.enums.DBMoldEnum;
import com.founder.tool.sql.common.factory.SQLToolManagerFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @className SQLToolApplication
 * @description sql脚本生成工具入口类
 * @author GPJ
 * @date 2019/7/10 14:20
 * @version 1.0
 **/
@Slf4j
public class SQLToolApplication {
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        log.info("开始生成sql语句...");
        ConfigInfo.init();

        try {
            DBMoldEnum dbEnum = ConfigInfo.getDBMoldEnum();
            SQLToolManagerFactory.newParserManager(dbEnum).handle();
            SQLToolManagerFactory.newConverterManager(dbEnum).handle();

            log.info("脚本生成成功!!共计生成:表[{}]张共计[{}]列\t索引[{}]条\t存储过程[{}]个\t触发器[{}]个;\t耗时:{}毫秒",
                    SQLInfoCache.totalTable(), SQLInfoCache.totalColumn(), SQLInfoCache.totalIndex(),
                    SQLInfoCache.totalProcedure(), SQLInfoCache.totalTrigger(), (System.nanoTime() - startTime)/1000000);
        }catch (Exception e){
            log.error("脚本生成失败!!{}...", e.getMessage());
        }
    }

    private static boolean isContainChinese(String data){
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(data);
        return m.find();
    }
}
