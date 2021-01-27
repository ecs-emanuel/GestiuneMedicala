-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 09, 2021 at 01:22 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cabinetmedical`
--

-- --------------------------------------------------------

--
-- Table structure for table `credentiale`
--

CREATE TABLE `credentiale` (
  `id` int(11) NOT NULL,
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `access` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `credentiale`
--

INSERT INTO `credentiale` (`id`, `username`, `password`, `access`) VALUES
(1, 'pompeiandreea', 'medic', 'Medic'),
(2, 'lacatusmariana', 'medic', 'Medic'),
(3, 'goroncamelia', 'medic', 'Medic'),
(4, 'rostasmarius', 'medic', 'Medic'),
(5, 'bojanadrian', 'medic', 'Medic'),
(6, 'rodinavirgil', 'medic', 'Medic'),
(7, 'plesacorina', 'pacient', 'Pacient'),
(8, 'ganeaemilia', 'pacient', 'Pacient'),
(9, 'popcristina', 'pacient', 'Pacient'),
(10, 'popmatei', 'pacient', 'Pacient'),
(11, 'vadandaniel', 'pacient', 'Pacient'),
(12, 'moldovanstefan', 'pacient', 'Pacient');

-- --------------------------------------------------------

--
-- Table structure for table `fisepacienti`
--

CREATE TABLE `fisepacienti` (
  `id` int(11) NOT NULL,
  `pacient` int(11) DEFAULT NULL,
  `medic` int(11) DEFAULT NULL,
  `diagnostic` varchar(50) DEFAULT NULL,
  `tratament` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `fisepacienti`
--

INSERT INTO `fisepacienti` (`id`, `pacient`, `medic`, `diagnostic`, `tratament`) VALUES
(1, 1, 1, 'migrena', 'algocalmin'),
(2, 1, 2, 'iacrs', 'nurofen'),
(3, 1, 1, 'alergie', 'alergofen'),
(4, 1, 1, 'pneumonie', 'amoxacilina'),
(5, 2, 1, 'fractura', 'amoxacilina'),
(6, 3, 2, 'febra', 'paracetamol'),
(9, 4, 1, 'Infectie Acuta', 'Aspirina'),
(10, 5, 1, 'Otita acuta', 'Penicilina 20gr 3x/zi');

-- --------------------------------------------------------

--
-- Table structure for table `medici`
--

CREATE TABLE `medici` (
  `id` int(11) NOT NULL,
  `nume` varchar(30) DEFAULT NULL,
  `prenume` varchar(30) DEFAULT NULL,
  `specializare` int(11) DEFAULT NULL,
  `nastere` date DEFAULT NULL,
  `sex` varchar(15) DEFAULT NULL,
  `telefon` varchar(30) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `adresa` varchar(30) DEFAULT NULL,
  `user` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `medici`
--

INSERT INTO `medici` (`id`, `nume`, `prenume`, `specializare`, `nastere`, `sex`, `telefon`, `email`, `adresa`, `user`) VALUES
(1, 'Pompei', 'Andreea', 3, '1985-02-06', 'Feminin', '0774111222', 'pompeiandreea@email.com', 'Str. Bucegi nr. 7', 1),
(2, 'Lacatus', 'Mariana', 5, '1983-05-02', 'Feminin', '0774222333', 'lacatusmariana@email.com', 'Str. Brasov nr. 1', 2),
(3, 'Goron', 'Camelia', 13, '1986-07-20', 'Feminin', '0774333444', 'goroncamelia@email.com', 'Str. Sinaia nr. 21', 3),
(4, 'Rostas', 'Marius', 13, '1980-12-16', 'Masculin', '0722111555', 'rostasmarius@email.com', 'Str. Galaxiei nr. 24', 4),
(5, 'Bojan', 'Adrian', 11, '1987-09-17', 'Masculin', '0755666777', 'bojanadrian@email.com', 'Str. Dunarii nr. 44', 5),
(6, 'Rodina', 'Virgil', 16, '1975-01-30', 'Masculin', '0788333222', 'rodinavirgil@email.com', 'Str. Alverna nr. 34', 6);

-- --------------------------------------------------------

--
-- Table structure for table `pacienti`
--

CREATE TABLE `pacienti` (
  `id` int(11) NOT NULL,
  `nume` varchar(30) DEFAULT NULL,
  `prenume` varchar(30) DEFAULT NULL,
  `nastere` date DEFAULT NULL,
  `sex` varchar(15) DEFAULT NULL,
  `telefon` varchar(30) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `adresa` varchar(30) DEFAULT NULL,
  `user` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pacienti`
--

INSERT INTO `pacienti` (`id`, `nume`, `prenume`, `nastere`, `sex`, `telefon`, `email`, `adresa`, `user`) VALUES
(1, 'Plesa', 'Corina', '1995-03-24', 'Feminin', '0773123456', 'plesacorina@email.com', 'Str. Alverna nr. 39', 7),
(2, 'Ganea', 'Emilia', '1992-07-28', 'Feminin', '0773987876', 'ganeaemilia@email.com', 'Str. Bizusa nr. 3', 8),
(3, 'Pop', 'Cristina', '1980-03-10', 'Feminin', '0772554433', 'popcristina@email.com', 'Str. Stejarului nr. 15', 9),
(4, 'Pop', 'Matei', '1984-06-13', 'Masculin', '0733999988', 'popmatei@email.com', 'Str. Macinului nr. 152', 10),
(5, 'Vadan', 'Daniel', '1982-01-06', 'Masculin', '0779887733', 'vadandaniel@email.com', 'Str. Macinului nr. 12', 11),
(6, 'Moldovan', 'Stefan', '1972-08-07', 'Masculin', '0723456121', 'moldovanstefan@email.com', 'Str. Lunii nr. 1', 12);

-- --------------------------------------------------------

--
-- Table structure for table `programari`
--

CREATE TABLE `programari` (
  `id` int(11) NOT NULL,
  `pacient` int(11) DEFAULT NULL,
  `medic` int(11) DEFAULT NULL,
  `data` datetime DEFAULT NULL,
  `realizata` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `programari`
--

INSERT INTO `programari` (`id`, `pacient`, `medic`, `data`, `realizata`) VALUES
(1, 1, 1, '2021-01-10 00:00:00', 0),
(2, 2, 1, '2021-01-10 09:30:00', 0),
(3, 3, 1, '2021-01-10 14:00:00', 0),
(4, 4, 1, '2021-01-10 17:30:00', 0),
(5, 1, 2, '2021-01-10 17:00:00', 0),
(6, 2, 2, '2021-01-10 12:00:00', 0),
(7, 5, 2, '2021-01-10 16:00:00', 0),
(8, 5, 1, '2021-02-11 09:00:00', 0),
(9, 3, 1, '2021-02-11 15:30:00', 0),
(10, 1, 4, '2021-02-11 15:30:00', 0),
(11, 6, 4, '2021-02-11 09:00:00', 0),
(12, 2, 4, '2021-02-11 11:00:00', 0),
(13, 1, 1, '2021-01-10 10:00:00', 0),
(14, 1, 1, '2021-01-10 07:30:00', 0),
(15, 1, 4, '2021-01-10 15:00:00', 0),
(16, 1, 2, '2021-01-10 14:30:00', 0),
(17, 1, 6, '2021-01-10 08:00:00', 0),
(18, 1, 2, '2021-01-10 12:30:00', 0);

-- --------------------------------------------------------

--
-- Table structure for table `specializari`
--

CREATE TABLE `specializari` (
  `id` int(11) NOT NULL,
  `denumire` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `specializari`
--

INSERT INTO `specializari` (`id`, `denumire`) VALUES
(1, 'Cardiologie'),
(2, 'Dermatologie'),
(3, 'Endocrinologie'),
(4, 'Gastroenterologie'),
(5, 'Ginecologie'),
(6, 'Hematologie'),
(7, 'Hepatologie'),
(8, 'Neurologie'),
(9, 'Oftamologie'),
(10, 'Oncologie'),
(11, 'Ortopedie'),
(12, 'Pediatrie'),
(13, 'Pneumologie'),
(14, 'Psihiatrie'),
(15, 'Reumatologie'),
(16, 'Urologie');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `credentiale`
--
ALTER TABLE `credentiale`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `fisepacienti`
--
ALTER TABLE `fisepacienti`
  ADD PRIMARY KEY (`id`),
  ADD KEY `pacient` (`pacient`),
  ADD KEY `medic` (`medic`);

--
-- Indexes for table `medici`
--
ALTER TABLE `medici`
  ADD PRIMARY KEY (`id`),
  ADD KEY `specializare` (`specializare`),
  ADD KEY `user` (`user`);

--
-- Indexes for table `pacienti`
--
ALTER TABLE `pacienti`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user` (`user`);

--
-- Indexes for table `programari`
--
ALTER TABLE `programari`
  ADD PRIMARY KEY (`id`),
  ADD KEY `pacient` (`pacient`),
  ADD KEY `medic` (`medic`);

--
-- Indexes for table `specializari`
--
ALTER TABLE `specializari`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `denumire` (`denumire`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `credentiale`
--
ALTER TABLE `credentiale`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `fisepacienti`
--
ALTER TABLE `fisepacienti`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `medici`
--
ALTER TABLE `medici`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `pacienti`
--
ALTER TABLE `pacienti`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `programari`
--
ALTER TABLE `programari`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `specializari`
--
ALTER TABLE `specializari`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `fisepacienti`
--
ALTER TABLE `fisepacienti`
  ADD CONSTRAINT `fisepacienti_ibfk_1` FOREIGN KEY (`pacient`) REFERENCES `pacienti` (`id`),
  ADD CONSTRAINT `fisepacienti_ibfk_2` FOREIGN KEY (`medic`) REFERENCES `medici` (`id`);

--
-- Constraints for table `medici`
--
ALTER TABLE `medici`
  ADD CONSTRAINT `medici_ibfk_1` FOREIGN KEY (`user`) REFERENCES `credentiale` (`id`),
  ADD CONSTRAINT `medici_ibfk_2` FOREIGN KEY (`specializare`) REFERENCES `specializari` (`id`),
  ADD CONSTRAINT `medici_ibfk_3` FOREIGN KEY (`user`) REFERENCES `credentiale` (`id`);

--
-- Constraints for table `pacienti`
--
ALTER TABLE `pacienti`
  ADD CONSTRAINT `pacienti_ibfk_1` FOREIGN KEY (`user`) REFERENCES `credentiale` (`id`);

--
-- Constraints for table `programari`
--
ALTER TABLE `programari`
  ADD CONSTRAINT `programari_ibfk_1` FOREIGN KEY (`pacient`) REFERENCES `pacienti` (`id`),
  ADD CONSTRAINT `programari_ibfk_2` FOREIGN KEY (`medic`) REFERENCES `medici` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
