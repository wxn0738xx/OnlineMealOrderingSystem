CREATE DATABASE OnlineMealOrderingDB
USE OnlineMealOrderingDB
--create tables
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager`(
 `manager_id` int NOT NULL AUTO_INCREMENT,
 `name` varchar(30) NOT NULL,
 `username` varchar(30) NOT NULL UNIQUE,
 `password` varchar(20) NOT NULL,
 PRIMARY KEY (manager_id)   
)
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`(
 `menu_id` int NOT NULL AUTO_INCREMENT,
 `resturant_name` varchar(30) NOT NULL,
 `manager_id` int NOT null,
 PRIMARY KEY (menu_id),
 FOREIGN KEY (manager_id) REFERENCES manager(manager_id)
)
DROP TABLE IF EXISTS `dish`;
CREATE TABLE `dish`(
    `dish_id` int NOT NULL AUTO_INCREMENT,
    `menu_id` int NOT NULL,
    `price` float NOT NULL,
    `name` varchar(40) NOT NULL,
    `description` text,
    `picture` text,
    PRIMARY KEY (dish_id),
    FOREIGN KEY (menu_id) REFERENCES menu(menu_id)
)

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`(
 `customer_id` int NOT NULL AUTO_INCREMENT,
 `name` varchar(30) NOT NULL,
 `username` varchar(30) NOT NULL UNIQUE,
 `password` varchar(20) NOT NULL,
 `phone_number` varchar(15) NOT NULL,
 PRIMARY KEY(customer_id)
)
DROP TABLE IF EXISTS `meal_order`;
CREATE TABLE `meal_order`(
   `order_id` int NOT NULL AUTO_INCREMENT,
   `create_time` datetime NOT NULL,
   `address` text,
   `total_price` float,
   `customer_id` int NOT NULL,
   `menu_id` int,
   PRIMARY KEY (order_id),
   FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
   FOREIGN KEY (menu_id) REFERENCES menu(menu_id)
)

CREATE TABLE `order_dish`(
    `order_id` int NOT NULL,
    `dish_id` int NOT NULL,
    `qty` int,
    FOREIGN KEY (order_id) REFERENCES meal_order(order_id),
    FOREIGN KEY (dish_id) REFERENCES dish(dish_id),
    PRIMARY KEY (order_id, dish_id)
)
