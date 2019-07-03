package com.gason.jvm.core.classfile;

import com.gason.jvm.core.classfile.attributes.AttributeInfo;
import com.gason.jvm.core.loader.ClassReader;

/**
 * @ClassName: MemberInfo
 * @auther: zhongjias
 * @date: 2019/6/26 09:58
 * @description:
 */
public class MemberInfo {
    private ConstantPool cp;
    private int accessFlags;
    private int nameIndex;
    private int descriptionIndex;
    private AttributeInfo[] attributes;

    public MemberInfo(ClassReader reader, ConstantPool constantPool) {
        this.cp = constantPool;
        this.accessFlags = reader.readUint16();
        this.nameIndex = reader.readUint16();
        this.descriptionIndex = reader.readUint16();
        this.attributes = AttributeInfo.readAttributes(reader, cp);
    }



    public String getName(){
        return cp.getUtf8(this.nameIndex);
    }

    public String getDescriptor(){
        return cp.getUtf8(this.descriptionIndex);
    }
    /**
     * readMembers（）读取字 段表或方法表
     *
     * @param reader
     * @param constantPool
     * @return
     */
    public static MemberInfo[] readMembers(ClassReader reader, ConstantPool constantPool) {
        int count = reader.readUint16();
        MemberInfo[] memberInfos = new MemberInfo[count];
        for (int i = 0; i < count; i++) {
            memberInfos[i] = readMember(reader, constantPool);
        }
        return memberInfos;
    }

    /**
     * 读取字段或方法数据
     *
     * @param reader
     * @param constantPool
     * @return
     */
    private static MemberInfo readMember(ClassReader reader, ConstantPool constantPool) {
        return new MemberInfo(reader, constantPool);
    }

    public ConstantPool getCp() {
        return cp;
    }

    public void setCp(ConstantPool cp) {
        this.cp = cp;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public void setAccessFlags(int accessFlags) {
        this.accessFlags = accessFlags;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public int getDescriptionIndex() {
        return descriptionIndex;
    }

    public void setDescriptionIndex(int descriptionIndex) {
        this.descriptionIndex = descriptionIndex;
    }

    public AttributeInfo[] getAttributes() {
        return attributes;
    }

    public void setAttributes(AttributeInfo[] attributes) {
        this.attributes = attributes;
    }
}
