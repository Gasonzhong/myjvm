package com.gason.jvm.core.classfile.constantinfo.info;

import com.gason.jvm.core.classfile.ConstantPool;
import com.gason.jvm.core.classfile.constantinfo.ConstantInfo;
import com.gason.jvm.core.loader.ClassReader;

/**
 * @ClassName: ConstantMethodrefInfo
 * @auther: zhongjias
 * @date: 2019/6/27 14:19
 * @description: 普通（非接口）方法符号引用
 */
public class ConstantMethodrefInfo extends ConstantMemberrefInfo {
    public ConstantMethodrefInfo(ConstantPool pool) {
        super(pool);
    }

    @Override
    public int tag() {
        return CONSTANT_METHODREF;
    }

}
