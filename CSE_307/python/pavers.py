from math import fabs
import sys
MAX_SIZE = 25
F = [0]*MAX_SIZE
F1 = [0]*MAX_SIZE
F2 = [0]*MAX_SIZE
F2 = [0]*MAX_SIZE
F3 = [0]*MAX_SIZE
G = [0]*MAX_SIZE
G1 = [0]*MAX_SIZE
G2 = [0]*MAX_SIZE
G3 = [0]*MAX_SIZE
def comp_tiles():
	#variables
	test = 0
	#empty list
	F[0] = 1
	F[1] = 2
	F[2] = 11
	F1[0] = 0
	F1[1] = 2
	F1[2] = 16
	F2[0] = 0
	F2[1] = 1
	F2[2] = 8
	F3[0] = 0
	F3[1] = 0
	F3[2] = 4
	G[0] = 0
	G[1] = 0
	G[2] = 2
	G1[0] = G1[1] = 0
	G1[2] = 1
	G2[0] = G2[1] = 0
	G2[2] = 1
	G3[0] = G3[1] = 0
	G3[2] = 1;
	for n in range(2, MAX_SIZE-1):
		F[n+1] = 2*F[n] + 7*F[n-1] + 4*G[n]
		F1[n+1] = 2*F1[n] + 2*F[n] + 7*F1[n-1] + 8*F[n-1] + 4*G1[n]+2*G[n]
		F2[n+1] = 2*F2[n] + F[n] + 7*F2[n-1] + 4*F[n-1] + 4*G2[n]+2*G[n]
		F3[n+1] = 2*F3[n] + 7*F3[n-1] + 4*F[n-1] + 4*G3[n]+2*G[n]
		test = 2.0*((n+1))*F[n+1]
		test1 = F1[n+1] + 2.0*F2[n+1] + 3.0*F3[n+1]
		if(fabs(test - test1) > .0000001*test):
			print("mismatch " + str(n+1) + ": " + str(test) + " != " + str(test1))
		G[n+1] = 2*F[n-1] + G[n]
		G1[n+1] = 2*F1[n-1] + F[n-1] + G1[n]
		G2[n+1] = 2*F2[n-1] + F[n-1] + G2[n] + G[n]
		G3[n+1] = 2*F3[n-1] + F[n-1] + G3[n]

	#print("val: " + str(F[3]))

def main():
	inbuf = [0]*256
	inputFile = open(str(sys.argv[1]))
	# if(inbuf[0] is None):
	# 	print("Read failed on problem count")
	# 	return -1
	nprob = inputFile.readline()
	nprob = int(nprob)
	# if(inbuf[0] != 1):
	# 	print("Scan failed on problem count")
	comp_tiles()
	index=0
	n=0
	#Read from file
	for curprob in range(1, nprob+1):
		line = inputFile.readline()
		lineArr = line.split(' ')
		index=lineArr[0]
		n=lineArr[1]
		n = int(n)
		if(n==1):
			print("2 2 1 0 " + str(curprob))
		elif((int(n)<2) or (int(n)>MAX_SIZE)):
			print("array width " + str(n) + " not in range 2 .. " +
			str(MAX_SIZE) + " problem " + str(curprob))
		else:
			f = F[n]
			f1 = F1[n]
			f2 = F2[n]
			f3 = F3[n]
			print(str(curprob) + " " + str(f) + " " + str(f1)
			+ " " + str(f2) + " " + str(f3))
main()
