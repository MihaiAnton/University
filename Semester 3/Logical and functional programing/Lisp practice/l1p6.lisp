;6.
;a) Sa se scrie de doua ori elementul de pe pozitia a n-a a unei liste
;liniare. De exemplu, pentru (10 20 30 40 50) si n=3 se va produce (10 20
;30 30 40 50).
;b) Sa se scrie o functie care realizeaza o lista de asociere cu cele doua
;liste pe care le primeste. De ex: (A B C) (X Y Z) --> ((A.X) (B.Y)
;(C.Z)).
;c) Sa se determine numarul tuturor sublistelor unei liste date, pe orice
;nivel. Prin sublista se intelege fie lista insasi, fie un element de pe
;orice nivel, care este lista. Exemplu: (1 2 (3 (4 5) (6 7)) 8 (9 10)) =>
;5 (lista insasi, (3 ...), (4 5), (6 7), (9 10)).
;d) Sa se construiasca o functie care intoarce numarul atomilor dintr-o lista,
;de la nivel superficial.


(defun b (l1 l2)
	(cond
		((and (null l1) (null l2)) nil)
	    (t 
			(cons 
				(cons (car l1) (car l2))
				(b (cdr l1) (cdr l2))
			)
		)
	)
)

(defun explore (l)
	(cond 
		((null l) 0)
		((atom (car l)) (explore (cdr l)))
		((listp (car l)) (+ (c (car l)) (explore (cdr l))))
	)
)

(defun c (l)
	(+ 1 (explore l))
)


