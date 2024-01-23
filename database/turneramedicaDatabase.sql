-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: turneramedica
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.28-MariaDB

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
-- Table structure for table `consultas`
--

DROP TABLE IF EXISTS `consultas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consultas` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `medico` int(10) unsigned NOT NULL,
  `paciente` int(10) unsigned DEFAULT NULL,
  `consultorio` varchar(45) NOT NULL,
  `precio` int(10) unsigned NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `medico` (`medico`),
  KEY `paciente` (`paciente`),
  KEY `consultorio` (`consultorio`),
  CONSTRAINT `consultorio` FOREIGN KEY (`consultorio`) REFERENCES `consultorios` (`nombre`) ON UPDATE CASCADE,
  CONSTRAINT `medico` FOREIGN KEY (`medico`) REFERENCES `medicos` (`dni`) ON UPDATE CASCADE,
  CONSTRAINT `paciente` FOREIGN KEY (`paciente`) REFERENCES `pacientes` (`dni`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consultas`
--

LOCK TABLES `consultas` WRITE;
/*!40000 ALTER TABLE `consultas` DISABLE KEYS */;
INSERT INTO `consultas` VALUES (1,'2024-01-24','08:00:00',77777777,12345678,'Hospital Italiano de Buenos Aires',125,'1');
/*!40000 ALTER TABLE `consultas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consultorios`
--

DROP TABLE IF EXISTS `consultorios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consultorios` (
  `nombre` varchar(45) NOT NULL,
  `activo` int(11) NOT NULL,
  PRIMARY KEY (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consultorios`
--

LOCK TABLES `consultorios` WRITE;
/*!40000 ALTER TABLE `consultorios` DISABLE KEYS */;
INSERT INTO `consultorios` VALUES ('Carniceria Dock Sud',1),('Centro de Partos de la CGT',1),('Centro Odontologico Butcher',1),('Desarmadero de Loros',1),('Hospital Aleman',1),('Hospital Italiano de Buenos Aires',1),('Sanatorio de la Trinidad',1);
/*!40000 ALTER TABLE `consultorios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consultorios_medicos`
--

DROP TABLE IF EXISTS `consultorios_medicos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consultorios_medicos` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_consultorio` varchar(45) NOT NULL,
  `dni_medico` int(10) unsigned NOT NULL,
  `dia` varchar(45) NOT NULL,
  `entrada` time NOT NULL,
  `salida` time NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_consultorio` (`id_consultorio`),
  KEY `dni_medico` (`dni_medico`),
  CONSTRAINT `dni_medico` FOREIGN KEY (`dni_medico`) REFERENCES `medicos` (`dni`) ON UPDATE CASCADE,
  CONSTRAINT `id_consultorio` FOREIGN KEY (`id_consultorio`) REFERENCES `consultorios` (`nombre`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consultorios_medicos`
--

LOCK TABLES `consultorios_medicos` WRITE;
/*!40000 ALTER TABLE `consultorios_medicos` DISABLE KEYS */;
INSERT INTO `consultorios_medicos` VALUES (6,'Centro de Partos de la CGT',12345987,'Lunes','09:00:00','18:00:00'),(7,'Centro de Partos de la CGT',12345987,'Martes','09:00:00','18:00:00'),(8,'Centro de Partos de la CGT',12345987,'Miercoles','09:00:00','18:00:00'),(9,'Sanatorio de la Trinidad',12345987,'Jueves','09:00:00','18:00:00'),(10,'Sanatorio de la Trinidad',12345987,'Viernes','09:00:00','18:00:00'),(11,'Desarmadero de Loros',23456766,'Lunes','05:00:00','23:00:00'),(12,'Desarmadero de Loros',23456766,'Martes','06:00:00','08:00:00'),(13,'Hospital Italiano de Buenos Aires',23456766,'Miercoles','14:00:00','21:00:00'),(14,'Hospital Italiano de Buenos Aires',23456766,'Jueves','14:00:00','21:00:00'),(15,'Hospital Aleman',23456766,'Viernes','14:00:00','21:00:00'),(16,'Sanatorio de la Trinidad',99999999,'Lunes','10:00:00','18:00:00'),(17,'Sanatorio de la Trinidad',99999999,'Martes','10:00:00','18:00:00'),(18,'Sanatorio de la Trinidad',99999999,'Miercoles','10:00:00','18:00:00'),(19,'Hospital Italiano de Buenos Aires',99999999,'Jueves','14:00:00','21:00:00'),(20,'Hospital Italiano de Buenos Aires',99999999,'Viernes','10:00:00','14:00:00'),(21,'Centro de Partos de la CGT',88888888,'Viernes','22:00:00','23:00:00'),(22,'Centro de Partos de la CGT',88888888,'Sabado','00:00:00','23:00:00'),(23,'Hospital Italiano de Buenos Aires',77777777,'Lunes','08:00:00','17:00:00'),(24,'Hospital Aleman',77777777,'Martes','08:00:00','14:00:00'),(25,'Hospital Italiano de Buenos Aires',77777777,'Miercoles','08:00:00','17:00:00'),(26,'Hospital Italiano de Buenos Aires',77777777,'Jueves','08:00:00','17:00:00'),(27,'Sanatorio de la Trinidad',77777777,'Viernes','10:00:00','16:00:00'),(30,'Centro Odontologico Butcher',33333333,'Lunes','17:00:00','23:00:00'),(31,'Centro Odontologico Butcher',33333333,'Martes','17:00:00','23:00:00'),(32,'Centro Odontologico Butcher',33333333,'Miercoles','17:00:00','23:00:00'),(33,'Centro Odontologico Butcher',33333333,'Jueves','17:00:00','23:00:00'),(34,'Centro Odontologico Butcher',33333333,'Viernes','17:00:00','23:00:00'),(35,'Centro Odontologico Butcher',33333333,'Sabado','00:00:00','23:00:00');
/*!40000 ALTER TABLE `consultorios_medicos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicos`
--

DROP TABLE IF EXISTS `medicos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medicos` (
  `dni` int(10) unsigned NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `honorarios` int(10) unsigned NOT NULL,
  `obrasocial` varchar(45) DEFAULT NULL,
  `activo` int(11) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicos`
--

LOCK TABLES `medicos` WRITE;
/*!40000 ALTER TABLE `medicos` DISABLE KEYS */;
INSERT INTO `medicos` VALUES (12345987,'Norberto','Gimenez',120,'Obra Social de Personal Doméstico',1,'gimenez123'),(23456766,'John','Jones',230,'Galeno',1,'jones123'),(33333333,'Jorge','Macana',80,'Unión Personal',1,'macana123'),(77777777,'Mary','Heinse',250,'Hospital Italiano',1,'heinse123'),(88888888,'Jorge','Porcel',800,'Jerárquicos Salud',1,'porcel123'),(99999999,'Lucrecia','Smith',200,'Swiss Medical',1,'smith123');
/*!40000 ALTER TABLE `medicos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pacientes`
--

DROP TABLE IF EXISTS `pacientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pacientes` (
  `dni` int(10) unsigned NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `nacimiento` date NOT NULL,
  `obrasocial` varchar(45) DEFAULT NULL,
  `sexo` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pacientes`
--

LOCK TABLES `pacientes` WRITE;
/*!40000 ALTER TABLE `pacientes` DISABLE KEYS */;
INSERT INTO `pacientes` VALUES (12345678,'Peter','Capusotto','1960-01-01','Hospital Italiano','hombre','Capusotto123');
/*!40000 ALTER TABLE `pacientes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-22 18:31:47
