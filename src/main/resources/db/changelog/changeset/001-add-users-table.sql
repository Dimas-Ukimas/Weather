--liquibase formatted sql

--changeset dimasukimas:create-table
CREATE TABLE Users
(
    id       SERIAL PRIMARY KEY,
    login    VARCHAR(20) UNIQUE NOT NULL,
    password VARCHAR(60) NOT NULL
)

--rollback DROP TABLE users;