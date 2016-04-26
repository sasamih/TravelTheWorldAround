-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.46-0ubuntu0.14.04.2 - (Ubuntu)
-- Server OS:                    debian-linux-gnu
-- HeidiSQL Version:             9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for traveldb
CREATE DATABASE IF NOT EXISTS `traveldb` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `traveldb`;


-- Dumping structure for table traveldb.ALBUM
CREATE TABLE IF NOT EXISTS `ALBUM` (
  `idAlbuma` int(11) NOT NULL AUTO_INCREMENT,
  `imeAutora` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `nazivAlbuma` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idPutopisa` int(11) DEFAULT NULL,
  PRIMARY KEY (`idAlbuma`),
  KEY `FK_ALBUM_KORISNIK` (`imeAutora`),
  KEY `FK_ALBUM_PUTOPIS` (`idPutopisa`),
  CONSTRAINT `FK_ALBUM_PUTOPIS` FOREIGN KEY (`idPutopisa`) REFERENCES `PUTOPIS` (`idPutopisa`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_ALBUM_KORISNIK` FOREIGN KEY (`imeAutora`) REFERENCES `KORISNIK` (`korisnickoIme`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table traveldb.ALBUM: ~0 rows (approximately)
/*!40000 ALTER TABLE `ALBUM` DISABLE KEYS */;
INSERT INTO `ALBUM` (`idAlbuma`, `imeAutora`, `nazivAlbuma`, `idPutopisa`) VALUES
	(1, 'sasa93mih', 'Album', 1);
/*!40000 ALTER TABLE `ALBUM` ENABLE KEYS */;


-- Dumping structure for table traveldb.KLJUCNE_RIJECI
CREATE TABLE IF NOT EXISTS `KLJUCNE_RIJECI` (
  `idKljucneRijeci` int(11) NOT NULL AUTO_INCREMENT,
  `Tekst` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PUTOPIS_idPutopis` int(11) NOT NULL,
  PRIMARY KEY (`idKljucneRijeci`),
  KEY `fk_KLJUCNE_RIJECI_PUTOPIS1_idx` (`PUTOPIS_idPutopis`),
  CONSTRAINT `fk_KLJUCNE_RIJECI_PUTOPIS1` FOREIGN KEY (`PUTOPIS_idPutopis`) REFERENCES `PUTOPIS` (`idPutopisa`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table traveldb.KLJUCNE_RIJECI: ~0 rows (approximately)
/*!40000 ALTER TABLE `KLJUCNE_RIJECI` DISABLE KEYS */;
INSERT INTO `KLJUCNE_RIJECI` (`idKljucneRijeci`, `Tekst`, `PUTOPIS_idPutopis`) VALUES
	(1, 'Svila', 1),
	(2, ' Azija', 1),
	(3, 'Putem svile', 1);
/*!40000 ALTER TABLE `KLJUCNE_RIJECI` ENABLE KEYS */;


-- Dumping structure for table traveldb.KOMENTAR_PUTOPIS
CREATE TABLE IF NOT EXISTS `KOMENTAR_PUTOPIS` (
  `idKomentara` int(11) NOT NULL AUTO_INCREMENT,
  `imeAutora` varchar(45) COLLATE utf8_unicode_ci DEFAULT '0',
  `PUTOPIS_idPutopis` int(11) NOT NULL,
  `tekstKomentara` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idKomentara`),
  KEY `fk_KOMENTAR_PUTOPIS_idx` (`PUTOPIS_idPutopis`),
  KEY `FK_KOMENTAR_PUTOPIS_KORISNIK` (`imeAutora`),
  CONSTRAINT `fk_KOMENTAR_PUTOPIS` FOREIGN KEY (`PUTOPIS_idPutopis`) REFERENCES `PUTOPIS` (`idPutopisa`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_KOMENTAR_PUTOPIS_KORISNIK` FOREIGN KEY (`imeAutora`) REFERENCES `KORISNIK` (`korisnickoIme`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table traveldb.KOMENTAR_PUTOPIS: ~0 rows (approximately)
/*!40000 ALTER TABLE `KOMENTAR_PUTOPIS` DISABLE KEYS */;
INSERT INTO `KOMENTAR_PUTOPIS` (`idKomentara`, `imeAutora`, `PUTOPIS_idPutopis`, `tekstKomentara`) VALUES
	(1, 'marijamarija', 1, 'Jako dobro napisano.');
/*!40000 ALTER TABLE `KOMENTAR_PUTOPIS` ENABLE KEYS */;


-- Dumping structure for table traveldb.KOMENTAR_SLIKA
CREATE TABLE IF NOT EXISTS `KOMENTAR_SLIKA` (
  `idKomentara` int(11) NOT NULL AUTO_INCREMENT,
  `SLIKA_idSlike` int(11) NOT NULL,
  `imeAutora` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tekstKomentara` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idKomentara`),
  KEY `fk_KOMENTAR_SLIKA_SLIKA1_idx` (`SLIKA_idSlike`),
  KEY `FK_KOMENTAR_SLIKA_KORISNIK` (`imeAutora`),
  CONSTRAINT `FK_KOMENTAR_SLIKA_KORISNIK` FOREIGN KEY (`imeAutora`) REFERENCES `KORISNIK` (`korisnickoIme`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_KOMENTAR_SLIKA_SLIKA1` FOREIGN KEY (`SLIKA_idSlike`) REFERENCES `SLIKA` (`idSlike`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table traveldb.KOMENTAR_SLIKA: ~0 rows (approximately)
/*!40000 ALTER TABLE `KOMENTAR_SLIKA` DISABLE KEYS */;
/*!40000 ALTER TABLE `KOMENTAR_SLIKA` ENABLE KEYS */;


-- Dumping structure for table traveldb.KONTAKT
CREATE TABLE IF NOT EXISTS `KONTAKT` (
  `korisnikIme` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `kontaktIme` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`korisnikIme`,`kontaktIme`),
  KEY `FK_kontakt_ime` (`kontaktIme`),
  CONSTRAINT `FK_kontakt_ime` FOREIGN KEY (`kontaktIme`) REFERENCES `KORISNIK` (`korisnickoIme`),
  CONSTRAINT `FK_korisnik_ime` FOREIGN KEY (`korisnikIme`) REFERENCES `KORISNIK` (`korisnickoIme`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table traveldb.KONTAKT: ~0 rows (approximately)
/*!40000 ALTER TABLE `KONTAKT` DISABLE KEYS */;
INSERT INTO `KONTAKT` (`korisnikIme`, `kontaktIme`) VALUES
	('marijamarija', 'sasa93mih');
/*!40000 ALTER TABLE `KONTAKT` ENABLE KEYS */;


-- Dumping structure for table traveldb.KORISNIK
CREATE TABLE IF NOT EXISTS `KORISNIK` (
  `ime` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `korisnickoIme` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `lozinka` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `prezime` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `eMail` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `kratkaBiografija` varchar(450) COLLATE utf8_unicode_ci NOT NULL,
  `datumRodjenja` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `korisnickaGrupa` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `statusKorisnik` int(11) DEFAULT NULL,
  PRIMARY KEY (`korisnickoIme`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table traveldb.KORISNIK: ~0 rows (approximately)
/*!40000 ALTER TABLE `KORISNIK` DISABLE KEYS */;
INSERT INTO `KORISNIK` (`ime`, `korisnickoIme`, `lozinka`, `prezime`, `eMail`, `kratkaBiografija`, `datumRodjenja`, `korisnickaGrupa`, `statusKorisnik`) VALUES
	('Admin', 'adminadmin', 'f6fdffe48c908deb0f4c3bd36c032e72', 'Admin', 'admin@travel.com', 'Admin', '21.2.1990.', 'administrator', 1),
	('Marija', 'marijamarija', '03804efb3bb797c50e4d4f60b5418575', 'Ivanovic', 'marija@travel.com', 'Ja sam Marija', '23.2.1993.', 'korisnik', 1),
	('Sasa', 'sasa93mih', 'ed30db6a5eed10daed2ba0b18d3a7d8e', 'Mihajlica', 'sasa@travel.com', 'Ja sam Sasa', '29.5.1993.', 'korisnik', 1),
	('Sasa', 'sasamih93', 'ed30db6a5eed10daed2ba0b18d3a7d8e', 'Mihajlica', 'sasa@travel.com', 'Ja sam Sasa', '29.5.1993.', 'korisnik', 1),
	('Nebojsa', 'sonesone', '92723f72afa43fe2ba79515103cf9078', 'Markovic', 'nebojsa@travel.com', 'Ja sam Nebojsa', '21.2.1990.', 'korisnik', 1);
/*!40000 ALTER TABLE `KORISNIK` ENABLE KEYS */;


-- Dumping structure for table traveldb.OCJENA_PUTOPIS
CREATE TABLE IF NOT EXISTS `OCJENA_PUTOPIS` (
  `idOcjene` int(11) NOT NULL AUTO_INCREMENT,
  `PUTOPIS_idPutopis` int(11) NOT NULL,
  `ocjena` int(11) DEFAULT NULL,
  `korisnickoIme` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`idOcjene`),
  KEY `fk_OCJENA_PUTOPIS1_idx` (`PUTOPIS_idPutopis`),
  KEY `fk_OCJENA_PUTOPIS2` (`korisnickoIme`),
  CONSTRAINT `fk_OCJENA_PUTOPIS1` FOREIGN KEY (`PUTOPIS_idPutopis`) REFERENCES `PUTOPIS` (`idPutopisa`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_OCJENA_PUTOPIS2` FOREIGN KEY (`korisnickoIme`) REFERENCES `KORISNIK` (`korisnickoIme`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table traveldb.OCJENA_PUTOPIS: ~0 rows (approximately)
/*!40000 ALTER TABLE `OCJENA_PUTOPIS` DISABLE KEYS */;
INSERT INTO `OCJENA_PUTOPIS` (`idOcjene`, `PUTOPIS_idPutopis`, `ocjena`, `korisnickoIme`) VALUES
	(1, 1, 5, 'sasa93mih'),
	(2, 1, 4, 'marijamarija');
/*!40000 ALTER TABLE `OCJENA_PUTOPIS` ENABLE KEYS */;


-- Dumping structure for table traveldb.OCJENA_SLIKA
CREATE TABLE IF NOT EXISTS `OCJENA_SLIKA` (
  `idOcjene` int(11) NOT NULL AUTO_INCREMENT,
  `SLIKA_idSlike` int(11) NOT NULL,
  `ocjena` int(11) DEFAULT NULL,
  `korisnickoIme` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idOcjene`),
  KEY `fk_OCJENA_SLIKA_SLIKA1_idx` (`SLIKA_idSlike`),
  KEY `FK_OCJENA_SLIKA_KORISNIK` (`korisnickoIme`),
  CONSTRAINT `FK_OCJENA_SLIKA_KORISNIK` FOREIGN KEY (`korisnickoIme`) REFERENCES `KORISNIK` (`korisnickoIme`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_OCJENA_SLIKA_SLIKA1` FOREIGN KEY (`SLIKA_idSlike`) REFERENCES `SLIKA` (`idSlike`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table traveldb.OCJENA_SLIKA: ~0 rows (approximately)
/*!40000 ALTER TABLE `OCJENA_SLIKA` DISABLE KEYS */;
INSERT INTO `OCJENA_SLIKA` (`idOcjene`, `SLIKA_idSlike`, `ocjena`, `korisnickoIme`) VALUES
	(1, 1, 4, 'sasa93mih');
/*!40000 ALTER TABLE `OCJENA_SLIKA` ENABLE KEYS */;


-- Dumping structure for table traveldb.PORUKA
CREATE TABLE IF NOT EXISTS `PORUKA` (
  `idPoruke` int(11) NOT NULL AUTO_INCREMENT,
  `posiljalac` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `primalac` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `tekstPoruke` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `statusProcitana` int(11) NOT NULL,
  `vrijemeSlanja` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idPoruke`),
  KEY `FK_posiljalac_ime` (`posiljalac`),
  KEY `FK_primalac_ime` (`primalac`),
  CONSTRAINT `FK_posiljalac_ime` FOREIGN KEY (`posiljalac`) REFERENCES `KORISNIK` (`korisnickoIme`),
  CONSTRAINT `FK_primalac_ime` FOREIGN KEY (`primalac`) REFERENCES `KORISNIK` (`korisnickoIme`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table traveldb.PORUKA: ~0 rows (approximately)
/*!40000 ALTER TABLE `PORUKA` DISABLE KEYS */;
INSERT INTO `PORUKA` (`idPoruke`, `posiljalac`, `primalac`, `tekstPoruke`, `statusProcitana`, `vrijemeSlanja`) VALUES
	(1, 'adminadmin', 'marijamarija', 'Vas nalog je odobren', 1, '26.4.2016.'),
	(2, 'adminadmin', 'sasamih93', 'Vas nalog je odobren', 0, '26.4.2016.'),
	(3, 'adminadmin', 'sonesone', 'Vas nalog je odobren', 0, '26.4.2016.'),
	(4, 'adminadmin', 'sasa93mih', 'Vas nalog je odobren', 1, '26.4.2016.'),
	(5, 'adminadmin', 'sasa93mih', 'Vas putopis je odobren', 1, '26.4.2016.'),
	(6, 'marijamarija', 'sasa93mih', 'Cao Sasa', 1, '26.4.2016.');
/*!40000 ALTER TABLE `PORUKA` ENABLE KEYS */;


-- Dumping structure for table traveldb.PUTOPIS
CREATE TABLE IF NOT EXISTS `PUTOPIS` (
  `idPutopisa` int(11) NOT NULL AUTO_INCREMENT,
  `nazivPutopisa` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `datumObjavljivanja` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `podaciOMjestu` varchar(90) COLLATE utf8_unicode_ci DEFAULT NULL,
  `putanja` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `imeAutora` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `statusPutopis` int(11) DEFAULT NULL,
  `prosjecnaOcjena` double DEFAULT NULL,
  `brojDjeljenja` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPutopisa`),
  KEY `fk_PUTOPIS_KORISNIK1_idx` (`imeAutora`),
  CONSTRAINT `fk_PUTOPIS_KORISNIK1` FOREIGN KEY (`imeAutora`) REFERENCES `KORISNIK` (`korisnickoIme`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table traveldb.PUTOPIS: ~0 rows (approximately)
/*!40000 ALTER TABLE `PUTOPIS` DISABLE KEYS */;
INSERT INTO `PUTOPIS` (`idPutopisa`, `nazivPutopisa`, `datumObjavljivanja`, `podaciOMjestu`, `putanja`, `imeAutora`, `statusPutopis`, `prosjecnaOcjena`, `brojDjeljenja`) VALUES
	(1, 'Putem svile', '26.4.2016.', 'Istocna Azija', '/WEB-INF/putopisi/Putem svile.txt', 'sasa93mih', 1, 4.5, NULL);
/*!40000 ALTER TABLE `PUTOPIS` ENABLE KEYS */;


-- Dumping structure for table traveldb.SLIKA
CREATE TABLE IF NOT EXISTS `SLIKA` (
  `idSlike` int(11) NOT NULL AUTO_INCREMENT,
  `imeAutora` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `idAlbuma` int(11) DEFAULT NULL,
  `putanjaSlike` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `statusSlika` int(11) DEFAULT NULL,
  PRIMARY KEY (`idSlike`),
  KEY `fk_SLIKA_KORISNIK1_idx` (`imeAutora`),
  KEY `FK_SLIKA_ALBUM` (`idAlbuma`),
  CONSTRAINT `FK_SLIKA_ALBUM` FOREIGN KEY (`idAlbuma`) REFERENCES `ALBUM` (`idAlbuma`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_SLIKA_KORISNIK1` FOREIGN KEY (`imeAutora`) REFERENCES `KORISNIK` (`korisnickoIme`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table traveldb.SLIKA: ~0 rows (approximately)
/*!40000 ALTER TABLE `SLIKA` DISABLE KEYS */;
INSERT INTO `SLIKA` (`idSlike`, `imeAutora`, `idAlbuma`, `putanjaSlike`, `statusSlika`) VALUES
	(1, 'sasa93mih', 1, '/WEB-INF/slike/img_1.jpg', 0);
/*!40000 ALTER TABLE `SLIKA` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
