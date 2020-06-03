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

INSERT INTO poi(name, coordX, coordY) VALUES('Lanchonete',27 ,12);
INSERT INTO poi(name, coordX, coordY) VALUES('Posto',31 ,18);
INSERT INTO poi(name, coordX, coordY) VALUES('Joalheria',15 ,12);
INSERT INTO poi(name, coordX, coordY) VALUES('Floricultura',19 ,21);
INSERT INTO poi(name, coordX, coordY) VALUES('Pub',12 ,8);
INSERT INTO poi(name, coordX, coordY) VALUES('Supermercado',23 ,6);
INSERT INTO poi(name, coordX, coordY) VALUES('Churrascaria',28 ,2);