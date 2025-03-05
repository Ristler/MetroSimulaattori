DROP DATABASE IF EXISTS MetroSimulaattori;
CREATE DATABASE MetroSimulaattori;

DROP USER IF EXISTS 'MetroSimulaattori'@'localhost';
CREATE USER 'MetroSimulaattori'@'localhost' IDENTIFIED BY 'MetroSimulaattori';

GRANT ALL PRIVILEGES ON MetroSimulaattori.* TO 'MetroSimulaattori'@'localhost';

USE MetroSimulaattori;

CREATE TABLE Metroasema (
    id INT PRIMARY KEY AUTO_INCREMENT,
    pvm DATE DEFAULT CURRENT_DATE,
    Asiak_palveltu INT DEFAULT 0,
    Keskim_jonotusaika FLOAT(5,2) DEFAULT 0,
    Simulointiaika FLOAT(10,2) DEFAULT 0
);

CREATE TABLE Lippuhalli (
    id INT PRIMARY KEY AUTO_INCREMENT,
    pvm DATE DEFAULT CURRENT_DATE,
    Asiak_palveltu INT DEFAULT 0,
    Keskim_jonotusaika FLOAT(5,2) DEFAULT 0,
    Simulointiaika FLOAT(10,2) DEFAULT 0
);

CREATE TABLE Laituri (
    id INT PRIMARY KEY AUTO_INCREMENT,
    pvm DATE DEFAULT CURRENT_DATE,
    Asiak_palveltu INT DEFAULT 0,
    Keskim_jonotusaika FLOAT(5,2) DEFAULT 0,
    Simulointiaika FLOAT(10,2) DEFAULT 0
);

CREATE TABLE Metro (
    id INT PRIMARY KEY AUTO_INCREMENT,
    pvm DATE DEFAULT CURRENT_DATE,
    Asiak_palveltu INT DEFAULT 0,
    Keskim_jonotusaika FLOAT(5,2) DEFAULT 0,
    Simulointiaika FLOAT(10,2) DEFAULT 0
);

