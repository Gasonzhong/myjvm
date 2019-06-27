package com.gason.jvm.core.classfile.constantinfo.info;

import com.gason.jvm.core.classfile.constantinfo.ConstantInfo;
import com.gason.jvm.core.loader.ClassReader;

/**
 * @ClassName: ConstantIntegerInfo
 * @auther: zhongjias
 * @date: 2019/6/27 14:14
 * @description: 存储整数常量
 */
public class ConstantIntegerInfo extends ConstantInfo {
    private int val;

    @Override
    protected void readInfo(ClassReader reader) {
        val = reader.readUint32TInteger();
    }

    public long getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    @Override
    public int tag() {
        return CONSTANT_INTEGER;
    }
}
