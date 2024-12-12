-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 12, 2024 at 02:20 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `aol_fla`
--

-- --------------------------------------------------------

--
-- Table structure for table `facilities`
--

CREATE TABLE `facilities` (
  `hotelID` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `facilities`
--

INSERT INTO `facilities` (`hotelID`, `name`) VALUES
('HO599', 'Breakfast'),
('HO599', 'Gym'),
('HO599', 'Parking'),
('HO599', 'Swimming Pool'),
('HO599', 'Wifi');

-- --------------------------------------------------------

--
-- Table structure for table `hotels`
--

CREATE TABLE `hotels` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `rating` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hotels`
--

INSERT INTO `hotels` (`id`, `name`, `address`, `rating`) VALUES
('HO599', 'Ariston', 'Alam Sutera', 0);

-- --------------------------------------------------------

--
-- Table structure for table `hotel_admin`
--

CREATE TABLE `hotel_admin` (
  `hotelID` varchar(255) NOT NULL,
  `adminID` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hotel_admin`
--

INSERT INTO `hotel_admin` (`hotelID`, `adminID`) VALUES
('HO599', 'AD407');

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE `reservation` (
  `id` varchar(255) NOT NULL,
  `hotelID` varchar(255) NOT NULL,
  `customerID` varchar(255) NOT NULL,
  `roomType` varchar(255) NOT NULL,
  `checkInDate` date NOT NULL,
  `checkOutDate` date NOT NULL,
  `roomAmount` int(11) NOT NULL,
  `state` varchar(255) NOT NULL,
  `paymentType` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`id`, `hotelID`, `customerID`, `roomType`, `checkInDate`, `checkOutDate`, `roomAmount`, `state`, `paymentType`) VALUES
('RE238', 'HO599', 'CU401', 'Premium', '2024-12-12', '2024-12-13', 3, 'Paid', 'Card'),
('RE243', 'HO599', 'CU753', 'Basic', '2024-12-12', '2024-12-13', 1, 'Unpaid', 'Cash'),
('RE470', 'HO599', 'CU401', 'Basic', '2024-12-12', '2024-12-13', 1, 'Paid', 'Cash'),
('RE594', 'HO599', 'CU001', 'Basic', '2024-12-12', '2024-12-13', 1, 'Paid', 'Cash'),
('RE882', 'HO599', 'CU753', 'Basic', '2024-12-13', '2024-12-27', 1, 'Paid', 'E-Wallet'),
('RE894', 'HO599', 'CU401', 'Basic', '2024-12-12', '2024-12-13', 1, 'Paid', 'Cash'),
('RE899', 'HO599', 'CU001', 'Smoking Room', '2024-12-12', '2024-12-13', 1, 'Paid', 'Cash');

-- --------------------------------------------------------

--
-- Table structure for table `roomtype`
--

CREATE TABLE `roomtype` (
  `hotelID` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `description` varchar(255) NOT NULL,
  `isNonSmoking` tinyint(1) NOT NULL,
  `roomAmount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `roomtype`
--

INSERT INTO `roomtype` (`hotelID`, `name`, `price`, `description`, `isNonSmoking`, `roomAmount`) VALUES
('HO599', 'Basic', 20.99, 'Basic Suite with twin bed', 1, 10),
('HO599', 'Premium', 50, 'Premium bedding and service', 1, 5),
('HO599', 'Smoking Room', 20.99, 'Basic Room but you can smoke', 0, 5);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phoneNo` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `phoneNo`, `email`, `password`, `role`) VALUES
('AD001', 'Andreas', '081238212367', 'andreas@gmail.com', 'andreas123', 'admin'),
('AD287', 'Dimas', '08291094827', 'dimas@gmail.com', 'dimas123', 'admin'),
('AD407', 'James', '0829381649274', 'james@gmail.com', 'james123', 'admin'),
('CU001', 'Matthew', '081293615492', 'matthew@gmail.com', 'matthew123', 'customer'),
('CU401', 'Kevin Ardi', '0891039203917', 'kevin@gmail.com', 'kevin123', 'customer'),
('CU753', 'Felix', '081294826495', 'felix@gmail.com', 'felix123', 'customer');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `facilities`
--
ALTER TABLE `facilities`
  ADD PRIMARY KEY (`hotelID`,`name`);

--
-- Indexes for table `hotels`
--
ALTER TABLE `hotels`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `hotel_admin`
--
ALTER TABLE `hotel_admin`
  ADD PRIMARY KEY (`hotelID`,`adminID`),
  ADD KEY `adminID` (`adminID`);

--
-- Indexes for table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `customerID` (`customerID`),
  ADD KEY `hotelID` (`hotelID`,`roomType`);

--
-- Indexes for table `roomtype`
--
ALTER TABLE `roomtype`
  ADD PRIMARY KEY (`hotelID`,`name`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `facilities`
--
ALTER TABLE `facilities`
  ADD CONSTRAINT `facilities_ibfk_1` FOREIGN KEY (`hotelID`) REFERENCES `hotels` (`id`);

--
-- Constraints for table `hotel_admin`
--
ALTER TABLE `hotel_admin`
  ADD CONSTRAINT `hotel_admin_ibfk_1` FOREIGN KEY (`hotelID`) REFERENCES `hotels` (`id`),
  ADD CONSTRAINT `hotel_admin_ibfk_2` FOREIGN KEY (`adminID`) REFERENCES `users` (`id`);

--
-- Constraints for table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`hotelID`) REFERENCES `hotels` (`id`),
  ADD CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`customerID`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `reservation_ibfk_3` FOREIGN KEY (`hotelID`,`roomType`) REFERENCES `roomtype` (`hotelID`, `name`);

--
-- Constraints for table `roomtype`
--
ALTER TABLE `roomtype`
  ADD CONSTRAINT `roomtype_ibfk_1` FOREIGN KEY (`hotelID`) REFERENCES `hotels` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
