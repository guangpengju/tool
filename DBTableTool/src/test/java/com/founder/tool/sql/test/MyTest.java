package com.founder.tool.sql.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @className MyTest
 * @description TODO
 * @author GPJ
 * @date 2019/7/17 13:36
 * @version 1.0
 **/
public class MyTest {
    @Test
    public void test(){
        String[] values = {"out ","OUT "," out ","asout ","asoutss"," outsss","out asout outi"};
        String regex = "^\\s?(out|OUT)\\s.*";

        for (String value : values) {
            System.out.println("[" + value + "]匹配结构:" + value.matches(regex) + ";replace:" + "[" + value.replaceAll("\\s?(out|OUT)\\s","") + "]");
        }
    }

    @Test
    public void test2(){
        String[] values = {"out "," out "," out","asr out","out asd","asout asda","ad asoutasd fff", "asda outssx","bfib   out","fafwfqwfdcs    out"};
        String regex = "\\bout\\b";
        for (String value : values) {
            System.out.println("[" + value + "]" + "替换后" + "[" + value.replaceAll(regex,"|gpj|") + "]");
        }
    }

    @Test
    public void test3(){
        List<String> list = new ArrayList();
        list.add("1234");
        list.add("2234");
        list.add("3234");

        System.out.println(list.stream().map(s -> {
            return "";
        }).filter(s -> StringUtils.isNotBlank(s)).collect(Collectors.joining(";")));
    }
}
