package com.gason.jvm.core.classfile.constantinfo.info;

import com.gason.jvm.core.classfile.constantinfo.ConstantInfo;
import com.gason.jvm.core.loader.ClassReader;

/**
 * @ClassName: ConstantFloatInfo
 * @auther: zhongjias
 * @date: 2019/6/27 14:14
 * @description: 节存储IEEE754单精度浮点数 常量
 */
public class ConstantFloatInfo extends ConstantInfo {
    private float val;

    @Override
    protected void readInfo(ClassReader reader) {
        val = reader.readU4ToFloat();
    }

    public float getVal() {
        return val;
    }

    public void setVal(float val) {
        this.val = val;
    }

    @Override
    public int tag() {
        return CONSTANT_FLOAT;
    }

}
