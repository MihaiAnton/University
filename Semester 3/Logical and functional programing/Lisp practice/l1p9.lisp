;9.
;a) Sa se scrie o functie care intoarce diferenta a doua multimi.
;b) Definiti o functie care inverseaza o lista impreuna cu toate sublistele
;sale de pe orice nivel.
;c) Dandu-se o lista, sa se construiasca lista primelor elemente ale tuturor
;elementelor lista ce au un numar impar de elemente la nivel superficial.
;Exemplu: (1 2 (3 (4 5) (6 7)) 8 (9 10 11)) => (1 3 9).
;d) Sa se construiasca o functie care intoarce suma atomilor numerici dintr-o
;lista, de la nivelul superficial.

(defun b (l)
	(cond
		((null l) nil)
		((atom (car l)) (append (b (cdr l)) (cons (car l) nil)))
		((listp (car l)) (append (b (cdr l)) (cons (b (car l)) nil)))
	)
)

(defun explore1 (l)
	(cond
		((null l) nil)
		((atom (car l)) (explore1 (cdr l)))
		(t (append (c (car l)) (explore1 (cdr l))))
	)
)

(defun countLevel0 (l)
	(cond
		((null l) 0)
		((atom (car l)) (+ 1 (countLevel0 (cdr l))))
		(t (countLevel0 (cdr l)))
	)
)

(defun c (l)
	(cond
		((null l) nil)
		((= (mod (countLevel0 l) 2) 1) (cons (car l) (explore1 l)))
		(t (explore1 l))
	)
)