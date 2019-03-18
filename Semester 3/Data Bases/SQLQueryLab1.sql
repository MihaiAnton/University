USE master;

--Create the database
CREATE DATABASE OnlineShop;
USE OnlineShop;

--Create the tables
CREATE TABLE categories(
	catId INT PRIMARY KEY check(catId>0),
	catName VARCHAR(64)
);


CREATE TABLE products(
	pId INT PRIMARY KEY,
	pName VARCHAR(64) NOT NULL,
	pDescription VARCHAR(1024),
	constraint defaultDesc DEFAULT 'No description available',
	catId INT FOREIGN KEY REFERENCES categories(catId)
);

CREATE TABLE prices(
	prodId INT PRIMARY KEY,
	price INT NOT NULL,
	FOREIGN KEY (prodId) REFERENCES products(pId)
);

CREATE TABLE stock(
	prodId INT PRIMARY KEY,
	pieces INT DEFAULT 0,
	constraint foreignKey FOREIGN KEY (prodId) REFERENCES products(pId)
);

CREATE TABLE offers(
	offId INT PRIMARY KEY CHECK(offId > 0),
	catName VARCHAR(64)
);

CREATE TABLE productOffer(
	prodId INT FOREIGN KEY REFERENCES products(pId),
	offId INT FOREIGN KEY REFERENCES offers(offId),
	CONSTRAINT notRepeat UNIQUE(prodId, offId)
);

CREATE TABLE customers(
	cId INT PRIMARY KEY,
	cName VARCHAR(64)
);

CREATE TABLE addresses(
	addId INT PRIMARY KEY,
	addres VARCHAR(64)
);

CREATE TABLE customerAddress(
	cId INT FOREIGN KEY REFERENCES customers(cId),
	addId INT FOREIGN KEY REFERENCES addresses(addId),
	CONSTRAINT uniqueCustAddCombination UNIQUE(cId, addId)
);


CREATE TABLE ProductOrder(
	orderId INT PRIMARY KEY,
	custId INT FOREIGN KEY REFERENCES customers(cId)
);

CREATE TABLE OrderProducts(
	orderId INT FOREIGN KEY REFERENCES ProductOrder(orderId),
	pId INT FOREIGN KEY REFERENCES products(pId),
	CONSTRAINT uniqueOrderProduct PRIMARY KEY(orderId, pId)
);


--Add data

INSERT INTO categories VALUES(1,'phone');
INSERT INTO categories VALUES(2,'laptop');
INSERT INTO categories VALUES(3,'gadget');

INSERT INTO products(pId, pName, catId) VALUES(1, 'iPhone 7' ,1);
INSERT INTO products(pId, pName, catId) VALUES(2, 'iPhone 8' ,1);
INSERT INTO products(pId, pName, catId) VALUES(3, 'Mi Band 2' ,3);
INSERT INTO products(pId, pName, pDescription , catId) VALUES(4, 'Mi Band 3','The newest fitness band from Xiaomi' ,3);
INSERT INTO products(pId, pName, pDescription , catId) VALUES(5, 'iPhone X','The best iPhone ever' ,3);

INSERT INTO prices VALUES (1,2500);
INSERT INTO prices VALUES (2,3300);
INSERT INTO prices VALUES (3,90);
INSERT INTO prices VALUES (4,200);
INSERT INTO prices VALUES (5,5000);

INSERT INTO stock VALUES(1,20);
INSERT INTO stock VALUES(2,28);
INSERT INTO stock VALUES(4,0);

INSERT INTO offers VALUES(1,'20% off for phones');
INSERT INTO offers VALUES(2,'Mi Band 2 30% off');

INSERT INTO productOffer VALUES(1,1);
INSERT INTO productOffer VALUES(2,1);
INSERT INTO productOffer VALUES(3,2);

INSERT INTO customers VALUES(1,'Mihai');
INSERT INTO customers VALUES(2,'Geo');
INSERT INTO customers VALUES(3,'Tudor');
INSERT INTO customers VALUES(4,'Cosmin');
INSERT INTO customers VALUES(5,'Roberta');
INSERT INTO customers VALUES(6,'Gabi');

INSERT INTO addresses VALUES(1, 'Gheorgheni');
INSERT INTO addresses VALUES(2, 'Eco');
INSERT INTO addresses VALUES(3, '16');
INSERT INTO addresses VALUES(4, 'Grigorescu');
INSERT INTO addresses VALUES(5, 'Calea Turzii');
INSERT INTO addresses VALUES(6, 'Dorobantilor');

INSERT INTO customerAddress VALUES(1,1);
INSERT INTO customerAddress VALUES(2,2);
INSERT INTO customerAddress VALUES(3,3);
INSERT INTO customerAddress VALUES(4,4);
INSERT INTO customerAddress VALUES(5,5);
INSERT INTO customerAddress VALUES(6,6);





--View data

select * from categories;
select * from products;
select * from prices;
select * from stock;
select * from offers;
select * from productOffer;
select * from customers;
select * from addresses;
select * from customerAddress;