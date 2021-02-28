from q4_p1 import parsefile
import operator

def toptenwords(dict):
	#loop through length of dictionary
	toptenlist = ['']*10
	for key in dict.keys():
		for i in range(0, 10):
			if(emptyspot(toptenlist) != -1):
				toptenlist[i] = key
			else:
				if(dict[toptenlist[i]] < dict[key] and key not in toptenlist):
					toptenlist[i] = key
	
	#print(toptenlist)
	#print top ten words:
	print("the top ten words are: ")
	filewr = open('top10words.txt', 'w')
	for item in toptenlist:
		filewr.write(item)
		filewr.write(",")
		filewr.write(str(dict[item]))
		filewr.write('\n')
		print(item)
		
	
def emptyspot(toptenlist):
	for i in range(0, 10):
		if(toptenlist[i]==''):
			return i
	
	return -1
	
toptenwords(parsefile('100-0.txt'))