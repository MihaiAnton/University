create database pinguini
go

use pinguini
go

--1
create table talent(
	tId int primary key identity,
	nume varchar(64),
	descr varchar(64)
)

create table cercPolar(
	cpId int primary key identity,
	nume varchar(64),
	surface int
)

create table pinguin(
	pId int primary key identity,
	name varchar(64),
	age int,
	tId int foreign key references talent(tId),
	cpId int foreign key references cercPolar(cpId)
)

create table copil(
	cId int primary key identity,
	nume varchar(64),
	age int
)

create table visit(
	cId int foreign key references copil(cId),
	pId int foreign key references pinguin(pId),
	timp int,
	entuziasm varchar(64)
)

--values
insert into cercPolar values ('nord',100),('sud',200)
insert into talent values ('jump','desc jump'),('flip','desc flip')
insert into copil values ('mihai', 20), ('geo',19),('cosmin', 18), ('gabi', 19)
insert into pinguin values ('ping1',5,1,2),('ping2',6,2,1),('ping3',2,2,1),('ping4',3,1,1)


--2
go
create or alter procedure addVisit
	@cId int,
	@pId int,
	@timp int,
	@entuziasm varchar(64)
as
begin
	declare @i int
	select @i = count(*) from visit v where (v.cId = @cId and v.pId = @pId)

	if @i = 0	--add
		begin
			insert into visit values (@cId, @pId, @timp, @entuziasm)
		end
	else		--update
		begin
			update visit
			set timp = @timp, entuziasm = @entuziasm
			where (cId = @cId and pId = @pId)
		end
end
go
exec addVisit 4,4,36,'super'

select * from visit

create 



--querry pentru 4
select p.name, t.nume
from talent t inner join pinguin p on p.tId = t.tId
		      inner join visit v on p.pId = v.pId
			  inner join copil c on c.cId = v.cId
	inner join 

	(
		select avg(c.age) as avgage
		from copil c
	)ages

	on c.age = ages.avgage




--4
go
create or alter function printPings()
returns table
as
return
	select p.name, t.nume, c.age
	from talent t inner join pinguin p on p.tId = t.tId
				  inner join visit v on p.pId = v.pId
				  inner join copil c on c.cId = v.cId
		inner join 

		(
			select avg(c.age) as avgage
			from copil c
		)ages

		on c.age = ages.avgage

		group by p.name, t.nume, c.age
go

select * from printPings()














