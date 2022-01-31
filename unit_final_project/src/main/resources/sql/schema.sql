CREATE DATABASE  IF NOT EXISTS `clinic_innodb` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `clinic_innodb`;
-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: clinic_innodb
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
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
                            `id_category` int unsigned NOT NULL AUTO_INCREMENT,
                            `category_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                            PRIMARY KEY (`id_category`),
                            UNIQUE KEY `id Categories_UNIQUE` (`id_category`),
                            UNIQUE KEY `Category_UNIQUE` (`category_name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `days`
--

DROP TABLE IF EXISTS `days`;
/*!50001 DROP VIEW IF EXISTS `days`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `days` AS SELECT
                                   1 AS `id`,
                                   1 AS `days`*/;
SET character_set_client = @saved_cs_client;
--
-- Table structure for table `specializations`
--

DROP TABLE IF EXISTS `specializations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `specializations` (
                                   `id_specializations` int unsigned NOT NULL AUTO_INCREMENT,
                                   `specialization_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                                   PRIMARY KEY (`id_specializations`),
                                   UNIQUE KEY `specialization_name_UNIQUE` (`specialization_name`),
                                   UNIQUE KEY `id_specializations_UNIQUE` (`id_specializations`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `id_user` int NOT NULL AUTO_INCREMENT,
                        `email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
                        `password` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
                        `role_type` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
                        PRIMARY KEY (`id_user`),
                        UNIQUE KEY `user_login_uindex` (`email`),
                        UNIQUE KEY `user_id_user_uindex` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=412 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor` (
                          `id_doctor` int unsigned NOT NULL AUTO_INCREMENT,
                          `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                          `assessment_of_certification` float unsigned NOT NULL,
                          `doctor_note` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                          `date_of_certification` date NOT NULL,
                          `id_category` int unsigned NOT NULL,
                          `id_specializations` int unsigned NOT NULL,
                          `id_user` int NOT NULL,
                          PRIMARY KEY (`id_doctor`),
                          UNIQUE KEY `id_doctor_UNIQUE` (`id_doctor`),
                          UNIQUE KEY `doctor_id_user_uindex` (`id_user`),
                          KEY `d_spec_idx` (`id_specializations`),
                          KEY `d_cat_idx` (`id_category`),
                          KEY `doctor_name_category` (`name`(15),`id_category`,`id_specializations`),
                          CONSTRAINT `d_cat` FOREIGN KEY (`id_category`) REFERENCES `category` (`id_category`) ON DELETE RESTRICT ON UPDATE CASCADE,
                          CONSTRAINT `d_spec` FOREIGN KEY (`id_specializations`) REFERENCES `specializations` (`id_specializations`) ON DELETE RESTRICT ON UPDATE CASCADE,
                          CONSTRAINT `doctor__user_fk` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=212 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `doctor_category_i_fx` BEFORE INSERT ON `doctor` FOR EACH ROW begin
    declare cab_co int;
    select count(*) into cab_co from category where id_category = new.id_category;
    if cab_co = 0 then SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Category  No not found in categories table'; end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `doctor_spec_i_fx` BEFORE INSERT ON `doctor` FOR EACH ROW begin
    declare cab_co int;
    select count(*) into cab_co from specializations where id_specializations = new.id_specializations;
    if cab_co = 0 then SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Specialization  No not found in specializations table'; end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;


--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient` (
                           `id_patient` int unsigned NOT NULL AUTO_INCREMENT,
                           `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                           `date_of_birth` date NOT NULL,
                           `phone_number` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                           `user_documents` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                           `patient_float` float unsigned NOT NULL DEFAULT '0',
                           `id_user` int NOT NULL,
                           PRIMARY KEY (`id_patient`),
                           UNIQUE KEY `id_patient_UNIQUE` (`id_patient`),
                           UNIQUE KEY `patient_id_user_uindex` (`id_user`),
                           KEY `patient_date_name` (`name`,`date_of_birth`),
                           FULLTEXT KEY `patient_F_name_docs` (`user_documents`),
                           CONSTRAINT `patient_user__fk` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=226 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `priqoms`
--

DROP TABLE IF EXISTS `priqoms`;
/*!50001 DROP VIEW IF EXISTS `priqoms`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `priqoms` AS SELECT
                                      1 AS `id`,
                                      1 AS `cat`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `reception`
--

DROP TABLE IF EXISTS `reception`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reception` (
                             `id_reception` int unsigned NOT NULL AUTO_INCREMENT,
                             `date_of_reception` date NOT NULL,
                             `reception_time` time NOT NULL,
                             `review_comment` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                             `reception_price` float unsigned NOT NULL,
                             `id_doctor` int unsigned NOT NULL,
                             `id_patient` int unsigned NOT NULL,
                             `doctorId` bigint DEFAULT NULL,
                             `patientId` bigint DEFAULT NULL,
                             `datetime` timestamp NOT NULL,
                             PRIMARY KEY (`id_reception`),
                             UNIQUE KEY `id_reception_UNIQUE` (`id_reception`),
                             KEY `rec_pat_fk_idx` (`id_patient`),
                             KEY `rec_doc_fk_idx` (`id_doctor`),
                             KEY `reception_date_doctor` (`date_of_reception`,`id_doctor`),
                             FULLTEXT KEY `reception_F_review` (`review_comment`),
                             CONSTRAINT `rec_doc_fk` FOREIGN KEY (`id_patient`) REFERENCES `patient` (`id_patient`) ON DELETE RESTRICT ON UPDATE CASCADE,
                             CONSTRAINT `rec_pat_fk` FOREIGN KEY (`id_doctor`) REFERENCES `doctor` (`id_doctor`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=244 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `reception_patient_i_fx` BEFORE INSERT ON `reception` FOR EACH ROW begin
    declare cab_co int;
    select count(*) into cab_co from patient where id_patient = new.id_patient ;
    if cab_co = 0 then SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Patient  No not found in patients table'; end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `reception_doctor_i_fx` BEFORE INSERT ON `reception` FOR EACH ROW begin
    declare cab_co int;
    select count(*) into cab_co from doctor where id_doctor = new.id_doctor ;
    if cab_co = 0 then SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Doctor  No not found in doctors table'; end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `recept_cab_doc` BEFORE INSERT ON `reception` FOR EACH ROW begin
    declare amb_id int;
    select time_to_sec(new.reception_time) into amb_id;
    if MOD(amb_id,900) !=0 then SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Time need to be multiple 15 minutes'; end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;


--
-- Dumping events for database 'clinic_innodb'
--

--
-- Dumping routines for database 'clinic_innodb'
--
/*!50003 DROP FUNCTION IF EXISTS `cabinet_doctor_count` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `cabinet_doctor_count`(id int) RETURNS int
    READS SQL DATA
BEGIN
    declare b int;
    select count(*) into b from doctor d left join reception r using(id_doctor) where id_doctor = id;
    RETURN b;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `calcCabByAm` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `calcCabByAm`(id int) RETURNS int
    READS SQL DATA
BEGIN
    declare b int;
    select count(*) into b from ambulatory a left join cabinet using(id_ambulatory);
    RETURN b;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `calcDoctorByAm` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `calcDoctorByAm`(id int) RETURNS int
    READS SQL DATA
BEGIN
    declare b int;
    select count(distinct id_doctor) into b from ambulatory a left join cabinet using(id_ambulatory) left join cabinet_doctor using(id_cabinet);
    RETURN b;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `calcMedName` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `calcMedName`(spec varchar(100)) RETURNS int
    READS SQL DATA
BEGIN
    declare b int;
    select count(*) into b from direction where name_operation = spec;
    RETURN b;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `calcPatientRecW` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `calcPatientRecW`(id int) RETURNS int
    READS SQL DATA
BEGIN
    declare b int;
    select count(*) into b from receptions r left join doctor_patient dp using(id_patient) where dp.id_doctor != r.id_doctor and end_date > curdate();
    RETURN b;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `concat_cabinet_name` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `concat_cabinet_name`(id int) RETURNS text CHARSET utf8mb3 COLLATE utf8_unicode_ci
    READS SQL DATA
BEGIN
    declare b text;
    select concat(number,address ) into b from cabinet left join ambulatory using(id_ambulatory) where id_cabinet = id;
    RETURN b;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `countDoctorBySpec` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `countDoctorBySpec`(spec varchar(100)) RETURNS int
    READS SQL DATA
BEGIN
    declare b int;
    select count(*) into b from doctor left join specializations s using(id_specializations) where s.specialization_name = spec group by id_specializations;
    RETURN b;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `patientYears` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `patientYears`(id int) RETURNS int
    READS SQL DATA
BEGIN
    declare b int;
    select TIMESTAMPDIFF(YEAR, date_of_birth, CURDATE()) into b from patient p;
    RETURN b;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `cabinetIsForRecept` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `cabinetIsForRecept`(id int)
BEGIN
    declare b int;
    select id_cabinet_type into b from cabinet where id_cabinet =id;
    case b
        when (select id_cabinet_type from cabinet_type where cabinet_Type_name = 'operational') then
            select "when it needed";
        when (select id_cabinet_type from cabinet_type where cabinet_Type_name = 'receive') then
            select "yes";
        when (select id_cabinet_type from cabinet_type where cabinet_Type_name = 'special') then
            select "no";
        end case;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `daysOfWorkLoop` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `daysOfWorkLoop`()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE b int;
    declare pri int;
    DECLARE cur1 CURSOR FOR SELECT id, `cat` FROM `priqoms`;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    create temporary table `days` (id int not null, days int);
    create or replace algorithm = temptable view `priqoms` as select id_cabinet as id, time_to_sec(time(TIMEDIFF(end_moment,start_moment)))/900  as `cat` from cabinet_doctor;
    OPEN cur1;
    read_loop: LOOP
        FETCH cur1 INTO b, pri;
        IF done THEN
            LEAVE read_loop;
        END IF;
        IF (select count(*) from days where id = b) =1  THEN
            update days set days = days+pri where id = b;
        ELSE
            INSERT INTO days VALUES (b,pri);
        END IF;
    END LOOP;
    CLOSE cur1;
    select * from `days` ;
    drop temporary table `days`;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `daysOfWorkRepeat` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `daysOfWorkRepeat`()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE b int;
    DECLARE cur1 CURSOR FOR SELECT id FROM cabinet_doctor;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    create temporary table `days` (id int not null, days int not null);
    OPEN cur1;
    read_loop: Repeat
        FETCH cur1 INTO b;
        IF (select count(*) from days where id = b) =1  THEN
            update days set days = days+1;
        ELSE
            INSERT INTO days VALUES (b,1);
        END IF;
    until done
        END repeat ;
    CLOSE cur1;
    select * from `days` ;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `daysOfWorkWhile` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `daysOfWorkWhile`()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE b int;
    DECLARE cur1 CURSOR FOR SELECT id FROM cabinet_doctor;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    create temporary table `days` (id int not null, days int not null);
    OPEN cur1;
    read_loop: while not done do
            FETCH cur1 INTO b;
            IF (select count(*) from days where id = b) =1  THEN
                update days set days = days+1;
            ELSE
                INSERT INTO days VALUES (b,1);
            END IF;
        END while ;
    CLOSE cur1;
    select * from `days` ;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getDoctorVability` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getDoctorVability`(id int)
BEGIN
    declare b int;
    select count(*) into b from doctor_patient where id_doctor =id;
    if b=0 then
        select "Doctor is new";
    elseif b<2000 then
        select "Doctor takes patients";
    else
        select "Doctor is taken";
    end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getMedOpNames` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getMedOpNames`()
BEGIN
    select group_concat(DISTINCT name_operation) from direction d  left join reception r using(id_reception) where d.price + r.reception_price>100;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getSpecializations` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getSpecializations`()
BEGIN
    select group_concat(DISTINCT specialization_name) from doctor d  left join specializations s using(id_specializations) ;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_price_patient` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_price_patient`()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE b int;
    declare pr float(5,2);
    DECLARE cur1 CURSOR FOR SELECT id_patient, price FROM reception;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    create temporary table `prices` (id int not null, pri float(5,2) not null);
    OPEN cur1;
    read_loop: while not done do
            FETCH cur1 INTO b, pr;
            IF (select count(*) from prices where id = b) =1  THEN
                update days set pri = pri+pr;
            ELSE
                INSERT INTO days VALUES (b,pr);
            END IF;
        END while ;
    CLOSE cur1;
    select * from `prices` ;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `isDoctorNeedCertification` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `isDoctorNeedCertification`(id int)
BEGIN
    declare b date;
    select date_of_certification into b from doctor where id_doctor =id;
    case
        when (date_add(b,interval 5 year)<date_sub(now(),interval 6 month)) then
            select "no";
        when (date_add(b,interval 5 year)>date_sub(now(),interval 6 month) &&date_add(b,interval 5 year)>now()) then
            select "yes";
        when (date_add(b,interval 5 year)>now()) then
            select "its to late";
        end case;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `showFullRecept` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `showFullRecept`()
BEGIN
    select id_reception, sum(d.price) + r.reception_price as price, review_comment, id_doctor, id_patient, concat(date_of_reception, reception_time) as `datetime` from reception r left join direction d using(id_reception) group by id_reception;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `trans` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `trans`(dateR date, timeR time, idD int, idP int)
BEGIN
    start transaction;
    set autocommit = 0;
    if not exists(select * from cabinet_doctor where id_doctor=idD and dateR between date(start_moment) and date(end_moment) and timeR between time(start_moment) and time(end_moment)) then
        insert into cabinet_doctor values(4005,idD,ADDTIME(CONVERT(dateR,datetime),timeR),DATE_ADD(ADDTIME(CONVERT(dateR,datetime),timeR), INTERVAL 15 MINUTE) );
        if(select time_to_sec(time(TIMEDIFF(end_moment,start_moment)))/900 >160 from cabinet_doctor where id_doctor=idD) then
            rollback;
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'doctor needs rest';
        end if;
    end if;
    commit;
    insert into reception values(default,dateR,timeR,'',0,idD,idP);
    commit;
    set autocommit = 1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `days`
--

/*!50001 DROP VIEW IF EXISTS `days`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
    /*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
    /*!50001 VIEW `days` AS select `cabinet`.`id_cabinet` AS `id`,0 AS `days` from `cabinet` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `priqoms`
--

/*!50001 DROP VIEW IF EXISTS `priqoms`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
    /*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
    /*!50001 VIEW `priqoms` AS select `cabinet_doctor`.`id_cabinet` AS `id`,(time_to_sec(cast(timediff(`cabinet_doctor`.`end_moment`,`cabinet_doctor`.`start_moment`) as time)) / 900) AS `cat` from `cabinet_doctor` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-31  5:27:54
