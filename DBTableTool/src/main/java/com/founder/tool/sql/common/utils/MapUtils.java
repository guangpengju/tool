package com.founder.tool.sql.common.utils;

import com.founder.tool.sql.common.pojo.Procedure;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class MapUtils {
    public static <T> void saveListMapping(String key, T val, Map<String, List<T>> map){
        saveListMapping(key, val, map, true);
    }

    public static <T> void saveListMapping(String key, T val, Map<String, List<T>> map, boolean repeat){
        if(map.containsKey(key)){
            List<T> list = map.get(key);
            if(repeat || !list.contains(val)) list.add(val);
        }else{
            List<T> list = new ArrayList<>();
            list.add(val);
            map.put(key, list);
        }
    }

    public static <E> void saveEntryMapping(String key, Class<E> entryClass, Map<String, E> map, MapCallBack<E> callback) {
        if(StringUtils.isNotBlank(key)) {
            if (map.containsKey(key)) {
                E entry = map.get(key);
                callback.callback(entry, false);
            } else {
                try {
                    E entry = entryClass.newInstance();
                    callback.callback(entry, true);
                    map.put(key, entry);
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
