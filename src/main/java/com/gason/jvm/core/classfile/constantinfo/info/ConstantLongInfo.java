package com.gason.jvm.core.classfile.constantinfo.info;

import com.gason.jvm.core.classfile.constantinfo.ConstantInfo;
import com.gason.jvm.core.loader.ClassReader;

/**
 * @ClassName: ConstantLongInfo
 * @auther: zhongjias
 * @date: 2019/6/27 14:15
 * @description: 使用8字节存储整数常量
 */
public class ConstantLongInfo extends ConstantInfo {
    private Long val;

    public Long getVal() {
        return val;
    }

    public void setVal(Long val) {
        this.val = val;
    }

    @Override
    protected void readInfo(ClassReader reader) {
        val = reader.readUint64TLong();
    }

    @Override
    public int tag() {
        return CONSTANT_LONG;
    }
}
