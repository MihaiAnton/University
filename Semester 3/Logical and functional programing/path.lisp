;











(defun rmNil (l)
	(cond
		((null l) nil)
		((null (car l)) 
			(rmNil (cdr l))
		)
		(t (cons (car l) (rmNil (cdr l))))
	)
)


(defun path (l dest)
	(cond
		((and (atom l) (equal l dest) )
			(cons dest nil)
		)
		((and 
			(listp l) 
			(not (null (rmNil (mapcar (lambda (subL) (path subL dest)) l))))
		 )
			(cons (car l) (car (rmNil (mapcar (lambda (subL) (path subL dest)) l))))
		)
		(t nil)
	)
)

(defun f(l)
	(cond
		((null l) 0)
		(t
			((lambda (x) 
				(cond
					((> x 2) (+ (car l) (f (cdr l))))
					(t x)
				)
			)(f  (car l))) 
		)
	)
)

(defun addK (l k crt)
	(cond
		((null l) nil)
		((= k crt) 
			(append (list (car l) (car l)) (addK (cdr l) k 1))
		)
		(t 
			(cons (car l) (addK (cdr l) k (+ 1 crt)))
		)
	)
)

(defun addKFinal (l k)
	(addK l k 1)
)
		
		
(defun getAll (l)
	(cond 
		((null l) nil)
		((listp (car l))
			(append (getAll (car l)) (getAll (cdr l)))
		)
		(t 
			(cons (car l) (getAll (cdr l)))
		)
	)
)
		
		















