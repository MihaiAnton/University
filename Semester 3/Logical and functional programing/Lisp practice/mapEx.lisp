;

(defun sumall (l)
	(mapcar (lambda (subL) (sum subL)) l)
)

(defun sum (l)
	(cond 
		((null l) 0)
		(t (+ (car l) (sum (cdr l))))
	)
)