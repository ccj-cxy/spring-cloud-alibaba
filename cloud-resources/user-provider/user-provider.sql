/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.71.10
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : 192.168.71.10:3306
 Source Schema         : user-provider

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 18/05/2021 09:19:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for waybill
-- ----------------------------
DROP TABLE IF EXISTS `waybill`;
CREATE TABLE `waybill`  (
  `id` bigint(0) NOT NULL COMMENT '主键id',
  `order_id` int(0) NOT NULL COMMENT '订单号',
  `waybill_id` int(0) NULL DEFAULT NULL COMMENT '运单号',
  `company` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '接单公司',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '运单' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
