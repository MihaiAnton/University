even(X):- X mod 2 =:= 0.
odd(X):- X mod 2 =:= 1.

modul(A,B,M):- M is (A mod B ).

%Suma elemente pare din lista.

sumaPare([],0):-!.
sumaPare([H|T],S):-even(H),
				   sumaPare(T,S1),
				   S is S1+H.
sumaPare([_|T],S):-sumaPare(T,S).

oddELems([],[]):-!.



taller(mihai,criss).
taller(criss,raluca).


taller(X,Y):-taller(X,Z),
			 taller(Z,Y).
			
p( a, b, [a|b]).





splitOddEven([],[],[]):-!.

splitOddEven([X|Rest],[X|Odd],Even):- odd(X),
	splitOddEven(Rest,Odd,Even).

splitOddEven([X|Rest],Odd,[X|Even]):- even(X),
	splitOddEven(Rest,Odd,Even).


deleteElem([],E,[]).
deleteElem([X|Rest],E,L):- X = E,
	deleteElem(Rest,E,L).
deleteElem([X|Rest],E,[X|L]):- 
	deleteElem(Rest,E,L).
	
replace([],R,E,[]).
replace([X|Rest],R,E,[E|List]):- X = R, replace(Rest,R,E,List).
replace([X|Rest],R,E,[X|List]):- replace(Rest,R,E,List).

isPrim(2).
isPrim(3).







   
				