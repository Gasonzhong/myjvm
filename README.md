# myjvm
java实现jvm(参考《自己动手写java虚拟机》),书是go实现，本项目用java实现
## 命令行工具
* 使用commons-cli
## 搜索class文件
* Entry 入口文件接口
* 主要方法readClass在DirEntry,ZipEntry等四个实现类中
* 测试命令  -X D:\env\jdk1.8\jre -cl java.lang.Class 
## 解析class文件
### 结构
* 魔数magic
* 版本号-minorVersion：次版本，majorVersion：主版本
* 常量池 ConstantPool constantPool
* 类访问标志 accessFlags
* 类索引：thisClass
* 超类索引：superClass
* 接口索引表：interfaces
* 字段，方法表：MemberInfo[] fields,methods;
* 属性 AttributeInfo attributes
 
