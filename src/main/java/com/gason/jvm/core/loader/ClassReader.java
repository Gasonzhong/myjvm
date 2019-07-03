package com.gason.jvm.core.loader;

import java.io.InputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName: ClassReader
 * @auther: zhongjias
 * @date: 2019/6/26 14:28
 * @description:
 * ClassFile中的字段类型有u1、u2、u4，
 * 分别表示1个字节，2个字节和4个字节的无符号整数。
 * java中short、int、long分别为2、4、8个字节的有符号整数，
 * 去掉符号位，刚好可以用来表示u1、u2、u4
 */
public class ClassReader {
    private byte[] data;

    public ClassReader(byte[] data) {
        this.data = data;
    }

    //u1
    public int readUint8() {
        byte[] val = readByte(1);
        return byte2int(val);
    }

    //u2
    public int readUint16() {
        byte[] val = readByte(2);
        return byte2int(val);
    }

    //u4
    public long readUint32() {
        byte[] val = readByte(4);
        String str_hex = new BigInteger(1, val).toString(16);
        return Long.parseLong(str_hex, 16);
    }

    public int readUint32TInteger(){
        byte[] val = readByte(4);
        return new BigInteger(1, val).intValue();
    }

    public float readUint64TFloat() {
        byte[] val = readByte(8);
        return new BigInteger(1, val).floatValue();
    }

    public long readUint64TLong() {
        byte[] val = readByte(8);
        return new BigInteger(1, val).longValue();
    }

    public double readUint64TDouble() {
        byte[] val = readByte(8);
        return new BigInteger(1, val).doubleValue();
    }

    public int[] readUint16s() {
        int n = this.readUint16();
        int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = this.readUint16();
        }
        return s;
    }

    public byte[] readBytes(int n) {
        return readByte(n);
    }

    private byte[] readByte(int length) {
        byte[] copy = new byte[length];
        System.arraycopy(data, 0, copy, 0, length);
        System.arraycopy(data, length, data, 0, data.length - length);
        return copy;
    }

    private int byte2int(byte[] val) {
        String str_hex = new BigInteger(1, val).toString(16);
        return Integer.parseInt(str_hex, 16);
    }
}
