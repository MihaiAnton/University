;8.
;a) Sa se elimine elementul de pe pozitia a n-a a unei liste liniare.
;b) Definiti o functie care determina succesorul unui numar reprezentat cifra
;cu cifra intr-o lista. De ex: (1 9 3 5 9 9) --> (1 9 3 6 0 0)
;c) Sa se construiasca multimea atomilor unei liste.Exemplu: (1 (2 (1 3 (2 4)
;3) 1) (1 4)) ==> (1 2 3 4)
;d) Sa se scrie o functie care testeaza daca o lista liniara este o multime.


(defun plus_aux (l)
	(cond
		((null (cdr l)) (cons (+ 1 (car l)) nil))
		(t 
			(setq restSum (plus_aux (cdr l)))
			(cond
				((> (car restSum) 9) (
					append
						(cons (+ 1 (car l)) nil)
						(cons (mod (car restSum) 10) nil)
						(cdr restSum)
				))
				(t (
					append 
						(cons (car l) nil)
						restSum
				))
			)
		)
	)
)

(defun plus (l)
	(setq aux (plus_aux l))
	(cond
		((> (car aux) 9) (
			append
				(cons 1 nil)
				(cons (mod (car aux) 10) nil)
				(cdr aux)
		))
		(t aux)
	)
)
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	