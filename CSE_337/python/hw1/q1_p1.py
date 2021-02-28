import datetime
import math
import sys

def convertTime(time):
	timestr = datetime.datetime.fromtimestamp(int(time)).strftime('%Y-%m-%d %H:%M:%S')
	return timestr
	
def readCells(filename):
	file = open(filename)
	str = file.readline()
	writefile = open('datetimes.txt', 'w')
	while(str!=''):
		strSplit = str.split(',')
		time = strSplit[0]
		btcVal = strSplit[1]
		time = convertTime(time)
		writefile.write(time + " " + btcVal)
		str = file.readline()
	file.close()
	writefile.close()
	
readCells('prices_sample.csv')