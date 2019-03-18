;a) Definiti o functie care intoarce produsul a doi vectori.
;b) Sa se construiasca o functie care intoarce adancimea unei liste.
;c) Definiti o functie care sorteaza fara pastrarea dublurilor o lista
;liniara.
;d) Sa se scrie o functie care intoarce intersectia a doua multimi.

(defun b (l)
	(cond
		((null l) 0)
		(t 
			(cond
				((listp (car l)) (max (+ 1 (b (car l))) (b (cdr l))))
				(t (b (cdr l)))
			)
		)
	)
)

(defun firstHalf (l len)
	(cond
		((= len 0) nil)
		(t (cons (car l) (firstHalf (cdr l) (- len 1))))
	)
)

(defun secondHalf (l len)
	(cond
		((or (null l) (= len 0)) l)
		(t (secondHalf (cdr l) (- len 1)))
	)
)

(defun mrg (a b)
	(cond 
		((and (null a) (null b)) nil)
		((null a) b)
		((null b) a)
		(t 
			(cond
				((< (car a) (car b)) (cons (car a) (mrg (cdr a) b)))
				((> (car a) (car b)) (cons (car b) (mrg a (cdr b))))
				(t (cons (car a) (mrg (cdr a) (cdr b))))
			)
		)
	)
)


(defun c (l)
	(cond 
		((null (cdr l)) l)
		(t
			(mrg (c (firstHalf l (/ (- (length l) (mod (length l) 2)) 2))) 
				 (c (secondHalf l (/ (- (length l) (mod (length l) 2)) 2))))
		)
	)
)





