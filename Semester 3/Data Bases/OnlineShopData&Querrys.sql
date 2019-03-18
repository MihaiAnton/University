use OnlineShop
go

--Adding data
insert into ProductOrder values (1,20);
insert into customers values (7,'Vlad');
insert into customerAddress values(7,3);

--Where: 4
--group by: 3
--distinct: 2
--having: 2
--more than 2: 7
--m-n: 6


--Queries

--Selects the phones from the products table(Where querry)
select p.pName
from products p
where p.catId = 1;

--Selects the iphones models before X(Where querry)
select p.pName
from products p
where pName like 'i__%X';

--Selects the customers living in 16(Where querry, m-n)
select c.cName, a.addres
from customers c inner join customerAddress ca on c.cId = ca.cId
inner join addresses a on ca.addId = a.addId
where a.addres = '16';

--Selects the products with price under 3000(Where)
select p.pName, p.pDescription
from products p inner join prices pr on p.pId = pr.prodId
where pr.price < 3000;

--Groups the products by their categories and counts them(group by)
select c.catName, q.cnt as CatCount
from categories c
right join (
	select COUNT(p.pId) as cnt, p.catId
	from products p left join categories c on p.catId = c.catId
	group by(p.catId)
) q
on q.catId = c.catId
order by CatCount;

--Show the products for a customer
select c.cName, p.pName
from customers c inner join ProductOrder prodOrd on c.cId = prodOrd.custId
inner join OrderProducts ordProd on prodOrd.orderId = ordProd.orderId
inner join products p on ordProd.pId = p.pId
where c.cName = 'Mihai';

--Compute the total ordered price for each customer(m-n, >2 , having)
select c.cName, sum(pr.price) as totalPrice
from customers c inner join ProductOrder prodOrd on c.cId = prodOrd.custId
inner join OrderProducts ordProd on prodOrd.orderId = ordProd.orderId
inner join products p on p.pId = ordProd.pId
inner join prices pr on pr.prodId = p.pId
group by c.cName;

--Show the top 10 most expensive orders(m-n, >2 , having)
select top 10 q.cName, q.totalPrice as prices
from (
	select c.cName, sum(pr.price) as totalPrice
	from customers c inner join ProductOrder prodOrd on c.cId = prodOrd.custId
	inner join OrderProducts ordProd on prodOrd.orderId = ordProd.orderId
	inner join products p on p.pId = ordProd.pId
	inner join prices pr on pr.prodId = p.pId
group by c.cName ) q
order by prices desc;

--Shows how many customers live at the same address(>2, m-n)
select a.addres, count (c.cName)
from customers c
inner join customerAddress ca on c.cId = ca.cId
inner join addresses a on a.addId = ca.addId
group by a.addres;


--Maps the clients to the addresses(>2 tables, m-n)
select customers.cName, a.addres
from customers
left join customerAddress ca on customers.cId = ca.cId
left join addresses a on ca.addId = a.addId;

--Displays the products that appear in orders, one or multiple times
select distinct p.pName
from customers c
inner join productOrder pr on c.cId = pr.custId
inner join OrderProducts op on pr.orderId = op.orderId
inner join products p on op.pId = p.pId;

--Display the number of items ordered for each product(m-n, >2, group by)
select p.pName, count(p.pName) as ItemsOrdered
from products p
inner join OrderProducts op on op.pId = p.pId
inner join ProductOrder po on po.orderId = op.orderId
group by(p.pName);

--Shows customers that have orders
select distinct c.cName
from customers c
inner join ProductOrder po on po.custId = c.cId;







--Viewing all the data in the tables
select * from products;
select * from ProductOrder;
select * from customers;
select * from addresses;
select * from customerAddress;
select * from categories;
select * from addresses;
select * from prices;
select * from OrderProducts;