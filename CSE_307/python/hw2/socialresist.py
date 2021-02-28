#Constants
import sys
from numpy import *
MAX_NODES = 20
MAX_QUERIES = 10
#initialize matrix
Adjacent = [0] * (MAX_NODES +1)
for i in range(0, MAX_NODES+1):
	Adjacent[i] = [0]*(MAX_NODES+MAX_QUERIES)
query1 = [0] * MAX_QUERIES
query2 = [0] * MAX_QUERIES

#print("i want to have "+ str(Adjacent[0]))

def AdjacentInit(nnodes, nqueries):
    for i in range(0, nnodes+1):
        for j in range(0, nnodes+nqueries):
            Adjacent[i][j] = 0.0

    for i in range(0, nnodes):
        Adjacent[nnodes][i] = 1.0
	
def ScanEdgeData(nnodes, pb):
	#print(pb)b   
	node = 0
	count = 0
	pblist = pb
	i=0
	
	#print(pb)
	#print(pblist)
	#print("Aokiji " + str(pb[i].isdigit()))
	while(pb[i] == ' '):
		i+=1
		#print("Akainu")
		if(pb[i]==' ' or pb[i]==''):
			break
	
	#print("pb[i] before node " + str(pb[i]))
	while(pb[i].isdigit()):
		node = (node*10) + int(pb[i])
		#print("node " + str(node))
		#print("Akainu")
		i+=1
	if((pb[i]==0)):
		#print('rert')
		return -2
	while(pb[i] == ' '):
		i+=1
		#print("Akainu")
		if(pb[i]==' ' or pb[i]==''):
			break
	
	#if((pb[i]==0) or not pb[i].isdigit()):
		#print('rert')
	#	return -3
	#i=0
	#print("pb[i] before count " + str(pb[i]))
	while(pb[i].isdigit()):
		count = (count*10) + int(pb[i])
		#print("count " + str(count))
		i+=1
	
	#print("count " + str(count))
	#print("node " + str(node))
	#print("size of adjacent " + str(len(Adjacent)))
	#print("size of node " + str(node))
	Adjacent[node-1][node-1] += float(count)
	for j in range(0, count):
		if((pb[i]==0) ):
			#print('rert')
			return -4
		while(pb[i]==' '):
			i+=1
		if((pb[i]==0) ):
			#print('rert')
			return -5
		val=0
		#i=0
		
		#print("edgelist ", end="", flush=True)
		#print(pb)
		#print("i : " + str(i))
		#print(pb[i]==' ')
		while(i < len(pb) and pb[i].isdigit()):
			val= (val*10) + int(pb[i])
			i+=1
			
		if(val==0 or val > nnodes):
			#print("val : " + str(val) + "nnodes " + str(nnodes))
			#print('rert')
			return -6
		Adjacent[node-1][val-1] = -1.0
		Adjacent[val-1][node-1] = -1.0
		Adjacent[val-1][val-1]+=1.0
		#print("here node " + str(node))
		#print("here val " + str(val))
		#print("here count " + str(count))
		#print("")
		
	#print(Adjacent)
	return count
		
def FindMaxRow(nnodes, nqueries, currow):
	i=0 
	maxrow=0
	max=0.0
	tmp=0.0
	max = fabs(Adjacent[currow][currow])
	maxrow=currow
	for i in range(currow+1, nnodes+1):
		tmp = fabs(Adjacent[i][currow])
		if(tmp>max):
			max=tmp
			maxrow=i

	return maxrow

def SwapRows(maxrow, currow, nnodes, nqueries):
		i=0 
		ncols=0
		tmp=0.0
		ncols=nnodes + nqueries
		for i in range(0, ncols):
			tmp=Adjacent[currow][i]
			Adjacent[currow][i] = Adjacent[maxrow][i]
			Adjacent[maxrow][i] = tmp

def Eliminate(currow, nrows, ncols):
	i=0
	j = 0
	factor=0.0
	for i in range(0, nrows):
		if(i==currow):
			continue
		factor = Adjacent[i][currow]
		for j in range(currow, ncols):
			Adjacent[i][j] -= factor*Adjacent[currow][j]

	return 0

def DumpMatrix(nrows, ncols): 
		j=0
		i=0
		for i in range(0, nrows):
			for j in range(0, ncols):
				#print(str(Adjacent[i][j]))
				i=0
		
def SolveMatrix(nnodes, nqueries):
	#print("nnodes " +str(nnodes))
	#print("nqueries " + str(nqueries))
	nrows=0 
	ncols=0
	currow=0 
	maxrow=0 
	i=0
	pivot=0.0
	ncols=nnodes+nqueries
	nrows=nnodes+1
	for currow in range(0, nnodes):
		maxrow=FindMaxRow(nnodes, nqueries, currow)
		#print("maxrow " + str(maxrow))
		#print("currow " + str(currow))
		if(maxrow!=currow):
			SwapRows(maxrow,currow, nnodes, nqueries)
			
		pivot = Adjacent[currow][currow]
		#print(str(currow) +" pivto " + str(pivot))
		if(fabs(pivot)<.001):
			#print('pvitor enter')
			return -1
		pivot = 1.0/pivot
		for i in range(currow, ncols):
			Adjacent[currow][i] *= pivot
		Eliminate(currow, nrows, ncols)
		DumpMatrix(nrows, ncols)

	return 0

def main():
	nprob=0
	curprob=0
	index=0 
	nnodes=0
	nqueries=0
	nedges=0
	i=0
	edgecnt=0 
	edgelines=0 
	queryno=0
	dist=0.0
	inputFile = open(str(sys.argv[1]))
	nprob = int(inputFile.readline())
	for curprob in range(1, nprob+1):
		#print('curprob ' + str(curprob))
		#print('nprob ' + str(nprob))
		line = inputFile.readline()
		lineSplit = line.split(" ")
		index = int(lineSplit[0])
		nnodes = int(lineSplit[1])
		nqueries = int(lineSplit[2])
		nedges = int(lineSplit[3])
		if(index != curprob):
				return -5
		if(nnodes > MAX_NODES or nqueries > MAX_QUERIES):
				return -6
		AdjacentInit(nnodes, nqueries)
		edgecnt = edgelines = 0
		j=0
		while(edgecnt < nedges):
			edgesreadline = inputFile.readline().strip()
			edges = edgesreadline.split(' ')
			edgeslist = modifyarr(edges)
			#print('edgecnt ' + str(edgecnt))
			#print("nedges " + str(nedges))
			#print("edgelist ", end="", flush=True)
			#print(edgeslist)
			if(len(edgeslist)>1):
				i = ScanEdgeData(nnodes, edgeslist)
				#edgesreadline = inputFile.readline().strip()
				#edges = edgesreadline.split(' ')
				#edgeslist = modifyarr(edges)
				#print("edgelist ", end="", flush=True)
				#print(edgeslist)
			else:
				break
			#print("i " + str(i))
			#j+=1
			edgelines+=1
			edgecnt+=i
		
		for i in range(0, nqueries):
			queryline = inputFile.readline()
			queryarr = queryline.strip('\n').split(' ')
			queryno = queryarr[0]
			#print("i " + str(i))
			#print("query1 size " + str(len(query1)))
			#print("queryarr ")
			#print(queryarr)
			query1[i] = int(queryarr[1])
			query2[i] = int(queryarr[2])
#			if(i+1 != queryno):
#				return -11
			Adjacent[int(query1[i])-1][nnodes+i] = 1.0
			Adjacent[int(query2[i])-1][nnodes+i] = -1.0
			
		#DumpMatrix(nnodes+1, nnodes+nqueries)
		i=SolveMatrix(nnodes, nqueries)
		#print("nnodes " + str(nnodes))
		#print("nqueries " + str(nqueries))
		if(i!=0):
			#print("yoo")
			return -13
		else:
			print(str(curprob) + " ", end="", flush=True)
			for i in range(0, nqueries):
				#print("adj " + str(Adjacent[int(query1[i])-1][int(nnodes)+i]))
				#print("adj " + str(Adjacent[int(query2[i])-1][int(nnodes)+i]))
				dist=fabs(Adjacent[int(query1[i])-1][int(nnodes)+i]
				- Adjacent[int(query2[i])-1][int(nnodes)+i])
				
				print("%.3f " %dist, end="", flush=True)
			print("")
	return 0

def modifyarr(arr):
	newlist = []
	for i in range(0, len(arr)):
		if(i%2==0):
			newlist.append(arr[i])
		else:
			newlist.append(' ')
			newlist.append(arr[i])
			if(i != len(arr)-1):
				newlist.append(' ')
	
	return newlist
	
main()
