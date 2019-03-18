;lisp test


(defun path (l r dest)
	(cond
		((null l) nil)
		
		((atom (car l)) 
			(cond
				((equal (car l) dest) (cons dest nil))
				(t (path (cdr l) (car l) dest))
			)
		)
		((listp (car l)) 
			(cond
				((not (null (path (car l) r dest))) (cons r (path (car l) r dest)))
				(t (path (cdr l) r dest))
			)
		)
		(t (path (cdr l) r dest))
	)
)

(defun path2 (l dest)
	(path l (car l) dest)
)

