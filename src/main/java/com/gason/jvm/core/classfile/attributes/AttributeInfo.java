package com.gason.jvm.core.classfile.attributes;

import com.gason.jvm.core.classfile.ConstantPool;
import com.gason.jvm.core.classfile.attributes.impl.*;
import com.gason.jvm.core.loader.ClassReader;

/**
 * @ClassName: AttributeInfo
 * @auther: zhongjias
 * @date: 2019/6/26 09:58
 * @description:  Java虚拟机规范预定义了23种属性
 */
public interface AttributeInfo {

    void readInfo(ClassReader reader);

    /**
     * 读取属性表
     * @param reader
     * @param constantPool
     * @return
     */
    static AttributeInfo[] readAttributes(ClassReader reader, ConstantPool constantPool) {
        int attributesCount = reader.readUint16();
        AttributeInfo[] attributes = new AttributeInfo[attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            attributes[i] = readAttribute(reader, constantPool);
        }
        return attributes;
    }

    /**
     * 读取单个属性
     * @param reader
     * @param constantPool
     * @return
     */
    static AttributeInfo readAttribute(ClassReader reader, ConstantPool constantPool) {
        int attrNameIdx = reader.readUint16();
        String attrName = constantPool.getUtf8(attrNameIdx);
        int attrLen = reader.readUint32TInteger();
        AttributeInfo attrInfo = newAttributeInfo(attrName, attrLen, constantPool);
        attrInfo.readInfo(reader);
        return attrInfo;
    }

    static AttributeInfo newAttributeInfo(String attrName, int attrLen, ConstantPool constantPool) {
        switch (attrName) {
            case "BootstrapMethods":
                return new BootstrapMethodsAttribute();
            case "Code":
                return new CodeAttribute(constantPool);
            case "ConstantValue":
                return new ConstantValueAttribute();
            case "Deprecated":
                return new DeprecatedAttribute();
            case "EnclosingMethod":
                return new EnclosingMethodAttribute(constantPool);
            case "Exceptions":
                return new ExceptionsAttribute();
            case "InnerClasses":
                return new InnerClassesAttribute();
            case "LineNumberTable":
                return new LineNumberTableAttribute();
            case "LocalVariableTable":
                return new LocalVariableTableAttribute();
            case "LocalVariableTypeTable":
                return new LocalVariableTypeTableAttribute();
            case "Signature":
                return new SignatureAttribute(constantPool);
            case "SourceFile":
                return new SourceFileAttribute(constantPool);
            case "Synthetic":
                return new SyntheticAttribute();
            default:
                return new UnparsedAttribute(attrName, attrLen);
            // case "SourceDebugExtension":
            // case "StackMapTable":
            // case "MethodParameters":
            // case "RuntimeInvisibleAnnotations":
            // case "RuntimeInvisibleParameterAnnotations":
            // case "RuntimeInvisibleTypeAnnotations":
            // case "RuntimeVisibleAnnotations":
            // case "RuntimeVisibleParameterAnnotations":
            // case "RuntimeVisibleTypeAnnotations":
        }

    }
}
