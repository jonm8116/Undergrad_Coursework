def find_primes():
	i=0
	primelist = []
	for i in range(2, 100+1):
		primelist.append(i)
	
	#print(primelist)
	for i in range(3, 100):
		for j in range(3, i):
			primelist = filter(lambda i: i %2!=0, primelist)
	
	primes = [2]
	primes.extend(list(primelist))
	#print(primes)
	for i in range(2, 100):
		if(i%2!=0):
			if(invalidprime(i)):
				print(str(i) )
				primes.remove(i)
			
	print(primes)

def invalidprime(num):
	boolval = False
	for i in range(2, num):
		if(num%i == 0):
			boolval = True
			
	return boolval
	
find_primes()