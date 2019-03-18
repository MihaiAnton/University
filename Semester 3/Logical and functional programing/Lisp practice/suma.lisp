;calculeaza suma adoua numere in forma de lista


(defun addDigit (d number)
	(cond
		((null number) (cons d nil))
		((> (car number) 9) (
			append
				(cons (+ d 1) nil)
				(cons (mod (car number) 10) nil)
				(cdr number)
			
		))
		(t (
			append
				(cons d nil)
				number
			
		))
	)
)



(defun suma_aux (a b)
	(cond 
		((and (null a) (null b)) nil)
		(t 
			(addDigit (+ (car a) (car b)) (suma_aux (cdr a) (cdr b)))
		)
	)
)

(defun suma (a b)
	(setq l (suma_aux a b))
	(cond
		((> (car l) 9) 
			(append 
					(cons 1 nil)
					(cons (mod (car l) 10) nil)
					(cdr l)
			)
		)
		(t l)
	)
)








