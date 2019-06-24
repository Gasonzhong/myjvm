package com.gason.jvm.entry.impl;

import com.gason.jvm.entry.Entry;
import com.gason.jvm.entry.EntryObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * @ClassName: WildcardEntry
 * @auther: zhongjias
 * @date: 2019/6/24 13:59
 * @description:
 */
public class WildcardEntry implements Entry {
    private String absPath;

    public WildcardEntry(String path) throws IOException {
        path = StringUtils.remove(path, "*");
        File abs = new File("");
        absPath = abs.getCanonicalPath().concat("\\").concat(path);
    }

    public EntryObject readClass(String className) {
        Collection<File> zipFiles = FileUtils.listFiles(new File(absPath), new String[]{"jar", "zip", "ZIP", "JAR"}, true);
        Collection<File> classFiles = FileUtils.listFiles(new File(absPath), new String[]{"class"}, true);
        for (File file : classFiles) {
            if (className.equals(file.getName())) {
                try {
                    return new EntryObject(FileUtils.readFileToByteArray(file), this, "");
                } catch (IOException e) {
                    return  new EntryObject(null, this, e.getMessage());
                }
            }
        }
        for (File file : zipFiles) {
            byte[] readByte = null;
            String error = "";

            ZipFile zf = null;
            InputStream in = null;
            ZipInputStream zin = null;
            try {
                URL url = new URL(this.absPath);
                URLConnection connection = url.openConnection();
                in = connection.getInputStream();
                zin = new ZipInputStream(in);
                java.util.zip.ZipEntry ze;
                while ((ze = zin.getNextEntry()) != null) {
                    if (ze.isDirectory()) {
                    } else {
                        long size = ze.getSize();
                        if (!ze.getName().equals(className)) {
                            continue;
                        }
                        if (size > 0) {
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            byte[] byte_s = new byte[1024];
                            int num = -1;
                            while ((num = zin.read(byte_s, 0, byte_s.length)) > -1) {//通过read方法来读取文件内容
                                byteArrayOutputStream.write(byte_s, 0, num);
                            }
                            readByte = byteArrayOutputStream.toByteArray();
                            return new EntryObject(readByte, this, "");
                        }
                    }
                }
            } catch (IOException e) {
                error = e.getMessage();
                return new EntryObject(readByte, this, error);
            } finally {
                try {
                    zin.closeEntry();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return   new EntryObject(null  , this, "class not found");
    }


}
