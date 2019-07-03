package com.gason.jvm.core.classfile.attributes.impl;

import com.gason.jvm.core.classfile.attributes.AttributeInfo;
import com.gason.jvm.core.loader.ClassReader;

/**
 * @ClassName:
 * @auther: zhongjias
 * @date: 2019/7/2 15:04
 * @description:
 */
public class UnparsedAttribute implements AttributeInfo {
    private String name;
    private int length;
    private byte[] info;

    public UnparsedAttribute(String attrName, int attrLen) {
        this.name = attrName;
        this.length = attrLen;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.info = reader.readBytes(this.length);
    }

    public byte[] info(){
        return this.info;
    }
}
