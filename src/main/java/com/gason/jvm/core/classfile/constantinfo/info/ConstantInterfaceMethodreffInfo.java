package com.gason.jvm.core.classfile.constantinfo.info;

import com.gason.jvm.core.classfile.ConstantPool;
import com.gason.jvm.core.classfile.constantinfo.ConstantInfo;
import com.gason.jvm.core.loader.ClassReader;

/**
 * @ClassName: ConstantInterfaceMethodreffInfo
 * @auther: zhongjias
 * @date: 2019/6/27 14:20
 * @description: 接口方法符号引用
 */
public class ConstantInterfaceMethodreffInfo extends ConstantMemberrefInfo {
    public ConstantInterfaceMethodreffInfo(ConstantPool pool) {
        super(pool);
    }

    @Override
    public int tag() {
        return CONSTANT_INTERFACEMETHODREF;
    }
}