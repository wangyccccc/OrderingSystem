/*
 Navicat Premium Data Transfer

 Source Server         : dasd
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : db_food

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 04/07/2023 15:51:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_cart
-- ----------------------------
DROP TABLE IF EXISTS `tb_cart`;
CREATE TABLE `tb_cart`  (
  `cid` int(0) NOT NULL AUTO_INCREMENT COMMENT '购物车编号,自增,主键',
  `gid` int(0) NULL DEFAULT NULL COMMENT '用户编号',
  `uuid` int(0) NULL DEFAULT NULL COMMENT '菜品编号',
  `gcount` int(0) NULL DEFAULT NULL COMMENT '菜品数量',
  `cprice` double NULL DEFAULT NULL COMMENT '菜品价格小计',
  `ctime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '购物车表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_collect
-- ----------------------------
DROP TABLE IF EXISTS `tb_collect`;
CREATE TABLE `tb_collect`  (
  `collectid` int(0) NOT NULL AUTO_INCREMENT COMMENT '收藏编号,自增,主键',
  `uuid` int(0) NULL DEFAULT NULL COMMENT '用户编号',
  `gname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜品姓名',
  `gprice` double NULL DEFAULT NULL COMMENT '菜品价格',
  `gkc` int(0) NULL DEFAULT NULL COMMENT '菜品库存',
  `collecttime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '收藏时间,系统默认时间',
  `gpath` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜品图片路径',
  PRIMARY KEY (`collectid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '收藏表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_goods
-- ----------------------------
DROP TABLE IF EXISTS `tb_goods`;
CREATE TABLE `tb_goods`  (
  `gid` int(0) NOT NULL AUTO_INCREMENT COMMENT '菜品编号',
  `gname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜品名称',
  `tname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜品类别',
  `gprice` double NULL DEFAULT NULL COMMENT '价格',
  `gkc` int(0) NULL DEFAULT 10 COMMENT '库存',
  `gxl` int(0) NULL DEFAULT 0 COMMENT '销量',
  `gtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `gpath` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片路径',
  `ginfo` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜品信息',
  PRIMARY KEY (`gid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜品表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_goods
-- ----------------------------
INSERT INTO `tb_goods` VALUES (31, 'Secret Forest', 'Alien', 21, 9, 1, '2023-06-05 22:28:30', 'C:\\Users\\14912\\Desktop\\image\\图片1.jpg', '超好喝！！！');
INSERT INTO `tb_goods` VALUES (32, 'Macaroon Alien', 'Alien', 10, 8, 2, '2023-06-05 22:30:27', 'C:\\Users\\14912\\Desktop\\image\\图片2.jpg', 'biscuits');
INSERT INTO `tb_goods` VALUES (33, 'Alien’s Salad', 'Alien', 28, 6, 4, '2023-06-05 22:33:08', 'C:\\Users\\14912\\Desktop\\image\\图片3.jpg', 'Salad');
INSERT INTO `tb_goods` VALUES (35, 'Sweetheart', 'Lotso', 21, 10, 0, '2023-06-05 22:34:26', 'C:\\Users\\14912\\Desktop\\image\\图片5.jpg', 'Drink');
INSERT INTO `tb_goods` VALUES (36, 'Macaroon Lotso', 'Lotso', 10, 10, 0, '2023-06-05 22:35:17', 'C:\\Users\\14912\\Desktop\\image\\图片6.jpg', 'biscuits');
INSERT INTO `tb_goods` VALUES (37, 'Red Velvet', 'Lotso', 32, 10, 0, '2023-06-05 22:35:47', 'C:\\Users\\14912\\Desktop\\image\\图片7.jpg', 'Cake');
INSERT INTO `tb_goods` VALUES (38, 'Lotso ’s Ice-cream', 'Lotso', 52, 10, 0, '2023-06-05 22:36:17', 'C:\\Users\\14912\\Desktop\\image\\图片8.jpg', 'Ice-cream');
INSERT INTO `tb_goods` VALUES (39, 'Birthday Lotso（Customizable）', 'Lotso', 188, 10, 0, '2023-06-05 22:36:57', 'C:\\Users\\14912\\Desktop\\image\\图片9.jpg', 'Cake');
INSERT INTO `tb_goods` VALUES (40, 'Blue Confessions', 'Buzz', 21, 10, 0, '2023-06-05 22:37:26', 'C:\\Users\\14912\\Desktop\\image\\图片10.jpg', 'Drink');
INSERT INTO `tb_goods` VALUES (41, 'Macaroon Buzz', 'Buzz', 10, 10, 0, '2023-06-05 22:38:15', 'C:\\Users\\14912\\Desktop\\image\\图片11.jpg', 'biscuits');
INSERT INTO `tb_goods` VALUES (42, 'Buzz’s Dim sum', 'Buzz', 32, 10, 0, '2023-06-05 22:38:40', 'C:\\Users\\14912\\Desktop\\image\\图片12.jpg', 'Cake');
INSERT INTO `tb_goods` VALUES (43, 'Birthday Buzz', 'Buzz', 188, 10, 0, '2023-06-05 22:39:13', 'C:\\Users\\14912\\Desktop\\image\\图片13.jpg', 'Cake');
INSERT INTO `tb_goods` VALUES (44, 'Golden Beach', 'Woody', 21, 10, 0, '2023-06-05 22:39:41', 'C:\\Users\\14912\\Desktop\\image\\图片14.jpg', 'Drink');
INSERT INTO `tb_goods` VALUES (45, 'Macaroon Woody ', 'Woody', 10, 10, 0, '2023-06-05 22:40:08', 'C:\\Users\\14912\\Desktop\\image\\图片15.jpg', 'cake');
INSERT INTO `tb_goods` VALUES (46, 'Mango Mousse', 'Woody', 32, 10, 0, '2023-06-05 22:40:36', 'C:\\Users\\14912\\Desktop\\image\\图片16.jpg', 'cake');
INSERT INTO `tb_goods` VALUES (48, 'Birthday Woody (Customizable)', 'Woody', 188, 10, 0, '2023-06-05 22:41:30', 'C:\\Users\\14912\\Desktop\\image\\图片17.jpg', 'cake');
INSERT INTO `tb_goods` VALUES (49, 'Family portraits Macaroon', 'BUCKET', 59, 10, 0, '2023-06-05 22:42:08', 'C:\\Users\\14912\\Desktop\\image\\图片18.jpg', 'biscuits');
INSERT INTO `tb_goods` VALUES (50, 'Birthday All Of Us', 'BUCKET', 188, 10, 0, '2023-06-05 22:42:36', 'C:\\Users\\14912\\Desktop\\image\\图片19.jpg', 'cake');
INSERT INTO `tb_goods` VALUES (51, 'Take a Party', 'BUCKET', 288, 10, 0, '2023-06-05 22:43:07', 'C:\\Users\\14912\\Desktop\\image\\图片20.jpg', 'Party!!!');
INSERT INTO `tb_goods` VALUES (52, 'Puzzle', 'TOYS', 30, 10, 0, '2023-06-05 22:43:32', 'C:\\Users\\14912\\Desktop\\image\\图片21.jpg', 'Puzzle');
INSERT INTO `tb_goods` VALUES (53, 'Light', 'TOYS', 99, 10, 0, '2023-06-05 22:43:59', 'C:\\Users\\14912\\Desktop\\image\\图片22.jpg', 'Light!!!');
INSERT INTO `tb_goods` VALUES (54, 'Listening', 'TOYS', 128, 10, 0, '2023-06-05 22:44:43', 'C:\\Users\\14912\\Desktop\\image\\图片23.jpg', ' Listening!!!');
INSERT INTO `tb_goods` VALUES (55, 'Blind Box', 'TOYS', 69, 10, 0, '2023-06-05 22:45:13', 'C:\\Users\\14912\\Desktop\\image\\图片24.jpg', '真的不喜欢');

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order`  (
  `ooid` int(0) NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单地址',
  `orderprice` double NULL DEFAULT NULL COMMENT '订单总价',
  `uuid` int(0) NULL DEFAULT NULL COMMENT '用户id',
  `otime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
  PRIMARY KEY (`ooid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES (14, '123', 0, 11, '2023-06-07 02:01:18');
INSERT INTO `tb_order` VALUES (15, '123', 66, 11, '2023-06-07 02:03:13');
INSERT INTO `tb_order` VALUES (16, '123', 87, 11, '2023-06-07 15:40:48');

-- ----------------------------
-- Table structure for tb_orderitem
-- ----------------------------
DROP TABLE IF EXISTS `tb_orderitem`;
CREATE TABLE `tb_orderitem`  (
  `otid` int(0) NOT NULL AUTO_INCREMENT COMMENT '订单项编号',
  `ooid` int(0) NULL DEFAULT NULL COMMENT '订单编号',
  `gid` int(0) NULL DEFAULT NULL COMMENT '商品编号',
  `gcount` int(0) NULL DEFAULT NULL COMMENT '商品数量',
  `orderItemPrice` double NULL DEFAULT NULL COMMENT '单个订单项的总价',
  `ottime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单项时间',
  PRIMARY KEY (`otid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_orderitem
-- ----------------------------
INSERT INTO `tb_orderitem` VALUES (23, 15, 33, 2, 56, '2023-06-07 02:03:13');
INSERT INTO `tb_orderitem` VALUES (24, 15, 32, 1, 10, '2023-06-07 02:03:13');
INSERT INTO `tb_orderitem` VALUES (25, 16, 31, 1, 21, '2023-06-07 15:40:48');
INSERT INTO `tb_orderitem` VALUES (26, 16, 32, 1, 10, '2023-06-07 15:40:48');
INSERT INTO `tb_orderitem` VALUES (27, 16, 33, 2, 56, '2023-06-07 15:40:48');

-- ----------------------------
-- Table structure for tb_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_type`;
CREATE TABLE `tb_type`  (
  `tid` int(0) NOT NULL AUTO_INCREMENT COMMENT '菜品类别编号,自增,主键',
  `tname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜品类别名称',
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜品类别表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_type
-- ----------------------------
INSERT INTO `tb_type` VALUES (1, 'Alien');
INSERT INTO `tb_type` VALUES (2, 'Lotso');
INSERT INTO `tb_type` VALUES (3, 'Buzz');
INSERT INTO `tb_type` VALUES (4, 'Woody');
INSERT INTO `tb_type` VALUES (5, 'BUCKET');
INSERT INTO `tb_type` VALUES (6, 'TOYS');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `uuid` int(0) NOT NULL AUTO_INCREMENT COMMENT '用户编号,自增,主键',
  `account` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户账号',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `sex` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户性别',
  `age` int(0) NULL DEFAULT NULL COMMENT '用户年龄',
  `tel` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户电话',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户地址',
  `hobby` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户爱好',
  `imagepath` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `power` int(0) NULL DEFAULT 3 COMMENT '用户权限,1管理员,2会员,3,普通用户,默认为3',
  `registertime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '用户注册时间',
  `info` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户信息',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员表&用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (11, '123', '123', 'wyc', '男', 18, '123', '123', '吃饭 ', '', 2, '2023-05-30 23:27:03', '123');
INSERT INTO `tb_user` VALUES (12, '123', '123', '123', '男', 12, '123', '123', '吃饭 ', '', 3, '2023-06-02 16:19:16', '123');
INSERT INTO `tb_user` VALUES (13, '1234', '1234', '1234', '男', 1234, '1234', '1234', '吃饭 ', 'C:\\Users\\14912\\Pictures\\Screenshots\\屏幕截图_20221226_113145.png', 1, '2023-06-04 12:05:13', '1234');

SET FOREIGN_KEY_CHECKS = 1;
