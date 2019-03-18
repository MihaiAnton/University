perm([],[]).

perm([E],[E]):-!.

perm([H|T],Rez):-
	perm(T,Rez1),
	addInAll(Rez1,H,Rez).
	

addInAll([],E,[E]).
addInAll([H|T],E,[E,H|T]).
addInAll([H|T],E,[H|Rez]):-
	addInAll(T,E,Rez).
	
subm([],[]).
subm([H|T],R):-
	subm(T,R).
subm([H|T],[H|R]):-
	subm(T,R).
	

	


	
	