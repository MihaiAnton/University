;2.
;a) Definiti o functie care selecteaza al n-lea element al unei liste, sau
;NIL, daca nu exista.
;b) Sa se construiasca o functie care verifica daca un atom e membru al unei
;liste nu neaparat liniara.
;c) Sa se construiasca lista tuturor sublistelor unei liste. Prin sublista se
;intelege fie lista insasi, fie un element de pe orice nivel, care este
;lista. Exemplu: (1 2 (3 (4 5) (6 7)) 8 (9 10)) =>
;( (1 2 (3 (4 5) (6 7)) 8 (9 10)) (3 (4 5) (6 7)) (4 5) (6 7) (9 10) ).
;d) Sa se scrie o functie care transforma o lista liniara intr-o multime.


(defun cnt (l)
	(cond
		((null l) 0)
		((atom (car l)) (+ 1 (cnt (cdr l))))
		(t (+ (cnt (car l)) (cnt (cdr l))))
	)
)

(defun a (l n)
	(cond
		((null l) nil)
		((and (atom (car l)) (= n 1)) (car l))
		((listp (car l))
			(cond
				((<= n (cnt (car l))) (a (car l) n))
				(t (a (cdr l) (- n (cnt (car l)))))
			)
		)
		(t (a (cdr l) (- n 1)))
	)
)

(defun findAll (l)
	(cond
		((null l) nil)
		((listp (car l)) (append (c (car l)) (findAll (cdr l))))
		(t (findAll (cdr l)))		
	)
)



(defun c (l)
	(cons l (findAll l))
)


(defun hasElem (l elem)
	(and (not (null l)) (or (= (car l) elem) (hasElem (cdr l) elem) ))
)


(defun makeSet (l)
	(cond
		((null l) nil)
		((not (hasElem (cdr l) (car l))) (cons (car l) (makeSet (cdr l))))
		(t (makeSet (cdr l)))
	)
)

















