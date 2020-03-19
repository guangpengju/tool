package com.gpj.tool.db.mysql;

import com.gpj.tool.db.core.template.DefaultAbstractTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * @className DefaultSqlHandler
 * @description TODO
 * @author GPJ
 * @date 2020/3/18 22:31
 * @version 1.0
 **/
@Slf4j
public class MysqlTemplate extends DefaultAbstractTemplate {
    @Override
    public String getSqlTemplate() {
        return "DROP TABLE IF EXISTS `${table.name}`; \n" +
            "CREATE TABLE `${table.name}` ( \n" +
                "#[removeLastSep]{" +
                    "#[loop|table.columns]{" +
                        "\t `${item.code}` ${item.type} ${item.size} " +
                        "#[isTrue|item.required]{NOT NULL} DEFAULT #[isEmpty|item.defaultValue]{NULL} " +
                        "#[isNotUnionPrimary]{#[isTrue|item.primary]{PRIMARY KEY} #[isTrue|item.autoPrimary]{AUTO_INCREMENT}} , \n" +
                    "}" +

                    "\t #[isUnionPrimary]{PRIMARY KEY (#[removeLastSep]{#[loop|table.columns]{#[isTrue|item.primary]{'${item.code}',}}}) USING BTREE, \n}" +

                    "#[loop|table.indexs]{" +
                        "\t INDEX `${item.indexName}`(#[loop|item.columnNames]{`${item}`}) USING BTREE , \n" +
                    "}" +
                "}" +
            ") ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic; \n";
    }
}