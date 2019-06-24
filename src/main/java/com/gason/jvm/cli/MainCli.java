package com.gason.jvm.cli;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * @ClassName: MainCli
 * @auther: zhongjias
 * @date: 2019/6/24 10:18
 * @description: 命令行工具
 */
public class MainCli {
//    static final String[] mJvmArgs = {"java", "-version", "-help"};

    public static void main(String[] args) {
        System.out.println("gason-jvm start!!!");
        String test="dfsdf\\sfsd.zip";
        File abs = new File("");
        test=StringUtils.remove(test,".zip");
        System.out.println(test);
//        JvmCli.parseCmd(args);
//        JvmCli.startJvm(args);
    }
}
