package com.gason.jvm.cli;

import org.apache.commons.cli.*;

import java.io.File;
import java.io.IOException;



/**
 * @ClassName: JvmCli
 * @auther: zhongjias
 * @date: 2019/6/24 10:24
 * @description:
 */
public class JvmCli {
    private final static String TAG="gason-jvm";
    private final static String mVersion="1.0";
    public static void parseCmd(String [] args){

        Options options = new Options();

        options.addOption("h", "help", false, "Show Usage");
        options.addOption("v", "version", false, "Show Version");
        options.addOption("cp", "classpath", true, "Jar path");
        options.addOption("X", "Xjre", true, "Jar path");

        CommandLineParser parser = new DefaultParser();
        HelpFormatter hf = new HelpFormatter();
        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("h")) {
                hf.printHelp(TAG, options, true);
            }

            if (cmd.hasOption("version")) {
                System.out.println(TAG+"Version : " +mVersion);
            }

            if (cmd.hasOption("classpath")) {
                String[] mClassPath = cmd.getOptionValues("classpath");
            }

            if (cmd.hasOption("X")) {
                String mXJre = cmd.getOptionValue("Xjre");
            }

            if(cmd.getArgList().size() == 0){
                hf.printHelp(TAG, options, true);
            }
        } catch (ParseException e) {
            hf.printHelp(TAG, options, true);
            e.printStackTrace();
        }

    }

    public static void startJvm(String [] args){

        System.err.println(TAG+"startJvm !!!");
    }
}
