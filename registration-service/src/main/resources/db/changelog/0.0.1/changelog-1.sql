--liquibase formatted sql
--changeset natallia:1
--comment first migration


CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(128),
    email VARCHAR(100) NOT NULL UNIQUE,
    address VARCHAR(256) NOT NULL,
    role VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS delivered_orders (
    id SERIAL PRIMARY KEY,
    pizza_name VARCHAR(50) NOT NULL,
    count INT NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

--create table user (
-- id INT(255) NOT NULL,
-- username VARCHAR(50) NOT NULL UNIQUE,
-- password VARCHAR(100) NOT NULL,
-- email VARCHAR(100) NOT NULL UNIQUE,
-- address VARCHAR(256) NOT NULL,
-- role VARCHAR(5),
-- PRIMARY KEY (id)
--);

--CREATE TABLE  DELIVERED_ORDERS (
-- id INT(255)  NOT NULL,
-- pizza_name VARCHAR(50) NOT NULL,
-- count INT(50) NOT NULL,
-- user_id INT(255) NOT NULL,
-- PRIMARY KEY (id),
-- FOREIGN KEY (user_id) REFERENCES user(id)
--);

insert into users ( username, password, email, address, role)
values ('nata', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'nata@gmail.com', 'Sadovay 5,35', 'USER'),
('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'admin@gmail.com', 'Tyrova 3, 55', 'ADMIN');


--rollback truncate table users;
--rollback truncate table delivered_orders;
