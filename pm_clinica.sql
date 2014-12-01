BEGIN TRANSACTION;
CREATE TABLE TipoExame (
        id  integer,
        nome varchar(255),
        primary key (id)
    );
INSERT INTO `TipoExame` VALUES(1,'TOMOGRAFIA COMPUTADORIZADA');
INSERT INTO `TipoExame` VALUES(2,'RESSONANCIA MAGNETICA');
INSERT INTO `TipoExame` VALUES(3,'ULTRASSONOGRAFIA');
INSERT INTO `TipoExame` VALUES(4,'ECOGRAFIA');
INSERT INTO `TipoExame` VALUES(5,'URINA');
INSERT INTO `TipoExame` VALUES(6,'ANTI SM');
INSERT INTO `TipoExame` VALUES(7,'ANTI SS-B (LA)');
INSERT INTO `TipoExame` VALUES(8,'HBE AG');
INSERT INTO `TipoExame` VALUES(9,'MICROBLOBULINA');
INSERT INTO `TipoExame` VALUES(10,'CADMIO');
INSERT INTO `TipoExame` VALUES(11,'CARBAMAZEPINA');
INSERT INTO `TipoExame` VALUES(12,'CERULOPLASMINA');
INSERT INTO `TipoExame` VALUES(13,'CHAGAS');
INSERT INTO `TipoExame` VALUES(14,'COLESTEROL');
INSERT INTO `TipoExame` VALUES(15,'CREATINA');
INSERT INTO `TipoExame` VALUES(16,'DOPPLER');
CREATE TABLE Funcionario (
        DTYPE varchar(31) not null,
        id  integer,
        nome varchar(255),
        especialidade_id integer,
        primary key (id)
    );
INSERT INTO `Funcionario` VALUES('Medico',1,'Dr. ATAUFFO RIBEIRO',1);
INSERT INTO `Funcionario` VALUES('Medico',2,'Dra. CRISTINA ALMEIDA',2);
INSERT INTO `Funcionario` VALUES('Medico',3,'Dr. CARLOS AMARAL',1);
INSERT INTO `Funcionario` VALUES('Medico',4,'Dr. ROBERTA PEREIRA DA SILVA',5);
INSERT INTO `Funcionario` VALUES('Medico',5,'Dra. NORMA FERREIRA MARSCHHAUSEN',7);
INSERT INTO `Funcionario` VALUES('Medico',6,'Dra. FERNANDA PEREIRA BARBOZA',3);
INSERT INTO `Funcionario` VALUES('Medico',7,'Dr. CARLOS EDUARDO BAPTISTA DE AGUIAR',1);
INSERT INTO `Funcionario` VALUES('Medico',8,'Dra. AIDA ROSSANA CUPELLO',3);
INSERT INTO `Funcionario` VALUES('Medico',9,'Dra. BRUNA PEREIRA PROTASIO',2);
CREATE TABLE Especialidade (
        id  integer,
        nome varchar(255),
        primary key (id)
    );
INSERT INTO `Especialidade` VALUES(1,'ALERGOLOGIA');
INSERT INTO `Especialidade` VALUES(2,'CARDIOLOGIA');
INSERT INTO `Especialidade` VALUES(3,'CIRURGIA GERAL');
INSERT INTO `Especialidade` VALUES(4,'CIRURGIA PLASTICA REPARADORA');
INSERT INTO `Especialidade` VALUES(5,'CIRURGIA VASCULAR / ANGIOLOGIA');
INSERT INTO `Especialidade` VALUES(6,'CLINICA MEDICA');
INSERT INTO `Especialidade` VALUES(7,'DERMATOLOGIA');
INSERT INTO `Especialidade` VALUES(8,'ENDOCRINOLOGIA');
INSERT INTO `Especialidade` VALUES(9,'FISIOTERAPIA AVALIA����O');
INSERT INTO `Especialidade` VALUES(10,'GASTROENTEROLOGIA');
INSERT INTO `Especialidade` VALUES(11,'GINECOLOGIA');
INSERT INTO `Especialidade` VALUES(12,'MASTOLOGIA');
INSERT INTO `Especialidade` VALUES(13,'NEFROLOGIA');
INSERT INTO `Especialidade` VALUES(14,'NEUROCIRURGIA');
INSERT INTO `Especialidade` VALUES(15,'NEUROLOGIA');
INSERT INTO `Especialidade` VALUES(16,'ORTOPEDIA E TRAUMATOLOGIA');
INSERT INTO `Especialidade` VALUES(17,'OTORRINOLARINGOLOGIA');
INSERT INTO `Especialidade` VALUES(18,'PNEUMOLOGIA');
INSERT INTO `Especialidade` VALUES(19,'PROCTOLOGIA');
INSERT INTO `Especialidade` VALUES(20,'RADIOLOGIA');
INSERT INTO `Especialidade` VALUES(21,'REUMATOLOGIA');
INSERT INTO `Especialidade` VALUES(22,'UROLOGIA');
CREATE TABLE Cliente (
        id  integer,
        dataNascimento varchar(255),
        endereco varchar(255),
        nome varchar(255),
        numCPF varchar(255) unique,
        numIdentidade varchar(255),
        telefone varchar(255),
        primary key (id)
    );
INSERT INTO `Cliente` VALUES(1,'11/02/1979','Rua Afonso Bicalho, 324','Paulo Henrique de Araujo',22121343565,12221563,'31 3344-5566');
INSERT INTO `Cliente` VALUES(2,'22/07/1988','Av. Coronel Armindo, 6654','Ana Maria de Assis',06542576587,14376838,'31 8877-6655');
CREATE TABLE Atendimento (
        DTYPE varchar(31) not null,
        id  integer,
        aprovado boolean,
        data datetime,
        tipo varchar(255),
        cliente_id integer,
        medico_id integer,
        tipoExame_id integer,
        primary key (id)
    );
;
COMMIT;
