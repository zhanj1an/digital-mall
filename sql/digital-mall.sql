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

 Date: 20/03/2019 15:00:41
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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of digital_mall_attribute
-- ----------------------------
BEGIN;
INSERT INTO `digital_mall_attribute` VALUES (8, '存储', 24, '1550739536823');
INSERT INTO `digital_mall_attribute` VALUES (9, '版本', 24, '1550739536823');
INSERT INTO `digital_mall_attribute` VALUES (10, '颜色', 24, '1550739536823');
INSERT INTO `digital_mall_attribute` VALUES (11, '屏幕尺寸', 24, '1550739536823');
INSERT INTO `digital_mall_attribute` VALUES (12, '屏幕尺寸', 30, '1552300453711');
INSERT INTO `digital_mall_attribute` VALUES (13, '处理器', 30, '1552300453711');
INSERT INTO `digital_mall_attribute` VALUES (14, '内存容量', 30, '1552300453711');
INSERT INTO `digital_mall_attribute` VALUES (15, '硬盘容量', 30, '1552300453711');
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_attrValue_attrId_goodsId` (`attr_value`,`attr_id`,`goods_id`) USING BTREE COMMENT '商品每个属性值唯一'
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of digital_mall_attribute_value
-- ----------------------------
BEGIN;
INSERT INTO `digital_mall_attribute_value` VALUES (16, '64G', 8, 24, '1550739536823');
INSERT INTO `digital_mall_attribute_value` VALUES (17, '128G', 8, 24, '1550739536823');
INSERT INTO `digital_mall_attribute_value` VALUES (18, '中国大陆版', 9, 24, '1550739536823');
INSERT INTO `digital_mall_attribute_value` VALUES (19, '港版', 9, 24, '1550739536823');
INSERT INTO `digital_mall_attribute_value` VALUES (20, '银白色', 10, 24, '1550739536823');
INSERT INTO `digital_mall_attribute_value` VALUES (21, '深空灰', 10, 24, '1550739536823');
INSERT INTO `digital_mall_attribute_value` VALUES (22, '黑色', 10, 24, '1550739536823');
INSERT INTO `digital_mall_attribute_value` VALUES (23, '5.8', 11, 24, '1550739536823');
INSERT INTO `digital_mall_attribute_value` VALUES (24, '13.3', 12, 30, '1552300453711');
INSERT INTO `digital_mall_attribute_value` VALUES (25, 'i5', 13, 30, '1552300453711');
INSERT INTO `digital_mall_attribute_value` VALUES (26, '8G', 14, 30, '1552300453711');
INSERT INTO `digital_mall_attribute_value` VALUES (27, '128G', 15, 30, '1552300453711');
INSERT INTO `digital_mall_attribute_value` VALUES (28, '256G', 15, 30, '1552300453711');
INSERT INTO `digital_mall_attribute_value` VALUES (29, '512G', 15, 30, '1552300453711');
INSERT INTO `digital_mall_attribute_value` VALUES (30, '15.4', 12, 30, '1552300596795');
INSERT INTO `digital_mall_attribute_value` VALUES (31, 'i7', 13, 30, '1552300596795');
INSERT INTO `digital_mall_attribute_value` VALUES (32, '16G', 14, 30, '1552300596795');
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
  `is_new` int(2) NOT NULL DEFAULT '0' COMMENT '是否为新品 1新品 0不是',
  `goods_rank` int(10) NOT NULL DEFAULT '0' COMMENT '商品前台检索排序 越大越靠前',
  `is_delete` int(2) NOT NULL COMMENT '是否删除 1删除 0没有删除',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=132 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of digital_mall_goods
-- ----------------------------
BEGIN;
INSERT INTO `digital_mall_goods` VALUES (24, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg,\\upload\\iphonex-4.png', 'iphonex 64G 256G 深空灰 黑色 白色 5.8英寸全面屏', '\\upload\\iphonex-des.jpg', 0, 1, 1, 100, 0, '1552550065328');
INSERT INTO `digital_mall_goods` VALUES (30, 'mac book pro', 1, 1, '\\upload\\macpro-1.png,\\upload\\macpro-2.png', 'Apple MacBook Pro 13.3英寸笔记本电脑 深空灰色 2018新款（四核八代i5 8G 256G固态硬盘 MR9Q2CH/A）', '\\upload\\macpro-des-1.jpg,\\upload\\macpro-des-2.jpg,\\upload\\macpro-des-3.jpg,\\upload\\macpro-des-4.jpg', 0, 1, 1, 99, 0, '1550042450864');
INSERT INTO `digital_mall_goods` VALUES (32, 'iphonex-2', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 1, 0, 98, 0, '1552012215229');
INSERT INTO `digital_mall_goods` VALUES (33, 'iphonex-3', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 0, 1, 4, 0, '1552012215346');
INSERT INTO `digital_mall_goods` VALUES (34, 'iphonex-4', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 0, 1, 10, 0, '1552012215360');
INSERT INTO `digital_mall_goods` VALUES (35, 'iphonex-5', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 0, 1, 6, 0, '1552012215373');
INSERT INTO `digital_mall_goods` VALUES (36, 'iphonex-6', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 0, 1, 2, 0, '1552012215385');
INSERT INTO `digital_mall_goods` VALUES (37, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 0, 1, 0, 0, '1552012215395');
INSERT INTO `digital_mall_goods` VALUES (38, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 0, 1, 0, 0, '1552012215408');
INSERT INTO `digital_mall_goods` VALUES (39, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 0, 0, 0, 0, '1552012215419');
INSERT INTO `digital_mall_goods` VALUES (40, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 0, 0, 0, 0, '1552012215434');
INSERT INTO `digital_mall_goods` VALUES (41, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 0, 0, 0, 0, '1552012215447');
INSERT INTO `digital_mall_goods` VALUES (42, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 0, 0, 0, 0, '1552012215462');
INSERT INTO `digital_mall_goods` VALUES (43, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 0, 0, 0, 0, '1552012215474');
INSERT INTO `digital_mall_goods` VALUES (44, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 0, 0, 0, 0, '1552012215487');
INSERT INTO `digital_mall_goods` VALUES (45, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 0, 0, 0, 0, '1552012215499');
INSERT INTO `digital_mall_goods` VALUES (46, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 0, 0, 0, 0, '1552012215511');
INSERT INTO `digital_mall_goods` VALUES (47, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 0, 0, 0, 0, '1552012215535');
INSERT INTO `digital_mall_goods` VALUES (48, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 0, 0, 0, 0, '1552012215549');
INSERT INTO `digital_mall_goods` VALUES (49, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 0, 0, 0, 0, '1552012215563');
INSERT INTO `digital_mall_goods` VALUES (50, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 0, 0, 0, 0, '1552012215574');
INSERT INTO `digital_mall_goods` VALUES (51, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 0, 0, 0, 0, '1552012215585');
INSERT INTO `digital_mall_goods` VALUES (52, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 0, 0, 0, 0, '1552012215598');
INSERT INTO `digital_mall_goods` VALUES (53, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 0, 0, 0, 0, '1552012215609');
INSERT INTO `digital_mall_goods` VALUES (54, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 0, 0, 0, 0, '1552012215620');
INSERT INTO `digital_mall_goods` VALUES (55, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 0, 0, 0, 0, '1552012215630');
INSERT INTO `digital_mall_goods` VALUES (56, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 0, 0, 0, 0, '1552012215639');
INSERT INTO `digital_mall_goods` VALUES (57, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 1, 0, 0, 0, '1552012215649');
INSERT INTO `digital_mall_goods` VALUES (58, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 1, 0, 0, 0, '1552012215661');
INSERT INTO `digital_mall_goods` VALUES (59, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 1, 0, 0, 0, '1552012215673');
INSERT INTO `digital_mall_goods` VALUES (60, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 1, 0, 0, 0, '1552012215686');
INSERT INTO `digital_mall_goods` VALUES (61, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 1, 0, 0, 0, '1552012215697');
INSERT INTO `digital_mall_goods` VALUES (62, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 1, 0, 0, 0, '1552012215710');
INSERT INTO `digital_mall_goods` VALUES (63, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 1, 0, 0, 0, '1552012215720');
INSERT INTO `digital_mall_goods` VALUES (64, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 1, 0, 0, 0, '1552012215731');
INSERT INTO `digital_mall_goods` VALUES (65, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 1, 0, 0, 0, '1552012215742');
INSERT INTO `digital_mall_goods` VALUES (66, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 1, 0, 0, 0, '1552012215753');
INSERT INTO `digital_mall_goods` VALUES (67, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 1, 0, 0, 0, '1552012215767');
INSERT INTO `digital_mall_goods` VALUES (68, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 1, 0, 0, 0, '1552012215778');
INSERT INTO `digital_mall_goods` VALUES (69, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 1, 0, 0, 0, '1552012215789');
INSERT INTO `digital_mall_goods` VALUES (70, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 1, 0, 0, 0, '1552012215799');
INSERT INTO `digital_mall_goods` VALUES (71, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 1, 0, 0, 0, '1552012215809');
INSERT INTO `digital_mall_goods` VALUES (72, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 1, 0, 0, 0, '1552012215822');
INSERT INTO `digital_mall_goods` VALUES (73, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 1, 0, 0, 0, '1552012215837');
INSERT INTO `digital_mall_goods` VALUES (74, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 1, 0, 0, 0, '1552012215850');
INSERT INTO `digital_mall_goods` VALUES (75, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 1, 0, 0, 0, '1552012215861');
INSERT INTO `digital_mall_goods` VALUES (76, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 1, 0, 0, 0, '1552012215872');
INSERT INTO `digital_mall_goods` VALUES (77, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 1, 0, 0, 0, '1552012215882');
INSERT INTO `digital_mall_goods` VALUES (78, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 1, 0, 0, 0, '1552012215897');
INSERT INTO `digital_mall_goods` VALUES (79, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 1, 0, 0, 0, '1552012215907');
INSERT INTO `digital_mall_goods` VALUES (80, 'iphonex', 2, 1, '\\upload\\iphonex-1.jpg,\\upload\\iphonex-2.jpg,\\upload\\iphonex-3.jpg', 'iphonex 64G 256G 深空灰 黑色 白色', '\\upload\\iphonex-des.jpg', 0, 1, 0, 0, 0, '1552012215920');
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
  `old_price` double(10,2) NOT NULL DEFAULT '0.00' COMMENT '旧的价格 用来计算价格优惠',
  `price` double(10,2) NOT NULL COMMENT '价格',
  `stock` int(10) NOT NULL COMMENT '库存',
  `is_delete` int(11) NOT NULL COMMENT '1删除 0没删',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of digital_mall_sku
-- ----------------------------
BEGIN;
INSERT INTO `digital_mall_sku` VALUES (13, 24, 'iphonex', '64G,中国大陆版,银白色,5.8', 7560.00, 6300.00, 6399, 0, '1550739536823');
INSERT INTO `digital_mall_sku` VALUES (14, 24, 'iphonex', '64G,中国大陆版,深空灰,5.8', 0.00, 6401.00, 10, 0, '1550739536823');
INSERT INTO `digital_mall_sku` VALUES (15, 24, 'iphonex', '64G,中国大陆版,黑色,5.8', 0.00, 6402.00, 1000, 0, '1550739536823');
INSERT INTO `digital_mall_sku` VALUES (16, 24, 'iphonex', '64G,港版,银白色,5.8', 0.00, 6000.00, 1000, 0, '1550739536823');
INSERT INTO `digital_mall_sku` VALUES (17, 24, 'iphonex', '64G,港版,深空灰,5.8', 0.00, 6001.00, 1000, 0, '1550739536823');
INSERT INTO `digital_mall_sku` VALUES (18, 24, 'iphonex', '64G,港版,黑色,5.8', 0.00, 6002.00, 1000, 0, '1550739536823');
INSERT INTO `digital_mall_sku` VALUES (19, 24, 'iphonex', '128G,中国大陆版,银白色,5.8', 0.00, 7400.00, 1000, 0, '1550739536823');
INSERT INTO `digital_mall_sku` VALUES (20, 24, 'iphonex', '128G,中国大陆版,深空灰,5.8', 0.00, 7401.00, 1000, 0, '1550739536823');
INSERT INTO `digital_mall_sku` VALUES (21, 24, 'iphonex', '128G,中国大陆版,黑色,5.8', 0.00, 7402.00, 1000, 0, '1550739536823');
INSERT INTO `digital_mall_sku` VALUES (22, 24, 'iphonex', '128G,港版,银白色,5.8', 0.00, 7000.00, 1000, 0, '1550739536823');
INSERT INTO `digital_mall_sku` VALUES (23, 24, 'iphonex', '128G,港版,深空灰,5.8', 0.00, 7001.00, 1000, 0, '1550739536823');
INSERT INTO `digital_mall_sku` VALUES (24, 24, 'iphonex', '128G,港版,黑色,5.8', 0.00, 7002.00, 1000, 0, '1550739536823');
INSERT INTO `digital_mall_sku` VALUES (25, 30, 'mac book pro', '13.3,i5,8G,128G', 0.00, 10189.00, 0, 0, '1552300453711');
INSERT INTO `digital_mall_sku` VALUES (26, 30, 'mac book pro', '13.3,i5,8G,256G', 0.00, 11775.00, 0, 0, '1552300453711');
INSERT INTO `digital_mall_sku` VALUES (27, 30, 'mac book pro', '13.3,i5,8G,512G', 0.00, 14177.00, 0, 0, '1552300453711');
INSERT INTO `digital_mall_sku` VALUES (28, 30, 'mac book pro', '15.4,i7,16G,256G', 0.00, 18677.00, 0, 0, '1552300596795');
INSERT INTO `digital_mall_sku` VALUES (29, 30, 'mac book pro', '15.4,i7,16G,512G', 0.00, 21977.00, 0, 0, '1552300596795');
INSERT INTO `digital_mall_sku` VALUES (30, 32, '测试1', '测试', 0.00, 2000.00, 0, 0, '1552300971794');
INSERT INTO `digital_mall_sku` VALUES (31, 33, '测试2', '测试', 0.00, 3000.00, 0, 0, '1552300972195');
INSERT INTO `digital_mall_sku` VALUES (32, 34, '测试3', '测试', 0.00, 4000.00, 0, 0, '1552300972208');
INSERT INTO `digital_mall_sku` VALUES (33, 35, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972221');
INSERT INTO `digital_mall_sku` VALUES (34, 36, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972234');
INSERT INTO `digital_mall_sku` VALUES (35, 37, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972246');
INSERT INTO `digital_mall_sku` VALUES (36, 38, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972260');
INSERT INTO `digital_mall_sku` VALUES (37, 39, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972276');
INSERT INTO `digital_mall_sku` VALUES (38, 40, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972291');
INSERT INTO `digital_mall_sku` VALUES (39, 41, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972306');
INSERT INTO `digital_mall_sku` VALUES (40, 42, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972329');
INSERT INTO `digital_mall_sku` VALUES (41, 43, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972345');
INSERT INTO `digital_mall_sku` VALUES (42, 44, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972360');
INSERT INTO `digital_mall_sku` VALUES (43, 45, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972375');
INSERT INTO `digital_mall_sku` VALUES (44, 46, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972386');
INSERT INTO `digital_mall_sku` VALUES (45, 47, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972395');
INSERT INTO `digital_mall_sku` VALUES (46, 48, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972405');
INSERT INTO `digital_mall_sku` VALUES (47, 49, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972415');
INSERT INTO `digital_mall_sku` VALUES (48, 50, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972425');
INSERT INTO `digital_mall_sku` VALUES (49, 51, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972434');
INSERT INTO `digital_mall_sku` VALUES (50, 52, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972444');
INSERT INTO `digital_mall_sku` VALUES (51, 53, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972454');
INSERT INTO `digital_mall_sku` VALUES (52, 54, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972475');
INSERT INTO `digital_mall_sku` VALUES (53, 55, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972495');
INSERT INTO `digital_mall_sku` VALUES (54, 56, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972514');
INSERT INTO `digital_mall_sku` VALUES (55, 57, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972528');
INSERT INTO `digital_mall_sku` VALUES (56, 58, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972547');
INSERT INTO `digital_mall_sku` VALUES (57, 59, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972564');
INSERT INTO `digital_mall_sku` VALUES (58, 60, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972581');
INSERT INTO `digital_mall_sku` VALUES (59, 61, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972595');
INSERT INTO `digital_mall_sku` VALUES (60, 62, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972604');
INSERT INTO `digital_mall_sku` VALUES (61, 63, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972615');
INSERT INTO `digital_mall_sku` VALUES (62, 64, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972625');
INSERT INTO `digital_mall_sku` VALUES (63, 65, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972635');
INSERT INTO `digital_mall_sku` VALUES (64, 66, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972648');
INSERT INTO `digital_mall_sku` VALUES (65, 67, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972661');
INSERT INTO `digital_mall_sku` VALUES (66, 68, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972679');
INSERT INTO `digital_mall_sku` VALUES (67, 69, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972694');
INSERT INTO `digital_mall_sku` VALUES (68, 70, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972711');
INSERT INTO `digital_mall_sku` VALUES (69, 71, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972724');
INSERT INTO `digital_mall_sku` VALUES (70, 72, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972734');
INSERT INTO `digital_mall_sku` VALUES (71, 73, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972747');
INSERT INTO `digital_mall_sku` VALUES (72, 74, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972763');
INSERT INTO `digital_mall_sku` VALUES (73, 75, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972779');
INSERT INTO `digital_mall_sku` VALUES (74, 76, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972792');
INSERT INTO `digital_mall_sku` VALUES (75, 77, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972804');
INSERT INTO `digital_mall_sku` VALUES (76, 78, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972813');
INSERT INTO `digital_mall_sku` VALUES (77, 79, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972827');
INSERT INTO `digital_mall_sku` VALUES (78, 80, '测试', '测试', 0.00, 6000.00, 0, 0, '1552300972839');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
