-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Dec 23, 2024 at 05:38 AM
-- Server version: 8.3.0
-- PHP Version: 8.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rental_application`
--

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
CREATE TABLE IF NOT EXISTS `customers` (
  `CustomerID` int NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(40) NOT NULL,
  `LastName` varchar(40) NOT NULL,
  `Email` varchar(140) NOT NULL,
  `Phone` varchar(20) NOT NULL,
  `Address` varchar(200) NOT NULL,
  PRIMARY KEY (`CustomerID`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`CustomerID`, `FirstName`, `LastName`, `Email`, `Phone`, `Address`) VALUES
(1, 'John', 'Doe', 'Address', 'Phone', 'Address'),
(2, 'Jane', 'Doe', 'Address', 'Phone', 'Address');

-- --------------------------------------------------------

--
-- Table structure for table `rentals`
--

DROP TABLE IF EXISTS `rentals`;
CREATE TABLE IF NOT EXISTS `rentals` (
  `RentalID` int NOT NULL AUTO_INCREMENT,
  `CustomerID` int DEFAULT NULL,
  `ToolID` int DEFAULT NULL,
  `RentalStartDate` varchar(30) DEFAULT NULL,
  `RentalEndDate` varchar(30) DEFAULT NULL,
  `TotalCost` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`RentalID`),
  KEY `CustomerID` (`CustomerID`),
  KEY `CarID` (`ToolID`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `rentals`
--

INSERT INTO `rentals` (`RentalID`, `CustomerID`, `ToolID`, `RentalStartDate`, `RentalEndDate`, `TotalCost`) VALUES
(1, 1, 1, '06/12/2024', '06/12/2024', '600'),
(4, 1, 4, '06/12/2024', '06/12/2024', '600'),
(6, 1, 2, 'date', 'date', '2500.0');

-- --------------------------------------------------------

--
-- Table structure for table `tools`
--

DROP TABLE IF EXISTS `tools`;
CREATE TABLE IF NOT EXISTS `tools` (
  `ToolID` int NOT NULL AUTO_INCREMENT,
  `Brand` varchar(40) NOT NULL,
  `Model` varchar(40) NOT NULL,
  `Year` varchar(10) NOT NULL,
  `Color` varchar(40) NOT NULL,
  `RentalRatePerDay` varchar(100) NOT NULL,
  `Availability` int NOT NULL,
  PRIMARY KEY (`ToolID`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tools`
--

INSERT INTO `tools` (`ToolID`, `Brand`, `Model`, `Year`, `Color`, `RentalRatePerDay`, `Availability`) VALUES
(1, 'Brand1', 'Model1', 'Year', 'Color', '600', 0),
(4, 'Brand2', 'Model2', 'Year', 'Color', '600', 0),
(5, 'Brand3', 'Model3', 'Year', 'Color', '600', 1);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
