CREATE DATABASE IF NOT EXISTS MONEY_TRACKER;

USE MONEY_TRACKER;

CREATE TABLE IF NOT EXISTS MONEY_TRACKER(
ID INTEGER NOT NULL AUTO_INCREMENT,
DATE_OF_EXPENSE DATE NOT NULL,
AMOUNT DECIMAL(8,2) NOT NULL,
CURRENCY CHAR(3) NOT NULL,
NAME_OF_PRODUCT CHAR(200) NOT NULL, PRIMARY KEY (ID)
);