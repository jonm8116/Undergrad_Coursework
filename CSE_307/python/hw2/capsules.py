import sys
from numpy import *
import re

class Square:
	val=''
	row=0 
	col = 0
	avail = []
	def __init__(self):
		val=''
		row=0
		col=0
		avail=[]
	

class Solution:
	grid = []
	to_do = []
	def __init__(self):
		grid=[]
		to_do=[]

filename = open(sys.argv[1])
soln = Solution()
#firstlineArr = []

def adjacent_okay(s, val, r, c):
	for dr in range(-1, 1+1):
		for dc in range(-1, 1+1):
			if(s.grid[r+dr][c+dc].val==val):
				return False

	return True
	
def attempt(s):
	#print(s.to_do)
	if(len(s.to_do)==0):
		return True
	
	curr = s.to_do[len(s.to_do)-1]
	s.to_do.pop(len(s.to_do)-1)
	row =  curr.row 
	col = curr.col 
	avail = curr.avail
	#print(s.grid)
	#avail = avail.sort()
	for p in sorted(avail):
		if(adjacent_okay(s, p, row, col)):
			curr.avail.remove(p)
			s.grid[row][col].val = p
			if(attempt(s)):
				return True
			s.grid[row][col].val = '-'
			curr.avail.append(p)
			
	
	s.to_do.append(curr)
	#print(s.to_do)
	return False 
	
def init_solution(s, firstlineArr):
	r=0
	c=0 
	cin=0
	v=""
	#print(firstlineArr)
	r = int(firstlineArr[1])
	c = int(firstlineArr[2])
	s.grid = [Square() for i in range(r+2)]
	#print("grid " + str(s.grid[0].row))
	#print("size of grid " + str(len(s.grid)))
	for x in range(0, r+2):
		s.grid[x] = [Square() for i in range(c+2)] #[Square()] * (c+2)		
	#print("grid " + str(s.grid))
	for i in range(1, r+1):
		v = filename.readline().strip()
		vsplit = v.split(" ")
		for j in range(1, c+1):
			s.grid[i][j].val = vsplit[j-1]
			s.grid[i][j].row = i 
			s.grid[i][j].col = j 
			
	nblocks=0

	nblocks = int(filename.readline().strip())
	#print("nblocks is " + str(nblocks))
	for i in range(0, nblocks):
		blk = [] 
		nsquares=0
		nsq = filename.readline().strip("\n")
		nsqlist = nsq.split(" ")
		nsquares = int(nsqlist[0])
		nsqlist.pop(0)
		#for item in nsqlist: 
			#item.strip("\n")
		#	jack=''
		#	for z in range(0, len(item)):
		#		if(item[z].isdigit()):
		#			jack+= item[z]
					
		#	item = jack
		
		v = nsqlist
		#print(nsqlist)
		for l in range(1, nsquares+1):
			blk.append(str(l))
		
		for j in range(1, nsquares+1):
			r = int(v[j-1][1]) 
			c = int(v[j-1][3])
			#print(blk)
			#print("item in list " + str(s.grid[r][c].val))
			if(s.grid[r][c].val in blk):
				blk.remove(s.grid[r][c].val)
				
			s.grid[r][c].avail=blk 
			if(s.grid[r][c].val.strip("\n") == "-"):
				#print("HERE")
				s.to_do.append(s.grid[r][c])

				
def main():
	p=0
	p = int(filename.readline())
	for i in range(1, p+1):
		firstline = filename.readline().strip("\n")
		firstlineArr = firstline.split(" ")
		#print(firstlineArr)
		k = int(firstlineArr[0])
		print(str(k))
		init_solution(soln, firstlineArr)
		if(attempt(soln)):
			for i in range(1,len(soln.grid)-1):
				for j in range(1,len(soln.grid[0])-1):
					print (soln.grid[i][j].val,end = " ")
				print ()
		else:
			print("no solution")
			
main()
		
		