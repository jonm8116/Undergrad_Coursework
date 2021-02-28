from q5_p1 import checkpasswords

def promptuser():
	text=''
	while(not checkpasswords(text)):
		text= input("Enter password")
		

promptuser()