%14.
% a) Definiti un predicat care determina predecesorul unui numar reprezentat
% cifra cu cifra intr-o lista. De ex: [1 9 3 6 0 0] => [1 9 3 5 9 9]


%decrement(list(lista cu numarul initial), list(lista cu numarul decrementat))
%		- decrementeaza numerul din lista initiala
%       - model de flux: (i,i),(i,o)
decrement(L,Rez):- dec(L,Rez,0).

%getCF(Elem(cifra din care se scade, Elem(vechiul CF care se scade), Elem(actualul CF)).
%		- seteaza noul carry flag in functie de o cifra si de vechiul carry flag
%		- model de flux: (i,i,i), (i,i,o)
getCF(0, 1, 1):-!.			
getCF(_, _, 0).

%sub1(Elem(numarul din care se scade), Elem(carry flag ul actual), Elem(rezultat))
%		- scade carry flagul din numar
%		- model de flux (i,i,i), (i,i,o)
sub1(Nr, CF, Rez):- Nr > 0,!, Rez is Nr - CF.
sub1(0, 0, 0).
sub1(0, 1, 9).
					 
%dec(list(lista din care scade), list(lista rezultat), Elem(CF setat in urma operatiei)
%		- decrementeaza un numar cu o cifra si seteaza CarryFlag-ul CF
%		- model de flux: (i,i,i), (i,i,o), (i,o,o).
dec([E],[Rez],CF):- E == 0, !, Rez is 9, CF is 1.
dec([E],[Rez],CF):- E > 0, !, Rez is E - 1, CF is 0.					 			  
dec([H|T],[First|Rez],CF):- dec(T,Rez,CF1),!,
							getCF(H, CF1, CF),
							sub1(H, CF1, First).
							
						
%b) Se da o lista eterogena, formata din numere intregi si liste de cifre.
%Pentru fiecare sublista sa se determine predecesorul numarului reprezentat
%cifra cu cifra de lista respectiva. De ex:
%[1, [2, 3], 4, 5, [6, 7, 9], 10, 11, [1, 2, 0], 6] =>
%[1, [2, 2], 4, 5, [6, 7, 8], 10, 11, [1, 1, 9] 6]


%decrementList(List(lista cu elemente si liste), List(lista rezultat))
%		- scade 1 din toate sublistele
%		- model de flux(i,i),(i,o)
decrementList([],[]).
decrementList([H|T],[Dec|Rez]):-is_list(H),
								decrement(H,Dec),!,
								decrementList(T,Rez).
decrementList([H|T],[H|Rez]):-  \+ is_list(H),
								decrementList(T,Rez).
							   


								
								
%Teste
testDecrement():-
	decrement([1,2,3],[1,2,2]),
	decrement([1],[0]),
	decrement([1,0,0,0],[0,9,9,9]).

testDecrementList():-decrementList([1,2],[1,2]),
	decrementList([[1,2]],[[1,1]]).

testAll():-
	testDecrement(),
	testDecrementList().
								
								
								
								
								
								
								
								
								
								
								
