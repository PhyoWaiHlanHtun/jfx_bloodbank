-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 09, 2018 at 10:43 AM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bloodbank`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(10) NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 NOT NULL,
  `dob` date NOT NULL,
  `phonenumber` varchar(20) NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 NOT NULL,
  `address` varchar(100) NOT NULL,
  `gender` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `name`, `dob`, `phonenumber`, `password`, `address`, `gender`) VALUES
(2, 'admin', '2012-03-14', '099999', 'admin', 'Yangon', 'Male');

-- --------------------------------------------------------

--
-- Table structure for table `blood`
--

CREATE TABLE `blood` (
  `id` int(12) NOT NULL,
  `blood_type` varchar(255) NOT NULL,
  `quantity` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `blood`
--

INSERT INTO `blood` (`id`, `blood_type`, `quantity`) VALUES
(1, 'O', 3),
(2, 'A', 0),
(3, 'B', 0),
(4, 'AB', 0);

-- --------------------------------------------------------

--
-- Table structure for table `blood_donate_records`
--

CREATE TABLE `blood_donate_records` (
  `blood_id` int(20) NOT NULL,
  `donate_id` varchar(20) NOT NULL,
  `blood_type` varchar(20) CHARACTER SET utf8 NOT NULL,
  `no_of_bags` int(20) NOT NULL,
  `donate_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `blood_donate_records`
--

INSERT INTO `blood_donate_records` (`blood_id`, `donate_id`, `blood_type`, `no_of_bags`, `donate_date`) VALUES
(1, 'BD11111', 'O', 1, '2018-08-15');

-- --------------------------------------------------------

--
-- Table structure for table `donater`
--

CREATE TABLE `donater` (
  `id` int(10) NOT NULL,
  `donaterid` varchar(20) NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 NOT NULL,
  `fathername` varchar(20) CHARACTER SET utf8 NOT NULL,
  `nrc` varchar(20) NOT NULL,
  `age` int(3) NOT NULL,
  `dob` date NOT NULL,
  `gender` varchar(20) CHARACTER SET utf8 NOT NULL,
  `address` varchar(200) CHARACTER SET utf8 NOT NULL,
  `phonenumber` varchar(15) CHARACTER SET utf8 NOT NULL,
  `bloodtype` varchar(10) CHARACTER SET utf8 NOT NULL,
  `region` varchar(100) NOT NULL,
  `donatedate` date NOT NULL,
  `jobposition` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `donater`
--

INSERT INTO `donater` (`id`, `donaterid`, `name`, `fathername`, `nrc`, `age`, `dob`, `gender`, `address`, `phonenumber`, `bloodtype`, `region`, `donatedate`, `jobposition`) VALUES
(1, 'BD11111', 'minthiha', 'asfagf', 'agsag', 32, '2010-09-09', 'male', 'afwsgsg', '235235', 'O', 'Yangon', '2018-08-22', 'dgsdg');

-- --------------------------------------------------------

--
-- Table structure for table `hospital`
--

CREATE TABLE `hospital` (
  `id` int(20) NOT NULL,
  `hp_name` varchar(2555) NOT NULL,
  `hp_address` varchar(255) NOT NULL,
  `hp_email` varchar(255) NOT NULL,
  `hp_registerdate` date NOT NULL,
  `hp_region` varchar(255) NOT NULL,
  `hp_phone` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `hospital_transfer`
--

CREATE TABLE `hospital_transfer` (
  `id` int(12) NOT NULL,
  `hp_name` varchar(255) NOT NULL,
  `blood_type` varchar(255) NOT NULL,
  `tf_date` date NOT NULL,
  `no_of_bags` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `id` int(12) NOT NULL,
  `p_name` varchar(255) NOT NULL,
  `p_nrc` varchar(255) NOT NULL,
  `p_phone` varchar(255) NOT NULL,
  `p_address` varchar(255) NOT NULL,
  `p_gender` varchar(255) NOT NULL,
  `p_registerdate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `patient_transfer`
--

CREATE TABLE `patient_transfer` (
  `id` int(12) NOT NULL,
  `p_name` varchar(255) NOT NULL,
  `bl` varchar(255) NOT NULL,
  `tf_` date NOT NULL,
  `no_of_bags` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `blood`
--
ALTER TABLE `blood`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `blood_donate_records`
--
ALTER TABLE `blood_donate_records`
  ADD PRIMARY KEY (`blood_id`);

--
-- Indexes for table `donater`
--
ALTER TABLE `donater`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `hospital`
--
ALTER TABLE `hospital`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `hospital_transfer`
--
ALTER TABLE `hospital_transfer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `patient_transfer`
--
ALTER TABLE `patient_transfer`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `blood`
--
ALTER TABLE `blood`
  MODIFY `id` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `blood_donate_records`
--
ALTER TABLE `blood_donate_records`
  MODIFY `blood_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `donater`
--
ALTER TABLE `donater`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `hospital`
--
ALTER TABLE `hospital`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hospital_transfer`
--
ALTER TABLE `hospital_transfer`
  MODIFY `id` int(12) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
  MODIFY `id` int(12) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `patient_transfer`
--
ALTER TABLE `patient_transfer`
  MODIFY `id` int(12) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
