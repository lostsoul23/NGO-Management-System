CREATE DATABASE NGOMS;
USE NGOMS;

CREATE TABLE `admin` (
  `user_id` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phoneno` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
);

CREATE TABLE `user` (
  `user_id` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phoneno` varchar(45) DEFAULT NULL,
  `residence` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ;

CREATE TABLE `item` (
  `item_id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL, 
  `rate` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`item_id`)
);

CREATE TABLE `student` (
  `student_id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL, 
  `age` int NOT NULL,
  `Class` int NOT NULL,
  `Gender` varchar(45) DEFAULT NULL,
  `School` varchar(45) DEFAULT NULL,
  `percentage` int NOT NULL,
  `parental_income` int NOT NULL,
  `Money` int NOT NULL,
  `Dress` int NOT NULL,
  `Books` int NOT NULL,
  `Shoes` int NOT NULL,
  `Stationery` int NOT NULL,
  `Bag` int NOT NULL,
  `donated_Money` int NOT NULL,
  `donated_Dress` varchar(45) DEFAULT NULL,
  `donated_Books` varchar(45) DEFAULT NULL,
  `donated_Shoes` varchar(45) DEFAULT NULL,
  `donated_Stationery` varchar(45) DEFAULT NULL,
  `donated_Bag` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
);

CREATE TABLE `student_sorted` LIKE `student`;

INSERT INTO `NGOMS`.`admin` (`user_id`, `name`, `username`, `password`, `email`, `phoneno`) VALUES ('1', 'John', 'john', 'admin', 'admin@gmail.com', '9876543210');
INSERT INTO `NGOMS`.`item` (`item_id`, `name`, `rate`, `quantity`) VALUES ('1', 'Money', '1', '0');
INSERT INTO `NGOMS`.`item` (`item_id`, `name`, `rate`, `quantity`) VALUES ('2', 'Dress', '700', '0');
INSERT INTO `NGOMS`.`item` (`item_id`, `name`, `rate`, `quantity`) VALUES ('3', 'Books', '1000', '0');
INSERT INTO `NGOMS`.`item` (`item_id`, `name`, `rate`, `quantity`) VALUES ('4', 'Shoes', '200', '0');
INSERT INTO `NGOMS`.`item` (`item_id`, `name`, `rate`, `quantity`) VALUES ('5', 'Stationery', '300', '0');
INSERT INTO `NGOMS`.`item` (`item_id`, `name`, `rate`, `quantity`) VALUES ('6', 'Bag', '100', '0');
