package com.gason.jvm.core.classfile.constantinfo;

import com.gason.jvm.core.classfile.ConstantPool;
import com.gason.jvm.core.classfile.constantinfo.info.*;
import com.gason.jvm.core.loader.ClassReader;

/**
 * @ClassName: ConstantInfo
 * @auther: zhongjias
 * @date: 2019/6/27 09:08
 * @description:
 */
public abstract class ConstantInfo {
    private int tag;
    public final static int CONSTANT_CLASS = 7;
    public final static int CONSTANT_FIELDREF = 9;
    public final static int CONSTANT_METHODREF = 10;
    public final static int CONSTANT_INTERFACEMETHODREF = 11;
    public final static int CONSTANT_STRING = 8;
    public final static int CONSTANT_INTEGER = 3;
    public final static int CONSTANT_FLOAT = 4;
    public final static int CONSTANT_LONG = 5;
    public final static int CONSTANT_DOUBLE = 6;
    public final static int CONSTANT_NAMEANDTYPE = 12;
    public final static int CONSTANT_UTF8 = 1;
    public final static int CONSTANT_METHODHANDLE = 15;
    public final static int CONSTANT_METHODTYPE = 16;
    public final static int CONSTANT_INVOKEDYNAMIC = 18;

    public abstract int tag();

    /**
     * 读取常量信息
     *
     * @param reader
     */
    protected abstract void readInfo(ClassReader reader);

    /**
     * @param reader
     * @return
     */
    public static ConstantInfo readConstantInfo(ClassReader reader, ConstantPool pool) {
        int tag = reader.readU2ToInt();
        ConstantInfo info = newConstantInfo(tag, pool);
        info.readInfo(reader);
        return info;
    }

    /**
     * @param tag
     * @param pool
     * @return
     */
    public static ConstantInfo newConstantInfo(int tag, ConstantPool pool) {
        switch (tag) {
            case CONSTANT_INTEGER:
                return new ConstantIntegerInfo();
            case CONSTANT_FLOAT:
                return new ConstantFloatInfo();
            case CONSTANT_DOUBLE:
                return new ConstantDoubleInfo();
            case CONSTANT_UTF8:
                return new ConstantUtf8Info();
            case CONSTANT_STRING:
                return new ConstantStringInfo(pool);
            case CONSTANT_CLASS:
                return new ConstantClassInfo(pool);
            case CONSTANT_FIELDREF:
                return new ConstantFeildrefInfo(pool);
            case CONSTANT_METHODREF:
                return new ConstantMethodrefInfo(pool);
            case CONSTANT_INTERFACEMETHODREF:
                return new ConstantInterfaceMethodreffInfo(pool);
            case CONSTANT_NAMEANDTYPE:
                return new ConstantNameAndTypeInfo();
            case CONSTANT_METHODTYPE:
                return new ConstantMethodType();
            case CONSTANT_METHODHANDLE:
                return new ConstantMethodHandleInfo();
            case CONSTANT_INVOKEDYNAMIC:
                return new ConstantInvokeDynamicInfo();
            default:
                throw new ClassFormatError("ERROR:constant pool tag!");
        }
    }
}
