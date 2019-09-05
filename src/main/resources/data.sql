DROP TABLE IF EXISTS registered_car;

CREATE TABLE registered_car
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    carNumber VARCHAR(16)              NOT NULL,
    timestamp TIMESTAMP NOT NULL,
);

INSERT INTO registered_car (id, carNumber, timestamp)
VALUES (1, '1234-AB', '2019-09-04T12:34:56+00:00'),
       (2, '2341-AB', '2019-09-05T12:34:56+00:00'),
       (3, '3412-AB', '2019-09-05T10:36:56+00:00');