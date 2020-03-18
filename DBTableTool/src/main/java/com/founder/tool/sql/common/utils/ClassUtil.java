package com.founder.tool.sql.common.utils;

import com.founder.tool.sql.common.exception.NewInstanceFailedException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @className ClassUtil
 * @description 类处理工具类
 * @author GPJ
 * @date 2019/7/11 17:08
 * @version 1.0
 **/
@Slf4j
public class ClassUtil {
    private static final String JAR_SUFFIX = "jar";
    private static final String CLASS_SUFFIX = ".class";

    public static Class createSonClass(String packageName, Class clazz) {
        packageName = packageName.replace(".", "/");
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            List<Class> classes = new ArrayList<>();
            Enumeration<URL> resources = loader.getResources(packageName);
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                classes.addAll(searchClass(url, packageName));
            }
            return findSonClass(classes, clazz);
        } catch (IOException | ClassNotFoundException e) {
            throw new NewInstanceFailedException(e.getMessage(), clazz);
        }
    }
    
    private static List<Class> searchClass(URL url, String packageName) throws IOException, ClassNotFoundException {
        if(StringUtils.equals(JAR_SUFFIX, url.getProtocol())){
            return searchClassFromJar(url, packageName.replace(".","/"));
        }else{
            return searchClassFromDir(new File(url.getFile()), packageName);
        }
    }
    
    private static List<Class> searchClassFromDir(File directory, String packageName) throws ClassNotFoundException {
        List<Class> list = new ArrayList<>();

        File[] files = directory.listFiles();
        if(files != null && files.length > 0){
            for (File file : files) {
                log.debug("搜索路径为:" + file.getPath());
                if(file.isDirectory()){
                    list.addAll(searchClassFromDir(file, packageName));
                }else if(file.getName().endsWith(CLASS_SUFFIX)){
                    Class clazz = createClass(file.getPath().replace("\\", "/"), packageName);
                    if(clazz != null){
                        list.add(clazz);
                    }
                }
            }
        }
        return list;
    }

    private static List<Class> searchClassFromJar(URL url, String packageName) throws IOException, ClassNotFoundException {
        List<Class> list = new ArrayList<>();
        log.debug("搜索路径为:" + url.getPath());
        JarURLConnection connection = (JarURLConnection) url.openConnection();
        if(connection != null){
            JarFile jarFile = connection.getJarFile();
            if(jarFile != null){
                Enumeration<JarEntry> entries = jarFile.entries();
                while(entries.hasMoreElements()){
                    JarEntry element = entries.nextElement();
                    Class clazz = createClass(element.getName(), packageName);
                    if(clazz != null){
                        list.add(clazz);
                    }
                }
            }
        }
        return list;
    }

    private static Class createClass(String path, String packageName) throws ClassNotFoundException {
        if(StringUtils.isNotBlank(path) && path.endsWith(CLASS_SUFFIX) && path.contains(packageName)){
            path = path.substring(path.indexOf(packageName), path.lastIndexOf(".")).replace("/",".");
            return Class.forName(path);
        }
        return null;
    }
    
    private static Class findSonClass(List<Class> classes, Class parentClass){
        for (Class clazz : classes) {
            if(isAssignableFrom(parentClass, clazz)) return clazz;
        }
        return null;
    }

    public static boolean isAssignableFrom(Class parentClass, Class sonClass){
        return parentClass.isAssignableFrom(sonClass);
    }
}
