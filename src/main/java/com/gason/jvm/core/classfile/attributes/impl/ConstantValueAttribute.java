package com.gason.jvm.core.classfile.attributes.impl;

import com.gason.jvm.core.classfile.attributes.AttributeInfo;
import com.gason.jvm.core.loader.ClassReader;

/**
 * @ClassName:
 * @auther: zhongjias
 * @date: 2019/7/2 15:04
 * @description:
 */
public class ConstantValueAttribute implements AttributeInfo {
    private Integer constantValueIndex;
    @Override
    public void readInfo(ClassReader reader) {
        this.constantValueIndex=reader.readUint16();
    }

    public Integer getConstantValueIndex() {
        return constantValueIndex;
    }

    public void setConstantValueIndex(Integer constantValueIndex) {
        this.constantValueIndex = constantValueIndex;
    }

}
