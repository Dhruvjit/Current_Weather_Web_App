CREATE DATABASE  IF NOT EXISTS `weather_map`;
USE `weather_map`;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `weather`;

CREATE TABLE weather(
 city VARCHAR(30) NOT NULL,
 headline VARCHAR(30) NOT NULL,
 descriptions VARCHAR(50) NOT NULL,
 icon VARCHAR(50) NOT NULL,
 currentTemp VARCHAR(50) NOT NULL,
 minTemp VARCHAR(50) NOT NULL,
 maxTemp VARCHAR(50) NOT NULL,
 sunrise VARCHAR(50) NOT NULL,
 sunset VARCHAR(50) NOT NULL,
 unit VARCHAR(50) NOT NULL
 )ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;