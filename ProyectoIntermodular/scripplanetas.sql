-- Borramos la base de datos
DROP DATABASE IF EXISTS practica_intermodular;

-- Creamos la base de datos
CREATE DATABASE practica_intermodular;

-- usamos la base de datos 
USE practica_intermodular;

-- creamos planeta
CREATE TABLE Planeta (
    id INT AUTO_INCREMENT NOT NULL,
    nombre VARCHAR(20) NOT NULL,
    radio DOUBLE NOT NULL,
    distancia_sol DOUBLE NOT NULL,
    periodo_orbita DOUBLE NOT NULL,
    temperatura INT NOT NULL,
    tipo_planeta ENUM('rocoso', 'gaseoso', 'helado') NOT NULL,
    numero_satelites INT DEFAULT 0,
    fecha_creacion DATE,
    PRIMARY KEY (id)
);

-- creamos un indice para planeta esto nos sirve para optimizar el codigo mejor
CREATE INDEX idx_planeta_nombre ON Planeta(nombre);

-- Creamos satelite
CREATE TABLE Satelite (
    id INT AUTO_INCREMENT NOT NULL,
    nombre VARCHAR(20) NOT NULL,
    planeta VARCHAR(20) NOT NULL,
    radio DOUBLE NOT NULL,
    distancia_planeta DOUBLE NOT NULL,
    periodo_orbital DOUBLE NOT NULL,
    temperatura INT NOT NULL,
    tipo ENUM('Sólido Rocoso', 'Sólido Hielo') NOT NULL,
    fecha_creacion DATE,
    PRIMARY KEY (id),
    FOREIGN KEY (planeta) REFERENCES Planeta(nombre) ON DELETE CASCADE
);


-- Cambiar el delimitador para definir triggers esto es para poder usar triguers en workbrench
DELIMITER $$

-- Trigger para actualizar el campo "fecha_creacion" en la tabla Planeta
CREATE TRIGGER before_planeta_insert
BEFORE INSERT ON Planeta
FOR EACH ROW
BEGIN
    SET NEW.fecha_creacion = CURDATE();
END$$

-- Trigger para actualizar el campo "fecha_creacion" en la tabla Satelite
CREATE TRIGGER before_satelite_insert
BEFORE INSERT ON Satelite
FOR EACH ROW
BEGIN
    SET NEW.fecha_creacion = CURDATE();
END$$

-- Crear el procedimiento almacenado para actualizar el número de satélites asociados a un planeta
DELIMITER $$

CREATE PROCEDURE actualizar_numero_satelites(IN planeta_nombre VARCHAR(20))
BEGIN
    DECLARE num_satelites INT;
    SELECT COUNT(*) INTO num_satelites FROM Satelite WHERE planeta = planeta_nombre;
    UPDATE Planeta SET numero_satelites = num_satelites WHERE nombre = planeta_nombre;
END$$

DELIMITER ;

-- Crear el disparador para actualizar automáticamente el número de satélites asociados a un planeta en la tabla Planeta
DELIMITER $$

CREATE TRIGGER after_satelite_change
AFTER INSERT ON Satelite
FOR EACH ROW
BEGIN
    CALL actualizar_numero_satelites(NEW.planeta);
END$$

DELIMITER ;


-- Restaurar el delimitador original
DELIMITER ;


-- Inserción de datos en la tabla Planeta
INSERT INTO Planeta (nombre, radio, distancia_sol, periodo_orbita, temperatura, tipo_planeta)
VALUES ('Mercurio', 2439.7, 57.9, 88, 167, 'rocoso');

INSERT INTO Planeta (nombre, radio, distancia_sol, periodo_orbita, temperatura, tipo_planeta)
VALUES ('Venus', 6051.8, 108.2, 225, 464, 'rocoso');

INSERT INTO Planeta (nombre, radio, distancia_sol, periodo_orbita, temperatura, tipo_planeta)
VALUES ('Tierra', 6371, 149.6, 365.25, 15, 'rocoso');

INSERT INTO Planeta (nombre, radio, distancia_sol, periodo_orbita, temperatura, tipo_planeta)
VALUES ('Marte', 3389.5, 227.9, 687, -65, 'rocoso');

INSERT INTO Planeta (nombre, radio, distancia_sol, periodo_orbita, temperatura, tipo_planeta)
VALUES ('Júpiter', 69911, 778.5, 4333, -110, 'gaseoso');

INSERT INTO Planeta (nombre, radio, distancia_sol, periodo_orbita, temperatura, tipo_planeta)
VALUES ('Saturno', 58232, 1429.4, 10759, -140, 'gaseoso');

INSERT INTO Planeta (nombre, radio, distancia_sol, periodo_orbita, temperatura, tipo_planeta)
VALUES ('Urano', 25362, 2870.9, 30687, -195, 'gaseoso');

INSERT INTO Planeta (nombre, radio, distancia_sol, periodo_orbita, temperatura, tipo_planeta)
VALUES ('Neptuno', 24622, 4498.3, 60190, -200, 'gaseoso');


-- Inserción de datos en la tabla Satelite
INSERT INTO Satelite (nombre, planeta, radio, distancia_planeta, periodo_orbital, temperatura, tipo)
VALUES ('Luna', 'Tierra', 1737.4, 384400, 27.3, -53, 'Sólido Rocoso');

INSERT INTO Satelite (nombre, planeta, radio, distancia_planeta, periodo_orbital, temperatura, tipo)
VALUES ('Fobos', 'Marte', 11.1, 9378, 0.3, -40, 'Sólido Rocoso');

INSERT INTO Satelite (nombre, planeta, radio, distancia_planeta, periodo_orbital, temperatura, tipo)
VALUES ('Deimos', 'Marte', 6.2, 23460, 1.3, -40, 'Sólido Rocoso');

INSERT INTO Satelite (nombre, planeta, radio, distancia_planeta, periodo_orbital, temperatura, tipo)
VALUES ('Ío', 'Júpiter', 1821.6, 421700, 1.8, -143, 'Sólido Rocoso');

INSERT INTO Satelite (nombre, planeta, radio, distancia_planeta, periodo_orbital, temperatura, tipo)
VALUES ('Europa', 'Júpiter', 1560.8, 670900, 3.5, -160, 'Sólido Hielo');

INSERT INTO Satelite (nombre, planeta, radio, distancia_planeta, periodo_orbital, temperatura, tipo)
VALUES ('Ganimedes', 'Júpiter', 2634.1, 1070400, 7.2, -163, 'Sólido Hielo');

INSERT INTO Satelite (nombre, planeta, radio, distancia_planeta, periodo_orbital, temperatura, tipo)
VALUES ('Calisto', 'Júpiter', 2410.3, 1882700, 16.7, -139, 'Sólido Hielo');

INSERT INTO Satelite (nombre, planeta, radio, distancia_planeta, periodo_orbital, temperatura, tipo)
VALUES ('Titán', 'Saturno', 2575.5, 1222000, 15.9, -179, 'Sólido Hielo');

INSERT INTO Satelite (nombre, planeta, radio, distancia_planeta, periodo_orbital, temperatura, tipo)
VALUES ('Encélado', 'Saturno', 252.1, 238000, 1.4, -201, 'Sólido Hielo');

INSERT INTO Satelite (nombre, planeta, radio, distancia_planeta, periodo_orbital, temperatura, tipo)
VALUES ('Titania', 'Urano', 788.4, 435900, 8.7, -203, 'Sólido Hielo');

INSERT INTO Satelite (nombre, planeta, radio, distancia_planeta, periodo_orbital, temperatura, tipo)
VALUES ('Oberón', 'Urano', 761.4, 583500, 13.5, -203, 'Sólido Hielo');



/*SELECT Satelite.nombre FROM Satelite JOIN Planeta ON Satelite.planeta = Planeta.nombre WHERE Planeta.nombre = 'Saturno';*/
/*select * from Satelite;*/


