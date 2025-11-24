-- MySQL dump 10.13  Distrib 9.0.1, for macos12.7 (x86_64)
--
-- Host: localhost    Database: planas
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `calendars`
--

DROP TABLE IF EXISTS `calendars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `calendars` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `user_id` bigint NOT NULL,
  `calendar_color` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2ef443fpfyuaay9nc09tvhjre` (`user_id`),
  CONSTRAINT `FK2ef443fpfyuaay9nc09tvhjre` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calendars`
--

LOCK TABLES `calendars` WRITE;
/*!40000 ALTER TABLE `calendars` DISABLE KEYS */;
INSERT INTO `calendars` VALUES (10,'업무',3,1),(11,'개인',3,6);
/*!40000 ALTER TABLE `calendars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `events` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `description` text,
  `end_date_time` datetime(6) DEFAULT NULL,
  `event_category` varchar(255) NOT NULL,
  `start_date_time` datetime(6) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `calendar_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsr217wml5c5jdpgd2ro6k8omx` (`calendar_id`),
  KEY `FKat8p3s7yjcp57lny4udqvqncq` (`user_id`),
  CONSTRAINT `FKat8p3s7yjcp57lny4udqvqncq` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKsr217wml5c5jdpgd2ro6k8omx` FOREIGN KEY (`calendar_id`) REFERENCES `calendars` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events`
--

LOCK TABLES `events` WRITE;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
INSERT INTO `events` VALUES (25,'2025-11-18 18:13:20.779760','','2025-11-18 18:00:00.000000','EVENT','2025-11-18 17:00:00.000000','중급처 회의','2025-11-18 18:13:20.779874',10,3),(28,'2025-11-19 13:02:31.537259','','2025-11-23 19:00:00.000000','EVENT','2025-11-23 17:30:00.000000','상민이 누나 저녁','2025-11-19 13:02:31.537348',11,3),(29,'2025-11-19 16:12:15.651536','','2025-11-23 12:00:00.000000','EVENT','2025-11-23 10:00:00.000000','밤축 풋살','2025-11-19 16:12:15.651589',11,3),(30,'2025-11-19 17:51:28.920557','','2025-11-19 18:00:00.000000','EVENT','2025-11-19 17:00:00.000000','중금처 회의','2025-11-19 17:51:28.920701',10,3),(31,'2025-11-19 17:51:51.644611','','2025-11-20 18:00:00.000000','EVENT','2025-11-20 17:00:00.000000','중급처 회의','2025-11-19 17:51:51.644621',10,3),(32,'2025-11-19 18:05:05.421631','','2025-11-21 18:00:00.000000','EVENT','2025-11-21 17:00:00.000000','중급처 회의','2025-11-19 18:05:05.421655',10,3),(33,'2025-11-20 14:02:47.577178','','2025-11-21 12:40:00.000000','EVENT','2025-11-21 11:40:00.000000','정형외과 진료예약','2025-11-20 14:02:47.577188',11,3),(34,'2025-11-20 14:51:26.468410','','2025-11-28 23:00:00.000000','EVENT','2025-11-28 19:00:00.000000','휘문고 1-14 저녁 모임','2025-11-20 14:51:26.468431',11,3);
/*!40000 ALTER TABLE `events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_color`
--

DROP TABLE IF EXISTS `item_color`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_color` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bg_color` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `text_color` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_color`
--

LOCK TABLES `item_color` WRITE;
/*!40000 ALTER TABLE `item_color` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_color` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `memo`
--

DROP TABLE IF EXISTS `memo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `memo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` text,
  `created_at` datetime(6) DEFAULT NULL,
  `memo_color` int NOT NULL,
  `tags` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `user_id` bigint NOT NULL,
  `folder_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj9dhd2726xu5txp0rr49k9vik` (`user_id`),
  KEY `FK5cql8ftvokho9d7dsqy2519y0` (`folder_id`),
  CONSTRAINT `FK5cql8ftvokho9d7dsqy2519y0` FOREIGN KEY (`folder_id`) REFERENCES `memo_folders` (`id`),
  CONSTRAINT `FKj9dhd2726xu5txp0rr49k9vik` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `memo`
--

LOCK TABLES `memo` WRITE;
/*!40000 ALTER TABLE `memo` DISABLE KEYS */;
INSERT INTO `memo` VALUES (24,'비동기 - 어떤 직업이 완료될 때까지 기다리지 않고 해당 작어이 얼마나 오래 걸릴지 가정하지 않음\n원자적 - 1. 연산의 호과가 어느 한 시점에 나타나서 \"절반만 수행된\" 상태를 동시에 실행하는 다른 프로세스에서 볼 수 없음 \n2. 반드시 모두 커밋되거나 모두 롤백돼야 하는 쓰기 집합을 묶음','2025-11-20 14:47:27.797485',2,'프로그래밍,용어','데이터 중심 어플리케이션 설계 용어 공부 1','2025-11-20 14:47:27.797500',3,6),(25,'신뢰성 (Reliability)\n- 하드웨어나 소프트웨어 결함, 심지어 인적 오류 가틍 역경에 직면하더라도 시스템은 지속적으로 올바르게 동작(원하는 성능 수준에서 정확한 기능을 수행)해야 한다. \n\n확장성 (Scalability)\n- 시스템의 데이터 양, 트래픽 양, 복잡도가 증가하면서 이를 처리할 수 있는 적절한 방법이 있어야 한다. \n\n유지보수성 (Maintainability)\n- 시간이 지남에 따라 여러 다양한 사람들이 시스템 상에서 작업(현재 작업을 유지보수하고 새로운 사용 사례를 시스템에 적용하는 엔지니어링과 운영)할 것이기 때문에 모든 사용자가 시스템 상에서 생산적으로 작업할 수 있게 해야 한다.','2025-11-20 15:11:36.344225',2,'소프트웨어,시스템,3요소','소프트웨어 시스템에서 중요한 3가지','2025-11-20 15:11:36.344239',3,6),(26,'RAID = 여러 개의 디스크를 묶어서 하나의 논리적 디스크를 만드는 기술 (속도 , 안정성)\n종류\nRAID 0 (스트라이핑, 속도 위주) - 디스크 2개, 용량 2배, 속도 2배, 하나만 고장나도 DB 장애\nRAID 1 (미러링, 안정성 위주) - 디스크 2개, 용량 그대로, 속도 비슷 (읽기만 조금 빠름), 하나 고장나도 문제 없음\nRAID 10 (RAID1 + RAID0) - 디스크 4개, (DISK1, DISK2) - SET A -> RAID1, (DISK3, DISK4) - SET B -> RAID1, SET A + SET B -> RAID0','2025-11-24 11:13:14.780192',8,'데이터베이스,DB,디스크,RAID','디스크 RAID 구성','2025-11-24 11:13:14.780221',1,6),(27,'부하 매개변수의 종류\n1. 웹 서버의 초당 요청 수\n2. 데이터베이스의 읽기 대 쓰기 비율\n3. 동시 활성 사용자 (Active User)\n4. 캐시 적중률\n- 평균적인 경우가 중요할 수도 있고, 소수의 극단적인 경우가 병목 현상의 원인일 수도 있다.\n\n예시 ) 트위터\n트윗 작성 - 사용자는 새로운 메시지를 게시할 수 있다 (평균 초당 4.6K 요청, 피크일 때 초당 12K 요청 이상)\n홈 타임라인 - 사용자는 팔로우 한 사람이 작성한 트윗을 볼 수 있다. (초당 300K 요청)\n두가지 기능을 구현하는 방법\n1. 트윗 작성은 단순히 새로운 트윗을 삽입한다. 홈 타임라인은 사용자가 요청하면 먼저 팔로워 테이블을 통해 모든 팔로워를 조회하고, 유저 테이블과 트윗 테이블을 조인하여 유저 정보와 트윗 정보를 가지고 온다.\n2. 홈 타임라인 요청이 월등히 많기 때문에 각 사용자의 홈 타임라인 캐시를 유지한다. 대신 트윗을 작성하면 팔로우하는 사람을 모두 찾고 팔로워 홈 타임라인 캐시에 새로운 트윗을 삽입한다.','2025-11-24 11:29:59.949401',1,'시스템,부하 기술,확장성,모니터링','시스템 부하 매개변수 설정','2025-11-24 11:29:59.949415',1,6),(28,'1) RDS가 부담되는 비용 구조라서\nRDS는 최소 스펙도 매달 30~50달러 넘어감\n거기에 스토리지 + 백업까지 붙으면 7~8만 원이 훌쩍 넘지\n사이드 프로젝트에서 이 정도는 과함\n\n반대로 EC2 t3.micro나 t3.small 하나에 MySQL 설치하면\n월 6~12달러 수준에서 끝남\nDB 비용이 사실상 0원\n\n2) 트래픽이 적은 서비스는 충분한 성능이 나옴\n사이드 프로젝트/초기 서비스는 보통:\n일일 활성 유저 < 1000\n요청 수 < 수천 req/day\n정도라서\nEC2 한 대(MySQL + API 같이)면 성능이 충분히 남아.\n심지어 작은 스타트업 MVP도 이 구조로 6개월~1년은 버팀.\n\n3) 운영 간단함\n서버 하나만 관리하면 됨\n비용 구조 간단\nDocker로 묶어두면 백업·복구도 쉬움\n사실 RDS는 “편하지만 비싼 서비스”라서\n규모가 커지기 전에는 굳이 필요하지 않아.\n\n4) 나중에 스케일링도 어렵지 않다\n초기에는 서버 1대로 운영하다가\n트래픽이 늘면 이렇게 단계적으로 확장할 수 있어:\n\nEC2 1대 (API + DB 같이)\n→ 2) API/DB 분리 (EC2 2대)\n→ 3) DB를 RDS로 마이그레이션\n→ 4) 캐싱/로드밸런서 도입\n→ 5) ECS 등 컨테이너 전환\n\n이게 초보 스타트업이나 개인 개발자가 실제로 밟는 전형적인 성장 루트야.','2025-11-24 15:00:14.537383',2,'시스템,아키텍처,배포,스타트업','초기 서비스 배포 방법','2025-11-24 15:00:14.537398',1,6);
/*!40000 ALTER TABLE `memo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `memo_folders`
--

DROP TABLE IF EXISTS `memo_folders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `memo_folders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf3w4t5r4fu8wou0kvew91ivjj` (`user_id`),
  CONSTRAINT `FKf3w4t5r4fu8wou0kvew91ivjj` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `memo_folders`
--

LOCK TABLES `memo_folders` WRITE;
/*!40000 ALTER TABLE `memo_folders` DISABLE KEYS */;
INSERT INTO `memo_folders` VALUES (6,'2025-11-18 16:47:36.414735','공부','2025-11-18 16:47:36.414750',3);
/*!40000 ALTER TABLE `memo_folders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refresh_tokens`
--

DROP TABLE IF EXISTS `refresh_tokens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refresh_tokens` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `expires_at` datetime(6) NOT NULL,
  `revoked` bit(1) NOT NULL,
  `token` varchar(255) NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKghpmfn23vmxfu3spu3lfg4r2d` (`token`),
  KEY `FK1lih5y2npsf8u5o3vhdb9y0os` (`user_id`),
  CONSTRAINT `FK1lih5y2npsf8u5o3vhdb9y0os` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refresh_tokens`
--

LOCK TABLES `refresh_tokens` WRITE;
/*!40000 ALTER TABLE `refresh_tokens` DISABLE KEYS */;
INSERT INTO `refresh_tokens` VALUES (1,'2025-12-05 02:34:58.235137',_binary '\0','WJU3LcOXXcuZK_i8Z_zPRy6HIngpWYRchswRNp4hJOQHpzgGfNp6xrHnKB6egSS5ITsMVaJPZg59DMQMAnR8Bw',2),(2,'2025-12-05 02:58:55.914047',_binary '\0','HWAGYn-KMXUJ5WyAdvGVZ1s4p0Xz-tv8DzmwbtVVUuc3I0mT6alyjjgUIOOp6LU7DqYa5qADCqIM3Pw2atoe9w',2),(3,'2025-12-05 03:09:40.292241',_binary '\0','QsINzfQeMDN88KMb4khubkPHadBXZWj0QAowGR7Z_yLx7Tv3n8sh76c04maiIPG4NkFiCemw5LM9gWjH1jiNuw',2),(4,'2025-12-05 03:32:34.865038',_binary '\0','xi_rt2DLOIHQPF3ZmNNX5i-RP6uHnTadMWNsmUSvIODtq149fmvTCcXcwFS2aLoil63P1qodvzCv3yrdg4nALw',2),(5,'2025-12-05 05:21:32.803878',_binary '','uU4evoxqOiFm6kObJeickVdB07WLegvNxJT1GiCE1NFRCbIMqr4KQ_NT7B8LEOpxJQc1YhrwRQn8ggMkbPcOLw',2),(6,'2025-12-05 06:46:10.559134',_binary '','SWXWUgvoeod6VcFyB372D94NvyCvJ_ey4D2E9Y0duBkRgRzFdDSUcDSoIZ0gxIvDMf6r56Za8O1upeJoBzMxow',2),(7,'2025-12-05 06:46:10.559134',_binary '\0','vWo1A6JNJRekiDG6vdQKALgSrn1Rgxcc8J2GkisHlLWGcUe6XAZ-rdvQP7vMo4HkIEdrtBualtBCu_NVtpOXOA',2),(8,'2025-12-05 06:46:10.559133',_binary '\0','5hrN4U2Lbs7cc2WI_TDjA0Gm5gckgXOuBVyow4mI9eSPSUcXPNLMbLgqRg111LSccW2fOpu38kyggJf9S9p7ZQ',2),(9,'2025-12-05 07:18:26.461831',_binary '','d0kpoNa3a19h8V2aGumUFJkh73M9sudWWf57zW2Er0-qvIhDvtVE3lDTJ0qb6UIl7fD1qgtK18VCMcEjdAX_eA',2),(10,'2025-12-05 07:18:26.463155',_binary '\0','sO4h1tN6xC0BGGctH4aGt2sa3YaJQZNIbtUu4yQ_mMN2WN-QX6Gtut3ewvjuqxBsoABsmfNGTpMjPYBhX_vLWA',2),(11,'2025-12-05 07:18:26.462851',_binary '\0','iyqi81AibaIubdIV_hNgz8WzHbf9bfc2KC-ZexfxbN5AdqSDGIMEwg0B-ViVsWWgGdRwbRpqFNrtP1auZNslzA',2),(12,'2025-12-05 07:35:59.971298',_binary '\0','FyWxFqayQ7J7yT98vN7mQ86d6bGBIqkCgPT1lNwiiAzUi-fQWWvYtUqH4n_8v7wM8jwyQL10hI3ORh_LA4-ljQ',2),(13,'2025-12-05 07:35:59.972575',_binary '\0','yubeJfzisxWpAlE6DNerYwjomB_qjdLSx9N2Xmi4fn_9WzhqTKblo1H1TWcbpTa-pKzVveXrrqRf06lINnivtA',2),(14,'2025-12-05 07:35:59.972476',_binary '','2xiUHC0XcMcyWIKDcs7vhiClxRshRLTwvcB4Igjl-mwQbKvDBV08816zf-CVnuRoDa-MCfgtLjibsYURSqXPmg',2),(15,'2025-12-05 07:52:47.048281',_binary '','isrcuywJr_rhe3NCmfu9u5IZ9eCjBDsYV42yLHSRcSCmdn9tjQmcqsiiGzf7RbZ8vrT5QwNTYFHdxl3wl7eMmg',2),(16,'2025-12-05 07:52:47.048281',_binary '\0','hK1kwZikpCJTGNWztCE2kd3wyh1LHLtmF6eemSkpZDZtrTJbP1u4Dq2GXvwxsPU-ov0X_EuVI0SCqHa02CL2IA',2),(17,'2025-12-05 08:22:28.686218',_binary '\0','XnPUGvYvWGlVFiFLwK4uUXqAdY5rtCSRrbkwFugzRcXs6MXLe_6PIPmKcNarmID64wG3Xf4cYK8n8aJK65qbpw',2),(18,'2025-12-05 08:22:28.686218',_binary '\0','j2ld7WRj80j9PrvFSeeMsKnkXHbEkcyFqEHxJm03vq57z5fAWmfdXRa44s8i-ogcbwJ1_RZluQZ_z_gCUxABrA',2),(19,'2025-12-05 08:22:28.686218',_binary '','QaZoWFU1lhPHh3Hyq2Arg53OBTygCYCpFIR20g_1tC3VdV6FDL_8VIANck9GpHZTJ1i-ZhRXASR5fUgg_dMZdQ',2),(20,'2025-12-08 01:14:07.182437',_binary '\0','DDujv0yi3xdITtguyFXTlsmKefijYVRjcLqVEU3Gfy_Q27zBlt2P0blC71OdR_dZychNKh90GkS8NiNc-uedEA',2),(21,'2025-12-08 01:14:07.182434',_binary '\0','5L6CXh8vBfELwhZLBT-tGwfYNwNEmFqJ6H7iH_SeZmaJnhZDzGqBmN5VipqloPI72LyZerKh2tSjmN-l3XKEzA',2),(22,'2025-12-08 01:14:07.182438',_binary '\0','ydOF8dJznZtmM5lPy01kG9_ZjzGDmyww0JnibJ1cZePYpl01UkED-R_mwe_m3oi_5L-_4pY7KSFK4d1uw9kv7w',2),(23,'2025-12-08 01:19:00.586272',_binary '','U0nLi1EioVv6mShQK7xGPAl2677nP6eKks5thfDBZCu3sxEKLxBz6q6hnTT5aW3wobXYJbsqIgrhGx7oitnQdQ',3),(24,'2025-12-08 01:36:29.101393',_binary '','FNSryvDA8OQeoUPtueQ-SPQwg5MZDAPrbF8F1Rtg0M2OE57pWiqWJgjeCJGioFz5CiRbB7iscLVwTBnLR0UfGA',3),(25,'2025-12-08 02:04:30.489413',_binary '\0','A_0F8c7UqxhQGrJp7o7NnbT9ZeeoM7CmpINZhcND8riB7IrDIq6XjdM-7csrTRAYEUmy4iWFzuCPBsscF74wTQ',3),(26,'2025-12-08 02:04:30.488552',_binary '','LBo5miLd9uX-o1KfmyRWfWf7ZfLUtJpwmaxgt6R1SQ5TorfK65-HHkLU8s3_X5hbjo11KFAeIuzK-zO3PPgbKQ',3),(27,'2025-12-08 02:29:59.915984',_binary '','BzXjyafIvYANaV70TVgPM6kpEp-rGgYeyqJQ_PgbywT5LHU2tZJwgysDwWsJdv2-mTTCjMPK0lv0h4bVVvzhRA',3),(28,'2025-12-08 05:13:29.222312',_binary '','VLjAr63XcBWbwZfJDcA9Orvl-163gV6zX0bxlcrrQmzavSs0q5dRbjDB6S0M4xnkjCtjpntO1RiThFb6EG4LHw',3),(29,'2025-12-08 05:13:29.220715',_binary '\0','sRSJRpTIncNCKToHxH-2IhYYluJI149ouRP91iufGFUm86alzCzVaegS0Vmaqps2VHRBnGvhtkOFax_Z4OT66g',3),(30,'2025-12-08 05:13:29.223136',_binary '\0','0-veJMonm8zwub1KSB1ONJQWScp7BmkpwA4UakOslEhq3g3JSr2sOm2Qu5FzdojyYtFHBy-1v3PlaHzzpRwlTQ',3),(31,'2025-12-08 05:13:29.224427',_binary '\0','B8SXGauSWHe2xQfPa1et2_qNN3yUmgXiHRu_c7hw3QQbKlkcHph5lyBDnZnVYRByP4R0wkpumOr7gmlb7kQmCw',3),(32,'2025-12-08 05:40:44.501068',_binary '\0','JZEc47Om-G6_ywxDES2Vsp3tZjAuuUOUodq3zFu_k3op4fsxEZvnsCPj07SISa7rTS1pGpDQshJn3TQqZCMZWA',3),(33,'2025-12-08 05:58:43.134854',_binary '\0','AcYdA5J_h4QxhaGpUwTlM3wg6ETVqDeYjJWTH7qFOxxXe2nYxD_fcFwSrk9EIzmo0W07QC-DKOPyrN30bR4-3w',3),(34,'2025-12-08 06:28:34.128738',_binary '','-rDH965vRFe8-GbVG77l3FMTy5LLupMw_td84TE4m5Qfk4LoEpgXnR0IC49v6ZyTkl9KRZDMRbOyRxX7EnSEpg',3),(35,'2025-12-08 06:28:34.128158',_binary '\0','THJv1kL__eD856o2pO0eAZbbjgeMYF-fONXFypz6JdgCQKIOPegemWC2CSQ4sAwGyQQEj6ppuWVgrIBad2nDcg',3),(36,'2025-12-08 06:28:34.128158',_binary '\0','4q1HKnjL3v1q_aDq08I_8DfUUkm5vob9l4v8pK8JweapyslErvRQJ0lsySZ4qSqxDkn9NN0YCNdNeVoJlLpilA',3),(37,'2025-12-08 06:39:27.075825',_binary '\0','HwrUBGss1JL9oLRAGIbXGtW_Fo6NmacNM6WMsPRmkyn6ivinIlrHVtm_sjZVZKvik0K6ROPYeHu5yJ-gjuf_qQ',3),(38,'2025-12-08 06:48:09.468568',_binary '\0','T41TNOx2K-YGhctfLHed366jLazZhMFBdjig9OSSvoBmb4JM42gY7ypYtGUZ4j8rkLiZkPbvcz40jMq9Hz3L2Q',3),(39,'2025-12-08 06:48:09.468568',_binary '','ktzB0JUX25cKMaGiy0uY-xFraIl0SHcgfYFax4pJRFg8fdb5ijnlicR0CQ6WR5VMn4jLbhDkscRzodOd67qkwg',3),(40,'2025-12-08 07:05:17.704716',_binary '\0','LYw9frrMnIYuhgIJBqZi7L4z80NgrffgZwN0T9AXeWWFpp_5PpcPdNwgSEMiE6J-YWdcTbSoBoLsXpgdQ6pkbQ',3),(41,'2025-12-08 07:05:17.704715',_binary '\0','simU2xBdM-bSM0VOcH1f7QfALhGYYIU8znVl1P_c5VikA9to4ezwjPsIObCRc9bmnPGUDc1rBelI1qlAGk-RoQ',3),(42,'2025-12-08 07:05:17.704715',_binary '\0','3bBGk6BPSuX4gzj822IAQGlUljkD9oFL7XODLXHWBpw8QkhS1b9YArXhssl0XRBfOPk-XMg1uXceFdYQdJfO2g',3),(43,'2025-12-08 07:09:13.393397',_binary '\0','18FYrnxZDDHAtByswqJUlh3kIxfuZaRG7lYrEf73YWJmFJNQHdS0XUG5_WOAS2QKeuIqRoi4cYil9i2DHxkuug',3);
/*!40000 ALTER TABLE `refresh_tokens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tasks` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `completed` bit(1) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` text,
  `duedate` date DEFAULT NULL,
  `priority` enum('HIGH','LOW','MEDIUM') DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6s1ob9k4ihi75xbxe2w0ylsdh` (`user_id`),
  CONSTRAINT `FK6s1ob9k4ihi75xbxe2w0ylsdh` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=165 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasks`
--

LOCK TABLES `tasks` WRITE;
/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
INSERT INTO `tasks` VALUES (146,_binary '','2025-11-18 14:52:48.759619','','2025-11-18','MEDIUM','캘린더 생성 기능 추가하기','2025-11-18 14:52:48.759777',3),(147,_binary '\0','2025-11-19 12:55:16.263323','일정이 아닌 할일 생성해도 캘린더에 추가',NULL,'MEDIUM','할일, 캘린더 통합','2025-11-19 12:55:16.263350',3),(148,_binary '','2025-11-19 12:55:53.400927','마지막장 완독','2025-11-19','MEDIUM','데이터 중심 어플리케이션 끝내기','2025-11-19 12:55:53.400939',3),(149,_binary '','2025-11-19 12:56:40.215268','','2025-11-21','LOW','돈 벌 수 있는일 한번 찾아보기','2025-11-19 12:56:40.215280',3),(150,_binary '\0','2025-11-19 14:07:12.373945','',NULL,'MEDIUM','반복일정 추가 기능 개발','2025-11-19 14:07:12.373957',3),(151,_binary '','2025-11-19 14:15:00.753323','','2025-11-19','HIGH','일정 삭제 기능 개발','2025-11-19 14:15:00.753336',3),(152,_binary '','2025-11-19 14:59:28.909976','폴더 별로 추가 버튼 따로 배치','2025-11-19','MEDIUM','메모 추가 버튼 위치 수정','2025-11-19 14:59:28.910024',3),(153,_binary '','2025-11-19 16:19:13.207559','- 해야하긴 하지만 당장은 할 수 없는 일 \n- 언제 할지는 모르겠지만 나중에 까먹을 수도 있어서 적어두고 싶은 작업','2025-11-19','MEDIUM','작업 중급처 기능 추가하기','2025-11-19 16:19:13.207573',3),(154,_binary '','2025-11-19 16:20:25.848838','','2025-11-19','LOW','할일 목록 버튼 색 추가','2025-11-19 16:20:25.848849',3),(155,_binary '','2025-11-19 18:07:11.067875','1장에서 기록할 만한 지식 메모 하나 작성','2025-11-20','MEDIUM','데이터 중심 어플리케이션 1장 메모 작성','2025-11-19 18:07:11.067893',3),(156,_binary '','2025-11-20 12:35:12.629570','kotlin spring boot, next.js 기반 로그인 기능 구현 자료 조사','2025-11-20','HIGH','로그인 기능 개발 자료 조사','2025-11-20 12:35:12.629594',3),(157,_binary '','2025-11-20 16:57:36.668165','','2025-11-20','MEDIUM','로그인 화면 만들기','2025-11-20 16:57:36.668299',3),(158,_binary '','2025-11-21 17:23:47.272198','테스트입니다','2025-12-05','LOW','할일 테스트','2025-11-21 17:23:47.272244',2),(159,_binary '','2025-11-21 17:36:54.561866','weg','2025-11-22','MEDIUM','xptm','2025-11-21 17:36:54.561878',2),(160,_binary '','2025-11-24 10:24:00.596320','','2025-11-24','MEDIUM','데이터 중심 애플리케이션 설계 1장 메모 추가','2025-11-24 10:24:00.596341',3),(161,_binary '\0','2025-11-24 10:25:02.573600','','2025-11-24','MEDIUM','planas 프런트 배포 시도','2025-11-24 10:25:02.573614',3),(162,_binary '\0','2025-11-24 10:25:58.680005','픽셀 수 늘려서 좀 더 정교한 픽셀 캐릭터 만들기','2025-11-25','LOW','planas 픽셀 캐릭터 수정','2025-11-24 10:25:58.680016',3),(163,_binary '\0','2025-11-24 10:36:29.126981','','2025-11-24','HIGH','LEET 1장 - 논리학 기초개념  정독','2025-11-24 10:36:29.126992',3),(164,_binary '\0','2025-11-24 14:43:22.045007','','2025-11-24','MEDIUM','로컬 DB 데이터 EC2로 복사','2025-11-24 14:43:22.045025',3);
/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'2025-11-05 16:40:43.585529','email','password'),(2,'2025-11-21 10:41:36.290272','test@example.com','$2a$10$tu3x4GRWisRWFxGwhumZueNpnJL1rZklP0K1b1xQkWfKpTwi0WPfG'),(3,'2025-11-24 10:18:51.569867','sangyun0904@gmail.com','$2a$10$CeRsYjMdaZbJiijaB.7kpe35sxMS2of9eSDXnVLOFuxH/wZ58lqhK');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-24 16:14:02
