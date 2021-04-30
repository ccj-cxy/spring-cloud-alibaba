/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.71.10
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : 192.168.71.10:3306
 Source Schema         : nacos

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 30/04/2021 18:19:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_use` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `effect` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_schema` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfo_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info
-- ----------------------------
INSERT INTO `config_info` VALUES (10, 'user-provider', 'DEFAULT_GROUP', 'service:\n  username: 张三', '5d6162512e76352344ffe93e0d613c0f', '2021-04-29 09:26:58', '2021-04-29 11:52:42', NULL, '127.0.0.1', '', '', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (13, 'user-consumer', 'DEFAULT_GROUP', 'spring:\n  cloud:\n    nacos:\n      server-addr: 127.0.0.1:8848\n      config:\n        file-extension: yml\n        name: user-consumer\n        group: DEFAULT_GROUP\n        namespace: e5aebd28-1c15-4991-a36e-0865bb5af930\n  application:\n    name: user-consumer', '6a0f1f6882898c46ecb8b22de07afa29', '2021-04-29 11:23:11', '2021-04-29 11:23:43', NULL, '127.0.0.1', '', '', '', '', '', 'yaml', '');

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime(0) NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfoaggr_datagrouptenantdatum`(`data_id`, `group_id`, `tenant_id`, `datum_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '增加租户字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfobeta_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_beta' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfotag_datagrouptenanttag`(`data_id`, `group_id`, `tenant_id`, `tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_tag' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation`  (
  `id` bigint(0) NOT NULL COMMENT 'id',
  `tag_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint(0) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`) USING BTREE,
  UNIQUE INDEX `uk_configtagrelation_configidtag`(`id`, `tag_name`, `tag_type`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_tag_relation' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_group_id`(`group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '集群、各Group容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info`  (
  `id` bigint(0) UNSIGNED NOT NULL,
  `nid` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `op_type` char(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`) USING BTREE,
  INDEX `idx_gmt_create`(`gmt_create`) USING BTREE,
  INDEX `idx_gmt_modified`(`gmt_modified`) USING BTREE,
  INDEX `idx_did`(`data_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '多租户改造' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
INSERT INTO `his_config_info` VALUES (0, 1, 'application-gateway', 'DEFAULT_GROUP', '', 'server:\r\n  port: 7000\r\nspring:\r\n  application:\r\n    name: api-gateway\r\n  cloud:\r\n    gateway:\r\n      #路由数组【路由 就是指当请求满足什么条件时转发到那个微服务上】\r\n      routes:\r\n        - id: product_route #当前路由转发的标识，要求唯一 ，默认是UUID\r\n          uri:  http://127.0.0.1:8081 #请求最终要被转发的地址\r\n          order: 1 #路由的优先级 数字越小代表路由的优先级越高\r\n          predicates: #断言(条件判断，返回值为boolean，转发满足的条件)\r\n            - Path=/product-serv/** #当请求路径满足Path指定的规则时，此路由信息才会正常转发\r\n          filters:  #过滤器（在请求传递过程中对请求做处理）\r\n            - StripPrefix=1 #在请求转发之前去掉一层路径', 'aea7c0313fa279eee9b14447dd48f344', '2021-04-29 11:23:46', '2021-04-29 03:23:48', NULL, '127.0.0.1', 'I', '');
INSERT INTO `his_config_info` VALUES (1, 2, 'application-gateway', 'DEFAULT_GROUP', '', 'server:\r\n  port: 7000\r\nspring:\r\n  application:\r\n    name: api-gateway\r\n  cloud:\r\n    gateway:\r\n      #路由数组【路由 就是指当请求满足什么条件时转发到那个微服务上】\r\n      routes:\r\n        - id: product_route #当前路由转发的标识，要求唯一 ，默认是UUID\r\n          uri:  http://127.0.0.1:8081 #请求最终要被转发的地址\r\n          order: 1 #路由的优先级 数字越小代表路由的优先级越高\r\n          predicates: #断言(条件判断，返回值为boolean，转发满足的条件)\r\n            - Path=/product-serv/** #当请求路径满足Path指定的规则时，此路由信息才会正常转发\r\n          filters:  #过滤器（在请求传递过程中对请求做处理）\r\n            - StripPrefix=1 #在请求转发之前去掉一层路径', 'aea7c0313fa279eee9b14447dd48f344', '2021-04-29 11:28:03', '2021-04-29 03:28:05', NULL, '127.0.0.1', 'D', '');
INSERT INTO `his_config_info` VALUES (0, 3, 'service-gateway', 'DEFAULT_GROUP', '', 'server:\r\n  port: 7000\r\nspring:\r\n  application:\r\n    name: api-gateway\r\n  cloud:\r\n    nacos:\r\n      discovery:\r\n        server-addr: 127.0.0.1:8848 #将gateway注册到nacos\r\n\r\n    gateway:\r\n      discovery:\r\n        locator:\r\n          enabled: true #让gateway从nacos中获取信息\r\n      #路由数组【路由 就是指当请求满足什么条件时转发到那个微服务上】\r\n      routes:\r\n        - id: product_route #当前路由转发的标识，要求唯一 ，默认是UUID\r\n          uri:  lb://service-product #lb指的时负载均衡 后面指具体微服务的标识\r\n          order: 1 #路由的优先级 数字越小代表路由的优先级越高\r\n          predicates: #断言(条件判断，返回值为boolean，转发满足的条件)\r\n            - Path=/product-serv/** #当请求路径满足Path指定的规则时，此路由信息才会正常转发\r\n          #  - Before=2021-11-28T00:00:00.000+08:00 #限制请求时间在2021-11-28日之前\r\n           # - Method=get #必须是get请求才会转发\r\n           # - Age=18,60 #自定义断言 限制年龄在18-60之间\r\n          filters:  #过滤器（在请求传递过程中对请求做处理）\r\n            - StripPrefix=1 #在请求转发之前去掉一层路径\r\n            #- SetStatus=250\r\n            #- Log=true,false\r\n\r\n        - id: order_route #当前路由转发的标识，要求唯一 ，默认是UUID\r\n          uri:  lb://service-order #lb指的时负载均衡 后面指具体微服务的标识\r\n          order: 1 #路由的优先级 数字越小代表路由的优先级越高\r\n          predicates: #断言(条件判断，返回值为boolean，转发满足的条件)\r\n            - Path=/order-serv/** #当请求路径满足Path指定的规则时，此路由信息才会正常转发\r\n            #- Before=2021-11-28T00:00:00.000+08:00 #限制请求时间在2021-11-28日之前\r\n           # - Method=get #必须是get请求才会转发\r\n            # - Age=18,60 #自定义断言 限制年龄在18-60之间\r\n          filters:  #过滤器（在请求传递过程中对请求做处理）\r\n            - StripPrefix=1 #在请求转发之前去掉一层路径\r\n            #- SetStatus=250\r\n           # - Log=true,false\r\n\r\n  sleuth:\r\n    sampler:\r\n      probability: 1.0 #采样百分比', '9fe2c6916594b48f64f08b8ccc80b7eb', '2021-04-29 11:35:13', '2021-04-29 03:35:14', NULL, '127.0.0.1', 'I', '');
INSERT INTO `his_config_info` VALUES (2, 4, 'service-gateway', 'DEFAULT_GROUP', '', 'server:\r\n  port: 7000\r\nspring:\r\n  application:\r\n    name: api-gateway\r\n  cloud:\r\n    nacos:\r\n      discovery:\r\n        server-addr: 127.0.0.1:8848 #将gateway注册到nacos\r\n\r\n    gateway:\r\n      discovery:\r\n        locator:\r\n          enabled: true #让gateway从nacos中获取信息\r\n      #路由数组【路由 就是指当请求满足什么条件时转发到那个微服务上】\r\n      routes:\r\n        - id: product_route #当前路由转发的标识，要求唯一 ，默认是UUID\r\n          uri:  lb://service-product #lb指的时负载均衡 后面指具体微服务的标识\r\n          order: 1 #路由的优先级 数字越小代表路由的优先级越高\r\n          predicates: #断言(条件判断，返回值为boolean，转发满足的条件)\r\n            - Path=/product-serv/** #当请求路径满足Path指定的规则时，此路由信息才会正常转发\r\n          #  - Before=2021-11-28T00:00:00.000+08:00 #限制请求时间在2021-11-28日之前\r\n           # - Method=get #必须是get请求才会转发\r\n           # - Age=18,60 #自定义断言 限制年龄在18-60之间\r\n          filters:  #过滤器（在请求传递过程中对请求做处理）\r\n            - StripPrefix=1 #在请求转发之前去掉一层路径\r\n            #- SetStatus=250\r\n            #- Log=true,false\r\n\r\n        - id: order_route #当前路由转发的标识，要求唯一 ，默认是UUID\r\n          uri:  lb://service-order #lb指的时负载均衡 后面指具体微服务的标识\r\n          order: 1 #路由的优先级 数字越小代表路由的优先级越高\r\n          predicates: #断言(条件判断，返回值为boolean，转发满足的条件)\r\n            - Path=/order-serv/** #当请求路径满足Path指定的规则时，此路由信息才会正常转发\r\n            #- Before=2021-11-28T00:00:00.000+08:00 #限制请求时间在2021-11-28日之前\r\n           # - Method=get #必须是get请求才会转发\r\n            # - Age=18,60 #自定义断言 限制年龄在18-60之间\r\n          filters:  #过滤器（在请求传递过程中对请求做处理）\r\n            - StripPrefix=1 #在请求转发之前去掉一层路径\r\n            #- SetStatus=250\r\n           # - Log=true,false\r\n\r\n  sleuth:\r\n    sampler:\r\n      probability: 1.0 #采样百分比', '9fe2c6916594b48f64f08b8ccc80b7eb', '2021-04-29 14:20:54', '2021-04-29 06:44:56', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (2, 5, 'service-gateway', 'DEFAULT_GROUP', '', '\nspring:\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 127.0.0.1:8848 #将gateway注册到nacos\n\n    gateway:\n      discovery:\n        locator:\n          enabled: true #让gateway从nacos中获取信息\n      #路由数组【路由 就是指当请求满足什么条件时转发到那个微服务上】\n      routes:\n        - id: product_route #当前路由转发的标识，要求唯一 ，默认是UUID\n          uri:  lb://service-product #lb指的时负载均衡 后面指具体微服务的标识\n          order: 1 #路由的优先级 数字越小代表路由的优先级越高\n          predicates: #断言(条件判断，返回值为boolean，转发满足的条件)\n            - Path=/product-serv/** #当请求路径满足Path指定的规则时，此路由信息才会正常转发\n          #  - Before=2021-11-28T00:00:00.000+08:00 #限制请求时间在2021-11-28日之前\n           # - Method=get #必须是get请求才会转发\n           # - Age=18,60 #自定义断言 限制年龄在18-60之间\n          filters:  #过滤器（在请求传递过程中对请求做处理）\n            - StripPrefix=1 #在请求转发之前去掉一层路径\n            #- SetStatus=250\n            #- Log=true,false\n\n        - id: order_route #当前路由转发的标识，要求唯一 ，默认是UUID\n          uri:  lb://service-order #lb指的时负载均衡 后面指具体微服务的标识\n          order: 1 #路由的优先级 数字越小代表路由的优先级越高\n          predicates: #断言(条件判断，返回值为boolean，转发满足的条件)\n            - Path=/order-serv/** #当请求路径满足Path指定的规则时，此路由信息才会正常转发\n            #- Before=2021-11-28T00:00:00.000+08:00 #限制请求时间在2021-11-28日之前\n           # - Method=get #必须是get请求才会转发\n            # - Age=18,60 #自定义断言 限制年龄在18-60之间\n          filters:  #过滤器（在请求传递过程中对请求做处理）\n            - StripPrefix=1 #在请求转发之前去掉一层路径\n            #- SetStatus=250\n           # - Log=true,false\n\n  sleuth:\n    sampler:\n      probability: 1.0 #采样百分比', '61c443d30d0f6fbd709c1f8d47f8db91', '2021-04-29 14:27:22', '2021-04-29 06:51:24', NULL, '127.0.0.1', 'D', '');
INSERT INTO `his_config_info` VALUES (0, 6, 'service-gateway', 'DEFAULT_GROUP', '', '\nspring:\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 127.0.0.1:8848 #将gateway注册到nacos\n\n    gateway:\n      discovery:\n        locator:\n          enabled: true #让gateway从nacos中获取信息\n      #路由数组【路由 就是指当请求满足什么条件时转发到那个微服务上】\n      routes:\n        - id: product_route #当前路由转发的标识，要求唯一 ，默认是UUID\n          uri:  lb://service-product #lb指的时负载均衡 后面指具体微服务的标识\n          order: 1 #路由的优先级 数字越小代表路由的优先级越高\n          predicates: #断言(条件判断，返回值为boolean，转发满足的条件)\n            - Path=/product-serv/** #当请求路径满足Path指定的规则时，此路由信息才会正常转发\n          #  - Before=2021-11-28T00:00:00.000+08:00 #限制请求时间在2021-11-28日之前\n           # - Method=get #必须是get请求才会转发\n           # - Age=18,60 #自定义断言 限制年龄在18-60之间\n          filters:  #过滤器（在请求传递过程中对请求做处理）\n            - StripPrefix=1 #在请求转发之前去掉一层路径\n            #- SetStatus=250\n            #- Log=true,false\n\n        - id: order_route #当前路由转发的标识，要求唯一 ，默认是UUID\n          uri:  lb://service-order #lb指的时负载均衡 后面指具体微服务的标识\n          order: 1 #路由的优先级 数字越小代表路由的优先级越高\n          predicates: #断言(条件判断，返回值为boolean，转发满足的条件)\n            - Path=/order-serv/** #当请求路径满足Path指定的规则时，此路由信息才会正常转发\n            #- Before=2021-11-28T00:00:00.000+08:00 #限制请求时间在2021-11-28日之前\n           # - Method=get #必须是get请求才会转发\n            # - Age=18,60 #自定义断言 限制年龄在18-60之间\n          filters:  #过滤器（在请求传递过程中对请求做处理）\n            - StripPrefix=1 #在请求转发之前去掉一层路径\n            #- SetStatus=250\n           # - Log=true,false\n\n  sleuth:\n    sampler:\n      probability: 1.0 #采样百分比', '61c443d30d0f6fbd709c1f8d47f8db91', '2021-04-29 14:32:59', '2021-04-29 06:57:01', NULL, '127.0.0.1', 'I', '');
INSERT INTO `his_config_info` VALUES (0, 7, 'user-provider', 'dev', '', 'username: user', 'f53f63138f7280528f0e717701b4fda8', '2021-04-29 14:58:08', '2021-04-29 07:22:10', NULL, '127.0.0.1', 'I', '531afa2d-de8c-4452-835e-a877df2fa021');
INSERT INTO `his_config_info` VALUES (5, 8, 'user-provider', 'dev', '', 'username: user', 'f53f63138f7280528f0e717701b4fda8', '2021-04-29 15:23:24', '2021-04-29 07:47:26', NULL, '127.0.0.1', 'U', '531afa2d-de8c-4452-835e-a877df2fa021');
INSERT INTO `his_config_info` VALUES (0, 9, 'user-consumer', 'dev', '', 'spring:\r\n  cloud:\r\n    nacos:\r\n        discovery:\r\n        server-addr: 127.0.0.1:8848\r\n        namespace: 531afa2d-de8c-4452-835e-a877df2fa021\r\n        group: ${spring.profiles.active}', '2ba464fbe022a66a37dc25f3186060d4', '2021-04-29 15:51:09', '2021-04-29 08:15:11', NULL, '127.0.0.1', 'I', '531afa2d-de8c-4452-835e-a877df2fa021');
INSERT INTO `his_config_info` VALUES (0, 10, 'user-provider-dev', 'dev', '', 'username: tom', '7d5d49a4cc0bfb73b4db6c438ce73dfe', '2021-04-29 16:23:55', '2021-04-29 08:47:57', NULL, '127.0.0.1', 'I', '531afa2d-de8c-4452-835e-a877df2fa021');
INSERT INTO `his_config_info` VALUES (5, 11, 'user-provider', 'dev', '', 'username: tom', '7d5d49a4cc0bfb73b4db6c438ce73dfe', '2021-04-29 16:43:15', '2021-04-29 09:07:17', NULL, '127.0.0.1', 'U', '531afa2d-de8c-4452-835e-a877df2fa021');
INSERT INTO `his_config_info` VALUES (8, 12, 'user-provider-dev', 'dev', '', 'username: tom', '7d5d49a4cc0bfb73b4db6c438ce73dfe', '2021-04-29 16:53:27', '2021-04-29 09:17:30', NULL, '127.0.0.1', 'D', '531afa2d-de8c-4452-835e-a877df2fa021');
INSERT INTO `his_config_info` VALUES (4, 13, 'service-gateway', 'DEFAULT_GROUP', '', '\nspring:\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 127.0.0.1:8848 #将gateway注册到nacos\n\n    gateway:\n      discovery:\n        locator:\n          enabled: true #让gateway从nacos中获取信息\n      #路由数组【路由 就是指当请求满足什么条件时转发到那个微服务上】\n      routes:\n        - id: product_route #当前路由转发的标识，要求唯一 ，默认是UUID\n          uri:  lb://service-product #lb指的时负载均衡 后面指具体微服务的标识\n          order: 1 #路由的优先级 数字越小代表路由的优先级越高\n          predicates: #断言(条件判断，返回值为boolean，转发满足的条件)\n            - Path=/product-serv/** #当请求路径满足Path指定的规则时，此路由信息才会正常转发\n          #  - Before=2021-11-28T00:00:00.000+08:00 #限制请求时间在2021-11-28日之前\n           # - Method=get #必须是get请求才会转发\n           # - Age=18,60 #自定义断言 限制年龄在18-60之间\n          filters:  #过滤器（在请求传递过程中对请求做处理）\n            - StripPrefix=1 #在请求转发之前去掉一层路径\n            #- SetStatus=250\n            #- Log=true,false\n\n        - id: order_route #当前路由转发的标识，要求唯一 ，默认是UUID\n          uri:  lb://service-order #lb指的时负载均衡 后面指具体微服务的标识\n          order: 1 #路由的优先级 数字越小代表路由的优先级越高\n          predicates: #断言(条件判断，返回值为boolean，转发满足的条件)\n            - Path=/order-serv/** #当请求路径满足Path指定的规则时，此路由信息才会正常转发\n            #- Before=2021-11-28T00:00:00.000+08:00 #限制请求时间在2021-11-28日之前\n           # - Method=get #必须是get请求才会转发\n            # - Age=18,60 #自定义断言 限制年龄在18-60之间\n          filters:  #过滤器（在请求传递过程中对请求做处理）\n            - StripPrefix=1 #在请求转发之前去掉一层路径\n            #- SetStatus=250\n           # - Log=true,false\n\n  sleuth:\n    sampler:\n      probability: 1.0 #采样百分比', '61c443d30d0f6fbd709c1f8d47f8db91', '2021-04-29 17:02:38', '2021-04-29 09:26:40', NULL, '127.0.0.1', 'D', '');
INSERT INTO `his_config_info` VALUES (0, 14, 'user-provider', 'DEFAULT_GROUP', '', 'username: tom', '7d5d49a4cc0bfb73b4db6c438ce73dfe', '2021-04-29 17:02:56', '2021-04-29 09:26:58', NULL, '127.0.0.1', 'I', '');
INSERT INTO `his_config_info` VALUES (5, 15, 'user-provider', 'dev', '', 'username1: tom', '6f38376359c53a0b6513517a44d94911', '2021-04-29 17:10:08', '2021-04-29 09:34:10', NULL, '127.0.0.1', 'U', '531afa2d-de8c-4452-835e-a877df2fa021');
INSERT INTO `his_config_info` VALUES (5, 16, 'user-provider', 'dev', '', 'username: tom', '7d5d49a4cc0bfb73b4db6c438ce73dfe', '2021-04-29 18:50:17', '2021-04-29 11:14:19', NULL, '127.0.0.1', 'D', '531afa2d-de8c-4452-835e-a877df2fa021');
INSERT INTO `his_config_info` VALUES (7, 17, 'user-consumer', 'dev', '', 'spring:\r\n  cloud:\r\n    nacos:\r\n        discovery:\r\n        server-addr: 127.0.0.1:8848\r\n        namespace: 531afa2d-de8c-4452-835e-a877df2fa021\r\n        group: ${spring.profiles.active}', '2ba464fbe022a66a37dc25f3186060d4', '2021-04-29 18:50:23', '2021-04-29 11:14:25', NULL, '127.0.0.1', 'D', '531afa2d-de8c-4452-835e-a877df2fa021');
INSERT INTO `his_config_info` VALUES (10, 18, 'user-provider', 'DEFAULT_GROUP', '', 'username: tom', '7d5d49a4cc0bfb73b4db6c438ce73dfe', '2021-04-29 18:56:22', '2021-04-29 11:20:24', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (0, 19, 'user-consumer', 'DEFAULT_GROUP', '', 'spring:\r\n  cloud:\r\n    nacos:\r\n      server-addr: 127.0.0.1:8848\r\n      config:\r\n        file-extension: yml\r\n        name: user-consumer\r\n        group: ${spring.profiles.active}\r\n        namespace: e5aebd28-1c15-4991-a36e-0865bb5af930\r\n  application:\r\n    name: user-consumer', '29c23f2915e85474a2b03a1b81172fae', '2021-04-29 18:59:09', '2021-04-29 11:23:11', NULL, '127.0.0.1', 'I', '');
INSERT INTO `his_config_info` VALUES (13, 20, 'user-consumer', 'DEFAULT_GROUP', '', 'spring:\r\n  cloud:\r\n    nacos:\r\n      server-addr: 127.0.0.1:8848\r\n      config:\r\n        file-extension: yml\r\n        name: user-consumer\r\n        group: ${spring.profiles.active}\r\n        namespace: e5aebd28-1c15-4991-a36e-0865bb5af930\r\n  application:\r\n    name: user-consumer', '29c23f2915e85474a2b03a1b81172fae', '2021-04-29 18:59:41', '2021-04-29 11:23:43', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (10, 21, 'user-provider', 'DEFAULT_GROUP', '', 'username: tom', '7d5d49a4cc0bfb73b4db6c438ce73dfe', '2021-04-29 19:09:52', '2021-04-29 11:33:54', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (10, 22, 'user-provider', 'DEFAULT_GROUP', '', 'service:\n  username: tom', 'd24d08c1ee7dbce6d527d22590e6427b', '2021-04-29 19:28:40', '2021-04-29 11:52:42', NULL, '127.0.0.1', 'U', '');

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions`  (
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `resource` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `action` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  UNIQUE INDEX `uk_role_permission`(`role`, `resource`, `action`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  UNIQUE INDEX `idx_user_role`(`username`, `role`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数',
  `max_aggr_size` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '租户容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_info_kptenantid`(`kp`, `tenant_id`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'tenant_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_info
-- ----------------------------
INSERT INTO `tenant_info` VALUES (3, '1', '371dfd6f-97d7-4b24-9a81-4d791a0cbcae', 'dev', '开发环境', 'nacos', 1619694890251, 1619694890251);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);

SET FOREIGN_KEY_CHECKS = 1;
