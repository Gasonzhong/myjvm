package com.gason.jvm.core.classfile.constantinfo.info;

import com.gason.jvm.core.classfile.ConstantPool;
import com.gason.jvm.core.classfile.constantinfo.ConstantInfo;
import com.gason.jvm.core.classfile.constantinfo.NameType;
import com.gason.jvm.core.loader.ClassReader;

/**
 * @ClassName: ConstantMemberrefInfo
 * @auther: zhongjias
 * @date: 2019/6/27 15:50
 * @description:
 */
public class ConstantMemberrefInfo extends ConstantInfo {
    protected int classIndex;
    private int nameAndTypeIndex;
    protected ConstantPool pool;

    public ConstantMemberrefInfo(ConstantPool pool) {
        this.pool = pool;
    }

    @Override
    protected void readInfo(ClassReader reader) {
        classIndex = reader.readU2ToInt();
        nameAndTypeIndex = reader.readU2ToInt();
    }

    @Override
    public int tag() {
        return 0;
    }

    protected String getClassName() {
        return pool.getClassName(classIndex);
    }

    protected NameType getNameAndType() {
        return pool.getNameAndType(nameAndTypeIndex);
    }
}
