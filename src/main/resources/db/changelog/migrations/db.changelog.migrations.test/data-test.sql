--liquibase formatted sql
--changeset versao:001_structure

CREATE TABLE poi
(
    id      bigserial    NOT NULL,
    name    varchar(150) NOT NULL,
    coordX   int NOT NULL,
    coordY   int NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO poi(name, coordX, coordY) VALUES('Test 1',10 ,10);
INSERT INTO poi(name, coordX, coordY) VALUES('Test 2',20 ,20);
INSERT INTO poi(name, coordX, coordY) VALUES('Test 3',30 ,30);
INSERT INTO poi(name, coordX, coordY) VALUES('Test 4',40 ,40);
INSERT INTO poi(name, coordX, coordY) VALUES('Test 5',50 ,50);
INSERT INTO poi(name, coordX, coordY) VALUES('Test 6',60 ,60);
INSERT INTO poi(name, coordX, coordY) VALUES('Test 7',70 ,70);