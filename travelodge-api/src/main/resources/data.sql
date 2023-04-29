/*
insert into Hotel (id,code ,hotel_Url,longitude, title, last_Updated, latitude)
values (641, 'GB0891','/hotels/641/London-Acton-hotel', -0.256519300000036,'London Acton', '2023-04-25', 51.5223667);

insert into Hotel (id,code ,hotel_Url,longitude, title, last_Updated, latitude)
values (11641, 'GB0891','/hotels/641/London-Acton-hotel', -0.256519300000036,'London Acton', '2023-04-25', 51.5223667);




INSERT INTO price (id, price, hotel_id, date, has_Availability, last_Updated)
vALUES(1999, 111.11,  11641, '2023-11-22', true, '2013-11-22');

INSERT INTO price (id, price, hotel_id, date, has_Availability, last_Updated)
vALUES(1998, 69.69,  11641, '2024-01-01', true, '2014-01-01');

INSERT INTO price (id, price, hotel_id, date, has_Availability, last_Updated)
vALUES(1997, 70.69,  11641, '2025-01-01', true, '2015-01-01');


INSERT INTO old_price (id, price, current_price_id, fetch_Date_Time)
vALUES(100, 70.69,1999, '2015-01-01');

INSERT INTO old_price (id, price, current_price_id, fetch_Date_Time)
vALUES(101, 80.69,1999, '2016-01-01');

INSERT INTO old_price (id, price, current_price_id, fetch_Date_Time)
vALUES(102, 90.69,1999, '2017-01-01');

/*

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
*/