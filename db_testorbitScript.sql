-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: testorbit
-- ------------------------------------------------------
-- Server version	5.7.27-log

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
-- Table structure for table `modules_to_testers`
--

DROP TABLE IF EXISTS `modules_to_testers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `modules_to_testers` (
  `MODULE_TESTER_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TESTER_ID` bigint(20) NOT NULL,
  `MODULE_ID` bigint(20) NOT NULL,
  `MOD_TESTER_STATUS` char(1) NOT NULL,
  PRIMARY KEY (`MODULE_TESTER_ID`),
  KEY `FK_MODTES_TESTER` (`TESTER_ID`),
  KEY `FK_MODTES_MOD` (`MODULE_ID`),
  CONSTRAINT `FK_MODTES_MOD` FOREIGN KEY (`MODULE_ID`) REFERENCES `testorbit_modules` (`MODULE_ID`),
  CONSTRAINT `FK_MODTES_TESTER` FOREIGN KEY (`TESTER_ID`) REFERENCES `testorbit_user_registration` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `testorbit_history`
--

DROP TABLE IF EXISTS `testorbit_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `testorbit_history` (
  `HISTORY_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TASK_ID` bigint(20) NOT NULL,
  `TESTER_ID` bigint(20) NOT NULL,
  `MODULE_ID` bigint(20) DEFAULT NULL,
  `TESTCASE_ID` bigint(20) DEFAULT NULL,
  `EXECUTION_DATE` date DEFAULT NULL,
  `RESULT_STATUS` varchar(10) NOT NULL,
  PRIMARY KEY (`HISTORY_ID`),
  KEY `FK_HISTORY_TESTER` (`TESTER_ID`),
  KEY `FK_HISTORY_TASK` (`TASK_ID`),
  KEY `FK_HISTORY_MOD` (`MODULE_ID`),
  KEY `FK_HISTORY_TESTCASE` (`TESTCASE_ID`),
  CONSTRAINT `FK_HISTORY_MOD` FOREIGN KEY (`MODULE_ID`) REFERENCES `testorbit_modules` (`MODULE_ID`),
  CONSTRAINT `FK_HISTORY_TASK` FOREIGN KEY (`TASK_ID`) REFERENCES `testorbit_tasks` (`TASK_ID`),
  CONSTRAINT `FK_HISTORY_TESTCASE` FOREIGN KEY (`TESTCASE_ID`) REFERENCES `testorbit_test_cases` (`TESTCASE_ID`),
  CONSTRAINT `FK_HISTORY_TESTER` FOREIGN KEY (`TESTER_ID`) REFERENCES `testorbit_user_registration` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `testorbit_modules`
--

DROP TABLE IF EXISTS `testorbit_modules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `testorbit_modules` (
  `MODULE_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `MODULE_NAME` varchar(50) NOT NULL,
  `MODULE_DESC` varchar(200) DEFAULT NULL,
  `PROJECT_MANAGER_ID` bigint(20) DEFAULT NULL,
  `TEST_MANAGER_ID` bigint(20) DEFAULT NULL,
  `MODULE_STATUS` varchar(10) NOT NULL,
  `CREAT_DATE` date DEFAULT NULL,
  `END_DATE` date DEFAULT NULL,
  `START_DATE` date DEFAULT NULL,
  PRIMARY KEY (`MODULE_ID`),
  KEY `FK_PROJ_MNGR` (`PROJECT_MANAGER_ID`),
  KEY `FK_TEST_MNGR` (`TEST_MANAGER_ID`),
  CONSTRAINT `FK_PROJ_MNGR` FOREIGN KEY (`PROJECT_MANAGER_ID`) REFERENCES `testorbit_user_registration` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `testorbit_tasks`
--

DROP TABLE IF EXISTS `testorbit_tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `testorbit_tasks` (
  `TASK_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TASK_DESC` varchar(200) DEFAULT NULL,
  `MODULE_ID` bigint(20) DEFAULT NULL,
  `TEST_MANAGER_ID` bigint(20) DEFAULT NULL,
  `TASK_STATUS` varchar(10) NOT NULL,
  `CREAT_DATE` date DEFAULT NULL,
  `END_DATE` date DEFAULT NULL,
  `START_DATE` date DEFAULT NULL,
  PRIMARY KEY (`TASK_ID`),
  KEY `FK_TASK_TEST_MNGR` (`TEST_MANAGER_ID`),
  KEY `FK_TASK_MOD` (`MODULE_ID`),
  CONSTRAINT `FK_TASK_MOD` FOREIGN KEY (`MODULE_ID`) REFERENCES `testorbit_modules` (`MODULE_ID`),
  CONSTRAINT `FK_TASK_TEST_MNGR` FOREIGN KEY (`TEST_MANAGER_ID`) REFERENCES `testorbit_user_registration` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `testorbit_tasks_to_testers`
--

DROP TABLE IF EXISTS `testorbit_tasks_to_testers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `testorbit_tasks_to_testers` (
  `TASK_TESTER_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TASK_ID` bigint(20) NOT NULL,
  `TESTER_ID` bigint(20) NOT NULL,
  `START_DATE` date DEFAULT NULL,
  PRIMARY KEY (`TASK_TESTER_ID`),
  KEY `FK_TASKTES_TESTER` (`TESTER_ID`),
  KEY `FK_TASKTES_TASK` (`TASK_ID`),
  CONSTRAINT `FK_TASKTES_TASK` FOREIGN KEY (`TASK_ID`) REFERENCES `testorbit_tasks` (`TASK_ID`),
  CONSTRAINT `FK_TASKTES_TESTER` FOREIGN KEY (`TESTER_ID`) REFERENCES `testorbit_user_registration` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `testorbit_test_cases`
--

DROP TABLE IF EXISTS `testorbit_test_cases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `testorbit_test_cases` (
  `TESTCASE_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TESTCASE_INPUT` varchar(500) DEFAULT NULL,
  `TESTCASE_OUTPUT` varchar(500) DEFAULT NULL,
  `TESTCASE_COMMENTS` varchar(500) DEFAULT NULL,
  `TASK_ID` bigint(20) DEFAULT NULL,
  `TESTER_ID` bigint(20) DEFAULT NULL,
  `TESTCASE_STATUS` char(1) NOT NULL,
  `CREAT_DATE` date DEFAULT NULL,
  `END_DATE` date DEFAULT NULL,
  PRIMARY KEY (`TESTCASE_ID`),
  KEY `FK_TESTCASE_TESTER` (`TESTER_ID`),
  KEY `FK_TASK_TESTCASE` (`TASK_ID`),
  CONSTRAINT `FK_TASK_TESTCASE` FOREIGN KEY (`TASK_ID`) REFERENCES `testorbit_tasks` (`TASK_ID`),
  CONSTRAINT `FK_TESTCASE_TESTER` FOREIGN KEY (`TESTER_ID`) REFERENCES `testorbit_user_registration` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `testorbit_user_registration`
--

DROP TABLE IF EXISTS `testorbit_user_registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `testorbit_user_registration` (
  `USER_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `EMPLOYEE_ID` varchar(50) NOT NULL,
  `FIRST_NAME` varchar(50) NOT NULL,
  `LAST_NAME` varchar(50) DEFAULT NULL,
  `E_MAIL` varchar(50) NOT NULL,
  `CONTACT_NO` varchar(12) DEFAULT NULL,
  `USER_PASSWORD` varchar(20) NOT NULL,
  `USER_STATUS` char(1) NOT NULL,
  `CREAT_DATE` date DEFAULT NULL,
  `MODIFY_DATE` date DEFAULT NULL,
  `ROLE_ID` int(11) DEFAULT NULL,
  `MANAGER_ID` bigint(20) DEFAULT NULL,
  `SUPER_MANAGER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  KEY `FK_RoleId` (`ROLE_ID`),
  CONSTRAINT `FK_RoleId` FOREIGN KEY (`ROLE_ID`) REFERENCES `testorbit_user_roles` (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `testorbit_user_resource_mapping`
--

DROP TABLE IF EXISTS `testorbit_user_resource_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `testorbit_user_resource_mapping` (
  `MAPPING_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_ID_MANAGER` bigint(20) DEFAULT NULL,
  `USER_ID_RESOURCE` bigint(20) DEFAULT NULL,
  `APPROVED_STATUS` char(1) NOT NULL,
  PRIMARY KEY (`MAPPING_ID`),
  KEY `FK_USR_MNGR` (`USER_ID_MANAGER`),
  KEY `FK_USR_RCRC` (`USER_ID_RESOURCE`),
  CONSTRAINT `FK_USR_MNGR` FOREIGN KEY (`USER_ID_MANAGER`) REFERENCES `testorbit_user_registration` (`USER_ID`),
  CONSTRAINT `FK_USR_RCRC` FOREIGN KEY (`USER_ID_RESOURCE`) REFERENCES `testorbit_user_registration` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `testorbit_user_roles`
--

DROP TABLE IF EXISTS `testorbit_user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `testorbit_user_roles` (
  `ROLE_ID` int(20) NOT NULL,
  `ROLE_NAME` varchar(50) NOT NULL,
  `ROLE_DESC` varchar(50) DEFAULT NULL,
  `ROLE_STATUS` char(1) NOT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'testorbit'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-12  3:03:35
