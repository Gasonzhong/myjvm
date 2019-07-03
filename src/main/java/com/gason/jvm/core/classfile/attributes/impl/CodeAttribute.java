package com.gason.jvm.core.classfile.attributes.impl;

import com.gason.jvm.core.classfile.ConstantPool;
import com.gason.jvm.core.classfile.attributes.AttributeInfo;
import com.gason.jvm.core.loader.ClassReader;

/**
 * @ClassName:
 * @auther: zhongjias
 * @date: 2019/7/2 15:03
 * @description: Code是变长属性，只存在于method_info结构中。Code属性中存 放字节码等方法相关信息
 */
public class CodeAttribute implements AttributeInfo {
    private ConstantPool pool;
    private Integer maxStack;
    private Integer maxLocals;
    private byte[] code;
    private ExceptionTableEntry[] exceptionTable;
    private AttributeInfo[] attributes;

    public CodeAttribute(ConstantPool constantPool) {
        this.pool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.maxStack = reader.readUint16();
        this.maxLocals = reader.readUint16();
        int codeLength = reader.readUint32TInteger();
        this.code = reader.readBytes(codeLength);
        this.exceptionTable = readExceptionTable(reader);
        this.attributes = AttributeInfo.readAttributes(reader, pool);
    }

    private ExceptionTableEntry[] readExceptionTable(ClassReader reader) {
        Integer length = reader.readUint16();
        ExceptionTableEntry[] exceptionTable = new ExceptionTableEntry[length];
        for (int i = 0; i < length; i++) {
            ExceptionTableEntry entry = new ExceptionTableEntry();
            entry.setStartPc(reader.readUint16());
            entry.setEndPc(reader.readUint16());
            entry.setHandlePc(reader.readUint16());
            entry.setCatchType(reader.readUint16());
            exceptionTable[i] = entry;
        }
        return exceptionTable;
    }


    private class ExceptionTableEntry {
        private Integer startPc;
        private Integer endPc;
        private Integer handlePc;
        private Integer catchType;

        public Integer getStartPc() {
            return startPc;
        }

        public void setStartPc(Integer startPc) {
            this.startPc = startPc;
        }

        public Integer getEndPc() {
            return endPc;
        }

        public void setEndPc(Integer endPc) {
            this.endPc = endPc;
        }

        public Integer getHandlePc() {
            return handlePc;
        }

        public void setHandlePc(Integer handlePc) {
            this.handlePc = handlePc;
        }

        public Integer getCatchType() {
            return catchType;
        }

        public void setCatchType(Integer catchType) {
            this.catchType = catchType;
        }
    }
}
