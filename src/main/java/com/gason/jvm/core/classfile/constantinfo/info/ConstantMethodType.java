package com.gason.jvm.core.classfile.constantinfo.info;

import com.gason.jvm.core.classfile.constantinfo.ConstantInfo;
import com.gason.jvm.core.loader.ClassReader;

/**
 * @ClassName: ConstantMethodType
 * @auther: zhongjias
 * @date: 2019/6/27 14:48
 * @description:
 */
public class ConstantMethodType extends ConstantInfo {
    private int descriptorIdx;

    @Override
    public void readInfo(ClassReader reader) {
        this.descriptorIdx = reader.readUint16();
    }

    @Override
    public int tag() {
        return CONSTANT_METHODTYPE;
    }
}
