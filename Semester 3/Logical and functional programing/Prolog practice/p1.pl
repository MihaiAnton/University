hasElem(E,[E|_]).

hasElem(E,[_|L]):- hasElem(E,L).