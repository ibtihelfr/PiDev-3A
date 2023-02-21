-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 13 fév. 2023 à 13:28
-- Version du serveur : 10.4.27-MariaDB
-- Version de PHP : 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `tunmix`
--

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE `categorie` (
  `IdCategorie` int(255) NOT NULL,
  `NomCategorie` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `IdCom` int(255) NOT NULL,
  `IdProduit` int(255) NOT NULL,
  `IdUser` int(255) NOT NULL,
  `QteProd` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `event`
--

CREATE TABLE `event` (
  `IdEvent` int(255) NOT NULL,
  `NomEvent` varchar(255) NOT NULL,
  `DateDebut` date NOT NULL,
  `DateFin` date NOT NULL,
  `Localisation` varchar(255) NOT NULL,
  `Description` varchar(255) NOT NULL,
  `HeureEvent` varchar(255) NOT NULL,
  `prix` float NOT NULL,
  `PhotoE` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `panier`
--

CREATE TABLE `panier` (
  `IdPanier` int(255) NOT NULL,
  `IdCom` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE `produit` (
  `IdProduit` int(255) NOT NULL,
  `NomProduit` varchar(255) NOT NULL,
  `DescProduit` varchar(255) NOT NULL,
  `DateProduit` date NOT NULL,
  `PrixProduit` float(255,0) NOT NULL,
  `Qte` int(255) NOT NULL,
  `PhotoP` varchar(255) NOT NULL,
  `IdCategorie` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

CREATE TABLE `reclamation` (
  `IdReclamation` int(255) NOT NULL,
  `IdProduit` int(255) NOT NULL,
  `IdEvent` int(255) NOT NULL,
  `NomReclamation` varchar(255) NOT NULL,
  `Historique` varchar(255) NOT NULL,
  `DateReclamation` date NOT NULL DEFAULT current_timestamp(),
  `EtatReclamation` varchar(255) NOT NULL,
  `Motif` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `reponse`
--

CREATE TABLE `reponse` (
  `IdReponse` int(255) NOT NULL,
  `Description` varchar(255) NOT NULL,
  `IdUser` int(255) NOT NULL,
  `IdReclamation` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `IdRes` int(11) NOT NULL,
  `IdEvent` int(11) NOT NULL,
  `IdUser` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `ticket`
--

CREATE TABLE `ticket` (
  `numTicket` int(255) NOT NULL,
  `nbMaxT` int(255) NOT NULL,
  `nbTDemande` int(255) NOT NULL,
  `idRes` int(255) NOT NULL,
  `PrixF` float(255,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `ticketo`
--

CREATE TABLE `ticketo` (
  `numTicketO` int(255) NOT NULL,
  `numTicket` int(255) NOT NULL,
  `Logement` varchar(255) NOT NULL,
  `Restauration` varchar(255) NOT NULL,
  `Prix` float(255,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `IdUser` int(255) NOT NULL,
  `NomUser` varchar(255) NOT NULL,
  `PrenomUser` varchar(255) NOT NULL,
  `Pwd` varchar(255) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `NumTel` int(255) NOT NULL,
  `TypeUser` varchar(255) NOT NULL,
  `Photo` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`IdCategorie`);

--
-- Index pour la table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`IdCom`,`IdProduit`,`IdUser`) USING BTREE,
  ADD KEY `IdProduit` (`IdProduit`),
  ADD KEY `IdUser` (`IdUser`);

--
-- Index pour la table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`IdEvent`);

--
-- Index pour la table `panier`
--
ALTER TABLE `panier`
  ADD PRIMARY KEY (`IdPanier`),
  ADD KEY `IdCom` (`IdCom`);

--
-- Index pour la table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`IdProduit`),
  ADD KEY `IdCategorie` (`IdCategorie`);

--
-- Index pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`IdReclamation`),
  ADD KEY `IdProduit` (`IdProduit`),
  ADD KEY `IdEvent` (`IdEvent`);

--
-- Index pour la table `reponse`
--
ALTER TABLE `reponse`
  ADD PRIMARY KEY (`IdReponse`),
  ADD KEY `IdUser` (`IdUser`),
  ADD KEY `IdReclamation` (`IdReclamation`);

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`IdRes`,`IdEvent`,`IdUser`),
  ADD KEY `IdEvent` (`IdEvent`),
  ADD KEY `IdUser` (`IdUser`);

--
-- Index pour la table `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`numTicket`),
  ADD KEY `idRes` (`idRes`);

--
-- Index pour la table `ticketo`
--
ALTER TABLE `ticketo`
  ADD PRIMARY KEY (`numTicketO`),
  ADD KEY `numTicket` (`numTicket`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`IdUser`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `IdCategorie` int(255) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `commande`
--
ALTER TABLE `commande`
  MODIFY `IdCom` int(255) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `event`
--
ALTER TABLE `event`
  MODIFY `IdEvent` int(255) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `panier`
--
ALTER TABLE `panier`
  MODIFY `IdPanier` int(255) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `produit`
--
ALTER TABLE `produit`
  MODIFY `IdProduit` int(255) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `IdReclamation` int(255) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `reponse`
--
ALTER TABLE `reponse`
  MODIFY `IdReponse` int(255) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `IdRes` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `ticket`
--
ALTER TABLE `ticket`
  MODIFY `numTicket` int(255) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `ticketo`
--
ALTER TABLE `ticketo`
  MODIFY `numTicketO` int(255) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `IdUser` int(255) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `commande_ibfk_1` FOREIGN KEY (`IdProduit`) REFERENCES `produit` (`IdProduit`),
  ADD CONSTRAINT `commande_ibfk_2` FOREIGN KEY (`IdUser`) REFERENCES `user` (`IdUser`);

--
-- Contraintes pour la table `panier`
--
ALTER TABLE `panier`
  ADD CONSTRAINT `panier_ibfk_1` FOREIGN KEY (`IdCom`) REFERENCES `commande` (`IdCom`);

--
-- Contraintes pour la table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `produit_ibfk_2` FOREIGN KEY (`IdCategorie`) REFERENCES `categorie` (`IdCategorie`);

--
-- Contraintes pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD CONSTRAINT `reclamation_ibfk_3` FOREIGN KEY (`IdProduit`) REFERENCES `produit` (`IdProduit`),
  ADD CONSTRAINT `reclamation_ibfk_4` FOREIGN KEY (`IdEvent`) REFERENCES `event` (`IdEvent`);

--
-- Contraintes pour la table `reponse`
--
ALTER TABLE `reponse`
  ADD CONSTRAINT `reponse_ibfk_1` FOREIGN KEY (`IdUser`) REFERENCES `user` (`IdUser`),
  ADD CONSTRAINT `reponse_ibfk_2` FOREIGN KEY (`IdReclamation`) REFERENCES `reclamation` (`IdReclamation`);

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`IdEvent`) REFERENCES `event` (`IdEvent`),
  ADD CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`IdUser`) REFERENCES `user` (`IdUser`);

--
-- Contraintes pour la table `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `ticket_ibfk_1` FOREIGN KEY (`idRes`) REFERENCES `user` (`IdUser`);

--
-- Contraintes pour la table `ticketo`
--
ALTER TABLE `ticketo`
  ADD CONSTRAINT `ticketo_ibfk_1` FOREIGN KEY (`numTicket`) REFERENCES `ticket` (`numTicket`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
