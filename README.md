# 项目简介

这是一个clojure web的demo项目，主要用来学习一下clojure编程。

## 环境

- JDK1.8
- Leiningen 1.8.1
- clojure 1.8.0
- mysql 5.7

## 使用

运行数据库脚本:
    
    mysql>use `database`;
    
    mysql>source resources/t_user.sql 
    
修改`config.edn`中数据库相关配置

在项目根目录输入命令：

    $ lein ring server
此命令将启动ring内置的jetty服务器，并自动打开浏览器，定位到`http://localhost:3000/`</p>
当然你也可以在repl中启动,在根目录下运行:

    $ lein repl
    
    app.core=>(use 'app.core :reload)
    
    app.core=>(-main)
这会启动一个`9000`端口的jetty的服务器,此端口可配置，修改`config.edn`中的`port`项

## 打包

像maven一样Leiningen也可以把所有源码和依赖编译并打包成一个独立的jar包,在项目根目录下运行:

    $ lein uberjar

打包后jar包在`target/uberjar/`目录下,通过java命令运行:

     $ java -jar demo-0.1.0-standalone.jar [args]
## Examples

    curl http://localhost:9000/api/users


## License

Copyright © 2017 fantasyding

Distributed under the Eclipse Public License either version 1.0 or any later version.
