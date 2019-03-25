CREATE DATABASE  IF NOT EXISTS `weather_map`;
USE `weather_map`;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user_details`;

CREATE TABLE user_details(
 username VARCHAR(30) NOT NULL,
 email VARCHAR(30) NOT NULL,
 pass VARCHAR(50) NOT NULL,
 date_of_birth VARCHAR(50) NOT NULL
 );