/*
 Navicat MySQL Data Transfer

 Source Server         : 127.0.0.1_3306
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : 127.0.0.1:3306
 Source Schema         : cess

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 08/04/2020 11:58:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_menu
-- ----------------------------
DROP TABLE IF EXISTS `admin_menu`;
CREATE TABLE `admin_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '与 Vue 路由中的 path 对应，即地址路径',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '与 Vue 路由中的 name 属性对应',
  `name_zh` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '中文名称，用于渲染导航栏（菜单）界面',
  `icon_cls` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'element 图标类名，用于渲染菜单名称前的小图标',
  `component` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '组件名，用于解析路由对应的组件',
  `parent_id` int(11) DEFAULT NULL COMMENT '父节点 id，用于存储导航栏层级关系',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_menu
-- ----------------------------
INSERT INTO `admin_menu` VALUES (1, '/admin', 'AdminIndex', '首页', 'el-icon-s-home', 'AdminIndex', 0);
INSERT INTO `admin_menu` VALUES (2, '/admin/user', 'User', '用户管理', 'el-icon-user', 'AdminIndex', 0);
INSERT INTO `admin_menu` VALUES (3, '/admin/content', 'Content', '内容管理', 'el-icon-tickets', 'AdminIndex', 0);
INSERT INTO `admin_menu` VALUES (4, '/admin/system', 'System', '系统管理', 'el-icon-s-tools', 'AdminIndex', 0);
INSERT INTO `admin_menu` VALUES (5, '/admin/link', 'Link', '链接', 'el-icon-link', 'AdminIndex', 0);
INSERT INTO `admin_menu` VALUES (6, '/admin/user/profile', 'Profile', '用户信息', 'el-icon-s-custom', 'user/UserProfile', 2);
INSERT INTO `admin_menu` VALUES (7, '/admin/user/role', 'Role', '角色配置', 'el-icon-s-check', 'user/Role', 2);
INSERT INTO `admin_menu` VALUES (9, '/admin/content/department', 'Department', '就业部类', 'el-icon-school', 'content/department/Notice', 3);
INSERT INTO `admin_menu` VALUES (10, '/admin/content/student', 'Student', '学生类', 'el-icon-s-custom', 'content/student/GraduateInfo', 3);
INSERT INTO `admin_menu` VALUES (11, '/admin/content/enterprise', 'Enterprise', '企业类', 'el-icon-office-building', 'content/enterprise/Qualification', 3);
INSERT INTO `admin_menu` VALUES (12, '/admin/system/run', 'Run', '运行情况', 'el-icon-document', 'system/Run', 4);
INSERT INTO `admin_menu` VALUES (13, '/admin/system/database', 'DataBase', '备份恢复数据库', 'el-icon-coin', 'system/DataBase', 4);
INSERT INTO `admin_menu` VALUES (14, '/admin/system/log', 'Log', '操作日志', 'el-icon-s-management', 'system/Log', 4);
INSERT INTO `admin_menu` VALUES (15, '/admin/content/student/graduateInfo', 'GraduateInfo', '毕业生信息', 'el-icon-s-custom', 'content/student/GraduateInfo', 10);
INSERT INTO `admin_menu` VALUES (16, '/admin/content/department/notice', 'Notice', '公告管理', 'el-icon-message-solid', 'content/department/Notice', 9);
INSERT INTO `admin_menu` VALUES (17, '/admin/content/enterprise/qualification', 'Qualification', '审核', 'el-icon-document-checked', 'content/enterprise/Qualification', 11);
INSERT INTO `admin_menu` VALUES (18, '/admin/welcome', 'Welcome', '欢迎', 'el-icon-house', 'Welcome', 1);

-- ----------------------------
-- Table structure for admin_permission
-- ----------------------------
DROP TABLE IF EXISTS `admin_permission`;
CREATE TABLE `admin_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `desc_` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_permission
-- ----------------------------
INSERT INTO `admin_permission` VALUES (1, 'users_management', '用户管理', '/api/admin/user');
INSERT INTO `admin_permission` VALUES (2, 'system_management', '系统管理', '/api/admin/system');
INSERT INTO `admin_permission` VALUES (3, 'content_management', '内容管理', '/api/admin/content');
INSERT INTO `admin_permission` VALUES (4, 'roles_management', '角色管理', '/api/admin/role');

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `name_zh` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES (1, 'admin', '超级管理员', 1);
INSERT INTO `admin_role` VALUES (2, 'student', '学生', 0);
INSERT INTO `admin_role` VALUES (3, 'enterprise', '企业', 0);
INSERT INTO `admin_role` VALUES (4, 'department', '校就业部', 1);
INSERT INTO `admin_role` VALUES (5, 'leader', '校领导', 0);
INSERT INTO `admin_role` VALUES (11, 'adminContent', '内容管理员', 0);

-- ----------------------------
-- Table structure for admin_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_menu`;
CREATE TABLE `admin_role_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) DEFAULT NULL,
  `mid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 445 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_role_menu
-- ----------------------------
INSERT INTO `admin_role_menu` VALUES (416, 1, 1);
INSERT INTO `admin_role_menu` VALUES (417, 1, 2);
INSERT INTO `admin_role_menu` VALUES (418, 1, 6);
INSERT INTO `admin_role_menu` VALUES (419, 1, 7);
INSERT INTO `admin_role_menu` VALUES (420, 1, 3);
INSERT INTO `admin_role_menu` VALUES (421, 1, 9);
INSERT INTO `admin_role_menu` VALUES (422, 1, 10);
INSERT INTO `admin_role_menu` VALUES (423, 1, 15);
INSERT INTO `admin_role_menu` VALUES (424, 1, 11);
INSERT INTO `admin_role_menu` VALUES (425, 1, 4);
INSERT INTO `admin_role_menu` VALUES (426, 1, 12);
INSERT INTO `admin_role_menu` VALUES (427, 1, 13);
INSERT INTO `admin_role_menu` VALUES (428, 1, 14);
INSERT INTO `admin_role_menu` VALUES (429, 1, 5);
INSERT INTO `admin_role_menu` VALUES (430, 4, 2);
INSERT INTO `admin_role_menu` VALUES (431, 4, 6);
INSERT INTO `admin_role_menu` VALUES (432, 4, 7);
INSERT INTO `admin_role_menu` VALUES (433, 4, 3);
INSERT INTO `admin_role_menu` VALUES (434, 4, 9);
INSERT INTO `admin_role_menu` VALUES (435, 4, 10);
INSERT INTO `admin_role_menu` VALUES (436, 4, 15);
INSERT INTO `admin_role_menu` VALUES (437, 4, 11);
INSERT INTO `admin_role_menu` VALUES (439, 1, 16);
INSERT INTO `admin_role_menu` VALUES (440, 1, 17);
INSERT INTO `admin_role_menu` VALUES (443, 4, 18);
INSERT INTO `admin_role_menu` VALUES (444, 1, 18);

-- ----------------------------
-- Table structure for admin_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_permission`;
CREATE TABLE `admin_role_permission`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `rid` int(20) DEFAULT NULL,
  `pid` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_role_permission_role_1`(`rid`) USING BTREE,
  INDEX `fk_role_permission_permission_1`(`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 97 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_role_permission
-- ----------------------------
INSERT INTO `admin_role_permission` VALUES (23, 11, 3);
INSERT INTO `admin_role_permission` VALUES (91, 1, 1);
INSERT INTO `admin_role_permission` VALUES (92, 1, 2);
INSERT INTO `admin_role_permission` VALUES (93, 1, 3);
INSERT INTO `admin_role_permission` VALUES (94, 1, 4);
INSERT INTO `admin_role_permission` VALUES (95, 4, 1);
INSERT INTO `admin_role_permission` VALUES (96, 4, 3);

-- ----------------------------
-- Table structure for admin_user_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_user_role`;
CREATE TABLE `admin_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_operator_role_operator_1`(`uid`) USING BTREE,
  INDEX `fk_operator_role_role_1`(`rid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_user_role
-- ----------------------------
INSERT INTO `admin_user_role` VALUES (1, 2, 2);
INSERT INTO `admin_user_role` VALUES (2, 3, 2);
INSERT INTO `admin_user_role` VALUES (3, 1, 1);
INSERT INTO `admin_user_role` VALUES (4, 4, 5);
INSERT INTO `admin_user_role` VALUES (5, 5, 4);
INSERT INTO `admin_user_role` VALUES (6, 6, 3);
INSERT INTO `admin_user_role` VALUES (23, 26, 2);
INSERT INTO `admin_user_role` VALUES (31, 27, 3);
INSERT INTO `admin_user_role` VALUES (32, 37, 2);
INSERT INTO `admin_user_role` VALUES (33, 38, 3);
INSERT INTO `admin_user_role` VALUES (39, 40, 2);
INSERT INTO `admin_user_role` VALUES (40, 40, 4);

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '院系名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 107 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, '机电工程学院');
INSERT INTO `department` VALUES (2, '信息与通信学院');
INSERT INTO `department` VALUES (3, '计算机与安全学院');
INSERT INTO `department` VALUES (4, '艺术与设计学院');
INSERT INTO `department` VALUES (5, '商学院');
INSERT INTO `department` VALUES (6, '外国语学院');
INSERT INTO `department` VALUES (7, '数学与计算科学学院');
INSERT INTO `department` VALUES (8, '电子工程与自动化学院');
INSERT INTO `department` VALUES (9, '法学院');
INSERT INTO `department` VALUES (10, '材料科学与工程学院');
INSERT INTO `department` VALUES (11, '生命与环境科学学院');
INSERT INTO `department` VALUES (12, '建筑与交通工程学院');
INSERT INTO `department` VALUES (13, '海洋信息工程学院');
INSERT INTO `department` VALUES (14, '创新学院');
INSERT INTO `department` VALUES (15, '人工智能学院');
INSERT INTO `department` VALUES (16, '数字经济学院');
INSERT INTO `department` VALUES (17, '国防生学院');

-- ----------------------------
-- Table structure for nation
-- ----------------------------
DROP TABLE IF EXISTS `nation`;
CREATE TABLE `nation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of nation
-- ----------------------------
INSERT INTO `nation` VALUES (1, '汉族');
INSERT INTO `nation` VALUES (2, '蒙古族');
INSERT INTO `nation` VALUES (3, '回族');
INSERT INTO `nation` VALUES (4, '藏族');
INSERT INTO `nation` VALUES (5, '维吾尔族');
INSERT INTO `nation` VALUES (6, '苗族');
INSERT INTO `nation` VALUES (7, '彝族');
INSERT INTO `nation` VALUES (8, '壮族');
INSERT INTO `nation` VALUES (9, '布依族');
INSERT INTO `nation` VALUES (10, '朝鲜族');
INSERT INTO `nation` VALUES (11, '满族');
INSERT INTO `nation` VALUES (12, '侗族');
INSERT INTO `nation` VALUES (13, '瑶族');
INSERT INTO `nation` VALUES (14, '白族');
INSERT INTO `nation` VALUES (15, '土家族');
INSERT INTO `nation` VALUES (16, '哈尼族');
INSERT INTO `nation` VALUES (17, '哈萨克族');
INSERT INTO `nation` VALUES (18, '傣族');
INSERT INTO `nation` VALUES (19, '黎族');
INSERT INTO `nation` VALUES (20, '傈僳族');
INSERT INTO `nation` VALUES (21, '佤族');
INSERT INTO `nation` VALUES (22, '畲族');
INSERT INTO `nation` VALUES (23, '高山族');
INSERT INTO `nation` VALUES (24, '拉祜族');
INSERT INTO `nation` VALUES (25, '水族');
INSERT INTO `nation` VALUES (26, '东乡族');
INSERT INTO `nation` VALUES (27, '纳西族');
INSERT INTO `nation` VALUES (28, '景颇族');
INSERT INTO `nation` VALUES (29, '柯尔克孜族');
INSERT INTO `nation` VALUES (30, '土族');
INSERT INTO `nation` VALUES (31, '达斡尔族');
INSERT INTO `nation` VALUES (32, '仫佬族');
INSERT INTO `nation` VALUES (33, '羌族');
INSERT INTO `nation` VALUES (34, '布朗族');
INSERT INTO `nation` VALUES (35, '撒拉族');
INSERT INTO `nation` VALUES (36, '毛难族');
INSERT INTO `nation` VALUES (37, '仡佬族');
INSERT INTO `nation` VALUES (38, '锡伯族');
INSERT INTO `nation` VALUES (39, '阿昌族');
INSERT INTO `nation` VALUES (40, '普米族');
INSERT INTO `nation` VALUES (41, '塔吉克族');
INSERT INTO `nation` VALUES (42, '怒族');
INSERT INTO `nation` VALUES (43, '乌孜别克族');
INSERT INTO `nation` VALUES (44, '俄罗斯族');
INSERT INTO `nation` VALUES (45, '鄂温克族');
INSERT INTO `nation` VALUES (46, '崩龙族');
INSERT INTO `nation` VALUES (47, '保安族');
INSERT INTO `nation` VALUES (48, '裕固族');
INSERT INTO `nation` VALUES (49, '京族');
INSERT INTO `nation` VALUES (50, '塔塔尔族');
INSERT INTO `nation` VALUES (51, '独龙族');
INSERT INTO `nation` VALUES (52, '鄂伦春族');
INSERT INTO `nation` VALUES (53, '赫哲族');
INSERT INTO `nation` VALUES (54, '门巴族');
INSERT INTO `nation` VALUES (55, '珞巴族');
INSERT INTO `nation` VALUES (56, '基诺族');

-- ----------------------------
-- Table structure for politics
-- ----------------------------
DROP TABLE IF EXISTS `politics`;
CREATE TABLE `politics`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of politics
-- ----------------------------
INSERT INTO `politics` VALUES (1, '中共党员');
INSERT INTO `politics` VALUES (2, '中共预备党员');
INSERT INTO `politics` VALUES (3, '共青团员');
INSERT INTO `politics` VALUES (4, '民革党员');
INSERT INTO `politics` VALUES (5, '民盟盟员');
INSERT INTO `politics` VALUES (6, '民建会员');
INSERT INTO `politics` VALUES (7, '民进会员');
INSERT INTO `politics` VALUES (8, '农工党党员');
INSERT INTO `politics` VALUES (9, '致公党党员');
INSERT INTO `politics` VALUES (10, '九三学社社员');
INSERT INTO `politics` VALUES (11, '台盟盟员');
INSERT INTO `politics` VALUES (12, '无党派民主人士');
INSERT INTO `politics` VALUES (13, '普通公民');

-- ----------------------------
-- Table structure for position
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '学生职位名称',
  `descp` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '职位简介',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of position
-- ----------------------------
INSERT INTO `position` VALUES (1, '班干部', '班长、副班长（有的学校分为男女班长）、学习委员、纪律委员、文艺委员、体育委员、宣传委员、生活委员（或劳动委员）、女生委员、心理委员（近年大学里较为常见）、科代表等。');
INSERT INTO `position` VALUES (2, '学生会干部', '主席、副主席、秘书长，各部（学习部、纪律部、劳动部、文艺部、体育部、女生部、生活部、网络部等）部长、副部长，各部（学习部、纪律部、劳动部、文艺部、体育部、女生部、生活部、网络部等）的处长、副处长，干事、预备干事等。');
INSERT INTO `position` VALUES (3, '学生科学技术协会干部', '主席（理事长）、副主席（副理事长）、秘书长（理事）、各部（秘书处、宣传部、论坛部、科技活动部、项目管理部、外联部、家电维修部等）部长、副部长、委员（干事）等。');
INSERT INTO `position` VALUES (4, '大自委干部', '主席、副主席、各部（办公室、公寓管理、勤工助学、学生建设（学业促进）、心理帮扶、文化（文艺）宣传）部长、副部长、干事、见习干事等。');
INSERT INTO `position` VALUES (5, '社团干部', '主席、副主席、各部（办公室、公寓管理、勤工助学、学生建设（学业促进）、心理帮扶、文化（文艺）宣传）部长、副部长、干事、见习干事等。');
INSERT INTO `position` VALUES (6, '团支部', '团支部书记、团支部副书记、组织委员、宣传委员等。');
INSERT INTO `position` VALUES (7, '新闻宣传干部', '新闻宣传干部多隶属于校党委宣传部的大学生记者团或者学生通讯社等，帮助老师进行学校新闻宣传或校报等工作，新闻宣传干部：包括主席（或团长、社长）、总编、主编、理事长、各部部长、学生记者等。');

-- ----------------------------
-- Table structure for specialty
-- ----------------------------
DROP TABLE IF EXISTS `specialty`;
CREATE TABLE `specialty`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '专业名称',
  `descp` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '专业简介',
  `department_id` int(11) DEFAULT NULL COMMENT '所属院系',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 97 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of specialty
-- ----------------------------
INSERT INTO `specialty` VALUES (1, '机械设计制造及其自动化', NULL, 1);
INSERT INTO `specialty` VALUES (2, '创新创业课程', NULL, 14);
INSERT INTO `specialty` VALUES (3, '电气工程及其自动化', NULL, 1);
INSERT INTO `specialty` VALUES (4, '机械电子工程', NULL, 1);
INSERT INTO `specialty` VALUES (5, '工业设计', NULL, 4);
INSERT INTO `specialty` VALUES (6, '法学类', NULL, 9);
INSERT INTO `specialty` VALUES (7, '自动化类', NULL, 8);
INSERT INTO `specialty` VALUES (8, '土木类', NULL, 12);
INSERT INTO `specialty` VALUES (9, '电子封装技术', NULL, 1);
INSERT INTO `specialty` VALUES (10, '机械类', NULL, 1);
INSERT INTO `specialty` VALUES (11, '材料类', NULL, 10);
INSERT INTO `specialty` VALUES (12, '车辆工程', NULL, 1);
INSERT INTO `specialty` VALUES (13, '新能源材料与器件', NULL, 10);
INSERT INTO `specialty` VALUES (14, '电子信息类', NULL, 2);
INSERT INTO `specialty` VALUES (15, '通信工程', NULL, 2);
INSERT INTO `specialty` VALUES (16, '计算机类', NULL, 3);
INSERT INTO `specialty` VALUES (17, '电子信息工程', NULL, 2);
INSERT INTO `specialty` VALUES (18, '电子科学与技术', NULL, 2);
INSERT INTO `specialty` VALUES (19, '设计学类', NULL, 4);
INSERT INTO `specialty` VALUES (20, '微电子科学与工程', NULL, 2);
INSERT INTO `specialty` VALUES (21, '导航工程', NULL, 2);
INSERT INTO `specialty` VALUES (22, '计算机科学与技术', NULL, 3);
INSERT INTO `specialty` VALUES (23, '软件工程', NULL, 3);
INSERT INTO `specialty` VALUES (24, '信息安全', NULL, 3);
INSERT INTO `specialty` VALUES (25, '信息对抗技术', NULL, 3);
INSERT INTO `specialty` VALUES (26, '金融学类', NULL, 5);
INSERT INTO `specialty` VALUES (27, '管理科学与工程类', NULL, 5);
INSERT INTO `specialty` VALUES (28, '物联网工程', NULL, 3);
INSERT INTO `specialty` VALUES (29, '智能科学与技术', NULL, 3);
INSERT INTO `specialty` VALUES (30, '工商管理类', NULL, 5);
INSERT INTO `specialty` VALUES (31, '数字媒体技术', NULL, 4);
INSERT INTO `specialty` VALUES (32, '产品设计', NULL, 4);
INSERT INTO `specialty` VALUES (33, '视觉传达设计', NULL, 4);
INSERT INTO `specialty` VALUES (34, '环境设计', NULL, 4);
INSERT INTO `specialty` VALUES (35, '服装与服饰设计', NULL, 4);
INSERT INTO `specialty` VALUES (36, '动画', NULL, 4);
INSERT INTO `specialty` VALUES (37, '书法学', NULL, 4);
INSERT INTO `specialty` VALUES (38, '会计学', NULL, 5);
INSERT INTO `specialty` VALUES (39, '市场营销', NULL, 5);
INSERT INTO `specialty` VALUES (40, '工业工程', NULL, 5);
INSERT INTO `specialty` VALUES (41, '电子商务', NULL, 5);
INSERT INTO `specialty` VALUES (42, '财务管理', NULL, 5);
INSERT INTO `specialty` VALUES (43, '人力资源管理', NULL, 5);
INSERT INTO `specialty` VALUES (44, '金融工程', NULL, 5);
INSERT INTO `specialty` VALUES (45, '信息管理与信息系统', NULL, 5);
INSERT INTO `specialty` VALUES (46, '工商管理', NULL, 5);
INSERT INTO `specialty` VALUES (47, '国际经济与贸易', NULL, 5);
INSERT INTO `specialty` VALUES (48, '公共事业管理', NULL, 5);
INSERT INTO `specialty` VALUES (49, '行政管理', NULL, 5);
INSERT INTO `specialty` VALUES (50, '经济统计学', NULL, 5);
INSERT INTO `specialty` VALUES (51, '英语', NULL, 6);
INSERT INTO `specialty` VALUES (52, '日语', NULL, 6);
INSERT INTO `specialty` VALUES (53, '汉语国际教育', NULL, 6);
INSERT INTO `specialty` VALUES (54, '信息与计算科学', NULL, 7);
INSERT INTO `specialty` VALUES (55, '应用统计学', NULL, 7);
INSERT INTO `specialty` VALUES (56, '数学与应用数学', NULL, 7);
INSERT INTO `specialty` VALUES (57, '统计学', NULL, 7);
INSERT INTO `specialty` VALUES (58, '测控技术与仪器', NULL, 8);
INSERT INTO `specialty` VALUES (59, '光电信息科学与工程', NULL, 8);
INSERT INTO `specialty` VALUES (60, '自动化', NULL, 8);
INSERT INTO `specialty` VALUES (61, '法学', NULL, 9);
INSERT INTO `specialty` VALUES (62, '知识产权', NULL, 9);
INSERT INTO `specialty` VALUES (63, '材料科学与工程', NULL, 10);
INSERT INTO `specialty` VALUES (64, '材料成型及控制工程', NULL, 10);
INSERT INTO `specialty` VALUES (65, '高分子材料与工程', NULL, 10);
INSERT INTO `specialty` VALUES (66, '应用物理学', NULL, 10);
INSERT INTO `specialty` VALUES (67, '环境工程', NULL, 11);
INSERT INTO `specialty` VALUES (68, '生物医学工程', NULL, 1);
INSERT INTO `specialty` VALUES (69, '生物工程', NULL, 11);
INSERT INTO `specialty` VALUES (70, '交通工程', NULL, 12);
INSERT INTO `specialty` VALUES (71, '建筑环境与能源应用工程', NULL, 12);
INSERT INTO `specialty` VALUES (72, '土木工程', NULL, 12);
INSERT INTO `specialty` VALUES (73, '建筑电气与智能化', NULL, 12);
INSERT INTO `specialty` VALUES (74, '网络工程', NULL, 13);
INSERT INTO `specialty` VALUES (75, '机械设计制造及其自动化(卓越)', NULL, 1);
INSERT INTO `specialty` VALUES (76, '材料科学与工程(卓越)', NULL, 10);
INSERT INTO `specialty` VALUES (77, '材料成型及控制工程(产教融合班)', NULL, 10);
INSERT INTO `specialty` VALUES (78, '生物医学工程', NULL, 11);
INSERT INTO `specialty` VALUES (79, '建筑环境与设备工程', NULL, 12);
INSERT INTO `specialty` VALUES (80, '信息工程', NULL, 2);
INSERT INTO `specialty` VALUES (81, '微电子学', NULL, 2);
INSERT INTO `specialty` VALUES (82, '电子信息工程(卓越)', NULL, 2);
INSERT INTO `specialty` VALUES (83, '通信工程(第二专业)', NULL, 2);
INSERT INTO `specialty` VALUES (84, '通信工程(卓越)', NULL, 2);
INSERT INTO `specialty` VALUES (85, '机器人工程', NULL, 15);
INSERT INTO `specialty` VALUES (86, '数据科学与大数据技术', NULL, 15);
INSERT INTO `specialty` VALUES (87, '数字经济', NULL, 16);
INSERT INTO `specialty` VALUES (88, '计算机科学与技术(卓越)', NULL, 3);
INSERT INTO `specialty` VALUES (89, '国防生课程', NULL, 17);
INSERT INTO `specialty` VALUES (90, '艺术设计', NULL, 4);
INSERT INTO `specialty` VALUES (91, '物流管理', NULL, 5);
INSERT INTO `specialty` VALUES (92, '越南班', NULL, 6);
INSERT INTO `specialty` VALUES (93, '对外汉语', NULL, 6);
INSERT INTO `specialty` VALUES (94, '电子信息科学与技术', NULL, 8);
INSERT INTO `specialty` VALUES (95, '测控技术与仪器(卓越)', NULL, 8);
INSERT INTO `specialty` VALUES (96, '光电信息工程', NULL, 8);
INSERT INTO `specialty` VALUES (97, '知识产权(双学位)', NULL, 9);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `student_id` int(32) DEFAULT NULL COMMENT '学号',
  `name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学生姓名',
  `gender` char(4) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '性别',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `id_card` char(18) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '身份证号',
  `nation_id` int(8) DEFAULT NULL COMMENT '民族ID',
  `native_place` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '籍贯',
  `politic_id` int(8) DEFAULT NULL COMMENT '政治面貌ID',
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话号码',
  `address` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系地址',
  `top_degree` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最高学历',
  `school` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '毕业院校',
  `department_id` int(11) DEFAULT NULL COMMENT '所属学院ID',
  `specialty_id` int(11) DEFAULT NULL COMMENT '所属专业ID',
  `position_id` int(11) DEFAULT NULL COMMENT '学生职位ID',
  `language_level` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '语言能力',
  `computer_level` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '计算机能力',
  `begin_date` date DEFAULT NULL COMMENT '入学日期',
  `end_date` date DEFAULT NULL COMMENT '毕业日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1147 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, 1600300001, '王宝东', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 15, 85, NULL, NULL, NULL, '2018-01-01', '2020-01-01');
INSERT INTO `student` VALUES (2, 1600300002, '陈静', '女', '1989-02-01', '421288198902011234', 2, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 1, 1, 4, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (3, 1600300003, '赵琳浩', '男', '1993-03-04', '610122199303041456', 3, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 9, 97, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (4, 1600300004, '鹿存亮', '男', '1990-01-03', '610122199001031456', 4, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 5, 91, 4, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (5, 1600300005, '姚森', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '硕士', '西北大学', 6, 93, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (6, 1600300006, '云星', '女', '1993-01-05', '610122199301054789', 5, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 8, 58, 2, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (7, 1600300007, '贾旭明', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 16, 87, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (8, 1600300008, '张黎明', '男', '1991-02-01', '610144199102014569', 10, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 14, 2, 1, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (9, 1600300009, '薛磊', '男', '1992-07-01', '610144199207017895', 33, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 11, 67, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (10, 1600300010, '张洁', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 15, 86, 6, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (11, 1600300011, '王宝东', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 5, 49, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (12, 1600300012, '陈静2', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 10, 13, 6, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (13, 1600300013, '赵琳浩2', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 6, 52, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (14, 1600300014, '鹿存亮2', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 5, 46, 5, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (15, 1600300015, '姚森2', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 4, 32, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (16, 1600300016, '云星2', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 6, 53, 3, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (17, 1600300017, '贾旭明2', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 15, 85, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (18, 1600300018, '王一亭', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 12, 8, 1, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (19, 1600300019, '薛磊2', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 10, 64, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (20, 1600300020, '张洁2', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 5, 27, 5, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (21, 1600300021, '王宝东', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 5, 48, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (22, 1600300022, '陈静3', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 2, 17, 6, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (23, 1600300023, '鹿存亮3', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 12, 72, 2, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (24, 1600300024, '姚森3', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 10, 63, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (25, 1600300025, '云星3', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 4, 19, 5, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (26, 1600300026, '贾旭明3', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 4, 35, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (27, 1600300027, '张黎明3', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 8, 60, 3, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (28, 1600300028, '薛磊3', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 4, 5, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (29, 1600300029, '王宝东', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 6, 53, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (30, 1600300030, '陈静4', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 14, 2, 6, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (31, 1600300031, '赵琳浩4', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 3, 16, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (32, 1600300032, '鹿存亮4', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 3, 24, 4, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (33, 1600300033, '姚森4', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 7, 57, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (34, 1600300034, '云星4', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 9, 97, 6, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (35, 1600300035, '贾旭明4', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (36, 1600300036, '张黎明2', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 6, 52, 6, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (37, 1600300037, '薛磊4', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 8, 58, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (38, 1600300038, '张洁4', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 6, 92, 4, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (39, 1600300039, '王宝东', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 6, 92, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (40, 1600300040, '陈静5', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 6, 51, 6, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (41, 1600300041, '赵琳浩5', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 5, 91, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (42, 1600300042, '鹿存亮5', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 12, 73, 7, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (43, 1600300043, '姚森5', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 8, 58, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (44, 1600300044, '云星5', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 13, 74, 3, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (45, 1600300045, '贾旭明5', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 10, 65, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (46, 1600300046, '张黎明5', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 9, 62, 4, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (47, 1600300047, '薛磊5', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 11, 78, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (48, 1600300048, '张洁5', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 1, 1, 7, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (49, 1600300049, '王宝东', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 15, 86, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (50, 1600300050, '陈静6', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 16, 87, 2, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (51, 1600300051, '赵琳浩6', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 7, 57, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (52, 1600300052, '鹿存亮6', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 9, 6, 4, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (53, 1600300053, '姚森6', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 13, 74, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (54, 1600300054, '云星6', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 2, 18, 4, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (55, 1600300055, '王宝东', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 4, 34, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (56, 1600300056, '陈静', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 6, 92, 5, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (57, 1600300057, '赵琳浩', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 12, 79, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (58, 1600300058, '鹿存亮', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 13, 74, 2, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (59, 1600300059, '姚森', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 1, 12, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (60, 1600300060, '贾旭明', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 10, 63, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (61, 1600300061, '张黎明', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 4, 33, 6, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (62, 1600300062, '薛磊', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 9, 62, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (63, 1600300063, '张洁', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 16, 87, 4, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (64, 1600300064, '王宝东', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 11, 78, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (65, 1600300065, '陈静2', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 14, 2, 7, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (66, 1600300066, '赵琳浩2', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 6, 52, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (67, 1600300067, '鹿存亮2', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 10, 11, 4, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (68, 1600300068, '姚森2', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 12, 71, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (69, 1600300069, '云星2', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 5, 47, 2, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (70, 1600300070, '贾旭明2', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (71, 1600300071, '王一亭', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 16, 87, 5, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (72, 1600300072, '薛磊2', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 12, 73, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (73, 1600300073, '张洁2', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 12, 72, 2, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (74, 1600300074, '王宝东', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 10, 76, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (75, 1600300075, '陈静3', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 9, 6, 3, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (76, 1600300076, '鹿存亮3', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 1, 12, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (77, 1600300077, '姚森3', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 1, 68, 3, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (78, 1600300078, '云星3', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 7, 54, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (79, 1600300079, '贾旭明3', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 11, 67, 4, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (80, 1600300080, '张黎明3', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 7, 57, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (81, 1600300081, '薛磊3', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 2, 18, 5, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (82, 1600300082, '王宝东', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 1, 75, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (83, 1600300083, '陈静4', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 15, 86, 1, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (84, 1600300084, '赵琳浩4', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 3, 25, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (85, 1600300085, '鹿存亮4', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 14, 2, 6, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (86, 1600300086, '姚森4', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 11, 69, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (87, 1600300087, '云星4', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 2, 84, 4, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (88, 1600300088, '贾旭明4', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 6, 51, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (89, 1600300089, '谢工', '女', '1970-01-01', '618177197001011234', 1, '江苏', 1, '584991843@qq.com', '18558887788', '北京', '本科', '南华大学', 6, 93, NULL, NULL, NULL, '2018-01-01', '2023-01-01');
INSERT INTO `student` VALUES (90, 1600300090, '林昭亮', '男', '1990-01-01', '610122199809091234', 1, '广东', 13, '584991843@qq.com', '16767776654', '广东深圳', '大专', '广东职业技术学院', 1, 1, 5, NULL, NULL, '2018-01-01', '2023-01-01');
INSERT INTO `student` VALUES (91, 1600300091, '11', '男', '2018-01-01', '610122201801011234', 1, '1', 1, '584991843@qq.com', '111', '1', '本科', '1', 6, 52, NULL, NULL, NULL, '2018-01-01', '2022-01-26');
INSERT INTO `student` VALUES (92, 1600300092, '王宝东', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 4, 37, NULL, NULL, NULL, '2018-01-01', '2020-01-01');
INSERT INTO `student` VALUES (93, 1600300093, '陈静', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 3, 24, 1, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (94, 1600300094, '赵琳浩', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 11, 67, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (95, 1600300095, '鹿存亮', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 8, 60, 3, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (96, 1600300096, '姚森', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '硕士', '西北大学', 15, 85, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (97, 1600300097, '云星', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 16, 87, 2, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (98, 1600300098, '贾旭明', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 6, 53, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (99, 1600300099, '张黎明', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 5, 38, 5, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (100, 1600300100, '薛磊', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 13, 74, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (101, 1600300101, '张洁', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 2, 15, 3, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (102, 1600300102, '王宝东', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 11, 67, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (103, 1600300103, '陈静2', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 4, 33, 5, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (104, 1600300104, '赵琳浩2', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 4, 36, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (105, 1600300105, '鹿存亮2', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 1, 68, 3, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (106, 1600300106, '姚森2', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 7, 54, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (107, 1600300107, '云星2', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 11, 69, 3, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (108, 1600300108, '贾旭明2', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 13, 74, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (109, 1600300109, '王一亭', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 9, 61, 7, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (110, 1600300110, '薛磊2', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 9, 6, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (111, 1600300111, '张洁2', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 9, 62, 5, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (112, 1600300112, '王宝东', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 4, 34, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (113, 1600300113, '陈静3', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 15, 85, 2, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (114, 1600300114, '鹿存亮3', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 9, 61, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (115, 1600300115, '姚森3', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 6, 52, 2, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (116, 1600300116, '云星3', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 7, 57, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (117, 1600300117, '贾旭明3', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 12, 8, 4, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (118, 1600300118, '张黎明3', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (119, 1600300119, '薛磊3', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 11, 69, 5, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (120, 1600300120, '王宝东', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 2, 81, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (121, 1600300121, '陈静4', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 9, 61, 1, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (122, 1600300122, '赵琳浩4', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 7, 56, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (123, 1600300123, '鹿存亮4', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 2, 20, 2, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (124, 1600300124, '姚森4', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 11, 67, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (125, 1600300125, '云星4', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 14, 2, 4, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (126, 1600300126, '贾旭明4', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (127, 1600300127, '张黎明2', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 3, 29, 1, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (128, 1600300128, '薛磊4', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 16, 87, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (129, 1600300129, '张洁4', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 1, 12, 6, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (130, 1600300130, '王宝东', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 2, 80, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (131, 1600300131, '陈静5', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 15, 86, 7, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (132, 1600300132, '赵琳浩5', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 11, 67, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (133, 1600300133, '鹿存亮5', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 2, 80, 3, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (134, 1600300134, '姚森5', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 2, 80, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (135, 1600300135, '云星5', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 8, 7, 4, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (136, 1600300136, '贾旭明5', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 5, 41, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (137, 1600300137, '张黎明5', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 15, 86, 4, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (138, 1600300138, '薛磊5', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 4, 37, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (139, 1600300139, '张洁5', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 4, 33, 2, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (140, 1600300140, '王宝东', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 15, 86, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (141, 1600300141, '陈静6', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 5, 46, 3, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (142, 1600300142, '赵琳浩6', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 16, 87, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (143, 1600300143, '鹿存亮6', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 6, 92, 1, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (144, 1600300144, '姚森6', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 8, 96, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (145, 1600300145, '云星6', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 10, 65, 2, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (146, 1600300146, '王宝东', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 7, 56, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (147, 1600300147, '陈静', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 3, 28, 5, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (148, 1600300148, '赵琳浩', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 10, 64, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (149, 1600300149, '鹿存亮', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 5, 46, 6, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (150, 1600300150, '姚森', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 1, 9, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (151, 1600300151, '贾旭明', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 7, 55, 7, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (152, 1600300152, '张黎明', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 16, 87, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (153, 1600300153, '薛磊', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 8, 7, 4, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (154, 1600300154, '张洁', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 6, 52, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (155, 1600300155, '王大志', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 3, 28, 5, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (156, 1600300156, '陈静2', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 16, 87, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (157, 1600300157, '赵琳浩2', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 16, 87, 4, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (158, 1600300158, '鹿存亮2', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 5, 91, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (159, 1600300159, '姚森2', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 5, 30, 1, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (160, 1600300160, '云星2', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 8, 96, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (161, 1600300161, '贾旭明2', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 9, 61, 3, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (162, 1600300162, '王一亭', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 12, 72, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (163, 1600300163, '薛磊2', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 13, 74, 6, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (164, 1600300164, '张洁2', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 2, 21, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (165, 1600300165, '王大志', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 3, 23, 6, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (166, 1600300166, '陈静3', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 16, 87, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (167, 1600300167, '鹿存亮3', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 15, 85, 6, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (168, 1600300168, '姚森3', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 3, 23, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (169, 1600300169, '云星3', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 16, 87, 6, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (170, 1600300170, '贾旭明3', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 12, 79, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (171, 1600300171, '张黎明3', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 6, 51, 5, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (172, 1600300172, '薛磊3', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 2, 80, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (173, 1600300173, '王大志', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 6, 53, 7, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (174, 1600300174, '陈静4', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 13, 74, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (175, 1600300175, '赵琳浩4', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 2, 82, 4, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (176, 1600300176, '鹿存亮4', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (177, 1600300177, '姚森4', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 3, 88, 5, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (178, 1600300178, '云星4', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 15, 85, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (179, 1600300179, '贾旭明4', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 1, 75, 4, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (180, 1600300180, '谢工', '女', '1970-01-01', '618177197001011234', 1, '江苏', 1, '584991843@qq.com', '18558887788', '北京', '本科', '南华大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2023-01-01');
INSERT INTO `student` VALUES (181, 1600300181, '林昭亮', '男', '1990-01-01', '610122199809091234', 1, '广东', 13, '584991843@qq.com', '16767776654', '广东深圳', '大专', '广东职业技术学院', 11, 78, 2, NULL, NULL, '2018-01-01', '2023-01-01');
INSERT INTO `student` VALUES (182, 1600300182, '11', '男', '2018-01-01', '610122201801011234', 1, '1', 1, '584991843@qq.com', '111', '1', '本科', '1', 14, 2, NULL, NULL, NULL, '2018-01-01', '2022-01-26');
INSERT INTO `student` VALUES (183, 1600300183, 'javaboy', '男', '1989-12-31', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 11, 78, NULL, NULL, NULL, '2017-12-31', '2019-12-31');
INSERT INTO `student` VALUES (184, 1600300184, 'javaboy', '男', '1989-12-31', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 15, 85, 6, NULL, NULL, '2017-12-31', '2019-12-31');
INSERT INTO `student` VALUES (185, 1600300185, 'javaboy', '男', '1989-12-31', '610122199001011256', 1, '', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 2, 17, NULL, NULL, NULL, '2017-12-31', '2019-12-31');
INSERT INTO `student` VALUES (186, 1600300186, 'javaboy', '男', '1989-12-31', '610122199001011256', 1, '陕西', 13, 'laowan@123.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 6, 51, 4, NULL, NULL, '2017-12-31', '2019-12-31');
INSERT INTO `student` VALUES (187, 1600300187, 'javaboy', '男', '1989-12-31', '610122199001011256', 1, '陕西', 13, 'laowan@123.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 7, 54, NULL, NULL, NULL, '2017-12-31', '2019-12-31');
INSERT INTO `student` VALUES (188, 1600300188, 'javaboy', '男', '1989-12-31', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 11, 69, 2, NULL, NULL, '2017-12-31', '2019-12-31');
INSERT INTO `student` VALUES (189, 1600300189, 'javaboy', '男', '1989-12-31', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 15, 85, NULL, NULL, NULL, '2017-12-31', '2024-02-29');
INSERT INTO `student` VALUES (190, 1600300190, 'javaboy666', '男', '2019-11-10', '610122199911111111', 1, '广东', 13, '123@qq.com', '12345678901', '广东深圳', '本科', '深圳大学', 13, 74, 4, NULL, NULL, '2019-11-10', '2023-02-22');
INSERT INTO `student` VALUES (191, 1600300191, 'javaboy666', '男', '2019-11-10', '610122199911111111', 1, '广东', 13, '123@qq.com', '12345678901', '广东深圳', '本科', '深圳大学', 13, 74, NULL, NULL, NULL, '2019-11-10', '2023-02-22');
INSERT INTO `student` VALUES (192, 1600300192, '王大志', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 12, 71, 2, NULL, NULL, '2018-01-01', '2020-01-01');
INSERT INTO `student` VALUES (193, 1600300193, '陈静', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 1, 75, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (194, 1600300194, '赵琳浩', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 8, 95, 6, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (195, 1600300195, '鹿存亮', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 5, 26, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (196, 1600300196, '姚森', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '硕士', '西北大学', 10, 65, 2, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (197, 1600300197, '云星', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 3, 24, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (198, 1600300198, '贾旭明', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 2, 84, 3, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (199, 1600300199, '张黎明', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 15, 86, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (200, 1600300200, '薛磊', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 11, 78, 3, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (201, 1600300201, '张洁', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 11, 78, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (202, 1600300202, '王大志', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 6, 53, 5, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (203, 1600300203, '陈静2', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 2, 15, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (204, 1600300204, '赵琳浩2', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 3, 16, 3, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (205, 1600300205, '鹿存亮2', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 15, 86, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (206, 1600300206, '姚森2', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 8, 96, 4, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (207, 1600300207, '云星2', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 4, 33, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (208, 1600300208, '贾旭明2', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 15, 86, 2, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (209, 1600300209, '王一亭', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 8, 59, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (210, 1600300210, '薛磊2', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 11, 69, 6, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (211, 1600300211, '张洁2', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 12, 72, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (212, 1600300212, '王大志', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 11, 69, 5, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (213, 1600300213, '陈静3', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 15, 85, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (214, 1600300214, '鹿存亮3', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 5, 46, 4, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (215, 1600300215, '姚森3', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 9, 62, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (216, 1600300216, '云星3', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 3, 23, 7, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (217, 1600300217, '贾旭明3', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 6, 51, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (218, 1600300218, '张黎明3', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 1, 9, 2, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (219, 1600300219, '薛磊3', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 5, 38, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (220, 1600300220, '王大志', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 1, 10, 7, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (221, 1600300221, '陈静4', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 9, 6, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (222, 1600300222, '赵琳浩4', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 13, 74, 2, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (223, 1600300223, '鹿存亮4', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 5, 91, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (224, 1600300224, '姚森4', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 6, 52, 5, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (225, 1600300225, '云星4', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 7, 55, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (226, 1600300226, '贾旭明4', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 1, 3, 2, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (227, 1600300227, '张黎明2', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 2, 15, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (228, 1600300228, '薛磊4', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 12, 73, 6, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (229, 1600300229, '张洁4', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 3, 25, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (230, 1600300230, '王大志', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 8, 59, 6, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (231, 1600300231, '陈静5', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 7, 54, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (232, 1600300232, '赵琳浩5', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 16, 87, 5, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (233, 1600300233, '鹿存亮5', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 4, 90, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (234, 1600300234, '姚森5', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 13, 74, 6, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (235, 1600300235, '云星5', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 4, 37, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (236, 1600300236, '贾旭明5', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 6, 92, 5, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (237, 1600300237, '张黎明5', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (238, 1600300238, '薛磊5', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 5, 40, 4, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (239, 1600300239, '张洁5', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 15, 85, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (240, 1600300240, '王大志', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 10, 11, 1, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (241, 1600300241, '陈静6', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 16, 87, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (242, 1600300242, '赵琳浩6', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 2, 82, 6, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (243, 1600300243, '鹿存亮6', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (244, 1600300244, '姚森6', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 7, 57, 7, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (245, 1600300245, '云星6', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 6, 51, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (246, 1600300246, '王大志', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 4, 90, 3, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (247, 1600300247, '陈静', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 4, 90, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (248, 1600300248, '赵琳浩', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 15, 86, 6, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (249, 1600300249, '鹿存亮', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 6, 52, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (250, 1600300250, '姚森', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 8, 59, 2, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (251, 1600300251, '贾旭明', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 1, 4, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (252, 1600300252, '张黎明', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 10, 63, 1, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (253, 1600300253, '薛磊', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 2, 18, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (254, 1600300254, '张洁', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 16, 87, 5, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (255, 1600300255, '王宝东', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 6, 52, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (256, 1600300256, '陈静2', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 16, 87, 5, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (257, 1600300257, '赵琳浩2', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 11, 78, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (258, 1600300258, '鹿存亮2', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 6, 92, 3, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (259, 1600300259, '姚森2', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (260, 1600300260, '云星2', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 14, 2, 5, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (261, 1600300261, '贾旭明2', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 2, 21, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (262, 1600300262, '王一亭', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 8, 95, 1, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (263, 1600300263, '薛磊2', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 1, 10, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (264, 1600300264, '张洁2', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 1, 9, 3, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (265, 1600300265, '王宝东', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 12, 70, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (266, 1600300266, '陈静3', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 2, 81, 2, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (267, 1600300267, '鹿存亮3', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 10, 13, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (268, 1600300268, '姚森3', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 1, 4, 3, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (269, 1600300269, '云星3', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 10, 76, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (270, 1600300270, '贾旭明3', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 7, 57, 6, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (271, 1600300271, '张黎明3', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 3, 28, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (272, 1600300272, '薛磊3', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 2, 20, 2, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (273, 1600300273, '王宝东', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 15, 86, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (274, 1600300274, '陈静4', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 10, 63, 5, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (275, 1600300275, '赵琳浩4', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 8, 95, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (276, 1600300276, '鹿存亮4', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 13, 74, 6, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (277, 1600300277, '姚森4', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 13, 74, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (278, 1600300278, '云星4', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 16, 87, 5, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (279, 1600300279, '贾旭明4', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 8, 60, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (280, 1600300280, '谢工', '女', '1970-01-01', '618177197001011234', 1, '江苏', 1, '584991843@qq.com', '18558887788', '北京', '本科', '南华大学', 9, 6, 2, NULL, NULL, '2018-01-01', '2023-01-01');
INSERT INTO `student` VALUES (281, 1600300281, '林昭亮', '男', '1990-01-01', '610122199809091234', 1, '广东', 13, '584991843@qq.com', '16767776654', '广东深圳', '大专', '广东职业技术学院', 2, 82, NULL, NULL, NULL, '2018-01-01', '2023-01-01');
INSERT INTO `student` VALUES (282, 1600300282, '王大志', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 2, 18, 6, NULL, NULL, '2018-01-01', '2020-01-01');
INSERT INTO `student` VALUES (283, 1600300283, '陈静', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 16, 87, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (284, 1600300284, '赵琳浩', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 5, 26, 5, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (285, 1600300285, '鹿存亮', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 10, 66, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (286, 1600300286, '姚森', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '硕士', '西北大学', 3, 28, 2, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (287, 1600300287, '云星', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 5, 26, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (288, 1600300288, '贾旭明', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 13, 74, 3, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (289, 1600300289, '张黎明', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 13, 74, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (290, 1600300290, '薛磊', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 10, 76, 3, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (291, 1600300291, '张洁', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 5, 26, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (292, 1600300292, '王大志', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 9, 97, 5, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (293, 1600300293, '陈静2', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 13, 74, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (294, 1600300294, '赵琳浩2', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 9, 62, 3, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (295, 1600300295, '鹿存亮2', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 15, 86, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (296, 1600300296, '姚森2', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 7, 54, 3, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (297, 1600300297, '云星2', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 15, 85, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (298, 1600300298, '贾旭明2', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 9, 61, 2, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (299, 1600300299, '王一亭', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (300, 1600300300, '薛磊2', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 8, 95, 3, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (301, 1600300301, '张洁2', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 15, 86, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (302, 1600300302, '王大志', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 15, 85, 5, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (303, 1600300303, '陈静3', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (304, 1600300304, '鹿存亮3', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 5, 42, 7, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (305, 1600300305, '姚森3', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (306, 1600300306, '云星3', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 11, 78, 1, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (307, 1600300307, '贾旭明3', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 1, 9, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (308, 1600300308, '张黎明3', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 4, 37, 2, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (309, 1600300309, '薛磊3', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 7, 57, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (310, 1600300310, '王大志', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 12, 71, 2, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (311, 1600300311, '陈静4', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 13, 74, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (312, 1600300312, '赵琳浩4', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 8, 59, 6, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (313, 1600300313, '鹿存亮4', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 3, 23, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (314, 1600300314, '姚森4', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 9, 6, 4, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (315, 1600300315, '云星4', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 4, 33, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (316, 1600300316, '贾旭明4', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 4, 36, 4, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (317, 1600300317, '张黎明2', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 13, 74, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (318, 1600300318, '薛磊4', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 5, 43, 6, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (319, 1600300319, '张洁4', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 6, 51, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (320, 1600300320, '王大志', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 16, 87, 4, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (321, 1600300321, '陈静5', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 16, 87, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (322, 1600300322, '赵琳浩5', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 13, 74, 6, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (323, 1600300323, '鹿存亮5', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 12, 71, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (324, 1600300324, '姚森5', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 3, 16, 7, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (325, 1600300325, '云星5', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 11, 69, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (326, 1600300326, '贾旭明5', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 8, 58, 2, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (327, 1600300327, '张黎明5', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 8, 96, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (328, 1600300328, '薛磊5', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 2, 15, 2, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (329, 1600300329, '张洁5', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 16, 87, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (330, 1600300330, '王大志', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 10, 76, 3, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (331, 1600300331, '陈静6', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 16, 87, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (332, 1600300332, '赵琳浩6', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 12, 72, 2, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (333, 1600300333, '鹿存亮6', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 5, 47, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (334, 1600300334, '姚森6', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 10, 65, 6, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (335, 1600300335, '云星6', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 6, 51, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (336, 1600300336, '王大志', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 3, 28, 3, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (337, 1600300337, '陈静', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 6, 53, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (338, 1600300338, '赵琳浩', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 3, 22, 5, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (339, 1600300339, '鹿存亮', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 6, 51, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (340, 1600300340, '姚森', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 8, 94, 3, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (341, 1600300341, '贾旭明', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 1, 3, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (342, 1600300342, '张黎明', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 14, 2, 3, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (343, 1600300343, '薛磊', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 1, 9, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (344, 1600300344, '张洁', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 6, 52, 5, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (345, 1600300345, '王宝东', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 13, 74, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (346, 1600300346, '陈静2', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 16, 87, 4, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (347, 1600300347, '赵琳浩2', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 13, 74, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (348, 1600300348, '鹿存亮2', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 3, 88, 4, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (349, 1600300349, '姚森2', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 7, 56, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (350, 1600300350, '云星2', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 14, 2, 5, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (351, 1600300351, '贾旭明2', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (352, 1600300352, '王一亭', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 16, 87, 3, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (353, 1600300353, '薛磊2', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 11, 78, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (354, 1600300354, '张洁2', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 13, 74, 4, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (355, 1600300355, '王宝东', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 3, 28, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (356, 1600300356, '陈静3', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 5, 42, 5, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (357, 1600300357, '鹿存亮3', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 4, 36, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (358, 1600300358, '姚森3', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 3, 24, 3, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (359, 1600300359, '云星3', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 3, 16, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (360, 1600300360, '贾旭明3', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 6, 51, 1, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (361, 1600300361, '张黎明3', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 9, 6, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (362, 1600300362, '薛磊3', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 15, 86, 3, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (363, 1600300363, '王宝东', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 1, 12, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (364, 1600300364, '陈静4', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 11, 78, 2, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (365, 1600300365, '赵琳浩4', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 2, 21, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (366, 1600300366, '鹿存亮4', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 16, 87, 6, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (367, 1600300367, '姚森4', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 10, 77, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (368, 1600300368, '云星4', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 7, 55, 4, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (369, 1600300369, '贾旭明4', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 16, 87, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (370, 1600300370, '谢工', '女', '1970-01-01', '618177197001011234', 1, '江苏', 1, '584991843@qq.com', '18558887788', '北京', '本科', '南华大学', 13, 74, 1, NULL, NULL, '2018-01-01', '2023-01-01');
INSERT INTO `student` VALUES (371, 1600300371, '林昭亮', '男', '1990-01-01', '610122199809091234', 1, '广东', 13, '584991843@qq.com', '16767776654', '广东深圳', '大专', '广东职业技术学院', 15, 86, NULL, NULL, NULL, '2018-01-01', '2023-01-01');
INSERT INTO `student` VALUES (372, 1600300372, 'javaboy', '男', '1989-12-31', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 11, 78, 5, NULL, NULL, '2017-12-31', '2019-12-31');
INSERT INTO `student` VALUES (373, 1600300373, 'javaboy', '男', '1989-12-31', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 2, 84, NULL, NULL, NULL, '2017-12-31', '2019-12-31');
INSERT INTO `student` VALUES (374, 1600300374, 'javaboy', '男', '1989-12-31', '610122199001011256', 1, '', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 7, 56, 2, NULL, NULL, '2017-12-31', '2019-12-31');
INSERT INTO `student` VALUES (375, 1600300375, 'javaboy', '男', '1989-12-31', '610122199001011256', 1, '陕西', 13, 'laowan@123.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 12, 8, NULL, NULL, NULL, '2017-12-31', '2019-12-31');
INSERT INTO `student` VALUES (376, 1600300376, 'javaboy', '男', '1989-12-31', '610122199001011256', 1, '陕西', 13, 'laowan@123.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 5, 48, 2, NULL, NULL, '2017-12-31', '2019-12-31');
INSERT INTO `student` VALUES (377, 1600300377, 'javaboy', '男', '1989-12-31', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 16, 87, NULL, NULL, NULL, '2017-12-31', '2019-12-31');
INSERT INTO `student` VALUES (378, 1600300378, 'javaboy', '男', '1989-12-31', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 15, 85, 6, NULL, NULL, '2017-12-31', '2024-02-29');
INSERT INTO `student` VALUES (379, 1600300379, 'javaboy666', '男', '2019-11-10', '610122199911111111', 1, '广东', 13, '123@qq.com', '12345678901', '广东深圳', '本科', '深圳大学', 9, 6, NULL, NULL, NULL, '2019-11-10', '2023-02-22');
INSERT INTO `student` VALUES (380, 1600300380, 'javaboy666', '男', '2019-11-10', '610122199911111111', 1, '广东', 13, '123@qq.com', '12345678901', '广东深圳', '本科', '深圳大学', 2, 82, 6, NULL, NULL, '2019-11-10', '2023-02-22');
INSERT INTO `student` VALUES (381, 1600300381, '王宝东', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 15, 85, NULL, NULL, NULL, '2018-01-01', '2020-01-01');
INSERT INTO `student` VALUES (382, 1600300382, '陈静', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 9, 97, 6, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (383, 1600300383, '赵琳浩', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 7, 57, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (384, 1600300384, '鹿存亮', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 16, 87, 5, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (385, 1600300385, '姚森', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '硕士', '西北大学', 14, 2, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (386, 1600300386, '云星', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 9, 61, 5, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (387, 1600300387, '贾旭明', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 4, 32, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (388, 1600300388, '张黎明', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 12, 71, 6, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (389, 1600300389, '薛磊', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (390, 1600300390, '张洁', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 14, 2, 4, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (391, 1600300391, '王宝东', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 6, 51, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (392, 1600300392, '陈静2', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 3, 88, 3, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (393, 1600300393, '赵琳浩2', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 9, 6, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (394, 1600300394, '鹿存亮2', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 10, 77, 3, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (395, 1600300395, '姚森2', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (396, 1600300396, '云星2', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 10, 64, 6, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (397, 1600300397, '贾旭明2', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 1, 9, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (398, 1600300398, '王一亭', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 8, 7, 5, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (399, 1600300399, '薛磊2', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 15, 86, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (400, 1600300400, '张洁2', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 2, 80, 6, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (401, 1600300401, '王宝东', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 8, 95, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (402, 1600300402, '陈静3', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 16, 87, 2, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (403, 1600300403, '鹿存亮3', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 15, 85, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (404, 1600300404, '姚森3', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 14, 2, 3, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (405, 1600300405, '云星3', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 12, 70, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (406, 1600300406, '贾旭明3', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 8, 7, 1, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (407, 1600300407, '张黎明3', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 15, 85, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (408, 1600300408, '薛磊3', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 6, 51, 3, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (409, 1600300409, '王宝东', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 2, 81, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (410, 1600300410, '陈静4', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 13, 74, 5, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (411, 1600300411, '赵琳浩4', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 15, 85, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (412, 1600300412, '鹿存亮4', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 3, 16, 1, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (413, 1600300413, '姚森4', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 10, 11, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (414, 1600300414, '云星4', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 4, 37, 4, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (415, 1600300415, '贾旭明4', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 10, 11, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (416, 1600300416, '张黎明2', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 9, 6, 3, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (417, 1600300417, '薛磊4', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 2, 81, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (418, 1600300418, '张洁4', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 2, 17, 1, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (419, 1600300419, '王宝东', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 3, 29, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (420, 1600300420, '陈静5', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 13, 74, 3, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (421, 1600300421, '赵琳浩5', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 4, 36, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (422, 1600300422, '鹿存亮5', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 9, 62, 4, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (423, 1600300423, '姚森5', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 13, 74, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (424, 1600300424, '云星5', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 6, 51, 6, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (425, 1600300425, '贾旭明5', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 11, 67, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (426, 1600300426, '张黎明5', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 16, 87, 2, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (427, 1600300427, '薛磊5', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 9, 61, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (428, 1600300428, '张洁5', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 4, 90, 3, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (429, 1600300429, '王宝东', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 3, 24, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (430, 1600300430, '陈静6', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 5, 26, 3, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (431, 1600300431, '赵琳浩6', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 6, 53, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (432, 1600300432, '鹿存亮6', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 13, 74, 3, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (433, 1600300433, '姚森6', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 1, 12, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (434, 1600300434, '云星6', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 4, 32, 7, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (435, 1600300435, '王宝东', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 2, 21, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (436, 1600300436, '陈静', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 2, 84, 6, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (437, 1600300437, '赵琳浩', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 12, 71, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (438, 1600300438, '鹿存亮', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 11, 78, 2, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (439, 1600300439, '姚森', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 6, 93, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (440, 1600300440, '贾旭明', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 11, 67, 4, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (441, 1600300441, '张黎明', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 2, 20, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (442, 1600300442, '薛磊', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 10, 13, 2, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (443, 1600300443, '张洁', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 12, 70, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (444, 1600300444, '王大志', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 4, 90, 3, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (445, 1600300445, '陈静2', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 8, 96, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (446, 1600300446, '赵琳浩2', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 1, 1, 6, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (447, 1600300447, '鹿存亮2', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 13, 74, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (448, 1600300448, '姚森2', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 3, 28, 2, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (449, 1600300449, '云星2', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 13, 74, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (450, 1600300450, '贾旭明2', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 14, 2, 5, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (451, 1600300451, '王一亭', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 1, 75, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (452, 1600300452, '薛磊2', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 5, 44, 7, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (453, 1600300453, '张洁2', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 5, 41, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (454, 1600300454, '王大志', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 14, 2, 5, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (455, 1600300455, '陈静3', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 12, 70, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (456, 1600300456, '鹿存亮3', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 4, 35, 2, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (457, 1600300457, '姚森3', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 4, 34, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (458, 1600300458, '云星3', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 4, 19, 2, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (459, 1600300459, '贾旭明3', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 9, 62, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (460, 1600300460, '张黎明3', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 10, 66, 4, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (461, 1600300461, '薛磊3', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 9, 61, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (462, 1600300462, '王大志', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 11, 78, 5, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (463, 1600300463, '陈静4', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 11, 67, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (464, 1600300464, '赵琳浩4', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 2, 17, 1, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (465, 1600300465, '鹿存亮4', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 9, 61, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (466, 1600300466, '姚森4', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 16, 87, 7, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (467, 1600300467, '云星4', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 3, 88, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (468, 1600300468, '贾旭明4', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 13, 74, 6, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (469, 1600300469, '谢工', '女', '1970-01-01', '618177197001011234', 1, '江苏', 1, '584991843@qq.com', '18558887788', '北京', '本科', '南华大学', 13, 74, NULL, NULL, NULL, '2018-01-01', '2023-01-01');
INSERT INTO `student` VALUES (470, 1600300470, '林昭亮', '男', '1990-01-01', '610122199809091234', 1, '广东', 13, '584991843@qq.com', '16767776654', '广东深圳', '大专', '广东职业技术学院', 6, 92, 3, NULL, NULL, '2018-01-01', '2023-01-01');
INSERT INTO `student` VALUES (471, 1600300471, '王宝东', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 10, 66, NULL, NULL, NULL, '2018-01-01', '2020-01-01');
INSERT INTO `student` VALUES (472, 1600300472, '陈静', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 5, 27, 3, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (473, 1600300473, '赵琳浩', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 1, 75, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (474, 1600300474, '鹿存亮', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 1, 9, 5, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (475, 1600300475, '姚森', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '硕士', '西北大学', 2, 83, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (476, 1600300476, '云星', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 5, 42, 4, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (477, 1600300477, '贾旭明', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 2, 15, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (478, 1600300478, '张黎明', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 2, 80, 3, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (479, 1600300479, '薛磊', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 10, 63, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (480, 1600300480, '张洁', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 13, 74, 2, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (481, 1600300481, '王宝东', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 4, 5, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (482, 1600300482, '陈静2', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 2, 84, 1, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (483, 1600300483, '赵琳浩2', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 2, 15, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (484, 1600300484, '鹿存亮2', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 5, 30, 5, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (485, 1600300485, '姚森2', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 16, 87, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (486, 1600300486, '云星2', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 15, 86, 1, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (487, 1600300487, '贾旭明2', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 3, 25, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (488, 1600300488, '王一亭', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 8, 7, 2, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (489, 1600300489, '薛磊2', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 1, 10, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (490, 1600300490, '张洁2', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 3, 25, 5, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (491, 1600300491, '王宝东', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 6, 52, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (492, 1600300492, '陈静3', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 12, 79, 6, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (493, 1600300493, '鹿存亮3', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 9, 6, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (494, 1600300494, '姚森3', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 12, 71, 4, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (495, 1600300495, '云星3', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 16, 87, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (496, 1600300496, '贾旭明3', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 7, 54, 2, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (497, 1600300497, '张黎明3', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 15, 86, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (498, 1600300498, '薛磊3', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 14, 2, 6, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (499, 1600300499, '王宝东', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (500, 1600300500, '陈静4', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 15, 86, 6, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (501, 1600300501, '赵琳浩4', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 4, 32, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (502, 1600300502, '鹿存亮4', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 2, 20, 6, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (503, 1600300503, '姚森4', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 7, 55, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (504, 1600300504, '云星4', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 16, 87, 6, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (505, 1600300505, '贾旭明4', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 8, 96, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (506, 1600300506, '张黎明2', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 8, 96, 2, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (507, 1600300507, '薛磊4', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 1, 75, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (508, 1600300508, '张洁4', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 12, 72, 4, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (509, 1600300509, '王宝东', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 2, 83, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (510, 1600300510, '陈静5', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 1, 4, 7, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (511, 1600300511, '赵琳浩5', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 10, 11, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (512, 1600300512, '鹿存亮5', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 14, 2, 3, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (513, 1600300513, '姚森5', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 15, 85, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (514, 1600300514, '云星5', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 3, 16, 6, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (515, 1600300515, '贾旭明5', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (516, 1600300516, '张黎明5', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 9, 62, 3, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (517, 1600300517, '薛磊5', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 4, 37, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (518, 1600300518, '张洁5', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 6, 92, 2, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (519, 1600300519, '王宝东', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 12, 70, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (520, 1600300520, '陈静6', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 1, 12, 1, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (521, 1600300521, '赵琳浩6', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 12, 79, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (522, 1600300522, '鹿存亮6', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 16, 87, 4, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (523, 1600300523, '姚森6', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 3, 24, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (524, 1600300524, '云星6', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 9, 62, 2, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (525, 1600300525, '王宝东', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (526, 1600300526, '陈静', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 1, 75, 4, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (527, 1600300527, '赵琳浩', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 8, 94, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (528, 1600300528, '鹿存亮', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 13, 74, 2, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (529, 1600300529, '姚森', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 15, 86, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (530, 1600300530, '贾旭明', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 12, 8, 1, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (531, 1600300531, '张黎明', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 11, 67, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (532, 1600300532, '薛磊', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 15, 86, 6, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (533, 1600300533, '张洁', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 1, 75, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (534, 1600300534, '王大志', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 3, 25, 3, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (535, 1600300535, '陈静2', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 13, 74, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (536, 1600300536, '赵琳浩2', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 8, 7, 5, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (537, 1600300537, '鹿存亮2', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 2, 83, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (538, 1600300538, '姚森2', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 5, 44, 4, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (539, 1600300539, '云星2', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 16, 87, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (540, 1600300540, '贾旭明2', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 2, 17, 5, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (541, 1600300541, '王一亭', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 6, 92, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (542, 1600300542, '薛磊2', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 3, 25, 5, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (543, 1600300543, '张洁2', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 2, 81, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (544, 1600300544, '王大志', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 14, 2, 5, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (545, 1600300545, '陈静3', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 8, 7, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (546, 1600300546, '鹿存亮3', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 14, 2, 3, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (547, 1600300547, '姚森3', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 6, 92, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (548, 1600300548, '云星3', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 15, 85, 5, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (549, 1600300549, '贾旭明3', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 10, 77, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (550, 1600300550, '张黎明3', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 13, 74, 2, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (551, 1600300551, '薛磊3', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 3, 24, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (552, 1600300552, '王大志', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 16, 87, 2, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (553, 1600300553, '陈静4', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 7, 54, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (554, 1600300554, '赵琳浩4', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 3, 29, 7, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (555, 1600300555, '鹿存亮4', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 1, 75, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (556, 1600300556, '姚森4', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 14, 2, 3, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (557, 1600300557, '云星4', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 7, 57, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (558, 1600300558, '贾旭明4', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 4, 32, 6, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (559, 1600300559, '谢工', '女', '1970-01-01', '618177197001011234', 1, '江苏', 1, '584991843@qq.com', '18558887788', '北京', '本科', '南华大学', 15, 86, NULL, NULL, NULL, '2018-01-01', '2023-01-01');
INSERT INTO `student` VALUES (560, 1600300560, '林昭亮', '男', '1990-01-01', '610122199809091234', 1, '广东', 13, '584991843@qq.com', '16767776654', '广东深圳', '大专', '广东职业技术学院', 12, 71, 7, NULL, NULL, '2018-01-01', '2023-01-01');
INSERT INTO `student` VALUES (561, 1600300561, 'javaboy', '男', '1989-12-31', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 3, 28, NULL, NULL, NULL, '2017-12-31', '2019-12-31');
INSERT INTO `student` VALUES (562, 1600300562, 'javaboy', '男', '1989-12-31', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 14, 2, 4, NULL, NULL, '2017-12-31', '2019-12-31');
INSERT INTO `student` VALUES (563, 1600300563, 'javaboy', '男', '1989-12-31', '610122199001011256', 1, '', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 3, 29, NULL, NULL, NULL, '2017-12-31', '2019-12-31');
INSERT INTO `student` VALUES (564, 1600300564, 'javaboy', '男', '1989-12-31', '610122199001011256', 1, '陕西', 13, 'laowan@123.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 2, 18, 5, NULL, NULL, '2017-12-31', '2019-12-31');
INSERT INTO `student` VALUES (565, 1600300565, 'javaboy', '男', '1989-12-31', '610122199001011256', 1, '陕西', 13, 'laowan@123.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 2, 81, NULL, NULL, NULL, '2017-12-31', '2019-12-31');
INSERT INTO `student` VALUES (566, 1600300566, 'javaboy', '男', '1989-12-31', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 9, 6, 4, NULL, NULL, '2017-12-31', '2019-12-31');
INSERT INTO `student` VALUES (567, 1600300567, 'javaboy', '男', '1989-12-31', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 7, 56, NULL, NULL, NULL, '2017-12-31', '2024-02-29');
INSERT INTO `student` VALUES (568, 1600300568, 'javaboy666', '男', '2019-11-10', '610122199911111111', 1, '广东', 13, '123@qq.com', '12345678901', '广东深圳', '本科', '深圳大学', 11, 69, 6, NULL, NULL, '2019-11-10', '2023-02-22');
INSERT INTO `student` VALUES (569, 1600300569, 'javaboy666', '男', '2019-11-10', '610122199911111111', 1, '广东', 13, '123@qq.com', '12345678901', '广东深圳', '本科', '深圳大学', 16, 87, NULL, NULL, NULL, '2019-11-10', '2023-02-22');
INSERT INTO `student` VALUES (570, 1600300570, 'javaboy', '男', '2017-11-01', '610144199905059999', 1, '广东', 13, '584991843@qq.com', '18564447789', '广东深圳', '本科', '深圳大学', 14, 2, 5, NULL, NULL, '2019-11-24', '2022-11-27');
INSERT INTO `student` VALUES (571, 1600300571, 'javaboy', '男', '2019-11-24', '610144199905056666', 1, '广东', 13, '584991843@qq.com', '18566667777', '广东深圳', '本科', '深圳大学', 12, 8, NULL, NULL, NULL, '2019-11-24', '2022-11-27');
INSERT INTO `student` VALUES (572, 1600300572, '王大志', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 14, 2, 4, NULL, NULL, '2018-01-01', '2020-01-01');
INSERT INTO `student` VALUES (573, 1600300573, '赵琳浩', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 1, 10, 7, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (574, 1600300574, '鹿存亮', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (575, 1600300575, '姚森', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '硕士', '西北大学', 14, 2, 3, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (576, 1600300576, '云星', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 16, 87, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (577, 1600300577, '贾旭明', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 5, 26, 3, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (578, 1600300578, '张黎明', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 12, 72, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (579, 1600300579, '薛磊', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 4, 37, 6, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (580, 1600300580, '张洁', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (581, 1600300581, '王大志', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 13, 74, 1, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (582, 1600300582, '陈静2', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 13, 74, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (583, 1600300583, '赵琳浩2', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 3, 22, 6, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (584, 1600300584, '鹿存亮2', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 5, 47, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (585, 1600300585, '姚森2', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 12, 8, 6, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (586, 1600300586, '云星2', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 4, 34, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (587, 1600300587, '贾旭明2', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 8, 7, 5, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (588, 1600300588, '王一亭', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 2, 17, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (589, 1600300589, '薛磊2', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 9, 61, 5, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (590, 1600300590, '张洁2', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (591, 1600300591, '王大志', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 4, 36, 2, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (592, 1600300592, '陈静3', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 7, 56, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (593, 1600300593, '鹿存亮3', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 4, 34, 2, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (594, 1600300594, '姚森3', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 2, 81, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (595, 1600300595, '云星3', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 4, 5, 2, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (596, 1600300596, '贾旭明3', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 8, 7, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (597, 1600300597, '张黎明3', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 16, 87, 5, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (598, 1600300598, '薛磊3', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 8, 59, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (599, 1600300599, '王大志', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 3, 29, 7, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (600, 1600300600, '陈静4', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 15, 85, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (601, 1600300601, '赵琳浩4', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 13, 74, 5, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (602, 1600300602, '鹿存亮4', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 10, 66, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (603, 1600300603, '姚森4', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 13, 74, 5, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (604, 1600300604, '云星4', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 13, 74, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (605, 1600300605, '贾旭明4', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 16, 87, 3, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (606, 1600300606, '张黎明2', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 2, 21, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (607, 1600300607, '薛磊4', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 7, 55, 6, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (608, 1600300608, '张洁4', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 10, 76, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (609, 1600300609, '王大志', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 16, 87, 5, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (610, 1600300610, '陈静5', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 2, 81, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (611, 1600300611, '赵琳浩5', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 9, 62, 2, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (612, 1600300612, '鹿存亮5', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 7, 54, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (613, 1600300613, '姚森5', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 6, 92, 1, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (614, 1600300614, '云星5', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 4, 36, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (615, 1600300615, '贾旭明5', '男', '1993-11-11', '610122199311111234', 1, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 8, 58, 4, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (616, 1600300616, '张黎明5', '男', '1991-02-01', '610144199102014569', 1, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 16, 87, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (617, 1600300617, '薛磊5', '男', '1992-07-01', '610144199207017895', 1, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 8, 96, 4, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (618, 1600300618, '张洁5', '女', '1990-10-09', '420177199010093652', 1, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 13, 74, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (619, 1600300619, '王大志', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 12, 70, 5, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (620, 1600300620, '陈静6', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 1, 1, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (621, 1600300621, '赵琳浩6', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 5, 38, 2, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (622, 1600300622, '鹿存亮6', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 1, 9, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (623, 1600300623, '姚森6', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 2, 21, 4, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (624, 1600300624, '云星6', '女', '1993-01-05', '610122199301054789', 1, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 14, 2, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (625, 1600300625, '王大志', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 6, 92, 7, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (626, 1600300626, '陈静', '女', '1989-02-01', '421288198902011234', 1, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 2, 15, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (627, 1600300627, '赵琳浩', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 8, 7, 3, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (628, 1600300628, '鹿存亮', '男', '1990-01-03', '610122199001031456', 1, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (629, 1600300629, '姚森', '男', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 8, 58, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (630, 1600300630, '贾旭明', '男', '1993-11-11', '610122199311111234', 45, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (631, 1600300631, '张黎明', '男', '1991-02-01', '610144199102014569', 29, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 6, 93, 7, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (632, 1600300632, '薛磊', '男', '1992-07-01', '610144199207017895', 8, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 5, 27, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (633, 1600300633, '张洁', '女', '1990-10-09', '420177199010093652', 8, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 15, 85, 4, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (634, 1600300634, '王宝东', '男', '1990-01-01', '610122199001011256', 14, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 16, 87, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (635, 1600300635, '陈静2', '女', '1989-02-01', '421288198902011234', 44, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 14, 2, 1, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (636, 1600300636, '赵琳浩2', '男', '1993-03-04', '610122199303041456', 13, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 4, 5, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (637, 1600300637, '鹿存亮2', '男', '1990-01-03', '610122199001031456', 42, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 3, 88, 4, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (638, 1600300638, '姚森2', '男', '1991-02-05', '610122199102058952', 6, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 6, 92, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (639, 1600300639, '云星2', '女', '1993-01-05', '610122199301054789', 13, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 7, 55, 2, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (640, 1600300640, '贾旭明2', '男', '1993-11-11', '610122199311111234', 45, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 12, 70, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (641, 1600300641, '王一亭', '男', '1991-02-01', '610144199102014569', 21, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 5, 30, 4, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (642, 1600300642, '薛磊2', '男', '1992-07-01', '610144199207017895', 23, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 1, 12, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (643, 1600300643, '张洁2', '女', '1990-10-09', '420177199010093652', 52, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 9, 61, 1, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (644, 1600300644, '王宝东', '男', '1990-01-01', '610122199001011256', 26, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 3, 25, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (645, 1600300645, '陈静3', '女', '1989-02-01', '421288198902011234', 28, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 15, 85, 5, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (646, 1600300646, '鹿存亮3', '男', '1990-01-03', '610122199001031456', 4, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (647, 1600300647, '姚森3', '男', '1991-02-05', '610122199102058952', 46, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 2, 81, 7, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (648, 1600300648, '云星3', '女', '1993-01-05', '610122199301054789', 53, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 14, 2, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (649, 1600300649, '贾旭明3', '男', '1993-11-11', '610122199311111234', 15, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 14, 2, 7, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (650, 1600300650, '张黎明3', '男', '1991-02-01', '610144199102014569', 27, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 9, 62, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (651, 1600300651, '薛磊3', '男', '1992-07-01', '610144199207017895', 32, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 8, 95, 6, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (652, 1600300652, '王宝东', '男', '1990-01-01', '610122199001011256', 23, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 16, 87, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (653, 1600300653, '陈静4', '女', '1989-02-01', '421288198902011234', 18, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 5, 46, 1, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (654, 1600300654, '赵琳浩4', '男', '1993-03-04', '610122199303041456', 22, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 16, 87, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (655, 1600300655, '鹿存亮4', '男', '1990-01-03', '610122199001031456', 52, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 14, 2, 6, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (656, 1600300656, '姚森4', '男', '1991-02-05', '610122199102058952', 29, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 9, 61, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (657, 1600300657, '云星4', '女', '1993-01-05', '610122199301054789', 45, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 15, 85, 7, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (658, 1600300658, '贾旭明4', '男', '1993-11-11', '610122199311111234', 24, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 3, 88, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (659, 1600300659, '谢工', '女', '1970-01-01', '618177197001011234', 40, '江苏', 1, '584991843@qq.com', '18558887788', '北京', '本科', '南华大学', 15, 86, 3, NULL, NULL, '2018-01-01', '2023-01-01');
INSERT INTO `student` VALUES (660, 1600300660, '林昭亮', '男', '1990-01-01', '610122199809091234', 16, '广东', 13, '584991843@qq.com', '16767776654', '广东深圳', '大专', '广东职业技术学院', 11, 67, NULL, NULL, NULL, '2018-01-01', '2023-01-01');
INSERT INTO `student` VALUES (661, 1600300661, '王大志', '男', '1990-01-01', '610122199001011256', 17, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 5, 50, 6, NULL, NULL, '2018-01-01', '2020-01-01');
INSERT INTO `student` VALUES (662, 1600300662, '陈静', '女', '1989-02-01', '421288198902011234', 34, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 7, 55, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (663, 1600300663, '赵琳浩', '男', '1993-03-04', '610122199303041456', 9, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 4, 32, 3, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (664, 1600300664, '鹿存亮', '男', '1990-01-03', '610122199001031456', 52, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 6, 51, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (665, 1600300665, '姚森', '男', '1991-02-05', '610122199102058952', 10, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '硕士', '西北大学', 1, 4, 6, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (666, 1600300666, '云星', '女', '1993-01-05', '610122199301054789', 5, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 12, 79, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (667, 1600300667, '贾旭明', '男', '1993-11-11', '610122199311111234', 50, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 8, 58, 2, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (668, 1600300668, '张黎明', '男', '1991-02-01', '610144199102014569', 13, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 10, 66, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (669, 1600300669, '薛磊', '男', '1992-07-01', '610144199207017895', 24, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 6, 92, 5, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (670, 1600300670, '张洁', '女', '1990-10-09', '420177199010093652', 25, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 15, 86, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (671, 1600300671, '王大志', '男', '1990-01-01', '610122199001011256', 52, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 16, 87, 6, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (672, 1600300672, '陈静2', '女', '1989-02-01', '421288198902011234', 19, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 2, 80, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (673, 1600300673, '赵琳浩2', '男', '1993-03-04', '610122199303041456', 48, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 15, 86, 6, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (674, 1600300674, '鹿存亮2', '男', '1990-01-03', '610122199001031456', 16, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 9, 6, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (675, 1600300675, '姚森2', '男', '1991-02-05', '610122199102058952', 45, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 13, 74, 6, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (676, 1600300676, '云星2', '女', '1993-01-05', '610122199301054789', 13, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 8, 58, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (677, 1600300677, '贾旭明2', '男', '1993-11-11', '610122199311111234', 38, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 10, 77, 7, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (678, 1600300678, '王一亭', '男', '1991-02-01', '610144199102014569', 41, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 6, 92, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (679, 1600300679, '薛磊2', '男', '1992-07-01', '610144199207017895', 32, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 2, 82, 1, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (680, 1600300680, '张洁2', '女', '1990-10-09', '420177199010093652', 39, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 9, 6, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (681, 1600300681, '王大志', '男', '1990-01-01', '610122199001011256', 41, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 14, 2, 4, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (682, 1600300682, '陈静3', '女', '1989-02-01', '421288198902011234', 34, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 3, 29, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (683, 1600300683, '鹿存亮3', '男', '1990-01-03', '610122199001031456', 45, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 13, 74, 2, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (684, 1600300684, '姚森3', '男', '1991-02-05', '610122199102058952', 10, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 5, 50, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (685, 1600300685, '云星3', '女', '1993-01-05', '610122199301054789', 26, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 16, 87, 5, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (686, 1600300686, '贾旭明3', '男', '1993-11-11', '610122199311111234', 45, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 7, 57, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (687, 1600300687, '张黎明3', '男', '1991-02-01', '610144199102014569', 37, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 5, 40, 5, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (688, 1600300688, '薛磊3', '男', '1992-07-01', '610144199207017895', 47, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 8, 60, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (689, 1600300689, '王大志', '男', '1990-01-01', '610122199001011256', 14, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 12, 72, 5, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (690, 1600300690, '陈静4', '女', '1989-02-01', '421288198902011234', 36, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 13, 74, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (691, 1600300691, '赵琳浩4', '男', '1993-03-04', '610122199303041456', 27, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 14, 2, 7, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (692, 1600300692, '鹿存亮4', '男', '1990-01-03', '610122199001031456', 28, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 2, 15, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (693, 1600300693, '姚森4', '男', '1991-02-05', '610122199102058952', 2, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 8, 59, 6, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (694, 1600300694, '云星4', '女', '1993-01-05', '610122199301054789', 35, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 15, 85, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (695, 1600300695, '贾旭明4', '男', '1993-11-11', '610122199311111234', 3, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 15, 86, 2, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (696, 1600300696, '张黎明2', '男', '1991-02-01', '610144199102014569', 18, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 9, 6, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (697, 1600300697, '薛磊4', '男', '1992-07-01', '610144199207017895', 25, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 12, 73, 6, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (698, 1600300698, '张洁4', '女', '1990-10-09', '420177199010093652', 15, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 9, 61, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (699, 1600300699, '王大志', '男', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 6, 92, 2, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (700, 1600300700, '陈静5', '女', '1989-02-01', '421288198902011234', 15, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 10, 13, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (701, 1600300701, '赵琳浩5', '男', '1993-03-04', '610122199303041456', 14, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 8, 94, 5, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (702, 1600300702, '鹿存亮5', '男', '1990-01-03', '610122199001031456', 25, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 3, 24, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (703, 1600300703, '姚森5', '男', '1991-02-05', '610122199102058952', 27, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 6, 51, 4, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (704, 1600300704, '云星5', '女', '1993-01-05', '610122199301054789', 4, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 10, 64, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (705, 1600300705, '贾旭明5', '男', '1993-11-11', '610122199311111234', 46, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 13, 74, 7, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (706, 1600300706, '张黎明5', '男', '1991-02-01', '610144199102014569', 55, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 3, 88, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (707, 1600300707, '薛磊5', '男', '1992-07-01', '610144199207017895', 26, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 7, 56, 1, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (708, 1600300708, '张洁5', '女', '1990-10-09', '420177199010093652', 17, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 3, 16, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (709, 1600300709, '王大志', '男', '1990-01-01', '610122199001011256', 8, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 2, 17, 4, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (710, 1600300710, '陈静6', '女', '1989-02-01', '421288198902011234', 42, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 5, 48, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (711, 1600300711, '赵琳浩6', '男', '1993-03-04', '610122199303041456', 20, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 10, 64, 1, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (712, 1600300712, '鹿存亮6', '男', '1990-01-03', '610122199001031456', 30, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 8, 7, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (713, 1600300713, '姚森6', '男', '1991-02-05', '610122199102058952', 31, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 2, 17, 7, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (714, 1600300714, '云星6', '女', '1993-01-05', '610122199301054789', 11, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 12, 73, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (715, 1600300715, '王大志', '男', '1990-01-01', '610122199001011256', 17, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 11, 69, 5, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (716, 1600300716, '陈静', '女', '1989-02-01', '421288198902011234', 50, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 2, 80, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (717, 1600300717, '赵琳浩', '男', '1993-03-04', '610122199303041456', 31, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 2, 84, 3, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (718, 1600300718, '鹿存亮', '男', '1990-01-03', '610122199001031456', 6, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 11, 69, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (719, 1600300719, '姚森', '男', '1991-02-05', '610122199102058952', 44, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 14, 2, 4, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (720, 1600300720, '贾旭明', '男', '1993-11-11', '610122199311111234', 39, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 8, 59, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (721, 1600300721, '张黎明', '男', '1991-02-01', '610144199102014569', 4, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 1, 68, 5, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (722, 1600300722, '薛磊', '男', '1992-07-01', '610144199207017895', 12, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 13, 74, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (723, 1600300723, '张洁', '女', '1990-10-09', '420177199010093652', 49, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 11, 69, 4, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (724, 1600300724, '王宝东', '男', '1990-01-01', '610122199001011256', 40, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 13, 74, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (725, 1600300725, '陈静2', '女', '1989-02-01', '421288198902011234', 54, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 1, 3, 6, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (726, 1600300726, '赵琳浩2', '男', '1993-03-04', '610122199303041456', 39, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 12, 70, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (727, 1600300727, '鹿存亮2', '男', '1990-01-03', '610122199001031456', 32, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 7, 54, 3, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (728, 1600300728, '姚森2', '男', '1991-02-05', '610122199102058952', 40, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (729, 1600300729, '云星2', '女', '1993-01-05', '610122199301054789', 50, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 4, 31, 2, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (730, 1600300730, '贾旭明2', '男', '1993-11-11', '610122199311111234', 18, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (731, 1600300731, '王一亭', '男', '1991-02-01', '610144199102014569', 50, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 9, 61, 7, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (732, 1600300732, '薛磊2', '男', '1992-07-01', '610144199207017895', 29, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 5, 43, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (733, 1600300733, '张洁2', '女', '1990-10-09', '420177199010093652', 48, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 13, 74, 4, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (734, 1600300734, '王宝东', '男', '1990-01-01', '610122199001011256', 45, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 4, 34, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (735, 1600300735, '陈静3', '女', '1989-02-01', '421288198902011234', 22, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 1, 1, 2, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (736, 1600300736, '鹿存亮3', '男', '1990-01-03', '610122199001031456', 30, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 3, 22, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (737, 1600300737, '姚森3', '男', '1991-02-05', '610122199102058952', 28, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 16, 87, 5, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (738, 1600300738, '云星3', '女', '1993-01-05', '610122199301054789', 48, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 5, 48, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (739, 1600300739, '贾旭明3', '男', '1993-11-11', '610122199311111234', 45, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 2, 20, 1, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (740, 1600300740, '张黎明3', '男', '1991-02-01', '610144199102014569', 25, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 11, 67, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (741, 1600300741, '薛磊3', '男', '1992-07-01', '610144199207017895', 46, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 1, 75, 2, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (742, 1600300742, '王宝东', '男', '1990-01-01', '610122199001011256', 43, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 8, 96, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (743, 1600300743, '陈静4', '女', '1989-02-01', '421288198902011234', 22, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 14, 2, 3, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (744, 1600300744, '赵琳浩4', '男', '1993-03-04', '610122199303041456', 34, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 15, 86, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (745, 1600300745, '鹿存亮4', '男', '1990-01-03', '610122199001031456', 49, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 14, 2, 3, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (746, 1600300746, '姚森4', '男', '1991-02-05', '610122199102058952', 32, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 5, 42, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (747, 1600300747, '云星4', '女', '1993-01-05', '610122199301054789', 12, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 6, 51, 4, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (748, 1600300748, '贾旭明4', '男', '1993-11-11', '610122199311111234', 18, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 13, 74, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (749, 1600300749, '谢工', '女', '1970-01-01', '618177197001011234', 53, '江苏', 1, '584991843@qq.com', '18558887788', '北京', '本科', '南华大学', 8, 7, 7, NULL, NULL, '2018-01-01', '2023-01-01');
INSERT INTO `student` VALUES (750, 1600300750, '林昭亮', '男', '1990-01-01', '610122199809091234', 47, '广东', 13, '584991843@qq.com', '16767776654', '广东深圳', '大专', '广东职业技术学院', 1, 12, NULL, NULL, NULL, '2018-01-01', '2023-01-01');
INSERT INTO `student` VALUES (751, 1600300751, 'javaboy', '男', '1989-12-31', '610122199001011256', 18, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 7, 56, 2, NULL, NULL, '2017-12-31', '2019-12-31');
INSERT INTO `student` VALUES (752, 1600300752, 'javaboy', '男', '1989-12-31', '610122199001011256', 5, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 8, 94, NULL, NULL, NULL, '2017-12-31', '2019-12-31');
INSERT INTO `student` VALUES (753, 1600300753, 'javaboy', '男', '1989-12-31', '610122199001011256', 26, '', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 6, 93, 6, NULL, NULL, '2017-12-31', '2019-12-31');
INSERT INTO `student` VALUES (754, 1600300754, 'javaboy', '男', '1989-12-31', '610122199001011256', 2, '陕西', 13, 'laowan@123.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 4, 90, NULL, NULL, NULL, '2017-12-31', '2019-12-31');
INSERT INTO `student` VALUES (755, 1600300755, 'javaboy', '男', '1989-12-31', '610122199001011256', 42, '陕西', 13, 'laowan@123.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 15, 86, 5, NULL, NULL, '2017-12-31', '2019-12-31');
INSERT INTO `student` VALUES (756, 1600300756, 'javaboy', '男', '1989-12-31', '610122199001011256', 37, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 15, 85, NULL, NULL, NULL, '2017-12-31', '2019-12-31');
INSERT INTO `student` VALUES (757, 1600300757, 'javaboy', '男', '1989-12-31', '610122199001011256', 4, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 11, 67, 4, NULL, NULL, '2017-12-31', '2024-02-29');
INSERT INTO `student` VALUES (758, 1600300758, 'javaboy666', '男', '2019-11-10', '610122199911111111', 18, '广东', 13, '123@qq.com', '12345678901', '广东深圳', '本科', '深圳大学', 12, 70, NULL, NULL, NULL, '2019-11-10', '2023-02-22');
INSERT INTO `student` VALUES (759, 1600300759, 'javaboy666', '男', '2019-11-10', '610122199911111111', 24, '广东', 13, '123@qq.com', '12345678901', '广东深圳', '本科', '深圳大学', 12, 71, 6, NULL, NULL, '2019-11-10', '2023-02-22');
INSERT INTO `student` VALUES (760, 1600300760, '王宝东', '男', '1990-01-01', '610122199001011256', 7, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 3, 28, NULL, NULL, NULL, '2018-01-01', '2020-01-01');
INSERT INTO `student` VALUES (761, 1600300761, '陈静', '女', '1989-02-01', '421288198902011234', 18, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 15, 85, 3, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (762, 1600300762, '赵琳浩', '男', '1993-03-04', '610122199303041456', 15, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 5, 39, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (763, 1600300763, '鹿存亮', '男', '1990-01-03', '610122199001031456', 17, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 5, 45, 2, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (764, 1600300764, '姚森', '男', '1991-02-05', '610122199102058952', 38, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '硕士', '西北大学', 3, 29, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (765, 1600300765, '云星', '女', '1993-01-05', '610122199301054789', 31, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 8, 60, 2, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (766, 1600300766, '贾旭明', '男', '1993-11-11', '610122199311111234', 39, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 5, 46, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (767, 1600300767, '张黎明', '男', '1991-02-01', '610144199102014569', 45, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 5, 91, 1, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (768, 1600300768, '薛磊', '男', '1992-07-01', '610144199207017895', 52, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 12, 73, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (769, 1600300769, '张洁', '女', '1990-10-09', '420177199010093652', 14, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 9, 97, 6, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (770, 1600300770, '王宝东', '男', '1990-01-01', '610122199001011256', 21, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 6, 53, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (771, 1600300771, '陈静2', '女', '1989-02-01', '421288198902011234', 10, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 3, 24, 3, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (772, 1600300772, '赵琳浩2', '男', '1993-03-04', '610122199303041456', 40, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 10, 64, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (773, 1600300773, '鹿存亮2', '男', '1990-01-03', '610122199001031456', 4, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 10, 65, 6, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (774, 1600300774, '姚森2', '男', '1991-02-05', '610122199102058952', 9, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 10, 11, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (775, 1600300775, '云星2', '女', '1993-01-05', '610122199301054789', 32, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 3, 88, 3, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (776, 1600300776, '贾旭明2', '男', '1993-11-11', '610122199311111234', 24, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 13, 74, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (777, 1600300777, '王一亭', '男', '1991-02-01', '610144199102014569', 21, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 3, 16, 3, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (778, 1600300778, '薛磊2', '男', '1992-07-01', '610144199207017895', 34, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 13, 74, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (779, 1600300779, '张洁2', '女', '1990-10-09', '420177199010093652', 49, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 4, 37, 6, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (780, 1600300780, '王宝东', '男', '1990-01-01', '610122199001011256', 33, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 2, 14, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (781, 1600300781, '陈静3', '女', '1989-02-01', '421288198902011234', 18, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 7, 54, 7, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (782, 1600300782, '鹿存亮3', '男', '1990-01-03', '610122199001031456', 43, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 8, 95, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (783, 1600300783, '姚森3', '男', '1991-02-05', '610122199102058952', 51, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 11, 69, 4, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (784, 1600300784, '云星3', '女', '1993-01-05', '610122199301054789', 17, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 14, 2, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (785, 1600300785, '贾旭明3', '男', '1993-11-11', '610122199311111234', 38, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 11, 78, 2, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (786, 1600300786, '张黎明3', '男', '1991-02-01', '610144199102014569', 31, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 11, 69, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (787, 1600300787, '薛磊3', '男', '1992-07-01', '610144199207017895', 38, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 11, 69, 4, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (788, 1600300788, '王宝东', '男', '1990-01-01', '610122199001011256', 44, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 10, 13, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (789, 1600300789, '陈静4', '女', '1989-02-01', '421288198902011234', 46, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 11, 69, 3, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (790, 1600300790, '赵琳浩4', '男', '1993-03-04', '610122199303041456', 45, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 1, 1, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (791, 1600300791, '鹿存亮4', '男', '1990-01-03', '610122199001031456', 32, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 8, 60, 2, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (792, 1600300792, '姚森4', '男', '1991-02-05', '610122199102058952', 23, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 4, 90, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (793, 1600300793, '云星4', '女', '1993-01-05', '610122199301054789', 16, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 1, 10, 3, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (794, 1600300794, '贾旭明4', '男', '1993-11-11', '610122199311111234', 11, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 4, 90, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (795, 1600300795, '张黎明2', '男', '1991-02-01', '610144199102014569', 6, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 12, 8, 3, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (796, 1600300796, '薛磊4', '男', '1992-07-01', '610144199207017895', 52, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 5, 40, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (797, 1600300797, '张洁4', '女', '1990-10-09', '420177199010093652', 22, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 11, 69, 1, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (798, 1600300798, '王宝东', '男', '1990-01-01', '610122199001011256', 9, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (799, 1600300799, '陈静5', '女', '1989-02-01', '421288198902011234', 30, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 1, 4, 6, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (800, 1600300800, '赵琳浩5', '男', '1993-03-04', '610122199303041456', 13, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 3, 24, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (801, 1600300801, '鹿存亮5', '男', '1990-01-03', '610122199001031456', 29, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 16, 87, 3, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (802, 1600300802, '姚森5', '男', '1991-02-05', '610122199102058952', 50, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (803, 1600300803, '云星5', '女', '1993-01-05', '610122199301054789', 52, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 6, 92, 1, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (804, 1600300804, '贾旭明5', '男', '1993-11-11', '610122199311111234', 54, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 5, 40, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (805, 1600300805, '张黎明5', '男', '1991-02-01', '610144199102014569', 2, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 11, 67, 2, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (806, 1600300806, '薛磊5', '男', '1992-07-01', '610144199207017895', 11, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 16, 87, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (807, 1600300807, '张洁5', '女', '1990-10-09', '420177199010093652', 50, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 11, 69, 2, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (808, 1600300808, '王宝东', '男', '1990-01-01', '610122199001011256', 48, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 16, 87, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (809, 1600300809, '陈静6', '女', '1989-02-01', '421288198902011234', 37, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 14, 2, 5, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (810, 1600300810, '赵琳浩6', '男', '1993-03-04', '610122199303041456', 39, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 9, 61, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (811, 1600300811, '鹿存亮6', '男', '1990-01-03', '610122199001031456', 30, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 6, 93, 2, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (812, 1600300812, '姚森6', '男', '1991-02-05', '610122199102058952', 29, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 7, 55, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (813, 1600300813, '云星6', '女', '1993-01-05', '610122199301054789', 2, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 6, 51, 2, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (814, 1600300814, '王宝东', '男', '1990-01-01', '610122199001011256', 29, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 11, 78, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (815, 1600300815, '陈静', '女', '1989-02-01', '421288198902011234', 29, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 6, 92, 5, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (816, 1600300816, '赵琳浩', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 5, 50, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (817, 1600300817, '鹿存亮', '男', '1990-01-03', '610122199001031456', 28, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 2, 20, 3, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (818, 1600300818, '姚森', '男', '1991-02-05', '610122199102058952', 27, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (819, 1600300819, '贾旭明', '男', '1993-11-11', '610122199311111234', 48, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 1, 4, 3, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (820, 1600300820, '张黎明', '男', '1991-02-01', '610144199102014569', 48, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (821, 1600300821, '薛磊', '男', '1992-07-01', '610144199207017895', 41, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 5, 48, 4, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (822, 1600300822, '张洁', '女', '1990-10-09', '420177199010093652', 6, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 12, 8, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (823, 1600300823, '王大志', '男', '1990-01-01', '610122199001011256', 16, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 9, 62, 4, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (824, 1600300824, '陈静2', '女', '1989-02-01', '421288198902011234', 4, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 10, 11, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (825, 1600300825, '赵琳浩2', '男', '1993-03-04', '610122199303041456', 27, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 11, 67, 7, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (826, 1600300826, '鹿存亮2', '男', '1990-01-03', '610122199001031456', 14, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 4, 90, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (827, 1600300827, '姚森2', '男', '1991-02-05', '610122199102058952', 41, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 5, 50, 4, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (828, 1600300828, '云星2', '女', '1993-01-05', '610122199301054789', 52, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 1, 9, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (829, 1600300829, '贾旭明2', '男', '1993-11-11', '610122199311111234', 27, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 11, 67, 3, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (830, 1600300830, '王一亭', '男', '1991-02-01', '610144199102014569', 34, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (831, 1600300831, '薛磊2', '男', '1992-07-01', '610144199207017895', 30, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 10, 65, 3, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (832, 1600300832, '张洁2', '女', '1990-10-09', '420177199010093652', 48, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 1, 1, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (833, 1600300833, '王大志', '男', '1990-01-01', '610122199001011256', 38, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 1, 4, 3, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (834, 1600300834, '陈静3', '女', '1989-02-01', '421288198902011234', 47, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 15, 86, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (835, 1600300835, '鹿存亮3', '男', '1990-01-03', '610122199001031456', 8, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 13, 74, 2, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (836, 1600300836, '姚森3', '男', '1991-02-05', '610122199102058952', 10, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 4, 35, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (837, 1600300837, '云星3', '女', '1993-01-05', '610122199301054789', 23, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 14, 2, 3, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (838, 1600300838, '贾旭明3', '男', '1993-11-11', '610122199311111234', 29, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 10, 64, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (839, 1600300839, '张黎明3', '男', '1991-02-01', '610144199102014569', 20, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 5, 44, 4, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (840, 1600300840, '薛磊3', '男', '1992-07-01', '610144199207017895', 10, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 2, 83, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (841, 1600300841, '王大志', '男', '1990-01-01', '610122199001011256', 47, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 6, 93, 5, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (842, 1600300842, '陈静4', '女', '1989-02-01', '421288198902011234', 38, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 4, 36, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (843, 1600300843, '赵琳浩4', '男', '1993-03-04', '610122199303041456', 46, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 1, 12, 6, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (844, 1600300844, '鹿存亮4', '男', '1990-01-03', '610122199001031456', 7, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 8, 58, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (845, 1600300845, '姚森4', '男', '1991-02-05', '610122199102058952', 6, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 8, 7, 2, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (846, 1600300846, '云星4', '女', '1993-01-05', '610122199301054789', 6, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 6, 51, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (847, 1600300847, '贾旭明4', '男', '1993-11-11', '610122199311111234', 11, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 13, 74, 3, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (848, 1600300848, '谢工', '女', '1970-01-01', '618177197001011234', 36, '江苏', 1, '584991843@qq.com', '18558887788', '北京', '本科', '南华大学', 4, 35, NULL, NULL, NULL, '2018-01-01', '2023-01-01');
INSERT INTO `student` VALUES (849, 1600300849, '林昭亮', '男', '1990-01-01', '610122199809091234', 36, '广东', 13, '584991843@qq.com', '16767776654', '广东深圳', '大专', '广东职业技术学院', 12, 70, 2, NULL, NULL, '2018-01-01', '2023-01-01');
INSERT INTO `student` VALUES (850, 1600300850, '王宝东', '男', '1990-01-01', '610122199001011256', 18, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 4, 33, NULL, NULL, NULL, '2018-01-01', '2020-01-01');
INSERT INTO `student` VALUES (851, 1600300851, '陈静', '女', '1989-02-01', '421288198902011234', 37, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 8, 58, 5, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (852, 1600300852, '赵琳浩', '男', '1993-03-04', '610122199303041456', 17, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 9, 61, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (853, 1600300853, '鹿存亮', '男', '1990-01-03', '610122199001031456', 29, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 8, 59, 2, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (854, 1600300854, '姚森', '男', '1991-02-05', '610122199102058952', 36, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '硕士', '西北大学', 10, 64, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (855, 1600300855, '云星', '女', '1993-01-05', '610122199301054789', 40, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 2, 82, 7, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (856, 1600300856, '贾旭明', '男', '1993-11-11', '610122199311111234', 36, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 5, 40, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (857, 1600300857, '张黎明', '男', '1991-02-01', '610144199102014569', 3, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 6, 93, 2, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (858, 1600300858, '薛磊', '男', '1992-07-01', '610144199207017895', 17, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 10, 76, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (859, 1600300859, '张洁', '女', '1990-10-09', '420177199010093652', 18, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 6, 93, 1, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (860, 1600300860, '王宝东', '男', '1990-01-01', '610122199001011256', 40, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 13, 74, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (861, 1600300861, '陈静2', '女', '1989-02-01', '421288198902011234', 32, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 16, 87, 4, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (862, 1600300862, '赵琳浩2', '男', '1993-03-04', '610122199303041456', 42, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 4, 36, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (863, 1600300863, '鹿存亮2', '男', '1990-01-03', '610122199001031456', 55, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 12, 72, 2, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (864, 1600300864, '姚森2', '男', '1991-02-05', '610122199102058952', 39, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 2, 82, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (865, 1600300865, '云星2', '女', '1993-01-05', '610122199301054789', 29, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 9, 6, 5, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (866, 1600300866, '贾旭明2', '男', '1993-11-11', '610122199311111234', 27, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 15, 86, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (867, 1600300867, '王一亭', '男', '1991-02-01', '610144199102014569', 46, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 11, 67, 4, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (868, 1600300868, '薛磊2', '男', '1992-07-01', '610144199207017895', 39, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 12, 70, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (869, 1600300869, '张洁2', '女', '1990-10-09', '420177199010093652', 55, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 9, 97, 5, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (870, 1600300870, '王宝东', '男', '1990-01-01', '610122199001011256', 46, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 11, 78, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (871, 1600300871, '陈静3', '女', '1989-02-01', '421288198902011234', 9, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 8, 7, 6, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (872, 1600300872, '鹿存亮3', '男', '1990-01-03', '610122199001031456', 19, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 15, 85, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (873, 1600300873, '姚森3', '男', '1991-02-05', '610122199102058952', 8, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 7, 57, 2, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (874, 1600300874, '云星3', '女', '1993-01-05', '610122199301054789', 40, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 12, 8, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (875, 1600300875, '贾旭明3', '男', '1993-11-11', '610122199311111234', 11, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 13, 74, 2, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (876, 1600300876, '张黎明3', '男', '1991-02-01', '610144199102014569', 41, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 4, 32, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (877, 1600300877, '薛磊3', '男', '1992-07-01', '610144199207017895', 5, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 8, 58, 5, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (878, 1600300878, '王宝东', '男', '1990-01-01', '610122199001011256', 13, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 8, 94, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (879, 1600300879, '陈静4', '女', '1989-02-01', '421288198902011234', 50, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 15, 86, 1, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (880, 1600300880, '赵琳浩4', '男', '1993-03-04', '610122199303041456', 42, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 3, 16, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (881, 1600300881, '鹿存亮4', '男', '1990-01-03', '610122199001031456', 7, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 5, 45, 6, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (882, 1600300882, '姚森4', '男', '1991-02-05', '610122199102058952', 17, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 12, 8, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (883, 1600300883, '云星4', '女', '1993-01-05', '610122199301054789', 8, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 14, 2, 3, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (884, 1600300884, '贾旭明4', '男', '1993-11-11', '610122199311111234', 41, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 8, 95, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (885, 1600300885, '张黎明2', '男', '1991-02-01', '610144199102014569', 16, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 13, 74, 1, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (886, 1600300886, '薛磊4', '男', '1992-07-01', '610144199207017895', 10, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (887, 1600300887, '张洁4', '女', '1990-10-09', '420177199010093652', 55, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 9, 61, 2, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (888, 1600300888, '王宝东', '男', '1990-01-01', '610122199001011256', 24, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 1, 9, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (889, 1600300889, '陈静5', '女', '1989-02-01', '421288198902011234', 11, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 13, 74, 1, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (890, 1600300890, '赵琳浩5', '男', '1993-03-04', '610122199303041456', 37, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 11, 69, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (891, 1600300891, '鹿存亮5', '男', '1990-01-03', '610122199001031456', 40, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 10, 65, 5, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (892, 1600300892, '姚森5', '男', '1991-02-05', '610122199102058952', 32, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 1, 68, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (893, 1600300893, '云星5', '女', '1993-01-05', '610122199301054789', 42, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 12, 73, 1, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (894, 1600300894, '贾旭明5', '男', '1993-11-11', '610122199311111234', 2, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 7, 55, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (895, 1600300895, '张黎明5', '男', '1991-02-01', '610144199102014569', 46, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 16, 87, 2, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (896, 1600300896, '薛磊5', '男', '1992-07-01', '610144199207017895', 6, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 3, 24, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (897, 1600300897, '张洁5', '女', '1990-10-09', '420177199010093652', 55, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 12, 73, 6, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (898, 1600300898, '王宝东', '男', '1990-01-01', '610122199001011256', 36, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 9, 61, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (899, 1600300899, '陈静6', '女', '1989-02-01', '421288198902011234', 15, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 13, 74, 6, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (900, 1600300900, '赵琳浩6', '男', '1993-03-04', '610122199303041456', 20, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 7, 57, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (901, 1600300901, '鹿存亮6', '男', '1990-01-03', '610122199001031456', 53, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 3, 24, 3, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (902, 1600300902, '姚森6', '男', '1991-02-05', '610122199102058952', 39, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 12, 71, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (903, 1600300903, '云星6', '女', '1993-01-05', '610122199301054789', 36, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 4, 19, 1, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (904, 1600300904, '王宝东', '男', '1990-01-01', '610122199001011256', 6, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 1, 75, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (905, 1600300905, '陈静', '女', '1989-02-01', '421288198902011234', 33, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 11, 67, 3, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (906, 1600300906, '赵琳浩', '男', '1993-03-04', '610122199303041456', 37, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 12, 70, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (907, 1600300907, '鹿存亮', '男', '1990-01-03', '610122199001031456', 28, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 14, 2, 5, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (908, 1600300908, '姚森', '男', '1991-02-05', '610122199102058952', 29, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 6, 53, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (909, 1600300909, '贾旭明', '男', '1993-11-11', '610122199311111234', 4, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 16, 87, 2, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (910, 1600300910, '张黎明', '男', '1991-02-01', '610144199102014569', 42, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 16, 87, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (911, 1600300911, '薛磊', '男', '1992-07-01', '610144199207017895', 32, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 10, 13, 5, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (912, 1600300912, '张洁', '女', '1990-10-09', '420177199010093652', 35, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 5, 27, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (913, 1600300913, '王大志', '男', '1990-01-01', '610122199001011256', 21, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 16, 87, 1, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (914, 1600300914, '陈静2', '女', '1989-02-01', '421288198902011234', 56, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 12, 73, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (915, 1600300915, '赵琳浩2', '男', '1993-03-04', '610122199303041456', 49, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 16, 87, 3, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (916, 1600300916, '鹿存亮2', '男', '1990-01-03', '610122199001031456', 23, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 10, 11, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (917, 1600300917, '姚森2', '男', '1991-02-05', '610122199102058952', 21, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 11, 78, 2, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (918, 1600300918, '云星2', '女', '1993-01-05', '610122199301054789', 37, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 14, 2, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (919, 1600300919, '贾旭明2', '男', '1993-11-11', '610122199311111234', 8, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 16, 87, 3, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (920, 1600300920, '王一亭', '男', '1991-02-01', '610144199102014569', 40, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 12, 79, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (921, 1600300921, '薛磊2', '男', '1992-07-01', '610144199207017895', 9, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 3, 29, 6, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (922, 1600300922, '张洁2', '女', '1990-10-09', '420177199010093652', 33, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 8, 58, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (923, 1600300923, '王大志', '男', '1990-01-01', '610122199001011256', 27, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 9, 6, 2, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (924, 1600300924, '陈静3', '女', '1989-02-01', '421288198902011234', 34, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 12, 71, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (925, 1600300925, '鹿存亮3', '男', '1990-01-03', '610122199001031456', 34, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 3, 88, 1, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (926, 1600300926, '姚森3', '男', '1991-02-05', '610122199102058952', 14, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 1, 9, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (927, 1600300927, '云星3', '女', '1993-01-05', '610122199301054789', 19, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 4, 32, 2, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (928, 1600300928, '贾旭明3', '男', '1993-11-11', '610122199311111234', 53, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 1, 12, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (929, 1600300929, '张黎明3', '男', '1991-02-01', '610144199102014569', 42, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 10, 64, 3, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (930, 1600300930, '薛磊3', '男', '1992-07-01', '610144199207017895', 51, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 13, 74, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (931, 1600300931, '王大志', '男', '1990-01-01', '610122199001011256', 19, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 3, 88, 4, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (932, 1600300932, '陈静4', '女', '1989-02-01', '421288198902011234', 49, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 4, 31, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (933, 1600300933, '赵琳浩4', '男', '1993-03-04', '610122199303041456', 24, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 7, 57, 4, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (934, 1600300934, '鹿存亮4', '男', '1990-01-03', '610122199001031456', 27, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 11, 67, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (935, 1600300935, '姚森4', '男', '1991-02-05', '610122199102058952', 6, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '硕士', '西北大学', 12, 70, 4, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (936, 1600300936, '云星4', '女', '1993-01-05', '610122199301054789', 5, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 7, 57, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (937, 1600300937, '贾旭明4', '男', '1993-11-11', '610122199311111234', 5, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 6, 51, 5, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (938, 1600300938, '谢工', '女', '1970-01-01', '618177197001011234', 7, '江苏', 1, '584991843@qq.com', '18558887788', '北京', '本科', '桂林电子科技大学', 4, 5, NULL, NULL, NULL, '2018-01-01', '2023-01-01');
INSERT INTO `student` VALUES (939, 1600300939, '林昭亮', '男', '1990-01-01', '610122199809091234', 21, '广东', 13, '584991843@qq.com', '16767776654', '广东深圳', '本科', '桂林电子科技大学', 3, 28, 3, NULL, NULL, '2018-01-01', '2023-01-01');
INSERT INTO `student` VALUES (940, 1600300940, 'javaboy', '男', '1989-12-31', '610122199001011256', 26, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '桂林电子科技大学', 12, 73, NULL, NULL, NULL, '2017-12-31', '2019-12-31');
INSERT INTO `student` VALUES (941, 1600300941, 'javaboy', '男', '1989-12-31', '610122199001011256', 11, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '桂林电子科技大学', 9, 61, 5, NULL, NULL, '2017-12-31', '2019-12-31');
INSERT INTO `student` VALUES (942, 1600300942, 'javaboy', '男', '1989-12-31', '610122199001011256', 34, '', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '桂林电子科技大学', 9, 97, NULL, NULL, NULL, '2017-12-31', '2019-12-31');
INSERT INTO `student` VALUES (943, 1600300943, 'javaboy', '男', '1989-12-31', '610122199001011256', 23, '陕西', 13, 'laowan@123.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 6, 52, 4, NULL, NULL, '2017-12-31', '2019-12-31');
INSERT INTO `student` VALUES (944, 1600300944, 'javaboy', '男', '1989-12-31', '610122199001011256', 12, '陕西', 13, 'laowan@123.com', '18565558897', '深圳市南山区', '本科', '桂林电子科技大学', 1, 9, NULL, NULL, NULL, '2017-12-31', '2019-12-31');
INSERT INTO `student` VALUES (945, 1600300945, 'javaboy', '男', '1989-12-31', '610122199001011256', 45, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '桂林电子科技大学', 5, 45, 5, NULL, NULL, '2017-12-31', '2019-12-31');
INSERT INTO `student` VALUES (946, 1600300946, 'javaboy', '男', '1989-12-31', '610122199001011256', 24, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '桂林电子科技大学', 4, 35, NULL, NULL, NULL, '2017-12-31', '2024-02-29');
INSERT INTO `student` VALUES (947, 1600300947, 'javaboy666', '男', '2019-11-10', '610122199911111111', 39, '广东', 13, '123@qq.com', '12345678901', '广东深圳', '硕士', '深圳大学', 2, 17, 7, NULL, NULL, '2019-11-10', '2023-02-22');
INSERT INTO `student` VALUES (948, 1600300948, 'javaboy666', '男', '2019-11-10', '610122199911111111', 13, '广东', 13, '123@qq.com', '12345678901', '广东深圳', '本科', '桂林电子科技大学', 3, 22, NULL, NULL, NULL, '2019-11-10', '2023-02-22');
INSERT INTO `student` VALUES (949, 1600300949, '王大志', '男', '1990-01-01', '610122199001011256', 55, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '桂林电子科技大学', 14, 2, 5, NULL, NULL, '2018-01-01', '2020-01-01');
INSERT INTO `student` VALUES (950, 1600300950, '陈静', '女', '1989-02-01', '421288198902011234', 16, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 15, 85, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (951, 1600300951, '赵琳浩', '男', '1993-03-04', '610122199303041456', 23, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '本科', '哈尔滨理工大学', 14, 2, 2, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (952, 1600300952, '鹿存亮', '男', '1990-01-03', '610122199001031456', 11, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (953, 1600300953, '姚森', '男', '1991-02-05', '610122199102058952', 39, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '博士', '西北大学', 13, 74, 2, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (954, 1600300954, '云星', '女', '1993-01-05', '610122199301054789', 50, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (955, 1600300955, '贾旭明', '男', '1993-11-11', '610122199311111234', 25, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 12, 71, 2, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (956, 1600300956, '张黎明', '男', '1991-02-01', '610144199102014569', 28, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '硕士', '清华大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (957, 1600300957, '薛磊', '男', '1992-07-01', '610144199207017895', 10, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 16, 87, 2, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (958, 1600300958, '张洁', '女', '1990-10-09', '420177199010093652', 19, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (959, 1600300959, '王大志', '男', '1990-01-01', '610122199001011256', 10, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '桂林电子科技大学', 1, 4, 5, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (960, 1600300960, '陈静2', '女', '1989-02-01', '421288198902011234', 48, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 7, 56, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (961, 1600300961, '赵琳浩2', '男', '1993-03-04', '610122199303041456', 43, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '本科', '哈尔滨理工大学', 15, 86, 7, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (962, 1600300962, '鹿存亮2', '男', '1990-01-03', '610122199001031456', 17, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 11, 69, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (963, 1600300963, '姚森2', '男', '1991-02-05', '610122199102058952', 7, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '博士', '西北大学', 11, 67, 4, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (964, 1600300964, '云星2', '女', '1993-01-05', '610122199301054789', 40, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '本科', '桂林电子科技大学', 8, 58, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (965, 1600300965, '贾旭明2', '男', '1993-11-11', '610122199311111234', 11, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 9, 62, 4, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (966, 1600300966, '王一亭', '男', '1991-02-01', '610144199102014569', 45, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '硕士', '清华大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (967, 1600300967, '薛磊2', '男', '1992-07-01', '610144199207017895', 27, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 2, 81, 6, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (968, 1600300968, '张洁2', '女', '1990-10-09', '420177199010093652', 55, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 2, 14, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (969, 1600300969, '王大志', '男', '1990-01-01', '610122199001011256', 26, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '桂林电子科技大学', 2, 18, 1, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (970, 1600300970, '陈静3', '女', '1989-02-01', '421288198902011234', 19, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (971, 1600300971, '鹿存亮3', '男', '1990-01-03', '610122199001031456', 16, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '哈尔滨理工大学', 16, 87, 6, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (972, 1600300972, '姚森3', '男', '1991-02-05', '610122199102058952', 22, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 13, 74, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (973, 1600300973, '云星3', '女', '1993-01-05', '610122199301054789', 5, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '博士', '西安电子科技学校', 4, 32, 5, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (974, 1600300974, '贾旭明3', '男', '1993-11-11', '610122199311111234', 13, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 9, 6, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (975, 1600300975, '张黎明3', '男', '1991-02-01', '610144199102014569', 49, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 2, 83, 5, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (976, 1600300976, '薛磊3', '男', '1992-07-01', '610144199207017895', 41, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 1, 9, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (977, 1600300977, '王大志', '男', '1990-01-01', '610122199001011256', 55, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 11, 78, 4, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (978, 1600300978, '陈静4', '女', '1989-02-01', '421288198902011234', 43, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 4, 5, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (979, 1600300979, '赵琳浩4', '男', '1993-03-04', '610122199303041456', 47, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 12, 8, 4, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (980, 1600300980, '鹿存亮4', '男', '1990-01-03', '610122199001031456', 53, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 7, 54, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (981, 1600300981, '姚森4', '男', '1991-02-05', '610122199102058952', 10, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 14, 2, 2, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (982, 1600300982, '云星4', '女', '1993-01-05', '610122199301054789', 3, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '本科', '桂林电子科技大学', 11, 67, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (983, 1600300983, '贾旭明4', '男', '1993-11-11', '610122199311111234', 36, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 12, 71, 1, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (984, 1600300984, '张黎明2', '男', '1991-02-01', '610144199102014569', 4, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 13, 74, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (985, 1600300985, '薛磊4', '男', '1992-07-01', '610144199207017895', 25, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 2, 15, 6, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (986, 1600300986, '张洁4', '女', '1990-10-09', '420177199010093652', 53, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 11, 69, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (987, 1600300987, '王大志', '男', '1990-01-01', '610122199001011256', 28, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '桂林电子科技大学', 5, 91, 2, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (988, 1600300988, '陈静5', '女', '1989-02-01', '421288198902011234', 32, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '博士', '武汉大学', 2, 18, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (989, 1600300989, '赵琳浩5', '男', '1993-03-04', '610122199303041456', 19, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 15, 85, 3, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (990, 1600300990, '鹿存亮5', '男', '1990-01-03', '610122199001031456', 54, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 3, 16, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (991, 1600300991, '姚森5', '男', '1991-02-05', '610122199102058952', 45, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '硕士', '西北大学', 10, 65, 2, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (992, 1600300992, '云星5', '女', '1993-01-05', '610122199301054789', 10, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '本科', '桂林电子科技大学', 11, 67, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (993, 1600300993, '贾旭明5', '男', '1993-11-11', '610122199311111234', 22, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 9, 61, 2, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (994, 1600300994, '张黎明5', '男', '1991-02-01', '610144199102014569', 26, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 16, 87, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (995, 1600300995, '薛磊5', '男', '1992-07-01', '610144199207017895', 9, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 16, 87, 6, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (996, 1600300996, '张洁5', '女', '1990-10-09', '420177199010093652', 20, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 6, 53, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (997, 1600300997, '王大志', '男', '1990-01-01', '610122199001011256', 18, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (998, 1600300998, '陈静6', '女', '1989-02-01', '421288198902011234', 27, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 16, 87, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (999, 1600300999, '赵琳浩6', '男', '1993-03-04', '610122199303041456', 25, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 4, 35, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (1000, 1600301000, '鹿存亮6', '男', '1990-01-03', '610122199001031456', 43, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '硕士', '哈尔滨理工大学', 12, 70, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (1001, 1600301001, '姚森6', '男', '1991-02-05', '610122199102058952', 31, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 4, 35, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (1002, 1600301002, '云星6', '女', '1993-01-05', '610122199301054789', 22, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '本科', '桂林电子科技大学', 9, 97, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (1003, 1600301003, '王大志', '男', '1990-01-01', '610122199001011256', 19, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '桂林电子科技大学', 2, 80, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (1004, 1600301004, '陈静', '女', '1989-02-01', '421288198902011234', 26, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '武汉大学', 5, 44, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (1005, 1600301005, '赵琳浩', '男', '1993-03-04', '610122199303041456', 16, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 5, 46, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (1006, 1600301006, '鹿存亮', '男', '1990-01-03', '610122199001031456', 3, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 16, 87, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (1007, 1600301007, '姚森', '男', '1991-02-05', '610122199102058952', 21, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 4, 90, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (1008, 1600301008, '贾旭明', '男', '1993-11-11', '610122199311111234', 38, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 2, 15, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (1009, 1600301009, '张黎明', '男', '1991-02-01', '610144199102014569', 16, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '硕士', '清华大学', 10, 76, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (1010, 1600301010, '薛磊', '男', '1992-07-01', '610144199207017895', 19, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 6, 52, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (1011, 1600301011, '张洁', '女', '1990-10-09', '420177199010093652', 48, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 6, 51, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (1012, 1600301012, '王宝东', '男', '1990-01-01', '610122199001011256', 15, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '大专', '深圳大学', 4, 31, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (1013, 1600301013, '陈静2', '女', '1989-02-01', '421288198902011234', 43, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '武汉大学', 6, 52, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (1014, 1600301014, '赵琳浩2', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '大专', '哈尔滨理工大学', 4, 36, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (1015, 1600301015, '鹿存亮2', '男', '1990-01-03', '610122199001031456', 43, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '大专', '哈尔滨理工大学', 3, 24, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (1016, 1600301016, '姚森2', '男', '1991-02-05', '610122199102058952', 45, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '大专', '西北大学', 14, 2, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (1017, 1600301017, '云星2', '女', '1993-01-05', '610122199301054789', 40, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '大专', '西安电子科技学校', 14, 2, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (1018, 1600301018, '贾旭明2', '男', '1993-11-11', '610122199311111234', 9, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '大专', '西北大学', 12, 8, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (1019, 1600301019, '王一亭', '男', '1991-02-01', '610144199102014569', 36, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '大专', '清华大学', 1, 75, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (1020, 1600301020, '薛磊2', '男', '1992-07-01', '610144199207017895', 39, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 1, 9, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (1021, 1600301021, '张洁2', '女', '1990-10-09', '420177199010093652', 31, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 16, 87, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (1022, 1600301022, '王宝东', '男', '1990-01-01', '610122199001011256', 38, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '桂林电子科技大学', 8, 59, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (1023, 1600301023, '陈静3', '女', '1989-02-01', '421288198902011234', 40, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '博士', '武汉大学', 15, 86, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (1024, 1600301024, '鹿存亮3', '男', '1990-01-03', '610122199001031456', 30, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 2, 21, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (1025, 1600301025, '姚森3', '男', '1991-02-05', '610122199102058952', 29, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '硕士', '西北大学', 12, 70, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (1026, 1600301026, '云星3', '女', '1993-01-05', '610122199301054789', 54, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 15, 86, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (1027, 1600301027, '贾旭明3', '男', '1993-11-11', '610122199311111234', 18, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 6, 52, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (1028, 1600301028, '张黎明3', '男', '1991-02-01', '610144199102014569', 35, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 2, 81, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (1029, 1600301029, '薛磊3', '男', '1992-07-01', '610144199207017895', 10, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 1, 10, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (1030, 1600301030, '王宝东', '男', '1990-01-01', '610122199001011256', 56, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '桂林电子科技大学', 1, 1, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (1031, 1600301031, '陈静4', '女', '1989-02-01', '421288198902011234', 26, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '武汉大学', 7, 56, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (1032, 1600301032, '赵琳浩4', '男', '1993-03-04', '610122199303041456', 19, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 8, 7, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (1033, 1600301033, '鹿存亮4', '男', '1990-01-03', '610122199001031456', 15, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 5, 39, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (1034, 1600301034, '姚森4', '男', '1991-02-05', '610122199102058952', 15, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 2, 17, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (1035, 1600301035, '云星4', '女', '1993-01-05', '610122199301054789', 29, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '本科', '桂林电子科技大学', 5, 44, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (1036, 1600301036, '贾旭明4', '男', '1993-11-11', '610122199311111234', 47, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '硕士', '西北大学', 11, 78, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (1037, 1600301037, '谢工', '女', '1970-01-01', '618177197001011234', 36, '江苏', 1, '584991843@qq.com', '18558887788', '北京', '本科', '桂林电子科技大学', 3, 16, NULL, NULL, NULL, '2018-01-01', '2023-01-01');
INSERT INTO `student` VALUES (1038, 1600301038, '林昭亮', '男', '1990-01-01', '610122199809091234', 37, '广东', 13, '584991843@qq.com', '16767776654', '广东深圳', '本科', '桂林电子科技大学', 8, 59, NULL, NULL, NULL, '2018-01-01', '2023-01-01');
INSERT INTO `student` VALUES (1039, 1600301039, '王大志', '男', '1990-01-01', '610122199001011256', 23, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '桂林电子科技大学', 1, 68, NULL, NULL, NULL, '2018-01-01', '2020-01-01');
INSERT INTO `student` VALUES (1040, 1600301040, '陈静', '女', '1989-02-01', '421288198902011234', 56, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 5, 48, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (1041, 1600301041, '赵琳浩', '男', '1993-03-04', '610122199303041456', 45, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '本科', '哈尔滨理工大学', 12, 8, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (1042, 1600301042, '鹿存亮', '男', '1990-01-03', '610122199001031456', 55, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 5, 39, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (1043, 1600301043, '姚森', '男', '1991-02-05', '610122199102058952', 29, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '博士', '西北大学', 13, 74, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (1044, 1600301044, '云星', '女', '1993-01-05', '610122199301054789', 36, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '本科', '桂林电子科技大学', 5, 38, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (1045, 1600301045, '贾旭明', '男', '1993-11-11', '610122199311111234', 37, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 11, 78, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (1046, 1600301046, '张黎明', '男', '1991-02-01', '610144199102014569', 19, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '硕士', '清华大学', 8, 60, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (1047, 1600301047, '薛磊', '男', '1992-07-01', '610144199207017895', 39, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 5, 43, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (1048, 1600301048, '张洁', '女', '1990-10-09', '420177199010093652', 25, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 13, 74, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (1049, 1600301049, '王大志', '男', '1990-01-01', '610122199001011256', 9, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '桂林电子科技大学', 1, 75, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (1050, 1600301050, '陈静2', '女', '1989-02-01', '421288198902011234', 25, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 6, 51, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (1051, 1600301051, '赵琳浩2', '男', '1993-03-04', '610122199303041456', 41, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '本科', '哈尔滨理工大学', 12, 70, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (1052, 1600301052, '鹿存亮2', '男', '1990-01-03', '610122199001031456', 17, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 6, 51, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (1053, 1600301053, '姚森2', '男', '1991-02-05', '610122199102058952', 18, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '博士', '西北大学', 12, 73, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (1054, 1600301054, '云星2', '女', '1993-01-05', '610122199301054789', 37, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '本科', '桂林电子科技大学', 1, 12, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (1055, 1600301055, '贾旭明2', '男', '1993-11-11', '610122199311111234', 20, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 7, 57, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (1056, 1600301056, '王一亭', '男', '1991-02-01', '610144199102014569', 44, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '硕士', '清华大学', 16, 87, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (1057, 1600301057, '薛磊2', '男', '1992-07-01', '610144199207017895', 49, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 1, 3, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (1058, 1600301058, '张洁2', '女', '1990-10-09', '420177199010093652', 55, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 13, 74, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (1059, 1600301059, '王大志', '男', '1990-01-01', '610122199001011256', 17, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '桂林电子科技大学', 6, 93, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (1060, 1600301060, '陈静3', '女', '1989-02-01', '421288198902011234', 30, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 3, 24, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (1061, 1600301061, '鹿存亮3', '男', '1990-01-03', '610122199001031456', 43, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '哈尔滨理工大学', 11, 78, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (1062, 1600301062, '姚森3', '男', '1991-02-05', '610122199102058952', 15, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 12, 8, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (1063, 1600301063, '云星3', '女', '1993-01-05', '610122199301054789', 56, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '博士', '西安电子科技学校', 1, 9, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (1064, 1600301064, '贾旭明3', '男', '1993-11-11', '610122199311111234', 11, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 5, 44, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (1065, 1600301065, '张黎明3', '男', '1991-02-01', '610144199102014569', 52, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 1, 75, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (1066, 1600301066, '薛磊3', '男', '1992-07-01', '610144199207017895', 5, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (1067, 1600301067, '王大志', '男', '1990-01-01', '610122199001011256', 32, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 10, 64, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (1068, 1600301068, '陈静4', '女', '1989-02-01', '421288198902011234', 37, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 4, 90, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (1069, 1600301069, '赵琳浩4', '男', '1993-03-04', '610122199303041456', 31, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 1, 9, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (1070, 1600301070, '鹿存亮4', '男', '1990-01-03', '610122199001031456', 42, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 4, 33, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (1071, 1600301071, '姚森4', '男', '1991-02-05', '610122199102058952', 7, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 12, 71, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (1072, 1600301072, '云星4', '女', '1993-01-05', '610122199301054789', 18, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '本科', '桂林电子科技大学', 5, 48, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (1073, 1600301073, '贾旭明4', '男', '1993-11-11', '610122199311111234', 13, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (1074, 1600301074, '张黎明2', '男', '1991-02-01', '610144199102014569', 10, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 13, 74, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (1075, 1600301075, '薛磊4', '男', '1992-07-01', '610144199207017895', 11, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (1076, 1600301076, '张洁4', '女', '1990-10-09', '420177199010093652', 22, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 9, 6, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (1077, 1600301077, '王大志', '男', '1990-01-01', '610122199001011256', 22, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '桂林电子科技大学', 13, 74, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (1078, 1600301078, '陈静5', '女', '1989-02-01', '421288198902011234', 41, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '博士', '武汉大学', 14, 2, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (1079, 1600301079, '赵琳浩5', '男', '1993-03-04', '610122199303041456', 29, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 1, 10, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (1080, 1600301080, '鹿存亮5', '男', '1990-01-03', '610122199001031456', 23, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 8, 94, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (1081, 1600301081, '姚森5', '男', '1991-02-05', '610122199102058952', 27, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '硕士', '西北大学', 1, 75, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (1082, 1600301082, '云星5', '女', '1993-01-05', '610122199301054789', 9, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (1083, 1600301083, '贾旭明5', '男', '1993-11-11', '610122199311111234', 20, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 5, 42, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (1084, 1600301084, '张黎明5', '男', '1991-02-01', '610144199102014569', 14, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '桂林电子科技大学', 10, 77, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (1085, 1600301085, '薛磊5', '男', '1992-07-01', '610144199207017895', 10, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 7, 55, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (1086, 1600301086, '张洁5', '女', '1990-10-09', '420177199010093652', 7, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 8, 59, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (1087, 1600301087, '王大志', '男', '1990-01-01', '610122199001011256', 3, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '桂林电子科技大学', 13, 74, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (1088, 1600301088, '陈静6', '女', '1989-02-01', '421288198902011234', 47, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 10, 77, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (1089, 1600301089, '赵琳浩6', '男', '1993-03-04', '610122199303041456', 7, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 4, 5, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (1090, 1600301090, '鹿存亮6', '男', '1990-01-03', '610122199001031456', 4, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '硕士', '哈尔滨理工大学', 7, 55, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (1091, 1600301091, '姚森6', '男', '1991-02-05', '610122199102058952', 50, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 5, 42, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (1092, 1600301092, '云星6', '女', '1993-01-05', '610122199301054789', 18, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (1093, 1600301093, '王大志', '男', '1990-01-01', '610122199001011256', 50, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '桂林电子科技大学', 15, 85, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (1094, 1600301094, '陈静', '女', '1989-02-01', '421288198902011234', 31, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '武汉大学', 7, 57, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (1095, 1600301095, '赵琳浩', '男', '1993-03-04', '610122199303041456', 1, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 4, 5, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (1096, 1600301096, '鹿存亮', '男', '1990-01-03', '610122199001031456', 24, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 3, 88, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (1097, 1600301097, '姚森', '男', '1991-02-05', '610122199102058952', 5, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '桂林电子科技大学', 1, 75, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (1098, 1600301098, '贾旭明', '男', '1993-11-11', '610122199311111234', 5, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 5, 49, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (1099, 1600301099, '张黎明', '男', '1991-02-01', '610144199102014569', 11, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '硕士', '清华大学', 1, 75, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (1100, 1600301100, '薛磊', '男', '1992-07-01', '610144199207017895', 37, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 4, 5, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (1101, 1600301101, '张洁', '女', '1990-10-09', '420177199010093652', 40, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (1102, 1600301102, '王宝东', '男', '1990-01-01', '610122199001011256', 36, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '大专', '深圳大学', 15, 86, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (1103, 1600301103, '陈静2', '女', '1989-02-01', '421288198902011234', 3, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '武汉大学', 7, 57, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (1104, 1600301104, '赵琳浩2', '男', '1993-03-04', '610122199303041456', 16, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '大专', '哈尔滨理工大学', 10, 63, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (1105, 1600301105, '鹿存亮2', '男', '1990-01-03', '610122199001031456', 16, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '大专', '哈尔滨理工大学', 2, 81, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (1106, 1600301106, '姚森2', '男', '1991-02-05', '610122199102058952', 32, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '大专', '西北大学', 11, 67, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (1107, 1600301107, '云星2', '女', '1993-01-05', '610122199301054789', 55, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '大专', '西安电子科技学校', 6, 53, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (1108, 1600301108, '贾旭明2', '男', '1993-11-11', '610122199311111234', 14, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '大专', '西北大学', 13, 74, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (1109, 1600301109, '王一亭', '男', '1991-02-01', '610144199102014569', 13, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '大专', '清华大学', 3, 88, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (1110, 1600301110, '薛磊2', '男', '1992-07-01', '610144199207017895', 22, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 16, 87, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (1111, 1600301111, '张洁2', '女', '1990-10-09', '420177199010093652', 15, '海南', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (1112, 1600301112, '王宝东', '男', '1990-01-01', '610122199001011256', 7, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 4, 5, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (1113, 1600301113, '陈静3', '女', '1989-02-01', '421288198902011234', 44, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '武汉大学', 15, 85, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (1114, 1600301114, '鹿存亮3', '男', '1990-01-03', '610122199001031456', 35, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '哈尔滨理工大学', 16, 87, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (1115, 1600301115, '姚森3', '男', '1991-02-05', '610122199102058952', 40, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '本科', '西北大学', 15, 85, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (1116, 1600301116, '云星3', '女', '1993-01-05', '610122199301054789', 40, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '本科', '西安电子科技学校', 10, 65, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (1117, 1600301117, '贾旭明3', '男', '1993-11-11', '610122199311111234', 23, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '西北大学', 3, 29, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (1118, 1600301118, '张黎明3', '男', '1991-02-01', '610144199102014569', 52, '广东', 6, 'zhangliming@qq.com', '18979994478', '广东珠海', '本科', '清华大学', 14, 2, NULL, NULL, NULL, '2018-01-01', '2025-01-30');
INSERT INTO `student` VALUES (1119, 1600301119, '薛磊3', '男', '1992-07-01', '610144199207017895', 22, '陕西西安', 13, 'xuelei@qq.com', '15648887741', '西安市雁塔区', '本科', '桂林电子科技大学', 9, 62, NULL, NULL, NULL, '2018-01-01', '2024-01-17');
INSERT INTO `student` VALUES (1120, 1600301120, '王宝东', '男', '1990-01-01', '610122199001011256', 8, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 12, 70, NULL, NULL, NULL, '2018-01-01', '2019-01-01');
INSERT INTO `student` VALUES (1121, 1600301121, '陈静4', '女', '1989-02-01', '421288198902011234', 30, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 12, 8, NULL, NULL, NULL, '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (1122, 1600301122, '赵琳浩4', '男', '1993-03-04', '610122199303041456', 15, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 9, 6, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (1123, 1600301123, '鹿存亮4', '男', '1990-01-03', '610122199001031456', 40, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 2, 17, NULL, NULL, NULL, '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (1124, 1600301124, '姚森4', '男', '1991-02-05', '610122199102058952', 41, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '硕士', '西北大学', 11, 69, NULL, NULL, NULL, '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (1125, 1600301125, '云星4', '女', '1993-01-05', '610122199301054789', 32, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 2, 21, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (1126, 1600301126, '贾旭明4', '男', '1993-11-11', '610122199311111234', 33, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 8, 7, NULL, NULL, NULL, '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (1127, 1600301127, '谢工', '女', '1970-01-01', '618177197001011234', 13, '江苏', 1, '584991843@qq.com', '18558887788', '北京', '本科', '桂林电子科技大学', 10, 66, NULL, NULL, NULL, '2018-01-01', '2023-01-01');
INSERT INTO `student` VALUES (1128, 1600301128, '林昭亮', '男', '1990-01-01', '610122199809091234', 21, '广东', 13, '584991843@qq.com', '16767776654', '广东深圳', '本科', '桂林电子科技大学', 6, 93, NULL, NULL, NULL, '2018-01-01', '2023-01-01');
INSERT INTO `student` VALUES (1129, 1600301129, 'javaboy', '男', '1989-12-31', '610122199001011256', 11, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '桂林电子科技大学', 1, 9, NULL, NULL, NULL, '2017-12-31', '2019-12-31');
INSERT INTO `student` VALUES (1130, 1600301130, 'javaboy', '男', '1989-12-31', '610122199001011256', 45, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '桂林电子科技大学', 16, 87, NULL, NULL, NULL, '2017-12-31', '2019-12-31');
INSERT INTO `student` VALUES (1131, 1600301131, 'javaboy', '男', '1989-12-31', '610122199001011256', 25, '黑龙江', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '桂林电子科技大学', 16, 87, NULL, NULL, NULL, '2017-12-31', '2019-12-31');
INSERT INTO `student` VALUES (1132, 1600301132, 'javaboy', '男', '1989-12-31', '610122199001011256', 47, '陕西', 13, 'laowan@123.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 16, 87, NULL, NULL, NULL, '2017-12-31', '2019-12-31');
INSERT INTO `student` VALUES (1133, 1600301133, 'javaboy', '男', '1989-12-31', '610122199001011256', 47, '陕西', 13, 'laowan@123.com', '18565558897', '深圳市南山区', '本科', '桂林电子科技大学', 14, 2, NULL, NULL, NULL, '2017-12-31', '2019-12-31');
INSERT INTO `student` VALUES (1134, 1600301134, 'javaboy', '男', '1989-12-31', '610122199001011256', 37, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '桂林电子科技大学', 2, 81, NULL, NULL, NULL, '2017-12-31', '2019-12-31');
INSERT INTO `student` VALUES (1135, 1600301135, 'javaboy', '男', '1989-12-31', '610122199001011256', 44, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '桂林电子科技大学', 7, 56, NULL, NULL, NULL, '2017-12-31', '2024-02-29');
INSERT INTO `student` VALUES (1136, 1600301136, 'javaboy666', '男', '2019-11-10', '610122199911111111', 55, '广东', 13, '123@qq.com', '12345678901', '广东深圳', '硕士', '深圳大学', 8, 94, NULL, NULL, NULL, '2019-11-10', '2023-02-22');
INSERT INTO `student` VALUES (1137, 1600301137, 'javaboy666', '男', '2019-11-10', '610122199911111111', 30, '广东', 13, '123@qq.com', '12345678901', '广东深圳', '本科', '桂林电子科技大学', 9, 6, NULL, NULL, NULL, '2019-11-10', '2023-02-22');
INSERT INTO `student` VALUES (1138, 1600301138, 'javaboy', '男', '2019-11-24', '610144199905056666', 39, '广东', 13, '584991843@qq.com', '18566667777', '广东深圳', '本科', '桂林电子科技大学', 2, 14, NULL, NULL, NULL, '2019-11-24', '2022-11-27');
INSERT INTO `student` VALUES (1139, 1600301139, '王大猫', '男', '1997-02-03', '450122199702065555', 8, '广西', 3, '13245653@cess.com', '13764654456', '广东广州', '硕士', '桂林电子科技大学', 3, 23, 1, 'CET4', '计算机二级', '2016-08-29', '2020-06-12');
INSERT INTO `student` VALUES (1140, 1600301140, '龙大龙', '男', '1997-02-02', '450122199702020202', 2, '北京', 1, 'long@cess.com', '13646554564', '北京王府城', '本科', '桂林电子科技大学', 3, 22, 3, 'CET6', '计算机二级', '2016-08-31', '2020-06-08');
INSERT INTO `student` VALUES (1141, 1600301141, '王宝西', '女', '1990-01-01', '610122199001011256', 1, '陕西', 13, 'laowang@qq.com', '18565558897', '深圳市南山区', '本科', '深圳大学', 15, 85, 2, 'CET6', '计算机四级', '2018-01-01', '2020-01-01');
INSERT INTO `student` VALUES (1142, 1600301142, '陈静静', '男', '1989-02-01', '421288198902011234', 2, '海南', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', '本科', '桂林电子科技大学', 1, 1, NULL, 'CET7', '计算机四级', '2015-06-09', '2018-06-08');
INSERT INTO `student` VALUES (1143, 1600301143, '赵雨浩', '女', '1993-03-04', '610122199303041456', 3, '陕西', 3, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', '博士', '哈尔滨理工大学', 9, 97, 2, 'CET8', '计算机四级', '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (1144, 1600301144, '鹿存暗', '女', '1990-01-03', '610122199001031456', 4, '陕西', 3, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', '本科', '桂林电子科技大学', 5, 91, NULL, 'CET4', '计算机二级', '2018-01-01', '2021-07-14');
INSERT INTO `student` VALUES (1145, 1600301145, '姚林', '女', '1991-02-05', '610122199102058952', 1, '河南', 3, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', '硕士', '西北大学', 6, 93, 2, 'CET5', '计算机二级', '2017-01-02', '2024-01-17');
INSERT INTO `student` VALUES (1146, 1600301146, '云星星', '男', '1993-01-05', '610122199301054789', 5, '陕西西安', 1, 'yunxing@qq.com', '15644442252', '陕西西安新城区', '硕士', '西安电子科技学校', 8, 58, 2, 'CET6', '计算机二级', '2018-01-01', '2023-04-13');
INSERT INTO `student` VALUES (1147, 1600301147, '贾旭月', '女', '1993-11-11', '610122199311111234', 8, '广东广州', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', '本科', '桂林电子科技大学', 16, 87, 2, 'CET7', '计算机四级', '2018-01-01', '2023-04-13');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `last_login` datetime(0) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'e5bd9b50c72835127a928dc6e47652a6', 'nQLH6AdvouoRAFP1DRiRKw==', '2020-04-08 11:54:13', 1, '系统管理员', '13888888888', 'admin@cess.com');
INSERT INTO `user` VALUES (2, 'xiaoming', '4da691f5fdb5193253a50d869de83418', '+hQyPVWqzZgX51ICFpTxAw==', '2020-03-28 15:37:50', 1, '小明', '13245644568', 'xiaoming@cess.com');
INSERT INTO `user` VALUES (3, 'lifang', '5d244d1cb121e54d0f278eb0146361b2', 'FpCRVPJlXLr6UvU/SyCvFg==', '2020-04-08 11:54:06', 1, '李芳', '13645657988', 'lifang@cess.com');
INSERT INTO `user` VALUES (4, 'leader', '97e1873dae145132312829dfd3faf1a9', 'kOuh4j9S/HZiF+TDi/oyOw==', '2020-03-28 15:38:26', 1, '校领导', '13645678945', 'leader@cess.com');
INSERT INTO `user` VALUES (5, 'department', '83fbc131b564a338ae5fbac310789653', 'dcSyHpNO9yB7lICEo0gTYw==', '2020-03-29 19:14:37', 1, '就业部工作人员', '13645678945', 'department@cess.com');
INSERT INTO `user` VALUES (6, 'enterprise', 'de262c5eb9512bdaf2fdaacdf4696583', 'Q7E+44NW3ZZYNruZNpOCug==', NULL, 1, '企业单位', '13545654565', 'enterprise@cess.com');
INSERT INTO `user` VALUES (26, 'taozi', '86be28d66a0496e6da502e864a8705f8', '7etC26ZNC/ceoS1VfpXQLw==', NULL, 1, '李桃子', '13565555500', 'taozi@cess.com');
INSERT INTO `user` VALUES (27, 'liuzong', '91e49009bae6ca2c176a85249ae9f7d3', 'LXw8AAXHQIRj0d5952mmDQ==', NULL, 1, '刘总统', '13546545555', 'liuzong@cess.com');
INSERT INTO `user` VALUES (37, 'fjdkadfjkl', '8bf6945ca9388d4c28a82790995cfbe1', 'E8EDJbDi3e7xPMTfvUHNCA==', NULL, 0, 'fsafggg', '13545644564', 'fdasj23@aa.cc');
INSERT INTO `user` VALUES (38, 'fjdkakkkkkkkkkkkkkkk', 'f6395d5d585896a28806d0938f52ab42', 'O4LIy7oc4J+f5cQxlvlR+A==', NULL, 0, 'fdasf', '13545654565', 'fas@qq.cfdsa');
INSERT INTO `user` VALUES (40, '4545', '67db2642e3bb914a9ce21bff91c99e6e', 'Ar2+lcpvSKQf00cJsRj5rQ==', NULL, 0, '哈哈哈哈哈哈哈哈11111', '13345654565', 'hahahahahha@foxmail.com');

SET FOREIGN_KEY_CHECKS = 1;
