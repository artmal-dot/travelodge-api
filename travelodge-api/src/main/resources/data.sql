
insert into Hotel (id, hotel_Code, title)
values (100, 'asd11','Warsaw');


insert into Hotel (id, hotel_Code, title)
values (101, 'asd12','Ealing');


insert into Hotel (id, hotel_Code, title)
values (102, 'asd13','London');

UPDATE Hotel
SET hotel_Code = 'fraa11', title= 'Frankfurt'
WHERE id = 101;


INSERT INTO price (id, price, hotel_id, date)
vALUES(100, 11.11,100, '2023-11-22');

INSERT INTO price (id, price, hotel_id, date)
vALUES(200, 22.11,100,'2011-11-19');