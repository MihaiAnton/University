;

(defmacro sum (l)
	(cond
		((null l) 0)
		(t (+ (car l) (sum (cdr l))))
	)
)