;a: Definiti o functie care intoarce produsul scalar a doi vectori.
(defun pScalar (v1 v2)
	(cond
		((and (null v1) (null v2)) 0)
		((and (atom (car v1)) (atom (car v2))) 
			(+ (* (car v1) (car v2)) (pScalar (cdr v1) (cdr v2))))
	
	)

)

 

;b:Sa se construiasca o functie care intoarce maximul
;  atomilor numerici dintr-o lista, de la orice nivel.

(defun maxNr (l)
	(cond
		((null l) -10000)
		;((and (= (length l) 1) (atom (car l))) (car l))
		;((and (= (length l) 1) (listp (car l))) (maxNr (car l)))
		((listp (car l)) (max (maxNr (car l)) (maxNr (cdr l))))
		((not (numberp (car l))) (maxNr (cdr l)))
		((atom (car l)) (max (car l) (maxNr (cdr l))))
		
		(t (maxNr (cdr l)))
	)
)


;c) Sa se scrie o functie care intoarce lista permutarilor
; unei liste date.

;permutari circulare
(defun push_back (l e)
	(cond
		((equal e NIL) l)
		((atom e) (append l (cons e NIL)))
		((listp e) (append l (cons e NIL)))	
		(t (append l (cons e NIL)))
	)
)


(defun perm (l nr)
	(cond
		((= nr 0) NIL)
		(t (push_back (perm (push_back (cdr l) (car l)) (- nr 1)) l) )
	)
)

;toate permutarile
(defun pushBackAll (l elem)
	(mapcar (lambda(x) (append x (cons elem nil))) l)
)

(defun removeElem (l e)
	(cond
		((null l) nil)
		((= e (car l)) (cdr l))
		(t (append (cons (car l) NIL) (removeElem (cdr l) e)))
	)
)
 
(defun allPerm (l)
	(cond
		((null l) nil)	;the list is empty
		((null (cdr l)) (list (cons (car l) nil)))
		(t  
			(let (result)
				(dolist (element l result) 
					(setq auxresult (pushBackAll (allPerm (removeElem l element)) element))	
					(setq result (append result auxresult))
				)
			 
			)
		)
	)
)



;d) Sa se scrie o functie care intoarce T daca o lista are numar par de
;elemente pe primul nivel si NIL in caz contrar, fara sa se numere
;elementele listei.

(defun switch (p)
	(cond
		((equal p 0) 1)
		((equal p 1) 0)
	)
)

(defun countFirstAux (l parity)
	(cond
		;((equal l NIL) t)
		((and  (equal parity 0) (equal l NIL)) T)
		((and  (equal parity 1) (equal l NIL)) NIL)
		((and (> (length l) 0) (atom (car l))) (countFirstAux (cdr l) (switch parity)))
		((and (> (length l) 0) (listp (car l))) (countFirstAux (cdr l) parity)) 
	)
)

(defun countFirst (l)
	(countFirstAux l 0)
)


;permutari fara functii map
(defun addAtPos (e l p)
	(cond
		((= p 1)  (append (cons e nil) l))
		(t (append (cons (car l) nil) (addAtPos e (cdr l) (- p 1))))
	)
)	


(defun addAllPos (e l len)
	(cond
		((= len 0) nil)
		(t (cons (addAtPos e l len) (addAllPos e l (- len 1))))
	)
)

(defun merge1 (l1 l2) 
	(cond
		((and (null l1) (null l2)) nil)
		((not (null l1)) (cons (car l1) (merge1 (cdr l1) l2)))
		((null l1) (cons (car l2) (merge1 l1 (cdr l2))))
	)
)

(defun addAll (e l)
	(cond
		((= 1 (length l)) (addAllPos e (car l) (+ 1 (length (car l)))))
		(t (merge1 (addAll e (cdr l)) (addAllPos e (car l) (+ (length (car l)) 1)) ))
	)
)

(defun Perm (l)
	(cond
		((null (cdr l)) (cons (cons (car l) nil) nil))
		(t (addAll (car l) (Perm (cdr l))))
	)
)

;(defun test1 (l)
	;(and
		;(= (pScalar (1 2 3) (4 5 6)) 32)
		;(eq (Perm (1 2 3)) ((2 3 1) (2 1 3) (1 2 3) (3 2 1) (3 1 2) (1 3 2)))
	
	;)

;)






















