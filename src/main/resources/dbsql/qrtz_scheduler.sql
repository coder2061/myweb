/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : quartz2

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-10-26 19:27:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for qrtz_scheduler
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler`;
CREATE TABLE `qrtz_scheduler` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `JOB_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `JOB_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  `JOB_CLASS_NAME` varchar(250) COLLATE utf8_bin NOT NULL,
  `CRON` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) COLLATE utf8_bin DEFAULT 'WAITING',
  `TRIGGER_TYPE` varchar(8) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME` bigint(13) DEFAULT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `DESCRIPTION` varchar(250) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`JOB_NAME`,`JOB_GROUP`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='管理各种job的定时器配置表';

-- ----------------------------
-- Records of qrtz_scheduler
-- ----------------------------
