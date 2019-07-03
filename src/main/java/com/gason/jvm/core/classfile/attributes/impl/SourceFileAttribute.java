package com.gason.jvm.core.classfile.attributes.impl;

import com.gason.jvm.core.classfile.ConstantPool;
import com.gason.jvm.core.classfile.attributes.AttributeInfo;
import com.gason.jvm.core.loader.ClassReader;

/**
 * @ClassName:
 * @auther: zhongjias
 * @date: 2019/7/2 15:04
 * @description: SourceFile是可选定长属性，只会出现在ClassFile结构中，用于 指出源文件名
 */
public class SourceFileAttribute implements AttributeInfo {
    private Integer sourceFileIndex;
    private ConstantPool pool;

    public SourceFileAttribute(ConstantPool constantPool) {
        this.pool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.sourceFileIndex = reader.readUint16();
    }

    public String FileName() {
        return pool.getUtf8(this.sourceFileIndex);
    }
}
