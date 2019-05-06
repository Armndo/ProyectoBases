DROP DATABASE vuelos;

CREATE DATABASE vuelos;
USE vuelos;
	
CREATE TABLE pais(
	id INT NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(80) NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE persona(
	id INT NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(30) NOT NULL,
	appaterno VARCHAR(30) NOT NULL,
	apmaterno VARCHAR(30),
	direccion VARCHAR(120),
	sexo VARCHAR(15),
	pais_id INT NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(pais_id) REFERENCES pais(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE usuario(
	id INT NOT NULL,
	email VARCHAR(30) NOT NULL UNIQUE,
	password VARCHAR(16) NOT NULL,
	tipo VARCHAR(8) NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(id) REFERENCES persona(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE pasajero(
	id INT NOT NULL,
	pasaporte VARCHAR(30) NOT NULL UNIQUE,
	PRIMARY KEY(id),
	FOREIGN KEY(id) REFERENCES persona(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE aerolinea(
	id INT NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(30) NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE empleado(
	id INT NOT NULL,
	puesto VARCHAR(30) NOT NULL,
	aerolinea_id INT,
	PRIMARY KEY(id),
	FOREIGN KEY(id) REFERENCES persona(id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY(aerolinea_id) REFERENCES aerolinea(id) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE aeropuerto(
	iata VARCHAR(4) NOT NULL,
	nombre VARCHAR(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
	pais_id INT NOT NULL,
	PRIMARY KEY(iata),
	FOREIGN KEY(pais_id) REFERENCES pais(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE fabricante(
	id INT NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(30) NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE aeronave(
	id INT NOT NULL AUTO_INCREMENT,
	modelo VARCHAR(60) NOT NULL,
	capacidad INT(3) NOT NULL,
	fabricante_id INT NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(fabricante_id) REFERENCES fabricante(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE avion(
	id INT NOT NULL AUTO_INCREMENT,
	aeronave_id INT NOT NULL,
	aerolinea_id INT NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(aeronave_id) REFERENCES aeronave(id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY(aerolinea_id) REFERENCES aerolinea(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE tripulacion(
	empleado_id INT NOT NULL,
	vuelo_id INT NOT NULL,
	PRIMARY KEY(empleado_id, vuelo_id),
	FOREIGN KEY(empleado_id) REFERENCES empleado(id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY(vuelo_id) REFERENCES vuelo(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE vuelo(
	id INT NOT NULL AUTO_INCREMENT,
	fecha DATE NOT NULL,
	hora TIME NOT NULL,
	avion_id INT NOT NULL,
	origen VARCHAR(4) NOT NULL,
	destino VARCHAR(4) NOT NULL,
	precio DOUBLE NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(avion_id) REFERENCES avion(id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY(origen) REFERENCES aeropuerto(iata) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY(destino) REFERENCES aeropuerto(iata) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE clase(
	id INT NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(30),
	PRIMARY KEY(id)
);

CREATE TABLE boleto(
	asiento INT NOT NULL AUTO_INCREMENT,
	folio INT NOT NULL UNIQUE,
	fecha_compra date NOT NULL,
	costo DOUBLE NOT NULL,
	clase_id INT NOT NULL,
	pasajero_id INT NOT NULL,
	vuelo_id INT NOT NULL,
	PRIMARY KEY(asiento, vuelo_id),
	FOREIGN KEY(clase_id) REFERENCES clase(id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY(pasajero_id) REFERENCES pasajero(id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY(vuelo_id) REFERENCES vuelo(id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO persona VALUES (1, 'Armando', 'González', null, null, null, 140);
INSERT INTO usuario VALUES (1, 'admin@mail.com', '123456', 'Empleado');
INSERT INTO empleado VALUES (1, 'Administrador', null);

SELECT ar.nombre, a.modelo, COUNT(*) FROM avion av, aerolinea ar, aeronave a WHERE av.aerolinea_id = ar.id AND av.aeronave_id = a.id GROUP BY ar.id, av.aeronave_id;
SELECT a.id, a.modelo, ar.nombre, COUNT(*) FROM avion av, aerolinea ar, aeronave a WHERE av.aerolinea_id = ar.id AND av.aeronave_id = a.id GROUP BY a.id, av.aerolinea_id;