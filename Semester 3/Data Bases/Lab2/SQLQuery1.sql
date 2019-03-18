

create database CeainarieLab1;
go

use CeainarieLab1;
go


create table arome (
	aId int primary key identity,
	denumire varchar(50),
	intensitate int not null 
);
go

create table tipuri(
	tId int primary key,
	nume varchar(50) default 'Ceai',
	origine varchar(50) 
);
go


create table ceaiuri(
	ceaId int primary key identity,
	denumire varchar(64),
	gramaj int check(gramaj>0 and gramaj < 1000),
	tId int foreign key references tipuri(tId)
);
go

create table comenzi(
	cId int foreign key references ceaiuri(ceaId),
	aId int foreign key references arome(aId),
	nrA int default 1,
	constraint pkComenzi primary key (cId, aId)
);
go

create table coduriBare(
	cbId int primary key foreign key references ceaiuri(ceaId),
	cod varchar(64)
);
go




