
from math import sqrt

class Table:

    def __init__(self,n,table,lineCheck = [],columnCheck = [],squareCheck = [],valueCount = [],freeSquares = []):
        self.__n = n
        self.__table = table
        self.__nextFreePos = self.__findNextFree(0)

        if not lineCheck:
            self.__lineCheck = self.__initLineCheck()
        else:
            self.__lineCheck = lineCheck

        if not columnCheck:
            self.__columnCheck = self.__initColumnCheck()
        else:
            self.__columnCheck = columnCheck

        if not squareCheck:
            self.__squareCheck = self.__initSquareCheck()
        else:
            self.__squareCheck = squareCheck

        if not valueCount:
            self.__valueCount = self.__initValueCount()
        else:
            self.__valueCount = valueCount

        if not freeSquares:
            self.__freeSquares = self.__initFreeSquares()
        else:
            self.__freeSquares = freeSquares

        self.__emptySpaces = len(self.__freeSquares)


    def getFreeSquares(self):
        return self.__freeSquares

    def getValueCount(self,value):
        return self.__valueCount[value]

    def getLineCheck(self):
        return self.__lineCheck

    def getColumnCheck(self):
        return self.__columnCheck

    def getSquareCheck(self):
        return self.__squareCheck

    def getValueFrequencies(self):
        return self.__valueCount


    def getTable(self):
        return self.__table

    def getN(self):
        return self.__n

    def getNextFree(self):
        return self.__nextFreePos

    def getNextLineCol(self):
        nextPos = self.getNextFree()
        return self.getBestAddSquare()
        line = nextPos // self.__n
        col = nextPos % self.__n

        return line,col

    def get(self,line,col):
        return self.__table[line * self.__n + col]

    def validAdd(self,line,col,value):
        cond1 = self.__lineCheck[line] & (1<<value)
        cond2 = self.__columnCheck[col] & (1 << value)
        cond3 = self.__squareCheck[self.__getContainedSquare(line,col)] & (1 << value)
        if cond1 or cond2 or cond3:
            return False
        return True

    def getBestAddSquare(self):
        minPos = self.__n + 1
        lin,col = -1,-1

        for sq in self.__freeSquares:
            posVal = 0
            for val in range(1,self.getN()+1):
                if self.validAdd(sq[0],sq[1],val):
                    posVal += 1
            if posVal > 0 and posVal < minPos:
                minPos = posVal
                lin,col = sq[0],sq[1]

        return lin,col


    def validTable(self):
        targetSum = 0
        for i in range(1,self.__n+1):
            targetSum = targetSum + (1<<i)

        columns = self.__initColumnCheck()
        lines = self.__initLineCheck()

        for item in columns:
            if item != targetSum:
                return False
        for item in lines:
            if item != targetSum:
                return False
        return True

    def add(self,line,col,val):
        #add the value
        listPos = line * self.__n + col
        self.__table[listPos] = val
        self.__nextFreePos = self.__findNextFree(listPos)

        #mark the value
        self.__lineCheck[line] = self.__lineCheck[line] | (1<<val)
        self.__columnCheck[col] = self.__columnCheck[col] | (1<<val)
        self.__squareCheck[self.__getContainedSquare(line,col)] |= (1<<val)
        self.__valueCount[val] += 1


        self.__freeSquares.remove([line,col])
        self.__emptySpaces -= 1

    def __findNextFree(self,crt):
        pos = crt
        while self.__table[pos] > 0:            #searches for the first 0 value
            pos = pos+1
            if pos >= (self.__n * self.__n):
                return self.__n * self.__n
        return pos

    def isFull(self):
        return self.__emptySpaces == 0

    def __initLineCheck(self):
        lines = [0]* self.__n
        for line in range(0,self.__n):         #for each line
            for column in range(0,self.__n):   #for each value on the line
                value = self.get(line,column)
                if value > 0:
                    lines[line] = lines[line] | (1<<value)
        return lines

    def __initColumnCheck(self):
        columns = [0] * self.__n
        for column in range(0, self.__n):      #for each column
            for line in range(0, self.__n):    #for each value on the column
                value = self.get(line, column)
                if value > 0:
                    columns[column] = columns[column] | (1 << value)
        return columns

    def __getContainedSquare(self,line,col):
        squaresOnLine = int(sqrt(self.getN()))
        return (line // squaresOnLine) * squaresOnLine + (col // squaresOnLine)

    def __initSquareCheck(self):
        n = self.getN()
        squares = [0] * n

        for i in range(0,n * n):
            line = i // n
            column = i % n
            value = self.__table[i]
            square = self.__getContainedSquare(line,column)
            if value > 0:
             squares[square] |= (1 << value)

        return squares

    def __initValueCount(self):
        frequency = [0] * (self.getN() + 1)
        for value in self.__table:
            if value > 0:
                frequency[value] += 1
        return frequency

    def __initFreeSquares(self):
        freeSpots = []
        for pos in range(0,self.__n * self.__n):
            if self.__table[pos] == 0:
                freeSpots.append([pos // self.getN(),pos % self.getN()])
        return freeSpots





















