;

(defun g (l)
	(+ (car l) (cadr l))
)

(defun dubluN (l n crt)
	(cond
		((null l) nil)
		((= crt n) (cons (car l) (cons (car l) (dubluN (cdr l) n 1))))
		(t (cons (car l) (dubluN (cdr l) n (+ 1 crt))))
	)
)



(defun countLevel (l k crt)
	(cond
		((null l) 0)
		((> crt k) 0)
		((and (= k crt) (numberp l)) 1)
		((and (listp l) (< crt k))
			(apply #'+ (mapcar (lambda (subL) (countLevel subL k (+ 1 crt))) l))
		)
		(t 0)
	)
)

(defun getDepth (l)
	(cond
		((null l) 0)
		((atom l) 0)
		((listp l) 
			(+ 1 (apply #'max (mapcar #'getDepth l)))
		)
	)
)

(defun checkListA (l lvl)
	(cond
		((null l) nil)
		((= lvl 0) T)
		((and (> lvl 0) (oddp (countLevel l lvl 0)))
			(checkListA l (- lvl 1))
		)
		(t nil)
	)
)

(defun checkList (l)
	(checkListA l (getDepth l))
)

(defun countL (l)
	(cond
		((and (listp l) (checkList l))
			(+ 1 (apply #'+ (mapcar #'countL l)))
		)
		((listp l)
			(apply #'+ (mapcar #'countL l))
		)
		(t 0)
	)
)




































