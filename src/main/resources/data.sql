CREATE TABLE IF NOT EXISTS Task(id IDENTITY PRIMARY KEY, done BOOLEAN, text VARCHAR(255));
DELETE FROM Task;
INSERT INTO Task VALUES(1, true, 'Alice - Pertemuan 2');
INSERT INTO Task VALUES(2, true, 'Greenfoot - Pertemuan 5');
INSERT INTO Task VALUES(3, false, 'Eclipse - Pertemuan 7');
