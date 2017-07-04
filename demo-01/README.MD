demo-01
=======

> Spring Cloud基础示例

包含以下组件：

1. eureka-server eureka服务
2. api-gateway API网关
3. base-service 基础服务
4. complex-service 复合服务

## 构建工程

```
cd <Root of demo-01>
mvn clean package -Dmaven.test.skip=true
```

## 测试

1) 进入工程根目录

```
cd <Root of demo-01>
```

2) 启动`eureka服务`
```
java -jar eureka-server/target/eureka-server-01-1.0.0-SNAPSHOT.jar --spring.profiles.active=test &
```

验证：浏览器访问[eureka服务网址](http://localhost:18000/)

3）启动`基础服务`

```
java -jar base-service/impl/target/base-service-impl-01-1.0.0-SNAPSHOT.jar --spring.profiles.active=test &
```

验证：

```
curl http://localhost:18100/base/hello?user=yyw
```


3）启动`复合服务`

```
java -jar complex-service/target/complex-service-01-1.0.0-SNAPSHOT.jar --spring.profiles.active=test &
```

验证：

```
curl http://localhost:18110/complex/hello?user=yyw
```

4）启动`API网关`

```
java -jar api-gateway/target/api-gateway-01-1.0.0-SNAPSHOT.jar --spring.profiles.active=test &
```

验证：

```
curl http://localhost:18010/base/hello?user=yyw
curl http://localhost:18010/complex/hello?user=yyw
```