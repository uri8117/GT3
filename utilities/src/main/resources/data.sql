USE GT3;
-- Inserts for Teams table
INSERT INTO Teams (team_id, name, country, foundation_year, contact_info) VALUES
                                                                              (1, 'Team A', 'USA', 2005, 'teamA@example.com'),
                                                                              (2, 'Team B', 'Germany', 2010, 'teamB@example.com'),
                                                                              (3, 'Team C', 'Italy', 2008, 'teamC@example.com');

-- Inserts for Circuits table
INSERT INTO Circuits (circuit_id, name, country, length, type) VALUES
                                                                   (1, 'Circuit 1', 'USA', 4.3, 'Permanent'),
                                                                   (2, 'Circuit 2', 'Germany', 5.1, 'Street'),
                                                                   (3, 'Circuit 3', 'Italy', 3.6, 'Permanent');

-- Inserts for Drivers table
INSERT INTO Drivers (driver_id, name, nationality, age, team_id) VALUES
                                                                     (1, 'John Smith', 'USA', 30, 1),
                                                                     (2, 'Emma Müller', 'Germany', 28, 2),
                                                                     (3, 'Luigi Rossi', 'Italy', 32, 3);

-- Inserts for Cars table
INSERT INTO Cars (car_id, brand, model, manufacturing_year, power, weight, engine_type, chassis_manufacturer) VALUES
                                                                                                                  (1, 'Brand X', 'Model 1', 2019, 550, 1200, 'V8', 'Manufacturer A'),
                                                                                                                  (2, 'Brand Y', 'Model 2', 2018, 600, 1150, 'V10', 'Manufacturer B'),
                                                                                                                  (3, 'Brand Z', 'Model 3', 2020, 580, 1250, 'V12', 'Manufacturer C');

-- Inserts for Brands table
INSERT INTO Brands (brand_id, name, country_of_origin, contact_info) VALUES
                                                                         (1, 'Brand X', 'USA', 'info@brandx.com'),
                                                                         (2, 'Brand Y', 'Germany', 'info@brandy.com'),
                                                                         (3, 'Brand Z', 'Italy', 'info@brandz.com');

-- Inserts for CarModels table
INSERT INTO Car_Models (model_id, brand_id, model) VALUES
                                                       (1, 1, 'Model 1'),
                                                       (2, 2, 'Model 2'),
                                                       (3, 3, 'Model 3');

-- Inserts for Races table
INSERT INTO Races (race_id, name, circuit_id, date) VALUES
                                                        (1, 'Race 1', 1, '2024-05-15'),
                                                        (2, 'Race 2', 2, '2024-06-20'),
                                                        (3, 'Race 3', 3, '2024-07-25');

-- Inserts for RaceTeams table
INSERT INTO Race_Teams (race_id, team_id) VALUES
                                              (1, 1),
                                              (2, 2),
                                              (3, 3);

-- Inserts for RaceDrivers table
INSERT INTO Race_Drivers (race_id, driver_id) VALUES
                                                  (1, 1),
                                                  (2, 2),
                                                  (3, 3);

-- Inserts for RaceResults table
INSERT INTO Race_Results (result_id, race_id, team_id, driver_id, position) VALUES
                                                                                (1, 1, 1, 1, 1),
                                                                                (2, 2, 2, 2, 2),
                                                                                (3, 3, 3, 3, 3);
