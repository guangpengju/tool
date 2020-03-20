package com.gpj.tool.db.core.template;

import com.gpj.tool.db.core.handler.TemplateHandler;
import com.gpj.tool.db.core.pojo.Table;
import com.gpj.tool.db.core.pojo.TableCursor;
import com.gpj.tool.db.core.store.InfoStore;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @className DefaultSqlHandler
 * @description TODO
 * @author GPJ
 * @date 2020/3/18 22:31
 * @version 1.0
 **/
@Slf4j
@Component
public abstract class DefaultAbstractTemplate extends TemplateHandler implements Template{

    public String generateSQL(Table table){
        cursor = new TableCursor(table);
        String sql = analysisExpression(getSqlTemplate());
        return analysisPlaceholder(sql);
    }

    private <T> String loop(ArrayList<T> list, String sql){
        StringBuffer buffer = new StringBuffer();
        if(list != null && !list.isEmpty()){

            list.stream().forEach( item -> {
                cursor.setItem(item);
                String result = analysisExpression(sql);
                cursor.setItem(item);
                buffer.append(analysisPlaceholder(result));
            });
        }
        return buffer.toString();
    }

    private String loop(HashMap<String,String> map, String sql){
        StringBuffer buffer = new StringBuffer();
        if(map != null && !map.isEmpty()){

            map.values().stream().forEach( item -> {
                cursor.setItem(item);
                String result = analysisExpression(sql);
                cursor.setItem(item);
                buffer.append(analysisPlaceholder(result));
            });
        }
        return buffer.toString();
    }

    private String removeLastSep(String sql){
        sql = analysisExpression(sql);
        sql = analysisPlaceholder(sql);

        int index = sql.lastIndexOf(",");
        String headSql = sql.substring(0, index);
        String tailSql = sql.substring(index + 1);
        return headSql + tailSql;
    }

    private String isTrue(Boolean param, String sql){
        if(param){
            String result = analysisExpression(sql);
            return analysisPlaceholder(result);
        }
        return "";
    }

    private String isFalse(Boolean param, String sql){
        if(!param){
            String result = analysisExpression(sql);
            return analysisPlaceholder(result);
        }
        return "";
    }

    private String userDefault(String value, String sql){
        sql = StringUtils.isBlank(value)?sql:value;
        String result = analysisExpression(sql);
        return analysisPlaceholder(result);
    }

    private String isEmpty(String value, String sql){
        sql = !StringUtils.isBlank(value)?sql:"";
        String result = analysisExpression(sql);
        return analysisPlaceholder(result);
    }


    private String isUnionPrimary(String sql){
        if(cursor.getTable().getPrimaryNum() > 1){
            String result = analysisExpression(sql);
            return analysisPlaceholder(result);
        }
        return "";
    }

    private String isNotUnionPrimary(String sql){
        if(cursor.getTable().getPrimaryNum() < 2){
            String result = analysisExpression(sql);
            return analysisPlaceholder(result);
        }
        return "";
    }
}
