sum2(A,B,R2,1):-
    R is A + B,
    R > 9,!,
    R2 is R - 10.
sum2(A,B,R,0):-
    R is A + B.

sum_aux([H1],[H2],[Rez],CF):-
    sum2(H1,H2,Rez,CF).
sum_aux([H1|T1],[H2|T2],[H|Rez],CF):-
    sum_aux(T1,T2,Rez,CF1),
    sum2(H1,H2,H3,CF2),
    sum2(H3,CF1,H,CF3),
    CF is CF2 + CF3.


sum(L1,L2,Rez):-
    sum_aux(L1,L2,Rez,CF),
    CF is 0,!.
sum(L1,L2,[1|Rez]):-
    sum_aux(L1,L2,Rez,CF),
    CF is 1,!.
    