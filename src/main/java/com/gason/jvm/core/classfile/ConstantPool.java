package com.gason.jvm.core.classfile;

import com.gason.jvm.core.classfile.constantinfo.ConstantInfo;
import com.gason.jvm.core.classfile.constantinfo.NameType;
import com.gason.jvm.core.classfile.constantinfo.info.ConstantClassInfo;
import com.gason.jvm.core.classfile.constantinfo.info.ConstantNameAndTypeInfo;
import com.gason.jvm.core.classfile.constantinfo.info.ConstantUtf8Info;
import com.gason.jvm.core.loader.ClassReader;

/**
 * @ClassName: ConstantPool
 * @auther: zhongjias
 * @date: 2019/6/26 09:58
 * @description:
 */
public class ConstantPool {
    private ConstantInfo[] constantInfos;
    private  int count;
    public NameType getNameAndType(int index) {
        ConstantNameAndTypeInfo nameAndTypeInfo=(ConstantNameAndTypeInfo)this.constantInfos[index];
        return new NameType(this.getUtf8(nameAndTypeInfo.getNameIndex()),this.getUtf8(nameAndTypeInfo.getDescriptorIndex()));
    }

    public String getClassName(int index) {
        ConstantClassInfo classInfo = (ConstantClassInfo) this.constantInfos[index];
        return this.getUtf8(classInfo.getNameIndex());

    }

    public String getUtf8(int index) {
        ConstantUtf8Info utf8Info = (ConstantUtf8Info) this.constantInfos[index];
        return utf8Info == null ? "" : utf8Info.getStr();
    }

    public ConstantPool (ClassReader reader) {
        count = reader.readUint16();
        constantInfos = new ConstantInfo[count];
        for (int i = 1; i < count; i++) {
            constantInfos[i] = ConstantInfo.readConstantInfo(reader, this);
            System.out.println( i+":"+constantInfos[i].tag());
            switch (constantInfos[i].tag()) {
                case ConstantInfo.CONSTANT_DOUBLE:
                case ConstantInfo.CONSTANT_LONG:
                    i++;
                    break;
                default:
                    break;
            }
        }
    }

    public ConstantInfo[] getConstantInfos() {
        return constantInfos;
    }

    public void setConstantInfos(ConstantInfo[] constantInfos) {
        this.constantInfos = constantInfos;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
