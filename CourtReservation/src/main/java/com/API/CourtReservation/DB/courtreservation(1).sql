-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Počítač: 127.0.0.1
-- Vytvořeno: Pon 07. čen 2021, 17:23
-- Verze serveru: 10.4.11-MariaDB
-- Verze PHP: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Databáze: `courtreservation`
--

-- --------------------------------------------------------

--
-- Struktura tabulky `courts`
--

CREATE TABLE IF NOT EXISTS `courts` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `surface` varchar(16) NOT NULL,
  `perMinutePrice` int(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

--
-- Vypisuji data pro tabulku `courts`
--

INSERT INTO `courts` (`ID`, `surface`, `perMinutePrice`) VALUES
(1, 'Grass', 20);

-- --------------------------------------------------------

--
-- Struktura tabulky `reservation`
--

CREATE TABLE IF NOT EXISTS `reservation` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CourtID` int(11) NOT NULL,
  `TimeInterval` int(255) NOT NULL,
  `GameType` tinyint(1) NOT NULL,
  `PhoneNumber` int(255) NOT NULL,
  `Surname` varchar(1024) NOT NULL,
  `Price` double DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_courts` (`CourtID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

--
-- Vypisuji data pro tabulku `reservation`
--

INSERT INTO `reservation` (`ID`, `CourtID`, `TimeInterval`, `GameType`, `PhoneNumber`, `Surname`, `Price`) VALUES
(3, 1, 480, 0, 123456789, 'Honza', 9600),
(4, 1, 480, 1, 123456789, 'Honza', 14400),
(5, 1, 480, 1, 123456789, 'Honza', 14400),
(6, 1, 1, 0, 123456789, 'Honza', 20),
(7, 1, 1, 0, 123456789, 'Honza', 20),
(10, 1, 78, 0, 789456323, 'Igi', 1560);

--
-- Omezení pro exportované tabulky
--

--
-- Omezení pro tabulku `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`CourtID`) REFERENCES `courts` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
