-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 06, 2025 at 03:21 PM
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
-- Database: `gojek`
--

-- --------------------------------------------------------

--
-- Table structure for table `drivers`
--

CREATE TABLE `drivers` (
  `driver_id` int(11) DEFAULT NULL,
  `driver_phonNum` varchar(15) DEFAULT NULL,
  `vehicle_name` text DEFAULT NULL,
  `vehicle_type` text DEFAULT NULL,
  `vehicle_plate` text DEFAULT NULL,
  `driver_status` enum('AVAILABLE','OFFLINE','BOOKED') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `drivers`
--

INSERT INTO `drivers` (`driver_id`, `driver_phonNum`, `vehicle_name`, `vehicle_type`, `vehicle_plate`, `driver_status`) VALUES
(26, '0812223333222', '', 'Motor', '', 'BOOKED'),
(27, '08111199999', 'CBR', 'Motor', 'D 11 DD', 'AVAILABLE'),
(28, '088123132321', 'Tesla', 'Mobil', 'D 133 DF', 'OFFLINE'),
(31, '08999911111', 'CBR', 'Motor', 'C 1 C', 'BOOKED'),
(32, '089777723221', 'CBR', 'Motor', 'D 1 MAS', 'AVAILABLE'),
(33, '0876632381', 'Mercy', 'Mobil', 'D 0 NTOL', 'BOOKED');

-- --------------------------------------------------------

--
-- Table structure for table `jopaylist`
--

CREATE TABLE `jopaylist` (
  `jopaylist_id` int(11) NOT NULL,
  `passanger_id` int(11) DEFAULT NULL,
  `driver_id` int(11) DEFAULT NULL,
  `nominal` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `jopaylist`
--

INSERT INTO `jopaylist` (`jopaylist_id`, `passanger_id`, `driver_id`, `nominal`) VALUES
(4, 25, 26, 5000);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `cust_id` int(11) DEFAULT NULL,
  `promo_id` int(11) DEFAULT NULL,
  `driver_id` int(11) DEFAULT NULL,
  `order_date` datetime DEFAULT NULL,
  `order_pickup` text DEFAULT NULL,
  `order_destination` text DEFAULT NULL,
  `order_price` float DEFAULT NULL,
  `order_final_price` float DEFAULT NULL,
  `order_vehicle_name` varchar(255) DEFAULT NULL,
  `order_vehicle_plate` varchar(255) DEFAULT NULL,
  `order_status` enum('NOW','CANCEL','FINISHED') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`order_id`, `cust_id`, `promo_id`, `driver_id`, `order_date`, `order_pickup`, `order_destination`, `order_price`, `order_final_price`, `order_vehicle_name`, `order_vehicle_plate`, `order_status`) VALUES
(16, 25, 8, 26, '2023-11-24 00:00:00', 'B', 'S', 17000, 15200, '0812223333222', 'F 1 F', 'CANCEL'),
(17, 25, 8, 27, '2023-11-24 00:00:00', 'S', 'A', 36000, 30400, '08111199999', 'D 1 D', 'FINISHED'),
(18, 25, 0, 26, '2023-11-24 00:00:00', 'V', 'A', 21000, 23000, '0812223333222', 'F 1 F', 'FINISHED'),
(19, 29, 9, 27, '2023-11-25 00:00:00', 'S', 'B', 34000, 28800, '08111199999', 'D 1 D', 'CANCEL'),
(20, 29, 9, 27, '2023-11-25 00:00:00', 'S', 'B', 34000, 28800, '08111199999', 'D 1 D', 'FINISHED'),
(21, 30, 0, 28, '2023-11-25 00:00:00', 'A', 'D', 6000, 8000, '088123132321', 'D 133 DF', 'CANCEL'),
(22, 25, 0, 27, '2024-01-26 00:00:00', 'A', 'C', 2000, 4000, '08111199999', 'D 11 DD', 'FINISHED'),
(23, 25, 0, 31, '2024-01-26 00:00:00', 'B', 'E', 3000, 5000, '08999911111', 'C 1 C', 'FINISHED'),
(24, 25, 10, 26, '2024-04-21 00:00:00', 'S', 'A', 18000, 12000, '0812223333222', 'F 1 F', 'NOW'),
(25, 25, 10, 31, '2024-04-22 00:00:00', 'A', 'B', 1000, 1800, '08999911111', 'C 1 C', 'NOW'),
(26, 25, 0, 33, '2024-06-13 00:00:00', 'J', 'A', 18000, 20000, '0876632381', 'D 0 NTOL', 'NOW');

-- --------------------------------------------------------

--
-- Table structure for table `passangers`
--

CREATE TABLE `passangers` (
  `passanger_id` int(11) DEFAULT NULL,
  `passanger_phonNum` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `passangers`
--

INSERT INTO `passangers` (`passanger_id`, `passanger_phonNum`) VALUES
(25, '086661111222'),
(29, '08973432112'),
(30, '08533434232');

-- --------------------------------------------------------

--
-- Table structure for table `promo`
--

CREATE TABLE `promo` (
  `promo_id` int(11) NOT NULL,
  `promo_code` text DEFAULT NULL,
  `promo_exp` date DEFAULT NULL,
  `promo_value` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `promo`
--

INSERT INTO `promo` (`promo_id`, `promo_code`, `promo_exp`, `promo_value`) VALUES
(10, 'JUVIJUARA', '2024-04-30', 0.4),
(14, 'IRVANBGSD', '2024-04-30', 0.1),
(15, 'zeejkt48', '2024-04-29', 0.2);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(100) DEFAULT NULL,
  `user_pass` varchar(100) DEFAULT NULL,
  `user_role` varchar(100) DEFAULT NULL,
  `user_wallet` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `user_name`, `user_pass`, `user_role`, `user_wallet`) VALUES
(1, 'adminjosen', 'admin123', 'Admin', 10000),
(25, 'Seto', 'ss22', 'Passanger', 92600),
(26, 'Driver3', 'dv3', 'Driver', 11000),
(27, 'Driver2', 'dv2', 'Driver', 1896200),
(28, 'Juan', 'jj11', 'Driver', 0),
(29, 'Halos', 'hh22', 'Passanger', 31200),
(30, 'Dea', 'kocak', 'Passanger', 40000),
(31, 'Driver1', 'dv1', 'Driver', 3000),
(32, 'JUVI', 'akusukasingkong', 'Driver', 0),
(33, 'Nathan', 'aa11', 'Driver', 0);

-- --------------------------------------------------------

--
-- Table structure for table `waitinglist`
--

CREATE TABLE `waitinglist` (
  `driver_username` varchar(255) NOT NULL,
  `driver_password` varchar(255) NOT NULL,
  `driver_phonNum` varchar(15) DEFAULT NULL,
  `vehicle_name` text DEFAULT NULL,
  `vehicle_type` text DEFAULT NULL,
  `vehicle_plate` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `waitinglist`
--

INSERT INTO `waitinglist` (`driver_username`, `driver_password`, `driver_phonNum`, `vehicle_name`, `vehicle_type`, `vehicle_plate`) VALUES
('Rafa', 'rr11', '0876756534', 'Kawasaki', 'Motor', 'N 1 GGA');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `drivers`
--
ALTER TABLE `drivers`
  ADD KEY `driver_id` (`driver_id`);

--
-- Indexes for table `jopaylist`
--
ALTER TABLE `jopaylist`
  ADD PRIMARY KEY (`jopaylist_id`),
  ADD KEY `driver_id` (`driver_id`),
  ADD KEY `passanger_id` (`passanger_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `cust_id` (`cust_id`),
  ADD KEY `driver_id` (`driver_id`);

--
-- Indexes for table `passangers`
--
ALTER TABLE `passangers`
  ADD KEY `passanger_id` (`passanger_id`);

--
-- Indexes for table `promo`
--
ALTER TABLE `promo`
  ADD PRIMARY KEY (`promo_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `jopaylist`
--
ALTER TABLE `jopaylist`
  MODIFY `jopaylist_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `promo`
--
ALTER TABLE `promo`
  MODIFY `promo_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `drivers`
--
ALTER TABLE `drivers`
  ADD CONSTRAINT `drivers_ibfk_1` FOREIGN KEY (`driver_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `jopaylist`
--
ALTER TABLE `jopaylist`
  ADD CONSTRAINT `jopaylist_ibfk_1` FOREIGN KEY (`driver_id`) REFERENCES `drivers` (`driver_id`),
  ADD CONSTRAINT `jopaylist_ibfk_2` FOREIGN KEY (`passanger_id`) REFERENCES `passangers` (`passanger_id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`cust_id`) REFERENCES `passangers` (`passanger_id`),
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`driver_id`) REFERENCES `drivers` (`driver_id`);

--
-- Constraints for table `passangers`
--
ALTER TABLE `passangers`
  ADD CONSTRAINT `passangers_ibfk_1` FOREIGN KEY (`passanger_id`) REFERENCES `users` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
