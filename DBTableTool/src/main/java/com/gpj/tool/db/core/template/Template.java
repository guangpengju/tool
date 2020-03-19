package com.gpj.tool.db.core.template;

import com.gpj.tool.db.core.pojo.Table;

public interface Template {

    public String generateSQL(Table table);

    String getSqlTemplate();
}
