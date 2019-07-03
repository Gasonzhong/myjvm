package com.gason.jvm.core.classfile.attributes.impl;

import com.gason.jvm.core.classfile.attributes.AttributeInfo;
import com.gason.jvm.core.loader.ClassReader;

/**
 * @ClassName:
 * @auther: zhongjias
 * @date: 2019/7/2 15:04
 * @description:
 */
public class LocalVariableTypeTableAttribute implements AttributeInfo {
    private LocalVariableTypeTableEntry[] localVariableTypeTable;

    @Override
    public void readInfo(ClassReader reader) {
        int localVariableTypeTableLength = reader.readUint16();
        this.localVariableTypeTable = new LocalVariableTypeTableEntry[localVariableTypeTableLength];
        for (int i = 0; i < localVariableTypeTableLength; i++) {
            this.localVariableTypeTable[i] = new LocalVariableTypeTableEntry(reader.readUint16(), reader.readUint16(), reader.readUint16(), reader.readUint16(), reader.readUint16());
        }
    }

    static class LocalVariableTypeTableEntry {
        private int startPC;
        private int length;
        private int nameIndex;
        private int signatureIndex;
        private int index;

        public LocalVariableTypeTableEntry(int startPC, int length, int nameIndex, int signatureIndex, int index) {
            this.startPC = startPC;
            this.length = length;
            this.nameIndex = nameIndex;
            this.signatureIndex = signatureIndex;
            this.index = index;
        }
    }
}

