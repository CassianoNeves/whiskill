-- Gerado por Oracle SQL Developer Data Modeler 4.0.3.853
--   em:        2015-06-14 14:10:42 BRT
--   site:      Oracle Database 11g
--   tipo:      Oracle Database 11g




CREATE TABLE Colaborador
  (
    IDColaborador INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Nome          VARCHAR2 (100) NOT NULL
  ) ;

CREATE TABLE Projeto
  (
    IDProjeto INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Nome      VARCHAR2 (100) NOT NULL
  ) ;

CREATE TABLE ProjetoColaborador
  (
    IDProjetoColaborador INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    IDProjeto            INTEGER NOT NULL ,
    IDColaborador        INTEGER NOT NULL ,
    TempoAlocacao        DATE NOT NULL
  ) ;

CREATE TABLE Skill
  (
    IDSkill   		INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT ,
    Nome      	VARCHAR2 (100) NOT NULL ,
    Descricao VARCHAR2 (200) NOT NULL ,
	Ativo 			BOOLEAN DEFAULT TRUE,
    Trilha_ID INTEGER NOT NULL
  ) ;

CREATE TABLE SkillColaborador
  (
    IDSkill            INTEGER NOT NULL ,
    IDColaborador      INTEGER NOT NULL ,
    IDSkillColaborador INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT 
  ) ;

CREATE TABLE SkillProjeto
  (
    IDSkill        INTEGER NOT NULL ,
    IDProjeto      INTEGER NOT NULL ,
    IDSkillProjeto INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT
  ) ;

CREATE TABLE Trilha
  (
    IDTrilha  INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Nome      VARCHAR2 (100) ,
    Descricao VARCHAR2 (200),
	Ativo 			BOOLEAN DEFAULT TRUE,
  ) ;

CREATE TABLE Usuario
  (
    IDUsuario INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT ,
    Nome      VARCHAR2 (50) NOT NULL ,
    Email     VARCHAR2 (100) NOT NULL,
    Senha     VARCHAR2 (50) NOT NULL
  ) ;


ALTER TABLE ProjetoColaborador ADD CONSTRAINT FK_ProjetoColaborador_Colab FOREIGN KEY ( IDColaborador ) REFERENCES Colaborador ( IDColaborador ) ;

ALTER TABLE ProjetoColaborador ADD CONSTRAINT FK_ProjetoColaborador_Projeto FOREIGN KEY ( IDProjeto ) REFERENCES Projeto ( IDProjeto ) ;

ALTER TABLE SkillColaborador ADD CONSTRAINT FK_SkillColaborador_Colab FOREIGN KEY ( IDColaborador ) REFERENCES Colaborador ( IDColaborador ) ;

ALTER TABLE SkillColaborador ADD CONSTRAINT FK_SkillColaborador_Skill FOREIGN KEY ( IDSkill ) REFERENCES Skill ( IDSkill ) ;

ALTER TABLE SkillProjeto ADD CONSTRAINT FK_SkillProjeto_Proj FOREIGN KEY ( IDProjeto ) REFERENCES Projeto ( IDProjeto ) ;

ALTER TABLE SkillProjeto ADD CONSTRAINT FK_SkillProjeto_Skill FOREIGN KEY ( IDSkill ) REFERENCES Skill ( IDSkill ) ;

ALTER TABLE Skill ADD CONSTRAINT FK_Skill_Trilha FOREIGN KEY ( Trilha_ID ) REFERENCES Trilha ( IDTrilha ) ;


-- Relatório do Resumo do Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                             8
-- CREATE INDEX                             0
-- ALTER TABLE                             15
-- CREATE VIEW                              0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
