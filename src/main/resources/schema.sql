CREATE TABLE citizen ( 
  id int(11) NOT NULL, 
  name varchar(20) NOT NULL,
  surname varchar(20) NOT NULL,
  salary FLOAT NULL, 
  takehome FLOAT NULL,
  PRIMARY KEY (id) 
);

CREATE TABLE rate ( 
  id int(11) NOT NULL, 
  band FLOAT NOT NULL,
  ratefield FLOAT NOT NULL,
  PRIMARY KEY (id) 
);