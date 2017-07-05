> Spring Cloud入门示例 - 02

| 目录 | 描述 |
| eureka-server | Eureka服务注册中心 |
| base-service | 基础服务 |
| base-service/interface | 基础服务接口 |
| base-service/impl | 基础服务实现 |
| complex-service | 复合服务(依赖基础服务) |
| turbine-server | Turbine服务 |

## 1. 构建工程

```
cd <Root of demo-02>
mvn clean package -Dmaven.test.skip=true
```

## 2. 测试验证

1) 进入工程根目录

```
cd <Root of demo-02>
```

2) 启动`eureka服务`
```
java -jar eureka-server/target/eureka-server-1.0.0-SNAPSHOT.jar
```

验证：浏览器访问[eureka服务网址](http://localhost:18000/)

3）启动`基础服务`

```
java -jar base-service/impl/target/base-service-impl-1.0.0-SNAPSHOT.jar
```

验证：

```
curl http://localhost:18100/hello?user=yyw
```


3）启动`复合服务`

```
java -jar complex-service/target/complex-service-1.0.0-SNAPSHOT.jar --server.port=18111
java -jar complex-service/target/complex-service-1.0.0-SNAPSHOT.jar --server.port=18112
```

验证：

```
curl http://localhost:18111/hello?user=yyw
curl http://localhost:18112/hello?user=yyw
curl http://localhost:18111/health
curl http://localhost:18112/health
```

4）启动`Turbine服务`

```
java -jar turbine-server/target/turbine-server-1.0.0-SNAPSHOT.jar
```

验证：

浏览器访问: [Turbine Server](http://localhost:18020/hystrix)

分别输入：

1. http://localhost:18111/hystrix.stream
2. http://localhost:18112/hystrix.stream
3. http://localhost:18020/turbine.stream

监控
