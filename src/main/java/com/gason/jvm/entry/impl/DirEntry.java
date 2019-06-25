package com.gason.jvm.entry.impl;

import com.gason.jvm.entry.Entry;
import com.gason.jvm.entry.EntryObject;
import com.gason.jvm.exception.EntryException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * @ClassName: DirEntry
 * @auther: zhongjias
 * @date: 2019/6/24 13:57
 * @description:
 */
public class DirEntry implements Entry {
    private String absPath;

    public DirEntry(String path) throws  IOException {
        File abs = new File("");
        absPath= Paths.get(abs.getCanonicalPath()).resolve(path).toString();
    }

    @Override
    public EntryObject readClass(String className) {
        String fileName = Paths.get(this.absPath).resolve(className).toString();
        String error = null;
        byte[] readByte=null;
        try {
           readByte = FileUtils.readFileToByteArray(FileUtils.getFile(fileName));
        } catch (IOException e) {
            error = e.getMessage();
        }

        return new EntryObject(readByte,this,error);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DirEntry{");
        sb.append("absPath='").append(absPath).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
