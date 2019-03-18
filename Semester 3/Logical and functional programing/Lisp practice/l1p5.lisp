;a) Definiti o functie care interclaseaza cu pastrarea dublurilor doua liste
;liniare sortate.
;b) Definiti o functie care substituie un element E prin elementele unei liste
;L1 la toate nivelurile unei liste date L.
;c) Definiti o functie care determina suma a doua numere in reprezentare de
;lista si calculeaza numarul zecimal corespunzator sumei.
;d) Definiti o functie care intoarce cel mai mare divizor comun al numerelor
;dintr-o lista liniara.

(defun mrg (a b)
	(cond 
		((and (null a) (null b)) nil)
		((null a) b)
		((null b) a)
		(t 
			(cond
				((< (car a) (car b)) (cons (car a) (mrg (cdr a) b)))
				((>= (car a) (car b)) (cons (car b) (mrg a (cdr b))))	
			)
		)
	)
)