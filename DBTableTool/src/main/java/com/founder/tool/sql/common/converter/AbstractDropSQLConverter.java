package com.founder.tool.sql.common.converter;

import com.founder.tool.sql.common.cache.SQLInfoCache;
import com.founder.tool.sql.common.constants.SQLType;
import com.founder.tool.sql.common.pojo.Column;
import lombok.extern.slf4j.Slf4j;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @className AbstractDropSQLConverter
 * @description 清理创建语句的sql
 * @author GPJ
 * @date 2019/7/10 15:54
 * @version 1.0
 **/
@Slf4j
public abstract class AbstractDropSQLConverter implements SQLConverter {
    @Override
    public void convert(OutputStream out) {
        // 删除索引
        List<String> indexNames = SQLInfoCache.getIndexNames();
        write(out, dropSQL(indexNames, SQLType.TYPE_INDEX));

        // 删除触发器
        List<String> triggerNames = SQLInfoCache.getTriggerNames();
        write(out, dropSQL(triggerNames, SQLType.TYPE_TRIGGER));

        // 删除存储过程
        List<String> procedureNames = SQLInfoCache.getProcedureNames();
        write(out, dropSQL(procedureNames, SQLType.TYPE_PROCEDURE));

        // 删除表
        List<String> tableNames = SQLInfoCache.getTableNames();
        write(out, dropSQL(tableNames, SQLType.TYPE_TABLE));
    }

    private boolean isNotEmpty(List<String> list){
        return list != null && !list.isEmpty();
    }

    protected String dropSQL(List<String> names, String type){
        if(isNotEmpty(names)){
            return noteSQL("DROP " + type) + names.stream().map(name -> {
                switch (type){
                    case SQLType.TYPE_INDEX:
                        String[] nameArry = name.split("-");
                        return dropIndexSQL(nameArry[0], nameArry[1], type);
                    case SQLType.TYPE_TABLE:
                        return dropTableSQL(name, type);
                    case SQLType.TYPE_PROCEDURE:
                        return dropProcedureSQL(name, type);
                    case SQLType.TYPE_TRIGGER:
                        return dropTriggerSQL(name, type);
                    default:
                        return null;
                }
            }).collect(Collectors.joining("; \n")) + " ; \n";
        }
        return null;
    }

    abstract protected String dropIndexSQL(String tableName, String name, String type);

    abstract protected String dropTableSQL(String name, String type);

    abstract protected String dropProcedureSQL(String name, String type);

    abstract protected String dropTriggerSQL(String name, String type);
}
