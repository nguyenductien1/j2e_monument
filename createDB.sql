--Celebrite

CREATE TABLE projetPA.Celebrite (
	numCelebrite INTEGER(8) auto_increment NOT NULL,
	nom varchar(46) NULL,
	prenom varchar(16) NULL,
	nationalite varchar(10) NULL,
	epoque varchar(6) NULL,
	CONSTRAINT Celebrite_PK PRIMARY KEY (numCelebrite)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;

--Departement
CREATE TABLE projetPA.Departement (
	dep varchar(4) NOT NULL,
	chefLieu varchar(46) NULL,
	nomDep varchar(30) NULL,
	reg varchar(4) NULL,
	CONSTRAINT Departement_PK PRIMARY KEY (dep)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;

//Lieu
CREATE TABLE projetPA.Lieu (
	codeInsee varchar(5) NOT NULL,
	dep varchar(4) NULL,
	nomCom varchar(46) NULL,
	longitude FLOAT NULL,
	latitude FLOAT NULL,
	CONSTRAINT Lieu_PK PRIMARY KEY (codeInsee)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;

//FK Lieu - Departement
ALTER TABLE projetPA.Lieu ADD CONSTRAINT Lieu_FK FOREIGN KEY (dep) REFERENCES projetPA.Departement(dep);
ALTER TABLE projetPA.Departement ADD CONSTRAINT Departement_FK FOREIGN KEY (chefLieu) REFERENCES projetPA.Lieu(codeInsee);

--Monument
CREATE TABLE projetPA.Monument (
	codeM varchar(5) NOT NULL,
	nomM varchar(25) NULL,
	proprietaire varchar(10) NULL,
	typeMonument varchar(16) NULL,
	longitude FLOAT NULL,
	latitude FLOAT NULL,
	codeLieu varchar(5) NULL,
	CONSTRAINT Monument_PK PRIMARY KEY (codeM),
	CONSTRAINT Monument_FK FOREIGN KEY (codeLieu) REFERENCES projetPA.Lieu(codeInsee)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;

--Associe A
CREATE TABLE projetPA.AssocieA (
	codeM varchar(5) NOT NULL,
	numCelebrite INTEGER NOT NULL,
	CONSTRAINT AssocieA_PK PRIMARY KEY (codeM,numCelebrite),
	CONSTRAINT AssocieA_FK FOREIGN KEY (codeM) REFERENCES projetPA.Monument(codeM),
	CONSTRAINT AssocieA_FK_1 FOREIGN KEY (numCelebrite) REFERENCES projetPA.Celebrite(numCelebrite)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;

--Alter Table
ALTER TABLE projetPA.Departement MODIFY COLUMN reg varchar(46) CHARACTER SET utf8 COLLATE utf8_general_ci NULL;

--Create user
GRANT Create view ON projetPA.* TO 'dtn'@'%';
GRANT Delete ON projetPA.* TO 'dtn'@'%';
GRANT Drop ON projetPA.* TO 'dtn'@'%';
GRANT Grant option ON projetPA.* TO 'dtn'@'%';
GRANT Index ON projetPA.* TO 'dtn'@'%';
GRANT Insert ON projetPA.* TO 'dtn'@'%';
GRANT References ON projetPA.* TO 'dtn'@'%';
GRANT Select ON projetPA.* TO 'dtn'@'%';
GRANT Show view ON projetPA.* TO 'dtn'@'%';
GRANT Trigger ON projetPA.* TO 'dtn'@'%';
GRANT Update ON projetPA.* TO 'dtn'@'%';

