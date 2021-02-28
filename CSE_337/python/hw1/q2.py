import sys
import math

class BitcoinVal:
	date=0.0
	value=0.0
	def __init__(self, date, value):
		self.date = date
		self.value = value
	
def pearsonCoffecient(filename):
	file = open(filename)
	filestr = file.readline()
	btclist = []
	while(str!=''):
		strSplit = filestr.split(',')
		#print(strSplit)
		if(len(strSplit)<2):
			break

		
		value = strSplit[1].replace('\n', '')
		btc = BitcoinVal(float(strSplit[0]), float(value))
		btclist.append(btc)
		filestr = file.readline()
		
	top = topleft(btclist) - topRight(btclist)
	Bottom = bottom(btclist)
	file.close()
	return (top /(math.sqrt(Bottom)))
	
def topleft(list):
	sum=0
	for btc in list:
		val = btc.date * btc.value
		sum += val
	
	return sum
	
def topRight(list):
	xsum=0
	ysum=0
	N=0
	#print(list)
	for btc in list:
		xsum += btc.date
		ysum += btc.value
		N+=1
		
	val = xsum * ysum
	return (val/N)
	
def bottom(list):
	xsquaresum=0
	ysquaresum=0
	xsum=0
	ysum=0
	N=0
	for btc in list:
		#Do X first (btc.date)
		xsq = btc.date**2
		xsquaresum+=xsq
		xsum += btc.date
		#y
		ysq = btc.value **2
		ysquaresum += ysq
		ysum += btc.value
		N+=1
	
	xside = xsquaresum - ((xsum**2)/N)
	yside = ysquaresum - ((ysum**2)/N)
	return xside * yside
	
varCof = pearsonCoffecient('prices_sample.csv')
print("Pearson coeficient" + str(varCof))
