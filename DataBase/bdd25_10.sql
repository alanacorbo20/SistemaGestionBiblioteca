CREATE TABLE `administradores` (
  `codigo` int(11) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

CREATE TABLE `cobros` (
  `cobro` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `dinero` decimal(10,0) DEFAULT NULL,
  `montoTotal` decimal(10,0) DEFAULT NULL,
  `codigoAdmin` int(11) DEFAULT NULL,
  PRIMARY KEY (`cobro`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

CREATE TABLE `computadoras` (
  `numero` int(11) NOT NULL,
  `estadoFisico` varchar(100) NOT NULL,
  PRIMARY KEY (`numero`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

CREATE TABLE `libros` (
  `isbn` varchar(13) NOT NULL,
  `titulo` varchar(100) NOT NULL,
  `autor` varchar(100) NOT NULL,
  `estadoFisico` varchar(100) NOT NULL,
  `materia` varchar(100) NOT NULL,
  `canEjemplares` int(11) DEFAULT NULL,
  PRIMARY KEY (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

CREATE TABLE `personas` (
  `ci` int(8) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `tipo` varchar(100) NOT NULL,
  `grupo` varchar(100) NOT NULL,
  PRIMARY KEY (`ci`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

CREATE TABLE `piden` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numero` int(11) NOT NULL,
  `ci` int(8) NOT NULL,
  `fechaP` date NOT NULL,
  `hora` time NOT NULL,
  `estado` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `numero` (`numero`),
  KEY `ci` (`ci`),
  CONSTRAINT `piden_ibfk_1` FOREIGN KEY (`numero`) REFERENCES `computadoras` (`numero`),
  CONSTRAINT `piden_ibfk_2` FOREIGN KEY (`ci`) REFERENCES `personas` (`ci`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

CREATE TABLE `solicitan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `isbn` varchar(13) NOT NULL,
  `ci` int(8) NOT NULL,
  `fechaP` date NOT NULL,
  `fechaD` date NOT NULL,
  `estado` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ci` (`ci`),
  KEY `isbn` (`isbn`),
  CONSTRAINT `solicitan_ibfk_1` FOREIGN KEY (`ci`) REFERENCES `personas` (`ci`),
  CONSTRAINT `solicitan_ibfk_2` FOREIGN KEY (`isbn`) REFERENCES `libros` (`isbn`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

CREATE TABLE `telefonos` (
  `ci` int(8) DEFAULT NULL,
  `telefono` int(11) NOT NULL,
  KEY `fkci_idx` (`ci`),
  CONSTRAINT `fkci` FOREIGN KEY (`ci`) REFERENCES `personas` (`ci`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
