use OnlineShop
go

-- insert tables to 
insert into Tables(Name) values
	('OrderProducts'),
	('products'),
	('categories')
go


--insert the views
insert into Views(Name) values
	('view1'),
	('view2'),
	('view3')
go

--tests
create or alter procedure deleteTop3
	@nr int
as
begin
	delete from OrderProducts where
	orderId in (select top (@nr) orderId from OrderProducts order by orderId desc)

	delete from ProductOrder where
	orderId in (select top (@nr) orderId from ProductOrder order by orderId desc)

end
go

create or alter procedure deleteTop2
	@nr int
as
begin
	delete from products where
	pId in (select top (@nr) pId from products order by pId desc)

end
go

create or alter procedure deleteTop1
	@nr int
as
begin
	delete from categories where 
	catId in (select top (@nr) catId from categories order by catId desc)
end
go


--table insertions

create or alter procedure insertTable3
	@nr_linii int
as
begin
	declare @i int = 1
	
	

	declare @custid int
	select @custid = min(cId) from customers

	declare @ordid int
	select @ordid = max(orderId) from ProductOrder

	declare @aux int
	select @aux = max(orderid) from OrderProducts
	if @aux > @ordid
		begin
			set @ordid = @aux
		end


	declare @productid int
	select @productid = min(pId) from products

	

	while @i <= @nr_linii
		begin
			set @ordid = @ordid + 1
			insert into ProductOrder(orderId, custId) values (@ordid, @custid)
			insert into OrderProducts(orderId, pId) values(@ordid, @productid)

			set @i = @i + 1
		end
	

end
go

create or alter procedure insertTable2
	@nr_linii int
as
begin
	declare @i int = 1

	declare @catid int
	select @catid = min(catId) from categories

	declare @pid int
	select @pid = max(pId) from products

	while @i <= @nr_linii
	begin
		set @pid = @pid + 1
		insert into products(pId, pName, pDescription, catId) values (@pid, 'TestName', 'TestDescription',@catid)
		set @i = @i + 1
	end


end
go

create or alter procedure insertTable1
	@nr_linii int
as
begin
	declare @catid int
	select @catid = max(catId) from categories

	declare @i int = 1

	while @i <= @nr_linii
	begin
		set @catid = @catid + 1
		insert into categories(catId ,catName) values (@catid, 'TestName')
		set @i = @i + 1
	end


end
go


-- create the views
create or alter procedure selectTable3
	(@nr int)
as
begin
	select top (@nr) * from OrderProducts
end
go

create or alter procedure selectTable2
	(@nr int)
as
begin
	select top (@nr) * from products
end
go

create or alter procedure selectTable1
	(@nr int)
as
begin
	select top (@nr) * from categories
end
go


--insert tests

insert into Tests(Name) values
	('insertTable1'),
	('insertTable2'),
	('insertTable3'),
	('selectTable1'),
	('selectTable2'),
	('selectTable3'),
	('deleteTop3'),
	('deleteTop2'),
	('deleteTop1')



insert into TestViews(TestID, ViewID) values
(4, 1),
(5, 2),
(6, 3)





delete from TestTables

insert into TestTables(TestID, TableID, NoOfRows, Position)
values


(1, 6, 100, 1),
(9, 5, 100, 2),
(2, 4, 500, 3),
(8, 4, 500, 4),
(3, 5, 1000, 5),
(7, 6, 1000, 6)
go






set nocount on
go



create or alter procedure runTests
as
begin

	declare @i int = 1
	
	declare @posMax int
	select @posMax = max(Position) from TestTables

	declare @startAll datetime = getdate()
	declare @testId0 int
	select @testId0 = max(TestRunID) from TestRuns

	insert into TestRuns(TestRuns.Description, StartAt, EndAt) values ('FirstTest', @startAll, @startAll)


	while @i <= @posMax
	begin
		

		
		declare @testId int
		declare @tableId int
		declare @procName varchar(64)
		declare @noRows int
		declare @commId int

		select @testId = TestID from TestTables where Position = @i 
		select @tableId  = TableID from TestTables where Position = @i
		select @noRows = NoOfRows from TestTables where Position = @i

		select @procName = Tests.Name from Tests where TestID = @testId
		
		declare @startTableTest datetime = getdate()
		exec @procName @noRows
		--print @procName
		--print @noRows


		declare @viewStart datetime = getdate()
		select @commId = ViewID from TestViews where TestID = @tableId - 3
		select @procName = Tests.Name from Tests where TestID = @commId
		exec @procName @noRows
		declare @viewEnd datetime = getdate()
		insert into TestRunViews values(@testId0, @tableId - 3, @viewStart, @viewEnd)		
	


		set @i = @i + 1
		select @testId = TestID from TestTables where Position = @i 
		select @tableId  = TableID from TestTables where Position = @i
		select @noRows = NoOfRows from TestTables where Position = @i

		select @procName = Tests.Name from Tests where TestID = @testId
		exec @procName @noRows

		declare @endTableTest datetime = getdate()

		--print @procName
		--print @noRows

		insert into TestRunTables values(@testId0, @tableId, @startTableTest, @endTableTest)

		set @i = @i + 1
		
	end

	declare @endAll datetime = getdate()
	update TestRuns set EndAt = @endAll where StartAt = @startAll

end
go

exec runTests





select * from ProductOrder
select * from OrderProducts
select * from products
select * from categories



exec insertTable1 100
exec insertTable2 1000
exec insertTable3 500
exec deleteTop3 500
exec deleteTop2 1000
exec deleteTop1 100