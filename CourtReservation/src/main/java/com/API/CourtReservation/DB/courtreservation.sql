-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Počítač: 127.0.0.1
-- Vytvořeno: Sob 05. čen 2021, 00:04
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
CREATE DATABASE IF NOT EXISTS `courtreservation` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `courtreservation`;

-- --------------------------------------------------------

--
-- Struktura tabulky `courts`
--

CREATE TABLE IF NOT EXISTS `courts` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `surface` varchar(16) NOT NULL,
  `perMinutePrice` int(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktura tabulky `reservation`
--

CREATE TABLE IF NOT EXISTS `reservation` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CourtID` int(11) NOT NULL,
  `TimeInterval` int(255) NOT NULL,
  `GameType` tinyint(1) NOT NULL,
  `PhoneNumber` varchar(1024) NOT NULL,
  `Surname` varchar(1024) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_courts` (`CourtID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
