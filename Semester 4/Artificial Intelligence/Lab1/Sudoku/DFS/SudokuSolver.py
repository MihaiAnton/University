from SudokuLogic.Table import Table

class SudokuSolver:


    """
        Solves a valid sudoku grid using DFS(backtracking)
        :param - the sudoku table
    """
    def solveSudoku(self, table):

        treeStack = [table]                                     #the DFS stack

        while len(treeStack) > 0:                               #still a sudoku table to try
            crtTable = treeStack.pop(0)                         #get the stack's top
            line,col = crtTable.getNextLineCol()                #get the next position to add
            #line,col = crtTable.getBestAddSquare()
            print(str(line) + " " + str(col))
            if line >= table.getN() or col >= table.getN():     #if the sudoku is complete
                return crtTable                                 #return the solution
            for val in range(1, crtTable.getN()+1):             #for each possible value [1,n]

                if crtTable.validAdd(line,col,val):             #add the value and continue adding
                    #print("Added " + str(val) + " on " + str(line) + " " + str(col))
                    nextTable = Table(crtTable.getN(),crtTable.getTable()[:])   #duplicate the table
                    nextTable.add(line,col,val)                 #add the element
                    treeStack.insert(0,nextTable)               #add the new table to the DFS stack
                    if treeStack[0].isFull():                   #if the table lately created is full(complete)
                        return treeStack[0]                         #return the solution


        return False                                            #could not find a solution