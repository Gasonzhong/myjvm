package com.gason.jvm.core.classfile.constantinfo.info;

import com.gason.jvm.core.classfile.constantinfo.ConstantInfo;
import com.gason.jvm.core.loader.ClassReader;

/**
 * @ClassName: ConstantInvokeDynamicInfo
 * @auther: zhongjias
 * @date: 2019/6/27 14:49
 * @description:
 */
public class ConstantInvokeDynamicInfo extends ConstantInfo {
    private int bootstrapMethodAttrIdx;
    private int nameAndTypeIdx;

    @Override
    public void readInfo(ClassReader reader) {
        this.bootstrapMethodAttrIdx = reader.readUint16();
        this.nameAndTypeIdx = reader.readUint16();
    }

    @Override
    public int tag() {
        return CONSTANT_INVOKEDYNAMIC;
    }
}
