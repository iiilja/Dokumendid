CREATE TABLE data_type 
( data_type numeric(10,0) NOT NULL ,
  type_name varchar(200),
  CONSTRAINT data_type_pk PRIMARY KEY (data_type)
) ;


CREATE SEQUENCE document_id ;

CREATE TABLE document 
( document numeric(10,0) NOT NULL DEFAULT nextval('document_id'),
  doc_nr text,
  name text,
  description text,
  created timestamp,
  created_by numeric(10,0),
  updated timestamp,
  updated_by numeric(10,0),
  doc_status_type_fk numeric(10,0),
  filename text,
  CONSTRAINT document_pk PRIMARY KEY (document)
) ;

CREATE SEQUENCE document_doc_type_id ;

CREATE TABLE document_doc_type 
( document_doc_type numeric(10,0) NOT NULL DEFAULT nextval('document_doc_type_id'),
  doc_type_fk numeric(10,0),
  document_fk numeric(10,0),
  CONSTRAINT document_doc_type_pk PRIMARY KEY (document_doc_type)
) ;

CREATE SEQUENCE doc_type_id ;

CREATE TABLE doc_type 
( doc_type numeric(10,0) NOT NULL DEFAULT nextval('doc_type_id'),
  super_type_fk numeric(10,0),
  level numeric(10,0),
  type_name varchar(200),
  CONSTRAINT doc_type_pk PRIMARY KEY (doc_type)
) ;

CREATE TABLE doc_catalog_type 
( doc_catalog_type numeric(10,0) NOT NULL ,
  type_name varchar(200),
  CONSTRAINT doc_catalog_type_pk PRIMARY KEY (doc_catalog_type)
) ;


CREATE SEQUENCE doc_catalog_id ;

CREATE TABLE doc_catalog
( doc_catalog numeric(10,0) NOT NULL DEFAULT nextval('doc_catalog_id'),
  catalog_owner_fk numeric(10,0),
  doc_catalog_type_fk numeric(10,0),
  name text,
  description text,
  level numeric(10,0),
  content_updated timestamp,
  content_updated_by numeric(10,0),
  upper_catalog_fk numeric(10,0),
  folder text,
  CONSTRAINT doc_catalog_pk PRIMARY KEY (doc_catalog)
) ;

CREATE SEQUENCE document_doc_catalog_id ;

CREATE TABLE document_doc_catalog
( document_doc_catalog numeric(10,0) NOT NULL DEFAULT nextval('document_doc_catalog_id'),
  document_fk numeric(10,0),
  doc_catalog_fk numeric(10,0),
  catalog_time timestamp,
  CONSTRAINT document_doc_catalog_pk PRIMARY KEY (document_doc_catalog)
) ;

CREATE SEQUENCE doc_status_type_id ;

CREATE TABLE doc_status_type 
( doc_status_type numeric(10,0) NOT NULL DEFAULT nextval('doc_status_type_id'),
  type_name varchar(200),
  CONSTRAINT doc_status_type_pk PRIMARY KEY (doc_status_type)
) ;

CREATE SEQUENCE doc_status_id ;

CREATE TABLE doc_status
( doc_status numeric(10,0) NOT NULL DEFAULT nextval('doc_status_id'),
  document_fk numeric(10,0),
  doc_status_type_fk numeric(10,0),
  status_begin timestamp,
  status_end timestamp,
  created_by numeric(10,0),
  CONSTRAINT doc_status_pk PRIMARY KEY (doc_status)
) ;


CREATE SEQUENCE doc_attribute_id ;

CREATE TABLE doc_attribute
( doc_attribute numeric(10,0) NOT NULL DEFAULT nextval('doc_attribute_id'),
  atr_type_selection_value_fk numeric(10,0),
  doc_attribute_type_fk numeric(10,0),
  document_fk numeric(10,0),
  type_name text,
  value_text text,
  value_number numeric,
  value_date date,
  data_type numeric(1,0),
  orderby numeric(10,0),
  required varchar(1),
  CONSTRAINT doc_attribute_pk PRIMARY KEY (doc_attribute)
) ;


CREATE SEQUENCE doc_type_attribute_id ;

CREATE TABLE doc_type_attribute
( doc_type_attribute numeric(10,0) NOT NULL DEFAULT nextval('doc_type_attribute_id'),
  doc_attribute_type_fk numeric(10,0),
  doc_type_fk numeric(10,0),
  orderby numeric(10,0),
  required varchar(1),
  created_by_default varchar(1),
  CONSTRAINT doc_type_attribute_pk PRIMARY KEY (doc_type_attribute)
) ;

CREATE SEQUENCE doc_attribute_type_id ;

CREATE TABLE doc_attribute_type
( doc_attribute_type numeric(10,0) NOT NULL DEFAULT nextval('doc_attribute_type_id'),
  default_selection_id_fk numeric(10,0),
  type_name text,
  default_value_text text,
  data_type_fk numeric(1,0),
  multiple_attributes varchar(1),
  CONSTRAINT doc_attribute_type_pk PRIMARY KEY (doc_attribute_type)
) ;


CREATE SEQUENCE atr_type_selection_value_id ;

CREATE TABLE atr_type_selection_value
( atr_type_selection_value numeric(10,0) NOT NULL DEFAULT nextval('atr_type_selection_value_id'),
  doc_attribute_type_fk numeric(10,0),
  value_text text,
  orderby numeric(10,0),
  CONSTRAINT atr_type_selection_value_pk PRIMARY KEY (atr_type_selection_value)
) ;


CREATE SEQUENCE doc_subject_id ;

CREATE TABLE doc_subject
( doc_subject numeric(10,0) NOT NULL DEFAULT nextval('doc_subject_id'),
  doc_subject_relation_type_fk numeric(10,0),
  doc_subject_type_fk numeric(10,0),
  document_fk numeric(10,0),
  subject_fk numeric(10,0),
  note text,
  CONSTRAINT doc_subject_pk PRIMARY KEY (doc_subject)
) ;

CREATE TABLE doc_subject_type
( doc_subject_type numeric(10,0) NOT NULL ,
  type_name varchar(200),
  CONSTRAINT doc_subject_type_pk PRIMARY KEY (doc_subject_type)
) ;

CREATE TABLE doc_subject_relation_type
( doc_subject_relation_type numeric(10,0) NOT NULL ,
  type_name varchar(200),
  CONSTRAINT doc_subject_relation_type_pk PRIMARY KEY (doc_subject_relation_type)
) ;

CREATE SEQUENCE person_id ;

CREATE TABLE person 
( person numeric(10,0) NOT NULL DEFAULT nextval('person_id'),
  first_name varchar(100),
  last_name varchar(100),
  identity_code varchar(20),
  birth_date date,
  created_by numeric(10,0),
  updated_by numeric(10,0),
  created timestamp,
  updated timestamp,
  CONSTRAINT person_pk PRIMARY KEY (person)
) ;

CREATE SEQUENCE employee_id ;

CREATE TABLE employee 
( employee numeric(10,0) NOT NULL DEFAULT nextval('employee_id'),
  person_fk numeric(10,0),
  enterprise_fk numeric(10,0),
  struct_unit_fk numeric(10,0),
  active varchar(1),
  CONSTRAINT employee_pk PRIMARY KEY (employee)
) ;

CREATE SEQUENCE customer_id ;

CREATE TABLE customer 
( customer numeric(10,0) NOT NULL DEFAULT nextval('customer_id'),
  subject_fk numeric(10,0),
  subject_type_fk numeric(10,0),
  CONSTRAINT customer_pk PRIMARY KEY (customer)
) ;

CREATE SEQUENCE user_account_id ;

CREATE TABLE user_account 
( user_account numeric(10,0) NOT NULL DEFAULT nextval('user_account_id'),
  subject_type_fk numeric(10,0),
  subject_fk numeric(10,0),
  username varchar(50),
  passw varchar(300),
  status numeric(10,0),
  valid_from date,
  valid_to date,
  created_by numeric(10,0),
  created timestamp,
  password_never_expires varchar(1),
  CONSTRAINT user_account_pk PRIMARY KEY (user_account)
) ;


CREATE SEQUENCE enterprise_id ;

CREATE TABLE enterprise
( enterprise numeric(10,0) NOT NULL DEFAULT nextval('enterprise_id'),
  name text,
  full_name text,
  created_by numeric(10,0),
  updated_by numeric(10,0),
  created timestamp,
  updated timestamp,
  CONSTRAINT enterprise_pk PRIMARY KEY (enterprise)
) ;

/* document tabeli indeksid ja piirangud */

CREATE   INDEX document_idx1
 ON document
  ( document);
  
CREATE   INDEX document_idx2
 ON document
  ( upper(doc_nr) varchar_pattern_ops);
  
CREATE   INDEX document_idx4
 ON document
  ( upper(name) varchar_pattern_ops);
  
CREATE   INDEX document_idx5
 ON document
  ( description varchar_pattern_ops);
  
CREATE   INDEX document_idx6
 ON document
  ( created);

CREATE   INDEX document_idx7
 ON document
  ( created_by);
  
CREATE   INDEX document_idx8
 ON document
  ( updated);

CREATE   INDEX document_idx9
 ON document
  ( updated_by);
  
CREATE   INDEX document_idx10
 ON document
  ( doc_status_type_fk);  

CREATE   INDEX document_idx11
 ON document
  ( upper(filename) varchar_pattern_ops);
  
/* doc_catalog tabeli indeksid ja piirangud */

CREATE   INDEX doc_catalog_idx1
 ON doc_catalog
  ( doc_catalog);
  
CREATE   INDEX doc_catalog_idx2
 ON doc_catalog
  ( upper(name) varchar_pattern_ops);

CREATE   INDEX doc_catalog_idx3
 ON doc_catalog
  ( upper(description) varchar_pattern_ops);
  
CREATE   INDEX doc_catalog_idx4
 ON doc_catalog
  ( upper_catalog_fk);
  
CREATE   INDEX doc_catalog_idx5
 ON doc_catalog
  ( catalog_owner_fk );
  
CREATE   INDEX doc_catalog_idx6
 ON doc_catalog
  ( content_updated );
  
CREATE   INDEX doc_catalog_idx7
 ON doc_catalog
  ( content_updated_by );

CREATE   INDEX doc_catalog_idx8
 ON doc_catalog
  ( upper(folder) varchar_pattern_ops);
  
 /* doc_status tabeli indeksid ja piirangud */

CREATE   INDEX doc_status_idx1
 ON doc_status
  ( doc_status);
  
CREATE   INDEX doc_status_idx2
 ON doc_status
  ( document_fk);
  
CREATE   INDEX doc_status_idx3
 ON doc_status
  ( doc_status_type_fk);
  
CREATE   INDEX doc_status_idx4
 ON doc_status
  ( created_by);
  
CREATE   INDEX doc_status_idx5
 ON doc_status
  ( status_begin);

CREATE   INDEX doc_status_idx6
 ON doc_status
  ( status_end);
    
  
/* doc_attribute tabeli indeksid ja piirangud */

CREATE   INDEX doc_attribute_idx1
 ON doc_attribute
  ( doc_attribute);

CREATE   INDEX doc_attribute_idx2
 ON doc_attribute
  ( document_fk);

CREATE   INDEX doc_attribute_idx3
 ON doc_attribute
  ( doc_attribute_type_fk);

CREATE   INDEX doc_attribute_idx4
 ON doc_attribute
  ( atr_type_selection_value_fk);

CREATE   INDEX doc_attribute_idx5
 ON doc_attribute
  ( upper(type_name) varchar_pattern_ops);
  
CREATE   INDEX doc_attribute_idx6
 ON doc_attribute
  ( upper(value_text) varchar_pattern_ops);

CREATE   INDEX doc_attribute_idx7
 ON doc_attribute
  ( value_number,data_type);
  
CREATE   INDEX doc_attribute_idx8
 ON doc_attribute
  ( value_date,data_type);
  
/* doc_attribute_type tabeli indeksid ja piirangud */

CREATE   INDEX doc_attribute_type_idx1
 ON doc_attribute_type
  ( doc_attribute_type);  

CREATE   INDEX doc_attribute_type_idx2
 ON doc_attribute_type
  ( upper(type_name) varchar_pattern_ops);  
  
  
/* doc_type tabeli indeksid ja piirangud */

CREATE   INDEX doc_type_idx1
 ON doc_type
  ( doc_type);  

CREATE   INDEX doc_type_idx2
 ON doc_type
  ( type_name varchar_pattern_ops);  
  
CREATE   INDEX doc_type_idx3
 ON doc_type
  ( super_type_fk);  

/* doc_type_attribute tabeli indeksid ja piirangud */

CREATE   INDEX doc_type_attribute_idx1
 ON doc_type_attribute
  ( doc_type_attribute);  
  
CREATE   INDEX doc_type_attribute_idx2
 ON doc_type_attribute
  ( doc_attribute_type_fk);  
  
CREATE   INDEX doc_type_attribute_idx3
 ON doc_type_attribute
  ( doc_type_fk);  
  
/* atr_type_selection_value tabeli indeksid ja piirangud */

CREATE   INDEX atr_type_selection_value_idx1
 ON atr_type_selection_value
  ( atr_type_selection_value);   

CREATE   INDEX atr_type_selection_value_idx2
 ON atr_type_selection_value
  ( doc_attribute_type_fk); 
  
/* document_doc_type tabeli indeksid ja piirangud */

CREATE   INDEX document_doc_type_idx1
 ON document_doc_type
  ( document_doc_type);  
  
CREATE   INDEX document_doc_type_idx2
 ON document_doc_type
  ( doc_type_fk);  
  
CREATE   INDEX document_doc_type_idx3
 ON document_doc_type
  ( document_fk); 

/* document_doc_catalog tabeli indeksid ja piirangud */

CREATE   INDEX document_doc_catalog_idx1
 ON document_doc_catalog
  ( document_doc_catalog);  
  
CREATE   INDEX document_doc_catalog_idx2
 ON document_doc_catalog
  ( document_fk);  
  
CREATE   INDEX document_doc_catalog_idx3
 ON document_doc_catalog
  ( doc_catalog_fk);    
  
  
/* doc_subject tabeli indeksid ja piirangud */

CREATE   INDEX doc_subject_idx1
 ON doc_subject
  ( doc_subject); 
  
CREATE   INDEX doc_subject_idx2
 ON doc_subject
  (subject_fk); 
  
CREATE   INDEX doc_subject_idx3
 ON doc_subject
  (subject_fk,doc_subject_type_fk); 
  
  
/* klassifikaatorid */
INSERT INTO data_type (data_type,type_name) VALUES (1,'string');
INSERT INTO data_type (data_type,type_name) VALUES (2,'number');
INSERT INTO data_type (data_type,type_name) VALUES (3,'kuupaev');
INSERT INTO data_type (data_type,type_name) VALUES (4,'valik nimekirjast');

INSERT INTO doc_subject_type (doc_subject_type, type_name) VALUES (1,'isik');
INSERT INTO doc_subject_type (doc_subject_type, type_name) VALUES (2,'ettevote');

INSERT INTO doc_subject_relation_type (doc_subject_relation_type, type_name) VALUES (1,'autor');
INSERT INTO doc_subject_relation_type (doc_subject_relation_type, type_name) VALUES (2,'tema kohta');
INSERT INTO doc_subject_relation_type (doc_subject_relation_type, type_name) VALUES (3,'talle saadetud');
INSERT INTO doc_subject_relation_type (doc_subject_relation_type, type_name) VALUES (4,'saatja');

INSERT INTO doc_catalog_type (doc_catalog_type,type_name)
VALUES (1,'isiklik');

INSERT INTO doc_catalog_type (doc_catalog_type,type_name)
VALUES (2,'yldine');

INSERT INTO doc_status_type (doc_status_type, type_name)
VALUES (1,'vastu voetud');



INSERT INTO doc_status_type (doc_status_type, type_name)
VALUES (2,'vastamisel');

INSERT INTO doc_status_type (doc_status_type, type_name)
VALUES (3,'kooskolastatud');

INSERT INTO doc_status_type (doc_status_type, type_name)
VALUES (4,'vastatud');


INSERT INTO doc_status_type (doc_status_type, type_name)
VALUES (5,'koostamisel');


INSERT INTO doc_type (type_name, level,super_type_fk) 
VALUES ('finantsdokument',1,0);

INSERT INTO doc_type (type_name, level,super_type_fk) 
VALUES ('leping',1,0);

INSERT INTO doc_type (type_name, level,super_type_fk) 
VALUES ('valjast saadetud',1,0);

INSERT INTO doc_type (type_name, level,super_type_fk) 
VALUES ('maaratlemata',1,0);

INSERT INTO doc_type (type_name, level,super_type_fk) 
VALUES ('arve',2,1);

INSERT INTO doc_type (type_name, level,super_type_fk) 
VALUES ('finantsaruanne',2,1);

INSERT INTO doc_type (type_name, level,super_type_fk) 
VALUES ('tarneleping',2,2);

INSERT INTO doc_type (type_name, level,super_type_fk) 
VALUES ('yyrileping',2,2);


INSERT INTO doc_type (type_name, level,super_type_fk) 
VALUES ('tooleping',2,2);

INSERT INTO doc_type (type_name, level,super_type_fk) 
VALUES ('teabenoue',2,3);

/* lisame teabenoude dokumendi liigile moned atribuudi tyybid */

INSERT INTO doc_attribute_type (type_name, data_type_fk,multiple_attributes)
VALUES ('saatjad',1,'N');

INSERT INTO doc_attribute_type (type_name, data_type_fk,default_selection_id_fk,multiple_attributes)
VALUES ('vastamise tahtaeg',4,4,'N');

INSERT INTO doc_attribute_type (type_name, data_type_fk,multiple_attributes)
VALUES ('formaat',1,'N');

INSERT INTO doc_attribute_type (type_name, data_type_fk,multiple_attributes)
VALUES ('tasumis_meetod',4,'N');

INSERT INTO doc_attribute_type (type_name, data_type_fk,multiple_attributes)
VALUES ('taarne_meetod',4,'N');

INSERT INTO doc_attribute_type (type_name, data_type_fk,multiple_attributes)
VALUES ('saatja_pangakonto_nr',2,'N');

INSERT INTO doc_attribute_type (type_name, data_type_fk,multiple_attributes)
VALUES ('saaja_pangakonto_nr',2,'N');

INSERT INTO doc_attribute_type (type_name, data_type_fk,multiple_attributes)
VALUES ('kuumakse',2,'N');

INSERT INTO doc_attribute_type (type_name, data_type_fk,multiple_attributes)
VALUES ('viitenumber',2,'N');

INSERT INTO doc_attribute_type (type_name, data_type_fk,multiple_attributes)
VALUES ('arveldusarve',2,'N');

INSERT INTO doc_attribute_type (type_name, data_type_fk,multiple_attributes)
VALUES ('kauba_kood',2,'N');

INSERT INTO doc_attribute_type (type_name, data_type_fk,multiple_attributes)
VALUES ('arve_nr',2,'N');

INSERT INTO doc_attribute_type (type_name, data_type_fk,multiple_attributes)
VALUES ('arve_summa',2,'N');



INSERT INTO atr_type_selection_value (doc_attribute_type_fk, value_text,orderby)
VALUES (2,'kahe paeva jooksul',1);

INSERT INTO atr_type_selection_value (doc_attribute_type_fk, value_text,orderby)
VALUES (2,'nadala jooksul',2);

INSERT INTO atr_type_selection_value (doc_attribute_type_fk, value_text,orderby)
VALUES (2,'kuu aja jooksul',3);

INSERT INTO atr_type_selection_value (doc_attribute_type_fk, value_text,orderby)
VALUES (2,'maaramata',4);

INSERT INTO atr_type_selection_value (doc_attribute_type_fk, value_text,orderby)
VALUES (4,'swedbank',1);

INSERT INTO atr_type_selection_value (doc_attribute_type_fk, value_text,orderby)
VALUES (4,'SEB',2);

INSERT INTO atr_type_selection_value (doc_attribute_type_fk, value_text,orderby)
VALUES (4,'Nordea',3);

INSERT INTO atr_type_selection_value (doc_attribute_type_fk, value_text,orderby)
VALUES (4,'LHV',4);

INSERT INTO atr_type_selection_value (doc_attribute_type_fk, value_text,orderby)
VALUES (4,'maaramata',5);

INSERT INTO atr_type_selection_value (doc_attribute_type_fk, value_text,orderby)
VALUES (5,'Smartpost',1);

INSERT INTO atr_type_selection_value (doc_attribute_type_fk, value_text,orderby)
VALUES (5,'Omniva',2);

INSERT INTO atr_type_selection_value (doc_attribute_type_fk, value_text,orderby)
VALUES (5,'Kohal',3);

INSERT INTO atr_type_selection_value (doc_attribute_type_fk, value_text,orderby)
VALUES (5,'maaramata',4);


/* seome atribuudi tyybid dokumendi tyybiga 'teabenoue' */
/* doc_type_fk=9 (teabenoue), doc_attribute_type-fk=1 (saatjad), created_by_deafult='Y' */
/* (see tahendab et seda tyypi atribuut (doc_attribute_type_fk-tyypi) tuleb sisestada andmebaasi */
/* kohe kui tabelisse DOCUMENT sisestatakse uus seda tyypi (doc_type_fk) atribuut. */
/* required='N' tahendab et kasutaja ei pea ekraanivormil taitma atribuudi vaartust (mis laheb valja value_text) */
/* selle andmetyybiga atribuudi puhul (doc_attribute_type_fk=1 korral on data_type=1 ehk "string") */


INSERT INTO doc_type_attribute (doc_type_fk, doc_attribute_type_fk,created_by_default, orderby,required)
VALUES (10,1,'Y',1,'N');

/*  doc_attribute_type-fk=2 (vastamise tahtaeg) */
INSERT INTO doc_type_attribute (doc_type_fk, doc_attribute_type_fk,created_by_default, orderby,required)
VALUES (10,2,'Y',2,'Y');

/*  doc_attribute_type-fk=3 (dokumendi formaadi kohta mingid tapsemad andmed) */
INSERT INTO doc_type_attribute (doc_type_fk, doc_attribute_type_fk,created_by_default, orderby,required)
VALUES (10,3,'Y',3,'N');

/* sisestame moned dokumendi kataloogid */

INSERT INTO doc_catalog (name,level,upper_catalog_fk,doc_catalog_type_fk,folder)
VALUES ('sisse tulnud',1,0,2,'/home/t567245/doc_root/1');

INSERT INTO doc_catalog (name,level,upper_catalog_fk,doc_catalog_type_fk,folder)
VALUES ('teated',1,0,2,'/home/t567245/doc_root/2');

INSERT INTO doc_catalog (name,level,upper_catalog_fk,doc_catalog_type_fk,folder)
VALUES ('toojuhendid',1,0,2,'/home/t567245/doc_root/3');

INSERT INTO doc_catalog (name,level,upper_catalog_fk,doc_catalog_type_fk,folder)
VALUES ('teabenouded',2,1,2,'/home/t567245/doc_root/1/1');

INSERT INTO doc_catalog (name,level,upper_catalog_fk,doc_catalog_type_fk,folder)
VALUES ('teated synnipaevadest',2,2,2,'/home/t567245/doc_root/2/1');

INSERT INTO doc_catalog (name,level,upper_catalog_fk,doc_catalog_type_fk,folder)
VALUES ('puhkusegraafikud',2,2,2,'/home/t567245/doc_root/2/2');

INSERT INTO doc_catalog (name,level,upper_catalog_fk,doc_catalog_type_fk,folder)
VALUES ('teated tooaja muudatustest',2,2,2,'/home/t567245/doc_root/2/3');

INSERT INTO person (first_name, last_name, identity_code, birth_date, created) VALUES ('Tauno','Toru','672727337XX','11.11.1977',NOW());
INSERT INTO person (first_name, last_name, identity_code, birth_date, created) VALUES ('Anna','Aru','57838222','11.11.1975',NOW());
INSERT INTO enterprise (name,full_name, created) VALUES ('Torupood','Torupood OY', NOW());
INSERT INTO enterprise (name,full_name, created) VALUES ('Yhendatud Systeemid','Oy yhendatud Systeemid Ltd', NOW());

/* sisestame dokumendi - yhe teabenoude mille atribuutid sisaldavad andmeid saatjate kohta (text-vali) ja */
/* vastamise tahtaegade kohta (valiku-list) */

/* created_by=1 - viit tabelisse SUBJEKT.employee */
/* doc_status_type_fk=1 'vastu voetud' */
INSERT INTO document (name,description, created, created_by, doc_status_type_fk)
VALUES ('Juhan Metsa teabenoue elektriliini asjus','Tahab teada miks elektriliin jookseb tema krundilt labi',NOW(),1,1);

/* seome dokumendi dokumendi tyybiga (kasutaja teeb seda dokumendi sisestamisel) */
INSERT INTO document_doc_type (doc_type_fk, document_fk) VALUES (10,1);

/* sellise tyybi korral tuleb nyyd lugeda tabelitest doc_type_attribute ja doc_attribute_type */
/* mis tyypi atribuudid tuleb kohe lisada dokumendile tabelisse doc_attribute (doc_type_attribute.created_by_default='Y') */

SELECT DAT.doc_attribute_type, DAT.type_name, DTA.orderby, DTA.required,DTA.created_by_default,DAT.default_selection_id_fk AS valiku_id, DAT.data_type_fk FROM doc_attribute_type DAT
INNER JOIN doc_type_attribute DTA ON DAT.doc_attribute_type=DTA.doc_attribute_type_fk WHERE DTA.doc_type_fk=10;

/* doc_attribute_type |     type_name     | orderby | required | created_by_default |  data_type_fk | valiku_id |   */
/*--------------------+-------------------+---------+----------+--------------------+-------------------------------*/
/*                  1 | saatjad           |       1  | N       | Y                  |     1                         */
/*                  2 | vastamise tahtaeg |       2  | Y       | Y                  |     4         |     4         */
/*                  2 | formaat           |       3  | N       | Y                  |     1         |               */
/* sisestame ylemisest SELECT-ist saadud info alusel dokumendile tema tyybile (10) vastavad atribuudid) */

INSERT INTO doc_attribute (document_fk,doc_attribute_type_fk,type_name,value_text,data_type,required,orderby) 
VALUES (1,1,'saatjad',NULL,1,'N',1);

/* atr_type_selection_value_fk=4 (SELECT-i tulemusest "valiku_id", viitab tabelisse atr_type_selection_value */
/* selle atribuudi vaartused valitakse tabelist atr_type_selection_value ja valja "atr_type_selection_value_fk" */
/* salvestatakse valiku id */
INSERT INTO doc_attribute (document_fk,doc_attribute_type_fk,type_name,atr_type_selection_value_fk,data_type,required,orderby) 
VALUES (1,2,'vastamise tahtaeg',4,4,'Y',2);

/* rakendus peab nyyd ekraanivormil andma selle atribuudi vaartusi valida */
SELECT ATSV.atr_type_selection_value, ATSV.value_text FROM atr_type_selection_value ATSV WHERE ATSV.doc_attribute_type_fk=2 ORDER BY ATSV.orderby;

/* atr_type_selection_value |     value_text       */
/*--------------------------+--------------------  */
/*                        5 | kahe paeva jooksul   */
/*                        6 | nadala jooksul       */
/*                        7 | kuu aja jooksul      */
/*                        8 | maaramata            */

INSERT INTO doc_attribute (document_fk,doc_attribute_type_fk,type_name,value_text,data_type,required,orderby) 
VALUES (1,3,'formaat',NULL,1,'N',3);
	
/* paneme dokumendi kataloogi 4 (teabenouded) */

INSERT INTO document_doc_catalog (doc_catalog_fk,document_fk,catalog_time)
VALUES (4,1,NOW());

/* seome dokumendi tema saatjaga, saatjaks on klient SUBJEKTI skeemist, kliendi andmed on tabelis person, */
/* samas peab selle person-iga olema seotud ka kirje tabelis customer , otsime yhe sellise */
SELECT C.customer, P.first_name,P.last_name FROM customer C INNER JOIN person P ON  C.subject_fk=P.person WHERE C.subject_type_fk=1 AND P.last_name LIKE 'Ar%';

/* customer | first_name | last_name   */
/* ----------+------------+----------- */
/*        2 | Anna       | Aru         */

/* seome dokumendi customer nr. 2-ga */

INSERT INTO doc_subject (doc_subject_type_fk, subject_fk,doc_subject_relation_type_fk,note)
VALUES (1,2,4,'tahab kiiresti vastust');

/* oletame et kasutaja muudab dokumendi staatust ekraanivormil */
UPDATE document SET doc_status_type_fk=2,updated=NOW() WHERE document=1 ;
/* rakendus (voi andmebaasi triger) peab  staatuse panema tabelisse doc_status */
/* tuleb uuendada ka vana dokumendi staatust - tuleb salvestada selle staatuse lopp */
/* eeldame et on 100% kindel - viimasel dokumendi staatusel tabelis [doc_status] status_end = NULL */
UPDATE doc_status SET status_end=NOW() WHERE document_fk=1 AND status_end IS NULL ;
INSERT INTO doc_status (document_fk,doc_status_type_fk,status_begin)
VALUES (1,1,NOW());

/* oletame et kasutaja  muudab UUESTI  dokumendi staatust ekraanivormil */
UPDATE document SET doc_status_type_fk=3,updated=NOW() WHERE document=1 ;
/* rakendus (voi andmebaasi triger) peab vana staatuse panema tabelisse doc_status */
UPDATE doc_status SET status_end=NOW() WHERE document_fk=1 AND status_end IS NULL ;
INSERT INTO doc_status (document_fk,doc_status_type_fk,status_begin)
VALUES (1,2,NOW());

/* oletame et kasutaja muudab dokumendi andmeid vormil ja sisestab atribuudi "saatjad" tekstivalja vaartuse */
UPDATE doc_attribute SET value_text='Aleksander Kaseke, Voldemar Kiik' WHERE doc_attribute=1;

/*  paneme teise dokumendi. */
INSERT INTO document (name,description, created, created_by, doc_status_type_fk)
VALUES ('2011 aasta puhkusegraafik','puhkused 2011',NOW(),1,3);

/* seome dokumendi dokumendi tyybiga (kasutaja teeb seda dokumendi sisestamisel) */
/* doc_type_fk=4 ('maaratlemata') */
INSERT INTO document_doc_type (doc_type_fk, document_fk) VALUES (4,2);

/* sellise tyybi korral tuleb nyyd lugeda tabelitest doc_type_attribute ja doc_attribute_type */	
SELECT DAT.doc_attribute_type, DAT.type_name, DTA.orderby, DTA.required,DTA.created_by_default,DAT.default_selection_id_fk AS valiku_id, DAT.data_type_fk FROM doc_attribute_type DAT
INNER JOIN doc_type_attribute DTA ON DAT.doc_attribute_type=DTA.doc_attribute_type_fk WHERE DTA.doc_type_fk=4;	

/* sellele dokumendityybiga ei ole seotud yhtegi atribuudi tyypi, seega selel dokumendi lisamisel ei */
/* pea lisama tabelisse doc_attribute yhtegi kirjet */


/* paneme dokumendi kataloogi 6 (puhkusegraafikud) */

INSERT INTO document_doc_catalog (doc_catalog_fk,document_fk,catalog_time)
VALUES (6,2,NOW());

/* seome dokumendi ettevottega, oletame et see dokument on antud ettevotte kohta (on selle ettevottega kuidagi */
/* seotud), ettevote on parit SUBJEKTI skeemist, ettevotte andmed on tabelis enterprise, */
/* Otsime yhe ettevote mille aadress algab nimega "Akadeemia" */

/*SELECT E.enterprise,E.name FROM enterprise E LEFT JOIN address A ON E.enterprise = A.subject_fk INNER JOIN address_type AT ON A.address_type_fk = AT.address_type
WHERE A.subject_type_fk = 2 AND A.street_address LIKE 'Akadeemia%' ;*/

/* enterprise |   name    */
/*------------+---------- */
/*          2 | Torupood  */

/* doc_subject_type_fk=2 (ettevote), subject_fk=2 (enterpise-valja vaartis paringu tulemusest) */
/* doc_subject_relation_type_fk=2 ("tema kohta") */
INSERT INTO doc_subject (doc_subject_type_fk, subject_fk,doc_subject_relation_type_fk,note)
VALUES (2,2,2,'artikkel selle ettevotte kohta');
		  