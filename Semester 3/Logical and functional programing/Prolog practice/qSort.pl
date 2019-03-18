partitiem(_,[],[]).
partitiem(E,[H|T],[H|Rez]):-
    H =< E,
    partitiem(E,T,Rez).
partitiem(E,[H|T],Rez):-
    \+ H =< E,
    partitiem(E,T,Rez).

partitieM(_,[],[]).
partitieM(E,[H|T],[H|Rez]):-
    H > E,
    partitieM(E,T,Rez).
partitieM(E,[H|T],Rez):-
    \+ H > E,
    partitieM(E,T,Rez).

concat([],L,L).
concat([E],[],[E]).
concat([E],L,[E|L]).
concat([H|T],L,[H|Rez]):-
    concat(T,L,Rez).
    


qSort([],[]).
qSort([E],[E]):-!.
qSort([H|T],Rez):-
    partitiem(H,T,Less),
    partitieM(H,T,Greater),
    qSort(Less,Left),
    qSort(Greater,Right),
    Copy = [H|Right],
    concat(Left,Copy,Rez).