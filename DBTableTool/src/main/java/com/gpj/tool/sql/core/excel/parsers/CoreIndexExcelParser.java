package com.gpj.tool.sql.core.excel.parsers;

import com.gpj.tool.sql.core.exceptions.ExcelAnalysisException;
import com.gpj.tool.sql.core.pojo.Column;
import com.gpj.tool.sql.core.pojo.RowInfo;
import com.gpj.tool.sql.store.InfoStore;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @className CoreColumnExcelParser
 * @description TODO
 * @author GPJ
 * @date 2020/3/18 17:07
 * @version 1.0
 **/
public class CoreIndexExcelParser {
    @Autowired
    InfoStore store;

    @Autowired
    private List<IndexExcelParser> indexParsers;

    public void analysisForIndexInfo(RowInfo rowInfo) throws ExcelAnalysisException {
        // TODO
    }
}
