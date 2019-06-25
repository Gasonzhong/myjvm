package com.gason.jvm.cli;

import com.gason.jvm.entry.ClassPath;
import com.gason.jvm.entry.EntryObject;
import com.gason.jvm.exception.EntryException;
import org.apache.commons.cli.*;
import org.apache.commons.lang3.StringUtils;

import javax.management.openmbean.SimpleType;
import java.io.File;
import java.io.IOException;


/**
 * @ClassName: JvmCli
 * @auther: zhongjias
 * @date: 2019/6/24 10:24
 * @description:
 */
public class JvmCli {
    private final static String TAG = "gason-jvm";
    private final static String mVersion = "1.0";
    private static String mXJre;
    private static String className;
    private static CommandLine cmd;

    public static void parseCmd(String[] args) {

        Options options = new Options();

        options.addOption("h", "help", false, "Show Usage");
        options.addOption("v", "version", false, "Show Version");
        options.addOption("cp", "classpath", true, "Jar path");
        options.addOption("X", "Xjre", true, "Jar path");
        options.addOption("cl", "class", true, "className");
        CommandLineParser parser = new DefaultParser();
        HelpFormatter hf = new HelpFormatter();
        try {
            cmd = parser.parse(options, args);

            if (cmd.hasOption("h")) {
                hf.printHelp(TAG, options, true);
            }

            if (cmd.hasOption("version")) {
                System.out.println(TAG + "Version : " + mVersion);
            }

            if (cmd.hasOption("classpath")) {
                String[] mClassPath = cmd.getOptionValues("classpath");
            }

            if (cmd.hasOption("X")) {
                mXJre = cmd.getOptionValue("Xjre");
            }
            if (cmd.hasOption("cl")) {
                className = cmd.getOptionValue("class");
            }
            if (cmd.getArgList().size() == 0) {
                hf.printHelp(TAG, options, true);
            }
        } catch (ParseException e) {
            hf.printHelp(TAG, options, true);
            e.printStackTrace();
        }

    }

    public static void startJvm(String[] args) throws IOException, EntryException {
        parseCmd(args);
        ClassPath cp = new ClassPath(cmd.getOptionValue("Xjre"), cmd.getOptionValue("classpath"));
        if (StringUtils.isNotBlank(className)) {
            className = className.replace(".", "/");
            EntryObject object = cp.readClass(className);
            if(StringUtils.isNotBlank(object.getError())){
                System.out.println("error:"+object.getError());
            }
            System.out.println("byte[]:"+object.getReadByte().toString());
        }
    }
}
