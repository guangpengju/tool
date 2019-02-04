package com.gpj.tool.sqltool;

import com.gpj.tool.sqltool.pojo.Table;
import com.gpj.tool.sqltool.utils.FileHandler;
import com.gpj.tool.sqltool.utils.SQLTemplate;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SQLToolApplication {
   
    
    public static void main(String[] args) {
        log.info("启动......");
        String directorypath = SQLTemplate.getDirectoryPath();
        String path = SQLTemplate.getSQLTemplatePath();
        List<Table> tables = FileHandler.readExcel(path);
        FileHandler.writeSQL(directorypath, tables);
        log.info("sql生成完毕!!!");
    }
}
