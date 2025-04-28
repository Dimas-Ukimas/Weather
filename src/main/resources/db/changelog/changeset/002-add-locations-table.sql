--liquibase formatted sql

--changeset dimasukimas:create-table
CREATE TABLE Locations
(
    id        SERIAL PRIMARY KEY,
    name      VARCHAR(40) NOT NULL,
    userId    INTEGER     NOT NULL,
    latitude  DECIMAL   NOT NULL,
    longitude DECIMAL    NOT NULL


)

--changeset dimasukimas:add-unique-index
CREATE UNIQUE INDEX ux_user_location_coordinates
ON Locations (userId, latitude, longitude);

--rollback DROP INDEX ux_user_location_coordinates;
--rollback DROP TABLE Locations;