package com.gason.jvm.core.classfile.attributes.impl;

import com.gason.jvm.core.classfile.attributes.AttributeInfo;
import com.gason.jvm.core.loader.ClassReader;

/**
 * @ClassName:
 * @auther: zhongjias
 * @date: 2019/7/2 15:05
 * @description: Exceptions是变长属性，记录方法抛出的异常表
 */
public class ExceptionsAttribute implements AttributeInfo {
    private int[] exceptionIndexTable;

    @Override
    public void readInfo(ClassReader reader) {
        exceptionIndexTable = reader.readUint16s();
    }

    public int[] getExceptionIndexTable() {
        return exceptionIndexTable;
    }

    public void setExceptionIndexTable(int[] exceptionIndexTable) {
        this.exceptionIndexTable = exceptionIndexTable;
    }
}
