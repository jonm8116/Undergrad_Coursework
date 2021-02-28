import re

def checkpasswords(password):
	if(len(password)<8):
		return False
	if(re.search('[a-zA-z]', password)== None or
		re.search('[0-9]', password)== None or 
		re.search('[\W]', password) == None):
		return False
	if(consecLetters(password)):
		return False
	if(checkpattern(password)):
		return False
	if(uniqueChars(password) < (len(password)/2)):
		print(uniqueChars(password))
		print(len(password))
		return False
	
	return True
	
def consecLetters(password):
	j=1
	boolval=False
	for i in range(0, len(password)-1):
		if(password[i] == password[j]):
			if(i<len(password)-2):
				if(password[i] == password[i+2]):
					boolval=True
		j+=1
    
	
	return boolval
	
def checkpattern(password):
    i=0
    j=1
    boolval = False
    for i in range(0, len(password)-1):
        firstVal = ord(password[i])
        secondVal = ord(password[j])
        if((firstVal+1) == secondVal):
            if(i<len(password)-2):
                if((ord(password[j])+1) == ord(password[j+1])):
                    boolval = True
        j+=1

    return boolval
	
def uniqueChars(password):
	listset = list(set(password))
	return len(listset)

#print(checkpasswords("ababab!2bab134"))
