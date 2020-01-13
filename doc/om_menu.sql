/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : mydb

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2019-09-07 19:37:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for om_menu
-- ----------------------------
DROP TABLE IF EXISTS `om_menu`;
CREATE TABLE `om_menu` (
  `menu_id` varchar(32) NOT NULL COMMENT '菜单id',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '上一级菜单id',
  `menu_name` varchar(256) DEFAULT NULL COMMENT '菜单文本',
  `menu_url` varchar(256) DEFAULT NULL COMMENT '菜单url',
  `status` varchar(10) DEFAULT '1' COMMENT '启用标示,1、启用，2、禁用',
  `menu_order` int(11) DEFAULT '0' COMMENT '菜单顺序. 通常一级菜单才需要设置',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of om_menu
-- ----------------------------
INSERT INTO `om_menu` VALUES ('1', '0', '人事部', 'menu/personnel_department', '1', '1', null, '2019-09-05 16:03:35', '2019-09-05 16:03:35');
INSERT INTO `om_menu` VALUES ('2', '0', '财务部', 'menu/accounting_department', '1', '1', null, '2019-09-05 16:03:35', '2019-09-05 16:03:35');
INSERT INTO `om_menu` VALUES ('3', '0', '行政部', 'menu/administrative_department', '1', '1', null, '2019-09-05 16:03:35', '2019-09-05 16:03:35');
INSERT INTO `om_menu` VALUES ('4', '0', '运维部', 'menu/operation_department', '1', '1', null, '2019-09-05 16:03:35', '2019-09-05 16:03:35');
INSERT INTO `om_menu` VALUES ('5', '0', '测试部', 'menu/test_department', '1', '1', null, '2019-09-05 16:03:35', '2019-09-05 16:03:35');
INSERT INTO `om_menu` VALUES ('6', '0', '市场部', 'menu/marketing_department', '1', '1', null, '2019-09-05 16:03:35', '2019-09-05 16:03:35');
