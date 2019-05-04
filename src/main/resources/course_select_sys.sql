/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : course_select_sys

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 04/05/2019 10:34:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for college
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college`  (
  `college_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '院系id',
  `college_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '院系名称',
  PRIMARY KEY (`college_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '院系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of college
-- ----------------------------
INSERT INTO `college` VALUES (1, '计算机学院');
INSERT INTO `college` VALUES (2, '马克思学院');
INSERT INTO `college` VALUES (3, '人武部');
INSERT INTO `college` VALUES (4, '外国语学院');
INSERT INTO `college` VALUES (6, '体育部');
INSERT INTO `college` VALUES (11, '教育科学技术学院');
INSERT INTO `college` VALUES (12, '管理学院');
INSERT INTO `college` VALUES (13, '学工处');
INSERT INTO `college` VALUES (14, '社会人口学院');
INSERT INTO `college` VALUES (15, '理学院');
INSERT INTO `college` VALUES (16, '光电学院');
INSERT INTO `college` VALUES (17, '实验中心');
INSERT INTO `college` VALUES (18, '通信学院');
INSERT INTO `college` VALUES (19, '传媒院');
INSERT INTO `college` VALUES (20, '经济学院');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `course_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '课程id',
  `course_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '课程代码',
  `course_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '课程名称',
  `teacher_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '教师id',
  `course_time` date NOT NULL DEFAULT '1996-01-01' COMMENT '开课时间',
  `class_room` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '开课地点',
  `course_week` int(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '课程学时',
  `course_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '必修课' COMMENT '课程类型',
  `college_id` int(11) UNSIGNED NOT NULL COMMENT '所属院系id',
  `score` float(5, 1) UNSIGNED NOT NULL DEFAULT 0.0 COMMENT '学分',
  `is_on` tinyint(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否开启了选课，默认0未开启',
  PRIMARY KEY (`course_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 103 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '课程表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (14, 'B0900171C', '形势与政策（上）', 'JS1002', '2019-02-23', '教2-101', 16, '必修课', 2, 2.0, 0);
INSERT INTO `course` VALUES (15, 'B0900181C', '形势与政策（下）', 'JS1004', '2018-10-01', '教3-102', 16, '必修课', 2, 2.0, 0);
INSERT INTO `course` VALUES (16, 'B0900161C', '军事理论', 'JS1005', '2018-09-01', '教2-202', 32, '必修课', 3, 1.0, 0);
INSERT INTO `course` VALUES (17, 'B0900032S', '中国近现代史纲要', 'JS1007', '2018-06-01', '教2-213', 24, '必修课', 2, 2.0, 0);
INSERT INTO `course` VALUES (18, 'B0900013S', '思想道德修养与法律基础', 'JS1008', '2018-03-04', '教2-210', 32, '必修课', 2, 2.0, 0);
INSERT INTO `course` VALUES (19, 'B0900023S', '马克思主义基本原理概论', 'JS1004', '2018-03-02', '教3-202', 32, '必修课', 2, 2.0, 0);
INSERT INTO `course` VALUES (20, 'B0900060S', '毛泽东思想和中国特色社会主义理论体系概论', 'JS1005', '2019-03-01', '教3-213', 72, '必修课', 2, 4.0, 0);
INSERT INTO `course` VALUES (21, 'B0800022S', '大学英语A(Ⅱ-Ⅳ)', 'JS1008', '2017-06-01', '教3-214', 144, '选修课', 4, 9.0, 1);
INSERT INTO `course` VALUES (22, 'B0800012S', '大学英语B(Ⅰ-Ⅳ)', 'JS1006', '2017-06-01', '教4-101', 192, '选修课', 4, 12.0, 1);
INSERT INTO `course` VALUES (23, 'B1000011C', '体育(Ⅰ-Ⅳ)', 'JS1007', '2017-09-01', '教4-210', 144, '必修课', 6, 4.0, 0);
INSERT INTO `course` VALUES (24, 'B0300011S', '高级语言程序设计', 'JS1005', '2017-09-01', '教4-211', 56, '必修课', 1, 4.0, 0);
INSERT INTO `course` VALUES (25, 'B3500011S', '大学生心理健康', 'JS1002', '2017-09-01', '教4-102', 8, '必修课', 11, 0.0, 0);
INSERT INTO `course` VALUES (26, 'B2201541C', '创新与创业管理B', 'JS1001', '2017-06-01', '教4-101', 32, '必修课', 12, 2.0, 0);
INSERT INTO `course` VALUES (27, 'B2201551S', '创业市场研究与开拓 B', 'JS1004', '2018-09-01', '教3-213', 32, '必修课', 12, 2.0, 0);
INSERT INTO `course` VALUES (28, 'B2110023S', '职业发展与就业指导', 'JS1006', '2018-09-01', '教4-210', 32, '必修课', 13, 1.0, 0);
INSERT INTO `course` VALUES (29, 'B0900101C', '大学语文', 'JS1008', '2015-09-01', '教2-313', 32, '必修课', 14, 2.0, 0);
INSERT INTO `course` VALUES (30, 'B0100011C', '现代管理科学基础', 'JS1005', '2018-09-01', '教3-301', 32, '必修课', 12, 2.0, 0);
INSERT INTO `course` VALUES (31, 'B0600121S', '高等数学A（上）', 'JS1004', '2016-09-01', '教2-313', 80, '必修课', 15, 5.0, 0);
INSERT INTO `course` VALUES (32, 'B0600131S', '高等数学A（下）', 'JS1006', '2017-03-05', '教2-313', 96, '必修课', 15, 6.0, 0);
INSERT INTO `course` VALUES (33, 'B0600031S', '线性代数与解析几何', 'JS1001', '2017-03-05', '教2-201', 48, '必修课', 15, 3.0, 0);
INSERT INTO `course` VALUES (34, 'B0600311S', '大学物理（上）', 'JS1005', '2017-09-01', '教4-112', 64, '必修课', 15, 4.0, 1);
INSERT INTO `course` VALUES (35, 'B0600321S', '大学物理（下）', 'JS1004', '2018-03-20', '教4-112', 48, '必修课', 15, 3.0, 1);
INSERT INTO `course` VALUES (36, 'B0600371S', '物理实验（上）', 'JS1002', '2017-09-01', '教4-234', 24, '必修课', 15, 2.0, 0);
INSERT INTO `course` VALUES (37, 'B0600381S', '物理实验（下）', 'JS1008', '2018-03-20', '教3-231', 24, '必修课', 15, 3.0, 0);
INSERT INTO `course` VALUES (38, 'B0301021C', '软件工程导论', 'JS1005', '2018-09-01', '教4-210', 16, '必修课', 1, 1.0, 0);
INSERT INTO `course` VALUES (39, 'B0300021S', '面向对象程序设计及C++', 'JS1004', '2015-09-01', '教4-211', 32, '必修课', 1, 2.0, 1);
INSERT INTO `course` VALUES (40, 'B0300053S', '数据结构', 'JS1006', '2018-09-01', '教4-102', 56, '必修课', 1, 4.0, 0);
INSERT INTO `course` VALUES (41, 'B0302021S', '离散数学', 'JS1001', '2016-09-01', '教4-101', 64, '必修课', 1, 4.0, 0);
INSERT INTO `course` VALUES (42, 'B0301572C', 'JAVA程序设计', 'JS1005', '2017-03-05', '教3-213', 32, '必修课', 1, 2.0, 0);
INSERT INTO `course` VALUES (43, 'B0600061S', '概率论与数理统计', 'JS1004', '2017-03-05', '教4-210', 48, '必修课', 15, 3.0, 0);
INSERT INTO `course` VALUES (44, 'B0301340S', '算法分析与设计', 'JS1002', '2017-09-01', '教2-313', 48, '必修课', 1, 3.0, 0);
INSERT INTO `course` VALUES (45, 'B0301061S', '汇编语言程序设计', 'JS1008', '2018-03-20', '教3-301', 32, '必修课', 1, 2.0, 0);
INSERT INTO `course` VALUES (46, 'B0301230S', 'Web技术（双语）', 'JS1005', '2017-09-01', '教2-313', 40, '必修课', 1, 2.0, 0);
INSERT INTO `course` VALUES (47, 'B0302271S', '软件工程', 'JS1004', '2018-03-20', '教2-313', 48, '必修课', 1, 3.0, 0);
INSERT INTO `course` VALUES (48, 'B0301082S', '操作系统 B', 'JS1006', '2018-09-01', '教2-201', 48, '必修课', 1, 3.0, 0);
INSERT INTO `course` VALUES (49, 'B0301114S', '数据库系统', 'JS1001', '2015-09-01', '教4-210', 56, '必修课', 1, 4.0, 0);
INSERT INTO `course` VALUES (50, 'B0400081S', '电工电子技术基础', 'JS1005', '2018-09-01', '教4-211', 48, '必修课', 16, 3.0, 0);
INSERT INTO `course` VALUES (51, 'B1100091S', '电工电子基础实验B', 'JS1004', '2016-09-01', '教4-102', 48, '必修课', 17, 3.0, 0);
INSERT INTO `course` VALUES (52, 'B0200034S', '信号与系统 C', 'JS1002', '2017-03-05', '教4-101', 32, '必修课', 18, 2.0, 0);
INSERT INTO `course` VALUES (53, 'B0400032S', '数字电路与逻辑设计 B', 'JS1008', '2017-03-05', '教3-213', 48, '必修课', 16, 3.0, 0);
INSERT INTO `course` VALUES (54, 'B0301172C', '专业英语(自学)', 'JS1006', '2017-09-01', '教4-210', 64, '必修课', 1, 0.0, 0);
INSERT INTO `course` VALUES (55, 'B0301561C', 'Windows高级软件开发', 'JS1001', '2018-03-20', '教3-301', 32, '选修课', 1, 2.0, 1);
INSERT INTO `course` VALUES (56, 'B0301072S', '微型计算机接口技术', 'JS1005', '2018-09-01', '教2-313', 32, '选修课', 1, 2.0, 1);
INSERT INTO `course` VALUES (57, 'B0301371S', 'Linux编程', 'JS1004', '2015-09-01', '教2-313', 32, '选修课', 1, 2.0, 1);
INSERT INTO `course` VALUES (58, 'B0301141S', '编译原理', 'JS1002', '2018-09-01', '教2-201', 48, '选修课', 1, 3.0, 1);
INSERT INTO `course` VALUES (59, 'B0302292C', '大型数据库技术', 'JS1008', '2016-09-01', '教4-210', 32, '选修课', 1, 2.0, 1);
INSERT INTO `course` VALUES (60, 'B0302161C', '嵌入式系统与开发', 'JS1006', '2017-03-05', '教4-211', 48, '选修课', 1, 3.0, 1);
INSERT INTO `course` VALUES (61, 'B0301152S', '计算机通信与网络', 'JS1005', '2018-03-20', '教4-102', 56, '必修课', 1, 4.0, 0);
INSERT INTO `course` VALUES (62, 'B0303091S', 'UML系统分析与设计', 'JS1004', '2018-09-01', '教4-101', 48, '必修课', 1, 3.0, 0);
INSERT INTO `course` VALUES (63, 'B0301602S', '软件测试（双语）', 'JS1002', '2015-09-01', '教3-213', 40, '必修课', 1, 2.0, 0);
INSERT INTO `course` VALUES (64, 'B0301391S', '软件项目管理', 'JS1008', '2018-09-01', '教4-210', 32, '选修课', 1, 2.0, 1);
INSERT INTO `course` VALUES (65, 'B0301381C', '软件体系结构', 'JS1006', '2016-09-01', '教3-301', 32, '选修课', 1, 2.0, 1);
INSERT INTO `course` VALUES (66, 'B0301352C', '软件开发方法', 'JS1001', '2017-03-05', '教2-313', 32, '选修课', 1, 2.0, 1);
INSERT INTO `course` VALUES (67, 'B0303081C', '云计算技术', 'JS1005', '2017-03-05', '教2-313', 32, '选修课', 1, 2.0, 1);
INSERT INTO `course` VALUES (68, 'B0303021C', '无线传感器网络技术', 'JS1004', '2017-09-01', '教2-201', 32, '选修课', 1, 3.0, 1);
INSERT INTO `course` VALUES (69, 'B0301521C', '计算机通信与网络实验', 'JS1002', '2018-03-20', '教4-210', 32, '选修课', 1, 2.0, 1);
INSERT INTO `course` VALUES (70, 'B0301582C', '计算机组成与结构', 'JS1005', '2018-09-01', '教4-211', 48, '选修课', 1, 2.0, 1);
INSERT INTO `course` VALUES (71, 'B1201371S', '信息安全技术', 'JS1004', '2015-09-01', '教4-102', 32, '选修课', 1, 2.0, 1);
INSERT INTO `course` VALUES (72, 'B1201272C', '人工智能', 'JS1002', '2018-09-01', '教3-301', 32, '选修课', 1, 2.0, 1);
INSERT INTO `course` VALUES (73, '00X110086', '中西文化比较', 'JS1001', '2018-03-20', '教3-301', 32, '选修课', 14, 2.0, 1);
INSERT INTO `course` VALUES (74, '00X090045', '中国经典文化的西方之旅', 'JS1005', '2018-09-01', '教2-313', 32, '选修课', 4, 2.0, 1);
INSERT INTO `course` VALUES (75, '00X090038', '英国文学作品选读', 'JS1004', '2015-09-01', '教2-313', 32, '选修课', 4, 2.0, 1);
INSERT INTO `course` VALUES (76, '00X080062', '西方音乐史', 'JS1002', '2018-09-01', '教2-201', 32, '选修课', 19, 2.0, 1);
INSERT INTO `course` VALUES (77, '00X080064', '中外美术作品赏析', 'JS1008', '2016-09-01', '教4-210', 32, '选修课', 19, 2.0, 1);
INSERT INTO `course` VALUES (78, '00X110055', '影视艺术审美与鉴赏', 'JS1006', '2017-03-05', '教4-211', 32, '选修课', 19, 2.0, 1);
INSERT INTO `course` VALUES (79, '00X080045', '中国民间美术赏析', 'JS1005', '2018-03-20', '教4-102', 32, '选修课', 19, 2.0, 1);
INSERT INTO `course` VALUES (80, '00X010001', '电信市场营销', 'JS1004', '2018-09-01', '教4-101', 32, '选修课', 12, 2.0, 1);
INSERT INTO `course` VALUES (81, '00X240001', '劳动法', 'JS1002', '2015-09-01', '教3-213', 32, '选修课', 2, 2.0, 1);
INSERT INTO `course` VALUES (82, '00X110091', '知识产权法', 'JS1008', '2018-09-01', '教4-210', 32, '选修课', 14, 2.0, 1);
INSERT INTO `course` VALUES (83, '00X020068', '宽带无线通信技术基础', 'JS1006', '2016-09-01', '教3-301', 32, '选修课', 18, 2.0, 1);
INSERT INTO `course` VALUES (84, '00X020074', '通信网概论', 'JS1001', '2017-03-05', '教2-313', 32, '选修课', 18, 2.0, 1);
INSERT INTO `course` VALUES (85, '00X060033', 'MSP430单片机原理与应用', 'JS1005', '2017-03-05', '教2-313', 32, '选修课', 16, 2.0, 1);
INSERT INTO `course` VALUES (86, '00X070008', '计算机图形学', 'JS1004', '2017-09-01', '教2-201', 48, '选修课', 15, 3.0, 1);
INSERT INTO `course` VALUES (87, '00X070004', '数学建模', 'JS1002', '2018-03-20', '教4-210', 48, '选修课', 15, 3.0, 1);
INSERT INTO `course` VALUES (88, '00X040082', 'ACM程序设计与创新基础', 'JS1005', '2018-09-01', '教4-211', 32, '选修课', 1, 2.0, 1);
INSERT INTO `course` VALUES (89, '00X010017', '技术创新管理', 'JS1004', '2015-09-01', '教4-102', 32, '选修课', 12, 2.0, 1);
INSERT INTO `course` VALUES (90, '00X020093', '创新思维与TRIZ创新方法及其应用', 'JS1002', '2018-09-01', '教3-301', 32, '选修课', 18, 2.0, 1);
INSERT INTO `course` VALUES (91, '00X060040', '电子设计与创新基础A', 'JS1001', '2018-03-20', '教3-301', 64, '选修课', 17, 4.0, 1);
INSERT INTO `course` VALUES (92, '00X060041', '电子设计与创新基础B', 'JS1005', '2018-09-01', '教2-313', 64, '选修课', 17, 4.0, 1);
INSERT INTO `course` VALUES (93, '00X160004', '科技创新方法与应用', 'JS1004', '2015-09-01', '教2-313', 32, '选修课', 16, 2.0, 1);
INSERT INTO `course` VALUES (94, 'B02000K2S', '通信原理 C', 'JS1005', '2017-03-05', '教2-313', 48, '选修课', 18, 3.0, 1);
INSERT INTO `course` VALUES (95, 'B02150K1S', '宽带无线技术', 'JS1004', '2017-09-01', '教2-201', 32, '选修课', 18, 2.0, 1);
INSERT INTO `course` VALUES (96, 'B01031K1C', '技术经济学', 'JS1002', '2018-03-20', '教4-210', 40, '选修课', 12, 2.0, 1);
INSERT INTO `course` VALUES (97, 'B01040K1C', '市场营销', 'JS1005', '2018-09-01', '教4-211', 40, '选修课', 12, 2.0, 1);
INSERT INTO `course` VALUES (98, 'B02150K2S', '宽带网络与交换技术', 'JS1004', '2015-09-01', '教4-102', 48, '选修课', 18, 3.0, 1);
INSERT INTO `course` VALUES (99, 'B02150K3C', '移动通信系统', 'JS1002', '2018-09-01', '教3-301', 32, '选修课', 18, 2.0, 1);
INSERT INTO `course` VALUES (100, 'B01060K1C', '电子商务', 'JS1001', '2018-03-20', '教3-301', 32, '选修课', 12, 2.0, 1);
INSERT INTO `course` VALUES (101, 'B1402051C', '创业与投融资', 'JS1005', '2018-09-01', '教2-313', 32, '选修课', 20, 2.0, 1);
INSERT INTO `course` VALUES (102, 'B1402041C', '创业经济学', 'JS1004', '2015-09-01', '教2-313', 32, '选修课', 20, 2.0, 1);

-- ----------------------------
-- Table structure for ranks
-- ----------------------------
DROP TABLE IF EXISTS `ranks`;
CREATE TABLE `ranks`  (
  `student_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学生id',
  `course_id` int(10) NOT NULL COMMENT '课程id',
  `rank` float(5, 1) UNSIGNED NOT NULL DEFAULT 0.0 COMMENT '总评分',
  PRIMARY KEY (`student_id`, `course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '角色名称',
  `permissions` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '权限',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'admin', '');
INSERT INTO `role` VALUES (2, 'teacher', '');
INSERT INTO `role` VALUES (3, 'student', '');

-- ----------------------------
-- Table structure for select_course
-- ----------------------------
DROP TABLE IF EXISTS `select_course`;
CREATE TABLE `select_course`  (
  `course_id` int(10) NOT NULL COMMENT '课程id',
  `student_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学生id',
  `mark` float(5, 1) UNSIGNED NULL DEFAULT NULL COMMENT '分数',
  `evaluation` float(5, 1) UNSIGNED NULL DEFAULT 0.0 COMMENT '对课程的评价',
  PRIMARY KEY (`course_id`, `student_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '学生成绩表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of select_course
-- ----------------------------
INSERT INTO `select_course` VALUES (21, 'B15041201', NULL, 0.1);
INSERT INTO `select_course` VALUES (21, 'B15041203', NULL, 0.1);
INSERT INTO `select_course` VALUES (21, 'B15041210', NULL, 0.1);
INSERT INTO `select_course` VALUES (22, 'B15041210', NULL, 0.1);
INSERT INTO `select_course` VALUES (39, 'B15041201', NULL, 0.0);
INSERT INTO `select_course` VALUES (39, 'B15041203', NULL, 0.0);
INSERT INTO `select_course` VALUES (39, 'B15041210', NULL, 0.0);
INSERT INTO `select_course` VALUES (39, 'B15041212', NULL, 0.0);
INSERT INTO `select_course` VALUES (39, 'B15041219', NULL, 0.0);
INSERT INTO `select_course` VALUES (55, 'B15041201', NULL, 0.1);
INSERT INTO `select_course` VALUES (55, 'B15041202', 86.0, 3.3);
INSERT INTO `select_course` VALUES (56, 'B15041212', NULL, 0.1);
INSERT INTO `select_course` VALUES (57, 'B15041201', NULL, 0.1);
INSERT INTO `select_course` VALUES (57, 'B15041202', NULL, 0.1);
INSERT INTO `select_course` VALUES (57, 'B15041203', NULL, 0.1);
INSERT INTO `select_course` VALUES (57, 'B15041210', NULL, 0.1);
INSERT INTO `select_course` VALUES (57, 'B15041212', NULL, 0.1);
INSERT INTO `select_course` VALUES (58, 'B15041210', NULL, 0.1);
INSERT INTO `select_course` VALUES (58, 'B15041212', 96.0, 4.3);
INSERT INTO `select_course` VALUES (59, 'B15041212', NULL, 0.1);
INSERT INTO `select_course` VALUES (72, 'B15041201', NULL, 0.1);
INSERT INTO `select_course` VALUES (72, 'B15041210', 89.0, 0.3);
INSERT INTO `select_course` VALUES (72, 'B15041212', NULL, 0.1);
INSERT INTO `select_course` VALUES (73, 'B15041203', NULL, 0.1);
INSERT INTO `select_course` VALUES (80, 'B15041202', NULL, 0.1);
INSERT INTO `select_course` VALUES (87, 'B15041202', 93.0, 0.3);
INSERT INTO `select_course` VALUES (88, 'B15041201', NULL, 0.1);
INSERT INTO `select_course` VALUES (88, 'B15041210', NULL, 0.1);
INSERT INTO `select_course` VALUES (88, 'B15041212', NULL, 0.1);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `stu_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学生学号',
  `stu_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学生姓名',
  `stu_sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '男' COMMENT '学生性别',
  `stu_birth_year` date NOT NULL DEFAULT '1996-01-01' COMMENT '学生出生年月日',
  `stu_grade` date NOT NULL DEFAULT '1996-01-01' COMMENT '学生入学时间',
  `college_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '学生所属院系id',
  PRIMARY KEY (`stu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '学生信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('B15041201', '小龙女', '女', '1997-08-15', '2015-09-01', 1);
INSERT INTO `student` VALUES ('B15041202', '容嬷嬷', '女', '1996-04-08', '2015-09-01', 11);
INSERT INTO `student` VALUES ('B15041203', '郭靖', '男', '1996-11-29', '2016-09-01', 1);
INSERT INTO `student` VALUES ('B15041210', '王涔宇', '男', '1996-02-22', '2015-09-01', 1);
INSERT INTO `student` VALUES ('B15041212', '刘小平', '男', '1996-11-29', '2015-09-01', 1);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `teacher_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '教师id',
  `teacher_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '教师姓名',
  `teacher_sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '男' COMMENT '教师性别，默认为男',
  `teacher_birth_year` date NOT NULL DEFAULT '1996-01-01' COMMENT '教师出生年月日',
  `teacher_degree` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '教师学历',
  `teacher_title` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '教师职称',
  `teacher_grade` date NOT NULL DEFAULT '1996-01-01' COMMENT '入职时间',
  `college_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '教师所属院系',
  PRIMARY KEY (`teacher_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '教师信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('JS1001', '张三', '男', '1991-03-23', '硕士', '研究生导师', '2017-09-01', 1);
INSERT INTO `teacher` VALUES ('JS1002', '李四', '女', '1988-02-13', '博士', '博士生导师', '2015-09-01', 3);
INSERT INTO `teacher` VALUES ('JS1004', '周伯通', '男', '1967-04-23', '硕士', '研究生导师', '2015-09-01', 2);
INSERT INTO `teacher` VALUES ('JS1005', '乔峰', '男', '1975-11-29', '博士', '博士生导师', '2015-09-01', 1);
INSERT INTO `teacher` VALUES ('JS1006', '王五', '男', '1985-11-29', '硕士', '教师', '2015-09-01', 1);
INSERT INTO `teacher` VALUES ('JS1007', '黄药师', '男', '1946-04-08', '博士', '药学导师', '1996-09-01', 11);
INSERT INTO `teacher` VALUES ('JS1008', '东方不败', '女', '1958-06-29', '硕士', '研究生导师', '1978-09-01', 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名称',
  `user_salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '盐值',
  `user_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户密码',
  `role_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户角色',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `username`(`user_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2, 'admin', '692030b5cacbcad4095fdf39b80fe375c1a28b7a', '36c476564a11ef553343dbf8f12ef4a3', 1);
INSERT INTO `user` VALUES (3, 'B15041210', '7ada342ba5ffa41921bffc408bd3e79505afbfb2', '1f02d6b66525b0daf992b44ae6a60d2c', 3);
INSERT INTO `user` VALUES (9, 'JS1001', '68a12a739c832b5f398b8bb88caeb54b4984df5e', '4ec81b9fddad38dec56e75d7d6ce7a4b', 2);
INSERT INTO `user` VALUES (10, 'JS1002', '52a9724588eb45e5fccd724a5c74dd2fea77e197', '5ae35a0554a39bf268a8442d097a29f4', 2);
INSERT INTO `user` VALUES (12, 'JS1004', '170bcb9be4598ac922121505031613f03519487d', 'cd8532f6da83e21c304105e69c0cda16', 2);
INSERT INTO `user` VALUES (13, 'JS1005', '34161a494621acf5f08f73b5717c281c797b0355', '76573c4fe20dfa7ef3a06d3c0c2a723b', 2);
INSERT INTO `user` VALUES (14, 'JS1006', 'b7a511599825ed3c8e2b5b2bf7b22b3cca5a5d0f', '28cf32f1bdf2bb3453af2869bfefe171', 2);
INSERT INTO `user` VALUES (16, 'B15041212', 'f0975d4baf7618535837e20ef23ad7cb282082ae', '9321cf22c4ebe3883d75ec350e539e39', 3);
INSERT INTO `user` VALUES (17, 'B15041201', '6ad9be9e41982bdf5db4f20e16ef4072d337a487', 'b21d94f50e067ed5fc05bb8c286d4cd9', 3);
INSERT INTO `user` VALUES (18, 'B15041202', '0ddd2e2f23446c79059673b95e953294e2f622ea', '186a6ab1c369f5f4566503da7f75fb9f', 3);
INSERT INTO `user` VALUES (19, 'B15041203', '55d24107a00cd8aee519a55564833bdad4abc8a3', 'd71e7057974f275f7f1f599b6d7604af', 3);
INSERT INTO `user` VALUES (20, 'JS1007', '1791a2db2046d59fcaf56ff42601d3119cb7cdbf', 'dde1065364f1c8a9a743c5f4a07abf8a', 2);
INSERT INTO `user` VALUES (21, 'JS1008', 'db47fd6ea62b19c0410b4074b7481680194ddf16', '7c74bed716aa46d0e36d9b37baed369c', 2);

SET FOREIGN_KEY_CHECKS = 1;
