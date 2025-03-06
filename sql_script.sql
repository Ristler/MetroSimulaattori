DROP DATABASE IF EXISTS MetroSimulaattori;
CREATE DATABASE MetroSimulaattori;

DROP USER IF EXISTS 'MetroSimulaattori'@'localhost';
CREATE USER 'MetroSimulaattori'@'localhost' IDENTIFIED BY 'MetroSimulaattori';

GRANT ALL PRIVILEGES ON MetroSimulaattori.* TO 'MetroSimulaattori'@'localhost';

USE MetroSimulaattori;

CREATE TABLE Metroasema (
    id INT PRIMARY KEY AUTO_INCREMENT,
    pvm DATE DEFAULT CURRENT_DATE,
    kello TIME DEFAULT CURRENT_TIME,
    Asiak_palveltu INT DEFAULT 0,
    Keskim_jonotusaika FLOAT(5,2) DEFAULT 0,
    Simulointiaika FLOAT(10,2) DEFAULT 0
);

CREATE TABLE Lippuhalli (
    id INT PRIMARY KEY AUTO_INCREMENT,
    pvm DATE DEFAULT CURRENT_DATE,
    kello TIME DEFAULT CURRENT_TIME,
    Asiak_palveltu INT DEFAULT 0,
    Keskim_jonotusaika FLOAT(5,2) DEFAULT 0,
    Simulointiaika FLOAT(10,2) DEFAULT 0
);

CREATE TABLE Laituri (
    id INT PRIMARY KEY AUTO_INCREMENT,
    pvm DATE DEFAULT CURRENT_DATE,
    kello TIME DEFAULT CURRENT_TIME,
    Asiak_palveltu INT DEFAULT 0,
    Keskim_jonotusaika FLOAT(5,2) DEFAULT 0,
    Simulointiaika FLOAT(10,2) DEFAULT 0
);

CREATE TABLE Metro_M1 (
    id INT PRIMARY KEY AUTO_INCREMENT,
    pvm DATE DEFAULT CURRENT_DATE,
    kello TIME DEFAULT CURRENT_TIME,
    Asiak_palveltu INT DEFAULT 0,
    Keskim_jonotusaika FLOAT(5,2) DEFAULT 0,
    Simulointiaika FLOAT(10,2) DEFAULT 0
);

CREATE TABLE Metro_M2 (
    id INT PRIMARY KEY AUTO_INCREMENT,
    pvm DATE DEFAULT CURRENT_DATE,
    kello TIME DEFAULT CURRENT_TIME,
    Asiak_palveltu INT DEFAULT 0,
    Keskim_jonotusaika FLOAT(5,2) DEFAULT 0,
    Simulointiaika FLOAT(10,2) DEFAULT 0
);

CREATE TABLE Asiakas (
    id INT PRIMARY KEY NOT NULL,
    pvm DATE DEFAULT CURRENT_DATE,
    kello TIME DEFAULT CURRENT_TIME,
    Asema_saap FLOAT DEFAULT 0,
    Asema_pois FLOAT DEFAULT 0,
    Lippu_saap FLOAT DEFAULT 0,
    Lippu_pois FLOAT DEFAULT 0,
    Laituri_saap FLOAT DEFAULT 0,
    Laituri_pois FLOAT DEFAULT 0,
    Metro_saap FLOAT DEFAULT 0,
    Metro_pois FLOAT DEFAULT 0
)

