from SudokuLogic.Table import Table

class GreedySolver2:

    """
        Solves a valid sudoku grid using DFS with greedy optimisation
            - orders the children in the k-th level so the first child has the highest probability to be a part of the solution
            - finds the best insert position for the current sudoku grid so the first has higher chances to provide a solution
        :param - the sudoku table
    """
    def solveSudoku(self, table):
        stack = [table]                                             #create the DFS stack

        while len(stack):                                           #while we have sudoku grids to process
            crtTable = stack.pop()                                  #get the current sudou grid
            if crtTable.isFull():                                   #if it is a solution return it
                return table

            line,col = crtTable.getBestAddSquare()                  #get the best square: considering A and B free spaces, A should be
                                                                    # considered first if it has a lower number of possible values than B
            if line == -1 and col == -1:
                continue

            children = []                                           #init the children list
            for number in range(1,crtTable.getN() + 1):             #for each value possible
                if crtTable.validAdd(line,col,number):              #if it is a valid addition
                   # print("Added " + str(number) + " on " + str(line) + " " + str(col))
                                                                    #copy the table for the child
                    newTable = Table(crtTable.getN(),crtTable.getTable()[:],crtTable.getLineCheck()[:],crtTable.getColumnCheck()[:],crtTable.getSquareCheck()[:],crtTable.getValueFrequencies()[:],crtTable.getFreeSquares()[:])
                    newTable.add(line,col,number)                   #add the value
                    if newTable.isFull():                           #if a solution was created return it
                        return newTable
                    children.append([number,newTable])              #add the child to the list


            children = sorted(children, key=lambda x: crtTable.getValueCount(x[0])) #sort the children with the logic from GreedySolver
            for child in children:                                  #add each child to the DFS stack
                stack.append(child[1])

        return None                                                 #no solution found