package com.gpj.tool.db.core.handler;

import com.gpj.tool.db.core.pojo.TableCursor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @className TemplateHandler
 * @description TODO
 * @author GPJ
 * @date 2020/3/19 20:51
 * @version 1.0
 **/
@Slf4j
public abstract class TemplateHandler {
    protected TableCursor cursor;

    protected String analysisPlaceholder(String template){
        try {
            while (true) {
                int head = template.indexOf("$");
                if (head < 0) break;
                int tail = template.indexOf("}", head + 1);
                String placeholder = template.substring(head + 2, tail);
                String value = null;
                value = (String) transform(placeholder);
                template = template.replace("${" +placeholder + "}", value);
            }
        } catch (Exception e) {
            log.error("占位符替换错误，未找到匹配值{} --> {}", e.toString(), template, e);
        }
        return template;
    }

    protected String analysisExpression(String template){
        if(StringUtils.isNotBlank(template)){
            int expIndex = template.indexOf("#");
            if(expIndex > -1){
                String headSql = template.substring(0, expIndex);
                String expressionSql = template.substring(expIndex);
                int[] areas = findAreaOfExp(expressionSql);
                String method = analysisExpMethod(expressionSql);
                String param = analysisExpParam(expressionSql);
                expressionSql = invokeMethod(method, param);
                return headSql + expressionSql + analysisExpression(template.substring(expIndex + areas[1] + 1));
            }
        }
        return template;
    }

    private String analysisExpMethod(String expTemplate){
        return expTemplate.substring(expTemplate.indexOf("#[") + 2, expTemplate.indexOf("]"));
    }

    private String analysisExpParam(String expTemplate){
        int[] areas = findAreaOfExp(expTemplate);
        int head = areas[0];
        int tail = areas[1];

        if(head < (tail - 1)){
            return expTemplate.substring(head + 1, tail);
        }
        return "";
    }

    public int[] findAreaOfExp(String expTemplate){
        int head = 0,tail = 0,num = 0;
        do {
            tail = expTemplate.indexOf("}", tail + 1);
            num--;

            while (true){
                int index = expTemplate.indexOf("{", head + 1);
                if(index < tail && index > 0){
                    head = index;
                    num++;
                }else{
                    break;
                }
            }
        } while(num != 0);
        return new int[]{expTemplate.indexOf("{"), tail};
    }

    private String invokeMethod(String method, String sql) {
        try {
            if(method.indexOf("|") != -1){
                String[] methodInfo = method.split("\\|");
                Object obj = transform(methodInfo[1]);
                Method realMethod = findMethod(this.getClass(), methodInfo[0], obj.getClass(), String.class);
                realMethod.setAccessible(true);
                return (String) realMethod.invoke(this, obj, sql);
            }else{
                Method realMethod = findMethod(this.getClass(), method, String.class);
                realMethod.setAccessible(true);
                return (String) realMethod.invoke(this, sql);
            }
        } catch (Exception e) {
            log.error("模板转换错误，方法调用错误:{} --> {{},{}}", e.toString(), method, sql,e);
            return "";
        }
    }

    private Method findMethod(Class clazz, String methodName, Class... param) throws NoSuchMethodException {
        try {
            return clazz.getDeclaredMethod(methodName, param);
        } catch (NoSuchMethodException e) {
            Class superclass = clazz.getSuperclass();
            if(superclass != null){
                return findMethod(superclass, methodName, param);
            }
            throw e;
        }
    }

    private Object transform(String exp) throws NoSuchFieldException, IllegalAccessException {
        String[] expArr = exp.split("\\.");
        try {
            Field field = cursor.getClass().getDeclaredField(expArr[0]);
            field.setAccessible(true);
            Object obj = field.get(cursor);
            field = obj.getClass().getDeclaredField(expArr[1]);
            field.setAccessible(true);
            return field.get(obj);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            log.error("模板转换错误，表达式转义错误:{}", exp, e);
            throw e;
        }
    }
}
