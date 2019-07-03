package com.gason.jvm.core.classfile.constantinfo.info;

import com.gason.jvm.core.classfile.ConstantPool;
import com.gason.jvm.core.classfile.constantinfo.ConstantInfo;
import com.gason.jvm.core.loader.ClassReader;

/**
 * @ClassName: ConstantClassInfo
 * @auther: zhongjias
 * @date: 2019/6/27 14:17
 * @description: 类或者接口的符号引用
 */
public class ConstantClassInfo extends ConstantInfo {
    private int nameIndex;
    private ConstantPool pool;

    public ConstantClassInfo(ConstantPool pool) {
        this.pool = pool;
    }

    @Override
    protected void readInfo(ClassReader reader) {
        this.nameIndex = reader.readUint16();
    }

    @Override
    public int tag() {
        return CONSTANT_CLASS;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return pool.getClassName(nameIndex);
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public ConstantPool getPool() {
        return pool;
    }

    public void setPool(ConstantPool pool) {
        this.pool = pool;
    }
}
