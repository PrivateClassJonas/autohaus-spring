DROP SCHEMA IF EXISTS autohausdb_test;
CREATE SCHEMA autohausdb_test;

DROP TABLE IF EXISTS autohaus;
CREATE TABLE `autohaus` (
  `autohaus_id` int NOT NULL AUTO_INCREMENT,
  `autohaus_adresse` varchar(45) NOT NULL,
  `guid` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`autohaus_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4;
DROP TABLE IF EXISTS kfz;
CREATE TABLE `kfz` (
  `kfz_id` int NOT NULL AUTO_INCREMENT,
  `kfz_bezeichnung` varchar(45) DEFAULT NULL,
  `kfz_marke` varchar(45) DEFAULT NULL,
  `kfz_milage` int DEFAULT NULL,
  `autohaus_id` int NOT NULL,
  `guid` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`kfz_id`),
  UNIQUE KEY `guid` (`guid`),
  KEY `fk_kfz_id_autohaus1_idx` (`autohaus_id`),
  CONSTRAINT `fk_kfz_id_autohaus1` FOREIGN KEY (`autohaus_id`) REFERENCES `autohaus` (`autohaus_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;
