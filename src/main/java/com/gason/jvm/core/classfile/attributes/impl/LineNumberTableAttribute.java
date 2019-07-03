package com.gason.jvm.core.classfile.attributes.impl;

import com.gason.jvm.core.classfile.attributes.AttributeInfo;
import com.gason.jvm.core.loader.ClassReader;

/**
 * @ClassName:
 * @auther: zhongjias
 * @date: 2019/7/2 15:05
 * @description: LineNumberTable属性表存放方法的行号信息， LocalVariableTable属性表中存放方法的局部变量信息。这两种属性 和前面介绍的SourceFile属性都属于调试信息，都不是运行时必需 的。在使用javac编译器编译Java程序时，默认会在class文件中生成 这些信息
 */
public class LineNumberTableAttribute implements AttributeInfo {
    private LineNumberTableEntry[] lineNumberTable;

    @Override
    public void readInfo(ClassReader reader) {
        int lineNumberTableLength = reader.readUint16();
        this.lineNumberTable = new LineNumberTableEntry[lineNumberTableLength];
        for (int i = 0; i < lineNumberTableLength; i++) {
            lineNumberTable[i] = new LineNumberTableEntry(reader.readUint16(), reader.readUint16());
        }
    }

    public int getLineNumber(int pc) {
        for (int i = this.lineNumberTable.length - 1; i >= 0; i--) {
            LineNumberTableEntry entry = this.lineNumberTable[i];
            if (pc >= entry.startPC){
                return entry.lineNumber;
            }
        }
        return -1;
    }

    static class LineNumberTableEntry {
        private int startPC;
        private int lineNumber;

        LineNumberTableEntry(int startPC, int lineNumber) {
            this.startPC = startPC;
            this.lineNumber = lineNumber;
        }

    }
}
