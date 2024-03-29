INSERT INTO Location
(Name)
VALUES
('Lviv'),
('Zhytomyr'),
('Chernivtsi'),
('Kharkiv'),
('Krasnograd');

INSERT INTO Station
(Name, Street, NumberStreet, LocationId)
VALUES
('Bus Station', 'Stryyska', '109', 1),
('AS-8', 'Dvirtseva Square', '1', 1),
('AS', 'Kyivska', '93', 2),
('Bus Station', 'Golovna', '219', 3),
('AB', 'Gagarina', '22', 4),
('6', 'Heroev-Kharkiv', '299a', 4),
('Bus Station', 'Poltavska', '91', 5);

INSERT INTO Bus
(Model, BusNumber, Seats)
VALUES
('SETRA 80', 'AX8080', 35),
('Mercedes', 'BB3546', 41),
('Volvo', 'CC4532', 55),
('Audi', 'FR4687', 90),
('BMW', 'GG3456', 20);


INSERT INTO Route
(BusId, Name, DepartureStation, ArrivalStation, DepartureTime, ArrivalTime)
VALUES
(1, '8782', 1, 3, '01:30', '05:30'),
(2, '7723P', 2, 5, '07:15', '22:55'),
(3, '7027', 6, 3, '22:10', '10:45'),
(4, '70K', 6, 1, '06:00', '18:00'),
(5, '703W', 3, 4, '14:20', '16:40');

INSERT INTO Schedule
(RouteId, StationId, DepartureTime, ArrivalTime)
VALUES
(1, 2, '01:50', '01:45'),
(2, 3, '10:30', '10:20'),
(3, 7, '03:40', '03:30'),
(4, 7, '07:40', '07:30'),
(4, 4, '11:40', '11:30'),
(1,  1, '01:30', NULL),
(1, 3,  NULL, '05:30'),
(2, 2, '07:15', '22:55'),
(2, 5, NULL, '07:15'),
(3, 6, '22:10', NULL),
(3, 3, NULL, '10:45'),
(4, 6, '06:00', NULL),
(4, 1, NULL, '18:00'),
(5, 3, '14:20', NULL),
(5, 4, NULL, '16:40');

INSERT INTO User
(FirstName, LastName, Email, Password, RoleType)
VALUES
('Yulia', 'Sidorova', NULL, NULL, 'CUSTOMER'),
('Oleg', 'Ivanov', NULL, NULL, 'CUSTOMER'),
('Max', 'Bars', NULL, NULL, 'CUSTOMER'),
('Gleb', 'Krotov', 'gleb@gmail.com', 'gleb', 'OPERATOR'),
('Olya', 'Petrich', 'admin@gmail.com', 'admin', 'ADMIN');

INSERT INTO Ticket
(UserId, RouteId, ScheduleDepartureStationId, ScheduleArrivalStationId, Price, TripDate, Status)
VALUES
(1, 1, 2, 3, 123.56, '2024-04-12', TRUE);













