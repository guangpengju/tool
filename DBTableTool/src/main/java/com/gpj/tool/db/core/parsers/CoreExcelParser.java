package com.gpj.tool.db.core.parsers;

import com.gpj.tool.db.core.annotations.ExcelOrder;
import com.gpj.tool.db.core.enums.ColumnType;
import com.gpj.tool.db.core.enums.PrimaryType;
import com.gpj.tool.db.core.pojo.*;
import com.gpj.tool.db.core.store.InfoStore;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.InvalidObjectException;
import java.lang.reflect.Field;

/**
 * @className CoreExcelParser
 * @description TODO
 * @author GPJ
 * @date 2020/3/19 9:48
 * @version 1.0
 **/
@Slf4j
@Order(100)
@Component
public class CoreExcelParser implements TableExcelParser,ColumnExcelParser,IndexExcelParser,DataExcelParser {
    @Autowired
    InfoStore store;

    @Override
    public boolean analysis(RowInfo rowInfo, Column column) {
        try {
            initColumnInfo(rowInfo, column);
        } catch (Exception e) {
            log.error("列信息解析失败，{}", e.toString(), e);
            return false;
        }

        // 处理主键
        if(column.isPrimary() && StringUtils.equals(PrimaryType.AUTO.getCode(), rowInfo.getCellValue(3))){
            // 处理主键
            column.setAutoPrimary(true);
            // 处理联合主键
            store.getTable(column.getTableName()).incrPrimaryNum();
        }

        // 处理类型
        column.setTypeEM(ColumnType.getColumnType(column.getType()));

        // 处理字典值、备注
        String dicAndRemark = rowInfo.getCellValue(9) + rowInfo.getCellValue(10);
        if(StringUtils.isNotBlank(dicAndRemark)){
            column.setRemark(dicAndRemark);
        }

        // 处理属性
        String attr = rowInfo.getCellValue(11);
        if(StringUtils.isNotBlank(attr)){
            column.addAttr("attr", attr);
        }

        // 处理编码
        String encoded = rowInfo.getCellValue(12);
        if(StringUtils.isNotBlank(encoded)){
            column.addAttr("encoded", encoded);
        }

        return true;
    }

    protected void initColumnInfo(RowInfo rowInfo, Column column) throws Exception {
        Field[] fields = column.getClass().getDeclaredFields();
        for (Field field : fields) {
            if(field.isAnnotationPresent(ExcelOrder.class)){
                ExcelOrder annotation = field.getAnnotation(ExcelOrder.class);
                boolean required = annotation.required();
                int value = annotation.value();
                String cellValue = rowInfo.getCellValue(value);
                if(StringUtils.isNotBlank(cellValue)){
                    try {
                        setValueToField(field, column, cellValue);
                    }catch (InvalidObjectException e){
                        // TODO 完善日志信息
                        if(required){
                            throw new Exception("该列信息未存储 --> excel:[{" + rowInfo.getSheetName() + "}]{" + rowInfo.getRowNum() + "}:{" + value + "}");
                        }else{
                            log.warn("[warnings]该列信息未存储 --> excel:[{}]{}:{} --> {}", rowInfo.getSheetName(), rowInfo.getRowNum(), value, cellValue);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else if(required){
                    throw new Exception("关键信息缺失 --> excel:[{" + rowInfo.getSheetName() + "}]{" + rowInfo.getRowNum() + "}:{" + value + "}");
                }
            }
        }
    }

    private void setValueToField(Field field, Object obj, String value) throws IllegalAccessException, InvalidObjectException {
        field.setAccessible(true);
        if(field.getType() == int.class
                || field.getType() == Integer.class){
            field.set(obj, Integer.valueOf(value));
        }else if(field.getType() == short.class
                || field.getType() == Short.class){
            field.set(obj, Short.valueOf(value));
        }else if(field.getType() == long.class
                || field.getType() == Long.class){
            field.set(obj, Long.valueOf(value));
        }else if(field.getType() == float.class
                || field.getType() == Float.class){
            field.set(obj, Float.valueOf(value));
        }else if(field.getType() == double.class
                || field.getType() == Double.class){
            field.set(obj, Double.valueOf(value));
        }else if(field.getType() == boolean.class
                || field.getType() == Boolean.class){
            field.set(obj, StringUtils.isNotBlank(value));
        }else if(field.getType() == String.class ){
            field.set(obj, value);
        }else {
            throw new InvalidObjectException("无效的field类型");
        }
    }


    @Override
    public boolean analysis(RowInfo rowInfo, Data data) {
        return true;
    }

    @Override
    public boolean analysis(RowInfo rowInfo, Index index) {
        return true;
    }

    @Override
    public boolean analysis(RowInfo rowInfo, Table table) {
        return true;
    }
}
