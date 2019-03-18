use OnlineShop
go

create table DataBaseVersion(
	DBversion INT not null default 0,
	constraint versionGreaterThan0 check (DBversion >= 0)
)
go
insert into DataBaseVersion values(0);
go

create procedure setVersion(@version INT)
as
	if @version < 0
		begin
			raiserror('Version must be greater than 0!',11,1);
			return
		end

	delete from DataBaseVersion;
	insert into DataBaseVersion values(@version);
go


exec setVersion 0;
select * from DataBaseVersion;
go

--update[i] : exchanges the database from version i-1 into version i
--          : i = 1 represents update from the initial state the initial state
--undo[i]   : undos the data base from state i to state i-1
--			: undo 1 = reverts the database to the original state

create procedure update1
as
	--reduces the maximum product description size
	alter table products
	alter column pDescription varchar(512);

	print 'Dimensiunea descrierii pentru produs s-a redus la 512';
	

	exec setVersion 1;
go

create procedure undo1
as
	--expands the maximum size for the product description
	alter table products
	alter column pDescription VARCHAR(1024);

	print 'Dimensiunea descrierii pentru produs a crescut la 1024';
	exec setVersion 0;
go



create procedure update2
as
	--sets a default customer name as "New Customer"
	update customers
	set cName = 'New Customer'-- + STR(cId)
	where cName = null;


	alter table customers
	add constraint dfName default 'New Customer' for cName;

	print 'Nume default pentru client'

	exec setVersion 2;
go

create procedure undo2
as
	alter table customers
	drop constraint dfName;

	print 'Eliminare nume default pentru client';

	exec setVersion 1;
go



create procedure update3
as
	--creates the age table for customers
	create table customerAge(
		cId  int foreign key references customers(cId),
		age int not null check (age>0),
		constraint notRepeatcustIdinage unique (cId)
	);

	print 'Tabel pentru varste creat'
	exec setVersion 3;
go

create procedure undo3
as
	--drops the table that maps customers to ages
	drop table customerAge;

	print 'Se elimina tabelul de varsta';

	exec setVersion 2;
go



create procedure update4
as
	--adds email field to customers
	alter table customers
	add email varchar(64);

	print 'Adugare coloana email pentru client';

	exec setVersion 4;
go

create procedure undo4
as
	--drops the email field for customers
	IF EXISTS(SELECT *
          FROM   INFORMATION_SCHEMA.COLUMNS
          WHERE  TABLE_NAME = 'customers'
                 AND COLUMN_NAME = 'email') 
		begin
				alter table customers
				drop column email;
		end

	print 'Stergere coloana email pentru client';

	exec setVersion 3;
go


create procedure update5
as
	--removes the productId reference in stock table
	alter table stock
	drop foreignKey;

	print 'Eliminare cheie straina pentr stoc';

	exec setVersion 5;
go

create procedure undo5
as
	--adds foreign key to stock tablr
	alter table stock
	add constraint foreignKey FOREIGN KEY (prodId) REFERENCES products(pId);

	print 'Adaugare foreign key la stoc';

	exec setVersion 4;
go

create procedure getVersion
@version int output 
as
	set @version = (select	top 1 DBversion
				from DataBaseVersion dbv)

go


drop procedure changeDBVersion
go
create procedure changeDBVersion(@version int)
as

	
	if @version < 0 or @version > 5
	begin
		raiserror ('Version must be between 0 and 5!',11,1);
		return
	end

	declare @crtVers int
	exec getVersion @crtVers output
	
	if @version > @crtVers
		begin
			set @crtVers = @crtVers + 1;
			while @crtVers <= @version
				begin
					declare @cmd  varchar(16)
					set @cmd = 'update' + convert(varchar(4),@crtVers)
					exec @cmd
					set @crtVers = @crtVers + 1
				end
		end
	else
		begin 
			while @crtVers > @version
				begin
					declare @cmd2  varchar(16)
					set @cmd = 'undo' + convert(varchar(4),@crtVers)
					exec @cmd
					set @crtVers = @crtVers - 1
				end
		end
	print 'Schimbat la versiunea: ' + convert(varchar(4),@version)
go

select * from DataBaseVersion
go

exec changeDBVersion 0

