package com.gason.jvm.object;

import com.gason.jvm.entry.Entry;

import java.util.List;

/**
 * @ClassName: EntryObject
 * @auther: zhongjias
 * @date: 2019/6/24 14:07
 * @description:
 */
public class EntryObject {
    private byte[] readByte;
    private Entry entry;
    private String error;
    public EntryObject(byte[] readByte, Entry entry, String error) {
        this.readByte = readByte;
        this.entry = entry;
        this.error = error;
    }

    public byte[] getReadByte() {
        return readByte;
    }

    public void setReadByte(byte[] readByte) {
        this.readByte = readByte;
    }

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("EntryObject{");
        sb.append("readByte=");
        if (readByte == null) sb.append("null");
        else {
            sb.append('[');
            for (int i = 0; i < readByte.length; ++i)
                sb.append(i == 0 ? "" : ", ").append(readByte[i]);
            sb.append(']');
        }
        sb.append(", entry=").append(entry);
        sb.append(", error='").append(error).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
