USE travell;

SET FOREIGN_KEY_CHECKS = 0;


INSERT INTO user (id, active, password, username) VALUES (1, true, 'q', 'q');
INSERT INTO user_role (user_id, roles) VALUES (1,'ADMIN'),(1,'USER');


INSERT INTO city (id, name) VALUES (1, 'Brest');
INSERT INTO city (id, name) VALUES (2, 'Minsk');
INSERT INTO city (id, name) VALUES (3, 'Grodno');
INSERT INTO city (id, name) VALUES (4, 'Gomel');
INSERT INTO city (id, name) VALUES (5, 'Vitebsk');
INSERT INTO city (id, name) VALUES (6, 'Mogilev');


INSERT INTO trip
    (id, date, time, from_city, where_city, amount_comfort_place, amount_economy_place, driver_name, model_car, price_comfort_place, price_economy_place)
    VALUES
    (1, '2019-03-01','14:15', 1, 2, 3, 7, 'Jack Willshare', 'Renault Trafic II Passenger', 14, 9);
INSERT INTO trip
    (id, date, time, from_city, where_city, amount_comfort_place, amount_economy_place, driver_name, model_car, price_comfort_place, price_economy_place)
    VALUES
    (2, '2019-03-01','11:10', 2, 1, 5, 9, 'John Bucket', 'Peugeot Boxer 1.9 D', 14, 9);
INSERT INTO trip
    (id, date, time, from_city, where_city, amount_comfort_place, amount_economy_place, driver_name, model_car, price_comfort_place, price_economy_place)
    VALUES
    (3, '2019-03-01','10:55', 3, 2, 4, 8, 'Alex Ferguson', 'Ford Transit 2.2', 15, 10);
INSERT INTO trip
    (id, date, time, from_city, where_city, amount_comfort_place, amount_economy_place, driver_name, model_car, price_comfort_place, price_economy_place)
    VALUES
    (4, '2019-03-01','17:35', 2, 3, 4, 8, 'Alex Ferguson', 'Ford Transit 2.2', 15, 10);
INSERT INTO trip
    (id, date, time, from_city, where_city, amount_comfort_place, amount_economy_place, driver_name, model_car, price_comfort_place, price_economy_place)
    VALUES
    (5, '2019-03-01','13:55', 4, 2, 5, 11, 'Andrew Robertson', 'Mercedes-Benz Vito', 15, 10);
INSERT INTO trip
    (id, date, time, from_city, where_city, amount_comfort_place, amount_economy_place, driver_name, model_car, price_comfort_place, price_economy_place)
    VALUES
    (6, '2019-03-01','20:15', 2, 4, 5, 11, 'Andrew Robertson', 'Mercedes-Benz Vito', 15, 10);
INSERT INTO trip
    (id, date, time, from_city, where_city, amount_comfort_place, amount_economy_place, driver_name, model_car, price_comfort_place, price_economy_place)
    VALUES
    (7, '2019-03-01','06:05', 5, 2, 3, 10, 'Kun Aguero', 'Fiat Ducato Universal Transformer', 18, 11);
INSERT INTO trip
    (id, date, time, from_city, where_city, amount_comfort_place, amount_economy_place, driver_name, model_car, price_comfort_place, price_economy_place)
    VALUES
    (8, '2019-03-01','16:35', 2, 5, 3, 10, 'Kun Aguero', 'Fiat Ducato Universal Transformer', 18, 11);
INSERT INTO trip
    (id, date, time, from_city, where_city, amount_comfort_place, amount_economy_place, driver_name, model_car, price_comfort_place, price_economy_place)
    VALUES
    (9, '2019-03-01','07:20', 6, 2, 3, 10, 'Marcos Alonso', 'Peugeot Boxer 1.9 D', 15, 9);
INSERT INTO trip
    (id, date, time, from_city, where_city, amount_comfort_place, amount_economy_place, driver_name, model_car, price_comfort_place, price_economy_place)
    VALUES
    (10, '2019-03-01','17:00', 2, 6, 3, 10, 'Marcos Alonso', 'Peugeot Boxer 1.9 D', 15, 9);


SET FOREIGN_KEY_CHECKS = 1;