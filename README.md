#  [![logo](https://s1.ax1x.com/2022/12/17/zHLNuQ.png)](https://imgse.com/i/zHLNuQ) NiceCSP  ![framework:Spring boot](https://img.shields.io/badge/framework-spring%20boot-brightgreen) ![framework: vue.js (shields.io)](https://img.shields.io/badge/framework-vue.js-brightgreen) ![build: Gradle (shields.io)](https://img.shields.io/badge/build-Gradle-blue) ![language: Kotlin (shields.io)](https://img.shields.io/badge/language-Kotlin-blueviolet)

------

这是一个CCF/CSP预报名辅助系统，可提供学生预报名，教师导入成绩等功能

### 从源码构建

------

从源码构建可能需要以下依赖项：

- [IntelliJ IDEA (jetbrains.com)](https://www.jetbrains.com/zh-cn/idea/)
- [OpenJDK  Microsoft 构建版本](https://learn.microsoft.com/zh-cn/java/openjdk/download)
- [Node.js (nodejs.org)](https://nodejs.org/en/)
- [Vue.js  (vuejs.org)](https://cn.vuejs.org/)
- [Spring Boot](https://spring.io/)

后端构建完成之后，`.war` 包位于`./server/build/libs`目录，`dist`目录位于`./vue`目录之下

构建完成之后，部署需要用到以下依赖项

- [MySQL](https://www.mysql.com/)
- [Nginx](https://nginx.org/en/)

安装依赖项，并将`config`目录下`init.sql`导入数据库，将`war`包复制到目标位置，将项目中`config`
文件夹复制到同级目录，修改`application.yml`中的`spring.datasource.druid.url`为

```
jdbc:mysql://数据库地址:数据库端口/chia?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true
```

修改`spring.datasource.druid.username`与`spring.datasource.druid.password`为数据库用户名与密码。

之后，使用以下命令启动后端服务器

```bash
java -jar chia-1.0.0.war --in=${初始化教师姓名} --ip=${初始化教师密码} --ie=${初始化教师邮箱} --sms=${系统邮件服务器地址} --smu=${系统邮件服务器用户名} --sma=${系统邮件服务器授权码}  
```

将`dist`目录下所有文件复制到`Nginx`的`html`文件夹中，修改`nginx.conf`中的`http.server`项，添加以下内容

```clike
location / {
            root   html/;
            index  index.html index.htm;
        }
        location /api {
            rewrite  ^.+api/?(.*)$ /$1 break;
            proxy_pass  http://127.0.0.1:8086;
            proxy_redirect off;
            proxy_set_header Host $host;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header X-Forwarded-For 	
            $proxy_add_x_forwarded_for;
      }
```

重启`Nginx`后即可访问

### 使用镜像

------

使用Docker镜像可快速启用服务，需要以下依赖项，<font color="#FF6347">注意：在Windows平台部署需要WSL支持</font>

- [Docker](https://www.docker.com/)
- [Docker Compose](https://github.com/docker/compose)

首先，加载镜像

```bash
sudo docker load -i chia.tar
```

修改`server.env`中的值

```apl
IN=初始教师姓名
IP=初始教师密码
IE=初始教师邮箱
SMS=系统邮件服务器地址 
SMU=系统邮件服务器用户名
SMA=系统邮件服务器授权码  
```

之后，运行

```bash
sudo docker compose up
```

即可启动服务，不过在此之前，请确认系统80,8086,3306端口空闲

