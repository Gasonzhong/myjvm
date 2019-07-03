package com.gason.jvm.core.classfile.attributes.impl;

import com.gason.jvm.core.classfile.attributes.AttributeInfo;
import com.gason.jvm.core.loader.ClassReader;

/**
 * @ClassName:
 * @auther: zhongjias
 * @date: 2019/7/2 15:03
 * @description:
 */
public class BootstrapMethodsAttribute implements AttributeInfo {
    BootstrapMethod[] bootstrapMethods;

    @Override
    public void readInfo(ClassReader reader) {
        int bootstrapMethodNum = reader.readUint16();
        bootstrapMethods = new BootstrapMethod[bootstrapMethodNum];
        for (int i = 0; i < bootstrapMethodNum; i++) {
            bootstrapMethods[i] = new BootstrapMethod(reader.readUint16(), reader.readUint16s());
        }
    }

    static class BootstrapMethod {
        int bootstrapMethodRef;
        int[] bootstrapArguments;

        BootstrapMethod(int bootstrapMethodRef, int[] bootstrapArguments) {
            this.bootstrapMethodRef = bootstrapMethodRef;
            this.bootstrapArguments = bootstrapArguments;
        }
    }
}
