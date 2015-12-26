/*
Navicat MySQL Data Transfer

Source Server         : localhost(本地)
Source Server Version : 50622
Source Host           : localhost:3306
Source Database       : bui

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2015-12-23 16:46:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `moduleName` varchar(50) NOT NULL DEFAULT '',
  `moduleUrl` varchar(255) NOT NULL DEFAULT '',
  `icon` varchar(255) NOT NULL DEFAULT '',
  `level` int(10) NOT NULL,
  `pid` int(10) NOT NULL,
  `sort` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('75', '系统监控', '', '', '0', '0', '1');
INSERT INTO `sys_menu` VALUES ('76', '监控中心', '', '', '1', '75', '1');
INSERT INTO `sys_menu` VALUES ('77', '车载设备监控', '', '', '2', '76', '1');
INSERT INTO `sys_menu` VALUES ('78', '充电设备监控', '', '', '2', '76', '2');
INSERT INTO `sys_menu` VALUES ('79', '租赁中心', '', '', '1', '75', '2');
INSERT INTO `sys_menu` VALUES ('80', '申请取车', '', '', '2', '79', '1');
INSERT INTO `sys_menu` VALUES ('81', '取车审核', '', '', '2', '79', '2');
INSERT INTO `sys_menu` VALUES ('82', '申请还车', '', '', '2', '79', '3');
INSERT INTO `sys_menu` VALUES ('83', '还车审核', '', '', '2', '79', '4');
INSERT INTO `sys_menu` VALUES ('84', '收取费用', '', '', '2', '79', '5');
INSERT INTO `sys_menu` VALUES ('85', '系统管理', '', '', '0', '0', '9');
INSERT INTO `sys_menu` VALUES ('86', '系统设置', '', '', '1', '85', '1');
INSERT INTO `sys_menu` VALUES ('87', '角色管理', '/system/role/manager.htm', '', '2', '86', '1');
INSERT INTO `sys_menu` VALUES ('88', '员工管理', '/system/user/manager.htm', '', '2', '86', '2');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(50) NOT NULL DEFAULT '',
  `roleRemark` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '系统管理员', '系统管理员');
INSERT INTO `sys_role` VALUES ('2', '测试', '测试');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) NOT NULL,
  `menuId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1', '75');
INSERT INTO `sys_role_menu` VALUES ('2', '1', '76');
INSERT INTO `sys_role_menu` VALUES ('3', '1', '77');
INSERT INTO `sys_role_menu` VALUES ('4', '1', '78');
INSERT INTO `sys_role_menu` VALUES ('5', '1', '79');
INSERT INTO `sys_role_menu` VALUES ('6', '1', '80');
INSERT INTO `sys_role_menu` VALUES ('7', '1', '81');
INSERT INTO `sys_role_menu` VALUES ('8', '1', '82');
INSERT INTO `sys_role_menu` VALUES ('9', '1', '83');
INSERT INTO `sys_role_menu` VALUES ('10', '1', '84');
INSERT INTO `sys_role_menu` VALUES ('11', '1', '85');
INSERT INTO `sys_role_menu` VALUES ('12', '1', '86');
INSERT INTO `sys_role_menu` VALUES ('13', '1', '87');
INSERT INTO `sys_role_menu` VALUES ('14', '1', '88');
INSERT INTO `sys_role_menu` VALUES ('50', '2', '75');
INSERT INTO `sys_role_menu` VALUES ('51', '2', '76');
INSERT INTO `sys_role_menu` VALUES ('52', '2', '77');
INSERT INTO `sys_role_menu` VALUES ('53', '2', '79');
INSERT INTO `sys_role_menu` VALUES ('54', '2', '80');
INSERT INTO `sys_role_menu` VALUES ('55', '2', '85');
INSERT INTO `sys_role_menu` VALUES ('56', '2', '86');
INSERT INTO `sys_role_menu` VALUES ('57', '2', '87');
INSERT INTO `sys_role_menu` VALUES ('58', '2', '88');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `sex` varchar(2) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `roleId` int(11) NOT NULL,
  `status` int(2) NOT NULL DEFAULT '1' COMMENT '员工状态，1：正常，2：删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('6', 'jixf', '纪小风', '123456', '男', '18255538529', '1', '0');
INSERT INTO `sys_user` VALUES ('7', 'admin', '纪小风', '123456', '女', '18255538529', '1', '1');
