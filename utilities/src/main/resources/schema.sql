-- PHPMYADMIN SQL DUMP
-- VERSION 5.0.1
-- HTTPS://WWW.PHPMYADMIN.NET/
--
-- HOST: 127.0.0.1
-- GENERATION TIME: MAR 24, 2020 AT 06:55 PM
-- SERVER VERSION: 10.4.11-MARIADB
-- PHP VERSION: 7.4.3
DROP DATABASE IF EXISTS GT3;

CREATE DATABASE GT3;

USE GT3;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET TIME_ZONE = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES UTF8MB4 */;

--
-- DATABASE: `GT3`
--

-- Teams table
CREATE TABLE Teams (
                       team_id INT PRIMARY KEY,
                       name VARCHAR(100),
                       country VARCHAR(100),
                       foundation_year INT,
                       contact_info VARCHAR(255)
);

-- Circuits table
CREATE TABLE Circuits (
                          circuit_id INT PRIMARY KEY,
                          name VARCHAR(100),
                          country VARCHAR(100),
                          length FLOAT,
                          type VARCHAR(100)
);

-- Drivers table
CREATE TABLE Drivers (
                         driver_id INT PRIMARY KEY,
                         name VARCHAR(100),
                         nationality VARCHAR(100),
                         age INT,
                         team_id INT,
                         FOREIGN KEY (team_id) REFERENCES Teams(team_id)
);

-- Cars table
CREATE TABLE Cars (
                      car_id INT PRIMARY KEY,
                      brand VARCHAR(100),
                      model VARCHAR(100),
                      manufacturing_year INT,
                      power INT,
                      weight INT,
                      engine_type VARCHAR(100),
                      chassis_manufacturer VARCHAR(100)
);

-- Brands table
CREATE TABLE Brands (
                        brand_id INT PRIMARY KEY,
                        name VARCHAR(100),
                        country_of_origin VARCHAR(100),
                        contact_info VARCHAR(255)
);

-- CarModels table
CREATE TABLE Car_Models (
                            model_id INT PRIMARY KEY,
                            brand_id INT,
                            model VARCHAR(100),
                            FOREIGN KEY (brand_id) REFERENCES Brands(brand_id)
);

-- Races table
CREATE TABLE Races (
                       race_id INT PRIMARY KEY,
                       name VARCHAR(100),
                       circuit_id INT,
                       date DATE,
                       FOREIGN KEY (circuit_id) REFERENCES Circuits(circuit_id)
);

-- RaceTeams table (Many-to-Many relationship between Races and Teams)
CREATE TABLE Race_Teams (
                            race_id INT,
                            team_id INT,
                            PRIMARY KEY (race_id, team_id),
                            FOREIGN KEY (race_id) REFERENCES Races(race_id),
                            FOREIGN KEY (team_id) REFERENCES Teams(team_id)
);

-- RaceDrivers table (Many-to-Many relationship between Races and Drivers)
CREATE TABLE Race_Drivers (
                              race_id INT,
                              driver_id INT,
                              PRIMARY KEY (race_id, driver_id),
                              FOREIGN KEY (race_id) REFERENCES Races(race_id),
                              FOREIGN KEY (driver_id) REFERENCES Drivers(driver_id)
);

-- RaceResults table
CREATE TABLE Race_Results (
                              result_id INT PRIMARY KEY,
                              race_id INT,
                              team_id INT,
                              driver_id INT,
                              position INT,
                              FOREIGN KEY (race_id) REFERENCES Races(race_id),
                              FOREIGN KEY (team_id) REFERENCES Teams(team_id),
                              FOREIGN KEY (driver_id) REFERENCES Drivers(driver_id)
);
