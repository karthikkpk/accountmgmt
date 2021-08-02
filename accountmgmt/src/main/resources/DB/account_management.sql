/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.5.14 : Database - account_management
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`account_management` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `account_management`;

/*Table structure for table `tbl_account` */

DROP TABLE IF EXISTS `tbl_account`;

CREATE TABLE `tbl_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `acc_name` varchar(50) COLLATE utf8_bin NOT NULL,
  `acc_number` int(12) NOT NULL,
  `address` varchar(1000) COLLATE utf8_bin NOT NULL,
  `phone` varchar(15) COLLATE utf8_bin NOT NULL,
  `acc_type` int(1) NOT NULL,
  `open_date` date NOT NULL,
  `balance_amt` double NOT NULL,
  `created_date` date NOT NULL,
  `modified_date` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `acc_name` (`acc_name`,`acc_number`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `tbl_account` */

insert  into `tbl_account`(`id`,`acc_name`,`acc_number`,`address`,`phone`,`acc_type`,`open_date`,`balance_amt`,`created_date`,`modified_date`) values 
(28,'Karthik P',1234567890,'Kovilambakkam, Chennai','1234567891',1,'2021-08-02',1000,'2021-08-02','2021-08-02'),
(29,'Deva K',1234567891,'Medavakkam, Chennai','1234567891',1,'2021-08-02',1500,'2021-08-02','2021-08-02'),
(30,'Kavin R',1234567892,'Tharamani, Chennai','1234567891',0,'2021-08-02',1200,'2021-08-02','2021-08-02');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
