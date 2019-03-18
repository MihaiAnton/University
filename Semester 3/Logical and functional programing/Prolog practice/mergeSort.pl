merge([],A,A):-!.
merge(A,[],A):-!.

merge([H1|T1],[H2|T2],[H1|Rez]):-
    H1 =< H2, !,
    merge(T1,[H2|T2],Rez).

merge([H1|T1],[H2|T2], [H2|Rez]):-
    merge([H1|T1],T2,Rez).

concat([],L,L).
concat([E],[],[E]).
concat([E],L,[E|L]).
concat([H|T],L,[H|Rez]):-
    concat(T,L,Rez).


split_aux(L,[],L,0):-!.
split_aux([H|T], [H|Left], Right, Lg):-
    Lg1 is Lg - 1,
    split_aux(T,Left,Right,Lg1).

split(L,Left,Right):-
    length(L,Lg),
    Lg2 is div(Lg,2),
    split_aux(L,Left,Right,Lg2).

mergeSort([],[]):-!.
mergeSort([E],[E]):-!.
mergeSort(L,Rez):-
    split(L,Left,Right),
    mergeSort(Left,LS),
    mergeSort(Right,RS),
    merge(LS,RS,Rez).
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             