package com.gason.jvm.entry;

import com.gason.jvm.entry.impl.CompositeEntry;
import com.gason.jvm.entry.impl.DirEntry;
import com.gason.jvm.entry.impl.WildcardEntry;
import com.gason.jvm.entry.impl.Zip_Entry;
import com.gason.jvm.exception.EntryException;

import java.io.IOException;

/**
 * @ClassName: EntryFactory
 * @auther: zhongjias
 * @date: 2019/6/24 14:11
 * @description:
 */
public class EntryFactory {

    public static Entry newEntry(String path) throws  IOException {
        if (path.contains(Entry.pathListSeparator)) {
              return new CompositeEntry(path);
        }
        if (path.contains("*")) {
            return new WildcardEntry(path);
        }
        if (path.endsWith(".zip")
                || path.endsWith(".ZIP")
                || path.endsWith(".jar")
                || path.endsWith(".JAR")) {
            return new Zip_Entry(path);
        }
        return new DirEntry(path);
    }
}