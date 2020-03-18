package com.founder.tool.sql.common.manage;

import com.founder.tool.sql.common.config.ConfigEnum;
import com.founder.tool.sql.common.config.ConfigInfo;
import com.founder.tool.sql.common.exception.SQLToolException;
import com.founder.tool.sql.common.parser.ExcelParser;
import com.founder.tool.sql.common.utils.IOUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.List;

/**
 * @className DefaultExcelParserManager
 * @description 默认excel解析器处理器
 * @author GPJ
 * @date 2019/7/10 17:26
 * @version 1.0
 **/
@Slf4j
@AllArgsConstructor
public class DefaultExcelParserManager implements Manager {
    private List<ExcelParser> list;

    @Override
    public void handle() {
        String filePath = ConfigInfo.getStringConfig(ConfigEnum.EXCEL_PATH.getKey());
        String fileName = ConfigInfo.getStringConfig(ConfigEnum.EXCEL_FILE.getKey());

        InputStream in = IOUtils.initInputstream(filePath, fileName);
        if(in == null) return;

        XSSFWorkbook workbook = IOUtils.initExcelReader(in);
        if(workbook == null) return;

        if (list != null && !list.isEmpty()) {
            try {
                for (ExcelParser excelParser : list) {
                    excelParser.analysis(workbook);
                }
            }catch (SQLToolException e){
                log.error("Excel解析错误!:{}", e.getMessage());
                throw new SQLToolException("Excel解析错误");
            }
        }
        IOUtils.closeExcelReader(in, workbook);
    }
}
