#12a: Să se determine dacă un anumit element este membru al unei liste
class Nod:
    def __init__(self, e):
        self.e = e
        self.urm = None
	
    
class Lista:
    def __init__(self):
        self.prim = None
	def removeHead(self):
		if self.prim != None:
			self.prim = self.prim.urm
			
	def empty(self):
		return self.prim == None
	def headInfo(Self):
		return self.prim.info()
		
		
def has(l, elem):
	if l.empty():
		return False
	else:
		return l.headInfo() == elem or has(l[1:], elem)
		
def testHas():
	assert has([1,2,3,4,5,6,7,8],8)
	assert has([1,2,3,4,5,6,7,8],1)
	assert has([1,2,3,4,5,6,7,8],4)
	assert has([0,0,0,0,0,0],0)
	assert has([2],2)
	assert not has([1,2,3,4],5)
	assert not has([1,1,1],0)
	assert not has([],1)
	assert not has([2],3)
	print('12a ok')

testHas()

#12b:  Să se substituie în listă toate valorile de pe poziții pare cu o valoare
# e dată

def subst(l, elem):
	if l == [] or l[1:] == []:
		return l
	else:
		return [l[0],elem] + subst(l[2:],elem)
		
		
l = [1,2,3,4,5,6,7,8,9,10]
elem = 100

print(subst(l, elem))
