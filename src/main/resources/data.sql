--create table parcc_result (id int not null, name varchar(50) not null, school varchar(50) not null);
--create table marks (id int not null, mark int not null, subject varchar(10) not null, parccId int not null);
INSERT INTO parcc_result(parccid, name, school) VALUES (1, 'john','NJ Public school');
INSERT INTO MARKS(markid, mark, subject, parccid) VALUES (1, 100,'MATH', 1);
