from SudokuLogic.Table import Table

class GreedySolver:

    """
        Solves a valid sudoku grid using DFS with greedy optimization
            - orders the children in the k-th level so the first child has the highest probability to be a part of the solution
        :param - the sudoku table
    """
    def solveSudoku(self,table):
        stack = [table]

        if table.getNextFree() == (table.getN() * table.getN()):                #if the current is complete
            return table                                                            #return the table

        while len(stack):                                                       #while sudoku grids in the DFS stack
            crtTable = stack.pop()                                              #get the table in the top

            children = []                                                       #init a children list

            line,col = crtTable.getNextLineCol()                                #find the next empty position

            for number in range(1,crtTable.getN() + 1):                         #try each possible value
                if crtTable.validAdd(line,col,number):                          #see if it fits
                   # print("Added " + str(number) + " on " + str(line) + " " + str(col))    #create a copy of the table
                    newTable = Table(crtTable.getN(),crtTable.getTable()[:],crtTable.getLineCheck()[:],crtTable.getColumnCheck()[:],crtTable.getSquareCheck()[:],crtTable.getValueFrequencies())
                    newTable.add(line,col,number)                                           #add the value
                    if newTable.isFull():                                       #check if it is a solution
                        return newTable
                    children.append([number,newTable])                          #add the pair [newTable, valueAdded] to the stack


            #order the child list
            children = sorted(children, key= lambda x:crtTable.getValueCount(x[0])) #sort the children: considering values A and B to be added
                                                                                    #            value A should be add first if a is more
                                                                                    #            frequent in the parent table than B
            for child in children:                                              #add the values
                stack.append(child[1])

        return False                                                            #no solution found