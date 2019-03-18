;ex2.lisp


(defun hasElem (l e)
	(cond 
		;((null l) 0)
		((and (numberp l) (equal l e)) 1)
		((listp l) (apply #'+ (mapcar (lambda (subL) (hasElem subL e)) l)))
		(t 0)
	)
)

(defun remk (l k e lvl)
	(cond
		((null l) nil)
		((equal k lvl) (cons e (cdr l)))
		((> k lvl)
			(cons (car l)
			(mapcar (lambda (sub) (remk sub k e (+ lvl 1))) (cdr l))
			)
		)
	)
)

(defun rm (l k e lvl)
	(cond
		((null l) nil)
		((and (atom (car l)) (= k lvl)) (cons e (rm (cdr l) k e lvl)))
		((atom (car l)) (cons (car l) (rm (cdr l) k e lvl)))
		(t (cons (rm (car l) k e (+ 1 lvl))
							   (rm (cdr l) k e lvl))
		)
	)
)

(defun firstEven (l)
	(cond
		((null l) nil)
		((and (numberp (car l)) (= 0 (mod (car l) 2)))
			t
		)
		((and (numberp (car l)) (= 1 (mod (car l) 2))) 
			nil
		)
		(t (firstEven (cdr l)))
	)
)


(defun countFirstEven (l)
	(apply #'+ (mapcar  (lambda (subL)
							(cond 
								((atom subL) 0)
								((listp subL) 
									(cond
										((firstEven subL) (+ 1 (countFirstEven subL)))
										(t (countFirstEven subL))
									)
								)
							)
						)	l
				)
	)
)

(defun countL (l)
	(cond
		((and (listp l) (firstEven l)) 
			(+ 1 (apply #'+ (mapcar #'countL l)))
		)
		((listp l) (apply #'+ (mapcar #'countL l)))
		(t 0)
	)
)

(defun addOne (l col)
	(cond
		((null l) col)
		((and (numberp (car l)) (evenp (car l)))
			(addOne (cdr l) (append col (cons (+ 1 (car l)) nil)))
		)
		((numberp (car l)) 
			(addOne (cdr l) (append col (cons (car l) nil)))
		)
		((listp (car l))
			(addOne (cdr l) (append col (cons (addOne (car l) nil) nil)))
		)
	)
)

(defun getLvl (l lvl)
	(cond
		((null l) nil)
		((and (evenp lvl) (listp (car l))) 
			(append (getLvl (car l) (+ lvl 1)) (getLvl (cdr l) lvl))
		)
		((evenp lvl) 
			(getLvl (cdr l) lvl)
		)
		((and (oddp lvl) (listp (car l)))
			(append (getLvl (car l) (+ 1 lvl)) (getLvl (cdr l) lvl))
		)
		((and (oddp lvl) (numberp (car l)))
			(cons (car l) (getLvl (cdr l) lvl))
		)
		( (and (oddp lvl) (not (numberp (car l))))
			(getLvl (cdr l) lvl)
		)
	)
)

(defun getMax (l)
	(apply #'max (getLvl l 1))
)

(defun countL (l)
	(cond
		( (and (listp l) (not (equal nil (getLvl l 1))) (evenp (getMax l)))
			(print l)
			(+ 1 (apply #'+ (mapcar #'countL l))) 
		)
		( (listp l)
			(apply #'+ (mapcar #'countL l))
		)
		(t 0)
	)
)


(defun changeK (l lvl k)
	(cond
		((null l) nil)
		((= lvl k) 
			(cond
				((numberp (car l))
					(cons 0 (changeK (cdr l) lvl k))
				)
				(t 
					(cons (car l) (changeK (cdr l) lvl k))
				)
			)
		)
		((> lvl k)
			l
		)
		(t 
			(cond
				((numberp (car l))
					(cons (car l) (changeK (cdr l) lvl k))
				)
				((listp (car l)) 
					(cons (changeK (car l) (+ 1 lvl) k) (changeK (cdr l) lvl k))
				)
				(t 
					(cons (car l) (changeK (cdr l) lvl k))
				)
			)
		)
	)
)












































