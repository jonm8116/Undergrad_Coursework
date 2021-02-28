def squareEven(maplist):
	retlist = map(lambda v: v**2, filter(lambda x: x%2==0, maplist))
	
	for num in retlist:
		print(str(num))
		
squareEven([1,2,3,4,5,6,7,8,9,10])