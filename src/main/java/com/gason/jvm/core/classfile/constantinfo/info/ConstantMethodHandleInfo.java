package com.gason.jvm.core.classfile.constantinfo.info;

import com.gason.jvm.core.classfile.constantinfo.ConstantInfo;
import com.gason.jvm.core.loader.ClassReader;

/**
 * @ClassName: ConstantMethodHandleInfo
 * @auther: zhongjias
 * @date: 2019/6/27 14:48
 * @description:
 */
public class ConstantMethodHandleInfo extends ConstantInfo {
    private int referenceKind;
    private int referenceIndex;

    @Override
    public void readInfo(ClassReader reader) {
        this.referenceKind = reader.readUint8();
        this.referenceIndex = reader.readUint16();
    }

    @Override
    public int tag() {
        return CONSTANT_METHODHANDLE;
    }
}
