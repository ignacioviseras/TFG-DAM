-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-04-2023 a las 19:56:32
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
  `expires` int(11) NOT NULL,
  `availables` int(11) NOT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `admin_id` int(11) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `accesses`
--

INSERT INTO `accesses` (`id`, `expires`, `availables`, `uuid`, `admin_id`, `customer_id`) VALUES
(1, 1642009600, 10, 'abc123', 1, 5),
(2, 1642096000, 15, 'def456', 2, 4),
(3, 1642182400, 20, 'ghi789', 3, 7),
(4, 1642268800, 5, 'jkl012', 4, 1),
(5, 1642355200, 30, 'mno345', 5, 8),
(6, 1642441600, 25, 'pqr678', 6, 3),
(7, 1642528000, 10, 'stu901', 7, 9),
(8, 1642614400, 15, 'vwx234', 8, 2),
(9, 1642700800, 20, 'yz567', 9, 6),
(10, 1642787200, 5, 'abc890', 10, 10),
(11, 1642873600, 10, 'def123', 1, 4),
(12, 1642960000, 15, 'ghi456', 2, 5),
(13, 1643046400, 20, 'jkl789', 3, 8),
(14, 1643132800, 5, 'mno012', 4, 2);

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
(2, 'admin2@example.com', 'Jane Smith', 'password456', 'token456'),
(3, 'admin3@example.com', 'Bob Johnson', 'password789', 'token789'),
(4, 'admin4@example.com', 'Emily Brown', 'passwordabc', 'tokenabc'),
(5, 'admin5@example.com', 'Alexandra Davis', 'passworddef', 'tokendef'),
(6, 'admin6@example.com', 'Mark Thompson', 'passwordghi', 'tokenghi'),
(7, 'admin7@example.com', 'Samantha Lee', 'passwordjkl', 'tokenjkl'),
(8, 'admin8@example.com', 'David Wilson', 'passwordmno', 'tokenmno'),
(9, 'admin9@example.com', 'Rachel Taylor', 'passwordpqr', 'tokenpqr'),
(10, 'admin10@example.com', 'Michael Brown', 'passwordstu', 'tokenstu'),
(21, 'admin1@example.com', 'John Doe', 'password123', 'token123');

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
(2, 'cliente2@hotmail.com', 'María González', 'pass456', 'token2'),
(3, 'cliente3@yahoo.com', 'Pedro Hernández', 'pass789', 'token3'),
(4, 'cliente4@gmail.com', 'Luisa Rodríguez', 'pass987', 'token4'),
(5, 'cliente5@hotmail.com', 'Roberto Sánchez', 'pass654', 'token5'),
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
  ADD PRIMARY KEY (`id`) USING BTREE;

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

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