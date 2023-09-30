use MediCount
CREATE TABLE Usuario (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(255),
    CorreoElectronico VARCHAR(255),
    Contraseña VARCHAR(255)
);

-- Crear la tabla Medicamento
CREATE TABLE Medicamento (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(255),
    Descripcion TEXT,
    Instrucciones TEXT
);

-- Crear la tabla Recordatorio
CREATE TABLE Recordatorio (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    HoraAlarma TIME,
    FechaInicio DATE,
    FechaFin DATE,
    Intervalo INT,
    UsuarioID INT,
    FOREIGN KEY (UsuarioID) REFERENCES Usuario(ID)
);

-- Crear la tabla TomarMedicamento
CREATE TABLE Registro (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    FechaHoraToma DATETIME,
    Estado ENUM('Tomado', 'NoTomado'),
    NotasAdicionales TEXT,
    UsuarioID INT,
    RecordatorioID INT,
    FOREIGN KEY (UsuarioID) REFERENCES Usuario(ID),
    FOREIGN KEY (RecordatorioID) REFERENCES Recordatorio(ID)
);

-- Crear la relación Usuario-Medicamento (tabla intermedia)
CREATE TABLE Usuario_Medicamento (
    UsuarioID INT,
    MedicamentoID INT,
    PRIMARY KEY (UsuarioID, MedicamentoID),
    FOREIGN KEY (UsuarioID) REFERENCES Usuario(ID),
    FOREIGN KEY (MedicamentoID) REFERENCES Medicamento(ID)
);

-- Crear la relación Medicamento-Recordatorio (tabla intermedia)
CREATE TABLE Medicamento_Recordatorio (
    MedicamentoID INT,
    RecordatorioID INT,
    PRIMARY KEY (MedicamentoID, RecordatorioID),
    FOREIGN KEY (MedicamentoID) REFERENCES Medicamento(ID),
    FOREIGN KEY (RecordatorioID) REFERENCES Recordatorio(ID)
);