package com.gason.jvm.core.classfile.attributes.impl;

import com.gason.jvm.core.classfile.ConstantPool;
import com.gason.jvm.core.classfile.attributes.AttributeInfo;
import com.gason.jvm.core.classfile.constantinfo.NameType;
import com.gason.jvm.core.loader.ClassReader;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName:
 * @auther: zhongjias
 * @date: 2019/7/2 15:04
 * @description:
 */
public class EnclosingMethodAttribute implements AttributeInfo {
    private ConstantPool constantPool;
    private int classIdx;
    private int methodIdx;


    public EnclosingMethodAttribute(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.classIdx = reader.readUint16();
        this.methodIdx = reader.readUint16();
    }

    public String className() {
        return this.constantPool.getClassName(this.classIdx);
    }

    public NameType methodNameAndDescriptor() {
        if (this.methodIdx <= 0) {
            return new NameType();
        }
        return this.constantPool.getNameAndType(this.methodIdx);
    }

}
