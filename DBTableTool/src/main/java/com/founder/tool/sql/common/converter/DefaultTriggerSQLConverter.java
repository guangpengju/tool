package com.founder.tool.sql.common.converter;

import com.founder.tool.sql.common.pojo.DBBase;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @className DefaultTriggerSQLConverter
 * @description TODO
 * @author GPJ
 * @date 2019/7/15 14:56
 * @version 1.0
 **/
@Setter
public class DefaultTriggerSQLConverter extends AbstractTriggerSQLConverter {
    private AbstractProcedureSQLConverter converter;

    @Override
    protected String dropTrigger(String triggerName) {
        return String.format("DROP TRIGGER IF EXISTS %s; \n", triggerName);
    }

    @Override
    protected String createTrigger(String triggerName, boolean replaceKeyWord) {
        return String.format("CREATE %s TRIGGER %s \n", replaceKeyWord?"OR REPLACE":"", triggerName);
    }

    @Override
    protected String createCondition(String time, String event, String tableName){
        return String.format("%s %s ON %s FOR EACH ROW \n", time.toUpperCase(), event.toUpperCase(), tableName);
    }

    @Override
    protected String createDeClare(List<DBBase.Entry> datas, boolean alone) {
        String sql = null;
        if(datas != null && !datas.isEmpty()){
            sql = alone?"DECLARE \n":"";
            sql += datas.stream().map( entry -> "\t" + (alone?"":"declare ") + entry.getData() + " " + entry.getAssist() ).collect(Collectors.joining("; \n"));
            sql += "; \n";
        }
        return sql;
    }

    @Override
    protected String startDelimiter() {
        return converter.startDelimiter();
    }

    @Override
    protected String endDelimiter() {
        return converter.endDelimiter();
    }

    @Override
    protected String createBody(List<DBBase.Entry> datas) {
        return converter.createBody(datas, null);
    }

    @Override
    protected boolean useReplaceKeyWord(){
        return converter.useReplaceKeyWord();
    }

    @Override
    protected boolean declareAlone(){
        return converter.declareAlone();
    }
}
