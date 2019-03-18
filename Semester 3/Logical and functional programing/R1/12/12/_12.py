#12a: Să se determine dacă un anumit element este membru al unei liste
class Nod:
    def __init__(self, e):
        self.e = e
        self.urm = None
    def setNext(self, next):
        self.urm = next

    
class Lista:
    def __init__(self, l=[]):
        self.prim = None
        for i in range(len(l)-1,-1,-1):
            node = Nod(l[i])
            node.setNext(self.prim)
            self.prim = node


    def removeHead(self):
        aux = Lista()
        if self.prim != None:
            aux.prim = self.prim.urm
        return aux

    def oneElem(self):
        return self.prim.urm == None

    def empty(self):
        return self.prim == None
    def headInfo(self):
        return self.prim.e
    def push_front(self, info):
        node = Nod(info)
        node.setNext(self.prim)
        self.prim = node
        return self
    def createList(self):
        l = []
        node = self.prim
        while node != None:
            l = l+ [node.e]
            node = node.urm
        return l


l = Lista([1,4,7,8,9])

def has(l, elem):
	if l.empty():
		return False
	else:
		return l.headInfo() == elem or has(l.removeHead(), elem)


print(has(l,4))
print(has(l,9))
print(has(l,10))

#12b:  Să se substituie în listă toate valorile de pe poziții pare cu o valoare
# e dată

def subst(l, elem):
	if l.empty() or l.oneElem():
		return l
	else:
		return subst(l.removeHead().removeHead(), elem).push_front(elem).push_front(l.headInfo())
		
print("")

l = Lista([1,2,3,4,5,6,7])
print(subst(l, 100).createList())

l = Lista([1,2])
print(subst(l, 100).createList())

l = Lista([1])
print(subst(l, 100).createList())
