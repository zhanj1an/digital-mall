/*
 Navicat Premium Data Transfer

 Source Server         : docker
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : digitalmall

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 19/02/2019 10:26:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for digital_mall_attribute
-- ----------------------------
DROP TABLE IF EXISTS `digital_mall_attribute`;
CREATE TABLE `digital_mall_attribute` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT '属性名称',
  `goods_id` int(10) NOT NULL COMMENT '商品id',
  `update_time` varchar(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of digital_mall_attribute
-- ----------------------------
BEGIN;
INSERT INTO `digital_mall_attribute` VALUES (1, '颜色', 1, '1548923928000');
INSERT INTO `digital_mall_attribute` VALUES (2, '内存', 1, '1548923928000');
INSERT INTO `digital_mall_attribute` VALUES (3, '屏幕尺寸', 1, '1548923928000');
COMMIT;

-- ----------------------------
-- Table structure for digital_mall_attribute_value
-- ----------------------------
DROP TABLE IF EXISTS `digital_mall_attribute_value`;
CREATE TABLE `digital_mall_attribute_value` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `attr_value` varchar(20) NOT NULL COMMENT '属性值',
  `attr_id` int(10) NOT NULL COMMENT '属性id',
  `goods_id` int(10) NOT NULL,
  `update_time` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of digital_mall_attribute_value
-- ----------------------------
BEGIN;
INSERT INTO `digital_mall_attribute_value` VALUES (1, '白色', 1, 1, '1548923928000');
INSERT INTO `digital_mall_attribute_value` VALUES (2, '深空灰', 1, 1, '1548923928000');
INSERT INTO `digital_mall_attribute_value` VALUES (3, '黑色', 1, 1, '1548923928000');
INSERT INTO `digital_mall_attribute_value` VALUES (4, '64G', 2, 1, '1548923928000');
INSERT INTO `digital_mall_attribute_value` VALUES (6, '256G', 2, 1, '1548923928000');
INSERT INTO `digital_mall_attribute_value` VALUES (7, '5.8英寸', 3, 1, '1548923928000');
COMMIT;

-- ----------------------------
-- Table structure for digital_mall_brand
-- ----------------------------
DROP TABLE IF EXISTS `digital_mall_brand`;
CREATE TABLE `digital_mall_brand` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '品牌id',
  `name` varchar(20) NOT NULL COMMENT '品牌名称',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of digital_mall_brand
-- ----------------------------
BEGIN;
INSERT INTO `digital_mall_brand` VALUES (1, '苹果(Apple)', '1548923928000');
INSERT INTO `digital_mall_brand` VALUES (2, '华为(HUAWEI)', '1548923928000');
INSERT INTO `digital_mall_brand` VALUES (3, '三星(SAMSUNG)', '154892392800');
INSERT INTO `digital_mall_brand` VALUES (4, '小米(MI)', '154892392800');
COMMIT;

-- ----------------------------
-- Table structure for digital_mall_category
-- ----------------------------
DROP TABLE IF EXISTS `digital_mall_category`;
CREATE TABLE `digital_mall_category` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '商品分类主键',
  `name` varchar(20) NOT NULL COMMENT '分类名称',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of digital_mall_category
-- ----------------------------
BEGIN;
INSERT INTO `digital_mall_category` VALUES (1, '笔记本电脑', '1548923928000');
INSERT INTO `digital_mall_category` VALUES (2, '手机', '1548923928000');
INSERT INTO `digital_mall_category` VALUES (3, '平板电脑', '1548923928000');
COMMIT;

-- ----------------------------
-- Table structure for digital_mall_category_brand
-- ----------------------------
DROP TABLE IF EXISTS `digital_mall_category_brand`;
CREATE TABLE `digital_mall_category_brand` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `category_id` int(10) NOT NULL COMMENT '分类id',
  `brand_id` int(10) NOT NULL COMMENT '品牌id',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_cid_bid` (`category_id`,`brand_id`) COMMENT '分类 品牌唯一索引'
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of digital_mall_category_brand
-- ----------------------------
BEGIN;
INSERT INTO `digital_mall_category_brand` VALUES (3, 2, 1, '1548923928000');
INSERT INTO `digital_mall_category_brand` VALUES (5, 1, 1, '1550042450989');
INSERT INTO `digital_mall_category_brand` VALUES (6, 2, 2, '1550487706238');
COMMIT;

-- ----------------------------
-- Table structure for digital_mall_goods
-- ----------------------------
DROP TABLE IF EXISTS `digital_mall_goods`;
CREATE TABLE `digital_mall_goods` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `category_id` int(10) NOT NULL COMMENT '商品分类id',
  `brand_id` int(10) NOT NULL COMMENT '商品品牌id',
  `image_url` varchar(100) NOT NULL COMMENT '商品图片url',
  `introduce` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品简介',
  `des_url` varchar(100) NOT NULL COMMENT '商品描述url',
  `sale_num` int(50) NOT NULL COMMENT '销量',
  `is_show` int(2) NOT NULL COMMENT '是否上架 1上架 0下架',
  `is_delete` int(2) NOT NULL COMMENT '是否删除 1删除 0没有删除',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of digital_mall_goods
-- ----------------------------
BEGIN;
INSERT INTO `digital_mall_goods` VALUES (24, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 0, 0, '1549960889756');
INSERT INTO `digital_mall_goods` VALUES (30, 'mac book pro', 1, 1, '\\upload\\macpro-1.png,\\upload\\macpro-2.png', 'Apple MacBook Pro 13.3英寸笔记本电脑 深空灰色 2018新款（四核八代i5 8G 256G固态硬盘 MR9Q2CH/A）', '\\upload\\macpro-des-1.jpg,\\upload\\macpro-des-2.jpg,\\upload\\macpro-des-3.jpg,\\upload\\macpro-des-4.jpg', 0, 0, 1, '1550042450864');
INSERT INTO `digital_mall_goods` VALUES (31, '测试555', 3, 3, '\\upload\\iphonex-3.jpg', '测试测试ccccc', '\\upload\\macpro-2.png', 0, 0, 1, '1550491537489');
COMMIT;

-- ----------------------------
-- Table structure for digital_mall_sku
-- ----------------------------
DROP TABLE IF EXISTS `digital_mall_sku`;
CREATE TABLE `digital_mall_sku` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `goods_id` int(10) NOT NULL COMMENT '商品id',
  `goods_name` varchar(20) NOT NULL COMMENT '商品名称',
  `attribute` varchar(100) NOT NULL COMMENT '属性',
  `price` double(10,2) NOT NULL COMMENT '价格',
  `stock` int(10) NOT NULL COMMENT '库存',
  `update_time` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
