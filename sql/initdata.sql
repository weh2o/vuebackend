CREATE DATABASE  IF NOT EXISTS `joe_ptsd` /*!40100 DEFAULT CHARACTER SET utf8mb3 COLLATE utf8mb3_bin */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `joe_ptsd`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: joe_ptsd
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `course_student`
--

DROP TABLE IF EXISTS `course_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_student` (
  `course_id` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `student_id` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  UNIQUE KEY `uk_course_student` (`course_id`,`student_id`),
  KEY `fk_student_course` (`student_id`),
  CONSTRAINT `fk_course_student` FOREIGN KEY (`course_id`) REFERENCES `j_course` (`id`),
  CONSTRAINT `fk_student_course` FOREIGN KEY (`student_id`) REFERENCES `j_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_student`
--

LOCK TABLES `course_student` WRITE;
/*!40000 ALTER TABLE `course_student` DISABLE KEYS */;
/*!40000 ALTER TABLE `course_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `j_course`
--

DROP TABLE IF EXISTS `j_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `j_course` (
  `id` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `disable` bit(1) DEFAULT NULL,
  `note` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `sort` int DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `count` int DEFAULT NULL,
  `deadline` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `end_time` time(6) DEFAULT NULL,
  `max_count` int DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `start_time` time(6) DEFAULT NULL,
  `course_location_id` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `teacher_id` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_course_course_location` (`course_location_id`),
  KEY `fk_course_teacher` (`teacher_id`),
  CONSTRAINT `fk_course_course_location` FOREIGN KEY (`course_location_id`) REFERENCES `j_course_location` (`id`),
  CONSTRAINT `fk_course_teacher` FOREIGN KEY (`teacher_id`) REFERENCES `j_teacher` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `j_course`
--

LOCK TABLES `j_course` WRITE;
/*!40000 ALTER TABLE `j_course` DISABLE KEYS */;
INSERT INTO `j_course` VALUES ('94bbac98-c85f-4b75-981c-4c041c4fb96d','2024-04-16 19:15:26.980115',NULL,NULL,NULL,'2024-04-16 19:15:27.080875',NULL,'2030-01-01','2030-01-01','15:00:00.000000',30,'如何不被老婆發現私房錢','2024-04-16','14:00:00.000000','2d1c7da4-366b-4104-aca5-9803aa06aa2b','382eaa3c-817f-44d8-8505-358e36516a97');
/*!40000 ALTER TABLE `j_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `j_course_location`
--

DROP TABLE IF EXISTS `j_course_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `j_course_location` (
  `id` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `disable` bit(1) DEFAULT NULL,
  `note` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `sort` int DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `address` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `code` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `name_zh` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `j_course_location`
--

LOCK TABLES `j_course_location` WRITE;
/*!40000 ALTER TABLE `j_course_location` DISABLE KEYS */;
INSERT INTO `j_course_location` VALUES ('2d1c7da4-366b-4104-aca5-9803aa06aa2b','2024-04-16 19:09:18.849085',NULL,NULL,NULL,'2024-04-16 19:09:18.849085',NULL,'1','LIBRARY','圖書館'),('2db71f50-f84e-4b4a-a99d-06a6b034f3f1','2024-04-16 19:09:18.843123',NULL,NULL,NULL,'2024-04-16 19:09:18.843123',NULL,'0','OTHER','其他'),('7e485610-7148-4f85-84e7-b05ed64ace88','2024-04-16 19:09:18.854072',NULL,NULL,NULL,'2024-04-16 19:09:18.854072',NULL,'2','PLAYGROUND','操場'),('88e59eb3-2d8c-4b81-8529-6c762b085d89','2024-04-16 19:09:18.858721',NULL,NULL,NULL,'2024-04-16 19:09:18.858721',NULL,'3','GYMNASIUM','體育館'),('c7446976-c4f1-478a-a6b8-d26a6e93f870','2024-04-16 19:09:18.861906',NULL,NULL,NULL,'2024-04-16 19:09:18.861906',NULL,'4','CLASSROOM_101','101教室');
/*!40000 ALTER TABLE `j_course_location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `j_identity`
--

DROP TABLE IF EXISTS `j_identity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `j_identity` (
  `id` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `disable` bit(1) DEFAULT NULL,
  `note` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `sort` int DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `code` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `name_zh` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ln1wpmslm7idwh706u6tp2rsw` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `j_identity`
--

LOCK TABLES `j_identity` WRITE;
/*!40000 ALTER TABLE `j_identity` DISABLE KEYS */;
INSERT INTO `j_identity` VALUES ('1b2e55ca-804f-4d6c-b273-62b765cdfe93','2024-04-16 19:09:17.107239',NULL,NULL,NULL,'2024-04-16 19:09:17.107239','2','TEACHER','老師'),('37484185-548e-42a9-8e0e-e8e459d46af7','2024-04-16 19:09:17.111986',NULL,NULL,NULL,'2024-04-16 19:09:17.111986','3','STUDENT','學生'),('48e31dd5-a439-4ea5-b229-d83463403e00','2024-04-16 19:09:17.076110',NULL,NULL,NULL,'2024-04-16 19:09:17.076110','1','ADMIN','系統管理者'),('76099522-003b-4320-9dbb-63a1f3b735b2','2024-04-16 19:09:17.116319',NULL,NULL,NULL,'2024-04-16 19:09:17.116319','99','VISITOR','遊客');
/*!40000 ALTER TABLE `j_identity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `j_menu`
--

DROP TABLE IF EXISTS `j_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `j_menu` (
  `id` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `disable` bit(1) DEFAULT NULL,
  `note` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `sort` int DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `icon` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `label` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `path` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `url` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `parent_id` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_menu_parent` (`parent_id`),
  CONSTRAINT `fk_menu_parent` FOREIGN KEY (`parent_id`) REFERENCES `j_menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `j_menu`
--

LOCK TABLES `j_menu` WRITE;
/*!40000 ALTER TABLE `j_menu` DISABLE KEYS */;
INSERT INTO `j_menu` VALUES ('0c07d5bd-2f9e-4385-9c5e-d4de43a7fa1b','2024-04-16 19:09:17.449413',NULL,NULL,3,'2024-04-16 19:09:17.454781','Tickets','公告管理',NULL,'/announcement',NULL,NULL),('44596c1c-8d67-4514-a206-7fca5733fd99','2024-04-16 19:09:17.345626',NULL,NULL,0,'2024-04-16 19:09:17.396518','House','首頁',NULL,'/home',NULL,NULL),('7030c90e-0a9e-4d15-a514-e36624ff16c5','2024-04-16 19:09:17.420173',NULL,NULL,1,'2024-04-16 19:09:17.426040','User','學生管理',NULL,'/studentManagement',NULL,NULL),('bb7bde16-26fe-4dfa-b38a-59d9b6a9f688','2024-04-16 19:09:17.434059',NULL,NULL,2,'2024-04-16 19:09:17.439941','Memo','課程管理',NULL,'/course',NULL,NULL),('d28f8295-a4db-4ee8-b30c-439339bf80b7','2024-04-16 19:09:17.461111',NULL,NULL,1,'2024-04-16 19:09:17.467015','User','教師管理',NULL,'/teacherManagement',NULL,NULL);
/*!40000 ALTER TABLE `j_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `j_role`
--

DROP TABLE IF EXISTS `j_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `j_role` (
  `id` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `disable` bit(1) DEFAULT NULL,
  `note` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `sort` int DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `name_zh` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `j_role`
--

LOCK TABLES `j_role` WRITE;
/*!40000 ALTER TABLE `j_role` DISABLE KEYS */;
INSERT INTO `j_role` VALUES ('0ae8c2a2-d941-4c17-aee8-fc86d4a544cf','2024-04-16 19:09:17.229164',NULL,NULL,NULL,'2024-04-16 19:09:17.229164','DIRECTOR','主任'),('1f65722a-74b0-4ebf-be94-d5ac87d35f52','2024-04-16 19:09:17.233920',NULL,NULL,NULL,'2024-04-16 19:09:17.233920','VISITOR','遊客'),('29369945-a9fc-4757-b479-489e68f81ff7','2024-04-16 19:09:17.224442',NULL,NULL,NULL,'2024-04-16 19:09:17.224442','STUDENT','學生'),('7a1deb94-ae3b-4067-a207-260c17344e87','2024-04-16 19:09:17.211058',NULL,NULL,NULL,'2024-04-16 19:09:17.211058','ADMIN','系統管理者'),('ff801370-69fa-4f3a-ada0-6580c57f42ba','2024-04-16 19:09:17.218951',NULL,NULL,NULL,'2024-04-16 19:09:17.218951','TEACHER','老師');
/*!40000 ALTER TABLE `j_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `j_student`
--

DROP TABLE IF EXISTS `j_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `j_student` (
  `no` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `id` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_388t2yvogskj99dwuk4x4eakt` (`no`),
  CONSTRAINT `FKrae7oppvmohuf55tv6f10vfq3` FOREIGN KEY (`id`) REFERENCES `j_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `j_student`
--

LOCK TABLES `j_student` WRITE;
/*!40000 ALTER TABLE `j_student` DISABLE KEYS */;
INSERT INTO `j_student` VALUES (NULL,'68c0c44f-6df5-4616-bba2-88fb1ac04d81'),('10001','d747e9f9-171a-4609-a735-e920cd7af6c8'),('10002','f530955a-db3b-432f-ae42-b0d44e62dad4'),('10003','8bf6d8b9-b080-4262-9df6-5545d6ece3da'),('10004','723af303-79b8-4dc0-9f37-b03b5c9d2f09'),('10005','90d720e7-fa99-4bee-a10d-d2a7e9f21f93'),('10006','6e0e4956-9dec-4e9c-a651-acf4c0ff29b1'),('10007','34e14f92-3e67-4b59-a7ef-ada932e10b4a'),('10008','3a13b21f-54b9-41c8-98fd-d568019fe75e'),('10009','5397e43c-b7f1-4fe3-99b1-ab4914eabecb'),('10010','1795c669-6697-4d7d-9ca7-beee94753201'),('10011','035e671a-b691-4194-8854-8555701da5d1'),('10012','2f47ded9-e02b-49ff-b62d-ab6e6f6dffbf'),('10013','6f41e306-0729-4934-bb08-a84930964ddc'),('10014','a4760f55-870c-401c-a953-2b7c5c493607'),('10015','133269d8-5701-4691-82aa-fb0a1eed024b'),('10016','378f0f77-928c-428a-a173-0b4b79f1621d'),('10017','452b167f-ad79-4b75-bd7e-9b192b1fbd59'),('10018','4ce8dcb2-2094-4d9d-b597-0779a5294a2d'),('10019','6bf66644-0485-4276-8a52-ccc789800b6b'),('10020','f34434a0-b7d6-4913-8946-0c5923041779'),('10021','bd3fd254-7f6b-4768-8b01-182c6c5be707'),('10022','7cd5fc7d-4544-4ce8-a9fe-531e131a600f'),('10023','3ab87fc0-055f-4a5a-b717-7953e2141492'),('10024','7ee1d8e1-2a0b-4a65-9628-b662ef3a221d'),('10025','551de222-ec6e-41bd-a758-bb4b63dd93a5'),('10026','539d984b-ffb9-4fdf-956d-e188b70873a3'),('10027','7fdc7a2c-8d3b-4675-9879-d1b831c29ca1'),('10028','dc8faa57-639e-4282-a0f0-13c688e33bec'),('10029','a6408a3b-f6c3-46cd-a6f1-f60d4f9b3664'),('10030','337e7140-2dd8-4864-8d36-d73b9d40c1d0'),('10031','a14c8b12-5629-402d-b5cc-3ef967ceefed'),('10032','d6589ce7-d8c0-42ff-b09c-bf361ec28672'),('10033','58ce215e-1fc9-4bee-9621-bd2a91fc4505'),('10034','9b182e88-f576-4b3a-aef1-b66fdceb4fe9'),('10035','bc771a7e-8a96-4867-9db3-755d8590c1b6'),('10036','120ff851-0b08-4ac3-9619-f2e144ef1656'),('10037','207db4b4-a265-4d5a-aa55-ddad3f8e8663'),('10038','c60260f9-5231-4d17-b53a-ef83053e4c55'),('10039','fe7a01eb-f9d2-4c59-8645-f08cdc277543'),('10040','a5d3dfd8-09d0-4d02-ab3c-eb9db62147db'),('10041','8a9581ba-0c35-43b6-b49f-bd334787f438'),('10042','11c0d511-5aac-4324-b6f2-0d778f389e24'),('10043','21761f9f-faf5-42bf-afe7-81594356d213'),('10044','1d098772-c38b-4c55-9dc3-2e757e7b7150'),('10045','e2841976-6674-4241-87c9-0d0d461768f1'),('10046','6693c893-aa4e-40d9-ad1c-ff12c4126a53'),('10047','fdfabdb7-e346-4ce3-a037-fe16c3d5cfce'),('10048','96a81b9a-bf89-4bc0-8720-2491a98678df'),('10049','a03a1f6e-886b-4302-89ab-dd5437b23c62'),('10050','0626d5da-7a06-4927-be5d-027aa50a6a9e');
/*!40000 ALTER TABLE `j_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `j_teacher`
--

DROP TABLE IF EXISTS `j_teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `j_teacher` (
  `id` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `teacher_no_id` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7dq9py99dm4max6llisxto2n2` (`teacher_no_id`),
  CONSTRAINT `fk_teacher_teacher_no` FOREIGN KEY (`teacher_no_id`) REFERENCES `j_teacher_no` (`id`),
  CONSTRAINT `FKir7h8qxf8p90jy1wkxyd42v4w` FOREIGN KEY (`id`) REFERENCES `j_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `j_teacher`
--

LOCK TABLES `j_teacher` WRITE;
/*!40000 ALTER TABLE `j_teacher` DISABLE KEYS */;
INSERT INTO `j_teacher` VALUES ('382eaa3c-817f-44d8-8505-358e36516a97','cd5485ed-3df8-4210-9f68-102e570e8139');
/*!40000 ALTER TABLE `j_teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `j_teacher_no`
--

DROP TABLE IF EXISTS `j_teacher_no`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `j_teacher_no` (
  `id` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `disable` bit(1) DEFAULT NULL,
  `note` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `sort` int DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `available` bit(1) DEFAULT NULL,
  `no` varchar(8) COLLATE utf8mb3_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_p3st6llyilrxk29wts893yi9v` (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `j_teacher_no`
--

LOCK TABLES `j_teacher_no` WRITE;
/*!40000 ALTER TABLE `j_teacher_no` DISABLE KEYS */;
INSERT INTO `j_teacher_no` VALUES ('07358d76-727b-4cde-8537-667958f3c24d','2024-04-16 19:09:18.709230',NULL,NULL,NULL,'2024-04-16 19:09:18.709230',_binary '','t1006'),('1593e142-ea44-489a-b0fc-67a7cc733080','2024-04-16 19:11:21.220654',NULL,NULL,NULL,'2024-04-16 19:11:21.220654',_binary '','t101'),('45273a0e-a0cf-43ee-bbfb-fe3fafe61f0c','2024-04-16 19:11:21.286635',NULL,NULL,NULL,'2024-04-16 19:11:21.286635',_binary '','t109'),('485a027e-dffe-40db-aa35-34d7f692514c','2024-04-16 19:11:21.268588',NULL,NULL,NULL,'2024-04-16 19:11:21.268588',_binary '','t105'),('50107adc-c32d-4918-a083-151720d63b2d','2024-04-16 19:11:21.290857',NULL,NULL,NULL,'2024-04-16 19:11:21.290857',_binary '','t110'),('5464fc27-1225-4105-a7aa-c3b0d1533fd4','2024-04-16 19:11:21.272831',NULL,NULL,NULL,'2024-04-16 19:11:21.272831',_binary '','t106'),('547dc3f4-5ed2-4df0-a775-4b23a8fb1f0a','2024-04-16 19:11:21.282396',NULL,NULL,NULL,'2024-04-16 19:11:21.282396',_binary '','t108'),('7865c7ab-6ab6-4d07-ab8e-1fd5d1aa8d90','2024-04-16 19:09:18.687020',NULL,NULL,NULL,'2024-04-16 19:09:18.687020',_binary '','t1001'),('8ba7d0b0-3333-439e-a3e1-5b042f234351','2024-04-16 19:11:21.277650',NULL,NULL,NULL,'2024-04-16 19:11:21.277650',_binary '','t107'),('9e1b17e6-7b1a-4a57-8421-a3ebbc2c2f45','2024-04-16 19:09:18.693337',NULL,NULL,NULL,'2024-04-16 19:09:18.693337',_binary '','t1002'),('a6ec2db1-cc7d-4ceb-a1e2-821be293ce8e','2024-04-16 19:09:18.727452',NULL,NULL,NULL,'2024-04-16 19:09:18.727452',_binary '','t10010'),('a819d5b9-bf09-4c11-ba38-9c99fe77dcaf','2024-04-16 19:09:18.723183',NULL,NULL,NULL,'2024-04-16 19:09:18.723183',_binary '','t1009'),('b1f0b93a-46af-4ab5-9533-c4069bf9a9a4','2024-04-16 19:11:21.260204',NULL,NULL,NULL,'2024-04-16 19:11:21.260204',_binary '','t103'),('b2d71780-e040-4a67-baac-320b41689eb1','2024-04-16 19:11:21.255376',NULL,NULL,NULL,'2024-04-16 19:11:21.255376',_binary '','t102'),('b686c830-a9a9-4e68-9515-a1d72068e880','2024-04-16 19:11:21.264387',NULL,NULL,NULL,'2024-04-16 19:11:21.264387',_binary '','t104'),('c2a1119b-1857-4b71-8882-f7cbf690ced4','2024-04-16 19:09:18.705441',NULL,NULL,NULL,'2024-04-16 19:09:18.705441',_binary '','t1005'),('cd5485ed-3df8-4210-9f68-102e570e8139','2024-04-16 19:12:59.943970',NULL,NULL,NULL,'2024-04-16 19:13:20.542027',_binary '\0','t100'),('d0e642b3-e2e5-444a-9a7b-8215192f5e10','2024-04-16 19:09:18.718319',NULL,NULL,NULL,'2024-04-16 19:09:18.718319',_binary '','t1008'),('eb6a4404-e573-4f79-b819-08153138ebcd','2024-04-16 19:09:18.697433',NULL,NULL,NULL,'2024-04-16 19:09:18.697433',_binary '','t1003'),('f11e7840-aca6-4ad7-b6f5-05397c3b547f','2024-04-16 19:09:18.713454',NULL,NULL,NULL,'2024-04-16 19:09:18.713454',_binary '','t1007'),('fe32c4e5-cae7-4413-af17-0d56e436f3d5','2024-04-16 19:09:18.701673',NULL,NULL,NULL,'2024-04-16 19:09:18.701673',_binary '','t1004');
/*!40000 ALTER TABLE `j_teacher_no` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `j_user`
--

DROP TABLE IF EXISTS `j_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `j_user` (
  `id` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `disable` bit(1) DEFAULT NULL,
  `note` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `sort` int DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `account` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `account_non_expired` bit(1) DEFAULT NULL,
  `account_non_locked` bit(1) DEFAULT NULL,
  `address` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `age` int DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `credentials_non_expired` bit(1) DEFAULT NULL,
  `enable` bit(1) DEFAULT NULL,
  `gender` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `last_login_time` datetime(6) DEFAULT NULL,
  `mail` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `identity_id` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r4i3w7dv9fxk8xph3n9p9cmqp` (`account`),
  KEY `fk_user_identity` (`identity_id`),
  CONSTRAINT `fk_user_identity` FOREIGN KEY (`identity_id`) REFERENCES `j_identity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `j_user`
--

LOCK TABLES `j_user` WRITE;
/*!40000 ALTER TABLE `j_user` DISABLE KEYS */;
INSERT INTO `j_user` VALUES ('035e671a-b691-4194-8854-8555701da5d1','2024-04-16 19:09:18.146474',NULL,NULL,NULL,'2024-04-16 19:09:18.149076',NULL,_binary '',_binary '',NULL,27,'1997-04-05',_binary '',_binary '','3',NULL,'11newJJ@gmail.com','機器人11',NULL,'0987100011','37484185-548e-42a9-8e0e-e8e459d46af7'),('0626d5da-7a06-4927-be5d-027aa50a6a9e','2024-04-16 19:09:18.588065',NULL,NULL,NULL,'2024-04-16 19:09:18.591064',NULL,_binary '',_binary '',NULL,23,'2000-09-14',_binary '',_binary '','3',NULL,'50newJJ@gmail.com','機器人50',NULL,'0987100050','37484185-548e-42a9-8e0e-e8e459d46af7'),('11c0d511-5aac-4324-b6f2-0d778f389e24','2024-04-16 19:09:18.492258',NULL,NULL,NULL,'2024-04-16 19:09:18.494354',NULL,_binary '',_binary '',NULL,26,'1998-02-09',_binary '',_binary '','1',NULL,'42newJJ@gmail.com','機器人42',NULL,'0987100042','37484185-548e-42a9-8e0e-e8e459d46af7'),('120ff851-0b08-4ac3-9619-f2e144ef1656','2024-04-16 19:09:18.427416',NULL,NULL,NULL,'2024-04-16 19:09:18.428932',NULL,_binary '',_binary '',NULL,25,'1999-03-10',_binary '',_binary '','1',NULL,'36newJJ@gmail.com','機器人36',NULL,'0987100036','37484185-548e-42a9-8e0e-e8e459d46af7'),('133269d8-5701-4691-82aa-fb0a1eed024b','2024-04-16 19:09:18.188712',NULL,NULL,NULL,'2024-04-16 19:09:18.191226',NULL,_binary '',_binary '',NULL,17,'2006-05-28',_binary '',_binary '','1',NULL,'15newJJ@gmail.com','機器人15',NULL,'0987100015','37484185-548e-42a9-8e0e-e8e459d46af7'),('1795c669-6697-4d7d-9ca7-beee94753201','2024-04-16 19:09:18.136991',NULL,NULL,NULL,'2024-04-16 19:09:18.139085',NULL,_binary '',_binary '',NULL,22,'2001-05-18',_binary '',_binary '','3',NULL,'10newJJ@gmail.com','機器人10',NULL,'0987100010','37484185-548e-42a9-8e0e-e8e459d46af7'),('1d098772-c38b-4c55-9dc3-2e757e7b7150','2024-04-16 19:09:18.517607',NULL,NULL,NULL,'2024-04-16 19:09:18.520010',NULL,_binary '',_binary '',NULL,16,'2007-07-25',_binary '',_binary '','2',NULL,'44newJJ@gmail.com','機器人44',NULL,'0987100044','37484185-548e-42a9-8e0e-e8e459d46af7'),('207db4b4-a265-4d5a-aa55-ddad3f8e8663','2024-04-16 19:09:18.436691',NULL,NULL,NULL,'2024-04-16 19:09:18.438788',NULL,_binary '',_binary '',NULL,17,'2006-04-27',_binary '',_binary '','3',NULL,'37newJJ@gmail.com','機器人37',NULL,'0987100037','37484185-548e-42a9-8e0e-e8e459d46af7'),('21761f9f-faf5-42bf-afe7-81594356d213','2024-04-16 19:09:18.505138',NULL,NULL,NULL,'2024-04-16 19:09:18.507792',NULL,_binary '',_binary '',NULL,26,'1997-12-09',_binary '',_binary '','3',NULL,'43newJJ@gmail.com','機器人43',NULL,'0987100043','37484185-548e-42a9-8e0e-e8e459d46af7'),('2f47ded9-e02b-49ff-b62d-ab6e6f6dffbf','2024-04-16 19:09:18.156197',NULL,NULL,NULL,'2024-04-16 19:09:18.158292',NULL,_binary '',_binary '',NULL,28,'1996-02-24',_binary '',_binary '','1',NULL,'12newJJ@gmail.com','機器人12',NULL,'0987100012','37484185-548e-42a9-8e0e-e8e459d46af7'),('337e7140-2dd8-4864-8d36-d73b9d40c1d0','2024-04-16 19:09:18.362830',NULL,NULL,NULL,'2024-04-16 19:09:18.365473',NULL,_binary '',_binary '',NULL,23,'2001-02-12',_binary '',_binary '','1',NULL,'30newJJ@gmail.com','機器人30',NULL,'0987100030','37484185-548e-42a9-8e0e-e8e459d46af7'),('34e14f92-3e67-4b59-a7ef-ada932e10b4a','2024-04-16 19:09:18.108496',NULL,NULL,NULL,'2024-04-16 19:09:18.110579',NULL,_binary '',_binary '',NULL,16,'2007-06-13',_binary '',_binary '','3',NULL,'7newJJ@gmail.com','機器人7',NULL,'0987100007','37484185-548e-42a9-8e0e-e8e459d46af7'),('378f0f77-928c-428a-a173-0b4b79f1621d','2024-04-16 19:09:18.200409',NULL,NULL,NULL,'2024-04-16 19:09:18.202598',NULL,_binary '',_binary '',NULL,27,'1997-02-16',_binary '',_binary '','2',NULL,'16newJJ@gmail.com','機器人16',NULL,'0987100016','37484185-548e-42a9-8e0e-e8e459d46af7'),('382eaa3c-817f-44d8-8505-358e36516a97','2024-04-16 19:13:20.466162',NULL,NULL,NULL,'2024-04-16 19:13:20.542027','teacher',_binary '',_binary '','睡在學校',30,'1994-01-01',_binary '',_binary '','1',NULL,'teacher@gmail.com','超級老師','$2a$10$QmOKs1BkugeJmDiOwRPyCugaBnaq0wdahxwO8.k9JIsw/s.FHd6OO','0988123456','1b2e55ca-804f-4d6c-b273-62b765cdfe93'),('3a13b21f-54b9-41c8-98fd-d568019fe75e','2024-04-16 19:09:18.117919',NULL,NULL,NULL,'2024-04-16 19:09:18.120142',NULL,_binary '',_binary '',NULL,25,'1998-10-16',_binary '',_binary '','2',NULL,'8newJJ@gmail.com','機器人8',NULL,'0987100008','37484185-548e-42a9-8e0e-e8e459d46af7'),('3ab87fc0-055f-4a5a-b717-7953e2141492','2024-04-16 19:09:18.283115',NULL,NULL,NULL,'2024-04-16 19:09:18.285723',NULL,_binary '',_binary '',NULL,20,'2003-06-05',_binary '',_binary '','3',NULL,'23newJJ@gmail.com','機器人23',NULL,'0987100023','37484185-548e-42a9-8e0e-e8e459d46af7'),('452b167f-ad79-4b75-bd7e-9b192b1fbd59','2024-04-16 19:09:18.211039',NULL,NULL,NULL,'2024-04-16 19:09:18.213558',NULL,_binary '',_binary '',NULL,27,'1996-07-03',_binary '',_binary '','3',NULL,'17newJJ@gmail.com','機器人17',NULL,'0987100017','37484185-548e-42a9-8e0e-e8e459d46af7'),('4ce8dcb2-2094-4d9d-b597-0779a5294a2d','2024-04-16 19:09:18.222806',NULL,NULL,NULL,'2024-04-16 19:09:18.225445',NULL,_binary '',_binary '',NULL,27,'1996-07-30',_binary '',_binary '','1',NULL,'18newJJ@gmail.com','機器人18',NULL,'0987100018','37484185-548e-42a9-8e0e-e8e459d46af7'),('5397e43c-b7f1-4fe3-99b1-ab4914eabecb','2024-04-16 19:09:18.126974',NULL,NULL,NULL,'2024-04-16 19:09:18.129585',NULL,_binary '',_binary '',NULL,24,'1999-07-04',_binary '',_binary '','1',NULL,'9newJJ@gmail.com','機器人9',NULL,'0987100009','37484185-548e-42a9-8e0e-e8e459d46af7'),('539d984b-ffb9-4fdf-956d-e188b70873a3','2024-04-16 19:09:18.318528',NULL,NULL,NULL,'2024-04-16 19:09:18.321298',NULL,_binary '',_binary '',NULL,17,'2006-06-07',_binary '',_binary '','3',NULL,'26newJJ@gmail.com','機器人26',NULL,'0987100026','37484185-548e-42a9-8e0e-e8e459d46af7'),('551de222-ec6e-41bd-a758-bb4b63dd93a5','2024-04-16 19:09:18.307338',NULL,NULL,NULL,'2024-04-16 19:09:18.310034',NULL,_binary '',_binary '',NULL,22,'2002-02-24',_binary '',_binary '','3',NULL,'25newJJ@gmail.com','機器人25',NULL,'0987100025','37484185-548e-42a9-8e0e-e8e459d46af7'),('58ce215e-1fc9-4bee-9621-bd2a91fc4505','2024-04-16 19:09:18.395036',NULL,NULL,NULL,'2024-04-16 19:09:18.397840',NULL,_binary '',_binary '',NULL,20,'2003-05-17',_binary '',_binary '','1',NULL,'33newJJ@gmail.com','機器人33',NULL,'0987100033','37484185-548e-42a9-8e0e-e8e459d46af7'),('6693c893-aa4e-40d9-ad1c-ff12c4126a53','2024-04-16 19:09:18.541068',NULL,NULL,NULL,'2024-04-16 19:09:18.544066',NULL,_binary '',_binary '',NULL,18,'2005-09-01',_binary '',_binary '','3',NULL,'46newJJ@gmail.com','機器人46',NULL,'0987100046','37484185-548e-42a9-8e0e-e8e459d46af7'),('68c0c44f-6df5-4616-bba2-88fb1ac04d81','2024-04-16 19:09:17.926007',NULL,NULL,NULL,'2024-04-16 19:09:17.936708','stu',_binary '',_binary '','睡在學校',4,'2020-04-01',_binary '',_binary '','1',NULL,'stu@gmail.com','超級學生','$2a$10$L3qjIlLt4bDB//DlP4v6OOtGjKFlV3mxldisuw7S.1H2SPIrThgH6','0988123456','37484185-548e-42a9-8e0e-e8e459d46af7'),('6bf66644-0485-4276-8a52-ccc789800b6b','2024-04-16 19:09:18.235445',NULL,NULL,NULL,'2024-04-16 19:09:18.238071',NULL,_binary '',_binary '',NULL,26,'1997-06-04',_binary '',_binary '','3',NULL,'19newJJ@gmail.com','機器人19',NULL,'0987100019','37484185-548e-42a9-8e0e-e8e459d46af7'),('6e0e4956-9dec-4e9c-a651-acf4c0ff29b1','2024-04-16 19:09:18.098353',NULL,NULL,NULL,'2024-04-16 19:09:18.100443',NULL,_binary '',_binary '',NULL,27,'1997-02-26',_binary '',_binary '','1',NULL,'6newJJ@gmail.com','機器人6',NULL,'0987100006','37484185-548e-42a9-8e0e-e8e459d46af7'),('6f41e306-0729-4934-bb08-a84930964ddc','2024-04-16 19:09:18.166868',NULL,NULL,NULL,'2024-04-16 19:09:18.168964',NULL,_binary '',_binary '',NULL,17,'2006-12-21',_binary '',_binary '','3',NULL,'13newJJ@gmail.com','機器人13',NULL,'0987100013','37484185-548e-42a9-8e0e-e8e459d46af7'),('723af303-79b8-4dc0-9f37-b03b5c9d2f09','2024-04-16 19:09:18.078265',NULL,NULL,NULL,'2024-04-16 19:09:18.080361',NULL,_binary '',_binary '',NULL,22,'2001-09-14',_binary '',_binary '','2',NULL,'4newJJ@gmail.com','機器人4',NULL,'0987100004','37484185-548e-42a9-8e0e-e8e459d46af7'),('7cd5fc7d-4544-4ce8-a9fe-531e131a600f','2024-04-16 19:09:18.270503',NULL,NULL,NULL,'2024-04-16 19:09:18.272515',NULL,_binary '',_binary '',NULL,22,'2001-12-13',_binary '',_binary '','3',NULL,'22newJJ@gmail.com','機器人22',NULL,'0987100022','37484185-548e-42a9-8e0e-e8e459d46af7'),('7ee1d8e1-2a0b-4a65-9628-b662ef3a221d','2024-04-16 19:09:18.295406',NULL,NULL,NULL,'2024-04-16 19:09:18.297505',NULL,_binary '',_binary '',NULL,29,'1994-11-07',_binary '',_binary '','1',NULL,'24newJJ@gmail.com','機器人24',NULL,'0987100024','37484185-548e-42a9-8e0e-e8e459d46af7'),('7fdc7a2c-8d3b-4675-9879-d1b831c29ca1','2024-04-16 19:09:18.329882',NULL,NULL,NULL,'2024-04-16 19:09:18.331983',NULL,_binary '',_binary '',NULL,20,'2003-07-24',_binary '',_binary '','1',NULL,'27newJJ@gmail.com','機器人27',NULL,'0987100027','37484185-548e-42a9-8e0e-e8e459d46af7'),('8a9581ba-0c35-43b6-b49f-bd334787f438','2024-04-16 19:09:18.480443',NULL,NULL,NULL,'2024-04-16 19:09:18.482550',NULL,_binary '',_binary '',NULL,16,'2007-04-24',_binary '',_binary '','3',NULL,'41newJJ@gmail.com','機器人41',NULL,'0987100041','37484185-548e-42a9-8e0e-e8e459d46af7'),('8bf6d8b9-b080-4262-9df6-5545d6ece3da','2024-04-16 19:09:18.068072',NULL,NULL,NULL,'2024-04-16 19:09:18.070182',NULL,_binary '',_binary '',NULL,20,'2003-06-28',_binary '',_binary '','1',NULL,'3newJJ@gmail.com','機器人3',NULL,'0987100003','37484185-548e-42a9-8e0e-e8e459d46af7'),('90d720e7-fa99-4bee-a10d-d2a7e9f21f93','2024-04-16 19:09:18.088252',NULL,NULL,NULL,'2024-04-16 19:09:18.090998',NULL,_binary '',_binary '',NULL,17,'2006-05-22',_binary '',_binary '','3',NULL,'5newJJ@gmail.com','機器人5',NULL,'0987100005','37484185-548e-42a9-8e0e-e8e459d46af7'),('94d76942-ee50-4294-a4a3-8949467df06b','2024-04-16 19:09:17.740609',NULL,NULL,NULL,'2024-04-16 19:15:40.868452','admin',_binary '',_binary '','無處不在',24,'2000-01-01',_binary '',_binary '','1','2024-04-16 19:15:40.862452','admin@gmail.com','超級管理員','$2a$10$qvqZapv78u9c6euqNIUgz.zL9vIP0uxxQJSP/UybD/EELtQNXDBWS','0988123456','48e31dd5-a439-4ea5-b229-d83463403e00'),('96a81b9a-bf89-4bc0-8720-2491a98678df','2024-04-16 19:09:18.565068',NULL,NULL,NULL,'2024-04-16 19:09:18.568066',NULL,_binary '',_binary '',NULL,19,'2004-06-26',_binary '',_binary '','1',NULL,'48newJJ@gmail.com','機器人48',NULL,'0987100048','37484185-548e-42a9-8e0e-e8e459d46af7'),('9b182e88-f576-4b3a-aef1-b66fdceb4fe9','2024-04-16 19:09:18.405237',NULL,NULL,NULL,'2024-04-16 19:09:18.406750',NULL,_binary '',_binary '',NULL,28,'1995-04-30',_binary '',_binary '','3',NULL,'34newJJ@gmail.com','機器人34',NULL,'0987100034','37484185-548e-42a9-8e0e-e8e459d46af7'),('a03a1f6e-886b-4302-89ab-dd5437b23c62','2024-04-16 19:09:18.576065',NULL,NULL,NULL,'2024-04-16 19:09:18.579064',NULL,_binary '',_binary '',NULL,29,'1995-03-08',_binary '',_binary '','3',NULL,'49newJJ@gmail.com','機器人49',NULL,'0987100049','37484185-548e-42a9-8e0e-e8e459d46af7'),('a14c8b12-5629-402d-b5cc-3ef967ceefed','2024-04-16 19:09:18.375066',NULL,NULL,NULL,'2024-04-16 19:09:18.377684',NULL,_binary '',_binary '',NULL,26,'1997-06-22',_binary '',_binary '','3',NULL,'31newJJ@gmail.com','機器人31',NULL,'0987100031','37484185-548e-42a9-8e0e-e8e459d46af7'),('a4760f55-870c-401c-a953-2b7c5c493607','2024-04-16 19:09:18.177462',NULL,NULL,NULL,'2024-04-16 19:09:18.180094',NULL,_binary '',_binary '',NULL,25,'1998-08-27',_binary '',_binary '','3',NULL,'14newJJ@gmail.com','機器人14',NULL,'0987100014','37484185-548e-42a9-8e0e-e8e459d46af7'),('a5d3dfd8-09d0-4d02-ab3c-eb9db62147db','2024-04-16 19:09:18.469142',NULL,NULL,NULL,'2024-04-16 19:09:18.471232',NULL,_binary '',_binary '',NULL,20,'2003-12-30',_binary '',_binary '','2',NULL,'40newJJ@gmail.com','機器人40',NULL,'0987100040','37484185-548e-42a9-8e0e-e8e459d46af7'),('a6408a3b-f6c3-46cd-a6f1-f60d4f9b3664','2024-04-16 19:09:18.351688',NULL,NULL,NULL,'2024-04-16 19:09:18.353780',NULL,_binary '',_binary '',NULL,20,'2003-10-12',_binary '',_binary '','3',NULL,'29newJJ@gmail.com','機器人29',NULL,'0987100029','37484185-548e-42a9-8e0e-e8e459d46af7'),('bc771a7e-8a96-4867-9db3-755d8590c1b6','2024-04-16 19:09:18.417300',NULL,NULL,NULL,'2024-04-16 19:09:18.419391',NULL,_binary '',_binary '',NULL,25,'1999-02-20',_binary '',_binary '','3',NULL,'35newJJ@gmail.com','機器人35',NULL,'0987100035','37484185-548e-42a9-8e0e-e8e459d46af7'),('bd3fd254-7f6b-4768-8b01-182c6c5be707','2024-04-16 19:09:18.259390',NULL,NULL,NULL,'2024-04-16 19:09:18.262012',NULL,_binary '',_binary '',NULL,23,'2000-12-06',_binary '',_binary '','1',NULL,'21newJJ@gmail.com','機器人21',NULL,'0987100021','37484185-548e-42a9-8e0e-e8e459d46af7'),('c60260f9-5231-4d17-b53a-ef83053e4c55','2024-04-16 19:09:18.446973',NULL,NULL,NULL,'2024-04-16 19:09:18.448491',NULL,_binary '',_binary '',NULL,29,'1994-06-29',_binary '',_binary '','3',NULL,'38newJJ@gmail.com','機器人38',NULL,'0987100038','37484185-548e-42a9-8e0e-e8e459d46af7'),('d6589ce7-d8c0-42ff-b09c-bf361ec28672','2024-04-16 19:09:18.385763',NULL,NULL,NULL,'2024-04-16 19:09:18.387772',NULL,_binary '',_binary '',NULL,18,'2005-05-15',_binary '',_binary '','2',NULL,'32newJJ@gmail.com','機器人32',NULL,'0987100032','37484185-548e-42a9-8e0e-e8e459d46af7'),('d747e9f9-171a-4609-a735-e920cd7af6c8','2024-04-16 19:09:18.049226',NULL,NULL,NULL,'2024-04-16 19:09:18.051306',NULL,_binary '',_binary '',NULL,23,'2000-07-12',_binary '',_binary '','3',NULL,'1newJJ@gmail.com','機器人1',NULL,'0987100001','37484185-548e-42a9-8e0e-e8e459d46af7'),('dc8faa57-639e-4282-a0f0-13c688e33bec','2024-04-16 19:09:18.340517',NULL,NULL,NULL,'2024-04-16 19:09:18.343145',NULL,_binary '',_binary '',NULL,16,'2007-12-07',_binary '',_binary '','2',NULL,'28newJJ@gmail.com','機器人28',NULL,'0987100028','37484185-548e-42a9-8e0e-e8e459d46af7'),('e2841976-6674-4241-87c9-0d0d461768f1','2024-04-16 19:09:18.530037',NULL,NULL,NULL,'2024-04-16 19:09:18.532697',NULL,_binary '',_binary '',NULL,19,'2004-05-21',_binary '',_binary '','1',NULL,'45newJJ@gmail.com','機器人45',NULL,'0987100045','37484185-548e-42a9-8e0e-e8e459d46af7'),('f34434a0-b7d6-4913-8946-0c5923041779','2024-04-16 19:09:18.247772',NULL,NULL,NULL,'2024-04-16 19:09:18.249883',NULL,_binary '',_binary '',NULL,18,'2005-11-19',_binary '',_binary '','2',NULL,'20newJJ@gmail.com','機器人20',NULL,'0987100020','37484185-548e-42a9-8e0e-e8e459d46af7'),('f530955a-db3b-432f-ae42-b0d44e62dad4','2024-04-16 19:09:18.058628',NULL,NULL,NULL,'2024-04-16 19:09:18.060712',NULL,_binary '',_binary '',NULL,25,'1998-11-20',_binary '',_binary '','3',NULL,'2newJJ@gmail.com','機器人2',NULL,'0987100002','37484185-548e-42a9-8e0e-e8e459d46af7'),('fdfabdb7-e346-4ce3-a037-fe16c3d5cfce','2024-04-16 19:09:18.554065',NULL,NULL,NULL,'2024-04-16 19:09:18.556067',NULL,_binary '',_binary '',NULL,25,'1999-01-03',_binary '',_binary '','3',NULL,'47newJJ@gmail.com','機器人47',NULL,'0987100047','37484185-548e-42a9-8e0e-e8e459d46af7'),('fe7a01eb-f9d2-4c59-8645-f08cdc277543','2024-04-16 19:09:18.457469',NULL,NULL,NULL,'2024-04-16 19:09:18.460230',NULL,_binary '',_binary '',NULL,27,'1997-03-20',_binary '',_binary '','1',NULL,'39newJJ@gmail.com','機器人39',NULL,'0987100039','37484185-548e-42a9-8e0e-e8e459d46af7');
/*!40000 ALTER TABLE `j_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu_role`
--

DROP TABLE IF EXISTS `menu_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu_role` (
  `menu_id` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `role_id` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  UNIQUE KEY `uk_menu_role` (`menu_id`,`role_id`),
  KEY `fk_role_menu` (`role_id`),
  CONSTRAINT `fk_menu_role` FOREIGN KEY (`menu_id`) REFERENCES `j_menu` (`id`),
  CONSTRAINT `fk_role_menu` FOREIGN KEY (`role_id`) REFERENCES `j_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_role`
--

LOCK TABLES `menu_role` WRITE;
/*!40000 ALTER TABLE `menu_role` DISABLE KEYS */;
INSERT INTO `menu_role` VALUES ('44596c1c-8d67-4514-a206-7fca5733fd99','0ae8c2a2-d941-4c17-aee8-fc86d4a544cf'),('44596c1c-8d67-4514-a206-7fca5733fd99','1f65722a-74b0-4ebf-be94-d5ac87d35f52'),('44596c1c-8d67-4514-a206-7fca5733fd99','29369945-a9fc-4757-b479-489e68f81ff7'),('bb7bde16-26fe-4dfa-b38a-59d9b6a9f688','29369945-a9fc-4757-b479-489e68f81ff7'),('0c07d5bd-2f9e-4385-9c5e-d4de43a7fa1b','7a1deb94-ae3b-4067-a207-260c17344e87'),('44596c1c-8d67-4514-a206-7fca5733fd99','7a1deb94-ae3b-4067-a207-260c17344e87'),('7030c90e-0a9e-4d15-a514-e36624ff16c5','7a1deb94-ae3b-4067-a207-260c17344e87'),('bb7bde16-26fe-4dfa-b38a-59d9b6a9f688','7a1deb94-ae3b-4067-a207-260c17344e87'),('d28f8295-a4db-4ee8-b30c-439339bf80b7','7a1deb94-ae3b-4067-a207-260c17344e87'),('44596c1c-8d67-4514-a206-7fca5733fd99','ff801370-69fa-4f3a-ada0-6580c57f42ba'),('7030c90e-0a9e-4d15-a514-e36624ff16c5','ff801370-69fa-4f3a-ada0-6580c57f42ba'),('bb7bde16-26fe-4dfa-b38a-59d9b6a9f688','ff801370-69fa-4f3a-ada0-6580c57f42ba');
/*!40000 ALTER TABLE `menu_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `role_id` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  UNIQUE KEY `uk_user_role` (`user_id`,`role_id`),
  KEY `fk_role_user` (`role_id`),
  CONSTRAINT `fk_role_user` FOREIGN KEY (`role_id`) REFERENCES `j_role` (`id`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`user_id`) REFERENCES `j_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES ('035e671a-b691-4194-8854-8555701da5d1','29369945-a9fc-4757-b479-489e68f81ff7'),('0626d5da-7a06-4927-be5d-027aa50a6a9e','29369945-a9fc-4757-b479-489e68f81ff7'),('11c0d511-5aac-4324-b6f2-0d778f389e24','29369945-a9fc-4757-b479-489e68f81ff7'),('120ff851-0b08-4ac3-9619-f2e144ef1656','29369945-a9fc-4757-b479-489e68f81ff7'),('133269d8-5701-4691-82aa-fb0a1eed024b','29369945-a9fc-4757-b479-489e68f81ff7'),('1795c669-6697-4d7d-9ca7-beee94753201','29369945-a9fc-4757-b479-489e68f81ff7'),('1d098772-c38b-4c55-9dc3-2e757e7b7150','29369945-a9fc-4757-b479-489e68f81ff7'),('207db4b4-a265-4d5a-aa55-ddad3f8e8663','29369945-a9fc-4757-b479-489e68f81ff7'),('21761f9f-faf5-42bf-afe7-81594356d213','29369945-a9fc-4757-b479-489e68f81ff7'),('2f47ded9-e02b-49ff-b62d-ab6e6f6dffbf','29369945-a9fc-4757-b479-489e68f81ff7'),('337e7140-2dd8-4864-8d36-d73b9d40c1d0','29369945-a9fc-4757-b479-489e68f81ff7'),('34e14f92-3e67-4b59-a7ef-ada932e10b4a','29369945-a9fc-4757-b479-489e68f81ff7'),('378f0f77-928c-428a-a173-0b4b79f1621d','29369945-a9fc-4757-b479-489e68f81ff7'),('3a13b21f-54b9-41c8-98fd-d568019fe75e','29369945-a9fc-4757-b479-489e68f81ff7'),('3ab87fc0-055f-4a5a-b717-7953e2141492','29369945-a9fc-4757-b479-489e68f81ff7'),('452b167f-ad79-4b75-bd7e-9b192b1fbd59','29369945-a9fc-4757-b479-489e68f81ff7'),('4ce8dcb2-2094-4d9d-b597-0779a5294a2d','29369945-a9fc-4757-b479-489e68f81ff7'),('5397e43c-b7f1-4fe3-99b1-ab4914eabecb','29369945-a9fc-4757-b479-489e68f81ff7'),('539d984b-ffb9-4fdf-956d-e188b70873a3','29369945-a9fc-4757-b479-489e68f81ff7'),('551de222-ec6e-41bd-a758-bb4b63dd93a5','29369945-a9fc-4757-b479-489e68f81ff7'),('58ce215e-1fc9-4bee-9621-bd2a91fc4505','29369945-a9fc-4757-b479-489e68f81ff7'),('6693c893-aa4e-40d9-ad1c-ff12c4126a53','29369945-a9fc-4757-b479-489e68f81ff7'),('68c0c44f-6df5-4616-bba2-88fb1ac04d81','29369945-a9fc-4757-b479-489e68f81ff7'),('6bf66644-0485-4276-8a52-ccc789800b6b','29369945-a9fc-4757-b479-489e68f81ff7'),('6e0e4956-9dec-4e9c-a651-acf4c0ff29b1','29369945-a9fc-4757-b479-489e68f81ff7'),('6f41e306-0729-4934-bb08-a84930964ddc','29369945-a9fc-4757-b479-489e68f81ff7'),('723af303-79b8-4dc0-9f37-b03b5c9d2f09','29369945-a9fc-4757-b479-489e68f81ff7'),('7cd5fc7d-4544-4ce8-a9fe-531e131a600f','29369945-a9fc-4757-b479-489e68f81ff7'),('7ee1d8e1-2a0b-4a65-9628-b662ef3a221d','29369945-a9fc-4757-b479-489e68f81ff7'),('7fdc7a2c-8d3b-4675-9879-d1b831c29ca1','29369945-a9fc-4757-b479-489e68f81ff7'),('8a9581ba-0c35-43b6-b49f-bd334787f438','29369945-a9fc-4757-b479-489e68f81ff7'),('8bf6d8b9-b080-4262-9df6-5545d6ece3da','29369945-a9fc-4757-b479-489e68f81ff7'),('90d720e7-fa99-4bee-a10d-d2a7e9f21f93','29369945-a9fc-4757-b479-489e68f81ff7'),('94d76942-ee50-4294-a4a3-8949467df06b','29369945-a9fc-4757-b479-489e68f81ff7'),('96a81b9a-bf89-4bc0-8720-2491a98678df','29369945-a9fc-4757-b479-489e68f81ff7'),('9b182e88-f576-4b3a-aef1-b66fdceb4fe9','29369945-a9fc-4757-b479-489e68f81ff7'),('a03a1f6e-886b-4302-89ab-dd5437b23c62','29369945-a9fc-4757-b479-489e68f81ff7'),('a14c8b12-5629-402d-b5cc-3ef967ceefed','29369945-a9fc-4757-b479-489e68f81ff7'),('a4760f55-870c-401c-a953-2b7c5c493607','29369945-a9fc-4757-b479-489e68f81ff7'),('a5d3dfd8-09d0-4d02-ab3c-eb9db62147db','29369945-a9fc-4757-b479-489e68f81ff7'),('a6408a3b-f6c3-46cd-a6f1-f60d4f9b3664','29369945-a9fc-4757-b479-489e68f81ff7'),('bc771a7e-8a96-4867-9db3-755d8590c1b6','29369945-a9fc-4757-b479-489e68f81ff7'),('bd3fd254-7f6b-4768-8b01-182c6c5be707','29369945-a9fc-4757-b479-489e68f81ff7'),('c60260f9-5231-4d17-b53a-ef83053e4c55','29369945-a9fc-4757-b479-489e68f81ff7'),('d6589ce7-d8c0-42ff-b09c-bf361ec28672','29369945-a9fc-4757-b479-489e68f81ff7'),('d747e9f9-171a-4609-a735-e920cd7af6c8','29369945-a9fc-4757-b479-489e68f81ff7'),('dc8faa57-639e-4282-a0f0-13c688e33bec','29369945-a9fc-4757-b479-489e68f81ff7'),('e2841976-6674-4241-87c9-0d0d461768f1','29369945-a9fc-4757-b479-489e68f81ff7'),('f34434a0-b7d6-4913-8946-0c5923041779','29369945-a9fc-4757-b479-489e68f81ff7'),('f530955a-db3b-432f-ae42-b0d44e62dad4','29369945-a9fc-4757-b479-489e68f81ff7'),('fdfabdb7-e346-4ce3-a037-fe16c3d5cfce','29369945-a9fc-4757-b479-489e68f81ff7'),('fe7a01eb-f9d2-4c59-8645-f08cdc277543','29369945-a9fc-4757-b479-489e68f81ff7'),('94d76942-ee50-4294-a4a3-8949467df06b','7a1deb94-ae3b-4067-a207-260c17344e87'),('382eaa3c-817f-44d8-8505-358e36516a97','ff801370-69fa-4f3a-ada0-6580c57f42ba');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-16 21:21:52
