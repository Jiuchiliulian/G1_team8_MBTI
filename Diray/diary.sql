/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80033 (8.0.33)
 Source Host           : localhost:3306
 Source Schema         : diary

 Target Server Type    : MySQL
 Target Server Version : 80033 (8.0.33)
 File Encoding         : 65001

 Date: 04/06/2024 00:21:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `comment_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论id',
  `diary_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '日记id',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评论内容',
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户id',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`comment_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('c001', 'd001', '同感，今天的确很不错！', 'u002', '2024-05-10 10:00:00', '2024-05-10 10:00:00');
INSERT INTO `comment` VALUES ('c002', 'd001', '我这里却是下雨了，好羡慕你。', 'u003', '2024-05-10 10:05:00', '2024-05-10 10:05:00');
INSERT INTO `comment` VALUES ('c003', 'd002', '长城是我的梦想之地！', 'u001', '2024-05-11 11:00:00', '2024-05-11 11:00:00');
INSERT INTO `comment` VALUES ('c004', 'd003', '这本书我也很喜欢！', 'u004', '2024-05-12 12:00:00', '2024-05-12 12:00:00');
INSERT INTO `comment` VALUES ('c005', 'd004', '健身很重要，加油！', 'u005', '2024-05-13 13:00:00', '2024-05-13 13:00:00');
INSERT INTO `comment` VALUES ('dd297d5ec54d965d0803a93e40b3f810', NULL, '测试评论001', NULL, '2024-06-03 23:18:38', NULL);
INSERT INTO `comment` VALUES ('f632dbdec4d8f85cd5b4b492e087903a', 'd001', '测试评论001', NULL, '2024-06-03 23:19:27', NULL);

-- ----------------------------
-- Table structure for diary
-- ----------------------------
DROP TABLE IF EXISTS `diary`;
CREATE TABLE `diary`  (
  `diary_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '日记ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题',
  `Content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '内容',
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发布用户id',
  `created_at` datetime NULL DEFAULT NULL COMMENT '发布时间',
  `comments_count` int NULL DEFAULT NULL COMMENT '评论数量',
  `likes_count` int NULL DEFAULT NULL COMMENT '点赞数量',
  PRIMARY KEY (`diary_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '日记表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of diary
-- ----------------------------
INSERT INTO `diary` VALUES ('3dd589a9ca50461cd01b5e3e5b59a8c0', '测试日记标题', '1234', NULL, '2024-06-03 22:39:07', NULL, NULL);
INSERT INTO `diary` VALUES ('ca68e2f6a5a2433a0ef8218a14f39185', '123', '123', NULL, '2024-06-03 22:38:47', NULL, NULL);
INSERT INTO `diary` VALUES ('d001', '今日感想', '今天的天气真好，心情也非常愉快！', 'u001', '2024-05-10 09:00:00', 2, 6);
INSERT INTO `diary` VALUES ('d002', '旅行日记', '昨天去了长城，体验非常棒！', 'u002', '2024-05-11 10:00:00', 3, 8);
INSERT INTO `diary` VALUES ('d003', '读书笔记', '刚读完《解忧杂货店》，感触良多。', 'u003', '2024-05-12 11:00:00', 1, 3);
INSERT INTO `diary` VALUES ('d004', '健身记录', '今天去健身房锻炼了两小时，感觉很好。', 'u004', '2024-05-13 12:00:00', 4, 6);
INSERT INTO `diary` VALUES ('d005', '工作总结', '项目终于完成了，总结了一下经验和教训。', 'u005', '2024-05-14 13:00:00', 5, 10);

-- ----------------------------
-- Table structure for likes
-- ----------------------------
DROP TABLE IF EXISTS `likes`;
CREATE TABLE `likes`  (
  `like_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '点赞id',
  `diary_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '日记id',
  `use_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`like_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '点赞表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of likes
-- ----------------------------
INSERT INTO `likes` VALUES ('87264a041efc369d103a5667eafeb69b', 'd001', NULL);
INSERT INTO `likes` VALUES ('l001', 'd001', 'u002');
INSERT INTO `likes` VALUES ('l002', 'd001', 'u003');
INSERT INTO `likes` VALUES ('l003', 'd002', 'u001');
INSERT INTO `likes` VALUES ('l004', 'd003', 'u004');
INSERT INTO `likes` VALUES ('l005', 'd004', 'u005');

-- ----------------------------
-- Table structure for report
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report`  (
  `report_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '举报id',
  `report_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '举报类型',
  `target_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '目标id',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '举报内容',
  `report_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '举报理由',
  `status` int NULL DEFAULT NULL COMMENT '0:待审核 1:已审核',
  `action` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作类型：可选值包括 \"delete\"、\"block\"、\"warn\" 等',
  `operation_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作理由',
  PRIMARY KEY (`report_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '举报表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of report
-- ----------------------------
INSERT INTO `report` VALUES ('6285baf4014fbd289524be2965773493', 'diary', 'd001', '测试举报内容', '测试举报理由', 0, NULL, NULL);
INSERT INTO `report` VALUES ('r001', 'comment', 'c001', NULL, '含有不当言论', 0, 'warn', '不恰当的表达');
INSERT INTO `report` VALUES ('r002', 'diary', 'd002', NULL, '误导信息', 1, 'delete', '虚假信息');
INSERT INTO `report` VALUES ('r003', 'comment', 'u005', NULL, '违反社区规定', 1, 'block', '重复违规行为');
INSERT INTO `report` VALUES ('r004', 'diary', 'd003', NULL, '抄袭内容', 0, 'warn', '未标明来源');
INSERT INTO `report` VALUES ('r005', 'comment', 'c004', NULL, '不文明互动', 1, 'block', '测试');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户id',
  `username` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电子邮箱',
  `mobile_phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像路径',
  `registration_date` datetime NULL DEFAULT NULL COMMENT '注册时间',
  `last_login` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `status` int NULL DEFAULT NULL COMMENT '0: 禁用，1:启用',
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '禁用原因',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('459a0085cbdf9ecdecb6f394d97b8fed', 'root', 'root', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('u001', 'admin', '123456', 'zhangsan@sample.com', '1388888888', 'avatar1.png', '2024-05-01 12:00:00', '2024-05-10 08:00:00', 1, NULL);
INSERT INTO `user` VALUES ('u002', '李四', 'abcdef', 'lisi@sample.com', '13800002222', 'avatar2.png', '2024-05-02 12:00:00', '2024-05-11 08:00:00', 1, NULL);
INSERT INTO `user` VALUES ('u003', '王五', 'pass123', 'wangwu@sample.com', '13800003333', 'avatar3.png', '2024-05-03 12:00:00', '2024-05-12 08:00:00', 1, NULL);
INSERT INTO `user` VALUES ('u004', '赵六', 'xyz789', 'zhaoliu@sample.com', '13800004444', 'avatar4.png', '2024-05-04 12:00:00', '2024-05-13 08:00:00', 0, '测试');
INSERT INTO `user` VALUES ('u005', '钱七', 'secure456', 'qianqi@sample.com', '13800005555', 'avatar5.png', '2024-05-05 12:00:00', '2024-05-14 08:00:00', 0, '违规操作');

SET FOREIGN_KEY_CHECKS = 1;
