package com.founder.tool.sql;

import org.apache.commons.lang3.StringUtils;

import java.io.*;

/**
 * @className MyTool
 * @description TODO
 * @author GPJ
 * @date 2019/9/17 10:12
 * @version 1.0
 **/
public class MyTool {
    public static void main(String[] args) {
//        MyTool.more2one("E:\\gtmp\\datas\\handle");
        MyTool.change("C:\\Users\\f\\Desktop\\sql");
    }

    public static void change(String dirPath){
        File dir = new File(dirPath);

        File[] files = dir.listFiles();


        for (File file : files) {
            if (file.isFile()) {
                String fileName = file.getName();
                String tableName = file.getName().substring(0, file.getName().indexOf("."));
                System.out.println("正在生成[" + tableName + "]表的数据...");
                try (
                        BufferedReader reader = new BufferedReader(new FileReader(file));
                        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(dirPath + "\\sql\\" + fileName)));
                ) {
                    String line = null;
                    writer.write("delete from " + tableName + ";");
                    writer.newLine();
                    while (StringUtils.isNotBlank(line = reader.readLine())) {
                        line = line.replace("`", "");
                        line = line.replace("\\n", "");
                        line = line.replace("\\\"", "\"");
                        line = line.replace("\\'", "''");
                        writer.write(line);
                        writer.newLine();
                    }
                    writer.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* public static void more2one(String dirPath){
        File dir = new File(dirPath);

        File[] files = dir.listFiles();
        int breakNum = 5.replaceAll(00;
        int fileNum = 1;
        int i  = 1;
        int lineNum = 0;
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(new File(dirPath + "\\sql\\datas-" + i + ".sql")));
            for (File file : files) {
                if (file.isFile()) {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    System.out.println("正在生成(" + fileNum++ + ")[" + file.getName().substring(0, file.getName().indexOf(".")) + "]表的数据...");

                    writer.newLine();
                    writer.newLine();
                    writer.newLine();
                    writer.write("-----------------------------------------------------------------------------");
                    writer.newLine();
                    writer.write("--------------------------------- ");
                    writer.write(file.getName().substring(0, file.getName().indexOf(".")));
                    writer.write(" ----------------------------------");
                    writer.newLine();
                    writer.write("-----------------------------------------------------------------------------");

                    String line = null;
                    while (StringUtils.isNotBlank(line = reader.readLine())) {
                        line = line.replace("`", "");
                        line = line.replace("\\n", "");
                        line = line.replace("\\\"", "\"");
                        line = line.replace("\\'", "''");
                        writer.newLine();
                        writer.write(line);
                        lineNum++;
                        if(line.indexOf(";") != -1 && lineNum > breakNum){
                            writer.flush();
                            lineNum = 0;
                            writer = new BufferedWriter(new FileWriter(new File(dirPath + "\\sql\\datas-" + ++i + ".sql")));
                            writer.write("---------------------------------- datas-" + i + ".sql ----------------------------------");
                            writer.newLine();
                        }
                    }
                    writer.flush();
                }
                if(lineNum > breakNum){
                    lineNum = 0;
                    writer = new BufferedWriter(new FileWriter(new File(dirPath + "\\sql\\datas-" + ++i + ".sql")));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
