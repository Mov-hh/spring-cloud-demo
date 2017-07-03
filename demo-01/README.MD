
基本示例，包含以下组件：

1. eureka-server eureka服务
2. api-gateway API网关
3. base-service 基础服务
4. complex-service 复合服务

## 测试

1) 进入工程根目录

```
cd <Root of demo-01>
```

2) 启动`eureka服务`
```
java -jar eureka-server/target/eureka-server-01-1.0.0-SNAPSHOT.jar --spring.profiles.active=test &
```

验证：浏览器访问[eureka服务网址](http://192.168.9.80:18801/)
【注】`192.168.9.80`需调整为jar包所在服务器的ip

2）启动`基础服务`

```
java -jar base-service/impl/target/base-service-impl-01-1.0.0-SNAPSHOT.jar --spring.profiles.active=test &
```

