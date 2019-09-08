DROP TABLE car;

CREATE TABLE car
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    car_Number VARCHAR(16) NOT NULL UNIQUE,
    time_stamp TIMESTAMP   NOT NULL,
);

INSERT INTO car (id, car_Number, time_stamp)
VALUES (1, '1234-AB', '2019-09-04T12:34:56+00:00'),
       (2, '2341-AB', '2019-09-05T12:34:56+00:00'),
       (3, '3451-AB', '2019-09-05T12:34:56+00:00'),
       (4, '4512-AB', '2019-09-05T12:34:56+00:00'),
       (5, '5123-AB', '2019-09-05T10:36:56+00:00');