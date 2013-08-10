/*
MySQL Data Transfer
Source Host: localhost
Source Database: sale
Target Host: localhost
Target Database: sale
Date: 2013-08-10 23:28:17
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for customer
-- ----------------------------
CREATE TABLE `customer` (
  `custId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '客户ID',
  `custName` varchar(64) DEFAULT NULL COMMENT '客户名称',
  `telephone` varchar(128) DEFAULT NULL COMMENT '客户电话',
  PRIMARY KEY (`custId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for procurement
-- ----------------------------
CREATE TABLE `procurement` (
  `procurementId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '采购ID',
  `procurementName` varchar(128) DEFAULT NULL COMMENT '采购名称',
  `prodId` bigint(20) NOT NULL COMMENT '产品ID',
  `procurementDate` date DEFAULT NULL COMMENT '采购日期',
  `amount` int(11) NOT NULL COMMENT '数量',
  `unit` int(1) NOT NULL COMMENT '单位',
  `remainedAmount` int(11) DEFAULT NULL COMMENT '剩余数量',
  `unitPrice` decimal(10,5) NOT NULL COMMENT '单价',
  `freightage` decimal(10,5) DEFAULT NULL COMMENT '运费',
  `loadFee` decimal(10,5) DEFAULT NULL COMMENT '卸费',
  `carNumber` varchar(32) DEFAULT NULL COMMENT '车牌号',
  `driver` varchar(64) DEFAULT NULL COMMENT '司机',
  `driverTel` varchar(32) DEFAULT NULL COMMENT '司机电话',
  `updateDate` datetime NOT NULL COMMENT '更新时间',
  `createdDate` datetime NOT NULL COMMENT '创建时间',
  `updateId` bigint(20) NOT NULL COMMENT '更新人ID',
  `createId` bigint(20) NOT NULL COMMENT '创建人ID',
  `transportId` bigint(20) DEFAULT NULL COMMENT '运输ID',
  PRIMARY KEY (`procurementId`),
  KEY `FK_Reference_11` (`updateId`),
  KEY `FK_Reference_12` (`transportId`),
  KEY `FK_Reference_30` (`createId`),
  KEY `FK_Reference_4` (`prodId`),
  CONSTRAINT `FK_Reference_11` FOREIGN KEY (`updateId`) REFERENCES `user` (`userId`),
  CONSTRAINT `FK_Reference_12` FOREIGN KEY (`transportId`) REFERENCES `transport` (`transportId`),
  CONSTRAINT `FK_Reference_30` FOREIGN KEY (`createId`) REFERENCES `user` (`userId`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`prodId`) REFERENCES `product` (`prodId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for procurementsale
-- ----------------------------
CREATE TABLE `procurementsale` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '销售记录ID',
  `procurementId` bigint(20) DEFAULT NULL COMMENT '采购ID',
  `saleId` bigint(20) DEFAULT NULL COMMENT '出售ID',
  `amount` int(11) DEFAULT NULL COMMENT '出售数量',
  `unitPrice` decimal(10,5) DEFAULT NULL COMMENT '出售单价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product
-- ----------------------------
CREATE TABLE `product` (
  `prodId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '产品ID',
  `typeId` bigint(20) NOT NULL COMMENT '产品类型ID',
  `providerId` bigint(20) DEFAULT NULL COMMENT '供应商ID',
  `productName` varchar(128) NOT NULL COMMENT '产品名称',
  `prodctDesc` varchar(128) DEFAULT NULL COMMENT '产品描述',
  PRIMARY KEY (`prodId`),
  KEY `FK_Reference_2` (`typeId`),
  KEY `FK_Reference_3` (`providerId`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`typeId`) REFERENCES `producttype` (`typeId`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`providerId`) REFERENCES `provider` (`providerId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for producttype
-- ----------------------------
CREATE TABLE `producttype` (
  `typeId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '产品类型ID',
  `name` varchar(64) DEFAULT NULL COMMENT '产品类型名称',
  `code` varchar(32) DEFAULT NULL COMMENT '产品类型编码',
  PRIMARY KEY (`typeId`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for provider
-- ----------------------------
CREATE TABLE `provider` (
  `providerId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '供应商ID',
  `provider` varchar(64) DEFAULT NULL COMMENT '供应商名称',
  PRIMARY KEY (`providerId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sale
-- ----------------------------
CREATE TABLE `sale` (
  `saleId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '出售ID',
  `custId` bigint(20) DEFAULT NULL COMMENT '客户ID',
  `custName` varchar(64) DEFAULT NULL COMMENT '客户名称',
  `custTel` varchar(128) DEFAULT NULL COMMENT '客户电话',
  `paid` decimal(10,5) DEFAULT NULL COMMENT '已付金额',
  `unPaid` decimal(10,5) DEFAULT NULL COMMENT '未付金额',
  `otherFee` decimal(10,5) DEFAULT NULL COMMENT '其它费用',
  `profit` decimal(10,5) DEFAULT NULL COMMENT '利润',
  `paymentStatus` int(1) DEFAULT NULL COMMENT '状态',
  `createId` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `transportId` bigint(20) DEFAULT NULL COMMENT '运输ID',
  `carNumber` varchar(32) DEFAULT NULL COMMENT '车牌号',
  `driver` varchar(64) DEFAULT NULL COMMENT '司机',
  `driverTel` varchar(32) DEFAULT NULL COMMENT '司机电话',
  `updateDate` datetime DEFAULT NULL COMMENT '更新时间',
  `createdDate` datetime DEFAULT NULL COMMENT '创建时间',
  `updateId` bigint(20) DEFAULT NULL COMMENT '更新人ID',
  PRIMARY KEY (`saleId`),
  KEY `FK_Reference_13` (`createId`),
  KEY `FK_Reference_14` (`transportId`),
  KEY `FK_Reference_7` (`custId`),
  CONSTRAINT `FK_Reference_13` FOREIGN KEY (`createId`) REFERENCES `user` (`userId`),
  CONSTRAINT `FK_Reference_14` FOREIGN KEY (`transportId`) REFERENCES `transport` (`transportId`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`custId`) REFERENCES `customer` (`custId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for transport
-- ----------------------------
CREATE TABLE `transport` (
  `transportId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '运输ID',
  `driver` varchar(64) NOT NULL COMMENT '司机',
  `carNumber` varchar(32) DEFAULT NULL COMMENT '车牌号',
  `driverTel` varchar(32) NOT NULL COMMENT '电话',
  PRIMARY KEY (`transportId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
CREATE TABLE `user` (
  `userId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `userName` varchar(64) NOT NULL COMMENT '用户名称',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `groupLevel` int(11) NOT NULL COMMENT '所属用户组',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `customer` VALUES ('1', '客户', '13312723');
INSERT INTO `customer` VALUES ('2', '123', '13312845620');
INSERT INTO `procurement` VALUES ('3', '采购名称', '1', '2013-08-13', '22', '1', '12', '11.00000', '11.00000', '11.00000', '953321', '张默', '13312845620', '2013-08-04 23:39:19', '2013-08-04 23:21:14', '1', '1', '3');
INSERT INTO `procurement` VALUES ('4', '采购名称2', '4', '2013-08-05', '22', '1', '22', '11.01000', '11.02000', '11.00000', '953321', '张默', '13312845620', '2013-08-10 21:31:31', '2013-08-05 00:43:36', '1', '1', '3');
INSERT INTO `procurement` VALUES ('5', '采购名称', '1', '2013-08-05', '22', '1', '12', '11.00000', '11.00000', '11.00000', '953321', '张默', '13312845620', '2013-08-10 13:31:59', '2013-08-10 13:31:59', '1', '1', '3');
INSERT INTO `procurement` VALUES ('6', '采购名称3', '1', '2013-07-30', '22', '1', '12', '11.00000', '11.00000', '11.00000', '953321', '张默', '13312845620', '2013-08-10 21:31:25', '2013-08-10 20:13:55', '1', '1', '3');
INSERT INTO `procurementsale` VALUES ('1', '1', '1', '1', '12.10000');
INSERT INTO `product` VALUES ('1', '72', '1', '32.5R编织袋水泥', '13131');
INSERT INTO `product` VALUES ('4', '74', '1', '32.5R编织袋水泥', '13131');
INSERT INTO `product` VALUES ('6', '76', '2', '32.5R编织袋水泥', '13131');
INSERT INTO `product` VALUES ('7', '73', '1', '32.5R编织袋水泥', '');
INSERT INTO `producttype` VALUES ('51', '32.5R产品类型', '32.5R');
INSERT INTO `producttype` VALUES ('72', '32.5R编织袋4', '32.5R');
INSERT INTO `producttype` VALUES ('73', '32.5R编织袋', '32.5R');
INSERT INTO `producttype` VALUES ('74', '32.5R编织袋', '32.5R');
INSERT INTO `producttype` VALUES ('75', '32.5R编织袋', '32.5R');
INSERT INTO `producttype` VALUES ('76', '32.5R编织袋', '32.5R');
INSERT INTO `producttype` VALUES ('77', '32.5R编织袋', '32.5R');
INSERT INTO `provider` VALUES ('1', '步步高1');
INSERT INTO `provider` VALUES ('2', '步步高2');
INSERT INTO `provider` VALUES ('3', 'default');
INSERT INTO `transport` VALUES ('3', '张默', '953321', '13312845620');
INSERT INTO `user` VALUES ('1', 'admin', 'admin', '1');
INSERT INTO `user` VALUES ('2', 'calin', '123', '1');
