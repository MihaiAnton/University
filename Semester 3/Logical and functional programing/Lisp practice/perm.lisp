;
(defun insertPos (l p elem)
	(cond
		((= p 0) (cons elem l))
		(t (cons (car l) (insertPos (cdr l) (- p 1) elem)))
	)
)

(defun insertAll (l elem len)
	(cond
		((= len -1) nil)
		(t (cons (insertPos l len elem) (insertAll l elem (- len 1))))
	)
)

(defun insertInAll (l elem)
	(cond
		((null l) nil)
		(t (append (insertAll (car l) elem (length (car l))) 
				   (insertInAll (cdr l) elem)
			)
		)
	)
)




(defun perm_aux (l len)
	(cond
		((= len 0) (cons l nil))
		(t 
			(insertInAll (perm_aux (cdr l) (- len 1)) (car l))			
		)
	)
)


(defun perm (l)
	(perm_aux l (length l))
)








