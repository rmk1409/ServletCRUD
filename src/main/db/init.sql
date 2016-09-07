DROP TABLE IF EXISTS Person;
DROP SEQUENCE IF EXISTS seq_for_person_id;

CREATE SEQUENCE seq_for_person_id;
CREATE TABLE IF NOT EXISTS "person" (
  "id" integer PRIMARY KEY default nextval('seq_for_person_id'),
  "name" varchar(30) DEFAULT NULL,
  "password" varchar(30) DEFAULT NULL,
  "country" varchar(30) DEFAULT NULL
);

INSERT into person(name, password, country) values ('Ivan', 'PassIvan', 'Russia');
INSERT into person(name, password, country) values ('Rmk', 'PassRmk', 'Russia');
INSERT into person(name, password, country) values ('Alex', 'PassAlex', 'USA');
INSERT into person(name, password, country) values ('Sala', 'PassSala', 'Europe');
