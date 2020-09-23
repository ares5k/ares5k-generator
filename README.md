# ares5k-generator
ares5k-generator一款基于web的Java代码生成工具

# **联系方式**
QQ: 16891544<br/>
邮箱: 16891544@qq.com

# **特性**
**1.使用简单方便, 下载后直接在项目根目录执行 mvn clean package, 然后把生成的jar放在公司服务器, 再然后在服务器上执行java -jar ares5k-generator-1.0-SNAPSHOT.jar 就可以使用了，端口号是1994**<br/>

**2.可灵活选择要生成的文件:** Controller Service Mapper Entity

**3.可灵活选择使用的插件:** Lombok Mybatis-Plus

**4.多种包划分形式可供选择:**<br/><br/>
(1) 以功能划分:
```
├── controller
│   ├── UserController.java
│   ├── OrderController.java
├── entity
│   ├── User.java
│   ├── Order.java

```
(2) 以模块划分:
```
├── user
│   ├── controller
│   ├──    ├── UserController.java
│   ├── entity
│   ├──    ├── User.java
├── order
│   ├── controller
│   ├──    ├── OrderController.java
│   ├── entity
│   ├──    ├── Order.java

```
(3) 先功能，后模块：
```
├── controller
│   ├── user
│   ├──    ├── UserController.java
│   ├── order
│   ├──    ├── OrderController.java
├── entity
│   ├── user
│   ├──    ├── User.java
│   ├── order
│   ├──    ├── Order.java

```
# 项目截图
填写数据源:<br/>
![](https://gitee.com/ares5k/resources/raw/master/images/ares5k-generator-images/ares5k-generator-datasource.jpg) <br/>
连接并获取所有表:<br/>
![](https://gitee.com/ares5k/resources/raw/master/images/ares5k-generator-images/ares5k-generator-connect.jpg) <br/>
个性化配置:<br/>
![](https://gitee.com/ares5k/resources/raw/master/images/ares5k-generator-images/ares5k-generator-generator.jpg) <br/>
下载自动生成的文件:<br/>
![](https://gitee.com/ares5k/resources/raw/master/images/ares5k-generator-images/ares5k-generator-download.jpg) <br/>

# 源码地址
- GitHub:https://github.com/ares5k/ares5k-generator
- 码云：https://gitee.com/ares5k/ares5k-generator

