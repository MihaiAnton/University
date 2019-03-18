;10.
;a) Sa se construiasca o functie care intoarce produsul atomilor numerici
;dintr-o lista, de la nivelul superficial.
;b) Sa se scrie o functie care, primind o lista, intoarce multimea tuturor
;perechilor din lista. De exemplu: (a b c d) --> ((a b) (a c) (a d)(b c) (b
;d) (c d))
;c) Sa se determine rezultatul unei expresii aritmetice memorate in preordine
;pe o stiva. Exemple:
;(+ 1 3) ==> 4 (1 + 3)
;(+ * 2 4 3) ==> 11 ((2 * 4) + 3)
;(+ * 2 4 - 5 * 2 2) ==> 9 ((2 * 4) + (5 - (2 * 2))
;d) Definiti o functie care, dintr-o lista de atomi, produce o lista de
;perechi (atom n), unde atom apare in lista initiala de n ori. De ex:
;(A B A B A C A) --> ((A 4) (B 2) (C 1)).

(defun addHead (elem l)
	(mapcar (lambda (lst) (list elem lst)) l)
)

(defun b (l)
	(cond
		((null l) nil)
		(t (append
			(addHead (car l) (cdr l))
			(b (cdr l))
		))
	)
)

(defun c (l)
	(cond
		((null l) 0)
		((numberp (car l)) (car l))
		(t ())
		
	)
)
(defun test (l)
	(setq sign (car l))
	(apply sign (cadr l) (caddr l))
)













