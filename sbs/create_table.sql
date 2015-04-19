CREATE DATABASE sbs_db;
USE sbs_db;
CREATE TABLE `sbs_db`.`sbs_userdata` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `userName` VARCHAR(45) NOT NULL,
  `scenarioId` INT NULL,
  `score` FLOAT NULL,
  `report` VARCHAR(1000) NULL,
  `debrief` VARCHAR(1000) NULL,
  PRIMARY KEY (`Id`));
