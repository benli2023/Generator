/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2013-07-21 13:00:00                          */
/*==============================================================*/


drop table if exists Customer;

drop table if exists Procurement;

drop table if exists ProcurementSale;

drop table if exists Product;

drop table if exists ProductType;

drop table if exists Provider;

drop table if exists Sale;

drop table if exists Transport;

drop table if exists User;

/*==============================================================*/
/* Table: Customer                                              */
/*==============================================================*/
create table Customer
(
   custId               bigint not null auto_increment comment '客户ID',
   custName             varchar(64) comment '客户名称',
   telephone            varchar(128) comment '客户电话',
   primary key (custId)
);

/*==============================================================*/
/* Table: Procurement                                           */
/*==============================================================*/
create table Procurement
(
   procurementId        bigint not null auto_increment comment '采购ID',
   procurementName      varchar(128) comment '采购名称',
   prodId               bigint comment '产品ID',
   procurementDate      date comment '采购日期',
   amount               int comment '数量',
   remainedAmount       int comment '剩余数量',
   unitPrice            decimal(10,5) comment '单价',
   freightage           decimal(10,5) comment '运费',
   loadFee              decimal(10,5) comment '卸费',
   carNumber            varchar(32) comment '车牌号',
   driver               varchar(64) comment '司机',
   driverTel            varchar(32) comment '司机电话',
   updateDate           datetime comment '更新时间',
   createdDate          datetime comment '创建时间',
   updateId             bigint comment '更新人ID',
   createId             bigint comment '创建人ID',
   transportId          bigint comment '运输ID',
   primary key (procurementId)
);

/*==============================================================*/
/* Table: ProcurementSale                                       */
/*==============================================================*/
create table ProcurementSale
(
   id                   bigint not null auto_increment comment '销售记录ID',
   procurementId        bigint comment '采购ID',
   saleId               bigint comment '出售ID',
   primary key (id)
);

/*==============================================================*/
/* Table: Product                                               */
/*==============================================================*/
create table Product
(
   prodId               bigint not null auto_increment comment '产品ID',
   typeId               bigint comment '产品类型ID',
   providerId           bigint comment '供应商ID',
   productName          varchar(128) comment '产品名称',
   prodctDesc           varchar(128) comment '产品描述',
   primary key (prodId)
);

/*==============================================================*/
/* Table: ProductType                                           */
/*==============================================================*/
create table ProductType
(
   typeId               bigint not null auto_increment comment '产品类型ID',
   name                 varchar(64) comment '产品类型名称',
   code                 varchar(32) comment '产品类型编码',
   primary key (typeId)
);

/*==============================================================*/
/* Table: Provider                                              */
/*==============================================================*/
create table Provider
(
   providerId           bigint not null auto_increment comment '供应商ID',
   provider             varchar(64) comment '供应商名称',
   primary key (providerId)
);

/*==============================================================*/
/* Table: Sale                                                  */
/*==============================================================*/
create table Sale
(
   saleId               bigint not null auto_increment comment '出售ID',
   custId               bigint comment '客户ID',
   amount               int comment '数量',
   custName             varchar(64) comment '客户名称',
   custTel              varchar(128) comment '客户电话',
   unitPrice            decimal(10,5) comment '售出单价',
   paid                 decimal(10,5) comment '已付金额',
   unPaid               decimal(10,5) comment '未付金额',
   otherFee             decimal(10,5) comment '其它费用',
   profit               decimal(10,5) comment '利润',
   paymentStatus        int(1) comment '状态',
   createId             bigint comment '创建人ID',
   transportId          bigint comment '运输ID',
   carNumber            varchar(32) comment '车牌号',
   driver               varchar(64) comment '司机',
   driverTel            varchar(32) comment '司机电话',
   updateDate           datetime comment '更新时间',
   createdDate          datetime comment '创建时间',
   updateId             bigint comment '更新人ID',
   primary key (saleId)
);

/*==============================================================*/
/* Table: Transport                                             */
/*==============================================================*/
create table Transport
(
   transportId          bigint not null auto_increment comment '运输ID',
   driver               varchar(64) comment '司机',
   carNumber            varchar(32) comment '车牌号',
   driverTel            varchar(32) comment '电话',
   primary key (transportId)
);

/*==============================================================*/
/* Table: User                                                  */
/*==============================================================*/
create table User
(
   userId               bigint not null auto_increment comment '用户ID',
   userName             varchar(64) comment '用户名称',
   password             varchar(128) comment '密码',
   groupLevel           int comment '所属用户组',
   primary key (userId)
);

alter table Procurement add constraint FK_Reference_11 foreign key (updateId)
      references User (userId) on delete restrict on update restrict;

alter table Procurement add constraint FK_Reference_12 foreign key (transportId)
      references Transport (transportId) on delete restrict on update restrict;

alter table Procurement add constraint FK_Reference_30 foreign key (createId)
      references User (userId) on delete restrict on update restrict;

alter table Procurement add constraint FK_Reference_4 foreign key (prodId)
      references Product (prodId) on delete restrict on update restrict;

alter table ProcurementSale add constraint FK_Reference_10 foreign key (saleId)
      references Sale (saleId) on delete restrict on update restrict;

alter table ProcurementSale add constraint FK_Reference_9 foreign key (procurementId)
      references Procurement (procurementId) on delete restrict on update restrict;

alter table Product add constraint FK_Reference_2 foreign key (typeId)
      references ProductType (typeId) on delete restrict on update restrict;

alter table Product add constraint FK_Reference_3 foreign key (providerId)
      references Provider (providerId) on delete restrict on update restrict;

alter table Sale add constraint FK_Reference_13 foreign key (createId)
      references User (userId) on delete restrict on update restrict;

alter table Sale add constraint FK_Reference_14 foreign key (transportId)
      references Transport (transportId) on delete restrict on update restrict;

alter table Sale add constraint FK_Reference_7 foreign key (custId)
      references Customer (custId) on delete restrict on update restrict;

