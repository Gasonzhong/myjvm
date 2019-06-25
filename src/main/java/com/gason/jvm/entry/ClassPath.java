package com.gason.jvm.entry;

import com.gason.jvm.entry.impl.WildcardEntry;
import com.gason.jvm.exception.EntryException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

/**
 * @ClassName: ClassPath
 * @auther: zhongjias
 * @date: 2019/6/24 19:24
 * @description:
 */
public class ClassPath {
    private Entry bootClassPath;
    private Entry extClassPath;
    private Entry userClassPath;

    public ClassPath(String jreOption, String cpOption) throws IOException, EntryException {
        this.parseBootAndExtClassPath(jreOption);
        this.parseUserClassPath(cpOption);
    }

    /**
     * 解析启动类路径
     *
     * @param jreOption
     * @return
     */
    public void parseBootAndExtClassPath(String jreOption) throws EntryException, IOException {
        String jreDir = getJreDir(jreOption);
        String jreLibPath = Paths.get(jreDir).resolve("lib").toString()+"/*";
        String jreExtPath = Paths.get(jreDir).resolve("lib").resolve("ext").toString()+"/*";
        this.bootClassPath = new WildcardEntry(jreLibPath);
        this.extClassPath = new WildcardEntry(jreExtPath);
    }

    /**
     * 优先使用用户输入的-Xjre选项作为jre目录。如果没有输入该
     * 选项，则在当前目录下寻找jre目录。如果找不到，尝试使用
     * JAVA_HOME环境变量
     *
     * @param jreOption
     * @return
     */
    public String getJreDir(String jreOption) throws EntryException {
        if (StringUtils.isNotBlank(jreOption) && exists(jreOption)) {
            return jreOption;
        }
        if (exists("./jre")) {
            return "./jre";
        }
        Map map = System.getenv();
        String JAVA_HOME = (String) map.get("JAVA_HOME");
        if (StringUtils.isNotBlank(JAVA_HOME)) {
            return Paths.get(JAVA_HOME).resolve("jre").toString();
        }
        throw new EntryException("Can not find jre folder!");
    }

    /**
     * 解析用户路径
     *
     * @param path
     */
    public void parseUserClassPath(String path) throws IOException {
        if (StringUtils.isEmpty(path)) {
            path = ".";
        }
        this.userClassPath = EntryFactory.newEntry(path);
    }

    public EntryObject readClass(String className) {
        className = className + ".class";
        EntryObject boot = this.bootClassPath.readClass(className);
        if (StringUtils.isBlank(boot.getError())) {
            return boot;
        }
        EntryObject ext = this.extClassPath.readClass(className);
        if (StringUtils.isBlank(ext.getError())) {
            return ext;
        }
        return userClassPath.readClass(className);
    }

    /**
     * @param path
     * @return
     */
    public boolean exists(String path) {
        File file = new File(path);
        return file.exists();
    }

    public Entry getBootClassPath() {
        return bootClassPath;
    }

    public void setBootClassPath(Entry bootClassPath) {
        this.bootClassPath = bootClassPath;
    }

    public Entry getExtClassPath() {
        return extClassPath;
    }

    public void setExtClassPath(Entry extClassPath) {
        this.extClassPath = extClassPath;
    }

    public Entry getUserClassPath() {
        return userClassPath;
    }

    public void setUserClassPath(Entry userClassPath) {
        this.userClassPath = userClassPath;
    }
}
