%		Tema Prolog1, pb 13


% a.) Sa se scrie un predicat care transforma o lista intr-o multime, in */
%     ordinea ultimei aparitii. Exemplu: [1,2,3,1,2] e transformat in [3,1,2].

%hasElem(List ,Elem), model de flux (i,i), (i,o)
hasElem([Head|_],Head).

hasElem([Head|Rest],Elem):- hasElem(Rest,Elem).

%makeSet(List, List(Result)), model de flux (i,i), (i,o)
makeSet([],[]).
makeSet([Head|Rest],Set):- hasElem(Rest,Head) ,
						   makeSet(Rest, Set),!.
makeSet([Head|Rest],[Head|Set]):- \+ hasElem(Rest,Head) ,
								makeSet(Rest,Set).

true().

testHasElem():-
	hasElem([1,2,3,4],1),
	hasElem([1],1),
	\+ hasElem([1,2,3],0),
	\+ hasElem([],0).
	





% b. Sa se calculeze cel mai mare divizor comun al elementelor unei liste.

%modulo (Int(A), Int(B), Int(A%B)), model de flux: (i,i,i), (i,i,o)
modulo(A,B,Mod):- Mod is (A mod B).

%cmmdc(Int(A), Int(B), Int(cmmdc(A,B)), model de flux: (i,i,i), (i,i,o)
cmmdc(A,B,A):- B = 0, !.
cmmdc(A,B,B):- A = 0, !.
cmmdc(A,B,Div):- Mod is (A mod B),
				 cmmdcWithRest(A,B,Mod,Div).

%cmmdcWithRest(Int(A),Int(B),Int(A%B),Int(cmmdc(A,B))) ,  model de flux: (i,i,i,i), (i,i,i,o)
cmmdcWithRest(A,B,R,Rez):- R = 0,Rez is B, !.
cmmdcWithRest(A,B,R,Rez):- R > 0,
							Mod is (B mod R),
					   cmmdcWithRest(B,R,Mod,Rez).


%cmmdcList(List , Int(Result)), model de flux (i,i), (i,o)
cmmdcList([Elem],Elem).
cmmdcList([First|Rest],Div):-   cmmdcList(Rest,Div1),
								cmmdc(First,Div1,Div).


testCmmdc():-
	cmmdcList([5],5),
	cmmdcList([18,12],6),
	cmmdcList([123,345,567,1],A),
	A =:= 1,
	\+ cmmdcList([123,345,567,1],2).

						
						
						
						
						
						
						
						
						
						
						
						
						
						
						