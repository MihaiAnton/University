;

(defun plusOne (l)
	(cond 
		((null l) nil)
		(t (cons (+ 1 (car l)) (plusOne (cdr l))))
	)
)