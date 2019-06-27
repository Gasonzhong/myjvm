package com.gason.jvm.core.classfile.constantinfo.info;

import com.gason.jvm.core.classfile.ConstantPool;
import com.gason.jvm.core.classfile.constantinfo.ConstantInfo;
import com.gason.jvm.core.loader.ClassReader;

/**
 * @ClassName: ConstantStringInfo
 * @auther: zhongjias
 * @date: 2019/6/27 14:17
 * @description: 表示java.lang.String字面量
 */
public class ConstantStringInfo extends ConstantInfo {
    private int strIndex;
    private ConstantPool pool;

    public ConstantStringInfo(ConstantPool pool) {
        this.pool = pool;
    }

    @Override
    public int tag() {
        return CONSTANT_STRING;
    }

    @Override
    protected void readInfo(ClassReader reader) {
        strIndex = reader.readU2ToInt();
    }

    /**
     * 按索引从常量池中查找字符串
     * @return
     */
    public String getString() {
        return pool.getUtf8(strIndex);
    }
}
