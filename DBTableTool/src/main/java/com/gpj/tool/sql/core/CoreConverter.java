package com.gpj.tool.sql.core;

import com.gpj.tool.sql.core.io.DefaultExcelReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @className CoreC
 * @description TODO
 * @author GPJ
 * @date 2020/3/18 16:01
 * @version 1.0
 **/
@Slf4j
@Component
public class CoreConverter {
    @Autowired
    private DefaultExcelReader reader;

    @PostConstruct
    public void convert(){
        reader.read();
    }

}
