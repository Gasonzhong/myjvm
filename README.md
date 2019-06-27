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
### 常量池（ConstantPool）
1. 基本类型byte、short、char、int、long、float和double的描述符 是单个字母，分别对应B、S、C、I、J、F和D。注意，long的描述符是J
而不是L。 
2. 引用类型的描述符是L＋类的完全限定名＋分号。 
3.  数组类型的描述符是[＋数组元素类型描述符。 2）字段描述符就是字段类型的描述符。 3）方法描述符是（分号分隔的参数类型描述符）+返回值类型描 述符，其中void返回值由单个字母V表示。 更详细的介绍可以参考Java虚拟机规范4.3节