/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : myapp

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-01-06 18:24:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for accesslog
-- ----------------------------
DROP TABLE IF EXISTS `accesslog`;
CREATE TABLE `accesslog` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ClickEntry` varchar(100) DEFAULT NULL COMMENT '点击入口',
  `MemberID` int(11) DEFAULT NULL,
  `Url` varchar(255) DEFAULT NULL,
  `Params` varchar(255) DEFAULT NULL,
  `Ip` varchar(20) DEFAULT NULL,
  `CreateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Role` tinyint(4) DEFAULT NULL COMMENT '角色',
  `Power` varchar(255) DEFAULT NULL COMMENT '权限',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for attachment
-- ----------------------------
DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '附件表',
  `MailID` int(11) DEFAULT NULL COMMENT '邮件内容ID',
  `Name` varchar(255) DEFAULT NULL,
  `Path` varchar(255) DEFAULT NULL COMMENT '文件路径',
  `Type` tinyint(4) DEFAULT NULL COMMENT '文件类型',
  `OrderNum` tinyint(4) DEFAULT NULL,
  `CreateTime` datetime DEFAULT NULL,
  `IsDelete` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='附件表';

-- ----------------------------
-- Table structure for basic
-- ----------------------------
DROP TABLE IF EXISTS `basic`;
CREATE TABLE `basic` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CreateTime` datetime DEFAULT NULL COMMENT '创建时间',
  `UpdateTime` datetime DEFAULT NULL COMMENT '更新时间（最后）',
  `OrderNum` tinyint(4) DEFAULT '0' COMMENT '序号',
  `IsDelete` tinyint(1) DEFAULT NULL,
  `Status` tinyint(4) DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '配置信息ID',
  `Key` varchar(45) DEFAULT NULL COMMENT '关键词',
  `Val` varchar(500) DEFAULT NULL COMMENT '值',
  `Description` varchar(100) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配置信息';

-- ----------------------------
-- Table structure for contact
-- ----------------------------
DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact` (
  `ID` int(20) NOT NULL AUTO_INCREMENT COMMENT '联系人表',
  `ContactID` int(11) DEFAULT NULL COMMENT '联系人ID',
  `MemberID` int(11) DEFAULT NULL COMMENT '用户ID',
  `ContactGroupID` int(11) DEFAULT NULL COMMENT '联系人分组',
  `Name` varchar(60) DEFAULT '' COMMENT '联系人名',
  `Mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `Email` varchar(100) DEFAULT NULL COMMENT '邮件地址',
  `QQ` varchar(255) DEFAULT NULL,
  `HeadPic` varchar(100) DEFAULT NULL COMMENT '头像',
  `Address` varchar(100) DEFAULT NULL COMMENT '地址',
  `CreateTime` datetime DEFAULT NULL COMMENT '创建时间',
  `UpdateTime` datetime DEFAULT NULL COMMENT '修改时间',
  `IsFirst` tinyint(1) DEFAULT '0' COMMENT 'MemberID是否首次发送邮件',
  `IsDel` tinyint(1) DEFAULT '0' COMMENT '是否删除 0否1是',
  `Status` tinyint(4) DEFAULT NULL COMMENT '0,等待同意 1同意 2系统外联系人',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`) USING BTREE,
  KEY `memberID` (`MemberID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='联系人表';

-- ----------------------------
-- Table structure for contactgroup
-- ----------------------------
DROP TABLE IF EXISTS `contactgroup`;
CREATE TABLE `contactgroup` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '联系人分组',
  `Name` varchar(64) DEFAULT NULL COMMENT '小组名',
  `Desc` varchar(255) DEFAULT NULL COMMENT '描述',
  `CreateTime` datetime DEFAULT NULL COMMENT '创建时间',
  `OrderNum` int(11) DEFAULT '0' COMMENT '序号',
  `IsDel` tinyint(1) DEFAULT '0' COMMENT '是否删除 0否1是',
  `Status` tinyint(4) DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='联系人分组';

-- ----------------------------
-- Table structure for content
-- ----------------------------
DROP TABLE IF EXISTS `content`;
CREATE TABLE `content` (
  `contentId` int(50) NOT NULL AUTO_INCREMENT,
  `contentName` varchar(50) DEFAULT NULL COMMENT '发送者',
  `content` varchar(1000) DEFAULT NULL COMMENT '聊天内容',
  `createDate` varchar(100) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`contentId`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户反馈表',
  `MemberID` int(11) DEFAULT NULL,
  `CreateTime` datetime DEFAULT NULL,
  `Title` varchar(100) DEFAULT NULL,
  `Content` varchar(1000) DEFAULT NULL,
  `AdminID` int(11) DEFAULT NULL,
  `IsReply` tinyint(1) DEFAULT '0' COMMENT '是否回复',
  `ReplyContent` varchar(1000) DEFAULT NULL,
  `ReplyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户反馈表';

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志表',
  `LogTypeID` int(11) DEFAULT NULL COMMENT '日志类型',
  `AdminID` int(11) DEFAULT NULL COMMENT '管理员',
  `MemberID` int(11) DEFAULT NULL COMMENT '用户',
  `MessageID` int(11) DEFAULT NULL COMMENT '邮件ID',
  `OperIp` varchar(50) DEFAULT NULL COMMENT '访问路径',
  `OperTime` datetime DEFAULT NULL COMMENT '操作时间',
  `OperDesc` varchar(255) DEFAULT NULL COMMENT '操作描述',
  `RequestContext` varchar(255) DEFAULT NULL COMMENT '全路径',
  `IsDel` tinyint(1) DEFAULT '0' COMMENT '删除标记 是否删除 0否1是',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COMMENT='日志表';

-- ----------------------------
-- Table structure for logtype
-- ----------------------------
DROP TABLE IF EXISTS `logtype`;
CREATE TABLE `logtype` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志类型',
  `TypeName` varchar(50) DEFAULT NULL,
  `TypeDesc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='日志类型';

-- ----------------------------
-- Table structure for mail
-- ----------------------------
DROP TABLE IF EXISTS `mail`;
CREATE TABLE `mail` (
  `ID` int(20) NOT NULL AUTO_INCREMENT COMMENT '邮件内容表',
  `Subject` varchar(50) DEFAULT NULL COMMENT '主题',
  `Content` varchar(1000) DEFAULT NULL COMMENT '内容',
  `Icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `Url` varchar(255) DEFAULT NULL COMMENT '链接',
  `CreateTime` datetime DEFAULT NULL COMMENT '创建时间',
  `UpdateTime` datetime DEFAULT NULL COMMENT '修改时间',
  `IsDelete` tinyint(4) DEFAULT '0' COMMENT '发送者是否删除(0未删除  1标记删除  2彻底删除)',
  `DeleteTime` datetime DEFAULT NULL COMMENT '发送者删除时间',
  `IsDraft` tinyint(1) DEFAULT '0' COMMENT '是否草稿',
  `IsTag` tinyint(1) DEFAULT '0' COMMENT '是否被标记',
  `OrderNum` int(11) DEFAULT '0' COMMENT '序号',
  `Status` tinyint(4) DEFAULT '0' COMMENT '0:未读1：已读2：回复3：转发4：全部转发 ',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='邮件内容表';

-- ----------------------------
-- Table structure for mailinbox
-- ----------------------------
DROP TABLE IF EXISTS `mailinbox`;
CREATE TABLE `mailinbox` (
  `ID` int(20) NOT NULL AUTO_INCREMENT COMMENT '收件箱',
  `ReceiverID` int(11) DEFAULT NULL COMMENT '接收者ID,0 表示为所有人',
  `SenderID` int(11) DEFAULT NULL,
  `MailID` int(11) DEFAULT NULL,
  `CreateTime` datetime DEFAULT NULL COMMENT '修改时间',
  `ReceiveType ` tinyint(4) DEFAULT NULL COMMENT '接收类型；0：普收 1：抄收 2：密收',
  `IsRead` tinyint(1) DEFAULT '0' COMMENT '接收者是否已阅',
  `ReadTime` datetime DEFAULT NULL COMMENT '阅读时间',
  `IsDel` tinyint(4) DEFAULT '0' COMMENT '接收者是否删除(0未删除  1标记删除  2彻底删除)',
  `DeleteTime` datetime DEFAULT NULL COMMENT '接收者删除时间',
  `IsTag` tinyint(1) DEFAULT '0' COMMENT '是否被标记',
  `IsReply` tinyint(1) DEFAULT '0' COMMENT '是否回复',
  `ReplyID` int(11) DEFAULT NULL COMMENT '回复邮件ID',
  `OrderNum` int(11) DEFAULT '0' COMMENT '序号',
  `Status` tinyint(4) DEFAULT '0' COMMENT '0:未读1：已读2：回复3：转发4：全部转发 ',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='收件箱';

-- ----------------------------
-- Table structure for mailoutbox
-- ----------------------------
DROP TABLE IF EXISTS `mailoutbox`;
CREATE TABLE `mailoutbox` (
  `ID` int(20) NOT NULL AUTO_INCREMENT COMMENT '发件箱',
  `SenderID` int(11) DEFAULT NULL COMMENT '发送者ID，0表示系统',
  `ReceiverID` varchar(100) DEFAULT NULL COMMENT '接收者ID,0 表示为所有人',
  `CopyerID` varchar(100) DEFAULT NULL COMMENT '抄送ID,0 表示为所有人',
  `SecreterID` varchar(100) DEFAULT NULL COMMENT '密送ID,0 表示为所有人',
  `MailID` int(11) DEFAULT NULL,
  `CreateTime` datetime DEFAULT NULL COMMENT '创建时间',
  `UpdateTime` datetime DEFAULT NULL COMMENT '修改时间',
  `IsSend` tinyint(1) DEFAULT '0' COMMENT '是否发送',
  `SendTime` datetime DEFAULT NULL COMMENT '发送时间',
  `SendType ` tinyint(4) DEFAULT NULL COMMENT '发送类型；0：普通1：急件',
  `IsDel` tinyint(4) DEFAULT '0' COMMENT '发送者是否删除(0未删除  1标记删除  2彻底删除)',
  `DeleteTime` datetime DEFAULT NULL COMMENT '发送者删除时间',
  `IsDraft` tinyint(1) DEFAULT '0' COMMENT '是否草稿',
  `IsTag` tinyint(1) DEFAULT '0' COMMENT '是否被标记',
  `OrderNum` int(11) DEFAULT '0' COMMENT '序号',
  `Status` tinyint(4) DEFAULT '0' COMMENT '0:未读1：已读2：回复3：转发4：全部转发 ',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='发件箱';

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `ID` varchar(18) NOT NULL COMMENT '用户表',
  `Name` varchar(18) DEFAULT NULL COMMENT '用户名',
  `NickName` varchar(18) DEFAULT NULL COMMENT '用户名',
  `Gender` tinyint(1) DEFAULT '0' COMMENT '性别（1男，0女）',
  `Birthday` datetime DEFAULT NULL COMMENT '生日',
  `HeadPic` varchar(100) DEFAULT NULL COMMENT '头像',
  `SpacePic` varchar(100) DEFAULT NULL COMMENT '空间头图',
  `Passwd` varchar(32) DEFAULT NULL COMMENT '密码',
  `Mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `Email` varchar(32) DEFAULT NULL COMMENT '电子邮件',
  `QQ` varchar(18) DEFAULT NULL,
  `CreateTime` datetime DEFAULT NULL COMMENT '注册时间',
  `Address` varchar(100) DEFAULT NULL COMMENT '地址',
  `PostCode` varchar(6) DEFAULT NULL COMMENT '邮编',
  `Level` tinyint(4) DEFAULT '0' COMMENT '等级',
  `LastLoginTime` datetime DEFAULT NULL COMMENT '最后登录时间',
  `IsLock` tinyint(1) DEFAULT '0' COMMENT '用户是否锁定，锁定后就不能登录,1是0否',
  `IsLogin` tinyint(1) DEFAULT '0' COMMENT '是否登录，1 login, 0 logout',
  `LockTime` datetime DEFAULT NULL COMMENT '账户锁住时间',
  `EncryptedID` varchar(32) DEFAULT NULL COMMENT '加密的用户ID',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户消息表',
  `SenderID` int(11) DEFAULT NULL COMMENT '发送者ID，0表示系统',
  `ReceiverID` int(11) DEFAULT NULL COMMENT '接收者ID,0 表示为所有人',
  `CopyerID` varchar(500) DEFAULT NULL COMMENT '抄送ID,0 表示为所有人',
  `SecreterID` varchar(500) DEFAULT NULL COMMENT '密送ID,0 表示为所有人',
  `Title` varchar(50) DEFAULT NULL COMMENT '主题',
  `Content` varchar(1000) DEFAULT NULL COMMENT '内容',
  `Icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `Url` varchar(255) DEFAULT NULL COMMENT '链接',
  `CreateTime` datetime DEFAULT NULL COMMENT '创建时间',
  `UpdateTime` datetime DEFAULT NULL COMMENT '修改时间',
  `IsSend` tinyint(1) DEFAULT '0' COMMENT '是否发送',
  `SendTime` datetime DEFAULT NULL COMMENT '发送时间',
  `SendType` int(4) DEFAULT NULL COMMENT '发送类型；0：普通1：急件',
  `IsRead` tinyint(1) DEFAULT '0' COMMENT '接收者是否已阅',
  `ReadTime` datetime DEFAULT NULL COMMENT '阅读时间',
  `IsReceiverDelete` int(1) DEFAULT '0' COMMENT '接收者是否删除(0未删除  1标记删除  2彻底删除)',
  `ReceiverDeleteTime` datetime DEFAULT NULL COMMENT '接收者删除时间',
  `IsSenderDelete` int(1) DEFAULT '0' COMMENT '发送者是否删除(0未删除  1标记删除  2彻底删除)',
  `SenderDeleteTime` datetime DEFAULT NULL COMMENT '发送者删除时间',
  `IsDraft` tinyint(1) DEFAULT '0' COMMENT '是否草稿',
  `IsTag` tinyint(1) DEFAULT '0' COMMENT '是否被标记',
  `IsReply` tinyint(1) DEFAULT '0' COMMENT '是否回复',
  `ReplyID` int(11) DEFAULT NULL COMMENT '回复邮件ID',
  `OrderNum` int(11) DEFAULT '0' COMMENT '序号',
  `Status` int(4) DEFAULT '0' COMMENT '0:未读1：已读2：回复3：转发4：全部转发 ',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户消息表';

-- ----------------------------
-- Table structure for right
-- ----------------------------
DROP TABLE IF EXISTS `right`;
CREATE TABLE `right` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单导航',
  `RightCode` char(4) DEFAULT NULL,
  `RightAction` varchar(50) DEFAULT NULL COMMENT '定位到Action',
  `RightController` varchar(50) DEFAULT NULL COMMENT '定位到Conroller',
  `RightName` varchar(45) DEFAULT NULL,
  `RightType` int(11) DEFAULT NULL COMMENT '权限类型0=一级菜单（没入口）   1=有入口的（一级或二级菜单） 2=按钮',
  `ParentID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单导航';

-- ----------------------------
-- Table structure for template
-- ----------------------------
DROP TABLE IF EXISTS `template`;
CREATE TABLE `template` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '邮件模板表',
  `TemplateTypeID` int(11) DEFAULT NULL COMMENT '短信类型ID',
  `Title` varchar(255) DEFAULT NULL COMMENT '短信标题',
  `Content` varchar(500) DEFAULT NULL,
  `SideNote` varchar(255) DEFAULT NULL COMMENT '备注',
  `CreateTime` datetime DEFAULT NULL,
  `UpdateTime` datetime DEFAULT NULL,
  `OrderNum` int(11) DEFAULT NULL,
  `IsDelete` tinyint(1) DEFAULT NULL,
  `Status` tinyint(4) DEFAULT NULL COMMENT '0=待受理 （1、2=审核中）1=编审审核通过 2=技术支持提交到第三方 3=审核未通过   4=审核通过',
  `Description` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邮件模板表';

-- ----------------------------
-- Table structure for templatetype
-- ----------------------------
DROP TABLE IF EXISTS `templatetype`;
CREATE TABLE `templatetype` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '模板类型表',
  `TypeName` varchar(100) DEFAULT NULL,
  `IsDelete` tinyint(1) DEFAULT '0' COMMENT '是否使用',
  `Status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模板类型表';

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permissionName` varchar(50) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `roleId` (`roleId`),
  CONSTRAINT `t_permission_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `roleId` (`roleId`),
  CONSTRAINT `t_user_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(32) NOT NULL,
  `name` varchar(18) DEFAULT NULL,
  `pswd` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
