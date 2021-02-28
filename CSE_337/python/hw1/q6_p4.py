def count_pattern(str, pattern, replace_str):
	assert len(str) >= len(pattern)
	newstr = ''
	i=0
	while(i < len(str)):
		index = checksub(i, str, pattern)
		if(index!=-1):
			newstr += replace_str
			i+= len(pattern)
		else:
			newstr += str[i]
			i+=1
			
	
	print(newstr)
	
def checksub(index, str, pattern):
	i=0
	boolval = True
	difflist= []
	spotfound=True
	saveindex=-1
	count=0
	
	for i in range(index, index + len(pattern)):
		if((i< len(str)-2)):
			#spotfound=False
			#print("pattern " + pattern[count] + pattern[count+1])
			#print("str " + str[i] + str[i+1])
			if(count != len(pattern)-1):
				if((ord(pattern[count])-ord(pattern[count+1])) == 
				(ord(str[i])-ord(str[i+1]))):
				
					if(spotfound):
						saveindex=i
						#spotfound=False
						#print("pattern " + pattern[count] + pattern[count+1])
						#print("str " + str[i] + str[i+1])
						#spotfound=False
						#saveindex=i
					count+=1
				
				else:
					break
				
	return saveindex
	
count_pattern('shihfdddedaaba', 'xyx', '123')
