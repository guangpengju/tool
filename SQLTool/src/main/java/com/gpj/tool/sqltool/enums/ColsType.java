package com.gpj.tool.sqltool.enums;

import org.apache.commons.lang3.StringUtils;

public enum ColsType {
    ID("ID","varchar","主键型",36,-1,"utf8","NULL"),
    C("C","char","定长字符型",36,-1,"utf8","NULL"),
    VC("VC","varchar","变长字符型",100,-1,"utf8","NULL"),
    N("N","numeric","浮点型",10,2,"","NULL"),
    I("I","int","整数型",10,-1,"","NULL"),
    DC("DC","varchar","字典型",2,-1,"utf8","NULL"),
    MONEY("MONEY","decimal","货币型",10,2,"","NULL"),
    T("DT","time","时间型",-1,-1,"","NULL"),
    DT("DT","timestamp","日期时间型",-1,-1,"","NULL");
    
    private final String showName;
    private final String code;
    private final String name;
    private final int length;
    private final int accuracy;
    private final String encode;
    private final String defaultVal;

    private ColsType(String showName,String code, String name, 
                     int length, int accuracy, String encode, String defaultVal) {
        this.showName = showName;
        this.code = code;
        this.name = name;
        this.length = length;
        this.accuracy = accuracy;
        this.encode = encode;
        this.defaultVal = defaultVal;
    }

    public static ColsType getColsType(String showName) {
        for (ColsType val : ColsType.values()) {
            if (StringUtils.equals(val.showName, showName)) {
                return val;
            }
        }
        return null;
    }

    public String getShowName() {
        return showName;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public String getEncode() {
        return encode;
    }

    public String getDefaultVal() {
        return defaultVal;
    }
}
