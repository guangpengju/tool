package com.founder.tool.sql.common.converter;

import com.founder.tool.sql.common.exception.SQLConverterException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @className SQLConverter
 * @description sql转化器
 * @author GPJ
 * @date 2019/7/10 15:37
 * @version 1.0
 **/
public interface SQLConverter {
    /**
     * 转化
     */
    public void convert(OutputStream out);

    default String noteSQL(String sql){
        String noteDelimiter = "-- --------------------------------------------------------\n";
        return "\n" + noteDelimiter + String.format("-- %s \n",sql) + noteDelimiter;
    }

    default void write(OutputStream out, String data){
        try {
            if(StringUtils.isNotBlank(data)) out.write(data.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new SQLConverterException(e.getMessage(), data);
        }
    }

}
