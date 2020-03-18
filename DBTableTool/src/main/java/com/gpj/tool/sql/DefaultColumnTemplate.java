package com.gpj.tool.sql;

import com.gpj.tool.sql.core.pojo.Table;

/**
 * @className ColumnTemplate
 * @description TODO
 * @author GPJ
 * @date 2020/3/18 22:39
 * @version 1.0
 **/
public class DefaultColumnTemplate {
//    private String template = "`SYS_DOCLIBID` decimal(6, 0) NOT NULL DEFAULT 0";
    private String template = "`${code}` ${type} #[isNumType]{(${} , ${})} #[]{NOT NULL} #[]{DEFAULT 0} #[]{} #[]{,}";

    public void generateSQL(Table table){
        int index = 0;
        index = template.indexOf("#", index);
    }

    private String expHandle(String template){
        int index = 0;
        int expStart = 0;
        int expEnd = template.length();
        expStart = template.indexOf("#", expStart);
        String startSql = template.substring(index, expStart);
        String expTemplate = template.substring(expStart, expEnd);
        expEnd = findExpEnd(expTemplate);
        expTemplate = template.substring(expStart, expEnd);
        String expSql = invoke(analysisExpMethod(expTemplate), analysisExpParam(expTemplate));
        String endTemplate = template.substring(expEnd, template.length());
        String endSql = expHandle(endTemplate);
        return startSql + expSql + endSql;

    }

    private int findExpEnd(String expTemplate){
        int index = 0;
        int num = 0;

        do {
            int start = template.indexOf("{", index);
            int end = template.indexOf("}", index);

            if(start < end){
                num++;
                index = start;
            }else{
                num--;
                index = end;
            }
        } while(num == 0);
        return index;
    }

    private String analysisExpMethod(String expTemplate){
        return expTemplate.substring(expTemplate.indexOf("#["), expTemplate.indexOf("]"));
    }

    private String analysisExpParam(String expTemplate){
        return expTemplate.substring(expTemplate.indexOf("{"), expTemplate.lastIndexOf("}")).trim();
    }

    private String invoke(String mothd, String params){
        return isNumType(params);
    }

    private String isNumType(String params){
        return null;
    }
}
