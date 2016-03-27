-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.47-0ubuntu0.14.04.1 - (Ubuntu)
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


-- Dumping structure for table traveldb.KLJUCNE_RIJECI
CREATE TABLE IF NOT EXISTS `KLJUCNE_RIJECI` (
  `idKljucneRijeci` int(11) NOT NULL AUTO_INCREMENT,
  `Tekst` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PUTOPIS_idPutopis` int(11) NOT NULL,
  PRIMARY KEY (`idKljucneRijeci`),
  KEY `fk_KLJUCNE_RIJECI_PUTOPIS1_idx` (`PUTOPIS_idPutopis`),
  CONSTRAINT `fk_KLJUCNE_RIJECI_PUTOPIS1` FOREIGN KEY (`PUTOPIS_idPutopis`) REFERENCES `PUTOPIS` (`idPutopisa`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table traveldb.KLJUCNE_RIJECI: ~5 rows (approximately)
/*!40000 ALTER TABLE `KLJUCNE_RIJECI` DISABLE KEYS */;
INSERT INTO `KLJUCNE_RIJECI` (`idKljucneRijeci`, `Tekst`, `PUTOPIS_idPutopis`) VALUES
	(1, 'Milano', 1),
	(2, 'Italija', 1),
	(3, 'istorija', 1),
	(4, 'Italija', 2),
	(5, 'Venecija', 2);
/*!40000 ALTER TABLE `KLJUCNE_RIJECI` ENABLE KEYS */;


-- Dumping structure for table traveldb.KOMENTAR_PUTOPIS
CREATE TABLE IF NOT EXISTS `KOMENTAR_PUTOPIS` (
  `idKomentara` int(11) NOT NULL AUTO_INCREMENT,
  `PUTOPIS_idPutopis` int(11) NOT NULL,
  `tekstPutopisa` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idKomentara`),
  KEY `fk_KOMENTAR_PUTOPIS_idx` (`PUTOPIS_idPutopis`),
  CONSTRAINT `fk_KOMENTAR_PUTOPIS` FOREIGN KEY (`PUTOPIS_idPutopis`) REFERENCES `PUTOPIS` (`idPutopisa`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table traveldb.KOMENTAR_PUTOPIS: ~0 rows (approximately)
/*!40000 ALTER TABLE `KOMENTAR_PUTOPIS` DISABLE KEYS */;
/*!40000 ALTER TABLE `KOMENTAR_PUTOPIS` ENABLE KEYS */;


-- Dumping structure for table traveldb.KOMENTAR_SLIKA
CREATE TABLE IF NOT EXISTS `KOMENTAR_SLIKA` (
  `idKomentara` int(11) NOT NULL AUTO_INCREMENT,
  `SLIKA_idSlike` int(11) NOT NULL,
  PRIMARY KEY (`idKomentara`),
  KEY `fk_KOMENTAR_SLIKA_SLIKA1_idx` (`SLIKA_idSlike`),
  CONSTRAINT `fk_KOMENTAR_SLIKA_SLIKA1` FOREIGN KEY (`SLIKA_idSlike`) REFERENCES `SLIKA` (`idSlike`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table traveldb.KOMENTAR_SLIKA: ~0 rows (approximately)
/*!40000 ALTER TABLE `KOMENTAR_SLIKA` DISABLE KEYS */;
/*!40000 ALTER TABLE `KOMENTAR_SLIKA` ENABLE KEYS */;


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
  PRIMARY KEY (`korisnickoIme`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table traveldb.KORISNIK: ~4 rows (approximately)
/*!40000 ALTER TABLE `KORISNIK` DISABLE KEYS */;
INSERT INTO `KORISNIK` (`ime`, `korisnickoIme`, `lozinka`, `prezime`, `eMail`, `kratkaBiografija`, `datumRodjenja`, `korisnickaGrupa`) VALUES
	('Branka', 'branka', '123', 'Pekez', 'branka@branka.com', 'Iz Podrasnice', '18.9.1993', 'administrator'),
	('Dragan', 'gago', '04e0d737c9292f826b0a4a42bc22cf5f', 'Torbica', 'dragan@jovetorbica.com', 'Iz Krajine', '16.5.1961.', 'korisnik'),
	('Mladen', 'mladen', '123', 'Stupar', 'mladen@mladen.com', 'Iz Tisce', '15.7.1993.', 'administrator'),
	('Sasa', 'sasa', '123', 'Mihajlica', 'sasa@sasa.com', 'Iz Krsalja', '29.5.1993', 'administrator');
/*!40000 ALTER TABLE `KORISNIK` ENABLE KEYS */;


-- Dumping structure for table traveldb.OCJENA_PUTOPIS
CREATE TABLE IF NOT EXISTS `OCJENA_PUTOPIS` (
  `idOcjene` int(11) NOT NULL AUTO_INCREMENT,
  `PUTOPIS_idPutopis` int(11) NOT NULL,
  `ocjena` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idOcjene`),
  KEY `fk_OCJENA_PUTOPIS1_idx` (`PUTOPIS_idPutopis`),
  CONSTRAINT `fk_OCJENA_PUTOPIS1` FOREIGN KEY (`PUTOPIS_idPutopis`) REFERENCES `PUTOPIS` (`idPutopisa`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table traveldb.OCJENA_PUTOPIS: ~0 rows (approximately)
/*!40000 ALTER TABLE `OCJENA_PUTOPIS` DISABLE KEYS */;
/*!40000 ALTER TABLE `OCJENA_PUTOPIS` ENABLE KEYS */;


-- Dumping structure for table traveldb.OCJENA_SLIKA
CREATE TABLE IF NOT EXISTS `OCJENA_SLIKA` (
  `idSlike` int(11) NOT NULL AUTO_INCREMENT,
  `SLIKA_idSlike` int(11) NOT NULL,
  PRIMARY KEY (`idSlike`),
  KEY `fk_OCJENA_SLIKA_SLIKA1_idx` (`SLIKA_idSlike`),
  CONSTRAINT `fk_OCJENA_SLIKA_SLIKA1` FOREIGN KEY (`SLIKA_idSlike`) REFERENCES `SLIKA` (`idSlike`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table traveldb.OCJENA_SLIKA: ~0 rows (approximately)
/*!40000 ALTER TABLE `OCJENA_SLIKA` DISABLE KEYS */;
/*!40000 ALTER TABLE `OCJENA_SLIKA` ENABLE KEYS */;


-- Dumping structure for table traveldb.PUTOPIS
CREATE TABLE IF NOT EXISTS `PUTOPIS` (
  `idPutopisa` int(11) NOT NULL AUTO_INCREMENT,
  `nazivPutopisa` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `datumObjavljivanja` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `podaciOMjestu` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `putanja` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `imeAutora` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`idPutopisa`),
  KEY `fk_PUTOPIS_KORISNIK1_idx` (`imeAutora`),
  CONSTRAINT `fk_PUTOPIS_KORISNIK1` FOREIGN KEY (`imeAutora`) REFERENCES `KORISNIK` (`korisnickoIme`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table traveldb.PUTOPIS: ~2 rows (approximately)
/*!40000 ALTER TABLE `PUTOPIS` DISABLE KEYS */;
INSERT INTO `PUTOPIS` (`idPutopisa`, `nazivPutopisa`, `datumObjavljivanja`, `podaciOMjestu`, `putanja`, `imeAutora`) VALUES
	(1, 'Put u Italiju', '23.1.2016.', 'Milano', '/WEB-INF/putopisi/put_u_italiju.txt', 'sasa'),
	(2, 'Venecija - grad na vodi', '14.8.2015.', 'Venecija', '/WEB-INF/putopisi/venecija.txt', 'sasa'),
	(4, 'Krslje', NULL, NULL, '/WEB-INF/putopisi/Krslje.txt', 'gago');
/*!40000 ALTER TABLE `PUTOPIS` ENABLE KEYS */;


-- Dumping structure for table traveldb.SLIKA
CREATE TABLE IF NOT EXISTS `SLIKA` (
  `idSlike` int(11) NOT NULL AUTO_INCREMENT,
  `putanjaSlike` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `imeAutora` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`idSlike`),
  KEY `fk_SLIKA_KORISNIK1_idx` (`imeAutora`),
  CONSTRAINT `fk_SLIKA_KORISNIK1` FOREIGN KEY (`imeAutora`) REFERENCES `KORISNIK` (`korisnickoIme`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table traveldb.SLIKA: ~0 rows (approximately)
/*!40000 ALTER TABLE `SLIKA` DISABLE KEYS */;
/*!40000 ALTER TABLE `SLIKA` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
