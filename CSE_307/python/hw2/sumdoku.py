from numpy import *
import sys
import operator
#CONSTANTS
ALL_MASK = 0x1ff
valid_masks = [0, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x100]
constraints = [0 for i in range(15)]
for i in range(0, len(constraints)):
	constraints[i] = [0 for j in range]

inputFile = open(sys.argv[1])	

class SEARCH_STATE:
	avail_mask=[]
	row_avail_counts=[]
	col_avail_counts=[]
	box_avail_counts=[]
	val_set = []
	
	def __init__(self):
		avail_mask = [0 for i in range(9)]
		for x in range(0, len(avail_mask)):
			avail_mask[x] = [0 for i in range(9)]
			
		row_avail_counts = [0 for i in range(9)]
		for x in range(0, len(row_avail_counts)):
			row_avail_counts[x] = [0 for i in range(9)]
			
		col_avail_counts = [0 for i in range(9)]
		for x in range(0, len(col_avail_counts)):
			col_avail_counts[x] = [0 for i in range(9)]
		
		box_avail_counts = [0 for i in range(3)]
		for x in range(0, len(box_avail_counts)):
			box_avail_counts[x] = [0 for i in range(3)]
			for y in range(0, len(box_avail_counts[x])):
				box_avail_counts[x][y] = [0 for i in range(9)]
		
		val_set = [0 for i in range(9)]
		for x in range(0, len(val_set)):
			val_set[x] = [0 for i in range(9)]

states = [SEARCH_STATE() in range(81)]
			
def search_init():
	pss = SEARCH_STATE()	#&(states[0])
	i=0
	j=0
	k=0
	for i in range(0, 9):
		for j in range(0, 9):
			pss.avail_mask[i][j] = ALL_MASK
			pss.val_set[i][j] = 0
			pss.row_avail_counts[i][j] = 9
			pss.col_avail_counts[i][j] = 9
			
	for i in range(0, 3):
		for j in range(0, 3):
			for k in range(0, 9):
				pss.box_avail_counts[i][j][k] = 9
				
				
def scan_convert(prow, n, s):
	i=0
	scounter=0
	for i in range(0, n):
		if(s[scounter]=='<'):
			prow+=1
			prow= -1
		elif(s[scounter]=='='):
			prow+=1
			prow = 0 
		elif(s[scounter]=='>'):
			prow+=1 
			prow=1
		else:
			return i 
	
	scounter+=1
		
def scan_constraints():
	i=0 
	j=0
	n=0
	for i in range(0, 3):
		for j in range(0, 3):
			#READLINE
			line= inputFile.readline()
			#if(line is None):
			#	print("Error 21")
			#	return -21
			
			n= scan_convert(constraints[(5*(i+2)*j)][0], 6, line)
			if(n!=6):
				print("Error -22")
				return -22
			
			if(j < 2):
				line= inputFile.readline()
				#SKIP ERROR CHECKING
				n=scan_convert(constraints[(5*(i+2)*j)+1][0], 9, line)
				if(n!=9):
					print("Error -24")
					return -24
					
	
	return 0 
	
#CHECK OR EQUALS
def checkEqual(baseMask, chkMask):
	result = 0
	i=0 
	if(valid_masks[5] and baseMask):
		result = result or valid_masks[5]
	
	for i in range(1,9+1):
		if(((valid_masks[i] & chkMask)==0) and (valid_masks[10-i] &baseMask)):
			result = result or vaild_masks[10-i]
			
			
	return result
	
def checkLess(baseMask, chkMask):
	result=0
	i=0 
	if(valid_masks[9] & baseMask):
		result = result or valid_masks[9]
		
	for i in range(1, 8+1):
		if((valid_masks[i] & chkMask) != 0):
			break 
		elif(valid_masks[9-i] & baseMask):
			result = result or valid_masks[9-i] 
			

	return result 
	
def checkGreater(baseMask, chkMask):
	result=0
	i=0
	if(valid_masks[1] & baseMask):
		result = result or valid_masks[1]
		
	for i in range(9, 3, -1):
		if((valid_masks[i] & chkMask) !=0):
			break
		elif(valid_masks[11-i] & baseMask):
			result = result or valid_masks[11-i]
			
	
	return result 
	
def checkConstraint(constraint, baseMask, chkMask):
	if(constraint < 0 ):
		return checkLess(baseMask, chkMask)
	elif(constraint>0):
		return checkGreater(baseMask, chkMask)
	else:
		return checkEqual(baseMask, chkMask)

def check_constraints(pss):
	i=1 
	row=1
	col=1 
	baseConsRow=1 
	baseConsCol=1 
	scan_count=1
	change_count=1
	
	baseMask=''
	chkMask=''
	resultMask=''
	totResult=''
	
	while(change_count>0):
		scan_count+=1 
		change_count=0 
		baseConsRow=0 
		for row in range(0, 9):
			for col in range(0, 9):
				if(pss.val_set[row][col]==0):
					baseMask = pss.avail_mask[row][col]
					totResult = 0
					if((col % 3) != 0):
						chkMask = pss.avail_mask[row][col-1]
						resultMask = checkConstraint(constraints[baseConsRow][baseConsCol-1], baseMask, chkMask)
						if(resultMask != 0):
							baseMask &= operator.invert(resultMask)
							change_count += 1 
							totResult |= resultMask 
					if((col%3)!= 2):
						chkMask = pss.avail_mask[row][col+1]
						resultMask=checkConstraint(constraints[baseConsRow][baseConsCol], baseMask, chkMask)
						if(resultMask!=0):
							baseMask &= operator.invert(resultMask)
							change_count+= 1 
							totResult |= resultMask
					
					if((row%3)!=0):
						chkMask = pss.avail_mask[row-1][col]
						resultMask = checkConstraint(constraints[baseConsRow-1][col], baseMask, chkMask)
						if(resultMask!=0):
							baseMask &= operator.invert(resultMask)
							change_count+=1 
							totResult |= resultMask 
					
					if((row%3)!=2):
						chkMask = pss.avail_mask[row+1][col]
						resultMask = checkConstraint(constraints[baseConsRow+1][col], baseMask, chkMask)
						if(resultMask!=0):
							baseMask &= operator.invert(resultMask)
							change_count+=1 
							totResult |= resultMask
							
					if(baseMask==0):
						return -1 
					
					pss.avail_mask[row][col] = baseMask
					if(totResult!=0):
						for i in range(0, 9):
							if(valid_masks[i] & totResult):
								pss.col_avail_counts[col][i-1] -= 1
								pss.row_avail_counts[row][i-1] -= 1
								box_avail_counts[row/3][i-1] -= 1 
					
					if((col%3)!=2):
						baseConsCol+=1 
				
				if((row%3)!=2):
					baseConsRow +=2 
				else:
					baseConsRow+=1 
	
	return 0
	
STYP_ROW = 1 
STYP_COL = 2 
STYP_BOX = 3 
class SOLVE_DATA:
	solve_type=0
	solve_val=0 
	solve_row=0 
	solve_col=0 
	solve_cnt=0 
	solve_index=0 
	test_row=0 
	test_col=0 
	
	def __init__(self):
		solve_type=0
		solve_val=0 
		solve_row=0 
		solve_col=0 
		solve_cnt=0 
		solve_index=0 
		test_row=0 
		test_col=0 
		
def GetSolveStep(pss, psd):
	i=0 
	j=0
	k=0 
	psd.solve_cnt = 10 
	for i in range(0, 9):
		for j in range(0, 9):
			if(pss.row_avail_counts[i][j] < psd.solve_cnt):
				psd.solve_cnt = pss.row_avail_counts[i][j] 
				psd.solve_type = STYP_ROW
				psd.solve_row = i 
				psd.solve_val = j+1 
				
	for i in range(0, 9):
		for j in range(0, 9):
			if(pss.col_avail_counts[i][j] < psd.solve_cnt):
				psd.solve_cnt = pss.col_avail_counts[i][j]
				psd.solve_type = STYP_COL
				psd.solve_col = i 
				psd.solve_val = j+1 
	
	for i in range(0, 3):
		for j in range(0, 3):
			for k in range(0, 9):
				if(pss.box_avail_counts[i][j][k] < psd.solve_cnt):
					psd.solve_cnt = pss.box_avail_counts[i][j][k]
					psd.solve_type = STYP_BOX 
					psd.solve_row = i 
					psd.solve_col = j 
					psd.solve_val = k+1 
					
	if(psd.solve_cnt==0):
		return -1 
	else:
		return 0 
		
def FindNextTest(pss, psd):
	i=0 
	j=0 
	starti=0 
	startj=0 
	mask = valid_masks[psd.solve_val]
	if(psd.solve_index >= psd.solve_cnt):
		return -1 
	elif(psd.solve_type == STYP_ROW):
		if(psd.solve_index == 0):
			startj = 0 
		else:
			startj=psd.test_col+1 
		i=psd.solve_row 
		for j in range(startj, 9):
			if(pss.avail_mask[i][j] & mask):
				psd.test_col = j 
				psd.test_row = i 
				psd.solve_index+=1 
				return 0 
		
		return -1 
	elif(psd.solve_type == STYP_COL):
		if(psd.solve_index==0):
			starti=0 
		else:
			starti=psd.test_row+1 
		j=psd.solve_col
		for i in range(starti, 9):
			if(pss.avail_mask[i][j] & mask):
				psd.test_col = j
				psd.test_row= i 
				psd.solve_index +=1 
				return 0 
				
	elif(psd.solve_type == STYP_BOX):
		if(psd.solve_index==0):
			starti=0 
			startj=0 
		else:
			starti=psd.test_row - (3 * psd.solve_row)
			startj = psd.test_col+1 - (3 * psd.solve_col)
		
		for i in range(starti, 3):
			for j in range(startj, 3):
				if(pss.avail_mask[i+(3*psd.solve_row)][j+3*psd.solve_col] & mask):
					psd.test_col = j + 3 * psd.solve_col
					psd.test_row = i + 3 *psd.solve_row 
					psd.solve_index += 1 
					return 0 
		
		return -1 
	else:
		print("Error")
		return -1 

def ApplyChoice(pss, row, col, val):
	i=0
	j=0 
	boxr=0 
	boxc=0 
	if(pss.val_set[row][col]!=0):
		print("error")
		return -1 
	pss.val_set[row][col] = val 
	boxr = row/3 
	boxc = col/3 
	for j in range(0, 9):
		if(pss.avail_mask[row][j] & mask):
			pss.box_avail_counts[boxr][j/3][val-1] -=1 
			pss.col_avail_counts[j][val-1]-=1 
			
		pss.avail_mask[row][j] &= operator.invert(mask)
		
	for i in range(0, 9):
		if(pss.avail_mask[i][col] & mask):
			pss.box_avail_counts[i/3][boxc][val-1]-=1 
			pss.row_avail_counts[i][val-1] -=1 
		
		pss.avail_mask[i][col] &= operator.invert(mask) 
	
	boxr = row/3 
	boxc = col/3 
	for i in range(3*boxr, 3*boxr+1):
		for j in range(3*boxc, 3*boxc+1):
			if(pss.avail_mask[i][j] & mask):
				pss.col_avail_counts[j][val-1]-=1 
				pss.row_avail_counts[i][val-1] -= 1 
				
			pss.avail_mask[i][j] &= operator.invert(mask)
			
	for i in range(1, 9+1):
		if((i!=val) && ((pss.avail_mask[row][col] & valid_masks[i] !=0)):	
			pss.box_avail_counts[row/3][col/3][i-1]-=1
			pss.col_avail_counts[col][i-1]-=1 
			pss.row_avail_counts[row][i-1]-=1 
	
	pss.avail_mask[row][col] = mask 
	pss.row_avail_counts[row][val-1] = 32 
	pss.col_avail_counts[col][val-1] = 32  
	pss.box_avail_counts[boxr][boxc][val-1] = 32 
	return 0 
	
def Solve(level):
	pssnxt = states[level]
	pss=states[level]
	i=0 
	j=0 
	if(GetSolveStep(pss, sd)!=0 ):
		return -1 
	print("level " + str(level) + " solve type " + str(sd.solve_type) +
	" row " + str(sd.solve_row) + " col " + str(sd.solve_col) + " val " +
	str(sd.solve_val) + " cnt " + str(sd.solve_cnt))
	
	sd.solve_index = 0 
	while(FindNextTest(pss, sd)==0):
		if(level==80):
			pss.val_set[sd.test_row][sd.test_col] = sd.solve_val
			return 0 
		else:
			pssnxt = states[level+1] 
			pssnxt = pss 
			print("error")
			
			
		
	