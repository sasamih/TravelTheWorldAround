-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.49-0ubuntu0.14.04.1 - (Ubuntu)
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
  PRIMARY KEY (`idAlbuma`),
  KEY `FK_ALBUM_KORISNIK` (`imeAutora`),
  CONSTRAINT `FK_ALBUM_KORISNIK` FOREIGN KEY (`imeAutora`) REFERENCES `KORISNIK` (`korisnickoIme`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table traveldb.ALBUM: ~1 rows (approximately)
/*!40000 ALTER TABLE `ALBUM` DISABLE KEYS */;
INSERT INTO `ALBUM` (`idAlbuma`, `imeAutora`, `nazivAlbuma`) VALUES
	(10, 'gago', 'Testni album');
/*!40000 ALTER TABLE `ALBUM` ENABLE KEYS */;


-- Dumping structure for table traveldb.KLJUCNE_RIJECI
CREATE TABLE IF NOT EXISTS `KLJUCNE_RIJECI` (
  `idKljucneRijeci` int(11) NOT NULL AUTO_INCREMENT,
  `Tekst` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PUTOPIS_idPutopis` int(11) NOT NULL,
  PRIMARY KEY (`idKljucneRijeci`),
  KEY `fk_KLJUCNE_RIJECI_PUTOPIS1_idx` (`PUTOPIS_idPutopis`),
  CONSTRAINT `fk_KLJUCNE_RIJECI_PUTOPIS1` FOREIGN KEY (`PUTOPIS_idPutopis`) REFERENCES `PUTOPIS` (`idPutopisa`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table traveldb.KLJUCNE_RIJECI: ~13 rows (approximately)
/*!40000 ALTER TABLE `KLJUCNE_RIJECI` DISABLE KEYS */;
INSERT INTO `KLJUCNE_RIJECI` (`idKljucneRijeci`, `Tekst`, `PUTOPIS_idPutopis`) VALUES
	(1, 'Milano', 1),
	(2, 'Italija', 1),
	(3, 'istorija', 1),
	(4, 'Italija', 2),
	(5, 'Venecija', 2),
	(6, 'Joja', 14),
	(7, 'Beograd', 14),
	(8, 'Split', 15),
	(9, 'kolegijum', 15),
	(10, 'U Splitu na kolegijumu', 15),
	(11, 'Grcka', 16),
	(12, 'Krf', 16),
	(13, 'Putovanje u Grcku', 16);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table traveldb.KOMENTAR_PUTOPIS: ~2 rows (approximately)
/*!40000 ALTER TABLE `KOMENTAR_PUTOPIS` DISABLE KEYS */;
INSERT INTO `KOMENTAR_PUTOPIS` (`idKomentara`, `imeAutora`, `PUTOPIS_idPutopis`, `tekstKomentara`) VALUES
	(1, 'gago', 1, 'Prvi komentar'),
	(2, 'djoko', 1, 'Drugi komentar');
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

-- Dumping data for table traveldb.KORISNIK: ~7 rows (approximately)
/*!40000 ALTER TABLE `KORISNIK` DISABLE KEYS */;
INSERT INTO `KORISNIK` (`ime`, `korisnickoIme`, `lozinka`, `prezime`, `eMail`, `kratkaBiografija`, `datumRodjenja`, `korisnickaGrupa`, `statusKorisnik`) VALUES
	('Admin', 'admin', '21232f297a57a5a743894a0e4a801fc3', 'Admin', 'admin@admin.com', 'Admin', '1.1.1950.', 'administrator', 1),
	('Nepoznato', 'boskic', 'bb06f375b88a7fb178292d6a7383f7f3', 'Boskic', 'boskic@arhivatori.com', 'Braco sredio posao', '21.4.1988.', 'korisnik', 0),
	('Branka', 'branka', '123', 'Pekez', 'branka@branka.com', 'Iz Podrasnice', '18.9.1993', 'administrator', 1),
	('Djordje', 'djoko', 'fac434c27191d7a5ec906a69d93aa7b3', 'Cvarkov', 'cvarkov@arhivatori.com', 'Glavni arhivator', '21.4.1943.', 'korisnik', 1),
	('Dragan', 'gago', '04e0d737c9292f826b0a4a42bc22cf5f', 'Torbica', 'dragan@jovetorbica.com', 'Iz Krajine', '16.5.1961.', 'korisnik', 1),
	('Mladen', 'mladen', '123', 'Stupar', 'mladen@mladen.com', 'Iz Tisce', '15.7.1993.', 'administrator', 1),
	('Sasa', 'sasa', '123', 'Mihajlica', 'sasa@sasa.com', 'Iz Krsalja', '29.5.1993', 'administrator', 1);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table traveldb.OCJENA_PUTOPIS: ~4 rows (approximately)
/*!40000 ALTER TABLE `OCJENA_PUTOPIS` DISABLE KEYS */;
INSERT INTO `OCJENA_PUTOPIS` (`idOcjene`, `PUTOPIS_idPutopis`, `ocjena`, `korisnickoIme`) VALUES
	(1, 1, 4, 'gago'),
	(2, 15, 4, 'gago'),
	(3, 1, 3, 'djoko'),
	(4, 15, 5, 'djoko');
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table traveldb.OCJENA_SLIKA: ~4 rows (approximately)
/*!40000 ALTER TABLE `OCJENA_SLIKA` DISABLE KEYS */;
INSERT INTO `OCJENA_SLIKA` (`idOcjene`, `SLIKA_idSlike`, `ocjena`, `korisnickoIme`) VALUES
	(2, 2, NULL, 'djoko'),
	(3, 3, 2, 'djoko'),
	(4, 2, 5, 'gago'),
	(5, 1, 4, 'gago');
/*!40000 ALTER TABLE `OCJENA_SLIKA` ENABLE KEYS */;


-- Dumping structure for table traveldb.PORUKA
CREATE TABLE IF NOT EXISTS `PORUKA` (
  `posiljalac` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `primalac` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `tekstPoruke` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `statusProcitana` int(11) NOT NULL,
  `vrijemeSlanja` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`posiljalac`,`primalac`),
  KEY `FK_primalac_ime` (`primalac`),
  CONSTRAINT `FK_posiljalac_ime` FOREIGN KEY (`posiljalac`) REFERENCES `KORISNIK` (`korisnickoIme`),
  CONSTRAINT `FK_primalac_ime` FOREIGN KEY (`primalac`) REFERENCES `KORISNIK` (`korisnickoIme`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table traveldb.PORUKA: ~0 rows (approximately)
/*!40000 ALTER TABLE `PORUKA` DISABLE KEYS */;
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
  PRIMARY KEY (`idPutopisa`),
  KEY `fk_PUTOPIS_KORISNIK1_idx` (`imeAutora`),
  CONSTRAINT `fk_PUTOPIS_KORISNIK1` FOREIGN KEY (`imeAutora`) REFERENCES `KORISNIK` (`korisnickoIme`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table traveldb.PUTOPIS: ~10 rows (approximately)
/*!40000 ALTER TABLE `PUTOPIS` DISABLE KEYS */;
INSERT INTO `PUTOPIS` (`idPutopisa`, `nazivPutopisa`, `datumObjavljivanja`, `podaciOMjestu`, `putanja`, `imeAutora`, `statusPutopis`, `prosjecnaOcjena`) VALUES
	(1, 'Put u Italiju', '23.1.2016.', 'Milano', '/WEB-INF/putopisi/put_u_italiju.txt', 'sasa', 1, 3.5),
	(2, 'Venecija - grad na vodi', '14.8.2015.', 'Venecija', '/WEB-INF/putopisi/venecija.txt', 'sasa', 1, NULL),
	(4, 'Krslje', NULL, NULL, '/WEB-INF/putopisi/Krslje.txt', 'gago', 1, NULL),
	(5, 'Na farmi kod babe', NULL, NULL, '/WEB-INF/putopisi/Na farmi kod babe.txt', 'djoko', 1, NULL),
	(9, 'U Majkic Japru po piletinu', '13.4.2016.', 'Majkic Japra', '/WEB-INF/putopisi/U Majkic Japru po piletinu.txt', 'gago', 0, NULL),
	(10, 'Kod tetka u Ofenbahu', '3.4.2016.', 'Ofenbah', '/WEB-INF/putopisi/Kod tetka u Ofenbahu.txt', 'gago', 1, NULL),
	(13, 'Sa koncerta Piju grupe', '3.4.2016.', 'Novi Sad', '/WEB-INF/putopisi/Sa koncerta Piju grupe.txt', 'djoko', 0, NULL),
	(14, 'Kod Joje u gostima', '3.4.2016.', 'Beograd', '/WEB-INF/putopisi/Kod Joje u gostima.txt', 'gago', 1, NULL),
	(15, 'U Splitu na kolegijumu', '5.4.2016.', 'Split', '/WEB-INF/putopisi/U Splitu na kolegijumu.txt', 'gago', 1, 4.5),
	(16, 'Putovanje u Grcku', '5.4.2016.', 'Krf', '/WEB-INF/putopisi/Putovanje u Grcku.txt', 'gago', 1, NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table traveldb.SLIKA: ~3 rows (approximately)
/*!40000 ALTER TABLE `SLIKA` DISABLE KEYS */;
INSERT INTO `SLIKA` (`idSlike`, `imeAutora`, `idAlbuma`, `putanjaSlike`, `statusSlika`) VALUES
	(1, 'gago', 10, '/WEB-INF/slike/Carina Nebula.jpg', 0),
	(2, 'gago', 10, '/WEB-INF/slike/clock.jpg', 0),
	(3, 'gago', 10, '/WEB-INF/slike/img_1.jpg', 0);
/*!40000 ALTER TABLE `SLIKA` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
