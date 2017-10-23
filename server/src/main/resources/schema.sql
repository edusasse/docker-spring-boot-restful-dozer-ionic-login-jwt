-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema app_dev
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `app_dev` ;

-- -----------------------------------------------------
-- Schema app_dev
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `app_dev` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `app_dev` ;

-- -----------------------------------------------------
-- Table `app_dev`.`Pessoa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `app_dev`.`Pessoa` ;

CREATE TABLE IF NOT EXISTS `app_dev`.`Pessoa` (
  `id_pessoa` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `id_tipo_pessoa` VARCHAR(1) NOT NULL COMMENT '',
  `ds_email` VARCHAR(70) NOT NULL COMMENT '',
  `ds_email_faturamento` VARCHAR(70) NULL COMMENT '',
  `ds_observacao` VARCHAR(255) NULL COMMENT '',
  `dh_cadastro` DATETIME NOT NULL COMMENT '',
  `dh_ultima_alteracao` DATETIME NULL COMMENT '',
  `nr_versao` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id_pessoa`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `app_dev`.`Cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `app_dev`.`Cliente` ;

CREATE TABLE IF NOT EXISTS `app_dev`.`Cliente` (
  `id_cliente` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `cd_situacao` CHAR(1) NOT NULL COMMENT '',
  `id_pessoa` INT NOT NULL COMMENT '',
  `ds_observacao` VARCHAR(255) NULL COMMENT '',
  `dh_cadastro` DATETIME NOT NULL COMMENT '',
  `dh_ultima_alteracao` DATETIME NULL COMMENT '',
  `nr_versao` INT NOT NULL COMMENT '',
  `fl_visto` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '',
  PRIMARY KEY (`id_cliente`)  COMMENT '',
  INDEX `fk_Cliente_Pessoa1_idx` (`id_pessoa` ASC)  COMMENT '',
  CONSTRAINT `fk_Cliente_Pessoa1`
    FOREIGN KEY (`id_pessoa`)
    REFERENCES `app_dev`.`Pessoa` (`id_pessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `app_dev`.`Pessoa_Fisica`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `app_dev`.`Pessoa_Fisica` ;

CREATE TABLE IF NOT EXISTS `app_dev`.`Pessoa_Fisica` (
  `id_pessoa` INT NOT NULL COMMENT '',
  `nm_primeiro_nome` VARCHAR(45) NOT NULL COMMENT '',
  `nm_segundo_nome` VARCHAR(255) NOT NULL COMMENT '',
  `cd_sexo` CHAR(1) NOT NULL COMMENT '',
  `dt_nascimento` DATETIME NOT NULL COMMENT '',
  `ds_documento` VARCHAR(30) NOT NULL COMMENT '',
  `ds_tipo_documento` VARCHAR(15) NOT NULL COMMENT '',
  PRIMARY KEY (`id_pessoa`)  COMMENT '',
  INDEX `fk_Pessoa_Fisica_Pessoa1_idx` (`id_pessoa` ASC)  COMMENT '',
  CONSTRAINT `fk_Pessoa_Fisica_Pessoa1`
    FOREIGN KEY (`id_pessoa`)
    REFERENCES `app_dev`.`Pessoa` (`id_pessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `app_dev`.`Usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `app_dev`.`Usuario` ;

CREATE TABLE IF NOT EXISTS `app_dev`.`Usuario` (
  `id_usuario` BIGINT NOT NULL AUTO_INCREMENT COMMENT '',
  `ds_apelido` VARCHAR(45) NOT NULL COMMENT '',
  `ds_senha` VARCHAR(255) NOT NULL COMMENT '',
  `fl_ativo` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '',
  `id_pessoa` INT NOT NULL COMMENT '',
  `ds_perfil` VARCHAR(20) NOT NULL DEFAULT 'CLIENTE' COMMENT '',
  `dt_ultimo_acesso` DATETIME NULL COMMENT '',
  `dh_ultima_alteracao` DATETIME NULL COMMENT '',
  `dh_cadastro` DATETIME NOT NULL COMMENT '',
  `nr_versao` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id_usuario`)  COMMENT '',
  INDEX `fk_Usuario_Pessoa1_idx` (`id_pessoa` ASC)  COMMENT '',
  UNIQUE INDEX `cod_pessoa_UNIQUE` (`id_pessoa` ASC)  COMMENT '',
  UNIQUE INDEX `des_apelido_UNIQUE` (`ds_apelido` ASC)  COMMENT '',
  CONSTRAINT `fk_Usuario_Pessoa1`
    FOREIGN KEY (`id_pessoa`)
    REFERENCES `app_dev`.`Pessoa_Fisica` (`id_pessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `app_dev`.`Pessoa_Juridica`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `app_dev`.`Pessoa_Juridica` ;

CREATE TABLE IF NOT EXISTS `app_dev`.`Pessoa_Juridica` (
  `id_pessoa` INT NOT NULL COMMENT '',
  `ds_razao_social` VARCHAR(70) NOT NULL COMMENT '',
  `nr_cnpj` DECIMAL(15) NULL COMMENT '',
  `ds_inscricao_estadual` VARCHAR(20) NULL COMMENT '',
  `ds_inscricao_municipal` VARCHAR(20) NULL COMMENT '',
  PRIMARY KEY (`id_pessoa`)  COMMENT '',
  INDEX `fk_Pessoa_Juridica_Pessoa1_idx` (`id_pessoa` ASC)  COMMENT '',
  CONSTRAINT `fk_Pessoa_Juridica_Pessoa1`
    FOREIGN KEY (`id_pessoa`)
    REFERENCES `app_dev`.`Pessoa` (`id_pessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `app_dev`.`Telefone`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `app_dev`.`Telefone` ;

CREATE TABLE IF NOT EXISTS `app_dev`.`Telefone` (
  `id_telefone` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `id_pessoa` INT NOT NULL COMMENT '',
  `nr_telefone` VARCHAR(30) NOT NULL COMMENT '',
  `ds_tipo_telefone` VARCHAR(20) NOT NULL COMMENT '',
  `nm_pessoa_contato` VARCHAR(45) NULL COMMENT '',
  `dh_cadastro` DATETIME NOT NULL COMMENT '',
  `dh_ultima_alteracao` DATETIME NULL COMMENT '',
  `nr_versao` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id_telefone`)  COMMENT '',
  INDEX `fk_Telefone_Pessoa1_idx` (`id_pessoa` ASC)  COMMENT '',
  CONSTRAINT `fk_Telefone_Pessoa1`
    FOREIGN KEY (`id_pessoa`)
    REFERENCES `app_dev`.`Pessoa` (`id_pessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `app_dev`.`Unidade_Federacao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `app_dev`.`Unidade_Federacao` ;

CREATE TABLE IF NOT EXISTS `app_dev`.`Unidade_Federacao` (
  `id_uf` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `ds_uf` VARCHAR(45) NOT NULL COMMENT '',
  `dh_cadastro` DATETIME NOT NULL COMMENT '',
  `dh_ultima_alteracao` DATETIME NULL COMMENT '',
  `nr_versao` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id_uf`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `app_dev`.`Cidade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `app_dev`.`Cidade` ;

CREATE TABLE IF NOT EXISTS `app_dev`.`Cidade` (
  `id_cidade` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `nm_cidade` VARCHAR(60) NOT NULL COMMENT '',
  `id_uf` INT NOT NULL COMMENT '',
  `dh_cadastro` DATETIME NOT NULL COMMENT '',
  `dh_ultima_alteracao` DATETIME NULL COMMENT '',
  `nr_versao` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id_cidade`)  COMMENT '',
  INDEX `fk_Cidade_Unidade_Federacao1_idx` (`id_uf` ASC)  COMMENT '',
  CONSTRAINT `fk_Cidade_Unidade_Federacao1`
    FOREIGN KEY (`id_uf`)
    REFERENCES `app_dev`.`Unidade_Federacao` (`id_uf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `app_dev`.`Endereco`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `app_dev`.`Endereco` ;

CREATE TABLE IF NOT EXISTS `app_dev`.`Endereco` (
  `id_endereco` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `id_pessoa` INT NOT NULL COMMENT '',
  `fl_tipo_endereco` VARCHAR(20) NOT NULL DEFAULT 'RESIDENCIAL' COMMENT '',
  `ds_cep` VARCHAR(8) NULL COMMENT '',
  `ds_logradouro` VARCHAR(70) NULL COMMENT '',
  `nr_logradouro` INT NULL COMMENT '',
  `ds_bairro` VARCHAR(60) NULL COMMENT '',
  `ds_complemento` VARCHAR(120) NULL COMMENT '',
  `id_cidade` INT NOT NULL COMMENT '',
  `dh_cadastro` DATETIME NOT NULL COMMENT '',
  `dh_ultima_alteracao` DATETIME NULL COMMENT '',
  `nr_versao` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id_endereco`)  COMMENT '',
  INDEX `fk_Endereco_Cidade1_idx` (`id_cidade` ASC)  COMMENT '',
  INDEX `fk_Endereco_Pessoa1_idx` (`id_pessoa` ASC)  COMMENT '',
  CONSTRAINT `fk_Endereco_Cidade1`
    FOREIGN KEY (`id_cidade`)
    REFERENCES `app_dev`.`Cidade` (`id_cidade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Endereco_Pessoa1`
    FOREIGN KEY (`id_pessoa`)
    REFERENCES `app_dev`.`Pessoa` (`id_pessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `app_dev`.`Parametro`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `app_dev`.`Parametro` ;

CREATE TABLE IF NOT EXISTS `app_dev`.`Parametro` (
  `id_parametro` BIGINT NOT NULL AUTO_INCREMENT COMMENT '',
  `ds_parametro` VARCHAR(255) NOT NULL COMMENT '',
  `vl_parametro` VARCHAR(255) NULL COMMENT '',
  `dh_cadastro` DATETIME NOT NULL COMMENT '',
  `dh_ultima_alteracao` DATETIME NULL COMMENT '',
  `nr_versao` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id_parametro`)  COMMENT '',
  UNIQUE INDEX `des_parametro_UNIQUE` (`ds_parametro` ASC)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `app_dev`.`Feriado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `app_dev`.`Feriado` ;

CREATE TABLE IF NOT EXISTS `app_dev`.`Feriado` (
  `id_feriado` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `dt_feriado` DATE NOT NULL COMMENT '',
  `dh_cadastro` DATETIME NOT NULL COMMENT '',
  `dh_ultima_alteracao` DATETIME NULL COMMENT '',
  `nr_versao` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id_feriado`)  COMMENT '')
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
