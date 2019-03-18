

--Selects all from the product table
create view view1 as
	select *  from products;


--Selects the products with their prices
create view view2 as
	select p.pName, p.pDescription, pr.price
	from products p inner join prices pr on p.pId = pr.prodId;


create view view3 as
	select cat.catId, count(pr.pId) as ProductCount
	from products pr inner join categories cat on pr.catId = cat.catId
	group by cat.catId;

