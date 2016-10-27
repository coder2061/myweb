/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : quartz2

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-10-27 21:26:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for qrtz_scheduler_job
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_job`;
CREATE TABLE `qrtz_scheduler_job` (
  `JOB_ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `JOB_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `JOB_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  `JOB_CLASS_NAME` varchar(250) COLLATE utf8_bin NOT NULL,
  `CRON_EXPRESSION` varchar(120) COLLATE utf8_bin DEFAULT NULL,
  `REPEAT_COUNT` bigint(13) DEFAULT NULL,
  `REPEAT_INTERVAL` bigint(13) NOT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) COLLATE utf8_bin DEFAULT 'WAITING',
  `TRIGGER_TYPE` varchar(8) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME` bigint(13) DEFAULT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `DESCRIPTION` varchar(250) COLLATE utf8_bin DEFAULT NULL,
  `JOB_STATUS` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '任务状态 0禁用 1启用 2删除',
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`JOB_NAME`,`JOB_GROUP`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`JOB_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='自定义作业调度配置表';
