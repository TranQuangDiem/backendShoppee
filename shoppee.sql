/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 100406
 Source Host           : localhost:3306
 Source Schema         : shoppee

 Target Server Type    : MySQL
 Target Server Version : 100406
 File Encoding         : 65001

 Date: 06/08/2021 16:32:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for brand
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of brand
-- ----------------------------
INSERT INTO `brand` VALUES (1, 1, 'quần áo');
INSERT INTO `brand` VALUES (2, 1, 'giày dép');
INSERT INTO `brand` VALUES (3, 1, 'thiết bị điện tử');

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `total_item` double NOT NULL,
  `total_price` double NOT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKl70asp4l4w0jmbm1tqyofho4o`(`user_id`) USING BTREE,
  CONSTRAINT `FKl70asp4l4w0jmbm1tqyofho4o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cart_cart_item
-- ----------------------------
DROP TABLE IF EXISTS `cart_cart_item`;
CREATE TABLE `cart_cart_item`  (
  `cart_id` bigint(20) NOT NULL,
  `cart_item_id` bigint(20) NOT NULL,
  INDEX `FKkgv5sxiejo1wfoyv3e2sejmgq`(`cart_item_id`) USING BTREE,
  INDEX `FKi4h5hjukd104538a2ugf4dgne`(`cart_id`) USING BTREE,
  CONSTRAINT `FKi4h5hjukd104538a2ugf4dgne` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKkgv5sxiejo1wfoyv3e2sejmgq` FOREIGN KEY (`cart_item_id`) REFERENCES `cart_item` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cart_item
-- ----------------------------
DROP TABLE IF EXISTS `cart_item`;
CREATE TABLE `cart_item`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `product_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKjcyd5wv4igqnw413rgxbfu4nv`(`product_id`) USING BTREE,
  CONSTRAINT `FKjcyd5wv4igqnw413rgxbfu4nv` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for color
-- ----------------------------
DROP TABLE IF EXISTS `color`;
CREATE TABLE `color`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `color_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of color
-- ----------------------------
INSERT INTO `color` VALUES (1, 'xanh');
INSERT INTO `color` VALUES (2, 'đỏ');
INSERT INTO `color` VALUES (3, 'vàng');
INSERT INTO `color` VALUES (4, 'gray');

-- ----------------------------
-- Table structure for color_size
-- ----------------------------
DROP TABLE IF EXISTS `color_size`;
CREATE TABLE `color_size`  (
  `color_id` bigint(20) NOT NULL,
  `size_id` bigint(20) NOT NULL,
  INDEX `FK7f8kdjve9lse717nxmijnt81c`(`size_id`) USING BTREE,
  INDEX `FK838msnq4moo2iivfs7tm5h2d`(`color_id`) USING BTREE,
  CONSTRAINT `FK7f8kdjve9lse717nxmijnt81c` FOREIGN KEY (`size_id`) REFERENCES `size` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK838msnq4moo2iivfs7tm5h2d` FOREIGN KEY (`color_id`) REFERENCES `color` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of color_size
-- ----------------------------
INSERT INTO `color_size` VALUES (1, 1);
INSERT INTO `color_size` VALUES (1, 2);
INSERT INTO `color_size` VALUES (4, 1);
INSERT INTO `color_size` VALUES (4, 2);
INSERT INTO `color_size` VALUES (3, 3);
INSERT INTO `color_size` VALUES (2, 1);
INSERT INTO `color_size` VALUES (2, 2);
INSERT INTO `color_size` VALUES (3, 2);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` int(11) NOT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `date` date NULL DEFAULT NULL,
  `idproduct` bigint(20) NOT NULL,
  `rate` int(11) NOT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK8kcum44fvpupyw6f5baccx25c`(`user_id`) USING BTREE,
  CONSTRAINT `FK8kcum44fvpupyw6f5baccx25c` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 1, 'sản phẩm rất tốt', '2021-08-02', 1, 4, 1);
INSERT INTO `comment` VALUES (2, 1, 'sản phẩm rất đẹp', '2021-08-03', 1, 5, 2);

-- ----------------------------
-- Table structure for image
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of image
-- ----------------------------
INSERT INTO `image` VALUES (1, 'https://cf.shopee.vn/file/9b5747a56d17b5dae20b28fe70f184a3', 'dfghjk');
INSERT INTO `image` VALUES (2, 'https://cf.shopee.vn/file/5062a9290e6394d31b84b4c896b64695', 'local');
INSERT INTO `image` VALUES (3, 'https://cf.shopee.vn/file/94e33b205271577604e842557b60b6b0', NULL);
INSERT INTO `image` VALUES (4, 'https://cf.shopee.vn/file/9b5747a56d17b5dae20b28fe70f184a3', NULL);
INSERT INTO `image` VALUES (5, 'https://cf.shopee.vn/file/5062a9290e6394d31b84b4c896b64695', NULL);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` int(1) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` double NOT NULL,
  `quantity_sold` int(11) NOT NULL,
  `rate` double NOT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `brand_id` bigint(20) NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `sale` double NOT NULL,
  `sale_price` double NOT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKs6cydsualtsrprvlf2bb3lcam`(`brand_id`) USING BTREE,
  CONSTRAINT `FKs6cydsualtsrprvlf2bb3lcam` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, 1, 'LEVENTS SWEATER 0.5 FLORAL/ GREY', 100000, 2, 4.5, 'phổ biến', 1, '2021-07-12', 20, 80000, NULL);
INSERT INTO `product` VALUES (2, 1, 'LEVENTS SWEATER 0.5 FLORAL/ GREY', 500000, 0, 5, NULL, 3, '2021-07-26', 9, 300000, NULL);
INSERT INTO `product` VALUES (3, 1, 'LEVENTS SWEATER 0.5 FLORAL/ GREY', 300000, 13, 3, 'phổ biến', 1, '2021-06-08', 12, 300000, NULL);
INSERT INTO `product` VALUES (4, 1, 'LEVENTS SWEATER 0.5 FLORAL/ GREY', 249000, 6, 4.2, NULL, 3, '2021-07-05', 16, 249000, NULL);
INSERT INTO `product` VALUES (5, 1, 'LEVENTS SWEATER 0.5 FLORAL/ GREY', 490000, 4, 2.8, 'phổ biến', 1, '2021-06-29', 28, 490000, NULL);
INSERT INTO `product` VALUES (6, 1, 'LEVENTS SWEATER 0.5 FLORAL/ GREY', 199000, 5, 4, NULL, 3, '2021-07-19', 5, 199000, NULL);
INSERT INTO `product` VALUES (7, 1, 'LEVENTS SWEATER 0.5 FLORAL/ GREY', 150000, 9, 1, NULL, 1, '2021-07-14', 10, 150000, NULL);
INSERT INTO `product` VALUES (8, 1, 'LEVENTS SWEATER 0.5 FLORAL/ GREY', 490000, 1, 1, 'phổ biến', 2, '2021-07-01', 0, 490000, NULL);
INSERT INTO `product` VALUES (9, 1, 'LEVENTS SWEATER 0.5 FLORAL/ GREY', 750000, 0, 3, 'phổ biến', 3, '2021-07-12', 0, 750000, NULL);
INSERT INTO `product` VALUES (10, 1, 'LEVENTS SWEATER 0.5 FLORAL/ GREY', 649000, 3, 2.4, NULL, 2, '2021-07-25', 7, 649000, NULL);
INSERT INTO `product` VALUES (11, 1, 'LEVENTS SWEATER 0.5 FLORAL/ GREY', 490000, 7, 3.6, NULL, 3, '2021-07-29', 7, 490000, NULL);
INSERT INTO `product` VALUES (12, 1, 'LEVENTS SWEATER 0.5 FLORAL/ GREY', 99000, 4, 5, 'phổ biến', 2, '2021-07-13', 7, 99000, NULL);
INSERT INTO `product` VALUES (13, 1, 'LEVENTS SWEATER 0.5 FLORAL/ GREY', 490000, 5, 5, NULL, 2, '2021-07-22', 7, 490000, NULL);
INSERT INTO `product` VALUES (14, 1, 'LEVENTS SWEATER 0.5 FLORAL/ GREY', 320000, 9, 5, 'phổ biến', 2, '2021-07-31', 7, 320000, NULL);
INSERT INTO `product` VALUES (15, 1, 'LEVENTS SWEATER 0.5 FLORAL/ GREY', 490000, 10, 4, 'phổ biến', 1, '2021-07-09', 0, 490000, NULL);
INSERT INTO `product` VALUES (16, 1, 'LEVENTS SWEATER 0.5 FLORAL/ GREY', 429000, 15, 3, NULL, 3, '2021-07-30', 0, 429000, NULL);
INSERT INTO `product` VALUES (17, 1, 'LEVENTS SWEATER 0.5 FLORAL/ GREY', 549000, 12, 3, NULL, 2, '2021-07-26', 0, 549000, NULL);
INSERT INTO `product` VALUES (18, 1, 'LEVENTS SWEATER 0.5 FLORAL/ GREY', 490000, 10, 2, 'phổ biến', 3, '2021-07-30', 50, 490000, NULL);
INSERT INTO `product` VALUES (19, 1, 'LEVENTS SWEATER 0.5 FLORAL/ GREY', 699000, 30, 2, NULL, 1, '2021-08-02', 50, 699000, NULL);
INSERT INTO `product` VALUES (20, 1, 'LEVENTS SWEATER 0.5 FLORAL/ GREY', 490000, 23, 4, NULL, 3, '2021-07-30', 50, 490000, NULL);

-- ----------------------------
-- Table structure for product_color
-- ----------------------------
DROP TABLE IF EXISTS `product_color`;
CREATE TABLE `product_color`  (
  `product_id` bigint(20) NOT NULL,
  `color_id` bigint(20) NOT NULL,
  INDEX `FK3iys6jgmsdkw7w5ncgm55wgj3`(`color_id`) USING BTREE,
  INDEX `FKqb6lncpndi0w5po3rr5r9up5e`(`product_id`) USING BTREE,
  CONSTRAINT `FK3iys6jgmsdkw7w5ncgm55wgj3` FOREIGN KEY (`color_id`) REFERENCES `color` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKqb6lncpndi0w5po3rr5r9up5e` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_color
-- ----------------------------
INSERT INTO `product_color` VALUES (3, 3);
INSERT INTO `product_color` VALUES (1, 2);
INSERT INTO `product_color` VALUES (2, 1);
INSERT INTO `product_color` VALUES (2, 4);
INSERT INTO `product_color` VALUES (3, 3);
INSERT INTO `product_color` VALUES (4, 2);
INSERT INTO `product_color` VALUES (4, 1);
INSERT INTO `product_color` VALUES (5, 4);
INSERT INTO `product_color` VALUES (5, 3);
INSERT INTO `product_color` VALUES (6, 2);
INSERT INTO `product_color` VALUES (6, 1);
INSERT INTO `product_color` VALUES (7, 4);
INSERT INTO `product_color` VALUES (7, 3);
INSERT INTO `product_color` VALUES (8, 2);
INSERT INTO `product_color` VALUES (8, 1);
INSERT INTO `product_color` VALUES (9, 4);
INSERT INTO `product_color` VALUES (9, 3);
INSERT INTO `product_color` VALUES (10, 2);
INSERT INTO `product_color` VALUES (10, 1);
INSERT INTO `product_color` VALUES (11, 4);
INSERT INTO `product_color` VALUES (11, 3);
INSERT INTO `product_color` VALUES (12, 2);
INSERT INTO `product_color` VALUES (12, 1);
INSERT INTO `product_color` VALUES (13, 4);
INSERT INTO `product_color` VALUES (13, 3);
INSERT INTO `product_color` VALUES (14, 2);
INSERT INTO `product_color` VALUES (14, 1);
INSERT INTO `product_color` VALUES (15, 4);
INSERT INTO `product_color` VALUES (15, 3);
INSERT INTO `product_color` VALUES (17, 1);
INSERT INTO `product_color` VALUES (17, 4);
INSERT INTO `product_color` VALUES (16, 2);
INSERT INTO `product_color` VALUES (18, 2);
INSERT INTO `product_color` VALUES (18, 3);
INSERT INTO `product_color` VALUES (19, 1);
INSERT INTO `product_color` VALUES (19, 4);
INSERT INTO `product_color` VALUES (20, 2);
INSERT INTO `product_color` VALUES (20, 3);
INSERT INTO `product_color` VALUES (16, 3);
INSERT INTO `product_color` VALUES (1, 3);

-- ----------------------------
-- Table structure for product_image
-- ----------------------------
DROP TABLE IF EXISTS `product_image`;
CREATE TABLE `product_image`  (
  `product_id` bigint(20) NOT NULL,
  `image_id` bigint(20) NOT NULL,
  INDEX `FKbhddxsl8axd5io2wgkcoealn5`(`image_id`) USING BTREE,
  INDEX `FK6oo0cvcdtb6qmwsga468uuukk`(`product_id`) USING BTREE,
  CONSTRAINT `FK6oo0cvcdtb6qmwsga468uuukk` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKbhddxsl8axd5io2wgkcoealn5` FOREIGN KEY (`image_id`) REFERENCES `image` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_image
-- ----------------------------
INSERT INTO `product_image` VALUES (2, 2);
INSERT INTO `product_image` VALUES (1, 2);
INSERT INTO `product_image` VALUES (3, 3);
INSERT INTO `product_image` VALUES (3, 4);
INSERT INTO `product_image` VALUES (3, 5);
INSERT INTO `product_image` VALUES (2, 3);
INSERT INTO `product_image` VALUES (2, 1);
INSERT INTO `product_image` VALUES (4, 4);
INSERT INTO `product_image` VALUES (4, 5);
INSERT INTO `product_image` VALUES (4, 1);
INSERT INTO `product_image` VALUES (5, 2);
INSERT INTO `product_image` VALUES (5, 3);
INSERT INTO `product_image` VALUES (6, 4);
INSERT INTO `product_image` VALUES (6, 5);
INSERT INTO `product_image` VALUES (6, 1);
INSERT INTO `product_image` VALUES (7, 2);
INSERT INTO `product_image` VALUES (7, 3);
INSERT INTO `product_image` VALUES (8, 4);
INSERT INTO `product_image` VALUES (8, 5);
INSERT INTO `product_image` VALUES (9, 2);
INSERT INTO `product_image` VALUES (9, 1);
INSERT INTO `product_image` VALUES (10, 3);
INSERT INTO `product_image` VALUES (10, 4);
INSERT INTO `product_image` VALUES (11, 5);
INSERT INTO `product_image` VALUES (11, 1);
INSERT INTO `product_image` VALUES (12, 2);
INSERT INTO `product_image` VALUES (12, 3);
INSERT INTO `product_image` VALUES (13, 4);
INSERT INTO `product_image` VALUES (13, 5);
INSERT INTO `product_image` VALUES (1, 1);
INSERT INTO `product_image` VALUES (14, 2);
INSERT INTO `product_image` VALUES (14, 1);
INSERT INTO `product_image` VALUES (15, 3);
INSERT INTO `product_image` VALUES (15, 4);
INSERT INTO `product_image` VALUES (16, 5);
INSERT INTO `product_image` VALUES (16, 1);
INSERT INTO `product_image` VALUES (17, 2);
INSERT INTO `product_image` VALUES (17, 3);
INSERT INTO `product_image` VALUES (18, 4);
INSERT INTO `product_image` VALUES (18, 5);
INSERT INTO `product_image` VALUES (19, 2);
INSERT INTO `product_image` VALUES (19, 1);
INSERT INTO `product_image` VALUES (20, 3);
INSERT INTO `product_image` VALUES (20, 4);

-- ----------------------------
-- Table structure for rate
-- ----------------------------
DROP TABLE IF EXISTS `rate`;
CREATE TABLE `rate`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rate
-- ----------------------------
INSERT INTO `rate` VALUES (1, 1);
INSERT INTO `rate` VALUES (2, 2);
INSERT INTO `rate` VALUES (3, 3);
INSERT INTO `rate` VALUES (4, 4);
INSERT INTO `rate` VALUES (5, 5);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'ROLE_USER');
INSERT INTO `role` VALUES (2, 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for size
-- ----------------------------
DROP TABLE IF EXISTS `size`;
CREATE TABLE `size`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `size_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of size
-- ----------------------------
INSERT INTO `size` VALUES (1, 's');
INSERT INTO `size` VALUES (2, 'l');
INSERT INTO `size` VALUES (3, 'm');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fullname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` bit(1) NOT NULL,
  `role` bigint(20) NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_ob8kqyqqgmefl0aco34akdtpe`(`email`) USING BTREE,
  INDEX `FKl5alypubd40lwejc45vl35wjb`(`role`) USING BTREE,
  CONSTRAINT `FKl5alypubd40lwejc45vl35wjb` FOREIGN KEY (`role`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '$2a$10$9ygQCpADYrPaPCraKf46oe1kTzgs6GEJOJcFxuLuVJyPTAqnGLm7S', 'trandiem1006@gmail.com', 'Trn diem', b'0', 1, NULL, NULL);
INSERT INTO `user` VALUES (2, '$2a$10$XXARQLMqprTTKNg4Po2EXOJ3gclH1ZnhMXEdto2QyHj/mlHjQtfw6', 'dinh8@gmail.com', 'dinh8', b'0', 1, 'HCM', '0988766567');
INSERT INTO `user` VALUES (3, '$2a$10$x9Yr2OjDYRWHifhHqKTAoOy.kFeokiJeJ9hU5/qbWPzvaxnjrDXly', 'dinh8@gmai', 'dinh8', b'0', 1, 'HCM', '0988766567');

SET FOREIGN_KEY_CHECKS = 1;
