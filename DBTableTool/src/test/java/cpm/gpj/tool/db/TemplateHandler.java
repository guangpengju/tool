package cpm.gpj.tool.db;

import com.gpj.tool.db.mysql.MysqlTemplate;
import org.junit.Test;

/**
 * @className TemplateHandler
 * @description TODO
 * @author GPJ
 * @date 2020/3/19 22:37
 * @version 1.0
 **/
public class TemplateHandler {
    @Test
    public void findAreaOfExpTest(){
        MysqlTemplate template = new MysqlTemplate();
        String str = "#[removeLastSep]{" +
                "#[loop|table.columns]{" +
                "\t `${item.code}` ${item.type} $(item.size) " +
                "#[isTrue|item.required]{NOT NULL} DEFAULT #[isEmpty|item.defaultValue]{NULL} " +
                "#[isUnionPrimary]{PRIMARY KEY} #[isTrue|item.autoPrimary]{AUTO_INCREMENT} , \n" +
                "}" +

                "#[isUnionPrimary]{PRIMARY KEY (#[loop|table.columns]{'${item.code}'}) USING BTREE, \n}" +

                "#[loop|table.indexs]{" +
                "\t INDEX `${item.indexName}`(#[loop|item.columnNames]{`${item}`}) USING BTREE , \n" +
                "}" +
                "}" +
                ") ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic; \n";
        int[] area = template.findAreaOfExp(str);
        System.out.println(str.substring(area[0], area[1] + 2));
    }
}
