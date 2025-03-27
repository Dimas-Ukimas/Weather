--liquibase formatted sql

--changeset dimasukimas:create-table
CREATE TABLE Locations
(
    id        SERIAL PRIMARY KEY,
    name      VARCHAR(20) NOT NULL,
    userId    INTEGER     NOT NULL,
    latitude  DECIMAL     NOT NULL,
    longitude DECIMAL     NOT NULL

--rollback DROP TABLE Locations;
)