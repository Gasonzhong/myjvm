package com.gason.jvm.entry;

import java.io.IOException;

/**
 * @InterfaceName: Entry
 * @auther: zhongjias
 * @date: 2019/6/24 13:54
 * @description:
 */
public interface Entry {
    public  final static String pathListSeparator=",";
    /**
     *
     * @param className
     * @return 负责寻找和加载class文件；
     */
    EntryObject readClass(String className) throws IOException;

}
