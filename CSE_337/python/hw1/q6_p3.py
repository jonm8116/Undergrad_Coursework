outerlist =[]

def combo(arr, marker):
	if (marker >= len(arr)):
		outerlist.append(arr)
	else:
		temp = arr.copy()
		temp[marker]= 0
		combo(temp, marker+1)
		temp[marker] = 1
		combo(temp, marker+1)

def interleave(list1, list2):
	order = list1 + list2
	combo(order, 0)
	for item in outerlist:
		for i in range(0, len(item)):
			if(item[i] == 1):
				print(str(order[i]), end="", flush=True )
		
		print()

interleave([1,2,3], [4,5,6])