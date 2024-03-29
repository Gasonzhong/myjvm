package com.gason.jvm.entry.impl;

import com.gason.jvm.entry.Entry;
import com.gason.jvm.object.EntryObject;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * @ClassName: Zip_Entry
 * @auther: zhongjias
 * @date: 2019/6/24 13:57
 * @description:
 */
public class Zip_Entry implements Entry {
    private String absPath;
    private String lastPath;
    private Map<String, byte[]> zipReadBytes;

    public Zip_Entry(String path) throws IOException {
        File abs = new File("");
        this.absPath = Paths.get(abs.getCanonicalPath()).resolve(path).toString();
        if (!this.absPath.equals(lastPath) || StringUtils.isEmpty(lastPath)) {
            this.zipReadBytes = new HashMap<String, byte[]>();
        }
        this.lastPath = this.absPath;
    }

    @Override
    public EntryObject readClass(String className) {
        byte[] readByte = null;
        String error = "";
        if (!zipReadBytes.isEmpty()) {
            readByte = zipReadBytes.get(className);
        } else {
            ZipFile zf = null;
            InputStream in = null;
            ZipInputStream zin = null;
            try {
                File file=Paths.get(this.absPath).resolve(className).toFile();
                in = new FileInputStream(file);
                zin = new ZipInputStream(in);
                java.util.zip.ZipEntry ze;
                while ((ze = zin.getNextEntry()) != null) {
                    if (ze.isDirectory()) {
                    } else {
                        long size = ze.getSize();
                        if (size > 0) {
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            byte[] byte_s = new byte[1024];
                            int num = -1;
                            while ((num = zin.read(byte_s, 0, byte_s.length)) > -1) {//通过read方法来读取文件内容
                                byteArrayOutputStream.write(byte_s, 0, num);
                            }
                            readByte = byteArrayOutputStream.toByteArray();
                            this.zipReadBytes.put(ze.getName(), readByte);
                        }
                    }
                }
            } catch (IOException e) {
                error = e.getMessage();
            } finally {
                try {
                    zin.closeEntry();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        readByte = this.zipReadBytes.get(className);
        return new EntryObject(readByte, this, error);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Zip_Entry{");
        sb.append("absPath='").append(absPath).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
