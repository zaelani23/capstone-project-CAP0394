SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;


DROP TABLE IF EXISTS `komoditas`;
CREATE TABLE IF NOT EXISTS `komoditas` (
  `id_komoditas` int(11) NOT NULL AUTO_INCREMENT,
  `nama_komoditas` varchar(100) NOT NULL,
  PRIMARY KEY (`id_komoditas`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

INSERT INTO `komoditas` (`id_komoditas`, `nama_komoditas`) VALUES
(1, 'Gabah'),
(2, 'Beras'),
(3, 'Jagung'),
(4, 'Bawang Merah'),
(5, 'Cabai');

DROP TABLE IF EXISTS `profil`;
CREATE TABLE IF NOT EXISTS `profil` (
  `id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `desa` varchar(255) NOT NULL,
  `kecamatan` varchar(50) NOT NULL,
  `kabupaten` varchar(50) NOT NULL,
  `provinsi` varchar(50) NOT NULL,
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `tengkulak`;
CREATE TABLE IF NOT EXISTS `tengkulak` (
  `id_tengkulak` int(11) NOT NULL,
  `id_komoditas` int(11) NOT NULL,
  `tawaran_harga` int(11) NOT NULL,
  `last_update` date NOT NULL,
  KEY `id_tengkulak` (`id_tengkulak`),
  KEY `id_komoditas` (`id_komoditas`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `tengkulak` (`id_tengkulak`, `id_komoditas`, `tawaran_harga`, `last_update`) VALUES
(15, 1, 4300, '0000-00-00');

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password_salt` varchar(255) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `role` enum('petani','tengkulak') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;


ALTER TABLE `profil`
  ADD CONSTRAINT `profil_ibfk_1` FOREIGN KEY (`id`) REFERENCES `users` (`id`);

ALTER TABLE `tengkulak`
  ADD CONSTRAINT `tengkulak_ibfk_1` FOREIGN KEY (`id_tengkulak`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `tengkulak_ibfk_2` FOREIGN KEY (`id_komoditas`) REFERENCES `komoditas` (`id_komoditas`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
