package com.founder.tool.sql.common.manage;

import com.founder.tool.sql.common.config.ConfigInfo;
import com.founder.tool.sql.common.constants.SQLFileConstants;
import com.founder.tool.sql.common.converter.*;
import com.founder.tool.sql.common.config.ConfigEnum;
import com.founder.tool.sql.common.exception.SQLToolException;
import com.founder.tool.sql.common.utils.IOUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.OutputStream;

/**
 * @className DefaultSQLConverterManager
 * @description 默认sql转换器处理器
 * @author GPJ
 * @date 2019/7/10 15:38
 * @version 1.0
 **/
@Slf4j
@AllArgsConstructor
public class DefaultSQLConverterManager implements Manager{
    protected AbstractTableSQLConverter tableConverter;
    protected AbstractCommentSQLConverter commentConverter;
    protected AbstractProcedureSQLConverter procedureConverter;
    protected AbstractTriggerSQLConverter triggerConverter;
    protected AbstractDropSQLConverter dropConverter;


    public AbstractProcedureSQLConverter getProcedureConverter() {
        return procedureConverter;
    }

    public AbstractTriggerSQLConverter getTriggerConverter() {
        return triggerConverter;
    }

    @Override
    public void handle() {
        try {
            String path = ConfigInfo.getStringConfig(ConfigEnum.SQL_PATH.getKey());
            // 建表语句输出
            if(tableConverter != null) handle(path, SQLFileConstants.getSQLTableFileName(), tableConverter);
            // 存储过程输出
            if(procedureConverter != null) handle(path, SQLFileConstants.getSQLProcedureFileName(), procedureConverter);
            // 触发器输出
            if(triggerConverter != null) handle(path, SQLFileConstants.getSQLTriggerFileName(), triggerConverter);
            // 注释语句输出
            if(commentConverter != null) handle(path, SQLFileConstants.getSQLCommentFileName(), commentConverter);
            // 注释语句输出
            if(dropConverter != null) handle(path, SQLFileConstants.getSQLDropFileName(), dropConverter);
        }catch (SQLToolException e){
            log.error("sql语句生成失败!:{}", e.getMessage());
            throw new SQLToolException("sql语句生成失败");
        }
    }

    private void handle(String path, String fileName, SQLConverter converter){
        File file = new File(path, fileName);
        if(file.exists()){
            file.delete();
        }
        OutputStream out = IOUtils.initOutputstream(path, fileName);
        converter.convert(out);
        IOUtils.closeOutputStream(out);
    }
}
