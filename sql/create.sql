CREATE DATABASE  IF NOT EXISTS `mydb`
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;
USE `mydb`;

drop database if exists mydb;
create database if not exists mydb;

DROP TABLE IF EXISTS `mydb`.`Tariffs`;
CREATE TABLE IF NOT EXISTS `mydb`.`Tariffs` (
  `id_tariffs` INT NOT NULL AUTO_INCREMENT,
  `receiving_speed` INT NULL,
  `transfer_speed` INT NULL,
  `price` INT NULL,
  PRIMARY KEY (`id_tariffs`))
ENGINE = InnoDB;

DROP TABLE IF EXISTS `mydb`.`Langs`;
CREATE TABLE IF NOT EXISTS `mydb`.`Langs` (
  `lang` VARCHAR(2) NOT NULL,
  `lname` VARCHAR(25) NULL,
  PRIMARY KEY (`lang`))
ENGINE = InnoDB;

DROP TABLE IF EXISTS `mydb`.`tTariffs`;
CREATE TABLE IF NOT EXISTS `mydb`.`tTariffs` (
  `lang` VARCHAR(2) NOT NULL,
  `id_tariffs` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(250) NULL,
  PRIMARY KEY (`lang`, `id_tariffs`),
  INDEX `id_tariffs_idx` (`id_tariffs` ASC),
  CONSTRAINT `lang`
    FOREIGN KEY (`lang`)
    REFERENCES `mydb`.`Langs` (`lang`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_tariffs`
    FOREIGN KEY (`id_tariffs`)
    REFERENCES `mydb`.`Tariffs` (`id_tariffs`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

DROP TABLE IF EXISTS `mydb`.`Profiles`;
CREATE TABLE IF NOT EXISTS `mydb`.`Profiles` (
  `id_profiles` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL,
  `second_name` VARCHAR(45) NULL,
  `passport` VARCHAR(45) NULL,
  `id_tariffs` INT NULL,
  `balance` DECIMAL(15,2) NULL,
  `register_date` DATE NULL,
  `login` VARCHAR(45) NOT NULL UNIQUE,
  `pass` VARCHAR(45) NOT NULL,
  `role` ENUM('admin', 'client') NULL,
  PRIMARY KEY (`id_profiles`),
  INDEX `tarif_idx` (`id_tariffs` ASC),
  CONSTRAINT `tarif`
    FOREIGN KEY (`id_tariffs`)
    REFERENCES `mydb`.`Tariffs` (`id_tariffs`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

drop table if exists `mydb`.`Transactions`;
CREATE TABLE IF NOT EXISTS `mydb`.`Transactions` (
  `id_transactions` INT NOT NULL AUTO_INCREMENT,
  `amount` DECIMAL(15,2) NULL,
  `date` DATE NULL,
  `id_profiles` INT NULL,
  PRIMARY KEY (`id_transactions`),
  INDEX `id_profiles_idx` (`id_profiles` ASC),
  CONSTRAINT `id_profiles`
    FOREIGN KEY (`id_profiles`)
    REFERENCES `mydb`.`Profiles` (`id_profiles`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
