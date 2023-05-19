-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mer. 31 mars 2021 à 01:41
-- Version du serveur :  10.4.11-MariaDB
-- Version de PHP : 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `spirity`
--

-- --------------------------------------------------------

--
-- Structure de la table `act`
--

CREATE TABLE `act` (
  `id_act` int(11) NOT NULL,
  `nom_act` varchar(50) NOT NULL,
  `type_act` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `act`
--

INSERT INTO `act` (`id_act`, `nom_act`, `type_act`) VALUES
(1, 'mp', 'yoga');

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE `admin` (
  `id_user` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` text NOT NULL,
  `mail` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`id_user`, `username`, `password`, `mail`) VALUES
(1, '', '', ''),
(2, '', '', ''),
(3, 'sffsfs', 'fsfsfsfsfs', 'fsfsfsfs'),
(4, 'sffsfs', 'fsfsfsfsfs', 'fsfsfsfs'),
(5, '', '', ''),
(6, '', '', ''),
(7, '', '', ''),
(8, 'arem', '$2a$13$GQZHj3cTWZ/M6YMbsmsKTuKsJ6IjQ3l5j0xiBNXzm3CLq6sbqhWrq', 'arem@aa.aa'),
(9, 'arem', '$2a$13$Ql0F/kMSWkpKQcvs88HuE.CfYZbvdImAAja7FrJ80iSHtZXoZgRFa', 'arem@aa.aa'),
(10, 'Sana', '$2a$13$zucjGiWa75Ix4rvwEJr8LucgM7grHYoTYMhFotTAVrig90y95cE8a', 'sana@sana.sa'),
(11, 'opop', '$2a$13$mCDxmuwBquRMbhNIjg4NE.lXIU62WxyjfiN83rUS8k541Vv1XUrMK', 'mmm@ytg.ji'),
(12, 'fefefe', '$2a$13$JxtxaUM/LtoeNiDZNNHo9uBrItyGOFhXQiIyNXD6.vLvzYYcO9U26', 'dedede@deded.de'),
(13, 'azer', '$2a$13$WJpBN8dddD12Pl/8ftxoyOR4OO62ENFS3ejv7YZ0HxASNLJTkc.xi', 'azer@azer.az'),
(14, 'Mourad', '$2a$13$dHa.wZRQWNPLQU6O5hXm0utl3nlvQCzpLrlPJO5cQrdJS5ke9G7he', 'mourad@esprit.tn'),
(16, 'mgkadmin', '$2a$13$h84ZE897OC3M1c1TghADruMr6ATIvqKQ8lbV32NqqWTXo.46eqwxK', 'mgkadmin@mgk2.tn');

-- --------------------------------------------------------

--
-- Structure de la table `articles`
--

CREATE TABLE `articles` (
  `id_art` int(11) NOT NULL,
  `titre_art` varchar(255) NOT NULL,
  `auteur_art` varchar(255) DEFAULT NULL,
  `description_art` varchar(1500) DEFAULT NULL,
  `date_art` datetime DEFAULT current_timestamp(),
  `likes` int(11) DEFAULT 0,
  `id_cat` int(11) NOT NULL,
  `photo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `articles`
--

INSERT INTO `articles` (`id_art`, `titre_art`, `auteur_art`, `description_art`, `date_art`, `likes`, `id_cat`, `photo`) VALUES
(22, 'régime guide complet pour perdre du poids', 'mgknutri', 'Pour perdre du poids durablement il est essentiel de combiner des changements alimentaires ET une activité sportive\nface à ce double défi certains régimes proposent de véritables plans d\'exercices\nnos experts ont élu les meilleurs régimes qui associent harmonieusement rééquilibrage alimentaire et activité physique', '2021-03-27 02:11:23', 2, 6, 'file:/C:/xampp/htdocs/img/palmares-des-regimes.jpg'),
(23, 'pourquoi aimons nous dessiner', 'test1Coach', 'Dessiner nous pousse à expérimenter des méthodes\ndes techniques afin de se rapprocher le plus possible de la réalité dans le but de la figer dans le temps \nà tout jamais ou au contraire à s’en éloigner tout en l’exprimant à travers notre prisme émotionnel', '2021-03-27 02:22:15', 2, 61, 'file:/C:/xampp/htdocs/img/téléchargement.jpg'),
(24, 'les bienfaits du sport sur la santé', 'mgkcoach', 'L\'activité physique est également un élément de prévention essentiel pour garder des os solides \net prévenir ainsi l\'ostéoporose Pratiquer un sport permet de prévenir les lombalgies et la récurrence des symptômes\nLe renforcement musculaire occasionné lors des exercices physiques est aussi bénéfique pour les rhumatismes inflammatoires chroniques', '2021-03-27 02:26:17', 4, 23, 'file:/C:/xampp/htdocs/img/sp.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE `categorie` (
  `id_cat` int(11) NOT NULL,
  `titre_cat` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`id_cat`, `titre_cat`) VALUES
(61, 'arts'),
(27, 'Méditation'),
(6, 'Nourriture'),
(23, 'santé'),
(5, 'sport');

-- --------------------------------------------------------

--
-- Structure de la table `coach`
--

CREATE TABLE `coach` (
  `id_user` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` text NOT NULL,
  `mail` varchar(50) NOT NULL,
  `date_n` date NOT NULL,
  `code` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `coach`
--

INSERT INTO `coach` (`id_user`, `username`, `password`, `mail`, `date_n`, `code`) VALUES
(111, 'fff', 'fff', 'fff', '2021-03-01', 'fff'),
(784, 'mgkcoach', '$2a$13$qBxEx/7EVV03YoUof1xGzOKVcAZyenXXL9SRGvljEpzZr2vXFwOgG', 'mgkcoach@mgk2.tn', '2021-03-11', 'ompo');

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

CREATE TABLE `commentaire` (
  `id_com` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_pub` int(11) NOT NULL,
  `suj_com` varchar(500) DEFAULT NULL,
  `date_com` timestamp NOT NULL DEFAULT current_timestamp(),
  `nb_reaction` int(250) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `commentaire`
--

INSERT INTO `commentaire` (`id_com`, `id_user`, `id_pub`, `suj_com`, `date_com`, `nb_reaction`) VALUES
(13, 5, 4, 'aaslema', '2021-03-10 21:51:07', 2),
(15, 5, 4, 'aaslema beslema', '2021-03-10 23:30:22', 1),
(17, 5, 4, 'aaaaa', '2021-03-11 00:19:45', 0),
(18, 5, 4, 'kkk', '2021-03-11 08:36:03', 0),
(19, 5, 4, 'Yes together', '2021-03-18 22:12:01', 0),
(21, 4, 4, 'no no', '2021-03-19 23:22:29', 0);

-- --------------------------------------------------------

--
-- Structure de la table `evenement`
--

CREATE TABLE `evenement` (
  `id_ev` int(11) NOT NULL,
  `titre_ev` varchar(80) NOT NULL,
  `type_ev` varchar(50) NOT NULL,
  `emplacement_ev` varchar(30) NOT NULL,
  `date_dev` date NOT NULL,
  `date_fev` date NOT NULL,
  `temps_dev` time NOT NULL,
  `temps_fev` time NOT NULL,
  `age_min` int(11) DEFAULT NULL,
  `age_max` int(11) DEFAULT NULL,
  `id_act` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `evenement`
--

INSERT INTO `evenement` (`id_ev`, `titre_ev`, `type_ev`, `emplacement_ev`, `date_dev`, `date_fev`, `temps_dev`, `temps_fev`, `age_min`, `age_max`, `id_act`) VALUES
(3, 'islem', 'sportif', 'ml', '2021-03-04', '2021-03-20', '15:00:00', '15:00:00', 13, 26, 1),
(73, 'hello', 'sportif', 'from', '2021-03-12', '2021-03-12', '07:00:00', '07:00:00', 12, 26, NULL),
(74, 'ev', 'sportif', 'terrain', '2021-03-04', '2021-03-04', '13:00:00', '13:00:00', 13, 13, NULL),
(76, 'sport', 'sportif', 'tunis', '2021-03-01', '2021-03-02', '15:00:00', '15:00:00', 18, 18, NULL),
(79, 'loi', 'loisir', 'gazela', '2021-03-09', '2021-03-24', '15:02:00', '18:22:00', 13, 12, NULL),
(80, 'event', 'loisir', 'ariana', '2021-03-02', '2021-03-03', '15:00:00', '15:00:00', 16, 16, NULL),
(81, 'event', 'loisir', 'ariana', '2021-03-02', '2021-03-03', '15:00:00', '15:00:00', 16, 16, NULL),
(82, 'ayevTEST', 'loisir', 'eee', '2021-03-26', '2021-03-26', '12:00:00', '12:00:00', 15, 15, NULL),
(90, 'islem', 'sportif', 'ml', '2021-03-11', '2021-03-12', '12:00:00', '12:00:00', 15, 15, NULL),
(91, 'islem', 'educatif', 'esprit', '2021-03-04', '2021-03-04', '12:03:00', '12:03:00', 12, 12, NULL),
(93, 'mm', 'sportif', 'ww', '2021-03-05', '2021-03-27', '21:42:00', '14:42:00', 12, 12, NULL),
(94, 'ui', 'educatif', 'ml', '2021-03-14', '2021-03-21', '15:40:00', '15:40:00', 13, 13, NULL),
(95, 'izlem', 'educatif', 'okkk', '2021-03-21', '2021-03-14', '14:51:00', '14:52:00', 12, 12, NULL),
(96, 'islem', 'sportif', 'lmp', '2021-03-21', '2021-03-21', '15:54:00', '14:54:00', 15, 15, NULL),
(97, 'islem', 'educatif', 'olo', '2021-03-21', '2021-03-20', '13:05:00', '15:05:00', 15, 15, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `invitation`
--

CREATE TABLE `invitation` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_ev` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `invitation`
--

INSERT INTO `invitation` (`id`, `id_user`, `id_ev`) VALUES
(5, -1, 93),
(6, 22, 80),
(7, 31, 74),
(9, -1, -1);

-- --------------------------------------------------------

--
-- Structure de la table `nutri`
--

CREATE TABLE `nutri` (
  `id_user` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` text NOT NULL,
  `mail` varchar(50) NOT NULL,
  `date_n` date NOT NULL,
  `code` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `nutri`
--

INSERT INTO `nutri` (`id_user`, `username`, `password`, `mail`, `date_n`, `code`) VALUES
(1, 'sss', 'sss', 'sss', '2021-03-18', 'sss'),
(4, 'aaa', 'aaa', 'aaa', '2021-03-03', 'aaa'),
(5, 'eee', 'eee', 'eee', '2021-03-11', 'eee'),
(6, 'mgknutri', '$2a$13$ymwEH1yMOSd1UpSxv2CtY.6V8xnPLstu1y6QghlT6WXVkQMx4MCbi', 'mgknutri@mgk2.tn', '2021-03-05', 'dododo'),
(7, 'jsjsjs', '$2a$13$FB1otbJc1qJ7ZNSQkgLLN.X0t8aTRRgBPgw3QavN180QmFpe02aZ2', 'jijiji@jxs.cd', '2021-03-10', 'cdijc');

-- --------------------------------------------------------

--
-- Structure de la table `participation`
--

CREATE TABLE `participation` (
  `id_par` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_event` int(11) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `date_par` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `participation`
--

INSERT INTO `participation` (`id_par`, `id_user`, `id_event`, `username`, `date_par`) VALUES
(7, 0, 3, 'Anas', '2021-03-30 21:01:13'),
(8, 2, 3, 'Islem', '2021-03-03 20:14:49'),
(10, 0, 3, 'Mourad', '2021-03-30 21:01:20'),
(11, 2, 3, 'Yasmine', '2021-03-30 21:01:32'),
(14, 0, 73, 'mgk', '2021-03-30 22:01:54');

-- --------------------------------------------------------

--
-- Structure de la table `psycho`
--

CREATE TABLE `psycho` (
  `id_user` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` text NOT NULL,
  `mail` varchar(50) NOT NULL,
  `date_n` date NOT NULL,
  `code` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `psycho`
--

INSERT INTO `psycho` (`id_user`, `username`, `password`, `mail`, `date_n`, `code`) VALUES
(1, 'aaa', 'aaa', 'aaa', '2021-03-02', ''),
(1003, 'zer', '$2a$13$SYNeZ/N3Dtf2QXJzukzj/OPRcwKbDmtywihI08gpEAsjs.MXBBefy', 'dgsf@ezf.fe', '2021-03-11', 'efzfd'),
(1004, 'azert', '$2a$13$exqsTF0KrFAh5XP/7FY1gOsH4lua/VpOek7Y8pVA3mMpu3kHR95Z6', 'aeae@aeae.ae', '2021-03-02', 'aeaeaeae'),
(1005, 'rera', '$2a$13$YPGGLPKjerAeGyUam4JlYOofcIPBam15Zom7SRaazbpXUMmdm5ai2', 'rera@rera.tn', '2021-03-02', 'rera'),
(1006, 'qaqa', '$2a$13$xAxUR1PZ45b9Kkfw5ya2UeuVt5sTCZKJy2Ijw8vqPxpPBQlxBe4om', 'qaqa@qaqa.qa', '2021-03-03', 'qaqaqaq'),
(1007, 'tatat', '$2a$13$Z/EKYSQ0BI9rkSgyzuArw.uvQING20xtxsDM7pNQ1FkE/5BqcUZT6', 'tata@tata.ta', '2021-03-02', 'tata'),
(1008, 'aqaq', '$2a$13$mlT7//YR8SKQQDCLvElESOZcqAN8HAN0j/3wxdcgkegrWfIOfAY/W', 'aqaq@aqaq.aq', '2021-03-03', 'aqaq'),
(1009, 'dede', '$2a$13$TZ8mRBd9z.Wnr/ePeMNxg.9Ljf9LcUfTfQHS0nDR0CJr3LxoHHSeC', 'dede@de.de', '2021-03-03', 'dede'),
(1010, 'trtr', '$2a$13$PHopZOHYWIA3aThOesDNJugv4SQwtgt95qgtvy8TgBrsaaL1JZzM2', 'tete@te.te', '2021-03-03', 'tr'),
(1011, 'yoyo', '$2a$13$DkzHMxw0Xk5g75hDezFWAe0pIhcCmmkbWZdE2YVPaRCxobr0OW14i', 'yoyo@yoyo.yy', '2021-03-01', 'yoyo'),
(1012, 'arr', '$2a$13$3rGyz83B3wW7ltDFpPJtgeqR6oFNFe96gtRWznOaHx4A.VrEDWEdq', 'arr@arr.ar', '2021-03-03', 'arar'),
(1013, 'tyty', '$2a$13$QZ/4g2OKb2yUyGBXAQyKUuz2A.ddAI1va3gqv/1TuMA8yF0/RmLIi', 'uiui@ui.ui', '2021-03-10', 'tyty'),
(1014, 'tera', '$2a$13$71skoueMPxb4o5YFFXf0L.uZ51uLf7RNw1unfp6byCKFLROIYnYEC', 'rere@erer.rr', '2021-03-03', '1lj'),
(1015, 'mgkpsy', '$2a$13$XqJEY.kvpYqNGbhlfCNyY.rb1ZlDRfB8FgJZSFpjqYVAsv0tidiS.', 'mgkpsy@mgk2.tn', '2021-03-03', 'fdsilfk');

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

CREATE TABLE `reclamation` (
  `id_rec` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `obj_rec` varchar(50) DEFAULT NULL,
  `area_rec` varchar(100) DEFAULT NULL,
  `suj_rec` varchar(150) DEFAULT NULL,
  `etat_rec` varchar(20) DEFAULT 'To do',
  `date_rec` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `reclamation`
--

INSERT INTO `reclamation` (`id_rec`, `id_user`, `username`, `obj_rec`, `area_rec`, `suj_rec`, `etat_rec`, `date_rec`) VALUES
(35, 2, '', 'test', 'Events', 'dsdpiskmnsd', 'To do', '2021-03-28 21:23:27'),
(36, 2, '', 'testtest', 'Pubs', 'pppppp', 'To do', '2021-03-28 22:31:58'),
(37, 2, '', 'test', 'Chat', 'dflidfn', 'To do', '2021-03-28 23:03:24'),
(38, 2, '', 'testXXX', 'Events', 'testXXX', 'To do', '2021-03-28 23:11:08'),
(39, 2, '', 'qsqsqqqsqsq', 'Chat', 'qsqsqsq', 'To do', '2021-03-29 09:25:29'),
(40, 0, 'mgk', 'test', 'Articles', 'test555', 'To do', '2021-03-30 17:03:56'),
(41, 0, 'mgk', 'salut', 'Events', 'test test makrouna', 'To do', '2021-03-30 22:05:37'),
(43, 51, 'shidono', 'birrass', 'Events', 'sqdqsdqsdqsdqs', 'To do', '2021-03-30 22:38:29'),
(48, 784, 'mgkcoach', 'cool life', 'Events', 'qsdqsdqsdqsd', 'To do', '2021-03-31 01:36:29'),
(49, 1015, 'mgkpsy', 'dcdcdcdc', 'Select the area of the problem', 'mlkjhgfd', 'To do', '2021-03-31 01:37:32'),
(50, 1015, 'mgkpsy', 'dcdcdcdc', 'Events', 'mlkjhgfd', 'To do', '2021-03-31 01:37:40');

-- --------------------------------------------------------

--
-- Structure de la table `simple`
--

CREATE TABLE `simple` (
  `id_user` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` text NOT NULL,
  `mail` varchar(50) NOT NULL,
  `date_n` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `simple`
--

INSERT INTO `simple` (`id_user`, `username`, `password`, `mail`, `date_n`) VALUES
(0, 'mgk', '$2a$13$L5cAtXVWc559U5FqH3U/EOCoiHMgzqLt7AszGKCG7EWmrpj0kd3aG', 'mgk@mgk2.tn', '2021-03-03'),
(2, 'pp', 'pp', 'pp', '2021-03-10'),
(20, 'aat', 'aaa', 'aae', '2021-03-05'),
(22, 'mm', 'mm', 'mm', '2021-03-02'),
(24, 'tt', 'tt', 'tt', '2021-03-10'),
(25, 'll', 'll', 'll', '2021-03-02'),
(26, 'fdsf', 'df', 'wdgxw', '2021-03-03'),
(28, 'nour', '$2a$13$PZs7yvuNcd6TcDahP6.Woe48ePHDUqJyM2sqxcaVAE9qpeMMBR6L2', 'aa@aa.aa', '2021-03-10'),
(29, 'arty', '$2a$13$Ho5UKw7rc.0yWnxetn89qOAEhpUfnxTUAdAwLPYOHDbTR6sNhSQEK', 'aret@tfg.gt', '2021-03-03'),
(30, 'mourad', '$2a$13$hPY/1pRjOJnRmVHULTSsj.leZn2S8R3VxIl/Sth7wIW31XR0S74bu', 'mouradmourad@esprit.tn', '2021-03-03'),
(32, 'sccscsc', '$2a$13$SY5cCVGMNXa.w38IN55sTuwJCHtL9NsQiAErQd8HENXUHwxSITZ1u', 'xovij@vucc.vs', '2021-03-04'),
(33, 'mgktest', '$2a$13$joYgYWNo8cEe9St1DX1A1.Iwg5onRPVkc9xaW5WJdEIfy75PguiGe', 'mgktest@esprit.tn', '2021-03-03'),
(50, 'aeae', 'aeae', 'aeae', '2021-03-03'),
(51, 'shidono', '$2a$13$RX.COVVC6QRr7QQZE8vwLOzs2j/ussVsOOSMRD8NYysg8QRf4nTqm', 'shidonosan@gmail.com', '2021-03-30');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `mail` varchar(50) NOT NULL,
  `date_n` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `act`
--
ALTER TABLE `act`
  ADD PRIMARY KEY (`id_act`);

--
-- Index pour la table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_user`);

--
-- Index pour la table `articles`
--
ALTER TABLE `articles`
  ADD PRIMARY KEY (`id_art`),
  ADD KEY `FK_cat_art` (`id_cat`);

--
-- Index pour la table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`id_cat`),
  ADD UNIQUE KEY `titre_cat` (`titre_cat`);

--
-- Index pour la table `coach`
--
ALTER TABLE `coach`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Index pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD PRIMARY KEY (`id_com`),
  ADD KEY `fk_com_user` (`id_user`),
  ADD KEY `fk_com_pub` (`id_pub`);

--
-- Index pour la table `evenement`
--
ALTER TABLE `evenement`
  ADD PRIMARY KEY (`id_ev`),
  ADD KEY `fk_art_cat` (`id_act`);

--
-- Index pour la table `invitation`
--
ALTER TABLE `invitation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id` (`id_user`),
  ADD KEY `fk_id_ev` (`id_ev`) USING BTREE;

--
-- Index pour la table `nutri`
--
ALTER TABLE `nutri`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Index pour la table `participation`
--
ALTER TABLE `participation`
  ADD PRIMARY KEY (`id_par`),
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `fk_event` (`id_event`),
  ADD KEY `fk_user_part` (`id_user`);

--
-- Index pour la table `psycho`
--
ALTER TABLE `psycho`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `username_2` (`username`);

--
-- Index pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`id_rec`);

--
-- Index pour la table `simple`
--
ALTER TABLE `simple`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `admin`
--
ALTER TABLE `admin`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT pour la table `articles`
--
ALTER TABLE `articles`
  MODIFY `id_art` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT pour la table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `id_cat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;

--
-- AUTO_INCREMENT pour la table `coach`
--
ALTER TABLE `coach`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=785;

--
-- AUTO_INCREMENT pour la table `commentaire`
--
ALTER TABLE `commentaire`
  MODIFY `id_com` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT pour la table `evenement`
--
ALTER TABLE `evenement`
  MODIFY `id_ev` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100;

--
-- AUTO_INCREMENT pour la table `invitation`
--
ALTER TABLE `invitation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `nutri`
--
ALTER TABLE `nutri`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `participation`
--
ALTER TABLE `participation`
  MODIFY `id_par` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT pour la table `psycho`
--
ALTER TABLE `psycho`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1016;

--
-- AUTO_INCREMENT pour la table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `id_rec` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT pour la table `simple`
--
ALTER TABLE `simple`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `evenement`
--
ALTER TABLE `evenement`
  ADD CONSTRAINT `fk_art_cat` FOREIGN KEY (`id_act`) REFERENCES `act` (`id_act`);

--
-- Contraintes pour la table `participation`
--
ALTER TABLE `participation`
  ADD CONSTRAINT `fk_event` FOREIGN KEY (`id_event`) REFERENCES `evenement` (`id_ev`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_user_part` FOREIGN KEY (`id_user`) REFERENCES `simple` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
