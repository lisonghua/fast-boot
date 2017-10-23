/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : sanji-boot-security

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-08-08 16:48:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_time` datetime DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `depth` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `skin` varchar(255) DEFAULT NULL,
  `sort` bigint(20) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2jrf4gb0gjqi8882gxytpxnhe` (`parent_id`),
  CONSTRAINT `FK2jrf4gb0gjqi8882gxytpxnhe` FOREIGN KEY (`parent_id`) REFERENCES `sys_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '2017-08-03 15:46:18', '2017-08-07 18:25:26', null, '0', null, '', 'ROOT', '', '', '0', null, null);
INSERT INTO `sys_menu` VALUES ('2', '2017-08-03 15:47:23', '2017-08-08 10:10:34', null, '1', null, 'zmdi zmdi-shield-security', '权限管理系统', '', 'skin-green', '2', '', '1');
INSERT INTO `sys_menu` VALUES ('3', '2017-08-03 15:47:47', '2017-08-08 10:10:34', null, '1', null, 'zmdi zmdi-wikipedia', '内容管理系统', '', 'skin-dark-blue', '22', '', '1');
INSERT INTO `sys_menu` VALUES ('4', '2017-08-03 15:48:08', '2017-08-08 10:10:34', null, '1', null, 'zmdi zmdi-paypal-alt', '支付管理系统', '', 'skin-pink', '32', '', '1');
INSERT INTO `sys_menu` VALUES ('5', '2017-08-03 15:48:26', '2017-08-08 10:10:34', null, '1', null, 'zmdi zmdi-account', '用户管理系统', '', 'skin-purple', '34', '', '1');
INSERT INTO `sys_menu` VALUES ('7', '2017-08-03 15:49:25', '2017-08-08 10:10:34', null, '2', null, 'zmdi zmdi-home', '首页', '', null, '23', 'home', '3');
INSERT INTO `sys_menu` VALUES ('8', '2017-08-03 15:49:45', '2017-08-08 10:10:34', null, '2', null, 'zmdi zmdi-accounts-list', '系统组织管理', '', null, '3', '', '2');
INSERT INTO `sys_menu` VALUES ('9', '2017-08-03 15:50:12', '2017-08-08 10:10:34', null, '3', null, 'zmdi zmdi-account', '系统管理(可以接入微服务)', '', null, '4', 'http://projects.spring.io/spring-cloud/', '8');
INSERT INTO `sys_menu` VALUES ('10', '2017-08-03 15:50:30', '2017-08-08 10:10:34', null, '2', null, 'zmdi zmdi-accounts', '角色用户管理', '', null, '7', '', '2');
INSERT INTO `sys_menu` VALUES ('11', '2017-08-03 15:50:50', '2017-08-08 10:10:34', null, '3', null, '', '用户管理', '', null, '8', 'page/sys/user/table.html', '10');
INSERT INTO `sys_menu` VALUES ('12', '2017-08-03 15:51:16', '2017-08-08 10:10:34', null, '3', null, '', '角色管理', '', null, '10', 'page/sys/role/table.html', '10');
INSERT INTO `sys_menu` VALUES ('13', '2017-08-03 15:51:38', '2017-08-08 10:10:34', null, '2', null, 'zmdi zmdi-lock-outline', '权限资源管理', '', null, '13', '', '2');
INSERT INTO `sys_menu` VALUES ('14', '2017-08-03 15:51:54', '2017-08-08 10:10:34', null, '3', null, '', '权限管理', '', null, '14', 'page/sys/menu/menu.html', '13');
INSERT INTO `sys_menu` VALUES ('15', '2017-08-03 15:52:14', '2017-08-08 10:10:34', null, '2', null, 'zmdi zmdi-more', '其他数据管理', '', null, '17', '', '2');
INSERT INTO `sys_menu` VALUES ('16', '2017-08-03 15:52:33', '2017-08-08 10:10:34', null, '3', null, '', '百度', '', null, '18', 'https://www.baidu.com/', '15');
INSERT INTO `sys_menu` VALUES ('17', '2017-08-08 09:59:07', '2017-08-08 10:10:34', null, '1', null, 'zmdi zmdi-assignment-o', '日志管理系统', '', 'skin-blue', '26', '', '1');
INSERT INTO `sys_menu` VALUES ('18', '2017-08-08 09:59:57', '2017-08-08 10:10:34', null, '2', null, 'zmdi zmdi-assignment ', '日志管理', '', 'skin-green', '27', '', '17');
INSERT INTO `sys_menu` VALUES ('19', '2017-08-08 10:08:08', '2017-08-08 10:10:34', null, '3', null, 'zmdi zmdi-dns', 'druid', '', null, '28', 'druid', '18');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_time` datetime DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `role_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('3', '2017-07-18 11:44:20', '2017-08-08 10:15:23', null, '778787', '用户管理员', 'user');
INSERT INTO `sys_role` VALUES ('4', '2017-07-18 11:47:10', '2017-08-08 10:15:17', null, '', '权限管理员', 'admin');
INSERT INTO `sys_role` VALUES ('5', '2017-08-07 18:32:48', '2017-08-07 18:32:48', null, '', '支付管理员', 'pay');
INSERT INTO `sys_role` VALUES ('6', '2017-08-08 10:08:43', '2017-08-08 10:08:43', null, '', '日志管理员', 'logger');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`),
  KEY `FKf3mud4qoc7ayew8nml4plkevo` (`menu_id`),
  CONSTRAINT `FKf3mud4qoc7ayew8nml4plkevo` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`id`),
  CONSTRAINT `FKkeitxsgxwayackgqllio4ohn5` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('3', '1');
INSERT INTO `sys_role_menu` VALUES ('4', '1');
INSERT INTO `sys_role_menu` VALUES ('5', '1');
INSERT INTO `sys_role_menu` VALUES ('6', '1');
INSERT INTO `sys_role_menu` VALUES ('4', '2');
INSERT INTO `sys_role_menu` VALUES ('5', '4');
INSERT INTO `sys_role_menu` VALUES ('3', '5');
INSERT INTO `sys_role_menu` VALUES ('4', '10');
INSERT INTO `sys_role_menu` VALUES ('4', '11');
INSERT INTO `sys_role_menu` VALUES ('4', '12');
INSERT INTO `sys_role_menu` VALUES ('4', '13');
INSERT INTO `sys_role_menu` VALUES ('4', '14');
INSERT INTO `sys_role_menu` VALUES ('4', '15');
INSERT INTO `sys_role_menu` VALUES ('4', '16');
INSERT INTO `sys_role_menu` VALUES ('6', '17');
INSERT INTO `sys_role_menu` VALUES ('6', '18');
INSERT INTO `sys_role_menu` VALUES ('6', '19');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_time` datetime DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `login_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_blyyljcvmmqokx6t10jvmcj98` (`login_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', null, '2017-08-08 10:08:52', null, 'admin', '$2a$10$QkslJHZDwgGhXzEd/4xBBehwDmMZxdUt8zKdCpgk04UNWgd5Ryfai', '0');
INSERT INTO `sys_user` VALUES ('2', '2017-07-18 09:47:09', '2017-08-08 10:12:52', null, 'root', '$2a$10$kZYdJQKnaVas2BYGC8Apy.TBg67sgKbFdkAxhtyAB5/MzXNZ0N1gy', '1');

-- ----------------------------
-- Table structure for sys_user_details
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_details`;
CREATE TABLE `sys_user_details` (
  `avatar` varchar(255) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `FKgcc0028pvs329h64e469ibgfm` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_details
-- ----------------------------
INSERT INTO `sys_user_details` VALUES ('admin', '2013-08-04 08:00:00', null, '15465456', 'admin', '(111) 1111-1111', '0', '1');
INSERT INTO `sys_user_details` VALUES ('root', '2091-03-30 08:00:00', null, '1456@gmail.com', 'root', '(789) 7987-8998', '1', '2');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKhh52n8vd4ny9ff4x9fb8v65qx` (`role_id`),
  CONSTRAINT `FKb40xxfch70f5qnyfw8yme1n1s` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FKhh52n8vd4ny9ff4x9fb8v65qx` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '3');
INSERT INTO `sys_user_role` VALUES ('1', '4');
INSERT INTO `sys_user_role` VALUES ('2', '4');
INSERT INTO `sys_user_role` VALUES ('1', '5');
INSERT INTO `sys_user_role` VALUES ('1', '6');
SET FOREIGN_KEY_CHECKS=1;
