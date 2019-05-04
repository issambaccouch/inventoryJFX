-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mar. 16 avr. 2019 à 01:46
-- Version du serveur :  5.7.21
-- Version de PHP :  5.6.35

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `pi`
--

-- --------------------------------------------------------

--
-- Structure de la table `article`
--

DROP TABLE IF EXISTS `article`;
CREATE TABLE IF NOT EXISTS `article` (
  `idart` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `titre` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `imageart` varchar(255) DEFAULT NULL,
  `videoart` varchar(255) DEFAULT NULL,
  `nbrvue` int(11) DEFAULT NULL,
  `dateajout` date DEFAULT NULL,
  `nbrrec` int(11) DEFAULT NULL,
  `idtag` int(11) NOT NULL,
  PRIMARY KEY (`idart`),
  KEY `iduser` (`iduser`),
  KEY `idtag` (`idtag`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `categorieprod`
--

DROP TABLE IF EXISTS `categorieprod`;
CREATE TABLE IF NOT EXISTS `categorieprod` (
  `idcp` int(11) NOT NULL AUTO_INCREMENT,
  `nomcp` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idcp`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `categorieprod`
--

INSERT INTO `categorieprod` (`idcp`, `nomcp`) VALUES
(1, 'Vêtement Homme'),
(2, 'Vêtement Femme'),
(3, 'Livre'),
(4, 'Multimédia'),
(5, 'Test'),
(6, 'Voitures');

-- --------------------------------------------------------

--
-- Structure de la table `categoriews`
--

DROP TABLE IF EXISTS `categoriews`;
CREATE TABLE IF NOT EXISTS `categoriews` (
  `idcw` int(11) NOT NULL,
  `nomcws` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idcw`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `df_category`
--

DROP TABLE IF EXISTS `df_category`;
CREATE TABLE IF NOT EXISTS `df_category` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `disp_position` int(10) UNSIGNED DEFAULT NULL,
  `read_authorised_roles` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `df_category`
--

INSERT INTO `df_category` (`id`, `name`, `disp_position`, `read_authorised_roles`) VALUES
(6, 'Plastic', 0, 'ROLE_USER'),
(7, 'Wood', 1, 'ROLE_USER'),
(10, 'Rubber', 0, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Structure de la table `df_forum`
--

DROP TABLE IF EXISTS `df_forum`;
CREATE TABLE IF NOT EXISTS `df_forum` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` smallint(6) NOT NULL,
  `name` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `slug` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `image_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `disp_position` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_6734FB05989D9B62` (`slug`),
  KEY `IDX_6734FB0512469DE2` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `df_forum`
--

INSERT INTO `df_forum` (`id`, `category_id`, `name`, `description`, `slug`, `image_url`, `disp_position`) VALUES
(11, 6, 'Plastic Recycling', 'Learn how to recycling', 'plastic-recycling', 'https://www.shopcoffee.co.uk/wp-content/uploads/2017/02/30-Gaggia-Decalcificante-Descaler-250ml-100x100.jpg', 0),
(12, 6, 'Plastic Effects', 'Plastic Effects', 'plastic-effects', 'https://yt3.ggpht.com/a-/AAuE7mBx8o2FjmCC2-XTX0q0Bw8NIy9qogBSlckJww=s100-mo-c-c0xffffffff-rj-k-no', 0),
(13, 6, 'Bioplastics', 'Plastics derived from renewable biomass sources', 'bioplastics', 'http://williamernest.com/wp-content/uploads/2011/05/290976-Cereplast_bioplastic_symbol_100x100.jpg', 0),
(14, 7, 'Wood Recycling', 'Learn how to recycle wood', 'wood-recycling', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQnWGXiG3O5R5UtiLCRVqVf6yMBdb_-YVCVOY_VpB2HIeSlP9ou', 0),
(15, 7, 'Wood Arts', 'Learn Making Art With Wood', 'wood-arts', 'http://www.turkishculture.org/images/page/applied_arts/wood_artwork/chests/chest2_t.jpg', 0),
(17, 10, 'Rubber Recycling', 'Learn Recycling', 'rubber-recycling', 'https://eldan-recycling.com/sites/default/files/styles/thumbnail_100x100_force-fill/public/Case_SharjahNationalCrumbRubber_2_kompr.jpg?itok=RaJ_0FaU', 0),
(18, 10, 'Rubber For Sale', 'Buy and Sell', 'rubber-for-sale', 'https://image.made-in-china.com/3f2j00NFLQaTnMgvzG/Rubber-Sheet-Rubber-Conveyor-Belting-for-Sale.jpg', 0),
(19, 10, 'Rubber Utilization', 'Learn How To Deal with With Rubber', 'rubber-utilization', 'https://www.marklines.com/statics/exhibitions/img/sape2016/small/sumitomorubber_IMG_3836_s.jpg', 0);

-- --------------------------------------------------------

--
-- Structure de la table `df_post`
--

DROP TABLE IF EXISTS `df_post`;
CREATE TABLE IF NOT EXISTS `df_post` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `topic_id` int(10) UNSIGNED NOT NULL,
  `poster_id` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `content` longtext COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_FDB5B0441F55203D` (`topic_id`),
  KEY `IDX_FDB5B0445BB66C05` (`poster_id`),
  KEY `IDX_FDB5B04416FE72E1` (`updated_by`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `df_post`
--

INSERT INTO `df_post` (`id`, `topic_id`, `poster_id`, `updated_by`, `content`, `created_at`, `updated_at`) VALUES
(11, 11, 3, NULL, '<p>Topic one post&nbsp;</p>', '2019-02-27 01:52:08', NULL),
(12, 11, 3, NULL, '<p>post thania&nbsp;</p>', '2019-02-27 01:56:43', NULL),
(13, 11, 3, NULL, '<h2 style=\"font-style:italic\">post thaltha&nbsp;</h2>', '2019-02-27 01:57:32', NULL),
(14, 11, 3, NULL, 'post thaltha', '2019-02-27 01:58:03', NULL),
(15, 11, 3, NULL, 'forth post', '2019-02-27 01:58:25', NULL),
(16, 12, 3, NULL, 'topic thania', '2019-02-27 02:00:03', NULL),
(17, 12, 3, NULL, 'commet 3al topic two', '2019-02-27 02:00:27', NULL),
(18, 13, 3, 3, 'lowel 3al topic 3', '2019-02-27 02:01:48', '2019-02-27 09:48:22'),
(19, 14, 3, NULL, 'commentair 3al effet', '2019-02-27 02:39:41', NULL),
(20, 14, 3, 3, 'commetaire louel', '2019-02-27 02:39:59', '2019-02-27 03:03:52'),
(21, 15, 3, NULL, 'commentaire theni', '2019-02-27 03:04:17', NULL),
(22, 16, 3, NULL, 'commentaire theleth', '2019-02-27 03:05:33', NULL),
(23, 17, 3, NULL, 'commentaire 1', '2019-02-27 03:10:24', NULL),
(24, 18, 3, NULL, 'commentaire 2', '2019-02-27 03:11:18', NULL),
(25, 19, 3, NULL, '<p>commentaire 3</p>', '2019-02-27 03:12:56', NULL),
(26, 20, 3, NULL, 'commentaire 1', '2019-02-27 03:15:12', NULL),
(27, 21, 3, NULL, 'commentaire 2', '2019-02-27 03:15:51', NULL),
(28, 22, 3, NULL, 'commentaire 3', '2019-02-27 03:20:40', NULL),
(29, 23, 3, NULL, 'commentaire 1', '2019-02-27 03:21:23', NULL),
(30, 24, 3, NULL, 'commentaire 2', '2019-02-27 03:21:54', NULL),
(31, 25, 3, NULL, 'commentaire 3', '2019-02-27 03:22:13', NULL),
(32, 26, 3, NULL, 'commentaire 1', '2019-02-27 03:22:51', NULL),
(33, 27, 3, NULL, 'commentaire 2', '2019-02-27 03:23:30', NULL),
(34, 28, 3, NULL, 'commentaire 3', '2019-02-27 03:24:02', NULL),
(35, 29, 3, NULL, 'commentaire 1', '2019-02-27 03:24:42', NULL),
(36, 30, 3, NULL, 'commentaire 2', '2019-02-27 03:25:04', NULL),
(37, 31, 3, NULL, 'commentaire 3', '2019-02-27 03:25:35', NULL),
(38, 32, 3, NULL, 'commentaire 1', '2019-02-27 03:26:13', NULL),
(39, 33, 3, NULL, 'commentaire 2', '2019-02-27 03:26:34', NULL),
(40, 34, 3, 3, 'commentaire 3', '2019-02-27 03:26:49', '2019-02-27 03:27:47'),
(41, 35, 3, NULL, 'commentaire 1', '2019-02-27 03:30:17', NULL),
(42, 36, 3, 3, 'commentaire 2', '2019-02-27 03:30:30', '2019-02-27 03:30:44'),
(43, 37, 3, NULL, 'commentaire 3', '2019-02-27 03:31:12', NULL),
(45, 13, 3, NULL, '<p>[quote=admin]lowel 3al topic 3[/quote]</p>\r\n\r\n<p>guthguthgutg</p>', '2019-02-27 09:49:04', NULL),
(46, 13, 3, NULL, '<p>gtgtg</p>', '2019-02-27 09:52:48', NULL),
(47, 13, 3, NULL, '<p>gtgtgt</p>', '2019-02-27 09:52:56', NULL),
(48, 13, 3, NULL, '<p>gtgtgtg</p>', '2019-02-27 09:53:02', NULL),
(49, 13, 3, NULL, '<p>tgtgtgtg</p>', '2019-02-27 09:53:09', NULL),
(50, 13, 3, NULL, '<p>tgtgtgt</p>', '2019-02-27 09:53:16', NULL),
(51, 13, 3, NULL, '<p>gtgtgtgtgtg</p>', '2019-02-27 09:53:22', NULL),
(52, 13, 3, NULL, '<p>rgrgrgr</p>', '2019-02-27 09:53:39', NULL),
(53, 13, 3, NULL, '<p>grgrgrgrgrgrgggrgrrgrgrgrg</p>', '2019-02-27 09:53:49', NULL),
(54, 13, 3, NULL, '<p>grgrgrgr</p>', '2019-02-27 09:53:56', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `df_topic`
--

DROP TABLE IF EXISTS `df_topic`;
CREATE TABLE IF NOT EXISTS `df_topic` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `forum_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `slug` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `pinned` tinyint(1) NOT NULL,
  `resolved` tinyint(1) NOT NULL,
  `closed` tinyint(1) NOT NULL,
  `last_post` datetime DEFAULT NULL,
  `views` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_7F5F9BD3989D9B62` (`slug`),
  KEY `IDX_7F5F9BD329CCBAD0` (`forum_id`),
  KEY `IDX_7F5F9BD3A76ED395` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `df_topic`
--

INSERT INTO `df_topic` (`id`, `forum_id`, `user_id`, `title`, `slug`, `created_at`, `pinned`, `resolved`, `closed`, `last_post`, `views`) VALUES
(11, 11, 3, 'Topic one', 'topic-one', '2019-02-27 01:52:08', 0, 0, 0, '2019-02-27 01:58:25', 5),
(12, 11, 3, 'Topic Two', 'topic-two', '2019-02-27 02:00:03', 0, 0, 0, '2019-02-27 02:00:27', 3),
(13, 11, 3, 'Topic thaltha', 'topic-thaltha', '2019-02-27 02:01:47', 1, 0, 0, '2019-02-27 09:53:56', 37),
(14, 12, 3, 'Effects loula', 'effects-loula', '2019-02-27 02:39:40', 0, 0, 0, '2019-02-27 02:39:59', 6),
(15, 12, 3, 'Effects thenya', 'effects-thenya', '2019-02-27 03:04:17', 0, 0, 0, '2019-02-27 03:04:17', 2),
(16, 12, 3, 'Effects theltha', 'effects-theltha', '2019-02-27 03:05:33', 0, 0, 0, '2019-02-27 03:05:33', 5),
(17, 13, 3, 'Bioplatics 1', 'bioplatics-1', '2019-02-27 03:10:24', 0, 0, 0, '2019-02-27 03:10:24', 1),
(18, 13, 3, 'Bioplatics 2', 'bioplatics-2', '2019-02-27 03:11:18', 0, 0, 0, '2019-02-27 03:11:18', 1),
(19, 13, 3, 'Bioplatics 3', 'bioplatics-3', '2019-02-27 03:12:56', 0, 0, 0, '2019-02-27 03:12:56', 2),
(20, 17, 3, 'Rubber Recycling 1', 'rubber-recycling-1', '2019-02-27 03:15:12', 0, 0, 0, '2019-02-27 03:15:12', 2),
(21, 17, 3, 'Rubber Recycling 2', 'rubber-recycling-2', '2019-02-27 03:15:50', 0, 0, 0, '2019-02-27 03:15:51', 1),
(22, 17, 3, 'Rubber Recycling 3', 'rubber-recycling-3', '2019-02-27 03:20:39', 0, 0, 0, '2019-02-27 03:20:40', 1),
(23, 18, 3, 'Rubber For Sale', 'rubber-for-sale', '2019-02-27 03:21:23', 0, 0, 0, '2019-02-27 03:21:23', 1),
(24, 18, 3, 'Rubber For Sale 2', 'rubber-for-sale-2', '2019-02-27 03:21:53', 0, 0, 0, '2019-02-27 03:21:54', 1),
(25, 18, 3, 'Rubber For Sale 3', 'rubber-for-sale-3', '2019-02-27 03:22:12', 0, 0, 0, '2019-02-27 03:22:13', 1),
(26, 19, 3, 'Rubber Utilization 1', 'rubber-utilization-1', '2019-02-27 03:22:51', 0, 0, 0, '2019-02-27 03:22:51', 1),
(27, 19, 3, 'Rubber Utilization 2', 'rubber-utilization-2', '2019-02-27 03:23:30', 0, 0, 0, '2019-02-27 03:23:30', 1),
(28, 19, 3, 'Rubber Utilization 3', 'rubber-utilization-3', '2019-02-27 03:24:02', 0, 0, 0, '2019-02-27 03:24:02', 1),
(29, 14, 3, 'Wood Recycling', 'wood-recycling', '2019-02-27 03:24:42', 0, 0, 0, '2019-02-27 03:24:42', 2),
(30, 14, 3, 'Wood Recycling 2', 'wood-recycling-2', '2019-02-27 03:25:04', 0, 0, 0, '2019-02-27 03:25:04', 2),
(31, 14, 3, 'Wood Recycling 3', 'wood-recycling-3', '2019-02-27 03:25:35', 0, 0, 0, '2019-02-27 03:25:35', 3),
(32, 15, 3, 'Wood Arts', 'wood-arts', '2019-02-27 03:26:13', 0, 0, 0, '2019-02-27 03:26:13', 2),
(33, 15, 3, 'Wood Arts 2', 'wood-arts-2', '2019-02-27 03:26:34', 0, 0, 0, '2019-02-27 03:26:34', 3),
(34, 15, 3, 'Wood Arts 3', 'wood-arts-3', '2019-02-27 03:26:48', 0, 0, 0, '2019-02-27 03:26:49', 3),
(35, 11, 3, 'Wood Market', 'wood-market', '2019-02-27 03:30:16', 0, 0, 0, '2019-02-27 03:30:17', 1),
(36, 11, 3, 'Wood Market 2', 'wood-market-2', '2019-02-27 03:30:30', 0, 0, 0, '2019-02-27 03:30:30', 3),
(37, 11, 3, 'Wood Market 3', 'wood-market-3', '2019-02-27 03:31:12', 0, 0, 0, '2019-02-27 03:31:12', 1);

-- --------------------------------------------------------

--
-- Structure de la table `evenement`
--

DROP TABLE IF EXISTS `evenement`;
CREATE TABLE IF NOT EXISTS `evenement` (
  `idev` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `nomev` varchar(255) DEFAULT NULL,
  `datedebut` date DEFAULT NULL,
  `datefin` date DEFAULT NULL,
  `etatev` int(11) DEFAULT NULL,
  `imageev` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `nbrparticipants` int(11) DEFAULT NULL,
  `lieu` varchar(255) NOT NULL,
  PRIMARY KEY (`idev`),
  KEY `iduser` (`iduser`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `favoris`
--

DROP TABLE IF EXISTS `favoris`;
CREATE TABLE IF NOT EXISTS `favoris` (
  `idfav` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `idcp` int(11) NOT NULL,
  PRIMARY KEY (`idfav`),
  KEY `iduser` (`iduser`),
  KEY `idcp` (`idcp`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `followers`
--

DROP TABLE IF EXISTS `followers`;
CREATE TABLE IF NOT EXISTS `followers` (
  `idfoll` int(11) NOT NULL,
  `idcw` int(11) NOT NULL,
  `idadherent` int(11) NOT NULL,
  `idformateur` int(11) NOT NULL,
  PRIMARY KEY (`idfoll`),
  KEY `idcw` (`idcw`),
  KEY `idadherent` (`idadherent`),
  KEY `idformateur` (`idformateur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `fos_user`
--

DROP TABLE IF EXISTS `fos_user`;
CREATE TABLE IF NOT EXISTS `fos_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `username_canonical` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email_canonical` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `confirmation_token` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '(DC2Type:array)',
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_957A647992FC23A8` (`username_canonical`),
  UNIQUE KEY `UNIQ_957A6479A0D96FBF` (`email_canonical`),
  UNIQUE KEY `UNIQ_957A6479C05FB297` (`confirmation_token`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `fos_user`
--

INSERT INTO `fos_user` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`, `nom`, `prenom`) VALUES
(2, 'azer', 'azer', 'azer@azer.com', 'azer@azer.com', 1, NULL, '$2y$13$VxNkUhyt/N8mr3oa5VEVL.GV41T3/CgcsNar3e2qEfx4.8f9IX6Ru', '2019-02-19 20:25:00', NULL, NULL, 'a:1:{i:0;s:10:\"ROLE_ADMIN\";}', 'issam', 'lkol'),
(3, 'admin', 'admin', 'admin@admin.com', 'admin@admin.com', 1, NULL, '$2y$13$MLpXffTZ.y4nqYZYNgPx9ujVPduMQ39Fan./vWeBmLxC/LYVGO/qe', '2019-03-27 12:55:15', NULL, NULL, 'a:2:{i:0;s:10:\"ROLE_ADMIN\";i:1;s:14:\"ROLE_MODERATOR\";}', 'Admin', 'Admin'),
(4, 'med', 'med', 'med@med.com', 'med@med.com', 1, NULL, '$2y$13$7BPsWuMfZZJN3s5l8MWohOFeB3U276facXd.ZDjrBs/VImoXdlP/y', '2019-02-17 09:57:51', NULL, NULL, 'a:0:{}', 'med', 'med'),
(5, 'user', 'user', 'user@user.com', 'user@user.com', 1, NULL, '$2y$13$eQ0rRzTY24o7i0q850Mobe5vyBPzacfjhzGbBQnONFkqnfKCXZabe', '2019-02-20 11:09:29', NULL, NULL, 'a:1:{i:0;s:9:\"ROLE_USER\";}', 'user', 'user'),
(6, 'a', NULL, 'a', NULL, NULL, NULL, 'a', NULL, NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'a', 'a'),
(7, 'aaaaa', NULL, 'aaaa', NULL, NULL, NULL, 'aaaa', NULL, NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'aaaa', 'aaaa');

-- --------------------------------------------------------

--
-- Structure de la table `likes`
--

DROP TABLE IF EXISTS `likes`;
CREATE TABLE IF NOT EXISTS `likes` (
  `idl` int(11) NOT NULL,
  `nbrl` int(11) DEFAULT NULL,
  PRIMARY KEY (`idl`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

DROP TABLE IF EXISTS `produit`;
CREATE TABLE IF NOT EXISTS `produit` (
  `idpr` int(11) NOT NULL AUTO_INCREMENT,
  `nomp` varchar(255) DEFAULT NULL,
  `prix` double DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `imagep` varchar(255) DEFAULT NULL,
  `etatpr` int(11) DEFAULT NULL,
  `enpromo` int(11) DEFAULT NULL,
  `iduser` int(11) NOT NULL,
  `idcp` int(11) DEFAULT NULL,
  `date_exp` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `views` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idpr`),
  KEY `iduser` (`iduser`),
  KEY `idcp` (`idcp`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`idpr`, `nomp`, `prix`, `description`, `imagep`, `etatpr`, `enpromo`, `iduser`, `idcp`, `date_exp`, `views`) VALUES
(8, 'Ordinateur Apple', 15, '16Go RAM\n256GO SSd\n36\"', 'C:/Users/Tryvl/Desktop/images/41Qj8q-atzL._SX355_.jpg', 1, 0, 2, 1, '2019-04-30', 10),
(10, 'Produit 5', 105, 'test', 'C:/Users/Tryvl/Desktop/images/2017-hip-hop-men-and-women-short-sleeve-t.jpg', 1, 0, 4, 3, '2018-04-15', 5),
(20, 'prodddd', 2000, 'dddd', 'C:/Users/Tryvl/Desktop/images/bgr-bgr-galaxy-s10-8.jpg', 1, 0, 6, 3, '2019-04-30', 1),
(22, 'dde', 200, 'dede', 'C:/Users/Tryvl/Desktop/images/19f4dea7-3ff4-4b11-a7af-722ed03cf650.jpg', 1, 0, 2, 4, '2019-04-17', 0),
(26, 'produit', 520, 'description', 'C:/Users/Tryvl/Desktop/images/7792117003_carles-puigdemont-va-louer-une-maison-a-bruxelles-a-4-400-euros-par-mois.jpg', 1, 0, 2, 1, '2019-04-30', 0),
(27, 'Produit Admin', 520, 'szszsz', 'C:/Users/Tryvl/Desktop/images/11f1dm.jpg', 1, 0, 2, 5, '2019-04-30', 0);

-- --------------------------------------------------------

--
-- Structure de la table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
CREATE TABLE IF NOT EXISTS `promotion` (
  `idpromo` int(11) NOT NULL AUTO_INCREMENT,
  `pourcentage` float DEFAULT NULL,
  `datefinpro` date DEFAULT NULL,
  `idpr` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `idcp` int(11) NOT NULL,
  PRIMARY KEY (`idpromo`),
  KEY `iduser` (`iduser`),
  KEY `idpr` (`idpr`),
  KEY `idcp` (`idcp`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `proposition`
--

DROP TABLE IF EXISTS `proposition`;
CREATE TABLE IF NOT EXISTS `proposition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cat` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `content` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `proposition`
--

INSERT INTO `proposition` (`id`, `cat`, `content`) VALUES
(1, 'plastic', 'forum category plastic'),
(2, 'wood', 'forum category wood'),
(3, 'rubber', 'forum category rubber ');

-- --------------------------------------------------------

--
-- Structure de la table `question`
--

DROP TABLE IF EXISTS `question`;
CREATE TABLE IF NOT EXISTS `question` (
  `idq` int(11) NOT NULL,
  `textq` varchar(255) DEFAULT NULL,
  `dateq` date DEFAULT NULL,
  PRIMARY KEY (`idq`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

DROP TABLE IF EXISTS `reclamation`;
CREATE TABLE IF NOT EXISTS `reclamation` (
  `idrec` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `idart` int(11) NOT NULL,
  `daterec` date DEFAULT NULL,
  PRIMARY KEY (`idrec`),
  KEY `iduser` (`iduser`),
  KEY `idart` (`idart`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `reponse`
--

DROP TABLE IF EXISTS `reponse`;
CREATE TABLE IF NOT EXISTS `reponse` (
  `idrep` int(11) NOT NULL,
  `textrep` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idrep`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tags`
--

DROP TABLE IF EXISTS `tags`;
CREATE TABLE IF NOT EXISTS `tags` (
  `idtag` int(11) NOT NULL,
  `nomtag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idtag`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `workshop`
--

DROP TABLE IF EXISTS `workshop`;
CREATE TABLE IF NOT EXISTS `workshop` (
  `idws` int(11) NOT NULL,
  `nomws` varchar(255) DEFAULT NULL,
  `datews` date DEFAULT NULL,
  `lieu` varchar(255) DEFAULT NULL,
  `nbrws` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `etatws` int(11) DEFAULT NULL,
  `iduser` int(11) NOT NULL,
  `idcw` int(11) NOT NULL,
  PRIMARY KEY (`idws`),
  KEY `iduser` (`iduser`),
  KEY `idcw` (`idcw`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `article`
--
ALTER TABLE `article`
  ADD CONSTRAINT `article_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `fos_user` (`id`),
  ADD CONSTRAINT `article_ibfk_2` FOREIGN KEY (`idtag`) REFERENCES `tags` (`idtag`);

--
-- Contraintes pour la table `df_forum`
--
ALTER TABLE `df_forum`
  ADD CONSTRAINT `FK_6734FB0512469DE2` FOREIGN KEY (`category_id`) REFERENCES `df_category` (`id`);

--
-- Contraintes pour la table `df_post`
--
ALTER TABLE `df_post`
  ADD CONSTRAINT `FK_FDB5B04416FE72E1` FOREIGN KEY (`updated_by`) REFERENCES `fos_user` (`id`),
  ADD CONSTRAINT `FK_FDB5B0441F55203D` FOREIGN KEY (`topic_id`) REFERENCES `df_topic` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_FDB5B0445BB66C05` FOREIGN KEY (`poster_id`) REFERENCES `fos_user` (`id`);

--
-- Contraintes pour la table `df_topic`
--
ALTER TABLE `df_topic`
  ADD CONSTRAINT `FK_7F5F9BD329CCBAD0` FOREIGN KEY (`forum_id`) REFERENCES `df_forum` (`id`),
  ADD CONSTRAINT `FK_7F5F9BD3A76ED395` FOREIGN KEY (`user_id`) REFERENCES `fos_user` (`id`);

--
-- Contraintes pour la table `evenement`
--
ALTER TABLE `evenement`
  ADD CONSTRAINT `evenement_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `fos_user` (`id`);

--
-- Contraintes pour la table `favoris`
--
ALTER TABLE `favoris`
  ADD CONSTRAINT `favoris_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `fos_user` (`id`),
  ADD CONSTRAINT `favoris_ibfk_2` FOREIGN KEY (`idcp`) REFERENCES `categorieprod` (`idcp`);

--
-- Contraintes pour la table `followers`
--
ALTER TABLE `followers`
  ADD CONSTRAINT `followers_ibfk_1` FOREIGN KEY (`idcw`) REFERENCES `categoriews` (`idcw`),
  ADD CONSTRAINT `followers_ibfk_2` FOREIGN KEY (`idadherent`) REFERENCES `fos_user` (`id`),
  ADD CONSTRAINT `followers_ibfk_3` FOREIGN KEY (`idformateur`) REFERENCES `fos_user` (`id`);

--
-- Contraintes pour la table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `produit_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `fos_user` (`id`),
  ADD CONSTRAINT `produit_ibfk_2` FOREIGN KEY (`idcp`) REFERENCES `categorieprod` (`idcp`);

--
-- Contraintes pour la table `promotion`
--
ALTER TABLE `promotion`
  ADD CONSTRAINT `promotion_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `fos_user` (`id`),
  ADD CONSTRAINT `promotion_ibfk_2` FOREIGN KEY (`idpr`) REFERENCES `produit` (`idpr`),
  ADD CONSTRAINT `promotion_ibfk_3` FOREIGN KEY (`idcp`) REFERENCES `categorieprod` (`idcp`);

--
-- Contraintes pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD CONSTRAINT `reclamation_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `fos_user` (`id`),
  ADD CONSTRAINT `reclamation_ibfk_2` FOREIGN KEY (`idart`) REFERENCES `article` (`idart`);

--
-- Contraintes pour la table `workshop`
--
ALTER TABLE `workshop`
  ADD CONSTRAINT `workshop_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `fos_user` (`id`),
  ADD CONSTRAINT `workshop_ibfk_2` FOREIGN KEY (`idcw`) REFERENCES `categoriews` (`idcw`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
