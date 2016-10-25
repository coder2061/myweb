/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : myapp

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-10-25 16:00:50
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
-- Records of accesslog
-- ----------------------------

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
-- Records of admin
-- ----------------------------

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
-- Records of attachment
-- ----------------------------

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
-- Records of basic
-- ----------------------------

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
-- Records of config
-- ----------------------------

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
-- Records of contact
-- ----------------------------
INSERT INTO `contact` VALUES ('1', '1', '4', '1', '姜子牙', '18779885539', 'jiangziya@jxau.com', '625337923', null, '北京朝阳', '2016-04-17 17:23:20', '2016-04-17 17:23:23', '0', '0', '1');
INSERT INTO `contact` VALUES ('2', '2', '4', '1', 'Jade', '18779885540', 'jiang@jxau.com', '625337924', null, '北京朝阳', '2016-04-17 18:08:46', '2016-04-17 18:08:48', '0', '0', '1');
INSERT INTO `contact` VALUES ('3', '3', '4', '2', 'aaa', '18779885512', 'jiang0@jxau.com', '66666666', null, 'beijing chaoyang', null, '2016-04-19 22:43:05', '0', '0', '1');
INSERT INTO `contact` VALUES ('4', '5', '4', '3', 'qqq', '18779885512', 'jiang1@jxau.com', '1111111111111', null, '北京朝阳', null, null, '0', '0', '1');
INSERT INTO `contact` VALUES ('5', '6', '4', '3', 'aaa', '18779885512', 'jiang2@jxau.com', '555555555', null, '望京', null, null, '0', '0', '1');

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
-- Records of contactgroup
-- ----------------------------
INSERT INTO `contactgroup` VALUES ('1', '亲戚', null, '2016-04-17 16:59:32', '1', '0', '0');
INSERT INTO `contactgroup` VALUES ('2', '朋友', null, '2016-04-17 17:00:23', '2', '0', '0');
INSERT INTO `contactgroup` VALUES ('3', '同学', null, '2016-04-17 17:00:25', '3', '0', '0');
INSERT INTO `contactgroup` VALUES ('4', '同事', null, '2016-04-17 17:00:48', '4', '0', '0');

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
-- Records of content
-- ----------------------------
INSERT INTO `content` VALUES ('2', null, '<p>9090<br/></p>', '2016-08-28 21:03:28');
INSERT INTO `content` VALUES ('7', '你好', 'hah', '都会死');
INSERT INTO `content` VALUES ('9', null, '<p>说的是都是<br/></p>', '2016-08-28 21:29:28');
INSERT INTO `content` VALUES ('10', null, '哈哈你说的是呢<br/>', '2016-08-28 21:30:28');
INSERT INTO `content` VALUES ('11', null, '<p>你说什么呢<br/></p>', '2016-08-28 23:53:28');
INSERT INTO `content` VALUES ('12', null, '<p>你好啊<img src=\"http://img.baidu.com/hi/jx2/j_0026.gif\"/><br/></p>', '2016-08-29 00:28:29');
INSERT INTO `content` VALUES ('13', null, '<p>nihao啊</p>', '2016-08-29 00:31:29');
INSERT INTO `content` VALUES ('14', null, '说的', '2016-08-29 00:33:29');
INSERT INTO `content` VALUES ('15', null, '哈哈哈哈<br/>', '2016-08-29 00:33:29');
INSERT INTO `content` VALUES ('16', null, '你在干甚呢<br/>', '2016-08-29 00:33:29');
INSERT INTO `content` VALUES ('17', null, '<p>搞好久</p>', '2016-08-29 01:00:29');
INSERT INTO `content` VALUES ('18', null, '<p>8989<br/></p>', '2016-08-29 01:00:29');
INSERT INTO `content` VALUES ('19', null, '9899', '2016-08-29 01:01:29');
INSERT INTO `content` VALUES ('20', null, '家哈哈哈', '2016-08-29 01:01:29');
INSERT INTO `content` VALUES ('21', null, '<p>说的是<br/></p>', '2016-08-29 01:02:29');
INSERT INTO `content` VALUES ('22', null, '<p>哈哈</p>', '2016-08-29 01:02:29');
INSERT INTO `content` VALUES ('23', null, '<p>你说什么<br/></p>', '2016-08-29 01:02:29');
INSERT INTO `content` VALUES ('24', null, '909', '2016-08-29 01:03:29');
INSERT INTO `content` VALUES ('25', null, '<p>你好<br/></p>', '2016-08-29 01:06:29');
INSERT INTO `content` VALUES ('26', null, '你在干嘛呢<br/>', '2016-08-29 01:06:29');
INSERT INTO `content` VALUES ('27', null, '<p>积极</p>', '2016-08-29 01:06:29');
INSERT INTO `content` VALUES ('28', null, '7878', '2016-08-29 01:06:29');

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
-- Records of feedback
-- ----------------------------

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
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES ('1', '2', '0', '4', '0', '/myzone/member/login', '2016-04-16 20:20:28', '用户登录', 'http://localhost:8080/myzone/member/login?account=jyf&passwd=123', '1');
INSERT INTO `log` VALUES ('2', '1', '0', '4', '0', '/myzone/member/register', '2016-04-16 20:50:32', '用户注册', 'http://localhost:8080/myzone/member/register?account=aaa&passwd=123', '0');
INSERT INTO `log` VALUES ('3', '1', '0', '4', '0', '/myzone/member/register', '2016-04-16 20:57:35', '用户注册', 'http://localhost:8080/myzone/member/register?account=abc1&passwd=123', '0');
INSERT INTO `log` VALUES ('4', '2', '0', '4', '0', '/myzone/member/login', '2016-04-16 20:57:53', '用户登录', 'http://localhost:8080/myzone/member/login?account=abc1&passwd=123&isRember=1', '0');
INSERT INTO `log` VALUES ('5', '2', '0', '4', '0', '/myzone/member/login', '2016-04-16 20:59:10', '用户登录', 'http://localhost:8080/myzone/member/login?account=abc1&passwd=123&isRember=1', '0');
INSERT INTO `log` VALUES ('6', '2', '0', '4', '0', '/myzone/member/login', '2016-04-16 21:39:04', '用户登录', 'http://localhost:8080/myzone/member/login?account=jyf&passwd=123', '0');
INSERT INTO `log` VALUES ('7', '2', '0', '4', '0', '/myzone/member/login', '2016-04-16 21:39:56', '用户登录', 'http://localhost:8080/myzone/member/login?account=jyf&passwd=123', '0');
INSERT INTO `log` VALUES ('8', '2', '0', '4', '0', '/myzone/member/login', '2016-04-16 21:42:35', '用户登录', 'http://localhost:8080/myzone/member/login?account=jyf&passwd=123', '0');
INSERT INTO `log` VALUES ('9', '2', '0', '4', '0', '/myzone/member/login', '2016-04-17 10:40:25', '用户登录', 'http://localhost:8080/myzone/member/login?account=jyf&passwd=123&isRember=1', '0');
INSERT INTO `log` VALUES ('10', '2', '0', '4', '0', '/myzone/member/login', '2016-04-17 10:48:46', '用户登录', 'http://localhost:8080/myzone/member/login?account=jyf&passwd=123&isRember=1', '0');
INSERT INTO `log` VALUES ('11', '2', '0', '4', '0', '/myzone/member/login', '2016-04-17 14:52:36', '用户登录', 'http://localhost:8080/myzone/member/login?account=jyf&passwd=123', '0');
INSERT INTO `log` VALUES ('12', '2', '0', '4', '0', '/myzone/member/login', '2016-04-17 15:36:05', '用户登录', 'http://localhost:8080/myzone/member/login?account=jyf&passwd=123', '0');
INSERT INTO `log` VALUES ('13', '2', '0', '4', '0', '/myzone/member/login', '2016-04-17 16:09:48', '用户登录', 'http://localhost:8080/myzone/member/login?account=jyf&passwd=123', '0');
INSERT INTO `log` VALUES ('14', '2', '0', '4', '0', '/myzone/member/login', '2016-04-17 16:15:07', '用户登录', 'http://localhost:8080/myzone/member/login?account=jyf&passwd=123', '0');
INSERT INTO `log` VALUES ('15', '2', '0', '4', '0', '/myzone/member/login', '2016-04-17 16:15:33', '用户登录', 'http://localhost:8080/myzone/member/login?account=jyf&passwd=123', '0');
INSERT INTO `log` VALUES ('16', '2', '0', '4', '0', '/myzone/member/login', '2016-04-17 17:26:10', '用户登录', 'http://localhost:8080/myzone/member/login?account=admin&passwd=123', '0');
INSERT INTO `log` VALUES ('17', '2', '0', '4', '0', '/myzone/member/login', '2016-04-17 17:35:05', '用户登录', 'http://localhost:8080/myzone/member/login?account=admin&passwd=123', '0');
INSERT INTO `log` VALUES ('18', '2', '0', '4', '0', '/myzone/member/login', '2016-04-17 18:36:53', '用户登录', 'http://localhost:8080/myzone/member/login?account=admin&passwd=123', '0');
INSERT INTO `log` VALUES ('19', '2', '0', '4', '0', '/myzone/member/login', '2016-04-17 19:18:46', '用户登录', 'http://localhost:8080/myzone/member/login?account=admin&passwd=123&isRember=1', '0');
INSERT INTO `log` VALUES ('20', '2', '0', '4', '0', '/myzone/member/login', '2016-04-17 20:55:19', '用户登录', 'http://localhost:8080/myzone/member/login?account=admin&passwd=123', '0');
INSERT INTO `log` VALUES ('21', '2', '0', '4', '0', '/myzone/member/login', '2016-04-17 21:50:06', '用户登录', 'http://localhost:8080/myzone/member/login?account=admin&passwd=123', '0');
INSERT INTO `log` VALUES ('22', '2', '0', '4', '0', '/myzone/member/login', '2016-04-17 23:32:00', '用户登录', 'http://localhost:8080/myzone/member/login?account=admin&passwd=123', '0');
INSERT INTO `log` VALUES ('23', '2', '0', '4', '0', '/myzone/member/login', '2016-04-18 17:44:48', '用户登录', 'http://localhost:8080/myzone/member/login?account=admin&passwd=123', '0');
INSERT INTO `log` VALUES ('24', '2', '0', '4', '0', '/myzone/member/login', '2016-04-18 18:21:34', '用户登录', 'http://localhost:8080/myzone/member/login?account=admin&passwd=123&isRember=1', '0');
INSERT INTO `log` VALUES ('25', '2', '0', '4', '0', '/myzone/member/login', '2016-04-18 20:24:18', '用户登录', 'http://localhost:8080/myzone/member/login?account=admin&passwd=123', '0');
INSERT INTO `log` VALUES ('26', '2', '0', '4', '0', '/myzone/member/login', '2016-04-18 21:02:46', '用户登录', 'http://localhost:8080/myzone/member/login?account=admin&passwd=123', '0');
INSERT INTO `log` VALUES ('27', '2', '0', '4', '0', '/myzone/member/login', '2016-04-18 21:32:20', '用户登录', 'http://localhost:8080/myzone/member/login?account=admin&passwd=123', '0');
INSERT INTO `log` VALUES ('28', '2', '0', '4', '0', '/myzone/member/login', '2016-04-18 22:29:41', '用户登录', 'http://localhost:8080/myzone/member/login?account=admin&passwd=123', '0');
INSERT INTO `log` VALUES ('29', '2', '0', '4', '0', '/myzone/member/login', '2016-04-18 23:38:27', '用户登录', 'http://localhost:8080/myzone/member/login?account=admin&passwd=123', '0');
INSERT INTO `log` VALUES ('30', '2', '0', '4', '0', '/myzone/member/login', '2016-04-19 22:00:34', '用户登录', 'http://localhost:8080/myzone/member/login?account=admin&passwd=123', '0');
INSERT INTO `log` VALUES ('31', '2', '0', '4', '0', '/myzone/member/login', '2016-04-19 22:41:41', '用户登录', 'http://localhost:8080/myzone/member/login?account=admin&passwd=123', '0');
INSERT INTO `log` VALUES ('32', '5', '0', '4', '0', '/myzone/contact/delContact', '2016-04-19 22:41:56', '联系人ID：4', 'http://localhost:8080/myzone/contact/delContact?null', '0');
INSERT INTO `log` VALUES ('33', '4', '0', '4', '0', '/myzone/contact/saveContact', '2016-04-19 22:43:05', '联系人ID：3', 'http://localhost:8080/myzone/contact/saveContact?ContactID=3&Name=aaa&Mobile=18779885512&Email=jiang0%40jxau.com&QQ=66666666&ContactGroupID=2&Address=beijing+chaoyang&HeadPic=', '0');
INSERT INTO `log` VALUES ('34', '7', '0', '4', '0', '/myzone/member/editPasswd', '2016-04-19 22:49:43', '修改用户密码', 'http://localhost:8080/myzone/member/editPasswd?oldPasswd=123&newPasswd=111&rePasswd=111', '0');
INSERT INTO `log` VALUES ('35', '2', '0', '4', '0', '/myzone/member/login', '2016-04-19 23:10:20', '用户登录', 'http://localhost:8080/myzone/member/login?account=admin&passwd=111', '0');
INSERT INTO `log` VALUES ('36', '2', '0', '4', '0', '/myzone/member/login', '2016-04-19 23:14:36', '用户登录', 'http://localhost:8080/myzone/member/login?account=admin&passwd=111', '0');
INSERT INTO `log` VALUES ('37', '6', '0', '4', '0', '/myzone/member/editMember', '2016-04-19 23:15:10', '修改用户信息', 'http://localhost:8080/myzone/member/editMember?NameID=admin&MobileID=18779885546&EmailID=aaa%40jxau.com&QQID=1111111111&AddressID=beijing&HeadPicID=', '0');
INSERT INTO `log` VALUES ('38', '2', '0', '4', '0', '/myzone/member/login', '2016-04-19 23:54:01', '用户登录', 'http://localhost:8080/myzone/member/login?account=admin&passwd=111', '0');
INSERT INTO `log` VALUES ('39', '2', '0', '4', '0', '/myzone/member/login', '2016-04-20 18:17:29', '用户登录', 'http://localhost:8080/myzone/member/login?account=admin&passwd=111', '0');
INSERT INTO `log` VALUES ('40', '2', '0', '4', '0', '/myzone/member/login', '2016-04-20 20:16:32', '用户登录', 'http://localhost:8080/myzone/member/login?account=admin&passwd=111', '0');
INSERT INTO `log` VALUES ('41', '2', '0', '4', '0', '/myzone/member/login', '2016-04-20 20:19:58', '用户登录', 'http://localhost:8080/myzone/member/login?account=admin&passwd=111', '0');
INSERT INTO `log` VALUES ('42', '2', '0', '4', '0', '/myzone/member/login', '2016-04-20 20:25:05', '用户登录', 'http://localhost:8080/myzone/member/login?account=admin&passwd=111', '0');

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
-- Records of logtype
-- ----------------------------
INSERT INTO `logtype` VALUES ('1', '用户注册', '用户日志');
INSERT INTO `logtype` VALUES ('2', '用户登录', '用户日志');
INSERT INTO `logtype` VALUES ('3', '新增联系人', '联系人日志');
INSERT INTO `logtype` VALUES ('4', '修改联系人', '联系人日志');
INSERT INTO `logtype` VALUES ('5', '删除联系人', '联系人日志');
INSERT INTO `logtype` VALUES ('6', '修改用户信息', '用户日志');
INSERT INTO `logtype` VALUES ('7', '修改用户密码', '用户日志');

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
-- Records of mail
-- ----------------------------
INSERT INTO `mail` VALUES ('1', 'aaaa', 'aaaaa', null, null, '2016-04-18 23:41:50', '2016-04-18 23:41:52', '0', null, '0', '0', '0', '0');
INSERT INTO `mail` VALUES ('5', 'ccc', 'ddd', null, null, '2016-04-20 20:34:55', null, '0', null, '0', '0', '0', '0');
INSERT INTO `mail` VALUES ('6', 'www', 'wwwwwwwww', null, null, '2016-04-20 20:41:00', null, '0', null, '0', '0', '0', '0');

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
-- Records of mailinbox
-- ----------------------------
INSERT INTO `mailinbox` VALUES ('1', '2', '1', '1', '2016-04-18 23:42:13', '1', '0', null, '0', null, '0', '0', null, '0', '0');
INSERT INTO `mailinbox` VALUES ('2', '4', '1', '1', '2016-04-20 19:49:32', '1', '0', null, '0', null, '0', '0', null, '0', '0');
INSERT INTO `mailinbox` VALUES ('3', '5', null, '6', '2016-04-20 20:41:00', null, '0', null, '0', null, '0', '0', null, '0', '0');
INSERT INTO `mailinbox` VALUES ('4', '5', null, '6', '2016-04-20 20:41:00', null, '0', null, '0', null, '0', '0', null, '0', '0');

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
-- Records of mailoutbox
-- ----------------------------
INSERT INTO `mailoutbox` VALUES ('1', '1', '2,4', null, null, '1', '2016-04-18 23:43:18', '2016-04-18 23:43:20', '1', '2016-04-18 23:43:28', '1', '0', null, '0', '0', '0', '0');
INSERT INTO `mailoutbox` VALUES ('2', '4', '1', '2', null, '5', null, null, '0', null, null, '0', null, '1', '0', '0', '0');
INSERT INTO `mailoutbox` VALUES ('3', '4', '5', '6', null, '6', null, null, '0', null, null, '0', null, '0', '0', '0', '0');

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户表',
  `Name` varchar(60) DEFAULT NULL COMMENT '用户名',
  `NickName` varchar(60) DEFAULT NULL COMMENT '用户名',
  `Gender` tinyint(1) DEFAULT '0' COMMENT '性别（1男，0女）',
  `Birthday` datetime DEFAULT NULL COMMENT '生日',
  `HeadPic` varchar(100) DEFAULT NULL COMMENT '头像',
  `SpacePic` varchar(100) DEFAULT NULL COMMENT '空间头图',
  `Passwd` varchar(64) DEFAULT NULL COMMENT '密码',
  `Mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `Email` varchar(100) DEFAULT NULL COMMENT '电子邮件',
  `QQ` varchar(18) DEFAULT NULL,
  `CreateTime` datetime DEFAULT NULL COMMENT '注册时间',
  `Address` varchar(100) DEFAULT NULL COMMENT '地址',
  `PostCode` varchar(6) DEFAULT NULL COMMENT '邮编',
  `Level` tinyint(4) DEFAULT '0' COMMENT '等级',
  `LastLoginTime` datetime DEFAULT NULL COMMENT '最后登录时间',
  `IsLock` tinyint(1) DEFAULT '0' COMMENT '用户是否锁定，锁定后就不能登录,1是0否',
  `IsLogin` tinyint(1) DEFAULT '0' COMMENT '是否登录，1 login, 0 logout',
  `LockTime` datetime DEFAULT NULL COMMENT '账户锁住时间',
  `EncryptedID` varchar(100) DEFAULT NULL COMMENT '加密的用户ID',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  KEY `mobile` (`Mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('1', 'admin1', null, '0', null, null, null, '123456', '18779885539', 'Jade@jxau.com', null, null, null, null, '0', null, '0', '0', null, null);
INSERT INTO `member` VALUES ('2', 'admin2', null, '0', null, null, null, '123', null, null, null, null, null, null, '0', null, '0', '0', null, null);
INSERT INTO `member` VALUES ('3', 'admin3', null, '0', null, null, null, '123', null, null, null, null, null, null, '0', null, '0', '0', null, null);
INSERT INTO `member` VALUES ('4', 'admin', null, '0', null, null, null, '111', '18779885546', 'aaa@jxau.com', '1111111111', null, 'beijing', null, '0', null, '0', '1', null, null);
INSERT INTO `member` VALUES ('5', 'abc', null, '0', null, null, null, '111', null, null, null, null, null, null, '0', null, '0', '1', null, null);
INSERT INTO `member` VALUES ('6', 'abc1', null, '0', null, null, null, '123', null, null, null, null, null, null, '0', null, '0', '1', null, null);
INSERT INTO `member` VALUES ('7', 'abc2', null, '0', null, null, null, '123', null, null, null, null, null, null, '0', null, '0', '1', null, null);

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
-- Records of message
-- ----------------------------

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
-- Records of right
-- ----------------------------

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
-- Records of template
-- ----------------------------

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
-- Records of templatetype
-- ----------------------------

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
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('1', 'user:*', '1');
INSERT INTO `t_permission` VALUES ('2', 'student:*', '2');

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
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'admin');
INSERT INTO `t_role` VALUES ('2', 'teacher');

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
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'crossoverJie', '123456', '1');
INSERT INTO `t_user` VALUES ('2', 'aaa', '12345', '2');
INSERT INTO `t_user` VALUES ('3', 'bbb', '12345', null);
INSERT INTO `t_user` VALUES ('4', 'ccc', '12345', null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(18) DEFAULT NULL,
  `pswd` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'jade', '123');
