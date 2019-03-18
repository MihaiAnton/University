;12. Definiti o functie care inlocuieste un nod cu altul intr-un arbore n-ar
;reprezentat sub forma (radacina lista_noduri_subarb1...lista_noduri_subarbn)
;Ex: arborelele este (a (b (c)) (d) (e (f))) si nodul 'b se inlocuieste cu
;nodul 'g => arborele (a (g (c)) (d) (e (f)))



(defun changeSubTrees (tree node new)
	(mapcar (lambda (subTree) (chNode subTree node new)) (cdr tree))
)



(defun chNode (tree node new)
	(cond
		((null tree) 
			nil)
		((equal (car tree) node) 
			(append (cons new nil) (changeSubTrees tree node new)))
		(t 
			(append (cons (car tree) nil) (changeSubTrees tree node new)))
	)
)


