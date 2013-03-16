/*
MySQL Data Transfer
Source Host: localhost
Source Database: yunwei
Target Host: localhost
Target Database: yunwei
Date: 2013-03-16 18:12:08
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for app_data
-- ----------------------------
CREATE TABLE `app_data` (
  `app_data_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_code` varchar(8) NOT NULL COMMENT '属性名',
  `data_value` varchar(256) DEFAULT NULL COMMENT '属性值',
  `data_value_text` text COMMENT '属性值',
  `display_name` varchar(256) DEFAULT NULL COMMENT '属性显示名',
  `short_name` varchar(64) DEFAULT NULL COMMENT '简称',
  `data_type` int(11) DEFAULT NULL COMMENT '属性类型',
  `is_available` bit(1) DEFAULT NULL COMMENT '是否可用',
  `cTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`app_data_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for category
-- ----------------------------
CREATE TABLE `category` (
  `cate_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL,
  `cate_name` varchar(128) DEFAULT NULL,
  `is_available` bit(1) DEFAULT NULL COMMENT '是否有效',
  `cTime` datetime DEFAULT NULL COMMENT '创建时间',
  `admin` bigint(20) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`cate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
CREATE TABLE `customer` (
  `cust_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cust_name` varchar(64) DEFAULT NULL COMMENT '客户名称',
  `short_name` varchar(64) DEFAULT NULL COMMENT '简名',
  `cust_code` varchar(128) DEFAULT NULL COMMENT '编码',
  `contact_name` varchar(64) DEFAULT NULL COMMENT '联系人',
  `contry_code` varchar(8) DEFAULT NULL COMMENT '国家',
  `prov_code` varchar(8) DEFAULT NULL COMMENT '省份',
  `city_code` varchar(8) DEFAULT NULL COMMENT '城市',
  `address` varchar(256) DEFAULT NULL COMMENT '详细地址',
  `contact` varchar(64) DEFAULT NULL COMMENT '联系人',
  `telephone` varchar(64) DEFAULT NULL COMMENT '联系电话',
  `fax` varchar(64) DEFAULT NULL COMMENT '传真',
  `email` varchar(64) DEFAULT NULL COMMENT '电子邮件',
  `website` varchar(256) DEFAULT NULL COMMENT '主页',
  `bank` varchar(128) DEFAULT NULL COMMENT '开户银行',
  `bank_account` varchar(128) DEFAULT NULL COMMENT '账号',
  `bank_account_name` varchar(128) DEFAULT NULL COMMENT '开户姓名',
  `tax_id` varchar(256) DEFAULT NULL COMMENT '税号',
  `business_people` varchar(64) DEFAULT NULL COMMENT '业务人员',
  `collection_people` varchar(64) DEFAULT NULL COMMENT '收款人员',
  `cTime` datetime DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`cust_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
CREATE TABLE `dept` (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(32) DEFAULT NULL COMMENT '部门名称',
  `parent_id` int(11) DEFAULT NULL COMMENT '上级部门',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for funds_record
-- ----------------------------
CREATE TABLE `funds_record` (
  `funds_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `approval_id` bigint(20) DEFAULT NULL COMMENT '审核人',
  `staff_id` bigint(20) DEFAULT NULL COMMENT '收/付款人',
  `order_id` bigint(20) DEFAULT NULL COMMENT '款项对应的订单ID',
  `cust_id` bigint(20) DEFAULT NULL COMMENT '对应的客户ID',
  `funds_type` int(1) DEFAULT NULL COMMENT '1=应付款\r\n            2=应收款',
  `amount` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `paid_amount` decimal(10,2) DEFAULT NULL COMMENT '已收/付金额',
  `approval_status` int(1) DEFAULT NULL COMMENT '审核状态\r\n            \r\n            0=未审核\r\n            1=审核通过\r\n            -1=审核未通过',
  `approval_date` date DEFAULT NULL COMMENT '审核日期',
  `status` int(1) DEFAULT NULL COMMENT '收付款状态\r\n            0=未收/付款\r\n            1=已收/付款\r\n            2=已收/付部分款',
  `payment_mode` varchar(8) DEFAULT NULL COMMENT '付款方式',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`funds_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for order_goods
-- ----------------------------
CREATE TABLE `order_goods` (
  `goods_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goods_code` varchar(256) DEFAULT NULL COMMENT '编码或条形码',
  `product_id` bigint(64) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `unit` varchar(8) DEFAULT NULL COMMENT '单位',
  `unit_price` decimal(10,2) DEFAULT NULL COMMENT '单价',
  `quantity` int(32) DEFAULT NULL COMMENT '数量',
  `handed_quantity` int(32) DEFAULT NULL COMMENT '已交数量',
  `untaxed_amount` decimal(10,2) DEFAULT NULL COMMENT '未税金额',
  `tax_rate` double(8,2) DEFAULT NULL COMMENT '税率',
  `output_tax_amount` decimal(10,2) DEFAULT NULL COMMENT '销项税额=未税金额x(1+税率)',
  `tax_amount` decimal(10,2) DEFAULT NULL COMMENT '税额=当期销项税额-当期进项税额',
  `model` varchar(8) DEFAULT NULL COMMENT '型号',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注说明',
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product
-- ----------------------------
CREATE TABLE `product` (
  `product_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cat_id` bigint(20) DEFAULT NULL,
  `product_name` varchar(64) DEFAULT NULL COMMENT '商品名称',
  `product_code` varchar(64) DEFAULT NULL COMMENT '商品编码',
  `ceil_limit` int(11) DEFAULT NULL COMMENT '库存上限',
  `low_limit` int(11) DEFAULT NULL COMMENT '库存下限',
  `piny_code` varchar(64) DEFAULT NULL COMMENT '拼音编码',
  `product_spec` varchar(64) DEFAULT NULL COMMENT '规格型号',
  `sale_price` decimal(10,2) DEFAULT NULL COMMENT '预设售价',
  `purchase_price` decimal(10,2) DEFAULT NULL COMMENT '预设进价',
  `is_available` bit(1) DEFAULT NULL COMMENT '是否有效',
  `is_sellable` bit(1) DEFAULT NULL COMMENT '是否可卖',
  `is_negative` bit(1) DEFAULT NULL COMMENT '是否允许负库存',
  `is_purchasable` bit(1) DEFAULT NULL COMMENT '是否允许采购',
  `product_image` varchar(256) DEFAULT NULL COMMENT '产品图片',
  `manufacturer` varchar(256) DEFAULT NULL COMMENT '生产商',
  `orginal_place` varchar(256) DEFAULT NULL COMMENT '产地',
  `cTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_supplier
-- ----------------------------
CREATE TABLE `product_supplier` (
  `product_supplier_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `cust_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`product_supplier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sale_order
-- ----------------------------
CREATE TABLE `sale_order` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(16) NOT NULL,
  `staff_id` bigint(20) DEFAULT NULL,
  `cust_id` bigint(20) DEFAULT NULL,
  `order_date` date DEFAULT NULL COMMENT '订单日期',
  `payment_mode` varchar(8) DEFAULT NULL COMMENT '付款方式',
  `invoice_no` varchar(128) DEFAULT NULL COMMENT '发票号码',
  `description` varchar(512) DEFAULT NULL COMMENT '订单说明',
  `accounting_deptId` bigint(20) DEFAULT NULL COMMENT '核算部门',
  `currency` varchar(8) DEFAULT NULL COMMENT '币种',
  `delivery_method` varchar(8) DEFAULT NULL COMMENT '交货方式',
  `delivery_date` date DEFAULT NULL COMMENT '交货日期',
  `additional_text` text COMMENT '更多内容',
  `is_available` bit(1) DEFAULT NULL COMMENT '是否可用',
  `updator` bigint(20) DEFAULT NULL COMMENT '更新人',
  `uTime` datetime DEFAULT NULL COMMENT '更新时间',
  `cTime` datetime DEFAULT NULL COMMENT '创建时间',
  `creator_Id` bigint(20) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`order_id`),
  UNIQUE KEY `Index_order_no` (`order_no`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for staff
-- ----------------------------
CREATE TABLE `staff` (
  `staff_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dept_id` int(11) DEFAULT NULL COMMENT '所在部门',
  `staff_name` varchar(128) DEFAULT NULL COMMENT '员工名称',
  `login_name` varchar(64) DEFAULT NULL COMMENT '登录名称',
  `login_password` varchar(128) DEFAULT NULL COMMENT '密码',
  `position` varchar(8) DEFAULT NULL COMMENT '职务',
  `gender` int(1) DEFAULT NULL COMMENT '性别',
  `birth_date` date DEFAULT NULL COMMENT '出生日期',
  `hire_date` date DEFAULT NULL COMMENT '合同签订日期',
  `mature_date` date DEFAULT NULL COMMENT '合同到期日期',
  `identity_card` varchar(32) DEFAULT NULL COMMENT '身份证号码',
  `address` varchar(256) DEFAULT NULL COMMENT '住址',
  `phone` varchar(32) DEFAULT NULL COMMENT '电话',
  `email` varchar(128) DEFAULT NULL COMMENT '电子邮件',
  `cTime` datetime DEFAULT NULL COMMENT '创建时间',
  `adminId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`staff_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for stock_house
-- ----------------------------
CREATE TABLE `stock_house` (
  `stock_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `staff_id` bigint(20) DEFAULT NULL COMMENT '仓库保管',
  `stock_name` varchar(256) DEFAULT NULL COMMENT '仓库名称',
  `address` varchar(256) DEFAULT NULL COMMENT '地址',
  `phone` varchar(32) DEFAULT NULL COMMENT '电话',
  `cTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`stock_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for stock_product
-- ----------------------------
CREATE TABLE `stock_product` (
  `stock_product_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stock_id` bigint(20) DEFAULT NULL COMMENT '所在仓库',
  `product_id` bigint(20) DEFAULT NULL COMMENT '产品名称',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `first_enter_date` datetime DEFAULT NULL COMMENT '首次入库时间',
  `outer_date` datetime DEFAULT NULL COMMENT '最新出库时间',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  PRIMARY KEY (`stock_product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for stock_record
-- ----------------------------
CREATE TABLE `stock_record` (
  `stock_operation_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dept_id` int(11) DEFAULT NULL COMMENT '责任部门',
  `staff_id` bigint(20) DEFAULT NULL COMMENT '操作员工',
  `oper_type` int(1) DEFAULT NULL COMMENT '业务类型',
  `cust_id` bigint(20) DEFAULT NULL COMMENT '客户',
  `oper_date` datetime DEFAULT NULL COMMENT '出货/退货日期',
  `approval_status` int(11) DEFAULT NULL COMMENT '审核状态',
  `approval_id` bigint(20) DEFAULT NULL COMMENT '审核人',
  `stock_id` bigint(20) DEFAULT NULL COMMENT '所在仓库',
  `to_stock_id` bigint(20) DEFAULT NULL COMMENT '目标仓库',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`stock_operation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for stock_record_line
-- ----------------------------
CREATE TABLE `stock_record_line` (
  `stock_oper_detail_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stock_operation_id` bigint(20) DEFAULT NULL COMMENT '操作ID',
  `product_id` bigint(20) DEFAULT NULL COMMENT '产品',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `quantity` int(11) DEFAULT NULL COMMENT '出库数量',
  `have_invoice` bit(1) DEFAULT NULL COMMENT '是否有发票',
  `invoice_number` varchar(32) DEFAULT NULL COMMENT '发票号码',
  PRIMARY KEY (`stock_oper_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `app_data` VALUES ('4', '', '', '', '', '', null, null, null);
INSERT INTO `app_data` VALUES ('5', '', '', '', '', '', null, null, null);
INSERT INTO `app_data` VALUES ('6', '', '', '', '', '', null, null, null);
INSERT INTO `app_data` VALUES ('7', '', '', '', '', '', null, null, null);
INSERT INTO `app_data` VALUES ('8', '', '', '', '', '', null, null, null);
INSERT INTO `app_data` VALUES ('9', '', '', '', '', '', null, null, null);
INSERT INTO `app_data` VALUES ('10', 'da', '121', '2', '22', '121', '121', '', null);
INSERT INTO `app_data` VALUES ('11', 'da', '121', '2', '22', '121', '121', '', null);
INSERT INTO `category` VALUES ('4', null, '笔记本44', '', '2013-02-05 00:00:00', '1');
INSERT INTO `category` VALUES ('6', '4', '联想笔记本', '', '2013-02-02 10:50:56', '1');
INSERT INTO `category` VALUES ('7', '4', '苹果笔记本', '', '2013-02-02 10:51:22', '1');
INSERT INTO `category` VALUES ('9', '8', '宏基2', '', '2013-02-02 11:26:46', '1');
INSERT INTO `category` VALUES ('10', '8', '宏基笔记本2', '', '2013-02-02 12:16:14', '1');
INSERT INTO `category` VALUES ('11', null, '2222', '', '2013-02-03 11:50:09', '1');
INSERT INTO `category` VALUES ('12', '6', '笔记本', '', '2013-02-23 15:49:48', '1');
INSERT INTO `category` VALUES ('13', null, '电话录音卡', '', '2013-03-02 10:52:28', '1');
INSERT INTO `category` VALUES ('14', null, '', '', '2013-03-16 15:02:04', '1');
INSERT INTO `customer` VALUES ('1', '112', '121', '121', '21', '212', '212', '12', '12', '121', '2', '1211', 'gostart82@gmail.com', '1111', '1', '11', '111', '1', '11', '11', '2013-02-04 00:00:00', '11');
INSERT INTO `customer` VALUES ('2', 'customer', 'customer', '11', '21', '212', '212', '12', '4412254455', '121', '11', '1211', 'gostart82@gmail.com', '1111', '1', '11', '111', '212', '121', '11', '2013-01-22 00:00:00', '22');
INSERT INTO `customer` VALUES ('3', 'ijiaicom', 'ij', 'ijiai', 'zhh', 'china', 'gd', 'gz', '89', '22', '34205998', '34207227', '', '', '', '', '', '', '', '', '2013-03-03 17:30:00', '');
INSERT INTO `customer` VALUES ('4', 'yunweicom', 'yunwei', 'yw', 'zhh1', '中国', '广东省', '广州市', '天河区中山大道西89号华景软件园A天河软件园17层', '', '13310884810', '34207227', '409344451@QQ.com', 'www.yunweisoft.com', '广州银行天河支行', '800144485608010', '广州云为软件科技有限公司', '88889999', '不知道', '知道', '2013-03-03 17:40:38', '重要客户');
INSERT INTO `dept` VALUES ('2', '仓库部', null, '11');
INSERT INTO `dept` VALUES ('3', '技术部', null, '1212');
INSERT INTO `dept` VALUES ('4', '销售部', null, '22');
INSERT INTO `dept` VALUES ('5', '仓库质检部', '2', '质量检查');
INSERT INTO `product` VALUES ('2', '6', '7544', 'dd', '11', '12', 'hj', '33333', '12.01', '21.00', '', '', '', '', 'fileUpload/productInfo/e94586aa-a9a8-4556-bc05-dd1b0ca4a61e.jpg', '', '', null);
INSERT INTO `product` VALUES ('3', '4', '商品名称', '商品名称', '12', '11', 'hj', '1', '12.01', '21.00', '', '', '', '', 'fileUpload/productInfo/34a4d674-c5d3-4460-ae41-535bb622fddd.jpg', '55', '5555', null);
INSERT INTO `product` VALUES ('5', '6', '7544', 'dd', '11', '12', 'hj', '1', '12.00', '21.00', '', '', '', '', 'fileUpload/productInfo/07e2f104-45ee-49c5-ba0b-ea2c9fc4066a.jpg', '11', '', null);
INSERT INTO `product` VALUES ('7', '4', '7544', 'dd', '11', '12', 'hj', '1', '12.00', '21.00', '', '', '', '', 'fileUpload/productInfo/069b4fd1-658f-442f-b243-51cabf657cb6.jpg', '11', '131', null);
INSERT INTO `product` VALUES ('8', '6', '商品名称', 'dd', '111', '112', '1hj', '1', '112.00', '121.00', '', '', '', '', 'fileUpload/productInfo/e3a8fb9b-44f0-4352-83d6-2a96e5e83da9.jpg', '', '', null);
INSERT INTO `product` VALUES ('9', '6', '大会', '33333', '10000', '3', 'LX', 'IIIIII777', '333.00', '100.00', '', '', '', '', 'fileUpload/productInfo/6ac1531f-fa5e-48c9-b02e-f30e365bae9b.jpg', '', '', null);
INSERT INTO `product` VALUES ('10', '13', 'pci专业录音卡PCI', 'PCIXXX88', '10000', '2', 'lxk', 'ijiaiRL8X', '1280.00', '900.00', '', '', '', '', 'fileUpload/productInfo/2137d50f-e56b-4742-9e04-35d46022f1eb.jpg', '', '', null);
INSERT INTO `product` VALUES ('11', '13', 'pci专业录音卡PCI16', 'PCIYYY', '111', '3', 'lxk16', 'ijiaiRL16X', '1980.00', '1500.00', '', '', '', '', 'fileUpload/productInfo/7a8109aa-bc03-4df6-abf1-4095ace694cd.jpg', 'ijiai', 'sz', '2013-03-02 11:06:30');
INSERT INTO `product` VALUES ('13', null, 'pci专业录音卡PCI', '33333', '111', '2', '1hj', 'IIIIII777', '112.00', '100.00', '', '', '', '', null, '', '', '2013-03-03 11:34:20');
INSERT INTO `product` VALUES ('14', null, '11', '11', null, null, 'lxk', '33333', '1280.00', '900.00', '', '', '', '', null, '', '', '2013-03-03 11:36:34');
INSERT INTO `product` VALUES ('15', null, 'pci22', '22', null, null, '1hj', 'ijiairl8x3', '1280.00', '900.00', '', '', '', '', null, '', '', '2013-03-03 11:40:48');
INSERT INTO `sale_order` VALUES ('1', '12313', '6', '1', '2013-01-10', '1', '13131', '3131313', '5', '2', '2', '2013-01-11', '2321', '', null, null, null, null);
INSERT INTO `staff` VALUES ('3', '2', '小明', 'Nick', '123', '123', '1', '2013-03-12', '2013-03-13', '2013-03-27', '4412254455', '4412254455', '4412254455', 'gos2@gmail.com', null, null);
INSERT INTO `staff` VALUES ('4', '5', 'Jimmy', 'Jimmy', '123', 'Saler', '1', '2013-03-05', '2013-04-01', '2013-03-12', '4412254455', '12', '4412254455', '4412254455@qq.com', null, null);
INSERT INTO `staff` VALUES ('5', '2', 'Sunny', 'Sunny', '123', 'Saler', '0', '2013-03-12', '2013-03-19', '2013-03-19', '4412254455', '4412254455', '4412254455', 'gos2@gmail.com', null, null);
INSERT INTO `staff` VALUES ('6', '2', '小明2', 'Jack1', '123', '123', '1', '2013-02-25', '2013-03-20', '2013-03-20', '4412254455', '12', '4412254455', 'gos2@gmail.com', null, null);
