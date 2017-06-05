-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-05-2017 a las 17:21:18
-- Versión del servidor: 10.1.21-MariaDB
-- Versión de PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `nctours`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `book`
--

CREATE TABLE `book` (
  `id` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `num_pax` int(11) NOT NULL,
  `pvp` double NOT NULL,
  `idtrip` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bookpax`
--

CREATE TABLE `bookpax` (
  `idbook` int(11) NOT NULL,
  `dnipax` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bookuser`
--

CREATE TABLE `bookuser` (
  `idbook` int(11) NOT NULL,
  `iduser` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `date`
--

CREATE TABLE `date` (
  `departurefirst` datetime NOT NULL,
  `departurelast` datetime NOT NULL,
  `idtrip` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hotel`
--

CREATE TABLE `hotel` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `category` int(11) NOT NULL,
  `location` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `itinerary`
--

CREATE TABLE `itinerary` (
  `id` int(11) NOT NULL,
  `day1` varchar(500) NOT NULL,
  `day2` varchar(500) NOT NULL,
  `day3` varchar(500) NOT NULL,
  `day4` varchar(500) NOT NULL,
  `day5` varchar(500) NOT NULL,
  `day6` varchar(500) NOT NULL,
  `day7` varchar(500) NOT NULL,
  `day8` varchar(500) NOT NULL,
  `day9` varchar(500) NOT NULL,
  `idtrip` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pax`
--

CREATE TABLE `pax` (
  `dni` varchar(9) NOT NULL,
  `name` varchar(200) NOT NULL,
  `surname` int(11) NOT NULL,
  `dob` datetime NOT NULL,
  `passport` varchar(15) NOT NULL,
  `passportexpiry` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `trip`
--

CREATE TABLE `trip` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `duration` varchar(200) DEFAULT NULL,
  `iditinerary` int(11) NOT NULL,
  `pricedouble` double NOT NULL,
  `pricesingle` double NOT NULL,
  `suplement` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `triphotels`
--

CREATE TABLE `triphotels` (
  `idtrip` int(11) NOT NULL,
  `idhotel` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(250) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `bookpax`
--
ALTER TABLE `bookpax`
  ADD PRIMARY KEY (`idbook`,`dnipax`),
  ADD KEY `dnipax` (`dnipax`);

--
-- Indices de la tabla `bookuser`
--
ALTER TABLE `bookuser`
  ADD PRIMARY KEY (`idbook`,`iduser`),
  ADD KEY `iduser` (`iduser`);

--
-- Indices de la tabla `date`
--
ALTER TABLE `date`
  ADD PRIMARY KEY (`departurefirst`,`departurelast`,`idtrip`),
  ADD KEY `idtrip` (`idtrip`);

--
-- Indices de la tabla `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `itinerary`
--
ALTER TABLE `itinerary`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `pax`
--
ALTER TABLE `pax`
  ADD PRIMARY KEY (`dni`),
  ADD UNIQUE KEY `passport` (`passport`);

--
-- Indices de la tabla `trip`
--
ALTER TABLE `trip`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indices de la tabla `triphotels`
--
ALTER TABLE `triphotels`
  ADD PRIMARY KEY (`idtrip`,`idhotel`),
  ADD KEY `idhotel` (`idhotel`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `book`
--
ALTER TABLE `book`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `hotel`
--
ALTER TABLE `hotel`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `itinerary`
--
ALTER TABLE `itinerary`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `trip`
--
ALTER TABLE `trip`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `bookpax`
--
ALTER TABLE `bookpax`
  ADD CONSTRAINT `bookpax_ibfk_1` FOREIGN KEY (`idbook`) REFERENCES `book` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `bookpax_ibfk_2` FOREIGN KEY (`dnipax`) REFERENCES `pax` (`dni`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `bookuser`
--
ALTER TABLE `bookuser`
  ADD CONSTRAINT `bookuser_ibfk_1` FOREIGN KEY (`idbook`) REFERENCES `book` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `bookuser_ibfk_2` FOREIGN KEY (`iduser`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `date`
--
ALTER TABLE `date`
  ADD CONSTRAINT `date_ibfk_1` FOREIGN KEY (`idtrip`) REFERENCES `trip` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `triphotels`
--
ALTER TABLE `triphotels`
  ADD CONSTRAINT `triphotels_ibfk_1` FOREIGN KEY (`idtrip`) REFERENCES `trip` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `triphotels_ibfk_2` FOREIGN KEY (`idhotel`) REFERENCES `hotel` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
