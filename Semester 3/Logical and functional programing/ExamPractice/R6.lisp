(defun countN (l lvl crt)
	(print l)
	(cond
		((null l) 0)
		((and (atom (car l)) (= lvl crt))
			(+ 1 (countN (cdr l) lvl crt))
		)
		((listp (car l) );(> lvl crt))
			(+
				(countN (cdr l) lvl crt)
				(countN (car l) lvl (+ 1 crt))
				
			)
		)
		(t (countN (cdr l) lvl crt))
	)
)

(defun countN2 (l lvl crt)
	(cond
		;((null l) 0)
		((and (atom l) (= lvl crt))
				1
		)
		((listp l)
			(apply #'+ (mapcar (lambda (subL) (countN2 subL lvl (+ 1 crt))) l))
		)
		(t 0)
	)
)

(defun getLin (l)
	(cond
		((null l) nil)
		((and (atom (car l)) (not (numberp (car l))))
			(getLin (cdr l))
		)
		((atom (car l))
			(append (list (car l)) (getLin (cdr l)))
		)
		((listp (car l))
			(append
				(getLin (car l))
				(getLin (cdr l))
			)
		)
	)
)

(defun checkL (l)
	(and
		(not (null (getLin l)))
		(oddp (car (getLin l)))
	)
)

(defun countL (l)
	(cond
		((and (listp l) (checkL l))
			(+ 1 (apply #'+ (mapcar #'countL l)))
		)
		((listp l)
			(apply #'+ (mapcar #'countL l))
		)
		(t 0)
	)
)




























