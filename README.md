# webpage-update-subscribe
检测特定网页更新，并将更新内容推送给用户。

# 说明
这是一个学习实践项目，还有一些不完善的地方，请多包涵。

数据库的建表文件 init_db.sql，放在了项目的根目录下，测试前请先创建好对应的表。

有关项目的组织方式，请参考这篇文章：http://www.codebelief.com/article/2017/10/maven-best-practice-to-manage-multi-module-project/

# 打包

模块之间的依赖是直接通过 Maven 的唯一标志 GAV（Group、Artifact、Version）来指定的，这是为了便于不同成员开发不同模块，而不用关心其它模块的具体代码。
  
因此，编译测试一个模块前，需要将它所依赖的其它模块作为依赖安装到机器上，可在依赖模块的目录下通过 `mvn install` 进行安装，或者用更加简便的方式，直接在根目录下执行 `mvn install`，将所有模块都安装到机器上。

最终在 webapp 目录下执行 `mvn clean package` 即可得到 war 文件。

# 配置

项目运行时，会自动在用户目录下创建 `.wus` 文件夹，用于存放配置文件，其中 db.properties 存放数据库配置，而 email.properties 存放邮件发送服务器的配置。

测试时，可先启动项目后关闭，然后对自动创建的配置文件进行修改，当项目再次运行时，就会读取这些配置信息。
