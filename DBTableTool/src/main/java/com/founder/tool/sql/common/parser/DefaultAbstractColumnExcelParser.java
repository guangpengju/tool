package com.founder.tool.sql.common.parser;

import com.founder.tool.sql.common.cache.SQLInfoCache;
import com.founder.tool.sql.common.config.ConfigInfo;
import com.founder.tool.sql.common.exception.ExcelContentException;
import com.founder.tool.sql.common.exception.ExcelParserException;
import com.founder.tool.sql.common.pojo.Column;
import com.founder.tool.sql.common.pojo.ColumnType;
import com.founder.tool.sql.common.utils.LogInfoUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @className DefaultAbstractColumnExcelParser
 * @description 默认抽象excel的column的sheet页的解析器
 * @author GPJ
 * @date 2019/7/10 18:24
 * @version 1.0
 **/
@Slf4j
public abstract class DefaultAbstractColumnExcelParser extends AbstractColumnExcelParser {
    private List<String> debugKey = new ArrayList<>();

    @Override
    protected void analysisColumn(Row row) {
        Column column = analysisRow2Column(row);
        if(column != null) SQLInfoCache.addColumn(column.getTableName(), column);
    }

    /**
     * 解析excel行对象,获取column信息
     * @param row
     * @return
     */
    private Column analysisRow2Column(Row row){
        Column column = new Column(
                getStringCellValue(row, 0),
                getStringCellValue(row, 1),
                getBooleanCellValue(row, 2),
                getBooleanCellValue(row, 3),
                getStringCellValue(row,4), "",
                getIntegerCellValue(row, 5), false,
                getIntegerCellValue(row, 6), false,
                getStringCellValue(row, 7),
                getStringCellValue(row, 8),
                getStringCellValue(row, 9),
                getStringCellValue(row, 10)
        );
        return setColumnTypeInfo(column, row);
    }

    private Column setColumnTypeInfo(Column column, Row row) {
        ColumnType type = getColumnType(column.getType(), ConfigInfo.getDBMoldEnum().getType());

        if(type != null){
            String realType = type.getRealType();
            if(StringUtils.isNotBlank(realType)){
                column.setRealType(realType);
                column.setEnableLen(type.isEnableLen());
                column.setEnableAccuracy(type.isEnableAccuracy());

                if(type.isForceUseDefaultValue()){
                    if(type.getDefaultLen() != null && type.getDefaultAccuracy() != null){
                        if(type.isForceUseDefaultValue() && type.isEnableLen()){
                            if(type.getDefaultLen() == null || type.getDefaultLen() < 0) {
                                throw new ExcelParserException("列类型要求强制使用默认长度,但默认长度为空!", type.toString());
                            }
                            column.setLen(type.getDefaultLen());
                        }
                        if(type.isForceUseDefaultValue() && type.isEnableAccuracy()){
                            if(type.getDefaultAccuracy() == null || type.getDefaultAccuracy() < 0) {
                                throw new ExcelParserException("列类型要求强制使用默认精度,但默认精度为空!", type.toString());
                            }
                            column.setAccuracy(type.getDefaultAccuracy());
                        }
                    }
                }else if(column.isEnableLen() && column.getLen() == null){
                    if(type.getDefaultLen() != null){
                        log.warn("[{}]该列类型允许设置长度且未指定长度,使用默认长度[{}]", LogInfoUtils.getCellInfo(row), type.getDefaultLen());
                        column.setLen(type.getDefaultLen());
                    }else{
                        throw new ExcelParserException("列类型允许设置长度,未指定长度且默认长度为空!", type.toString());
                    }
                }else if(column.isEnableAccuracy() && column.getAccuracy() == null){
                    if(type.getDefaultAccuracy() != null) {
                        log.warn("[{}]该列类型允许设置精度且未指定精度,使用默认精度[{}]", LogInfoUtils.getCellInfo(row), type.getDefaultAccuracy());
                        column.setAccuracy(type.getDefaultAccuracy());
                    }else{
                        throw new ExcelParserException("列类型允许设置精度,未指定精度且默认精度为空!", type.toString());
                    }
                }

                if(log.isDebugEnabled() && !debugKey.contains(column.getTableName())){
                    log.debug("[{}]表的列信息信息已解析入内存...", column.getTableName());
                    debugKey.add(column.getTableName());
                }

                return column;
            }
            throw new ExcelParserException("ColumnType信息缺失!", column.toString());
        }
        throw new ExcelContentException("未找到匹配的列类型!", column.toString());
    }

    protected ColumnType ColumnTypeEnum2ColumnType(Object en){
        if(en == null) return null;
        ColumnType type = new ColumnType();

        Class enClass = en.getClass();
        Class<ColumnType> typeClass = ColumnType.class;

        String field = null;
        try {
            Field[] typeFields = typeClass.getDeclaredFields();
            Field[] enFields = enClass.getDeclaredFields();

            for (Field typeField : typeFields) {
                for (Field enField : enFields) {
                    if(StringUtils.equals(typeField.getName(), enField.getName())){
                        field = typeField.getName();
                        typeField.setAccessible(true);
                        enField.setAccessible(true);
                        typeField.set(type, enField.get(en));
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new ExcelParserException("列类型转化失败!",field);
        }
        return type;
    }

    abstract protected ColumnType getColumnType(String columnType, String dbType);
}
