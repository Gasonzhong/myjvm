package com.gason.jvm.core.classfile.constantinfo.info;

import com.gason.jvm.core.classfile.constantinfo.ConstantInfo;
import com.gason.jvm.core.loader.ClassReader;

/**
 * @ClassName: ConstantUtf8Info
 * @auther: zhongjias
 * @date: 2019/6/27 14:17
 * @description: MUTF-8编码的字符串
 */
public class ConstantUtf8Info extends ConstantInfo {
    private String str;
    private int length;

    @Override
    protected void readInfo(ClassReader reader) {
        length = reader.readUint16();
        byte[] bytes = reader.readBytes(length);
        str = new String(bytes);
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public int tag() {
        return CONSTANT_UTF8;
    }
}
