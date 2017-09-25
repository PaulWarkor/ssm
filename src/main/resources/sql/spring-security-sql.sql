/*
Navicat MySQL Data Transfer

Source Server         : Fmaster_prd
Source Server Version : 50634
Source Host           : rm-wz94r68n046bihaul.mysql.rds.aliyuncs.com:3306
Source Database       : fmaster_sys

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2017-09-20 15:14:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job` (
  `id` varchar(32) NOT NULL,
  `job_id` varchar(32) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `code` varchar(32) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `state` tinyint(1) DEFAULT NULL,
  `create_by` varchar(32) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `role_id` (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES ('1', '0ca9e1330ac211e69a91005056b961cc', '设备设施专家', 'LB20009', '设备设施专家', '0', null, null, null, null);
INSERT INTO `sys_job` VALUES ('10', '0ca9e1330ac211e69a91005057b96110', '设备监控', 'LB10018', '设备监控', '0', null, null, null, null);
INSERT INTO `sys_job` VALUES ('2', '0ca91f330ac211e69a91005056b961cc', '业务支持负责人', 'LB10016', '业务支持负责人', '0', null, null, null, null);
INSERT INTO `sys_job` VALUES ('3', '0ca9e1330ac211e69a91005057b961cc', '环境监控', 'LB10017', '环境监控', '0', null, null, null, null);
INSERT INTO `sys_job` VALUES ('4', '0ca9d1330ac211e69a91005057b961cc', '万睿技术', 'VB10001', '万睿技术', '0', null, null, null, null);
INSERT INTO `sys_job` VALUES ('5', '0ca9d1330ac211e69a91005057b961c5', '电梯外包', 'VB10002', '电梯外包', '0', null, null, null, null);
INSERT INTO `sys_job` VALUES ('6', '0ca9d1330ac211e69a91005057b961c6', '消防外包', 'VB10003', '消防外包', '0', null, null, null, null);
INSERT INTO `sys_job` VALUES ('7', '0ca9d1330ac211e69a91005057b961c7', '暖通空调外包', 'VB10004', '暖通空调外包', '0', null, null, null, null);
INSERT INTO `sys_job` VALUES ('8', '0ca9d1330ac211e69a91005057b961c8', '供配电外包', 'VB10005', '供配电外包', '0', null, null, null, null);
INSERT INTO `sys_job` VALUES ('9', '0ca9d1330ac211e69a91005057b961c9', '给排水外包', 'VB10006', '给排水外包', '0', null, null, null, null);

-- ----------------------------
-- Table structure for sys_job_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_role`;
CREATE TABLE `sys_job_role` (
  `job_id` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL,
  `state` tinyint(1) DEFAULT NULL,
  `create_by` varchar(32) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`job_id`,`role_id`),
  KEY `role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_job_role
-- ----------------------------
INSERT INTO `sys_job_role` VALUES ('0ca91f330ac211e69a91005056b961c5', '3ca9ef330ac211e69a91005056b961c8', '0', null, null, null, null);
INSERT INTO `sys_job_role` VALUES ('0ca91f330ac211e69a91005056b961c6', '3ca9ef330ac211e69a91005056b961c8', '0', null, null, null, null);
INSERT INTO `sys_job_role` VALUES ('0ca91f330ac211e69a91005056b961c7', '3ca9ef330ac211e69a91005056b961c8', '0', null, null, null, null);
INSERT INTO `sys_job_role` VALUES ('0ca91f330ac211e69a91005056b961c8', '3ca9ef330ac211e69a91005056b961c8', '0', null, null, null, null);
INSERT INTO `sys_job_role` VALUES ('0ca91f330ac211e69a91005056b961c9', '3ca9ef330ac211e69a91005056b961c8', '0', null, null, null, null);
INSERT INTO `sys_job_role` VALUES ('0ca91f330ac211e69a91005056b961cc', '0ca9ef330ac211e61a91005056b961cc', '0', null, null, null, null);
INSERT INTO `sys_job_role` VALUES ('0ca91f330ac211e69a91005056b961cc', '0ca9ef330ac211e69a91005056b961cc', '0', null, null, null, null);
INSERT INTO `sys_job_role` VALUES ('0ca91f330ac211e69a91005056b961cc', '1ca9ef330ac211e69a91005056b961c6', '0', null, null, null, null);
INSERT INTO `sys_job_role` VALUES ('0ca91f330ac211e69a91005056b961cc', '1ca9ef330ac211e69a91005056b961cc', '0', null, null, null, null);
INSERT INTO `sys_job_role` VALUES ('0ca91f330ac211e69a91005056b961cc', '2ca9ef330ac211e69a91005056b961cc', '0', null, null, null, null);
INSERT INTO `sys_job_role` VALUES ('0ca91f330ac211e69a91005056b961cc', '3ca9ef330ac211e69a91005056b961c8', '0', null, null, null, null);
INSERT INTO `sys_job_role` VALUES ('0ca91f330ac211e69a91005056b961cc', '3ca9ef330ac211e69a91005056b961cc', '0', null, null, null, null);
INSERT INTO `sys_job_role` VALUES ('0ca91f330ac211e69a91005056b961cc', '4ca9ef330ac211e69a91005056b961cc', '0', null, null, null, null);
INSERT INTO `sys_job_role` VALUES ('0ca91f330ac211e69a91005056b961cc', '5ca9ef330ac211e69a91005056b961cc', '0', null, null, null, null);
INSERT INTO `sys_job_role` VALUES ('0ca91f330ac211e69a91005056b961cc', '6ca9ef330ac211e69a91005056b961cc', '0', null, null, null, null);
INSERT INTO `sys_job_role` VALUES ('0ca9d1330ac211e69a91005057b961cc', '1ca9ef330ac211e69a91005056b961c6', '0', null, null, null, null);
INSERT INTO `sys_job_role` VALUES ('0ca9e1330ac211e69a91005056b961cc', '0ca9ef330ac211e61a91005056b961cc', '0', null, null, null, null);
INSERT INTO `sys_job_role` VALUES ('0ca9e1330ac211e69a91005056b961cc', '0ca9ef330ac211e69a91005056b961cc', '0', null, null, null, null);
INSERT INTO `sys_job_role` VALUES ('0ca9e1330ac211e69a91005057b961cc', '1ca9ef330ac211e69a91005056b961c1', '0', null, null, null, null);
INSERT INTO `sys_job_role` VALUES ('0ca9e1330ac211e69a91005057b961cc', '2ca9ef330ac211e69a91005056b961c2', '0', null, null, null, null);
INSERT INTO `sys_job_role` VALUES ('0ca9e1330ac211e69a91005057b961cc', '3ca9ef330ac211e69a91005056b961c3', '0', null, null, null, null);
INSERT INTO `sys_job_role` VALUES ('0ca9e1330ac211e69a91005057b961cc', '4ca9ef330ac211e69a91005056b961c4', '0', null, null, null, null);

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` varchar(32) NOT NULL,
  `permission_id` varchar(32) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `code` varchar(32) DEFAULT NULL,
  `type` varchar(32) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `state` tinyint(1) DEFAULT NULL,
  `create_user` varchar(32) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_user` varchar(32) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `permission_id` (`permission_id`),
  CONSTRAINT `fk_permission_resource` FOREIGN KEY (`permission_id`) REFERENCES `sys_permission_resource` (`permission_id`),
  CONSTRAINT `fk_permission_role` FOREIGN KEY (`permission_id`) REFERENCES `sys_role_permission` (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------

-- ----------------------------
-- Table structure for sys_permission_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_resource`;
CREATE TABLE `sys_permission_resource` (
  `permission_id` varchar(32) NOT NULL,
  `resource_id` varchar(32) NOT NULL,
  `state` tinyint(1) DEFAULT NULL,
  `create_user` varchar(32) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_user` varchar(32) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  PRIMARY KEY (`permission_id`,`resource_id`),
  KEY `resource_id` (`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_permission_resource
-- ----------------------------

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` varchar(32) NOT NULL,
  `code` varchar(32) DEFAULT NULL,
  `resource_id` varchar(32) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `method` varchar(32) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `state` tinyint(1) DEFAULT NULL,
  `create_by` varchar(32) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `resource_id` (`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(32) NOT NULL,
  `role_id` varchar(32) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `code` varchar(32) DEFAULT NULL,
  `group` varchar(32) DEFAULT NULL,
  `level` tinyint(10) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `state` tinyint(1) DEFAULT NULL,
  `create_by` varchar(32) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('10', '6ca9ef330ac211e69a91005056b961cc', null, 'ROLE_SPACE_DELETE', 'SPACE', '2', null, null, null, null, null, null);
INSERT INTO `sys_role` VALUES ('11', '1ca9ef330ac211e69a91005056b961c1', null, 'ROLE_REPORT_VIEW', 'REPORT', '8', null, null, null, null, null, null);
INSERT INTO `sys_role` VALUES ('12', '2ca9ef330ac211e69a91005056b961c2', null, 'ROLE_REPORT_ADD', 'REPORT', '1', null, null, null, null, null, null);
INSERT INTO `sys_role` VALUES ('13', '3ca9ef330ac211e69a91005056b961c3', null, 'ROLE_REPORT_UPDATE', 'REPORT', '4', null, null, null, null, null, null);
INSERT INTO `sys_role` VALUES ('14', '4ca9ef330ac211e69a91005056b961c4', null, 'ROLE_REPORT_DELETE', 'REPORT', '2', null, null, null, null, null, null);
INSERT INTO `sys_role` VALUES ('15', '1ca9ef330ac211e69a91005056b961c6', null, 'ROLE_DURATION_VIEW', 'DURATION', '8', null, null, null, null, null, null);
INSERT INTO `sys_role` VALUES ('16', '2ca9ef330ac211e69a91005056b961c7', null, 'ROLE_DURATION_ADD', 'DURATION', '1', null, null, null, null, null, null);
INSERT INTO `sys_role` VALUES ('17', '3ca9ef330ac211e69a91005056b961c8', null, 'ROLE_DURATION_UPDATE', 'DURATION', '4', null, null, null, null, null, null);
INSERT INTO `sys_role` VALUES ('18', '4ca9ef330ac211e69a91005056b961c9', null, 'ROLE_DURATION_DELETE', 'DURATION', '2', null, null, null, null, null, null);
INSERT INTO `sys_role` VALUES ('3', '0ca9ef330ac211e61a91005056b961cc', null, 'ROLE_EQUITMENT_VIEW', 'EQUITMENT', '8', null, null, null, null, null, null);
INSERT INTO `sys_role` VALUES ('4', '0ca9ef330ac211e69a91005056b961cc', null, 'ROLE_SPACE_VIEW', 'SPACE', '8', null, null, null, null, null, null);
INSERT INTO `sys_role` VALUES ('5', '1ca9ef330ac211e69a91005056b961cc', null, 'ROLE_EQUITMENT_ADD', 'EQUITMENT', '1', null, null, null, null, null, null);
INSERT INTO `sys_role` VALUES ('6', '2ca9ef330ac211e69a91005056b961cc', null, 'ROLE_SPACE_ADD', 'SPACE', '1', null, null, null, null, null, null);
INSERT INTO `sys_role` VALUES ('7', '3ca9ef330ac211e69a91005056b961cc', null, 'ROLE_EQUITMENT_UPDATE', 'EQUITMENT', '4', null, null, null, null, null, null);
INSERT INTO `sys_role` VALUES ('8', '4ca9ef330ac211e69a91005056b961cc', null, 'ROLE_SPACE_UPDATE', 'SPACE', '4', null, null, null, null, null, null);
INSERT INTO `sys_role` VALUES ('9', '5ca9ef330ac211e69a91005056b961cc', null, 'ROLE_EQUITMENT_DELETE', 'EQUITMENT', '2', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `role_id` varchar(32) NOT NULL,
  `permission_id` varchar(32) NOT NULL,
  `state` tinyint(1) DEFAULT NULL,
  `create_user` varchar(32) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_user` varchar(32) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  PRIMARY KEY (`role_id`,`permission_id`),
  KEY `permission_id` (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
  `role_id` varchar(32) DEFAULT NULL,
  `resource_id` varchar(32) DEFAULT NULL,
  `state` tinyint(1) DEFAULT NULL,
  `create_by` varchar(32) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  KEY `role_id` (`role_id`),
  KEY `resource_id` (`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `group_id` varchar(32) DEFAULT NULL,
  `account` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `mobile_phone` varchar(32) DEFAULT NULL,
  `type` varchar(32) DEFAULT NULL,
  `state` tinyint(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `create_by` varchar(32) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL,
  `state` tinyint(1) DEFAULT NULL,
  `create_by` varchar(32) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
