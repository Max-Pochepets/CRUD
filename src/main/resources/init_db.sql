CREATE SCHEMA `taxi` DEFAULT CHARACTER SET utf8 ;
CREATE TABLE `taxi`.`manufacturers` (
                                        `manufacturer_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
                                        `manufacturer_name` VARCHAR(225) NOT NULL,
                                        `manufacturer_country` VARCHAR(225) NOT NULL,
                                        `deleted` TINYINT NOT NULL DEFAULT 0,
                                        PRIMARY KEY (`manufacturer_id`));
CREATE TABLE `taxi`.`cars` (
                               `car_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
                               `manufacturer_id` BIGINT(11) NOT NULL,
                               `car_model` VARCHAR(225) NOT NULL,
                               `deleted` TINYINT NOT NULL DEFAULT 0,
                               PRIMARY KEY (`car_id`),
                               UNIQUE INDEX `id_UNIQUE` (`car_id` ASC) VISIBLE,
                               INDEX `cars_manufacturers_idx` (`manufacturer_id` ASC) VISIBLE,
                               CONSTRAINT `cars_manufacturers`
                                   FOREIGN KEY (`manufacturer_id`)
                                       REFERENCES `taxi`.`manufacturers` (`manufacturer_id`)
                                       ON DELETE NO ACTION
                                       ON UPDATE NO ACTION);

CREATE TABLE `taxi`.`drivers` (
                                  driver_id BIGINT(11) NOT NULL AUTO_INCREMENT,
                                  `driver_name` VARCHAR(225) NOT NULL,
                                  `driver_license_number` VARCHAR(225) NOT NULL,
                                  `deleted` TINYINT NOT NULL DEFAULT 0,
                                  PRIMARY KEY (driver_id),
                                  UNIQUE INDEX `id_UNIQUE` (driver_id ASC) VISIBLE,
                                  UNIQUE INDEX `license_number_UNIQUE` (`driver_license_number` ASC) VISIBLE);
CREATE TABLE `taxi`.`cars_drivers` (
                                       `driver_id` BIGINT(11) NOT NULL,
                                       `cars_id` BIGINT(11) NOT NULL,
                                       INDEX `cars_drivers_cars_idx` (`cars_id` ASC) VISIBLE,
                                       INDEX `cars_drivers_drivers_idx` (`driver_id` ASC) VISIBLE,
                                       CONSTRAINT `cars_drivers_cars`
                                           FOREIGN KEY (`cars_id`)
                                               REFERENCES `taxi`.`cars` (`car_id`)
                                               ON DELETE NO ACTION
                                               ON UPDATE NO ACTION,
                                       CONSTRAINT `cars_drivers_drivers`
                                           FOREIGN KEY (`driver_id`)
                                               REFERENCES `taxi`.`drivers` (driver_id)
                                               ON DELETE NO ACTION
                                               ON UPDATE NO ACTION);
ALTER TABLE `taxi`.`drivers`
    ADD COLUMN `driver_login` VARCHAR(225) NOT NULL AFTER `driver_license_number`,
    ADD COLUMN `driver_password` VARCHAR(225) NOT NULL AFTER `driver_login`,
    ADD UNIQUE INDEX `login_UNIQUE` (`driver_login` ASC) VISIBLE;
;
