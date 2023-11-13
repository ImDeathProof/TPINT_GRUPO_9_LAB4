create database bancoDB;
USE bancoDB;
CREATE TABLE Usuario (
IDUsuario INT AUTO_INCREMENT PRIMARY KEY,
Username VARCHAR(50) NOT NULL,
Pass VARCHAR(50) NOT NULL,
DNI VARCHAR(20) NOT NULL,
CUIL VARCHAR(20) NOT NULL,
Nombre VARCHAR(50) NOT NULL,
Apellido VARCHAR(50) NOT NULL,
Sexo CHAR(1) NOT NULL,
Nacionalidad VARCHAR(50) NOT NULL,
FechaNacimiento DATE NOT NULL,
Direccion VARCHAR(100) NOT NULL,
Localidad VARCHAR(100) NOT NULL,
Provincia VARCHAR(50) NOT NULL,
Mail VARCHAR(100) NOT NULL,
Telefono VARCHAR(20) NOT NULL,
ADMIN boolean NOT NULL,
Bloqueado boolean NOT NULL
);

CREATE TABLE Cuenta (
IDCuenta INT AUTO_INCREMENT PRIMARY KEY,
TipoCuenta ENUM('Ahorros', 'Corriente') NOT NULL,
Saldo DECIMAL(10, 2) NOT NULL,
NumeroCuenta VARCHAR(20) NOT NULL,
Fecha_Creacion DATETIME NOT NULL,
Estado boolean NOT NULL,
CBU VARCHAR(20) UNIQUE NOT NULL,

IDUsuario INT NOT NULL,
FOREIGN KEY (IDUsuario) REFERENCES Usuario(IDUsuario)
);

CREATE TABLE Prestamos (
IDPrestamo INT AUTO_INCREMENT PRIMARY KEY,
MontoTotal DECIMAL(10, 2) NOT NULL,
Importe_x_Cuota DECIMAL(10, 2) NOT NULL,
Plazo_Pago INT,
MontoAprobado DECIMAL(10, 2),
TasaInteres DECIMAL(5, 2),
Fecha_Pedido DATETIME NOT NULL,
EstadoPrestamo ENUM('Pendiente', 'Aprobado', 'Rechazado'),
IDCuenta VARCHAR(20) NOT NULL,

IDUsuario INT NOT NULL,
FOREIGN KEY (IDUsuario) REFERENCES Usuario(IDUsuario)
);

CREATE TABLE Cuotas_x_Clientes (
IDCuota INT AUTO_INCREMENT PRIMARY KEY,
Monto_a_Pagar DECIMAL(10, 2) NOT NULL,
Estado ENUM('Pagado', 'No Pagado', 'Vencido'),
Fecha_Pago DATETIME NOT NULL,
Nro_Cuota INT NOT NULL,
Cuotas_Totales INT NOT NULL,

IDPrestamo INT NOT NULL,
FOREIGN KEY (IDPrestamo) REFERENCES Prestamos(IDPrestamo),
IDUsuario INT NOT NULL,
FOREIGN KEY (IDUsuario) REFERENCES Usuario(IDUsuario),
IDCuenta INT NOT NULL,
foreign key (IDCuenta) references Cuenta(IDCuenta)
);

CREATE TABLE TiposMovimientos(
IDTipo INT PRIMARY KEY,
Descripcion VARCHAR(100) NOT NULL
);

CREATE TABLE Movimiento (
IDMoviento INT AUTO_INCREMENT PRIMARY KEY,
Monto INT NOT NULL,
Fecha DATETIME NOT NULL,
Detalles VARCHAR(100) NOT NULL,

IDTipo int NOT NULL,
FOREIGN KEY (IDTipo) REFERENCES TiposMovimientos(IDTipo),
IDUsuario INT NOT NULL,
FOREIGN KEY (IDUsuario) REFERENCES Usuario(IDUsuario),
IDCuenta INT NOT NULL,
foreign key (IDCuenta) references Cuenta(IDCuenta)
);

ALTER TABLE Prestamos
DROP INDEX IDCuenta;
ALTER TABLE cuotas_x_clientes
ADD COLUMN Nro_Cuota INT NOT NULL,
ADD COLUMN Cuotas_Totales INT NOT NULL,
ADD COLUMN IDCuota INT AUTO_INCREMENT PRIMARY KEY,
MODIFY COLUMN Monto_a_Pagar DECIMAL(10, 2) NOT NULL;