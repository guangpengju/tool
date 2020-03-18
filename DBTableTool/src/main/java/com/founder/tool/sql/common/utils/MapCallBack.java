package com.founder.tool.sql.common.utils;

@FunctionalInterface
public interface MapCallBack<T> {
    public void callback(T t, boolean isNewInstance);
}
