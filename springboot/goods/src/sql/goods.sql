/*
Navicat MySQL Data Transfer

Source Server         : 192.168.2.200
Source Server Version : 50173
Source Host           : 192.168.2.200:3306
Source Database       : goods

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2016-09-30 12:10:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_goods`
-- ----------------------------
DROP TABLE IF EXISTS `tb_goods`;
CREATE TABLE `tb_goods` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL DEFAULT '',
  `price` float NOT NULL DEFAULT '0',
  `date` date NOT NULL,
  `num` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_goods
-- ----------------------------
INSERT INTO `tb_goods` VALUES ('1', '铅笔', '1', '2016-09-09', '120');
INSERT INTO `tb_goods` VALUES ('2', '钢笔', '5', '2016-09-02', '11');
INSERT INTO `tb_goods` VALUES ('3', '矿泉水', '1', '2016-09-28', '21');
