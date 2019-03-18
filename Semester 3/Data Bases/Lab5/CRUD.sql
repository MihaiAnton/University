use OnlineShop
go

-------------------Check functions to see if the CRUD operations are tested correctly
				   -- returns 0 on succes
				   -- returns 1 and error message on failure

 create or alter procedure CheckCRUDcategories
	@noRows int,
	@flag bit output,
	@error varchar(32) output
as
begin
	set @flag = 0
	if @noRows = 0
		begin
			set @flag = 1
			set @error = 'Row count must be > 0.'
		end
end
go

create or alter procedure CheckCRUDproducts
	@noRows int,
	@flag bit output,
	@error varchar(32) output
as
begin
	set @flag = 0
	if @noRows = 0
		begin
			set @flag = 1
			set @error = 'Row count must be > 0.'
		end
end
go

create or alter procedure CheckCRUDaddresses
	@noRows int,
	@flag bit output,
	@error varchar(32) output
as
begin
	set @flag = 0
	if @noRows = 0
		begin
			set @flag = 1
			set @error = 'Row count must be > 0.'
		end
end
go



create or alter procedure CheckCRUDorderProducts
	@noRows int,
	@flag bit output,
	@error varchar(32) output
as
begin
	set @flag = 0
	if @noRows = 0
		begin
			set @flag = 1
			set @error = 'Row count must be > 0.'
		end
end
go

create or alter procedure CheckCRUDprices
	@noRows int,
	@flag bit output,
	@error varchar(32) output
as
begin
	set @flag = 0
	if @noRows = 0
		begin
			set @flag = 1
			set @error = 'Row count must be > 0.'
		end
end
go


-------------------CRUD on table 'categories'
create or alter procedure CRUDcategories
	@noRows int
as
begin
	set nocount on
	declare @flag bit
	declare @error varchar(32)

	exec CheckCRUDcategories @noRows, @flag output, @error output
	
	if @flag = 1
		begin
			print 'Error: ' + @error
		end

	else
		begin
			declare @i int
			set @i = 1

			declare @testId int
			select top 1 @testId = catId from categories order by catId desc
			declare @idCopy int
			set @idCopy = @testId
			
			--Insert
			while @i <= @noRows
				begin
					set @testId = @testId + 1
					
					insert into categories values (@testId, 'testData')

					set @i = @i + 1
				end


			--Select
			select * from categories

			--Update
			update categories set catName='testDataUpdated' where catId > @idCopy

			--Delete
			delete categories where catId > @idCopy
			

		end

end
go




--------------------CRUD on table 'products'
create or alter procedure CRUDproducts
	@noRows int
as
begin
	set nocount on
	declare @flag bit
	declare @error varchar(32)

	exec CheckCRUDproducts @noRows, @flag output, @error output

	if @flag = 1
		begin
			print 'Error: ' + @error
		end
	else
		begin
			declare @catId int
			select top 1 @catId = catId from categories order by catId desc
			set @catId = @catId + 1

			declare @prodId int
			select top 1 @prodId = pId from products order by pId desc
			declare @idCopy int = @prodId
			set @prodId = @prodId + 1
			

			declare @i int
			set @i = 1

			--insert
			
			insert into categories values (@catId, 'Test category')
			while @i <= @noRows
				begin 
					insert into products values (@prodId, 'Test name', 'Test description', @catId)
					set @prodId = @prodId + 1
					set @i = @i + 1
				end

			--select
			select * from products

			--update
			update products set pName = 'Test name updated' where pId > @idCopy

			--delete
			delete from products where pId > @idCopy
			delete from categories where catId = @catId

		end
end
go

-------------------CRUD on table 'addresses'
create or alter procedure CRUDaddresses
	@noRows int
as
begin
	set nocount on
	declare @flag bit
	declare @error varchar(32)

	exec CheckCRUDaddresses @noRows, @flag output, @error output
	
	if @flag = 1
		begin
			print 'Error: ' + @error
		end

	else
		begin
			declare @i int
			set @i = 1

			declare @testId int
			select top 1 @testId = addId from addresses order by addId desc
			declare @idCopy int
			set @idCopy = @testId
			
			--Insert
			while @i <= @noRows
				begin
					set @testId = @testId + 1
					
					insert into addresses values (@testId, 'testData')

					set @i = @i + 1
				end


			--Select
			select * from addresses

			--Update
			update addresses set addres='testDataUpdated' where addId > @idCopy

			--Delete
			delete addresses where addId > @idCopy
			

		end

end
go


create or alter procedure CRUDorderProducts --many to many
	@noRows int
as
begin
	declare @flag bit 
	declare @error varchar(32)

	exec CheckCRUDorderProducts @noRows, @flag output, @error output

	if @flag = 1
		begin
			print 'Error: ' + @error
		end
	else
		begin
			set nocount on

			declare @prodId int
			select top 1 @prodId = pId from products order by pId desc
			set @prodId = @prodId + 1

			declare @catId int
			select top 1 @catId = catId from categories order by catId desc
			set @catId = @catId + 1

			declare @ordId int
			select top 1 @ordId = orderId from ProductOrder order by orderId desc
			declare @ordCopy int
			set @ordCopy = @ordId
			
			declare @custId int
			select top 1 @custId = cId from customers order by cId desc
			set @custId = @custId + 1

			
			print @prodId	--7
			print @catId	--5
			print @ordId	--2
			print @custId	--8

			--insert
			
			insert into categories values (@catId, 'Test category')
			insert into products values (@prodId, 'Test Name' ,'Test description', @catId)
			insert into customers values (@custId, 'Test Name')
			
			declare @i int
			set @i = 1

			while @i <= @noRows
				begin
					set @ordId = @ordId + 1

					insert into ProductOrder values (@ordId, @custId)
					insert into OrderProducts values (@ordId, @prodId)

					set @i = @i + 1
				end
				
			--select
			select * from OrderProducts

			--update

			--delete
			delete from OrderProducts where orderId > @ordCopy
			delete from ProductOrder where orderId > @ordCopy
			delete from customers where cId = @custId
			delete from products where pId = @prodId
			delete from categories where catId = @catId

		end
end
go


-----------------CRUD on table prices
create or alter procedure CRUDprices
	@noRows int
as
begin
	
	declare @flag bit
	declare @error varchar(32)

	exec CheckCRUDprices @noRows, @flag output, @error output

	if @flag = 1
		begin
			print 'Error :' + @error
		end
	else
		begin
			declare @prId int
			declare @idCopy int
			select top 1 @prId = pId from products order by pId desc
			set @idCopy = @prId

			declare @i int
			set @i = 1

			declare @catId int
			select top 1 @catId = catId from categories order by catId desc
			set @catId = @catId + 1
			insert into categories values (@catId, 'Test category')

			print @idCopy
			--insert
			while @i <= @noRows
				begin
					set @prId = @prId + 1

					insert into products values (@prId, 'Test Name', 'Test description', @catId)
					insert into prices values (@prId, 1024)

					set @i = @i + 1
				end

			--select
			select * from prices

			--update
			update prices set price = 2048 where prodId > @idCopy

			--delete
			delete from prices where prodId > @idCopy
			delete from products where pId > @idCopy
			delete from categories where catId = @catId

		end
end
go


-------------------------Views

---------View on products and prices, ordered by price

create or alter view productPrices 
as
select top 100 pr.pName as pName, prices.price as pPrice
from products pr inner join prices on pr.pId = prices.prodId
order by prices.price desc;
go

if exists (select name from sys.indexes where name = 'N_INDEX_PRICES')
	drop index N_INDEX_PRICES on productPrices
go
create nonclustered index N_INDEX_PRICES on prices(price)
go

select * from productPrices;
go

--------View on categories, ordered by name
create or alter view sortedCategories
as
select top 100 categories.catName 
from categories 
order by catName desc;
go

if exists (select name from sys.indexes where name  = 'N_INDEX_CATEGORIES')
	drop index N_INDEX_CATEGORIES on categories
go
create nonclustered index N_INDEX_CATEGORIES on categories(catName)
go



create or alter procedure test
as
begin

	exec CRUDaddresses 10
	exec CRUDcategories 10
	exec CRUDprices 10
	exec CRUDproducts 10
	exec CRUDorderProducts 10

end
go

exec test