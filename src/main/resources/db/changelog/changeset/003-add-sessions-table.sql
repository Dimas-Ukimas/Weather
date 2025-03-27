--liquibase formatted sql

--changeset dimasukimas:create-table

CREATE TABLE Sessions
(
    id       UUID PRIMARY KEY,
    userId   INTEGER     NOT NULL,
    expiresAt TIMESTAMP
)