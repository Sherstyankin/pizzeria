--liquibase formatted sql
--changeset natallia:1
--comment first migration


create TABLE IF NOT EXISTS users
(
    id
    SERIAL
    PRIMARY
    KEY,
    username
    VARCHAR
(
    50
) NOT NULL UNIQUE,
    password VARCHAR
(
    128
),
    email VARCHAR
(
    100
) NOT NULL UNIQUE,
    address VARCHAR
(
    256
) NOT NULL,
    role VARCHAR
(
    10
)
    );

create TABLE IF NOT EXISTS pizzas
(
    id
    SERIAL
    PRIMARY
    KEY,
    pizza_name
    VARCHAR
(
    50
) NOT NULL,
    count INT NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY
(
    user_id
) REFERENCES users
(
    id
)
    );



insert into users (username, password, email, address, role)
values ('nata', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'nata@gmail.com', 'Sadovay 5,35',
        'USER'),
       ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'admin@gmail.com', 'Tyrova 3, 55',
        'ADMIN');


--rollback truncate table users;
--rollback truncate table pizzas;
