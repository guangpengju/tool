package cpm.gpj.tool.db;

import com.gpj.tool.db.core.pojo.Column;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * @className OtherTest
 * @description TODO
 * @author GPJ
 * @date 2020/3/19 8:58
 * @version 1.0
 **/
public class OtherTest {

    @Test
    public void classPtahTest() throws IOException {
        System.out.println(new ClassPathResource("/").toString());
    }

    @Test
    public void filedValue() {
        String str = "${columns.type}12345 |,| aaa";

        System.out.println(str.substring(0, str.indexOf(",")) + str.substring(str.indexOf(",") + 1, str.length() - 1));
    }

}
