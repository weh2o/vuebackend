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
INSERT INTO `course_student` VALUES ('27cfa3b2-78f6-4297-9a26-89772c55235b','1a2494ff-194a-4f7d-9219-a5aaf3f1ef0f'),('27cfa3b2-78f6-4297-9a26-89772c55235b','5b905845-7a5b-467a-880f-e1407d689f74');
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
INSERT INTO `j_course` VALUES ('27cfa3b2-78f6-4297-9a26-89772c55235b','2024-04-12 12:32:07.975291',NULL,NULL,NULL,'2024-04-12 12:32:08.006457',NULL,'2030-01-01','2030-01-01','15:00:00.000000',30,'如何不被老婆發現私房錢','2024-04-12','14:00:00.000000','e360f8f5-0a58-4871-8dde-2615ce47b6a6','06fa27e9-602f-4dd3-93b6-dad862963d0d'),('39caceb2-0d86-43d0-bdbd-0a6c95d4aec2','2024-04-12 15:34:31.975710',NULL,NULL,NULL,'2024-04-12 15:34:32.088780',NULL,'1994-01-01','2030-01-01','15:00:00.000000',30,'過期課程','2024-04-12','14:00:00.000000','e360f8f5-0a58-4871-8dde-2615ce47b6a6','06fa27e9-602f-4dd3-93b6-dad862963d0d'),('6254fb72-d80a-4310-9a57-bcbf98fffdcf',NULL,NULL,NULL,NULL,'2024-04-15 09:19:00.915669',NULL,'2024-04-17','2024-04-23','09:00:00.000000',9,'吃很多不胖的訣竅','2024-04-15','08:30:00.000000','00775303-fb30-459f-8dba-3ab0b16d5821','e2476e38-652a-4593-a319-4e75fd7a1872');
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
INSERT INTO `j_course_location` VALUES ('00775303-fb30-459f-8dba-3ab0b16d5821','2024-04-12 12:32:07.885760',NULL,NULL,NULL,'2024-04-12 12:32:07.885760',NULL,'2','PLAYGROUND','操場'),('5e90c3e2-f410-4ef5-830f-a8d44330f643','2024-04-12 12:32:07.893129',NULL,NULL,NULL,'2024-04-12 12:32:07.893129',NULL,'4','CLASSROOM_101','101教室'),('e360f8f5-0a58-4871-8dde-2615ce47b6a6','2024-04-12 12:32:07.882099',NULL,NULL,NULL,'2024-04-12 12:32:07.882099',NULL,'1','LIBRARY','圖書館'),('e4bb6c29-fcc3-47eb-95ca-70b74afebf1a','2024-04-12 12:32:07.875819',NULL,NULL,NULL,'2024-04-12 12:32:07.875819',NULL,'0','OTHER','其他'),('f945b843-9e62-40b1-9452-6a4df31d759d','2024-04-12 12:32:07.889504',NULL,NULL,NULL,'2024-04-12 12:32:07.889504',NULL,'3','GYMNASIUM','體育館');
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
INSERT INTO `j_identity` VALUES ('70638573-73fc-45b1-a5e7-a3b3b24e166d','2024-04-12 12:32:06.184447',NULL,NULL,NULL,'2024-04-12 12:32:06.184447','2','TEACHER','老師'),('b4df6fbd-f76f-4226-a74a-b36997d8aeff','2024-04-12 12:32:06.189725',NULL,NULL,NULL,'2024-04-12 12:32:06.189725','3','STUDENT','學生'),('c10b4846-edb2-41a8-b06d-f01ee0f0e3ab','2024-04-12 12:32:06.195023',NULL,NULL,NULL,'2024-04-12 12:32:06.195023','99','VISITOR','遊客'),('caaef9d0-d44b-4578-9ce2-b1f5b6d58118','2024-04-12 12:32:06.147585',NULL,NULL,NULL,'2024-04-12 12:32:06.147585','1','ADMIN','系統管理者');
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
INSERT INTO `j_menu` VALUES ('0aa10cae-2028-4b9d-ad3b-82de52ee4abd','2024-04-12 12:32:06.515303',NULL,NULL,1,'2024-04-12 12:32:06.522158','User','學生管理',NULL,'/studentManagement',NULL,NULL),('32d7aec7-cae4-434f-9fd4-ed6315515bf6','2024-04-12 12:32:06.549401',NULL,NULL,3,'2024-04-12 12:32:06.556004','Tickets','公告管理',NULL,'/announcement',NULL,NULL),('7c926f63-6dc7-40de-9778-c48c799f0904','2024-04-12 12:32:06.562903',NULL,NULL,1,'2024-04-12 12:32:06.569233','User','教師管理',NULL,'/teacherManagement',NULL,NULL),('c544ea32-1058-4de9-8b98-a0567751e09c','2024-04-12 12:32:06.530928',NULL,NULL,2,'2024-04-12 12:32:06.537702','Memo','課程管理',NULL,'/course',NULL,NULL),('d817f754-7371-4835-a217-b4bf271788b9','2024-04-12 12:32:06.436784',NULL,NULL,0,'2024-04-12 12:32:06.487866','House','首頁',NULL,'/home',NULL,NULL);
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
INSERT INTO `j_role` VALUES ('1817a9e1-c51e-4503-8581-aa567a03f7f2','2024-04-12 12:32:06.336425',NULL,NULL,NULL,'2024-04-12 12:32:06.336425','DIRECTOR','主任'),('38c1875b-9cf6-46d5-8b88-b4c7bd4890cd','2024-04-12 12:32:06.325627',NULL,NULL,NULL,'2024-04-12 12:32:06.325627','TEACHER','老師'),('70e6dd12-532f-4235-810b-1e1daeb28b59','2024-04-12 12:32:06.330903',NULL,NULL,NULL,'2024-04-12 12:32:06.330903','STUDENT','學生'),('c5af190f-4f63-475c-8d8f-0600a04f76bf','2024-04-12 12:32:06.318180',NULL,NULL,NULL,'2024-04-12 12:32:06.318180','ADMIN','系統管理者'),('fee317e5-45c3-48db-9d44-ab1ba2d99e83','2024-04-12 12:32:06.341263',NULL,NULL,NULL,'2024-04-12 12:32:06.341263','VISITOR','遊客');
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
INSERT INTO `j_student` VALUES (NULL,'1a2494ff-194a-4f7d-9219-a5aaf3f1ef0f'),('10001','80600e2c-9307-4ba3-99a6-e88e84549ff0'),('10002','22d81c85-2114-409a-bce9-32a7e5776c0b'),('10003','10884f8e-88ce-48a5-a9f7-9c470fb57572'),('10004','cce331a3-55e1-4e70-a47b-58c6b0468d8e'),('10005','fed82480-b20f-45cc-b337-802bb8b9ff38'),('10006','0e2768bd-6d0a-4788-8394-0e005d0d8e3b'),('10007','9b06a42f-62e0-4e48-b800-a00ccce72479'),('10008','5704d25e-22f9-45b0-89a2-3603f2ad2e19'),('10009','bf855a97-15b1-4881-b5d7-23752e07557d'),('10010','4c78df2b-127e-47fb-9f4b-e7b0e94f1645'),('10011','7f5699b9-c9e5-475c-9e78-fab0ba9784d2'),('10012','4ef88edf-e2e7-48cc-91f3-fc265350e114'),('10013','4dcce67a-b661-4b28-b027-f80cd32a924f'),('10014','dffc6fca-796d-4401-be90-6003a2abeb72'),('10015','83f33672-5d20-4191-ae7e-3f0d15e8fb95'),('10016','35275f9d-322f-4383-9fcf-bc295485da0f'),('10017','e5e2beba-2f9f-4420-a75c-baefd96e0de1'),('10018','144490da-f48f-4a11-856d-7f64b88883a3'),('10019','785276ec-0421-401b-9c55-e76c5447072d'),('10020','58fd7fba-19bc-4cbe-8b9d-dc34491e29e1'),('10021','411c0be3-ff8a-4af8-b49e-d69f7d9a8b38'),('10022','ab3436aa-7f20-427d-b8d6-1ac5e85f86d7'),('10023','a17b1ba8-d4d5-483d-bfd9-78de0e78bdbe'),('10024','4d9863e3-138c-4efc-8cd3-3a9ac2368271'),('10025','692b7a4d-53a7-4b50-a668-71458dc486a0'),('10026','38f88d78-e99a-4a78-9d36-3d105bec0e8d'),('10027','3bfe1597-864b-4ff8-9a85-6204fbec501b'),('10028','2c57db92-50a6-4d31-848d-3be6efc4e82c'),('10029','6dadc71f-2098-4eb1-bde9-0c87840e26e5'),('10030','37de06a0-e352-4503-9b59-a53d96c81389'),('10031','d00cecee-06cd-4898-a6b2-ce137103ef62'),('10032','93ac1bb7-a0b8-4078-b2bc-ca70c7aee38d'),('10033','987f212c-7dbc-4a33-9ed0-3afdd2940d2f'),('10034','a135ab59-ec74-4af5-aa8e-749a34e15e44'),('10035','0eb66da9-1d6e-4e03-a30c-2e09595b6346'),('10036','c55ef75e-6c47-4915-8347-d0b206119d4f'),('10037','de0f5e0d-2c42-4cae-85bf-139a1aa2e474'),('10038','9374bd45-394f-4064-9d90-7ae98307f2a1'),('10039','572b2634-e73c-4021-8fdc-2f688fba3494'),('10040','3eb8cb01-43b7-4bac-81ba-dcc25f7140f7'),('10041','7d532222-424a-4e6c-a45a-67d375d5b1c0'),('10042','60aed5f4-bd61-4fe1-ab1e-7780b139d468'),('10043','48fde1ec-708c-475f-8d42-425bb85c52ed'),('10044','e048b984-4dd9-4668-afeb-237b2db8ebd7'),('10045','4eb733a6-72c6-4d59-993a-1de5895c0e35'),('10046','3070939f-b202-440b-b06c-221b93db5d61'),('10047','750b3e5c-fe26-4823-8cc8-9a07b19bc86e'),('10048','eb01b7c8-48c8-45e6-a24a-503acdb0b0af'),('10049','3a9c896e-d50c-4d9e-b065-f1395311f7b6'),('10050','52328569-7137-4d0f-a5cd-6d06957bce34');
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
INSERT INTO `j_teacher` VALUES ('e2476e38-652a-4593-a319-4e75fd7a1872','20bfb2ba-3e4b-431b-a64b-05e6967d15c1'),('06fa27e9-602f-4dd3-93b6-dad862963d0d','c904b45a-291e-4c20-8a07-29409a4c2e1c');
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
INSERT INTO `j_teacher_no` VALUES ('165f5bc8-e0dd-43c3-a78e-8dd44b1195db','2024-04-12 12:32:07.615932',NULL,NULL,NULL,'2024-04-12 12:32:07.615932',_binary '','t1005'),('20bfb2ba-3e4b-431b-a64b-05e6967d15c1','2024-04-12 12:32:07.603659',NULL,NULL,NULL,'2024-04-12 14:10:42.097617',_binary '\0','t1002'),('48c8271d-b665-4d9b-a30b-69c19c6df21e','2024-04-12 12:32:07.612215',NULL,NULL,NULL,'2024-04-12 12:32:07.612215',_binary '','t1004'),('6a5fa38f-f16c-488c-8d10-825361c31b04','2024-04-12 12:32:07.623359',NULL,NULL,NULL,'2024-04-12 12:32:07.623359',_binary '','t1007'),('7a787896-377f-4689-a562-d3494f28a0ee','2024-04-12 12:32:07.607905',NULL,NULL,NULL,'2024-04-12 12:32:07.607905',_binary '','t1003'),('8826defa-c80d-4661-9c8b-12465e399ed4','2024-04-12 12:32:07.633776',NULL,NULL,NULL,'2024-04-12 12:32:07.633776',_binary '','t10010'),('98ae45c1-5ae7-4cc4-8446-bcd8d08cb72a','2024-04-12 12:32:07.630085',NULL,NULL,NULL,'2024-04-12 12:32:07.630085',_binary '','t1009'),('b25aa513-a78a-4b66-940a-6103fc25b818','2024-04-12 12:32:07.626576',NULL,NULL,NULL,'2024-04-12 12:32:07.626576',_binary '','t1008'),('c904b45a-291e-4c20-8a07-29409a4c2e1c','2024-04-12 12:32:07.596809',NULL,NULL,NULL,'2024-04-12 12:32:07.773283',_binary '\0','t1001'),('d0fac6e9-1f44-4f76-8b19-dcc4ad02206e','2024-04-12 12:32:07.619658',NULL,NULL,NULL,'2024-04-12 12:32:07.619658',_binary '','t1006');
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
  `address` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `age` int DEFAULT NULL,
  `birth` date DEFAULT NULL,
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
INSERT INTO `j_user` VALUES ('06fa27e9-602f-4dd3-93b6-dad862963d0d','2024-04-12 12:32:07.745269',NULL,NULL,NULL,'2024-04-12 14:31:11.164168','teacher','睡在學校',30,'1994-01-01','1','2024-04-12 14:31:11.151584','teacher@gmail.com','超級老師','b59c67bf196a4758191e42f76670ceba','0988123456','70638573-73fc-45b1-a5e7-a3b3b24e166d'),('0e2768bd-6d0a-4788-8394-0e005d0d8e3b','2024-04-12 12:32:07.048810',NULL,NULL,NULL,'2024-04-12 12:32:07.050898',NULL,NULL,22,'2001-11-29','1',NULL,'6newJJ@gmail.com','機器人6',NULL,'0987100006','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('0eb66da9-1d6e-4e03-a30c-2e09595b6346','2024-04-12 12:32:07.334960',NULL,NULL,NULL,'2024-04-12 12:32:07.337077',NULL,NULL,22,'2002-03-13','3',NULL,'35newJJ@gmail.com','機器人35',NULL,'0987100035','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('10884f8e-88ce-48a5-a9f7-9c470fb57572','2024-04-12 12:32:07.020967',NULL,NULL,NULL,'2024-04-12 12:32:07.022530',NULL,NULL,20,'2004-04-04','1',NULL,'3newJJ@gmail.com','機器人3',NULL,'0987100003','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('144490da-f48f-4a11-856d-7f64b88883a3','2024-04-12 12:32:07.157654',NULL,NULL,NULL,'2024-04-12 12:32:07.160251',NULL,NULL,24,'1999-04-22','1',NULL,'18newJJ@gmail.com','機器人18',NULL,'0987100018','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('1a2494ff-194a-4f7d-9219-a5aaf3f1ef0f','2024-04-12 12:32:06.878227',NULL,NULL,NULL,'2024-04-12 12:45:20.407814','stu','睡在學校',4,'2020-04-01','1','2024-04-12 12:45:20.391584','stu@gmail.com','超級學生','b59c67bf196a4758191e42f76670ceba','0988123456','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('22d81c85-2114-409a-bce9-32a7e5776c0b','2024-04-12 12:32:07.012538',NULL,NULL,NULL,'2024-04-12 12:32:07.014639',NULL,NULL,29,'1994-11-07','3',NULL,'2newJJ@gmail.com','機器人2',NULL,'0987100002','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('2c57db92-50a6-4d31-848d-3be6efc4e82c','2024-04-12 12:32:07.262024',NULL,NULL,NULL,'2024-04-12 12:32:07.264148',NULL,NULL,17,'2007-02-12','2',NULL,'28newJJ@gmail.com','機器人28',NULL,'0987100028','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('3070939f-b202-440b-b06c-221b93db5d61','2024-04-12 12:32:07.451805',NULL,NULL,NULL,'2024-04-12 12:32:07.454093',NULL,NULL,20,'2003-09-12','3',NULL,'46newJJ@gmail.com','機器人46',NULL,'0987100046','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('35275f9d-322f-4383-9fcf-bc295485da0f','2024-04-12 12:32:07.139575',NULL,NULL,NULL,'2024-04-12 12:32:07.141670',NULL,NULL,28,'1995-06-17','2',NULL,'16newJJ@gmail.com','機器人16',NULL,'0987100016','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('37de06a0-e352-4503-9b59-a53d96c81389','2024-04-12 12:32:07.283290',NULL,NULL,NULL,'2024-04-12 12:32:07.285511',NULL,NULL,20,'2004-03-10','1',NULL,'30newJJ@gmail.com','機器人30',NULL,'0987100030','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('38f88d78-e99a-4a78-9d36-3d105bec0e8d','2024-04-12 12:32:07.240719',NULL,NULL,NULL,'2024-04-12 12:32:07.242852',NULL,NULL,22,'2002-02-20','3',NULL,'26newJJ@gmail.com','機器人26',NULL,'0987100026','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('3a9c896e-d50c-4d9e-b065-f1395311f7b6','2024-04-12 12:32:07.489617',NULL,NULL,NULL,'2024-04-12 12:32:07.492341',NULL,NULL,27,'1997-02-16','3',NULL,'49newJJ@gmail.com','機器人49',NULL,'0987100049','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('3bfe1597-864b-4ff8-9a85-6204fbec501b','2024-04-12 12:32:07.251792',NULL,NULL,NULL,'2024-04-12 12:32:07.254421',NULL,NULL,16,'2007-10-02','1',NULL,'27newJJ@gmail.com','機器人27',NULL,'0987100027','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('3eb8cb01-43b7-4bac-81ba-dcc25f7140f7','2024-04-12 12:32:07.382546',NULL,NULL,NULL,'2024-04-12 12:32:07.384559',NULL,NULL,27,'1996-07-23','2',NULL,'40newJJ@gmail.com','機器人40',NULL,'0987100040','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('411c0be3-ff8a-4af8-b49e-d69f7d9a8b38','2024-04-12 12:32:07.186364',NULL,NULL,NULL,'2024-04-12 12:32:07.188398',NULL,NULL,16,'2007-09-06','1',NULL,'21newJJ@gmail.com','機器人21',NULL,'0987100021','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('48fde1ec-708c-475f-8d42-425bb85c52ed','2024-04-12 12:32:07.414722',NULL,NULL,NULL,'2024-04-12 12:32:07.417347',NULL,NULL,28,'1995-06-19','3',NULL,'43newJJ@gmail.com','機器人43',NULL,'0987100043','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('4c78df2b-127e-47fb-9f4b-e7b0e94f1645','2024-04-12 12:32:07.085409',NULL,NULL,NULL,'2024-04-12 12:32:07.087506',NULL,NULL,22,'2002-04-02','3',NULL,'10newJJ@gmail.com','機器人10',NULL,'0987100010','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('4d9863e3-138c-4efc-8cd3-3a9ac2368271','2024-04-12 12:32:07.218224',NULL,NULL,NULL,'2024-04-12 12:32:07.220330',NULL,NULL,28,'1995-10-01','1',NULL,'24newJJ@gmail.com','機器人24',NULL,'0987100024','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('4dcce67a-b661-4b28-b027-f80cd32a924f','2024-04-12 12:32:07.113129',NULL,NULL,NULL,'2024-04-12 12:32:07.114689',NULL,NULL,17,'2006-08-12','3',NULL,'13newJJ@gmail.com','機器人13',NULL,'0987100013','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('4eb733a6-72c6-4d59-993a-1de5895c0e35','2024-04-12 12:32:07.439141',NULL,NULL,NULL,'2024-04-12 12:32:07.441795',NULL,NULL,21,'2002-09-07','1',NULL,'45newJJ@gmail.com','機器人45',NULL,'0987100045','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('4ef88edf-e2e7-48cc-91f3-fc265350e114','2024-04-12 12:32:07.104161',NULL,NULL,NULL,'2024-04-12 12:32:07.106768',NULL,NULL,21,'2002-10-20','1',NULL,'12newJJ@gmail.com','機器人12',NULL,'0987100012','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('52328569-7137-4d0f-a5cd-6d06957bce34','2024-04-12 12:32:07.501987',NULL,NULL,NULL,'2024-04-12 12:32:07.505170',NULL,NULL,22,'2001-06-05','3',NULL,'50newJJ@gmail.com','機器人50',NULL,'0987100050','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('5704d25e-22f9-45b0-89a2-3603f2ad2e19','2024-04-12 12:32:07.067213',NULL,NULL,NULL,'2024-04-12 12:32:07.069336',NULL,NULL,22,'2001-11-29','2',NULL,'8newJJ@gmail.com','機器人8',NULL,'0987100008','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('572b2634-e73c-4021-8fdc-2f688fba3494','2024-04-12 12:32:07.371835',NULL,NULL,NULL,'2024-04-12 12:32:07.373948',NULL,NULL,28,'1996-02-10','1',NULL,'39newJJ@gmail.com','機器人39',NULL,'0987100039','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('58fd7fba-19bc-4cbe-8b9d-dc34491e29e1','2024-04-12 12:32:07.176728',NULL,NULL,NULL,'2024-04-12 12:32:07.179463',NULL,NULL,25,'1998-10-28','2',NULL,'20newJJ@gmail.com','機器人20',NULL,'0987100020','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('5b905845-7a5b-467a-880f-e1407d689f74','2024-04-12 12:32:06.704399',NULL,NULL,NULL,'2024-04-15 09:18:07.481044','admin','無處不在',24,'2000-01-01','1','2024-04-15 09:18:07.457043','admin@gmail.com','超級管理員666','b59c67bf196a4758191e42f76670ceba','0988123456','caaef9d0-d44b-4578-9ce2-b1f5b6d58118'),('60aed5f4-bd61-4fe1-ab1e-7780b139d468','2024-04-12 12:32:07.403521',NULL,NULL,NULL,'2024-04-12 12:32:07.405042',NULL,NULL,22,'2001-05-30','1',NULL,'42newJJ@gmail.com','機器人42',NULL,'0987100042','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('692b7a4d-53a7-4b50-a668-71458dc486a0','2024-04-12 12:32:07.228966',NULL,NULL,NULL,'2024-04-12 12:32:07.231076',NULL,NULL,26,'1997-05-19','3',NULL,'25newJJ@gmail.com','機器人25',NULL,'0987100025','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('6dadc71f-2098-4eb1-bde9-0c87840e26e5','2024-04-12 12:32:07.272119',NULL,NULL,NULL,'2024-04-12 12:32:07.274203',NULL,NULL,26,'1998-04-06','3',NULL,'29newJJ@gmail.com','機器人29',NULL,'0987100029','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('750b3e5c-fe26-4823-8cc8-9a07b19bc86e','2024-04-12 12:32:07.464227',NULL,NULL,NULL,'2024-04-12 12:32:07.466887',NULL,NULL,29,'1994-09-05','3',NULL,'47newJJ@gmail.com','機器人47',NULL,'0987100047','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('785276ec-0421-401b-9c55-e76c5447072d','2024-04-12 12:32:07.166732',NULL,NULL,NULL,'2024-04-12 12:32:07.169294',NULL,NULL,19,'2005-01-25','3',NULL,'19newJJ@gmail.com','機器人19',NULL,'0987100019','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('7d532222-424a-4e6c-a45a-67d375d5b1c0','2024-04-12 12:32:07.392799',NULL,NULL,NULL,'2024-04-12 12:32:07.394926',NULL,NULL,26,'1997-08-18','3',NULL,'41newJJ@gmail.com','機器人41',NULL,'0987100041','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('7f5699b9-c9e5-475c-9e78-fab0ba9784d2','2024-04-12 12:32:07.094625',NULL,NULL,NULL,'2024-04-12 12:32:07.097231',NULL,NULL,26,'1997-05-20','3',NULL,'11newJJ@gmail.com','機器人11',NULL,'0987100011','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('80600e2c-9307-4ba3-99a6-e88e84549ff0','2024-04-12 12:32:07.003959',NULL,NULL,NULL,'2024-04-12 12:32:07.005520',NULL,NULL,29,'1995-03-19','3',NULL,'1newJJ@gmail.com','機器人1',NULL,'0987100001','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('83f33672-5d20-4191-ae7e-3f0d15e8fb95','2024-04-12 12:32:07.130943',NULL,NULL,NULL,'2024-04-12 12:32:07.133113',NULL,NULL,18,'2006-03-23','1',NULL,'15newJJ@gmail.com','機器人15',NULL,'0987100015','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('9374bd45-394f-4064-9d90-7ae98307f2a1','2024-04-12 12:32:07.363197',NULL,NULL,NULL,'2024-04-12 12:32:07.364752',NULL,NULL,19,'2004-08-22','3',NULL,'38newJJ@gmail.com','機器人38',NULL,'0987100038','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('93ac1bb7-a0b8-4078-b2bc-ca70c7aee38d','2024-04-12 12:32:07.304315',NULL,NULL,NULL,'2024-04-12 12:32:07.305834',NULL,NULL,23,'2001-04-03','2',NULL,'32newJJ@gmail.com','機器人32',NULL,'0987100032','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('987f212c-7dbc-4a33-9ed0-3afdd2940d2f','2024-04-12 12:32:07.314257',NULL,NULL,NULL,'2024-04-12 12:32:07.316441',NULL,NULL,20,'2003-07-05','1',NULL,'33newJJ@gmail.com','機器人33',NULL,'0987100033','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('9b06a42f-62e0-4e48-b800-a00ccce72479','2024-04-12 12:32:07.058230',NULL,NULL,NULL,'2024-04-12 12:32:07.060333',NULL,NULL,24,'1999-05-20','3',NULL,'7newJJ@gmail.com','機器人7',NULL,'0987100007','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('a135ab59-ec74-4af5-aa8e-749a34e15e44','2024-04-12 12:32:07.324444',NULL,NULL,NULL,'2024-04-12 12:32:07.325963',NULL,NULL,20,'2003-10-03','3',NULL,'34newJJ@gmail.com','機器人34',NULL,'0987100034','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('a17b1ba8-d4d5-483d-bfd9-78de0e78bdbe','2024-04-12 12:32:07.207984',NULL,NULL,NULL,'2024-04-12 12:32:07.210234',NULL,NULL,16,'2007-05-29','3',NULL,'23newJJ@gmail.com','機器人23',NULL,'0987100023','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('ab3436aa-7f20-427d-b8d6-1ac5e85f86d7','2024-04-12 12:32:07.196474',NULL,NULL,NULL,'2024-04-12 12:32:07.199637',NULL,NULL,26,'1998-01-18','3',NULL,'22newJJ@gmail.com','機器人22',NULL,'0987100022','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('bf855a97-15b1-4881-b5d7-23752e07557d','2024-04-12 12:32:07.075280',NULL,NULL,NULL,'2024-04-12 12:32:07.077898',NULL,NULL,25,'1999-03-06','1',NULL,'9newJJ@gmail.com','機器人9',NULL,'0987100009','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('c55ef75e-6c47-4915-8347-d0b206119d4f','2024-04-12 12:32:07.346170',NULL,NULL,NULL,'2024-04-12 12:32:07.347768',NULL,NULL,18,'2005-07-10','1',NULL,'36newJJ@gmail.com','機器人36',NULL,'0987100036','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('cce331a3-55e1-4e70-a47b-58c6b0468d8e','2024-04-12 12:32:07.030041',NULL,NULL,NULL,'2024-04-12 12:32:07.031618',NULL,NULL,26,'1997-09-10','2',NULL,'4newJJ@gmail.com','機器人4',NULL,'0987100004','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('d00cecee-06cd-4898-a6b2-ce137103ef62','2024-04-12 12:32:07.293613',NULL,NULL,NULL,'2024-04-12 12:32:07.295191',NULL,NULL,21,'2002-08-28','3',NULL,'31newJJ@gmail.com','機器人31',NULL,'0987100031','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('de0f5e0d-2c42-4cae-85bf-139a1aa2e474','2024-04-12 12:32:07.354665',NULL,NULL,NULL,'2024-04-12 12:32:07.356751',NULL,NULL,26,'1997-09-27','3',NULL,'37newJJ@gmail.com','機器人37',NULL,'0987100037','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('dffc6fca-796d-4401-be90-6003a2abeb72','2024-04-12 12:32:07.122527',NULL,NULL,NULL,'2024-04-12 12:32:07.124615',NULL,NULL,17,'2006-05-12','3',NULL,'14newJJ@gmail.com','機器人14',NULL,'0987100014','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('e048b984-4dd9-4668-afeb-237b2db8ebd7','2024-04-12 12:32:07.427265',NULL,NULL,NULL,'2024-04-12 12:32:07.429394',NULL,NULL,22,'2001-10-30','2',NULL,'44newJJ@gmail.com','機器人44',NULL,'0987100044','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('e2476e38-652a-4593-a319-4e75fd7a1872','2024-04-12 14:10:41.998264',NULL,NULL,NULL,'2024-04-12 14:31:38.964623','teacher2','睡在學校',30,'1994-01-01','1','2024-04-12 14:31:38.964103','teacher@gmail.com','路過的老師','b59c67bf196a4758191e42f76670ceba','0988123456','70638573-73fc-45b1-a5e7-a3b3b24e166d'),('e5e2beba-2f9f-4420-a75c-baefd96e0de1','2024-04-12 12:32:07.148132',NULL,NULL,NULL,'2024-04-12 12:32:07.150212',NULL,NULL,28,'1995-07-03','3',NULL,'17newJJ@gmail.com','機器人17',NULL,'0987100017','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('eb01b7c8-48c8-45e6-a24a-503acdb0b0af','2024-04-12 12:32:07.476061',NULL,NULL,NULL,'2024-04-12 12:32:07.478711',NULL,NULL,18,'2005-10-30','1',NULL,'48newJJ@gmail.com','機器人48',NULL,'0987100048','b4df6fbd-f76f-4226-a74a-b36997d8aeff'),('fed82480-b20f-45cc-b337-802bb8b9ff38','2024-04-12 12:32:07.039266',NULL,NULL,NULL,'2024-04-12 12:32:07.041394',NULL,NULL,22,'2002-03-22','3',NULL,'5newJJ@gmail.com','機器人5',NULL,'0987100005','b4df6fbd-f76f-4226-a74a-b36997d8aeff');
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
INSERT INTO `menu_role` VALUES ('d817f754-7371-4835-a217-b4bf271788b9','1817a9e1-c51e-4503-8581-aa567a03f7f2'),('0aa10cae-2028-4b9d-ad3b-82de52ee4abd','38c1875b-9cf6-46d5-8b88-b4c7bd4890cd'),('c544ea32-1058-4de9-8b98-a0567751e09c','38c1875b-9cf6-46d5-8b88-b4c7bd4890cd'),('d817f754-7371-4835-a217-b4bf271788b9','38c1875b-9cf6-46d5-8b88-b4c7bd4890cd'),('c544ea32-1058-4de9-8b98-a0567751e09c','70e6dd12-532f-4235-810b-1e1daeb28b59'),('d817f754-7371-4835-a217-b4bf271788b9','70e6dd12-532f-4235-810b-1e1daeb28b59'),('0aa10cae-2028-4b9d-ad3b-82de52ee4abd','c5af190f-4f63-475c-8d8f-0600a04f76bf'),('32d7aec7-cae4-434f-9fd4-ed6315515bf6','c5af190f-4f63-475c-8d8f-0600a04f76bf'),('7c926f63-6dc7-40de-9778-c48c799f0904','c5af190f-4f63-475c-8d8f-0600a04f76bf'),('c544ea32-1058-4de9-8b98-a0567751e09c','c5af190f-4f63-475c-8d8f-0600a04f76bf'),('d817f754-7371-4835-a217-b4bf271788b9','c5af190f-4f63-475c-8d8f-0600a04f76bf'),('d817f754-7371-4835-a217-b4bf271788b9','fee317e5-45c3-48db-9d44-ab1ba2d99e83');
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
INSERT INTO `user_role` VALUES ('06fa27e9-602f-4dd3-93b6-dad862963d0d','38c1875b-9cf6-46d5-8b88-b4c7bd4890cd'),('e2476e38-652a-4593-a319-4e75fd7a1872','38c1875b-9cf6-46d5-8b88-b4c7bd4890cd'),('0e2768bd-6d0a-4788-8394-0e005d0d8e3b','70e6dd12-532f-4235-810b-1e1daeb28b59'),('0eb66da9-1d6e-4e03-a30c-2e09595b6346','70e6dd12-532f-4235-810b-1e1daeb28b59'),('10884f8e-88ce-48a5-a9f7-9c470fb57572','70e6dd12-532f-4235-810b-1e1daeb28b59'),('144490da-f48f-4a11-856d-7f64b88883a3','70e6dd12-532f-4235-810b-1e1daeb28b59'),('1a2494ff-194a-4f7d-9219-a5aaf3f1ef0f','70e6dd12-532f-4235-810b-1e1daeb28b59'),('22d81c85-2114-409a-bce9-32a7e5776c0b','70e6dd12-532f-4235-810b-1e1daeb28b59'),('2c57db92-50a6-4d31-848d-3be6efc4e82c','70e6dd12-532f-4235-810b-1e1daeb28b59'),('3070939f-b202-440b-b06c-221b93db5d61','70e6dd12-532f-4235-810b-1e1daeb28b59'),('35275f9d-322f-4383-9fcf-bc295485da0f','70e6dd12-532f-4235-810b-1e1daeb28b59'),('37de06a0-e352-4503-9b59-a53d96c81389','70e6dd12-532f-4235-810b-1e1daeb28b59'),('38f88d78-e99a-4a78-9d36-3d105bec0e8d','70e6dd12-532f-4235-810b-1e1daeb28b59'),('3a9c896e-d50c-4d9e-b065-f1395311f7b6','70e6dd12-532f-4235-810b-1e1daeb28b59'),('3bfe1597-864b-4ff8-9a85-6204fbec501b','70e6dd12-532f-4235-810b-1e1daeb28b59'),('3eb8cb01-43b7-4bac-81ba-dcc25f7140f7','70e6dd12-532f-4235-810b-1e1daeb28b59'),('411c0be3-ff8a-4af8-b49e-d69f7d9a8b38','70e6dd12-532f-4235-810b-1e1daeb28b59'),('48fde1ec-708c-475f-8d42-425bb85c52ed','70e6dd12-532f-4235-810b-1e1daeb28b59'),('4c78df2b-127e-47fb-9f4b-e7b0e94f1645','70e6dd12-532f-4235-810b-1e1daeb28b59'),('4d9863e3-138c-4efc-8cd3-3a9ac2368271','70e6dd12-532f-4235-810b-1e1daeb28b59'),('4dcce67a-b661-4b28-b027-f80cd32a924f','70e6dd12-532f-4235-810b-1e1daeb28b59'),('4eb733a6-72c6-4d59-993a-1de5895c0e35','70e6dd12-532f-4235-810b-1e1daeb28b59'),('4ef88edf-e2e7-48cc-91f3-fc265350e114','70e6dd12-532f-4235-810b-1e1daeb28b59'),('52328569-7137-4d0f-a5cd-6d06957bce34','70e6dd12-532f-4235-810b-1e1daeb28b59'),('5704d25e-22f9-45b0-89a2-3603f2ad2e19','70e6dd12-532f-4235-810b-1e1daeb28b59'),('572b2634-e73c-4021-8fdc-2f688fba3494','70e6dd12-532f-4235-810b-1e1daeb28b59'),('58fd7fba-19bc-4cbe-8b9d-dc34491e29e1','70e6dd12-532f-4235-810b-1e1daeb28b59'),('5b905845-7a5b-467a-880f-e1407d689f74','70e6dd12-532f-4235-810b-1e1daeb28b59'),('60aed5f4-bd61-4fe1-ab1e-7780b139d468','70e6dd12-532f-4235-810b-1e1daeb28b59'),('692b7a4d-53a7-4b50-a668-71458dc486a0','70e6dd12-532f-4235-810b-1e1daeb28b59'),('6dadc71f-2098-4eb1-bde9-0c87840e26e5','70e6dd12-532f-4235-810b-1e1daeb28b59'),('750b3e5c-fe26-4823-8cc8-9a07b19bc86e','70e6dd12-532f-4235-810b-1e1daeb28b59'),('785276ec-0421-401b-9c55-e76c5447072d','70e6dd12-532f-4235-810b-1e1daeb28b59'),('7d532222-424a-4e6c-a45a-67d375d5b1c0','70e6dd12-532f-4235-810b-1e1daeb28b59'),('7f5699b9-c9e5-475c-9e78-fab0ba9784d2','70e6dd12-532f-4235-810b-1e1daeb28b59'),('80600e2c-9307-4ba3-99a6-e88e84549ff0','70e6dd12-532f-4235-810b-1e1daeb28b59'),('83f33672-5d20-4191-ae7e-3f0d15e8fb95','70e6dd12-532f-4235-810b-1e1daeb28b59'),('9374bd45-394f-4064-9d90-7ae98307f2a1','70e6dd12-532f-4235-810b-1e1daeb28b59'),('93ac1bb7-a0b8-4078-b2bc-ca70c7aee38d','70e6dd12-532f-4235-810b-1e1daeb28b59'),('987f212c-7dbc-4a33-9ed0-3afdd2940d2f','70e6dd12-532f-4235-810b-1e1daeb28b59'),('9b06a42f-62e0-4e48-b800-a00ccce72479','70e6dd12-532f-4235-810b-1e1daeb28b59'),('a135ab59-ec74-4af5-aa8e-749a34e15e44','70e6dd12-532f-4235-810b-1e1daeb28b59'),('a17b1ba8-d4d5-483d-bfd9-78de0e78bdbe','70e6dd12-532f-4235-810b-1e1daeb28b59'),('ab3436aa-7f20-427d-b8d6-1ac5e85f86d7','70e6dd12-532f-4235-810b-1e1daeb28b59'),('bf855a97-15b1-4881-b5d7-23752e07557d','70e6dd12-532f-4235-810b-1e1daeb28b59'),('c55ef75e-6c47-4915-8347-d0b206119d4f','70e6dd12-532f-4235-810b-1e1daeb28b59'),('cce331a3-55e1-4e70-a47b-58c6b0468d8e','70e6dd12-532f-4235-810b-1e1daeb28b59'),('d00cecee-06cd-4898-a6b2-ce137103ef62','70e6dd12-532f-4235-810b-1e1daeb28b59'),('de0f5e0d-2c42-4cae-85bf-139a1aa2e474','70e6dd12-532f-4235-810b-1e1daeb28b59'),('dffc6fca-796d-4401-be90-6003a2abeb72','70e6dd12-532f-4235-810b-1e1daeb28b59'),('e048b984-4dd9-4668-afeb-237b2db8ebd7','70e6dd12-532f-4235-810b-1e1daeb28b59'),('e5e2beba-2f9f-4420-a75c-baefd96e0de1','70e6dd12-532f-4235-810b-1e1daeb28b59'),('eb01b7c8-48c8-45e6-a24a-503acdb0b0af','70e6dd12-532f-4235-810b-1e1daeb28b59'),('fed82480-b20f-45cc-b337-802bb8b9ff38','70e6dd12-532f-4235-810b-1e1daeb28b59'),('5b905845-7a5b-467a-880f-e1407d689f74','c5af190f-4f63-475c-8d8f-0600a04f76bf');
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

-- Dump completed on 2024-04-15  9:22:41
