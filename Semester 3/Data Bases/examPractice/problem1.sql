use catalog
go

create table grupa(

	idGr int primary key identity(1,1),
	name varchar(64)
);

create table student(

	idStud int primary key identity(1,1),
	name varchar(64),
	idGr int foreign key references grupa(idGr)
);

create table task(

	idTask int primary key identity(1,1),
	name varchar(64)
);

create table grade(

	idStud int foreign key references student(idStud),
	idTask int foreign key references task(idTask),
	nota float

	constraint pk primary key (idStud, idTask)

);



create table comment(

	idCom int primary key identity(1,1),
	stat varchar(64),

	idStud int,
	idTask int,

	constraint fk foreign key (idStud, idTask) references grade(idStud, idTask)
);


insert into grupa values 
	('221'),
	('222'),
	('223')

insert into student values
	('roberta',1),
	('mihai',2),
	('geo',3),
	('cosmin',1)

insert into task values
	('map'),('bd'),('lisp'),('cs')

insert into grade values
	(1,1,10),
	(2,2,9.5),
	(3,3,9),
	(4,4,8.5)

insert into comment values
	('opened',1,1),
	('resolved',2,2),
	('opened',3,3),
	('resolved',4,4)

go
create or alter procedure gradeStudent
	@idStud int,
	@idTask int,
	@grade float,
	@comment varchar(64)
as
begin
	declare @nr int
	set @nr = 0
	select @nr = count(*) from
		grade where idStud = @idStud and idTask = @idTask

	if @nr = 0
		begin
			insert into grade values (@idStud, @idTask, @grade)
			insert into comment values (@comment, @idStud, @idTask)
		end
	else
		begin
			update grade
			set nota = @grade
			where idStud = @idStud and idTask = @idTask

			update comment
			set stat = @comment
			where idStud = @idStud and idTask = @idTask
		end

end
go

select * from grade;
select * from comment; 


exec gradeStudent 1, 1, 5, 'resolved'

go
create or alter view students 
as
	select s.name
	from student s inner join grade g on s.idStud =  g.idStud
				   inner join task t  on  g.idTask = t.idTask
				   inner join comment c on (c.idStud = g.idStud and c.idTask = g.idTask)
	where c.stat = 'opened'


select * from students



create or alter view orderedView
as
	select top 99.99 percent * 
	from anotherView
	order by nume desc;
go




select countTasks.taskName, countTasks.taskCnt, sumGrades.grSum, sumGrades.grSum/countTasks.taskCnt as averageGrade
from 
	(
	select s.name as taskName, count(*) as taskCnt
	from student s inner join grade g on s.idStud = g.idStud
				   inner join task t  on g.idTask = t.idTask
	group by s.name
	) countTasks

	inner join 

	(
	select s.name as sumName, sum(g.nota) as grSum
	from student s inner join grade g on s.idStud = g.idStud
	group by s.name
	) sumGrades

	on countTasks.taskName = sumGrades.sumName


create or alter function average()
returns table
as

	



return
	
	declare @taskCount int
	select 

go




create or alter function taskCount()
returns int
as
begin
	declare @cnt int
	select @cnt = count(*) from task

return @cnt
end
go

declare @n int
select @n = [dbo].[taskCount]()
print @n








	





















