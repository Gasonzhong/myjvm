package com.gason.jvm.core.classfile.constantinfo.info;

import com.gason.jvm.core.classfile.constantinfo.ConstantInfo;
import com.gason.jvm.core.loader.ClassReader;

/**
 * @ClassName: ConstantNameAndTypeInfo
 * @auther: zhongjias
 * @date: 2019/6/27 14:18
 * @description: 给出字段或方法的名称和描述
 */
public class ConstantNameAndTypeInfo extends ConstantInfo {
    private int nameIndex;
    private int descriptorIndex;

    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public void setDescriptorIndex(int descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }

    @Override
    public void readInfo(ClassReader reader) {
        nameIndex = reader.readUint16();
        descriptorIndex = reader.readUint16();
    }

    @Override
    public int tag() {
        return CONSTANT_NAMEANDTYPE;
    }
}
