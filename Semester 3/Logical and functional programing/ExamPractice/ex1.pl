
delN([],_,_,[]).

delN([H|T],N,N1,R):-
	N =:= N1,!,
	NextP is N+1,
	NextD is N1*2,
	delN(T,NextP,NextD,R).

delN([H|T],P,D,[H|R]):-
	P1 is P+1,
	delN(T,P1,D,R).

	

	
subm([],[]).
subm([H|T],[H|R]):-
	subm(T,R).
subm([_|T],R):-
	subm(T,R).

verif(L):-
	length(L,Len),
	0 =:= mod(Len,2).
	
evenPerm(L,R):-
	subm(L,R),
	verif(R).
