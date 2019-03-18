use CeainarieLab1
go

insert into tipuri(tId, nume, origine) values (1, 'Plante','India');
insert into tipuri values (2, 'Negru','Anglia');



insert into arome(denumire, intensitate) values 
	('Musetel', 3),
	('Vanilie',10);

insert into ceaiuri values
	('Fructe de padure' ,100, 2),
	('Sunatoare', 80, 2);

insert into comenzi values
	(1,2,0),
	(2,1,7);

--afisare ceaiuri din fiecare tip cu gramajul > 50 si origine india
select c.denumire, c.gramaj, t.nume 
from ceaiuri as c, tipuri as t
where gramaj > 50 and origine = 'India' and t.tId = c.tId

select denumire , gramaj, nume
from ceaiuri c inner join tipuri t on t.tId = c.tId
where gramaj > 50 and origine = 'India';

select denumire , gramaj, nume
from ceaiuri c inner join tipuri t on t.tId = c.tId;

select denumire , gramaj, nume
from ceaiuri c left outer join tipuri t on t.tId = c.tId;

select denumire , gramaj, nume
from ceaiuri c right outer join tipuri t on t.tId = c.tId;

select denumire , gramaj, nume
from ceaiuri c full join tipuri t on t.tId = c.tId;

--afisare ceaiuri cu arome(sortate dupa denumirea ceaiului) avand litera A in interiorul aromei
select c.denumire, a.denumire
from ceaiuri c, arome a
where a.denumire like '%a%'
order by c.denumire desc;

select distinct c.denumire, a.denumire
from ceaiuri c inner join comenzi co on c.ceaId = co.cId
inner join arome a on co.aId = a.aId
where a.denumire like '%_A_%'
order by c.denumire desc;

--sa se afiseze pentru fiecare tip de ceai suma gramajelor
select t.tId as tip, sum(c.gramaj) as 'suma gramaj'
from ceaiuri c inner join tipuri t on c.tId = t.tId
group by t.tId;

select t.tId as tip, sum(c.gramaj) as s
from ceaiuri c inner join tipuri t on c.tId = t.tId
group by t.tId
having sum(c.gramaj) > 10;















select * from comenzi;

