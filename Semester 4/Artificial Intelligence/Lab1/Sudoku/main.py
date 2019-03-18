from Greedy.GreedySolver import GreedySolver
from Greedy.GreedySolver2 import GreedySolver2
from SudokuLogic.Table import Table
from DFS.SudokuSolver import SudokuSolver
from math import sqrt
import time


lst = [5,0,0,9,0,7,4,0,3,
       0,4,0,0,0,0,6,0,7,
       8,0,0,0,0,2,0,1,0,
       0,0,8,3,0,0,0,7,0,
       0,0,0,0,7,0,0,0,0,
       0,3,0,0,0,4,2,0,0,
       0,8,0,2,0,0,0,0,1,
       7,0,3,0,0,0,0,6,0,
       6,0,1,7,0,3,0,0,5]

lst16 = [1,0,0,2,3,4,0,0,12,0,6,0,0,0,7,0,
         0,0,8,0,0,0,7,0,0,3,0,0,9,10,6,11,
         0,12,0,0,10,0,0,1,0,13,0,11,0,0,14,0,
         3,0,0,15,2,0,0,14,0,0,0,9,0,0,12,0,
         13,0,0,0,8,0,0,10,0,12,2,0,1,15,0,0,
         0,11,7,6,0,0,0,16,0,0,0,15,0,0,5,13,
         0,0,0,10,0,5,15,0,0,4,0,8,0,0,11,0,
         16,0,0,5,9,12,0,0,1,0,0,0,0,0,8,0,
         0,2,0,0,0,0,0,13,0,0,12,5,8,0,0,3,
         0,13,0,0,15,0,3,0,0,14,8,0,16,0,0,0,
         5,8,0,0,1,0,0,0,2,0,0,0,13,9,15,0,
         0,0,12,4,0,6,16,0,13,0,0,7,0,0,0,5,
         0,3,0,0,12,0,0,0,6,0,0,4,11,0,0,16,
         0,7,0,0,16,0,5,0,14,0,0,1,0,0,2,0,
         11,1,15,9,0,0,13,0,0,2,0,0,0,14,0,0,
         0,14,0,0,0,11,0,2,0,0,13,3,5,0,0,12]

lst16b = [0,0,0,4,0,6,7,8,9,10,11,12,13,14,15,16,
          5,6,7,8,9,10,11,12,13,14,15,16,1,2,3,4,
          9,10,11,12,13,14,15,16,1,2,3,4,5,6,7,8,
          13,14,15,16,1,2,3,4,5,6,7,8,9,10,11,12,
          0,0,4,5,6,7,8,9,10,11,12,13,14,15,16,1,
          6,7,8,9,10,11,12,13,14,15,16,1,2,3,4,5,
          10,11,12,13,14,15,16,1,2,3,4,5,6,7,8,9,
          14,15,16,1,2,3,4,5,6,7,8,9,10,11,12,13,
          0,4,5,6,7,0,0,10,0,12,13,14,15,16,1,2,
          7,8,9,0,11,12,13,14,15,16,1,2,3,4,5,6,
          11,12,13,14,15,16,1,2,3,4,5,6,7,8,9,10,
          15,16,1,2,3,4,5,6,7,8,9,10,11,12,13,14,
          4,5,6,7,8,9,10,11,12,13,0,15,16,1,2,3,
          8,9,10,11,12,13,14,15,16,1,0,0,4,0,6,7,
          12,13,14,15,16,1,2,3,4,5,6,7,8,9,10,11,
          16,0,2,3,4,5,6,7,8,9,10,11,12,0,14,15]

lst16c = [1,7,0,2,14,8,11,4,16,5,9,13,0,0,0,6,
          11,12,13,8,9,10,0,3,1,2,15,0,0,0,0,14,
          9,14,16,5,6,7,12,13,3,4,10,8,1,0,11,15,
          3,6,10,4,2,15,1,5,7,14,0,0,0,0,13,16,
          10,13,0,1,0,4,7,15,2,8,14,16,9,6,3,12,
          8,9,2,15,0,3,6,16,10,1,13,4,11,0,5,7,
          12,16,11,0,8,0,0,1,0,6,0,0,13,0,4,10,
          7,3,4,6,0,9,0,10,12,11,0,15,2,16,1,8,
          15,1,0,13,4,0,0,0,0,0,2,0,10,5,12,3,
          16,8,14,0,5,0,10,0,0,0,6,9,15,11,2,4,
          2,4,12,10,7,16,8,11,15,0,1,0,0,13,6,9,
          5,11,6,9,3,13,15,2,14,12,4,10,16,7,8,1,
          6,0,9,0,0,5,4,14,11,13,8,1,3,12,0,2,
          4,0,0,11,0,12,13,8,0,15,3,14,7,0,0,5,
          13,0,3,0,1,6,0,0,0,0,0,0,0,0,14,0,
          14,0,8,0,10,0,0,0,4,9,16,2,6,1,15,13]



lst2 = [0,0,3,4,
        3,0,1,2,
        4,0,2,3,
        0,3,0,1]


def readCSV(fileName):
    file = open(fileName, "r")
    lst = []
    n = int(file.readline())
    line = file.readline()
    while line != "":
        numbers = line.split(",")
        for i in range (0,n):
            numbers[i] = int(numbers[i])
        lst += numbers
        line = file.readline()
    file.close()
    return lst

def writeCSV(fileName, lst):
    file = open(fileName,"w")
    file.close()
    file = open(fileName,"a+")
    n = int(sqrt(len(lst)))
    file.write(str(n) + "\n")
    string = ""
    counter = 0
    for item in lst:
        if counter == n:
            counter = 0
            string = string[:-1] + "\n"
            file.write(string)
            string = ""
        string = string + str(item) + ","
        counter += 1
    string = string[:-1] + "\n"
    file.write(string)
    file.close()



"""             Main start           """

lst = readCSV("input.txt");


T = Table(int(sqrt(len(lst))) , lst)
'''
#greedy2
start = time.time()
greedy2 = GreedySolver2()
solution = greedy2.solveSudoku(T)
end = time.time()
print("Greedy2: " + str(solution.validTable()) + " " + str(end - start))

#greedy
start = time.time()
greedy = GreedySolver()
solution = greedy.solveSudoku(T)
end = time.time()
print("Greedy:  " + str(solution.validTable()) + " " + str(end - start))



#dfs
start = time.time()
solver = SudokuSolver()
solution = solver.solveSudoku(T)
end = time.time()
print("DFS:     " + str(solution.validTable()) + " " + str(end - start))


'''
LOGIC = "greedy2"

start = time.time()
if LOGIC == "dfs":
    solver = SudokuSolver()
    solution = solver.solveSudoku(T)

elif LOGIC == "greedy":
    greedy = GreedySolver()
    solution = greedy.solveSudoku(T)

elif LOGIC == "greedy2":
    greedy2 = GreedySolver2()
    solution = greedy2.solveSudoku(T)

end = time.time()


print(solution.validTable())
print(end - start)



writeCSV("output.txt",solution.getTable())
