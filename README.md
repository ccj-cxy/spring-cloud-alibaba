# spring-cloud-alibaba

微服务阿里版

# snk-web-common

核心工具包

## common-core

项目核心工具包

## common-redis

项目操作redis工具包

## common-swagger

项目api文档包

## starter

springboot整个启动包 自动注入 

# geteway

网关

# snk-auth

 登录用户管理服务

登录验证流程

![snk框架登录验证流程](.\snk框架登录验证流程.png)

# snk-amqp

RabbitMQ 服务

## 主要交换机类型有：

### Direct Exchange 

处理路由键。需要将一个队列绑定到交换机上，要求该消息与一个特定的路由键完全匹配。这是一个完整的匹配。如果一个队列绑定到该交换机上要求路由键 “abc”，则只有被标记为“abc”的消息才被转发，不会转发abc.def，也不会转发dog.ghi，只会转发abc。

### Fanout Exchange

不处理路由键。你只需要简单的将队列绑定到交换机上。一个发送到交换机的消息都会被转发到与该交换机绑定的所有队列上。很像子网广播，每台子网内的主机都获得了一份复制的消息。Fanout交换机转发消息是最快的。

### Topic Exchange

将路由键和某模式进行匹配。此时队列需要绑定要一个模式上。符号“#”匹配一个或多个词，符号“”匹配不多不少一个词。因此“abc.#”能够匹配到“abc.def.ghi”，但是“abc.” 只会匹配到“abc.def”。

### Headers Exchanges

不处理路由键。而是根据发送的消息内容中的headers属性进行匹配。在绑定Queue与Exchange时指定一组键值对；当消息发送到RabbitMQ时会取到该消息的headers与Exchange绑定时指定的键值对进行匹配；如果完全匹配则消息会路由到该队列，否则不会路由到该队列。headers属性是一个键值对，可以是Hashtable，键值对的值可以是任何类型。而fanout，direct，topic 的路由键都需要要字符串形式的。
匹配规则x-match有下列两种类型：
x-match = all ：表示所有的键值对都匹配才能接受到消息
x-match = any ：表示只要有键值对匹配就能接受到消息

摘抄自：https://www.cnblogs.com/linyufeng/p/9885020.html

# snk-email

邮件发送服务

# user-consumer

用户消费者
--controller 
    --OrderController            发送订单
--service 
    --impl  OrderServiceImpl     订单服务实现类  --消息可靠投递
    OrderService                 订单服务
    

# user-provider

用户生产者

# snk-file

文件上传及下载服务，整合了minio一个可以在内网环境使用的文件服务
minio github网址：https://github.com/minio/minio
minio 中文文档 : http://docs.minio.org.cn/docs/master/minio-monitoring-guide
...
后续摸索前行