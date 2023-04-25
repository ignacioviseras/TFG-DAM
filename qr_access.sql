-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 25-04-2023 a las 18:50:55
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `qr_access`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `accesses`
--

CREATE TABLE `accesses` (
  `id` int(11) NOT NULL,
  `expires` bigint(11) NOT NULL,
  `availables` int(11) NOT NULL,
  `admin_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `accesses`
--

INSERT INTO `accesses` (`id`, `expires`, `availables`, `admin_id`, `customer_id`) VALUES
(2, 1642096000, 15, 2, 4),
(3, 1642182400, 20, 3, 7),
(4, 1642268800, 5, 4, 1),
(5, 1642355200, 30, 5, 8),
(6, 1642441600, 25, 6, 3),
(7, 1642528000, 10, 7, 9),
(9, 1642700800, 20, 9, 6),
(10, 1642787200, 5, 10, 10),
(11, 1642873600, 10, 1, 4),
(13, 1643046400, 20, 3, 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `admins`
--

CREATE TABLE `admins` (
  `id` int(11) NOT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `token_access` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `admins`
--

INSERT INTO `admins` (`id`, `mail`, `name`, `password`, `token_access`) VALUES
(3, 'admin3@example.com', 'Bob Johnson', 'password789', 'token789'),
(4, 'admin4@example.com', 'Emily Brown', 'passwordabc', 'tokenabc'),
(5, 'admin5@example.com', 'Alexandra Davis', 'passworddef', 'tokendef'),
(6, 'admin6@example.com', 'Mark Thompson', 'passwordghi', 'tokenghi'),
(7, 'admin7@example.com', 'Samantha Lee', 'passwordjkl', 'tokenjkl'),
(8, 'admin8@example.com', 'David Wilson', 'passwordmno', 'tokenmno'),
(9, 'admin9@example.com', 'Rachel Taylor', 'passwordpqr', 'tokenpqr'),
(10, 'admin10@example.com', 'Michael Brown', 'passwordstu', 'tokenstu');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `customers`
--

CREATE TABLE `customers` (
  `id` int(11) NOT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `token_access` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `customers`
--

INSERT INTO `customers` (`id`, `mail`, `name`, `password`, `token_access`) VALUES
(1, 'cliente1@gmail.com', 'Juan Pérez', 'pass123', 'token1'),
(3, 'cliente3@yahoo.com', 'Pedro Hernández', 'pass789', 'token3'),
(4, 'cliente4@gmail.com', 'Luisa Rodríguez', 'pass987', 'token4'),
(6, 'cliente6@yahoo.com', 'Ana Torres', 'pass321', 'token6'),
(7, 'cliente7@gmail.com', 'Lucía García', 'pass789', 'token7'),
(8, 'cliente8@hotmail.com', 'Santiago Martínez', 'pass123', 'token8'),
(9, 'cliente9@yahoo.com', 'Paola Gómez', 'pass456', 'token9'),
(10, 'cliente10@gmail.com', 'José Ramírez', 'pass987', 'token10');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `accesses`
--
ALTER TABLE `accesses`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `fk_customer_id_update` (`customer_id`);

--
-- Indices de la tabla `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `accesses`
--
ALTER TABLE `accesses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `admins`
--
ALTER TABLE `admins`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT de la tabla `customers`
--
ALTER TABLE `customers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `accesses`
--
ALTER TABLE `accesses`
  ADD CONSTRAINT `fk_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_customer_id_update` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
