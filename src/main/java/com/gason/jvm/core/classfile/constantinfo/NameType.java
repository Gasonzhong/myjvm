package com.gason.jvm.core.classfile.constantinfo;

/**
 * @ClassName: NameType
 * @auther: zhongjias
 * @date: 2019/6/27 11:36
 * @description:
 */
public class NameType {
    private String name;
    private String type;

    public NameType() {
    }

    public NameType(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
