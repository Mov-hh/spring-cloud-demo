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

## 测试1：单Eureka实例

1) 进入工程根目录

```
cd <Root of demo-01>
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
curl http://localhost:18100/base/hello?user=yyw
```


3）启动`复合服务`

```
java -jar complex-service/target/complex-service-1.0.0-SNAPSHOT.jar
```

验证：

```
curl http://localhost:18110/complex/hello?user=yyw
```

4）启动`API网关`

```
java -jar api-gateway/target/api-gateway-1.0.0-SNAPSHOT.jar
```

验证：

```
curl http://localhost:18010/base/hello?user=yyw
curl http://localhost:18010/complex/hello?user=yyw
```

## 测试2：2个Eureka实例

### 成功的操作

1) 进入工程根目录

```
cd <Root of demo-01>
```

2）配置`hosts`，增加如下内容：

```
127.0.0.1      eureka01
127.0.0.1      eureka02
```

3) 启动`eureka服务`

```
java -jar eureka-server/target/eureka-server-1.0.0-SNAPSHOT.jar --server.port=18001 --eureka.client.register-with-eureka=true eureka.client.fetch-registry=true --eureka.instance.hostname=eureka01 --eureka.client.serviceUrl.defaultZone=http://eureka02:18002/eureka/

java -jar eureka-server/target/eureka-server-1.0.0-SNAPSHOT.jar --server.port=18002 --eureka.client.register-with-eureka=true eureka.client.fetch-registry=true  --eureka.instance.hostname=eureka02 --eureka.client.serviceUrl.defaultZone=http://eureka01:18001/eureka/
```

验证：浏览器访问[eureka服务节点01](http://localhost:18001/)、[eureka服务节点02](http://localhost:18002/)

成功提示如下(以 http://eureka01:18001/ 为例)：
```
registered-replicas： http://eureka02:18002/eureka/
unavailable-replicas：
available-replicas：http://eureka02:18002/eureka/
```

### 失败的操作-01

```
java -jar eureka-server/target/eureka-server-1.0.0-SNAPSHOT.jar --server.port=18001 --eureka.client.register-with-eureka=true eureka.client.fetch-registry=true --eureka.instance.hostname=eureka01 --eureka.client.serviceUrl.defaultZone=http://127.0.0.1:18002/eureka/

java -jar eureka-server/target/eureka-server-1.0.0-SNAPSHOT.jar --server.port=18002 --eureka.client.register-with-eureka=true eureka.client.fetch-registry=true --eureka.instance.hostname=eureka02 --eureka.client.serviceUrl.defaultZone=http://127.0.0.1:18001/eureka/
```

失败提示如下(以 http://eureka01:18001/ 为例)：
```
registered-replicas： http://eureka02:18002/eureka/
unavailable-replicas：http://eureka02:18002/eureka/
available-replicas
```

[注] 失败可能原因：`eureka.instance.hostname`的值和`eureka.client.serviceUrl.defaultZone`不匹配导致

### 失败的操作-02


```
java -jar eureka-server/target/eureka-server-1.0.0-SNAPSHOT.jar --server.port=18001 --eureka.client.register-with-eureka=true eureka.client.fetch-registry=true --eureka.client.serviceUrl.defaultZone=http://eureka02:18002/eureka/

java -jar eureka-server/target/eureka-server-1.0.0-SNAPSHOT.jar --server.port=18002 --eureka.client.register-with-eureka=true eureka.client.fetch-registry=true --eureka.client.serviceUrl.defaultZone=http://eureka01:18001/eureka/
```

失败提示如下(以 http://eureka01:18001/ 为例)：
```
registered-replicas： http://eureka02:18002/eureka/
unavailable-replicas：http://eureka02:18002/eureka/
available-replicas
```

[注] 失败可能原因：配置`eureka.instance.hostname`缺失

## 测试3：3个Eureka实例

### 方案1 - 环状串联

1) 进入工程根目录

```
cd <Root of demo-01>
```

2）配置`hosts`，增加如下内容：

```
127.0.0.1      eureka01
127.0.0.1      eureka02
127.0.0.1      eureka03
```

3) 启动`eureka服务`

```
java -jar eureka-server/target/eureka-server-1.0.0-SNAPSHOT.jar --server.port=18001 --eureka.client.register-with-eureka=true eureka.client.fetch-registry=true --eureka.instance.hostname=eureka01 --eureka.client.serviceUrl.defaultZone=http://eureka02:18002/eureka/

java -jar eureka-server/target/eureka-server-1.0.0-SNAPSHOT.jar --server.port=18002 --eureka.client.register-with-eureka=true eureka.client.fetch-registry=true --eureka.instance.hostname=eureka02 --eureka.client.serviceUrl.defaultZone=http://eureka03:18003/eureka/

java -jar eureka-server/target/eureka-server-1.0.0-SNAPSHOT.jar --server.port=18003 --eureka.client.register-with-eureka=true eureka.client.fetch-registry=true --eureka.instance.hostname=eureka03 --eureka.client.serviceUrl.defaultZone=http://eureka01:18001/eureka/
```

成功提示如下(以 http://eureka01:18001/ 为例)：
```
registered-replicas http://eureka02:18002/eureka/
unavailable-replicas  
available-replicas  http://eureka02:18002/eureka/
```

4）启动服务

```
java -jar base-service/impl/target/base-service-impl-1.0.0-SNAPSHOT.jar --eureka.client.serviceUrl.defaultZone=http://eureka01:18001/eureka/,http://eureka02:18002/eureka/,http://eureka03:18003/eureka/

java -jar complex-service/target/complex-service-1.0.0-SNAPSHOT.jar --eureka.client.serviceUrl.defaultZone=http://eureka01:18001/eureka/,http://eureka02:18002/eureka/,http://eureka03:18003/eureka/
```

### 方案2 - 每个节点都向另外两个节点注册

1) 进入工程根目录

```
cd <Root of demo-01>
```

2）配置`hosts`，增加如下内容：

```
127.0.0.1      eureka01
127.0.0.1      eureka02
127.0.0.1      eureka03
```

3) 启动`eureka服务`

```
java -jar eureka-server/target/eureka-server-1.0.0-SNAPSHOT.jar --server.port=18001 --eureka.client.register-with-eureka=true eureka.client.fetch-registry=true --eureka.instance.hostname=eureka01 --eureka.client.serviceUrl.defaultZone=http://eureka02:18002/eureka/,http://eureka03:18003/eureka/

java -jar eureka-server/target/eureka-server-1.0.0-SNAPSHOT.jar --server.port=18002 --eureka.client.register-with-eureka=true eureka.client.fetch-registry=true --eureka.instance.hostname=eureka02 --eureka.client.serviceUrl.defaultZone=http://eureka01:18001/eureka/,http://eureka03:18003/eureka/

java -jar eureka-server/target/eureka-server-1.0.0-SNAPSHOT.jar --server.port=18003 --eureka.client.register-with-eureka=true eureka.client.fetch-registry=true --eureka.instance.hostname=eureka03 --eureka.client.serviceUrl.defaultZone=http://eureka01:18001/eureka/,http://eureka02:18002/eureka/
```

成功提示如下(以 http://eureka01:18001/ 为例)：
```
registered-replicas http://eureka03:18003/eureka/, http://eureka02:18002/eureka/
unavailable-replicas  
available-replicas  http://eureka03:18003/eureka/,http://eureka02:18002/eureka/,
```

4）启动服务

```
java -jar base-service/impl/target/base-service-impl-1.0.0-SNAPSHOT.jar --eureka.client.serviceUrl.defaultZone=http://eureka01:18001/eureka/,http://eureka02:18002/eureka/,http://eureka03:18003/eureka/

java -jar complex-service/target/complex-service-1.0.0-SNAPSHOT.jar --eureka.client.serviceUrl.defaultZone=http://eureka01:18001/eureka/,http://eureka02:18002/eureka/,http://eureka03:18003/eureka/
```
