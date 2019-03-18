;a) Sa se insereze intr-o lista liniara un atom a dat dupa al 2-lea, al 4-lea,
;al 6-lea,....element.
;b) Definiti o functie care obtine dintr-o lista data lista tuturor atomilor
;care apar, pe orice nivel, dar in ordine inversa. De exemplu: (((A B) C)
;(D E)) --> (E D C B A)
;c) Definiti o functie care intoarce cel mai mare divizor comun al numerelor
;dintr-o lista neliniara.
;d) Sa se scrie o functie care determina numarul de aparitii ale unui atom dat
;intr-o lista neliniara.


(defun a (l elem)
	(cond 
		((null l) nil)
		((not (null (cdr l))) 
			(append
				(list 
					(car l)
					(car (cdr l))
					elem
				)
				(a (cddr l) elem)
			)
		)
		(t l)				
	)
)

(defun b (l)
	(cond 
		((null l) nil)
		((atom (car l)) (append (b (cdr l)) (cons (car l) nil)))
		((listp (car l)) (append (b (cdr l)) (b (car l))))
	)
)


;	12 8 4
;	8  4 0
;	   4


(defun cmmdc_aux (a b r)
	(cond 
		((= r 0) b)
		(t (cmmdc_aux b r (mod b r)))
	)
)

(defun cmmdc (a b)
	(cmmdc_aux a b (mod a b))
)

(defun c (l)
	(cond 
		((null (cdr l))	
			(cond
				((numberp (car l)) (car l))
				(t (c (car l)))
			)
		)
		(t 
			(cond 
				((numberp (car l)) (cmmdc (car l) (c (cdr l))))
				(t (cmmdc (c (car l)) (c (cdr l))))
			)
		)
	)
)

(defun d (l elem)
	(cond 
		((null l) 0)
		(t
			(cond
				((and (atom (car l)) (equal elem (car l)))
					(+ 1 (d (cdr l) elem)))
				((listp (car l)) 
					(+ (d (car l) elem) (d (cdr l) elem)))
				(t 
					(d (cdr l) elem))
			)
		)
	)
)












