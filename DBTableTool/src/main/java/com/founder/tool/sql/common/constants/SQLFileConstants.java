package com.founder.tool.sql.common.constants;

import com.founder.tool.sql.common.config.ConfigInfo;

/**
 * @className SQLFileConstants
 * @description TODO
 * @author GPJ
 * @date 2019/7/11 14:39
 * @version 1.0
 **/
public class SQLFileConstants {
    private static final String SQL_FILE_TABLE = "table";
    private static final String SQL_FILE_COMMENT = "comment";
    private static final String SQL_FILE_PROCEDURE = "procedure";
    private static final String SQL_FILE_TRIGGER = "trigger";
    private static final String SQL_FILE_DATAS = "datas";
    private static final String SQL_FILE_SUFFIX = ".sql";

    public static String getSQLTableFileName(){
        return "sql-" + ConfigInfo.getDBMoldEnum().getType() + "-" + SQL_FILE_TABLE + SQL_FILE_SUFFIX;
    }

    public static String getSQLCommentFileName(){
        return "sql-" + ConfigInfo.getDBMoldEnum().getType() + "-" + SQL_FILE_COMMENT + SQL_FILE_SUFFIX;
    }

    public static String getSQLProcedureFileName(){
        return "sql-" + ConfigInfo.getDBMoldEnum().getType() + "-" + SQL_FILE_PROCEDURE + SQL_FILE_SUFFIX;
    }

    public static String getSQLTriggerFileName(){
        return "sql-" + ConfigInfo.getDBMoldEnum().getType() + "-" + SQL_FILE_TRIGGER + SQL_FILE_SUFFIX;
    }

    public static String getSQLDatasFileName(){
        return "sql-" + ConfigInfo.getDBMoldEnum().getType() + "-" + SQL_FILE_DATAS + SQL_FILE_SUFFIX;
    }

    public static String getSQLDropFileName(){
        return "sql-" + ConfigInfo.getDBMoldEnum().getType() + "-clear" + SQL_FILE_SUFFIX;
    }
}
