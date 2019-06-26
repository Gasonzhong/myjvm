package com.gason.jvm.core.classfile;

import com.gason.jvm.core.loader.ClassReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName: ClassFile
 * @auther: zhongjias
 * @date: 2019/6/26 09:33
 * @description:
 */
public class ClassFile {
    private final static String magicVersion = "cafebabe";
    private int minorVersion;
    private int majorVersion;
    private int constantCount;
    private ConstantPool constantPool;
    private int accessFlags;
    private int thisClass;
    private int superClass;
    private int interfacesCount;
    private int[] interfaces;
    private MemberInfo[] feilds;
    private MemberInfo[] methods;
    private AttributeInfo[] attributes;

    public ClassFile(byte[] classData) {
        ClassReader classReader = new ClassReader(classData);
        readAndCheckMagic(classReader);
        readAndCheckVersion(classReader);
        readConstantPool(classReader);
        this.accessFlags = classReader.readU2ToInt();
        this.thisClass = classReader.readU2ToInt();
        this.superClass = classReader.readU2ToInt();
        this.interfacesCount = classReader.readU2ToInt();
        this.interfaces = classReader.readUInt16s();
        this.feilds = readMembers(classReader, this.constantPool);
        this.methods = readMembers(classReader, this.constantPool);
        this.attributes = readAttributes(classReader, this.constantPool);
    }


    private MemberInfo[] readMembers(ClassReader classReader, ConstantPool constantPool) {
        return null;
    }

    private AttributeInfo[] readAttributes(ClassReader classReader, ConstantPool constantPool) {
        return null;
    }

    /**
     * 检查和读取magic
     *
     * @param classReader
     */
    private void readAndCheckMagic(ClassReader classReader) {
        String magic = classReader.readU4ToHexStr().toLowerCase();
        if (!magicVersion.equals(magic)) {
            throw new ClassFormatError("magic is wrong!!!");
        }
    }

    /**
     * 检查版本号
     *
     * @param classReader
     */
    private void readAndCheckVersion(ClassReader classReader) {
        this.minorVersion = classReader.readU2ToInt();
        this.majorVersion = classReader.readU2ToInt();
        switch (this.majorVersion) {
            case 45:
                return;
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
                if (this.minorVersion == 0) {
                    return;
                }
            default:
                break;
        }
        throw new UnsupportedClassVersionError("version is wrong!");
    }

    private void readConstantPool(ClassReader classReader) {
    }

    /**
     * 从常量池查找类名
     *
     * @return
     */
    public String getClassName() {
        return this.constantPool.getClassName(this.thisClass);
    }

    /**
     * 获取父类名
     *
     * @return
     */
    public String getSuperClassName() {
        if (this.superClass > 0) {
            return this.constantPool.getClassName(this.superClass);
        }
        return "";
    }

    /**
     * 获取接口名列表
     *
     * @return
     */
    public String[] getInfacesName() {
        List<String> names = new LinkedList<>();
        for (int inter : this.interfaces) {
            names.add(this.constantPool.getClassName(inter));
        }
        String[] strings = new String[names.size()];
        return names.toArray(strings);
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public void setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
    }

    public int getConstantCount() {
        return constantCount;
    }

    public void setConstantCount(int constantCount) {
        this.constantCount = constantCount;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public void setAccessFlags(int accessFlags) {
        this.accessFlags = accessFlags;
    }

    public int getThisClass() {
        return thisClass;
    }

    public void setThisClass(int thisClass) {
        this.thisClass = thisClass;
    }

    public int getSuperClass() {
        return superClass;
    }

    public void setSuperClass(int superClass) {
        this.superClass = superClass;
    }

    public int getInterfacesCount() {
        return interfacesCount;
    }

    public void setInterfacesCount(int interfacesCount) {
        this.interfacesCount = interfacesCount;
    }

    public int[] getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(int[] interfaces) {
        this.interfaces = interfaces;
    }


    public MemberInfo[] getFeilds() {
        return feilds;
    }

    public void setFeilds(MemberInfo[] feilds) {
        this.feilds = feilds;
    }

    public MemberInfo[] getMethods() {
        return methods;
    }

    public void setMethods(MemberInfo[] methods) {
        this.methods = methods;
    }


    public AttributeInfo[] getAttributes() {
        return attributes;
    }

    public void setAttributes(AttributeInfo[] attributes) {
        this.attributes = attributes;
    }

}
