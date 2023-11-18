INSERT INTO Localidad(Descripcion) VALUES
("Tigre"),
("Adrogué"),
("Avellaneda"),
("Banfield"),
("Bernal"),
("Berazategui"),
("Burzaco"),
("Caseros"),
("Castelar"),
("Don Torcuato"),
("Ezeiza"),
("Florencio Varela"),
("General San Martín"),
("Ituzaingó"),
("José C. Paz"),
("La Plata"),
("Lanús"),
("Lomas de Zamora"),
("Morón"),
("Quilmes"),
("Ramos Mejía"),
("San Fernando"),
("San Isidro"),
("San Justo"),
("Vicente López"),
("NINGUNA");


INSERT INTO Provincia(Descripcion) VALUES
("Buenos Aires"),
("CABA"),
("Córdoba"),
("Santa Fe"),
("Mendoza"),
("Tucumán"),
("Entre Ríos"),
("Salta"),
("Misiones"),
("Chaco"),
("Corrientes"),
("Santiago del Estero"),
("Jujuy"),
("San Juan"),
("Río Negro"),
("Neuquén"),
("Formosa"),
("Chubut"),
("San Luis"),
("Catamarca"),
("La Pampa"),
("La Rioja"),
("Santa Cruz"),
("Tierra del Fuego");

-- INSERTS DIRECCIONES

INSERT INTO Direccion (Calle, NUMERO, IDLocalidad, IDProvincia)
VALUES ('Avenida San Martín', '724', 1, 1);

INSERT INTO Direccion (Calle, NUMERO, IDLocalidad, IDProvincia)
VALUES ('Calle Belgrano', '893', 2, 5);

INSERT INTO Direccion (Calle, NUMERO, IDLocalidad, IDProvincia)
VALUES ('Paseo Sarmiento', '156', 3, 10);

INSERT INTO Direccion (Calle, NUMERO, IDLocalidad, IDProvincia)
VALUES ('Avenida Moreno', '432', 4, 15);

INSERT INTO Direccion (Calle, NUMERO, IDLocalidad, IDProvincia)
VALUES ('Boulevard Güemes', '607', 5, 20);

INSERT INTO Direccion (Calle, NUMERO, IDLocalidad, IDProvincia)
VALUES ('Pasaje Rosas', '318', 6, 23);

INSERT INTO Direccion (Calle, NUMERO, IDLocalidad, IDProvincia)
VALUES ('Avenida Alberdi', '975', 7, 8);

INSERT INTO Direccion (Calle, NUMERO, IDLocalidad, IDProvincia)
VALUES ('Calle Dorrego', '520', 8, 3);

INSERT INTO Direccion (Calle, NUMERO, IDLocalidad, IDProvincia)
VALUES ('Paseo San Juan', '649', 9, 18);

INSERT INTO Direccion (Calle, NUMERO, IDLocalidad, IDProvincia)
VALUES ('Avenida Pueyrredón', '834', 10, 12);

-- INSERTS USUARIOS
INSERT INTO Usuario (Username, Pass, DNI, CUIL, Nombre, Apellido, Sexo, Nacionalidad, FechaNacimiento, Mail, Telefono, ADMIN, Bloqueado, IDDireccion)
VALUES ('admin', 'admin', '12344442', '20123444429', 'Admin', 'Admin', 0, 'Argentino', '1977-06-12', 'admin@example.com', '123456789', TRUE, FALSE, 4);

INSERT INTO Usuario (Username, Pass, DNI, CUIL, Nombre, Apellido, Sexo, Nacionalidad, FechaNacimiento, Mail, Telefono, ADMIN, Bloqueado, IDDireccion)
VALUES ('user1', 'password1', '12345678', '20345678901', 'Juan', 'Perez', 0, 'Argentino', '1990-05-15', 'juan@example.com', '123456789', FALSE, FALSE, 1);

INSERT INTO Usuario (Username, Pass, DNI, CUIL, Nombre, Apellido, Sexo, Nacionalidad, FechaNacimiento, Mail, Telefono, ADMIN, Bloqueado, IDDireccion)
VALUES ('user2', 'password2', '23456789', '30345678901', 'Maria', 'Gomez', 1, 'Argentino', '1985-08-22', 'maria@example.com', '987654321', FALSE, TRUE, 2);

INSERT INTO Usuario (Username, Pass, DNI, CUIL, Nombre, Apellido, Sexo, Nacionalidad, FechaNacimiento, Mail, Telefono, ADMIN, Bloqueado, IDDireccion)
VALUES ('user3', 'password3', '34567890', '40345678901', 'Lucas', 'Rodriguez', 0, 'Argentino', '1992-11-10', 'lucas@example.com', '555123456', FALSE, FALSE, 3);

INSERT INTO Usuario (Username, Pass, DNI, CUIL, Nombre, Apellido, Sexo, Nacionalidad, FechaNacimiento, Mail, Telefono, ADMIN, Bloqueado, IDDireccion)
VALUES ('user4', 'password4', '45678901', '50345678901', 'Laura', 'Fernandez', 1, 'Argentino', '1988-02-28', 'laura@example.com', '333444555', FALSE, TRUE, 4);

INSERT INTO Usuario (Username, Pass, DNI, CUIL, Nombre, Apellido, Sexo, Nacionalidad, FechaNacimiento, Mail, Telefono, ADMIN, Bloqueado, IDDireccion)
VALUES ('user5', 'password5', '56789012', '60345678901', 'Matias', 'Lopez', 0, 'Argentino', '1995-07-05', 'matias@example.com', '666777888', FALSE, TRUE, 5);

INSERT INTO Usuario (Username, Pass, DNI, CUIL, Nombre, Apellido, Sexo, Nacionalidad, FechaNacimiento, Mail, Telefono, ADMIN, Bloqueado, IDDireccion)
VALUES ('user6', 'password6', '67890123', '70345678901', 'Carla', 'Garcia', 1, 'Argentino', '1991-10-18', 'carla@example.com', '999000111', FALSE, FALSE, 6);

INSERT INTO Usuario (Username, Pass, DNI, CUIL, Nombre, Apellido, Sexo, Nacionalidad, FechaNacimiento, Mail, Telefono, ADMIN, Bloqueado, IDDireccion)
VALUES ('user7', 'password7', '78901234', '80345678901', 'Pablo', 'Martinez', 0, 'Argentino', '1989-03-30', 'pablo@example.com', '111222333', FALSE, FALSE, 7);

INSERT INTO Usuario (Username, Pass, DNI, CUIL, Nombre, Apellido, Sexo, Nacionalidad, FechaNacimiento, Mail, Telefono, ADMIN, Bloqueado, IDDireccion)
VALUES ('user8', 'password8', '89012345', '90345678901', 'Ana', 'Sanchez', 1, 'Argentino', '1993-06-13', 'ana@example.com', '444555666', FALSE, FALSE, 8);

INSERT INTO Usuario (Username, Pass, DNI, CUIL, Nombre, Apellido, Sexo, Nacionalidad, FechaNacimiento, Mail, Telefono, ADMIN, Bloqueado, IDDireccion)
VALUES ('user9', 'password9', '90123456', '100345678901', 'Diego', 'Alvarez', 0, 'Argentino', '1994-09-25', 'diego@example.com', '777888999', FALSE, TRUE, 9);

INSERT INTO Usuario (Username, Pass, DNI, CUIL, Nombre, Apellido, Sexo, Nacionalidad, FechaNacimiento, Mail, Telefono, ADMIN, Bloqueado, IDDireccion)
VALUES ('user10', 'password10', '12301234', '110345678901', 'Cecilia', 'Rios', 1, 'Argentino', '1997-12-07', 'cecilia@example.com', '888999000', FALSE, FALSE, 10);

INSERT INTO Usuario (Username, Pass, DNI, CUIL, Nombre, Apellido, Sexo, Nacionalidad, FechaNacimiento, Mail, Telefono, ADMIN, Bloqueado, IDDireccion)
VALUES ('user11', 'password11', '23401234', '120345678901', 'Hector', 'Luna', 0, 'Argentino', '1996-01-20', 'hector@example.com', '111000999', FALSE, FALSE, 1);
