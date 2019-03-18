;7.
;a) Sa se scrie o functie care testeaza daca o lista este liniara.
;b) Definiti o functie care substituie prima aparitie a unui element intr-o
;lista data.
;c) Sa se inlocuiasca fiecare sublista a unei liste cu ultimul ei element.
;Prin sublista se intelege element de pe primul nivel, care este lista.
;Exemplu: (a (b c) (d (e (f)))) ==> (a c (e (f))) ==> (a c (f)) ==> (a c
;f)
;(a (b c) (d ((e) f))) ==> (a c ((e) f)) ==> (a c f)
;d) Definiti o functie care interclaseaza fara pastrarea dublurilor doua liste
;liniare sortate.


(defun getLast (l)
	(cond
		((null l) nil)
		((null (cdr l)) (
			cond
				((atom (car l)) (car l))
				(t (getLast (car l)))
		))
		(t (getLast (cdr l)))
	)
)

(defun c (l)
	(mapcar (lambda (elem) (
		cond
			((listp elem) (getLast elem))
			(t elem)
		
	
	)) l)
)

