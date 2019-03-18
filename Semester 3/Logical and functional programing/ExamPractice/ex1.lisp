;

(defun remN (l p n)
	(cond
		((null l) nil)
		((= p n ) (remN (cdr l) 1 n))
		(t (cons (car l) (remN (cdr l) (+ 1 p) n)))
	)
)


(defun countSubL (l)
	(cond
		
	)
)

(defun countNonNAtoms (l level)
	(cond
		((null l) 0)
		((and (atom (car l))
			  (not (numberp (car l)))
			  (= (mod level 2) 0)
		 ) 
		 (+ 1 (countNonNAtoms (cdr l) level))
		)
		((listp (car l)) (+ (countNonNAtoms (car l) (+ level 1)) 
							(countNonNAtoms (cdr l) level)))
		(t (countNonNAtoms (cdr l) level))
	)
)

(defun listCond (l)
	(= 1 (mod (countNonNAtoms l 1) 2))
)

(defun rmNIL (l)
	(cond
		((null l) nil)
		((null (car l)) (rmNIL (cdr l)))
		(t (cons (car l) (rmNIL (cdr l))))
	)
)

(defun findAllSublists (l)
	;(print l)
	(cond 
		((or (null l) (atom l)) nil)
		((listCond l) (cons l (rmNIL(
			mapcar (lambda (subL) (findAllSublists subL)) l) 
		)))
		(t (rmNIL(
			mapcar (lambda (subL) (findAllSublists subL)) l) 
		))
	)
)

(defun cnt (l)
	(length (findAllSublists l))
)










