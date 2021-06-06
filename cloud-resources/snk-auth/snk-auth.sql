/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.71.10
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : 192.168.71.10:3306
 Source Schema         : snk-auth

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 06/06/2021 22:16:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for public_org
-- ----------------------------
DROP TABLE IF EXISTS `public_org`;
CREATE TABLE `public_org`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '机构ID',
  `CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '机构代号',
  `NAME` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '机构名',
  `FULL_NAME` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '机构路径全称',
  `SHORT_NAME` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '机构简称',
  `SORT_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '排序代码',
  `PARENT_ID` int(0) NULL DEFAULT NULL COMMENT '上级机构',
  `LEVEL` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '机构级别',
  `ORG_TYPE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '机构类型',
  `LEADER` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '负责人',
  `REMARK` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '机构说明',
  `CREATED_BY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '部门机构 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of public_org
-- ----------------------------
INSERT INTO `public_org` VALUES (1, '0', 'xxx集团公司', NULL, '总公司', '0', 0, '0', '0', '0', '总公司', '0', '2021-06-06 18:44:23', '0', '2021-06-06 18:44:29');
INSERT INTO `public_org` VALUES (2, '1', 'xxx有限公司', NULL, '分公司', '0', 1, '1', '1', '1', '分公司', '0', '2021-06-06 18:45:35', '0', '2021-06-06 18:45:42');
INSERT INTO `public_org` VALUES (3, '2', '开发部', NULL, '开发部门', '1', 2, '2', '2', '1', '职能部门', NULL, NULL, NULL, NULL);
INSERT INTO `public_org` VALUES (4, '3', '测试部', NULL, '测试部门', '2', 2, '2', '2', '1', '职能部门', '0', '2021-06-06 18:47:40', '0', '2021-06-06 18:47:45');
INSERT INTO `public_org` VALUES (5, '4', '业务部门', NULL, '业务部门', '3', 2, '2', '2', '1', '职能部门', '0', NULL, '0', NULL);

-- ----------------------------
-- Table structure for public_resource
-- ----------------------------
DROP TABLE IF EXISTS `public_resource`;
CREATE TABLE `public_resource`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '资源名称',
  `type` tinyint(1) NOT NULL COMMENT '资源类型 1:子系统 2:业务模块 3:功能项 4:对外接口',
  `url` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '资源地址',
  `permission_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '访问权限',
  `icon` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '资源图标',
  `parent_id` int(0) NOT NULL DEFAULT 0 COMMENT '父级资源id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of public_resource
-- ----------------------------
INSERT INTO `public_resource` VALUES (1, '系统', 0, '/snk', '/snk', '', 0);
INSERT INTO `public_resource` VALUES (2, 'auth服务', 1, '/snk-auth', '/snk-auth', '', 1);
INSERT INTO `public_resource` VALUES (3, 'file服务', 1, '/snk-file', '/snk-file', NULL, 1);
INSERT INTO `public_resource` VALUES (4, 'consumer服务', 1, '/user-consumer', '/user-consumer', NULL, 1);
INSERT INTO `public_resource` VALUES (5, '获取所有订单', 2, '/user-consumer/order/getPage', '/user-consumer/order/getPage', NULL, 4);

-- ----------------------------
-- Table structure for public_role
-- ----------------------------
DROP TABLE IF EXISTS `public_role`;
CREATE TABLE `public_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '角色生效开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '角色生效结束时间',
  `defaulted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '默认角色 0:非默认角色 1:默认角色',
  `org_id` int(0) NOT NULL COMMENT '站点id',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_name_web_id`(`name`, `org_id`) USING BTREE,
  INDEX `idx_name`(`name`) USING BTREE,
  INDEX `fk_web_id`(`org_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of public_role
-- ----------------------------
INSERT INTO `public_role` VALUES (1, '测试角色', NULL, NULL, 1, 0, '2021-05-27 23:10:45', '2021-05-27 23:10:45');

-- ----------------------------
-- Table structure for public_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `public_role_resource`;
CREATE TABLE `public_role_resource`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_id` int(0) NOT NULL COMMENT '角色id',
  `resource_id` int(0) NOT NULL COMMENT '资源id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_role_id`(`role_id`) USING BTREE,
  INDEX `fk_resource_id`(`resource_id`) USING BTREE,
  INDEX `uk_role_id_resource_id`(`role_id`, `resource_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色资源关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of public_role_resource
-- ----------------------------
INSERT INTO `public_role_resource` VALUES (5, 1, 1);
INSERT INTO `public_role_resource` VALUES (6, 1, 2);
INSERT INTO `public_role_resource` VALUES (7, 1, 3);
INSERT INTO `public_role_resource` VALUES (8, 1, 4);
INSERT INTO `public_role_resource` VALUES (9, 1, 5);

-- ----------------------------
-- Table structure for public_user
-- ----------------------------
DROP TABLE IF EXISTS `public_user`;
CREATE TABLE `public_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `nike_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `mobile_phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机',
  `qq` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'QQ',
  `email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `enabled` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态 0:禁用 1:启用',
  `type` tinyint(1) NOT NULL DEFAULT 2 COMMENT '0:系统管理员 1:站点管理员 2:普通用户',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `org_id` int(0) NOT NULL COMMENT '站点id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE COMMENT '唯一索引，用户名必须唯一',
  INDEX `idx_nick_name`(`nike_name`) USING BTREE,
  INDEX `idx_mobile_phone`(`mobile_phone`) USING BTREE,
  INDEX `idx_email`(`email`) USING BTREE,
  INDEX `idx_create_time`(`create_time`) USING BTREE,
  INDEX `fk_web_id`(`org_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of public_user
-- ----------------------------
INSERT INTO `public_user` VALUES (1, 'zs', 'MTIz', 'zs', '123', '123', '123', 1, 0, '2021-05-26 22:20:02', '2021-05-26 22:20:02', 0);
INSERT INTO `public_user` VALUES (2, 'lisi', 'MTIz', 'lisi', '123', '123', '123', 1, 1, '2021-05-26 22:24:39', '2021-05-26 22:24:39', 0);
INSERT INTO `public_user` VALUES (3, 'wangwu', 'MTIzNDU2', 'wangwu', '123', '123', '123', 1, 1, '2021-05-26 22:31:34', '2021-05-26 22:31:34', 0);
INSERT INTO `public_user` VALUES (12, 'ccj', 'MTIzNDU2', 'snk', '1111111', '99', 'caic.j@qq.com', 1, 2, '2021-06-02 16:09:35', '2021-06-02 16:09:35', 0);
INSERT INTO `public_user` VALUES (19, 'ml', 'MTIzNDU2', 'snk', '1111111', '99', 'caic.j@qq.com', 1, 2, '2021-06-02 16:47:10', '2021-06-02 16:47:10', 0);

-- ----------------------------
-- Table structure for public_user_role
-- ----------------------------
DROP TABLE IF EXISTS `public_user_role`;
CREATE TABLE `public_user_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(0) NOT NULL COMMENT '用户id',
  `role_id` int(0) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_user_id_role_id`(`user_id`, `role_id`) USING BTREE,
  INDEX `fk_user_id`(`user_id`) USING BTREE,
  INDEX `fk_role_id`(`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of public_user_role
-- ----------------------------
INSERT INTO `public_user_role` VALUES (5, 1, 1);
INSERT INTO `public_user_role` VALUES (6, 1, 2);
INSERT INTO `public_user_role` VALUES (7, 1, 3);

SET FOREIGN_KEY_CHECKS = 1;
