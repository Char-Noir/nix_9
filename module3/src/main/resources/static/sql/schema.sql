CREATE DATABASE  IF NOT EXISTS `module_3` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `module_3`;
-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: module_3
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
                           `idaccount` int unsigned NOT NULL AUTO_INCREMENT,
                           `balance` float unsigned NOT NULL,
                           `idclient` int unsigned NOT NULL,
                           PRIMARY KEY (`idaccount`),
                           UNIQUE KEY `idaccount_UNIQUE` (`idaccount`),
                           KEY `idclient_idx` (`idclient`),
                           CONSTRAINT `FK2q2bplauea985753mbd70x7gp` FOREIGN KEY (`idclient`) REFERENCES `client` (`idclient`),
                           CONSTRAINT `idclient` FOREIGN KEY (`idclient`) REFERENCES `client` (`idclient`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
                          `idclient` int unsigned NOT NULL AUTO_INCREMENT,
                          `email` varchar(45) NOT NULL,
                          PRIMARY KEY (`idclient`),
                          UNIQUE KEY `idclient_UNIQUE` (`idclient`),
                          UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `operation`
--

DROP TABLE IF EXISTS `operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operation` (
                             `idoperation` int unsigned NOT NULL AUTO_INCREMENT,
                             `incomeid` int unsigned NOT NULL,
                             `outcomeid` int unsigned NOT NULL,
                             `sum` float unsigned NOT NULL,
                             `purpose` varchar(45) NOT NULL,
                             `datetime` timestamp NOT NULL,
                             PRIMARY KEY (`idoperation`),
                             UNIQUE KEY `idoperation_UNIQUE` (`idoperation`),
                             KEY `incodeid_idx` (`incomeid`),
                             KEY `outcomeid_idx` (`outcomeid`),
                             CONSTRAINT `FK1enhpunma41fmg9hku27kpogu` FOREIGN KEY (`incomeid`) REFERENCES `account` (`idaccount`),
                             CONSTRAINT `FK82dyfjjpxp4oscsf28596neye` FOREIGN KEY (`outcomeid`) REFERENCES `account` (`idaccount`),
                             CONSTRAINT `incodeid` FOREIGN KEY (`incomeid`) REFERENCES `account` (`idaccount`),
                             CONSTRAINT `outcomeid` FOREIGN KEY (`outcomeid`) REFERENCES `account` (`idaccount`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'module_3'
--

--
-- Dumping routines for database 'module_3'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-28 15:21:36
