package com.gason.jvm.cli;

import com.gason.jvm.exception.EntryException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;

/**
 * @ClassName: MainCli
 * @auther: zhongjias
 * @date: 2019/6/24 10:18
 * @description: 命令行工具
 */
public class MainCli {
//    static final String[] mJvmArgs = {"java", "-version", "-help"};

    public static void main(String[] args) {
        int i = 6;
        switch (i) {
            case 0:
                break;
            case 6:
                System.out.println(i);
                break;
            default:
                break;
        }
//        System.out.println("gason-jvm start!!!");
////        JvmCli.parseCmd(args);
        try {
            JvmCli.startJvm(args);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EntryException e) {
            e.printStackTrace();
        }
    }
}
