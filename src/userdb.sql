/*
 Navicat Premium Data Transfer

 Source Server         : userdb
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : localhost:3306
 Source Schema         : userdb

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 16/10/2020 20:43:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for menuInfo
-- ----------------------------
DROP TABLE IF EXISTS `menuInfo`;
CREATE TABLE `menuInfo` (
  `menuId` int(11) NOT NULL AUTO_INCREMENT,
  `menuName` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `menuPath` varchar(150) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`menuId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of menuInfo
-- ----------------------------
BEGIN;
INSERT INTO `menuInfo` VALUES (1, '用户管理', '/userServlet?type=0');
INSERT INTO `menuInfo` VALUES (2, '角色管理', '/roleServlet?type=0');
INSERT INTO `menuInfo` VALUES (3, '菜单管理', '/menuServlet?type=0');
INSERT INTO `menuInfo` VALUES (4, '权限管理', '/roleMenuServlet?type=0');
COMMIT;

-- ----------------------------
-- Table structure for roleInfo
-- ----------------------------
DROP TABLE IF EXISTS `roleInfo`;
CREATE TABLE `roleInfo` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of roleInfo
-- ----------------------------
BEGIN;
INSERT INTO `roleInfo` VALUES (1, '超级管理员');
INSERT INTO `roleInfo` VALUES (2, '普通用户');
INSERT INTO `roleInfo` VALUES (3, '系统管理员');
INSERT INTO `roleInfo` VALUES (4, '权限管理员');
INSERT INTO `roleInfo` VALUES (5, '用户管理员');
INSERT INTO `roleInfo` VALUES (6, '普通用户13');
INSERT INTO `roleInfo` VALUES (7, '普通用户14');
INSERT INTO `roleInfo` VALUES (8, '普通用户15');
INSERT INTO `roleInfo` VALUES (9, '普通用户16');
INSERT INTO `roleInfo` VALUES (10, '普通用户17');
INSERT INTO `roleInfo` VALUES (11, '普通用户18');
INSERT INTO `roleInfo` VALUES (12, '普通用户19');
INSERT INTO `roleInfo` VALUES (13, '普通用户20');
INSERT INTO `roleInfo` VALUES (14, '普通用户21');
INSERT INTO `roleInfo` VALUES (15, '普通用户22');
INSERT INTO `roleInfo` VALUES (16, '普通用户23');
INSERT INTO `roleInfo` VALUES (17, '普通用户24');
INSERT INTO `roleInfo` VALUES (18, '普通用户25');
INSERT INTO `roleInfo` VALUES (19, '普通用户26');
INSERT INTO `roleInfo` VALUES (20, '普通用户27');
INSERT INTO `roleInfo` VALUES (21, '普通用户28');
INSERT INTO `roleInfo` VALUES (22, '普通用户29');
INSERT INTO `roleInfo` VALUES (23, '普通用户30');
INSERT INTO `roleInfo` VALUES (24, '普通用户31');
INSERT INTO `roleInfo` VALUES (25, '普通用户32');
INSERT INTO `roleInfo` VALUES (26, '普通用户33');
INSERT INTO `roleInfo` VALUES (27, '普通用户34');
INSERT INTO `roleInfo` VALUES (28, '普通用户35');
INSERT INTO `roleInfo` VALUES (29, '普通用户36');
INSERT INTO `roleInfo` VALUES (30, '普通用户37');
INSERT INTO `roleInfo` VALUES (31, '普通用户38');
INSERT INTO `roleInfo` VALUES (32, '普通用户39');
COMMIT;

-- ----------------------------
-- Table structure for roleMenuInfo
-- ----------------------------
DROP TABLE IF EXISTS `roleMenuInfo`;
CREATE TABLE `roleMenuInfo` (
  `roleId` int(11) NOT NULL,
  `menuId` int(11) NOT NULL,
  PRIMARY KEY (`roleId`,`menuId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of roleMenuInfo
-- ----------------------------
BEGIN;
INSERT INTO `roleMenuInfo` VALUES (1, 1);
INSERT INTO `roleMenuInfo` VALUES (1, 2);
INSERT INTO `roleMenuInfo` VALUES (1, 3);
INSERT INTO `roleMenuInfo` VALUES (1, 4);
INSERT INTO `roleMenuInfo` VALUES (2, 1);
INSERT INTO `roleMenuInfo` VALUES (2, 2);
INSERT INTO `roleMenuInfo` VALUES (4, 4);
INSERT INTO `roleMenuInfo` VALUES (6, 1);
INSERT INTO `roleMenuInfo` VALUES (6, 2);
INSERT INTO `roleMenuInfo` VALUES (6, 3);
INSERT INTO `roleMenuInfo` VALUES (6, 4);
COMMIT;

-- ----------------------------
-- Table structure for userInfo
-- ----------------------------
DROP TABLE IF EXISTS `userInfo`;
CREATE TABLE `userInfo` (
  `userId` varchar(30) NOT NULL COMMENT '用户登录id',
  `userName` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  `userPassword` varchar(50) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`userId`),
  KEY `user_role_id` (`roleId`),
  CONSTRAINT `user_role_id` FOREIGN KEY (`roleId`) REFERENCES `roleInfo` (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of userInfo
-- ----------------------------
BEGIN;
INSERT INTO `userInfo` VALUES ('admin', '张三', 1, 1, '123456');
INSERT INTO `userInfo` VALUES ('user10', '李四', 1, 4, '111111');
INSERT INTO `userInfo` VALUES ('user11', '测试用户11', 1, 2, '111111');
INSERT INTO `userInfo` VALUES ('user12', '测试用户12', 0, 2, '111111');
INSERT INTO `userInfo` VALUES ('user13', '测试用户13', 1, 2, '111111');
INSERT INTO `userInfo` VALUES ('user14', '测试用户14', 0, 2, '111111');
INSERT INTO `userInfo` VALUES ('user15', '测试用户15', 1, 2, '111111');
INSERT INTO `userInfo` VALUES ('user16', '测试用户16', 0, 2, '111111');
INSERT INTO `userInfo` VALUES ('user17', '测试用户17', 1, 2, '111111');
INSERT INTO `userInfo` VALUES ('user18', '测试用户18', 0, 2, '111111');
INSERT INTO `userInfo` VALUES ('user19', '测试用户19', 1, 2, '111111');
INSERT INTO `userInfo` VALUES ('user20', '测试用户20', 0, 2, '111111');
INSERT INTO `userInfo` VALUES ('user21', '测试用户21', 1, 2, '111111');
INSERT INTO `userInfo` VALUES ('user22', '测试用户22', 0, 2, '111111');
INSERT INTO `userInfo` VALUES ('user23', '测试用户23', 1, 2, '111111');
INSERT INTO `userInfo` VALUES ('user24', '测试用户24', 0, 2, '111111');
INSERT INTO `userInfo` VALUES ('user25', '测试用户25', 1, 2, '111111');
INSERT INTO `userInfo` VALUES ('user26', '测试用户26', 0, 2, '111111');
INSERT INTO `userInfo` VALUES ('user27', '测试用户27', 1, 2, '111111');
INSERT INTO `userInfo` VALUES ('user28', '测试用户28', 0, 2, '111111');
INSERT INTO `userInfo` VALUES ('user29', '测试用户29', 1, 2, '111111');
INSERT INTO `userInfo` VALUES ('user30', '测试用户30', 0, 2, '111111');
INSERT INTO `userInfo` VALUES ('user31', '测试用户31', 1, 2, '111111');
INSERT INTO `userInfo` VALUES ('user32', '测试用户32', 0, 2, '111111');
INSERT INTO `userInfo` VALUES ('user33', '测试用户33', 1, 2, '111111');
INSERT INTO `userInfo` VALUES ('user34', '测试用户34', 0, 2, '111111');
INSERT INTO `userInfo` VALUES ('user35', '测试用户35', 1, 2, '111111');
INSERT INTO `userInfo` VALUES ('user36', '测试用户36', 0, 2, '111111');
INSERT INTO `userInfo` VALUES ('user37', '测试用户37', 1, 2, '111111');
INSERT INTO `userInfo` VALUES ('user38', '测试用户38', 0, 2, '111111');
INSERT INTO `userInfo` VALUES ('user39', '测试用户39', 1, 2, '111111');
INSERT INTO `userInfo` VALUES ('user40', '测试用户40', 0, 2, '111111');
INSERT INTO `userInfo` VALUES ('user41', '测试用户41', 1, 2, '111111');
INSERT INTO `userInfo` VALUES ('user42', '测试用户42', 0, 2, '111111');
INSERT INTO `userInfo` VALUES ('user43', '测试用户43', 1, 2, '111111');
INSERT INTO `userInfo` VALUES ('user44', '测试用户44', 0, 2, '111111');
INSERT INTO `userInfo` VALUES ('user45', '测试用户45', 1, 2, '111111');
INSERT INTO `userInfo` VALUES ('user46', '测试用户46', 0, 2, '111111');
INSERT INTO `userInfo` VALUES ('user47', '测试用户47', 1, 2, '111111');
INSERT INTO `userInfo` VALUES ('user48', '测试用户48', 0, 2, '111111');
INSERT INTO `userInfo` VALUES ('user49', '测试用户49', 1, 2, '111111');
INSERT INTO `userInfo` VALUES ('user50', '测试用户50', 0, 2, '111111');
INSERT INTO `userInfo` VALUES ('user51', '测试用户51', 1, 2, '111111');
INSERT INTO `userInfo` VALUES ('user52', '测试用户52', 0, 2, '111111');
INSERT INTO `userInfo` VALUES ('user53', '测试用户53', 1, 2, '111111');
INSERT INTO `userInfo` VALUES ('user54', '测试用户54', 0, 2, '111111');
INSERT INTO `userInfo` VALUES ('user55', '测试用户55', 1, 2, '111111');
INSERT INTO `userInfo` VALUES ('user56', '测试用户56', 0, 2, '111111');
INSERT INTO `userInfo` VALUES ('user57', '测试用户57', 1, 2, '111111');
INSERT INTO `userInfo` VALUES ('user58', '测试用户58', 0, 2, '111111');
INSERT INTO `userInfo` VALUES ('user59', '测试用户59', 1, 2, '111111');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
