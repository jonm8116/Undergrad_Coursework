import q1_p1
import math
btcdict = {}

class BitcoinVal:
	def __init__(self, date, value):
		self.date = date
		self.value = value

def btcStats(filename):
	file = open(filename)
	list_data = []
	list_prices = []
	filestr = file.readline()
	#debug point
	counter=0
	while(str!=''):
		strSplit = filestr.split(" ")
#		print(strSplit)
#		print(len(strSplit))
		if(len(strSplit)<2):
			filestr = file.readline()
			break

		btcVal = strSplit[2]
		btcDate = strSplit[0] + " " + strSplit[1]
		btcdict[btcVal] = btcDate
		btcObj = BitcoinVal(btcDate, strSplit[2])
		list_prices.append(float(btcVal))
		list_data.append(btcObj)
		counter +=1
		filestr = file.readline()
		
		
	#print("loop traverse " + str(counter))
	Listmax = max(list_prices)
	Listmin = min(list_prices)
	mean = calcMean(list_prices)
	sd = calcSD(list_prices, mean)
	#dates
	maxdate = matchPart(Listmax, list_data)
	mindate = matchPart(Listmin, list_data)
	median = findmedian(list_prices)
	mediandate = matchPart(median, list_data)
	
	print("max: " + str(Listmax))
	print("max date: " + str(maxdate))
	print("min: " + str(Listmin))
	print("min date: " + str(mindate))
	print("mean: " + str(mean))
	print("median: " + str(median)) #.value before
	print("median date: " + str(mediandate))
	print("standard deviation " + str(sd))
	file.close()
	
def matchPart(val, btclist):
	for btc in btclist:
		if(float(btc.value) == val):
			
			return btc.date
	
def calcMean(list):
	counter=0
	sum=0
	for element in list:
		sum += element
		counter+=1
	mean = sum/counter
	return mean

def findmedian(list_data):
	#list_data.sort(key=lambda x: x.value, reverse = True)
	list_data.sort(key=float)
	#print("list size " + str(len(list_data)))
	#print("list min " + str(list_data[0]))
	#print("list max " + str(list_data[len(list_data)-1]))
	size = len(list_data)
	index = int(size/2)+1
	#print("index " + str(list_data[index]))
	median = list_data[index-1]
	#for e in list_data:
	#	print("val " + e.value)
	return median
		
	
def calcSD(list, mean):
	sum=0
	counter=0
	for element in list:
		val = (element - mean)**2
		sum+= val
		counter+=1
	nextval = sum/counter
	return math.sqrt(nextval)

btcStats('datetimes.txt')