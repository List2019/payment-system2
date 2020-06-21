-- MySQL dump 10.13  Distrib 8.0.14, for Win64 (x86_64)
--
-- Host: localhost    Database: epam
-- ------------------------------------------------------
-- Server version	8.0.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `credit_card`
--

DROP TABLE IF EXISTS `credit_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `credit_card` (
  `number_card` int(11) NOT NULL,
  `id_users` int(11) DEFAULT NULL,
  `balance` decimal(10,2) NOT NULL,
  `block` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`number_card`),
  KEY `id_client_idx` (`id_users`),
  CONSTRAINT `id_client` FOREIGN KEY (`id_users`) REFERENCES `users` (`id_users`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credit_card`
--

LOCK TABLES `credit_card` WRITE;
/*!40000 ALTER TABLE `credit_card` DISABLE KEYS */;
INSERT INTO `credit_card` VALUES (111,22,10.00,0),(123,29,0.00,0),(222,23,0.00,0),(333,24,0.00,1),(444,25,0.00,0),(555,26,0.00,0),(666,19,42.00,0),(777,18,21.00,0),(789,18,99.00,0),(888,27,0.00,0),(999,22,2.00,0);
/*!40000 ALTER TABLE `credit_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `id_users` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `number_card` bigint(16) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `login` varchar(45) DEFAULT NULL,
  `role` varchar(45) DEFAULT 'user',
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_users`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (18,'Александр','Матвеев',777,'123','sasha','admin','vrnvr@bk.ru'),(19,'Иван','Иванов',666,'321','ivan','user','abc@mail.ru'),(22,'Анна ','Семакина',111,'0000','anna','user','anna@mail.ru'),(23,'Вадим ','Петров',222,'0000','vadim','user','vadim@mail.ru'),(24,'Евгения','Старкова',333,'0000','egunia','user','egunia@mail.ru'),(25,'Тихон','Радонежский',444,'0000','tihon','user','tihon@mail.ru'),(26,'Василий ','Пупкин',555,'0000','vasyia','user','vasyia@mail.ru'),(27,'Феофан','Мефодьевич',888,'0000','feofan','user','feofan@mail.ru'),(28,'Ян ','Штольц',999,'0000','yan','user','yan@mail.ru'),(29,'Иван ','Иванов',123,'0000','ivan','user','ivan@mail.ru'),(30,'bvcx','gfds',432,'12345','adds','user','adds@mail.ru');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'epam'
--

--
-- Dumping routines for database 'epam'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-06 21:41:38
