;(nod nr-subarbori lista-subarbore-1 lista-subarbore-2 ...) (1)
;(nod (lista-subarbore-1) (lista-subarbore-2)) (2)
;
;4. Sa se converteasca un arbore de tipul (2) 
;   la un arbore de tipul (1).






(defun convertSubtrees (l)
	(cond
		((null l) nil)
		(t
			(append 
				(type1Tree (car l)) 
				(convertSubtrees (cdr l))
			)	
		)
	)
)

(defun type1Tree (tree)
	(cond
		((null tree) nil)
		((null (cdr tree)) (list (car tree) 0))
		(t 
		   (append 
				(list (car tree) (- (length tree) 1)) 
				(convertSubtrees (cdr tree))
			)
		)
	)
)











