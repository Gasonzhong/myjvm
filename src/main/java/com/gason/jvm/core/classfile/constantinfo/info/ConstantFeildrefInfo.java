package com.gason.jvm.core.classfile.constantinfo.info;

import com.gason.jvm.core.classfile.ConstantPool;
import com.gason.jvm.core.classfile.constantinfo.ConstantInfo;
import com.gason.jvm.core.loader.ClassReader;

/**
 * @ClassName: ConstantFeildrefInfo
 * @auther: zhongjias
 * @date: 2019/6/27 14:18
 * @description: 字段符号引用
 */
public class ConstantFeildrefInfo extends ConstantMemberrefInfo {

    public ConstantFeildrefInfo( ConstantPool pool) {
        super(pool);
    }

    @Override
    public int tag() {
        return CONSTANT_FIELDREF;
    }

}