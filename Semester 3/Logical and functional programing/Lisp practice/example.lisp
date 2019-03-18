(defun tail (l) (cdr l))
(defun first (l) (car l))
(defun s(l)
  (cond
     ((null l) 0)
     ((atom (car l)) (+ (car l) (s (cdr l))))
     (t (s (cdr l)))
  )
)
