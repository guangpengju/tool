package com.founder.tool.sql.common.converter;

import com.founder.tool.sql.common.cache.SQLInfoCache;
import com.founder.tool.sql.common.constants.InputType;
import com.founder.tool.sql.common.pojo.DBBase;
import com.founder.tool.sql.common.pojo.Procedure;
import com.founder.tool.sql.common.pojo.Trigger;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @className AbstractTriggerSQLConverter
 * @description 抽象触发器语句转换器
 * @author GPJ
 * @date 2019/7/10 15:54
 * @version 1.0
 **/
@Slf4j
public abstract class AbstractTriggerSQLConverter implements SQLConverter {
    @Override
    public void convert(OutputStream out) {
        List<String> triggerNames = SQLInfoCache.getTriggerNames();

        for (String triggerName : triggerNames) {
            Trigger trigger = SQLInfoCache.getTrigger(triggerName);
            // 注释语句sql
            write(out, noteSQL("TRIGGER " + triggerName));
            if(log.isDebugEnabled()){
                log.debug("[{}]触发器语句生成完毕",triggerName);
            }

            // 1.删除trigger
            boolean replaceKeyWord = useReplaceKeyWord();
            if(!replaceKeyWord){
                write(out, dropTrigger(triggerName));
            }

            if(StringUtils.isNotBlank(startDelimiter())) write(out, startDelimiter());

            // 2.创建trigger
            write(out, createTrigger(triggerName, replaceKeyWord));

            // 3.条件
            write(out, createCondition(trigger.getTirgger_time(), trigger.getTirgger_event(), trigger.getTable()));

            // 4.declare
            boolean alone = declareAlone();
            if(!alone) write(out, "BEGIN \n");
            write(out, createDeClare(trigger.getDeclare(), alone));

            // 5.body
            if(alone) write(out, "BEGIN \n");
            write(out, createBody(trigger.getBody()));
            write(out, "\nEND \n");

            if(StringUtils.isNotBlank(endDelimiter())) write(out, endDelimiter());
        }
    }

    abstract protected String startDelimiter();

    abstract protected String endDelimiter();

    abstract protected String dropTrigger(String triggerName);

    abstract protected String createTrigger(String triggerName, boolean replaceKeyWord);

    abstract protected String createCondition(String time, String event, String tableName);

    abstract protected String createDeClare(List<DBBase.Entry> datas, boolean alone);

    abstract protected String createBody(List<DBBase.Entry> datas);

    abstract protected boolean useReplaceKeyWord();

    abstract protected boolean declareAlone();
}
