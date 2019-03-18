;

(defun subm (l)
	(cond
		((null l) (list nil))
		(t
			((lambda (x) 
			(append
				x
				(insertInAll (car l) x)
			
			)
			) (subm (cdr l)))
		)
	)
)

(defun insertInAll (elem l)
	(cond 
		((null l) nil)
		(t
			(mapcar (lambda (subL) (cons elem subL)) l)
		)
	)
)

(defun inc(x) (+ x 1))