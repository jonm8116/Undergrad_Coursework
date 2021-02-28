import sys

def parsefile(filename):
	#file = open(filename, encoding="utf-8")
	#linestr = str(file.readline())
	counter=0
	worddict = {} 
	for line in open(filename, encoding='utf8'):
		linearr = line.split(' ')
		#linearr = linestr.split(' ')
		
		for word in linearr:
			#get rid of 
			
			if (" 's " in word):
				word.replace("'s", "")
			if (word in worddict.keys()):
				worddict[word] += 1
			else:
				worddict[word] = 1
				
		counter+=1
		#linestr = file.readline()
	return worddict
	
#print (sys.stdout.encoding)
dict = parsefile('100-0.txt')
#print(dict)
#for key in worddict.keys():
#	print("key: " + key + " val: " + worrdict[key])