-- phpMyAdmin SQL Dump
-- version 5.2.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mer. 07 jan. 2026 à 14:17
-- Version du serveur : 12.1.2-MariaDB
-- Version de PHP : 8.3.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `business`
--

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `id_client` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `mail` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_client`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id_client`, `first_name`, `last_name`, `mail`, `phone`) VALUES
(1, 'Eddy', 'Malou', 'eddie-malou@gmail.com', '0601020304'),
(2, 'Loup', 'Loup', 'lemalaimé@gmail.com', '0608008983'),
(3, 'Egg', 'Man', 'iloveeggs@gmail.com', '0718248412'),
(4, 'Papyrus', 'The great', 'thegreatpapyrus@nyehehe.com', '0401020304');

-- --------------------------------------------------------

--
-- Structure de la table `course`
--

DROP TABLE IF EXISTS `course`;
CREATE TABLE IF NOT EXISTS `course` (
  `id_course` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `length` int(11) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `price` decimal(8,2) DEFAULT NULL,
  `id_client` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_course`),
  KEY `id_client` (`id_client`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

--
-- Déchargement des données de la table `course`
--

INSERT INTO `course` (`id_course`, `name`, `description`, `length`, `type`, `price`, `id_client`) VALUES
(1, 'Java Python', 'Java Python', 20, 'Remote', 19.99, 1),
(2, 'Apprendre les lois du marché', 'La congolexicomatisation des lois du marché tout ça tout ça', 123, 'On-site', 123.00, 1),
(3, 'Egg', 'Egg.', 1, 'On-site', 0.01, 3),
(4, 'The unloved one\'s kitchen', 'Do you feel unloved by those around you ? Cook them.', 5, 'Remote', 0.00, 2),
(5, 'Human hunting tutorial', 'Learn how to hunt humans, approved by THE GREAT PAPYRUS!', 10, 'On-site', 0.00, 4),
(6, 'Italian cuisine', 'My amazing spaghetti will amaze you amazingly.', 10, 'On-site', 0.00, 4);

-- --------------------------------------------------------

--
-- Structure de la table `order_`
--

DROP TABLE IF EXISTS `order_`;
CREATE TABLE IF NOT EXISTS `order_` (
  `id_user` int(11) NOT NULL,
  `id_client` int(11) NOT NULL,
  `order_date` varchar(20) DEFAULT NULL,
  `total` decimal(8,2) NOT NULL,
  PRIMARY KEY (`id_user`,`id_client`),
  KEY `order_fk_2` (`id_client`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

--
-- Déchargement des données de la table `order_`
--

INSERT INTO `order_` (`id_user`, `id_client`, `order_date`, `total`) VALUES
(153, 2, '07/01/2026 15:10:47', 0.00);

-- --------------------------------------------------------

--
-- Structure de la table `user_`
--

DROP TABLE IF EXISTS `user_`;
CREATE TABLE IF NOT EXISTS `user_` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=154 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

--
-- Déchargement des données de la table `user_`
--

INSERT INTO `user_` (`id_user`, `login`, `password`) VALUES
(153, 'Java', '2025');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `course`
--
ALTER TABLE `course`
  ADD CONSTRAINT `course_fk` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`);

--
-- Contraintes pour la table `order_`
--
ALTER TABLE `order_`
  ADD CONSTRAINT `order_fk_1` FOREIGN KEY (`id_user`) REFERENCES `user_` (`id_user`),
  ADD CONSTRAINT `order_fk_2` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
