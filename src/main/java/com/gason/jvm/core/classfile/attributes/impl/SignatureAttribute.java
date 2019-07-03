package com.gason.jvm.core.classfile.attributes.impl;

import com.gason.jvm.core.classfile.ConstantPool;
import com.gason.jvm.core.classfile.attributes.AttributeInfo;
import com.gason.jvm.core.loader.ClassReader;

/**
 * @ClassName:
 * @auther: zhongjias
 * @date: 2019/7/2 15:04
 * @description:
 */
public class SignatureAttribute implements AttributeInfo {

    private ConstantPool constantPool;
    private int signatureIdx;

    public SignatureAttribute(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.signatureIdx = reader.readUint16();
    }

    public String signature(){
        return this.constantPool.getUtf8(this.signatureIdx);
    }

}
