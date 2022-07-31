-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 18, 2022 at 09:59 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `shansbook`
--

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `CustomerID` int(11) NOT NULL,
  `Name` varchar(30) NOT NULL,
  `Phone` int(11) NOT NULL,
  `Address` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`CustomerID`, `Name`, `Phone`, `Address`) VALUES
(2, 'Noori', 4656, 'kky'),
(2002, 'Unicom', 222, 'batti'),
(2004, 'Jazeera', 9800, 'KKY-2');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `ProductID` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `ProductCode` int(11) NOT NULL,
  `SupplierID` int(11) NOT NULL,
  `Stock` int(11) NOT NULL DEFAULT 0,
  `CostPrice` float NOT NULL,
  `SellingPrice` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`ProductID`, `Name`, `ProductCode`, `SupplierID`, `Stock`, `CostPrice`, `SellingPrice`) VALUES
(33, 'Promate Ex BOOK 200P', 111, 1, 106, 150, 200),
(34, 'Promate Ex BOOK 400P', 122, 1, 145, 200, 250),
(35, 'Promate PenciL', 133, 1, 500, 15, 20),
(36, 'Araliya Book100P', 211, 2, 382, 130, 180),
(37, 'Araliya Book200P', 222, 2, 170, 180, 230),
(38, 'Araliya Pen', 233, 2, 1, 10, 15),
(39, 'Atlas Book 200P', 311, 3, 418, 180, 230),
(43, 'Atlas Pen', 322, 3, 350, 17, 25),
(44, 'World Map Atlas', 333, 3, 500, 2, 5);

-- --------------------------------------------------------

--
-- Table structure for table `sales`
--

CREATE TABLE `sales` (
  `SalesID` int(11) NOT NULL,
  `UserID` int(11) NOT NULL,
  `CustomerID` int(11) DEFAULT NULL,
  `Total` float DEFAULT NULL,
  `Paid` float DEFAULT NULL,
  `Date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sales`
--

INSERT INTO `sales` (`SalesID`, `UserID`, `CustomerID`, `Total`, `Paid`, `Date`) VALUES
(1, 1, NULL, NULL, NULL, '2022-06-23'),
(2, 1, NULL, NULL, NULL, '2022-05-23'),
(3, 1, NULL, NULL, NULL, NULL),
(4, 1, NULL, NULL, NULL, NULL),
(5, 1, NULL, NULL, NULL, NULL),
(6, 1, NULL, NULL, NULL, NULL),
(7, 1, NULL, NULL, NULL, NULL),
(8, 1, NULL, NULL, NULL, NULL),
(9, 1, NULL, NULL, NULL, NULL),
(10, 1, NULL, NULL, NULL, NULL),
(11, 1, NULL, NULL, NULL, NULL),
(12, 1, NULL, NULL, NULL, NULL),
(13, 1, NULL, NULL, NULL, NULL),
(14, 1, NULL, NULL, NULL, NULL),
(15, 1, NULL, NULL, NULL, NULL),
(16, 1, NULL, NULL, NULL, NULL),
(17, 1, NULL, NULL, NULL, NULL),
(18, 1, NULL, NULL, NULL, NULL),
(20, 1, NULL, NULL, NULL, NULL),
(21, 1, NULL, NULL, NULL, NULL),
(22, 1, NULL, NULL, NULL, NULL),
(23, 1, NULL, NULL, NULL, NULL),
(24, 1, NULL, NULL, NULL, NULL),
(25, 1, NULL, NULL, NULL, NULL),
(27, 1, NULL, NULL, NULL, NULL),
(28, 1, NULL, NULL, NULL, NULL),
(29, 1, NULL, NULL, NULL, NULL),
(30, 1, NULL, NULL, NULL, NULL),
(31, 1, NULL, NULL, NULL, NULL),
(33, 1, NULL, NULL, NULL, NULL),
(34, 1, NULL, NULL, NULL, NULL),
(35, 1, NULL, NULL, NULL, NULL),
(36, 1, NULL, NULL, NULL, NULL),
(37, 1, NULL, NULL, NULL, NULL),
(38, 1, NULL, NULL, NULL, NULL),
(39, 1, NULL, NULL, NULL, NULL),
(40, 1, NULL, NULL, NULL, NULL),
(41, 1, NULL, NULL, NULL, NULL),
(42, 1, NULL, NULL, NULL, NULL),
(43, 1, NULL, NULL, NULL, NULL),
(44, 1, NULL, NULL, NULL, NULL),
(45, 1, NULL, NULL, NULL, NULL),
(46, 1, NULL, NULL, NULL, NULL),
(48, 1, NULL, NULL, NULL, NULL),
(49, 1, NULL, NULL, NULL, NULL),
(50, 1, NULL, NULL, NULL, NULL),
(51, 1, NULL, NULL, NULL, NULL),
(52, 1, NULL, NULL, NULL, NULL),
(53, 1, NULL, NULL, NULL, NULL),
(54, 1, NULL, NULL, NULL, NULL),
(55, 1, NULL, NULL, NULL, NULL),
(56, 1, NULL, NULL, NULL, NULL),
(57, 1, NULL, NULL, NULL, NULL),
(58, 1, NULL, NULL, NULL, NULL),
(59, 1, NULL, NULL, NULL, NULL),
(60, 1, NULL, NULL, NULL, NULL),
(61, 1, NULL, NULL, NULL, NULL),
(62, 1, NULL, NULL, NULL, NULL),
(63, 1, NULL, NULL, NULL, NULL),
(64, 1, NULL, NULL, NULL, NULL),
(65, 1, NULL, NULL, NULL, NULL),
(66, 1, NULL, NULL, NULL, NULL),
(67, 1, NULL, NULL, NULL, NULL),
(68, 1, NULL, NULL, NULL, NULL),
(69, 1, NULL, NULL, NULL, NULL),
(70, 1, NULL, NULL, NULL, NULL),
(71, 1, NULL, NULL, NULL, NULL),
(72, 1, NULL, NULL, NULL, NULL),
(73, 1, NULL, NULL, NULL, NULL),
(74, 1, NULL, NULL, NULL, NULL),
(75, 1, NULL, NULL, NULL, NULL),
(76, 1, 2004, 14100, 15000, '2022-06-26'),
(77, 1, 2002, 21960, 22000, '2022-06-29'),
(78, 1, 2002, 900, 1500, '2022-06-29'),
(79, 1, 2002, 1440, 4332, '2022-06-29'),
(80, 1, NULL, NULL, NULL, NULL),
(81, 1, 2002, 720, 1000, '2022-06-29'),
(82, 1, NULL, NULL, NULL, NULL),
(83, 1, 2002, 11750, 12000, '2022-06-29'),
(84, 1, 2004, 12220, 13000, '2022-07-01'),
(85, 1, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `solditems`
--

CREATE TABLE `solditems` (
  `ID` int(11) NOT NULL,
  `SalesID` int(11) NOT NULL,
  `ProductCode` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `solditems`
--

INSERT INTO `solditems` (`ID`, `SalesID`, `ProductCode`, `Quantity`) VALUES
(72, 3, 111, 10),
(73, 3, 133, 20),
(74, 7, 211, 5),
(75, 8, 322, 45),
(76, 9, 233, 12),
(77, 9, 333, 9),
(83, 10, 111, 10),
(88, 18, 111, 5),
(94, 22, 322, 10),
(95, 22, 333, 5),
(115, 27, 211, 40),
(116, 29, 111, 7),
(118, 30, 122, 2),
(128, 57, 311, 4),
(129, 58, 322, 2),
(130, 58, 122, 9),
(132, 60, 322, 10),
(133, 60, 122, 500),
(136, 61, 322, 451),
(137, 62, 322, 450),
(138, 63, 322, 450),
(139, 64, 233, 450),
(145, 65, 322, 40),
(146, 65, 333, 120),
(147, 65, 233, 100),
(149, 66, 322, 10),
(153, 67, 311, 2),
(165, 70, 233, 10),
(172, 72, 333, 400),
(173, 72, 233, 85),
(174, 73, 311, 60),
(175, 73, 222, 100),
(181, 76, 211, 70),
(182, 76, 322, 60),
(183, 77, 211, 7),
(184, 77, 311, 90),
(185, 78, 211, 5),
(186, 79, 211, 8),
(187, 81, 211, 4),
(188, 83, 211, 20),
(189, 83, 311, 30),
(190, 83, 322, 50),
(191, 84, 211, 4),
(192, 84, 222, 50),
(197, 85, 233, 4),
(198, 85, 122, 5);

-- --------------------------------------------------------

--
-- Table structure for table `suppliers`
--

CREATE TABLE `suppliers` (
  `SupplierID` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Phone` int(11) NOT NULL,
  `Address` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `suppliers`
--

INSERT INTO `suppliers` (`SupplierID`, `Name`, `Phone`, `Address`) VALUES
(1, 'Promate', 783278, 'Kandy'),
(2, 'Araliya', 86932, 'YIY'),
(3, 'ATLAS', 893298, 'Colombo'),
(12, 'Ataso', 98965, 'colombo');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `UserID` int(11) NOT NULL,
  `Name` varchar(30) NOT NULL,
  `Phone` int(10) NOT NULL,
  `Username` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Role` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`UserID`, `Name`, `Phone`, `Username`, `Password`, `Role`) VALUES
(1, 'Administrator', 11111, 'Admin', 'HNDIT', 'Admin'),
(34, 'mohamed amri', 389072098, 'Amri', 'Pass123', 'Employee'),
(44, 'Hacker', 687216879, 'hack', 'Hack123', 'Employee');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`CustomerID`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`ProductID`),
  ADD UNIQUE KEY `ProductCode` (`ProductCode`),
  ADD KEY `products_ibfk_1` (`SupplierID`);

--
-- Indexes for table `sales`
--
ALTER TABLE `sales`
  ADD PRIMARY KEY (`SalesID`),
  ADD KEY `UserID` (`UserID`),
  ADD KEY `sales_ibfk_2` (`CustomerID`);

--
-- Indexes for table `solditems`
--
ALTER TABLE `solditems`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `SalesID` (`SalesID`),
  ADD KEY `ProductCode` (`ProductCode`) USING BTREE;

--
-- Indexes for table `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`SupplierID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`UserID`),
  ADD UNIQUE KEY `Username` (`Username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `CustomerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2006;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `ProductID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT for table `solditems`
--
ALTER TABLE `solditems`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=199;

--
-- AUTO_INCREMENT for table `suppliers`
--
ALTER TABLE `suppliers`
  MODIFY `SupplierID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `UserID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`SupplierID`) REFERENCES `suppliers` (`SupplierID`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `sales`
--
ALTER TABLE `sales`
  ADD CONSTRAINT `sales_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `sales_ibfk_2` FOREIGN KEY (`CustomerID`) REFERENCES `customers` (`CustomerID`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `solditems`
--
ALTER TABLE `solditems`
  ADD CONSTRAINT `solditems_ibfk_1` FOREIGN KEY (`SalesID`) REFERENCES `sales` (`SalesID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `solditems_ibfk_2` FOREIGN KEY (`ProductCode`) REFERENCES `products` (`ProductCode`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
