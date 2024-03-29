package com.gason.jvm.entry.impl;

import com.gason.jvm.entry.Entry;
import com.gason.jvm.object.EntryObject;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: CompositeEntry
 * @auther: zhongjias
 * @date: 2019/6/24 13:58
 * @description:
 */
public class CompositeEntry implements Entry {
    private List<String> absPathList;

    public CompositeEntry(String paths) throws IOException {
        String pathArr[] = paths.split(pathListSeparator);
        absPathList = new ArrayList<String>();
        File abs = new File("");
        for (String path : pathArr) {
            absPathList.add(Paths.get(abs.getCanonicalPath()).resolve(path).toString());
        }
    }

    /**
     * 读取后
     *
     * @param className
     * @return
     */
    @Override
    public EntryObject readClass(String className) {
        String error = null;
        byte[] readByte = null;
        for (String path : absPathList) {
            String fileName = path.concat("\\").concat(className);
            File file=new File(fileName);
            if(file.exists()){
                try {
                    readByte = FileUtils.readFileToByteArray(FileUtils.getFile(fileName));
                } catch (IOException e) {
                    error = e.getMessage();
                }
                continue;
            }
        }
        if (readByte == null) {
            error = className+"file not found";
        }
        return new EntryObject(readByte, this, error);
    }
}
