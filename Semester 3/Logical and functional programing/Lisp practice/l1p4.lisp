;4.
;a) Definiti o functie care intoarce suma a doi vectori.
;b) Definiti o functie care obtine dintr-o lista data lista tuturor atomilor
;care apar, pe orice nivel, dar in aceeasi ordine. De exemplu:
;(((A B) C) (D E)) --> (A B C D E)
;c) Sa se scrie o functie care plecand de la o lista data ca argument,
;inverseaza numai secventele continue de atomi. Exemplu:
;(a b c (d (e f) g h i)) ==> (c b a (d (f e) i h g))
;d) Sa se construiasca o functie care intoarce maximul atomilor numerici
;dintr-o lista, de la nivelul superficial.

(defun getInverted (l)
	(cond
		((or (null l) (not (atom (car l)))) nil)
		(t (append (getInverted (cdr l)) (cons (car l) nil)))
	)
)

(defun nextNonAtom (l)
	(cond 
		((null l) nil)
		((atom (car l)) (nextNonAtom (cdr l)))
		(t l)
	)
)



(defun c (l)
	(cond 
		((null l) nil)
		((listp (car l)) (cons (car l) (c (cdr l))))
		(t (append (getInverted l) (c (nextNonAtom l))))
	)
)