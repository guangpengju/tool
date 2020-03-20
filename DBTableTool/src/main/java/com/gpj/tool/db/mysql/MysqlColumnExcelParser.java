package com.gpj.tool.db.mysql;

import com.gpj.tool.db.core.enums.ColumnType;
import com.gpj.tool.db.core.parsers.ColumnExcelParser;
import com.gpj.tool.db.core.pojo.Column;
import com.gpj.tool.db.core.pojo.RowInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @className MysqlColumnExcelParser
 * @description TODO
 * @author GPJ
 * @date 2020/3/19 12:26
 * @version 1.0
 **/
@Slf4j
public class MysqlColumnExcelParser implements ColumnExcelParser {
    @Override
    public boolean analysis(RowInfo rowInfo, Column column) {
        try {
            // 处理类型
            column.setType(this.trans(column.getTypeEM()));
            // 处理长度和精度
            column.setLength(showLength(column.getTypeEM())?column.getLength():-1);
            column.setAccuracy(showLength(column.getTypeEM())?column.getAccuracy():-1);
            // 处理长度和精度sql
            if(showLength(column.getTypeEM())){
                StringBuilder builder = new StringBuilder();
                builder.append("(").append(column.getLength());
                if(column.getAccuracy() >= 0){
                    builder.append(",").append(column.getLength());
                }
                builder.append(")");
                column.setSize(builder.toString());
            }

            // 处理默认值
            String defaultValue = column.getDefaultValue();
            if(StringUtils.isNotBlank(defaultValue) && isString(column.getTypeEM())){
                column.setDefaultValue("'" + defaultValue + "'");
            }
            return true;
        }catch (IllegalStateException e){
            log.error("列类型匹配失败，请确定是否新增列类型[{}]", column.getTypeEM());
            return false;
        }
    }

    private String trans(ColumnType type){
        switch (type){
            case VC: return "varchar";
            case C: return "char";
            case N: return "decimal";
            case I: return "int";
            case DT: return "datetime";
            case MONEY: return "decimal";
            case T: return "time";
            case CLOB: return "longtext";
            case BLOB: return "blob";
            case TEXT: return "longtext";
            default: throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    private boolean showLength(ColumnType type){
        switch (type){
            case VC:
            case C:
            case N:
            case MONEY:
                return true;
            default:
                return false;
        }
    }

    private boolean isString(ColumnType type){
        switch (type){
            case VC:
            case C:
            case DT:
            case T:
            case CLOB:
            case TEXT:
                return true;
            default:
                return false;
        }
    }
}
