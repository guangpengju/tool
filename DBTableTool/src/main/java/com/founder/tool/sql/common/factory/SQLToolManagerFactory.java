package com.founder.tool.sql.common.factory;

import com.founder.tool.sql.common.converter.*;
import com.founder.tool.sql.common.enums.DBMoldEnum;
import com.founder.tool.sql.common.exception.NewInstanceFailedException;
import com.founder.tool.sql.common.manage.DefaultExcelParserManager;
import com.founder.tool.sql.common.manage.DefaultSQLConverterManager;
import com.founder.tool.sql.common.parser.*;
import com.founder.tool.sql.common.parser.impl.DefaultIndexExcelParser;
import com.founder.tool.sql.common.parser.impl.DefaultProcedureExcelParser;
import com.founder.tool.sql.common.parser.impl.DefaultTableExcelParser;
import com.founder.tool.sql.common.parser.impl.DefaultTriggerExcelParser;
import com.founder.tool.sql.common.utils.ClassUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @className SQLToolManagerFactory
 * @description manage工厂方法
 * @author GPJ
 * @date 2019/7/12 11:26
 * @version 1.0
 **/
public class SQLToolManagerFactory {
    public static DefaultSQLConverterManager newConverterManager(DBMoldEnum enumDB){
        DefaultSQLConverterManager converterManager = new DefaultSQLConverterManager(
                newSonClassInstance(enumDB.getHandelPackage(), AbstractTableSQLConverter.class),
                newSonClassInstance(enumDB.getHandelPackage(), AbstractCommentSQLConverter.class),
                newSonClassInstance(enumDB.getHandelPackage(), AbstractProcedureSQLConverter.class),
                newSonClassInstance(enumDB.getHandelPackage(), AbstractTriggerSQLConverter.class, DefaultTriggerSQLConverter.class),
                newSonClassInstance(enumDB.getHandelPackage(), AbstractDropSQLConverter.class,DefaultDropSQLConverter.class)
        );
        return injection(converterManager);
    }

    public static DefaultExcelParserManager newParserManager(DBMoldEnum enumDB){
        List<ExcelParser> list = new ArrayList<>();
        list.add(newSonClassInstance(enumDB.getHandelPackage(), AbstractTableExcelParser.class, DefaultTableExcelParser.class));
        list.add(newSonClassInstance(enumDB.getHandelPackage(), AbstractColumnExcelParser.class));
        list.add(newSonClassInstance(enumDB.getHandelPackage(), AbstractIndexExcelParser.class, DefaultIndexExcelParser.class));
        list.add(newSonClassInstance(enumDB.getHandelPackage(), AbstractProcedureExcelParser.class, DefaultProcedureExcelParser.class));
        list.add(newSonClassInstance(enumDB.getHandelPackage(), AbstractTriggerExcelParser.class, DefaultTriggerExcelParser.class));
        return new DefaultExcelParserManager(list);
    }

    private static <T> T newSonClassInstance(String packageName, Class<T> clazz){
        T instance = newSonClassInstance(packageName, clazz, null);
        if(instance != null) return instance;
        throw new NewInstanceFailedException("未找到相关类的实现子类!", clazz);
    }

    private static <T> T newSonClassInstance(String packageName, Class<T> clazz, Class<? extends T> defaultClazz){
        Class<? extends T> coverter = ClassUtil.createSonClass(packageName, clazz);
        try {
            if(coverter != null) {
                return coverter.newInstance();
            }
            return defaultClazz != null?defaultClazz.newInstance():null;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new NewInstanceFailedException(clazz.getName(), e.getMessage());
        }
    }

    private static DefaultSQLConverterManager injection(DefaultSQLConverterManager converterManager){
        AbstractTriggerSQLConverter triggerConverter = converterManager.getTriggerConverter();
        if(ClassUtil.isAssignableFrom(DefaultTriggerSQLConverter.class, triggerConverter.getClass())){
            ((DefaultTriggerSQLConverter) triggerConverter).setConverter(converterManager.getProcedureConverter());
        }
        return converterManager;
    }
}
