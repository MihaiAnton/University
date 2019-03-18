%test

lg([],0).
lg([_|T],L):-
	lg(T,L1),
	L is L1+1 .
	
delPos([_|T],1,T).
delPos([H|T],Pos,[H|Rest]):-
	Pos > 1,
	Pos1 is Pos-1,
	delPos(T,Pos1,Rest).

del([],_,_,[]):-!.
del(L,Pos,It,L):-
	lg(L,Lg),
	NewIt is It - 1,
	NewL is Pos - NewIt,
	NewL > Lg,!.
del(L,Pos,It,Rez):-
	NewL is Pos - It + 1,
	NextIt is It+1,
	NextPos is Pos+Pos+1,
	delPos(L,NewL,Rez1),
	del(Rez1,NextPos,NextIt,Rez).
	
main(L,Rez):-
	del(L,1,1,Rez).
	
test():-
	main([1,2,3,4,5,6,7,8,9,10,11],[2,4,5,6,8,9,10,11]),
	main([5,3,8,9,4,0,1,17,20,11,12,3,8,9,20,21],[3,9,4,0,17,20,11,12,3,8,9,21]),
	!.
	
 