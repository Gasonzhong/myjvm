package com.gason.jvm.core.classfile.constantinfo.info;

import com.gason.jvm.core.classfile.constantinfo.ConstantInfo;
import com.gason.jvm.core.loader.ClassReader;

/**
 * @ClassName: ConstantDoubleInfo
 * @auther: zhongjias
 * @date: 2019/6/27 14:17
 * @description: 使用8字节存 储IEEE754双精度浮点数
 */
public class ConstantDoubleInfo extends ConstantInfo {
    private double val;

    @Override
    protected void readInfo(ClassReader reader) {
        val = reader.readUint64TDouble();
    }

    public Double getVal() {
        return val;
    }

    public void setVal(Double val) {
        this.val = val;
    }

    public void setVal(double val) {
        this.val = val;
    }

    @Override
    public int tag() {
        return CONSTANT_DOUBLE;
    }
}
