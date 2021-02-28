class Node:
	def __init__(self):
		print("init node")

	def evaluate(self):
		return 0

	def execute(self):
		return 0
class NumberNode(Node):
	def __init__(self, v):
		if('.' in v):
			self.value = float(v)
		else:
			self.value = int(v)

	def evaluate(self):
		return self.value

class BopNode(Node):
	def __init__(self, op, v1, v2):
		self.v1 = v1
		self.v2 = v2
		self.op = op

	def evaluate(self):
		if (self.op == '+'):
			return self.v1.evaluate() + self.v2.evaluate()
		elif (self.op == '-'):
			return self.v1.evaluate() - self.v2.evaluate()
		elif (self.op == '*'):
			return self.v1.evaluate() * self.v2.evaluate()
		elif (self.op == '/'):
			return self.v1.evaluate() / self.v2.evaluate()

		elif (self.v1.type =="STR" and self.v2.type =="STR"):
			return str(self.vl) + str(self.v2)

class PrintNode(Node):
	def __init__(self, v):
		self.value = v

	def execute(self):
		self.value = self.value.evaluate()
		print(self.value)

#ADDED NODES BELOW
class StrNode(Node):
	def __init__(self, v):
		temp = str(v)
		self.value = temp.replace("\"", "\'")
		#self.type="STR"

	def evaluate(self):
		return self.value


#ADDED NODES ABOVE ^

class BlockNode(Node):
	def __init__(self, s):
		self.sl = [s]

	def execute(self):
		for statement in self.sl:
			statement.execute()

tokens = (
	'LBRACE', 'RBRACE',
	'PRINT','LPAREN', 'RPAREN', 'SEMI',
	'NUMBER',
	#ADD STRING
	'STR',
	'PLUS','MINUS','TIMES','DIVIDE'
	)

# Tokens
#t_LBRACE  = r'\{'
#t_RBRACE  = r'\}'
t_PRINT    = 'print'
t_LPAREN  = r'\('
t_RPAREN  = r'\)'
t_SEMI  = r';'
t_PLUS    = r'\+'
t_MINUS   = r'-'
t_TIMES   = r'\*'
t_DIVIDE  = r'/'
#ADDED TOKENS
def t_NUMBER(t):
	r'-?\d*(\d\.|\.\d)\d* | \d+'
	try:
		t.value = NumberNode(t.value)
	except ValueError:
		print("Integer value too large %d", t.value)
		t.value = 0
	return t

#ADDED FOR STR NODE
def t_STR(t):
	r'\"(.*)?\"|\'(.*)?\''
	try:
		t.value = StrNode(t.value)
	#t.evaluate()
	except ValueError:
		t.value = ""
		print("String error")

	return t

def t_ARRAY(t):
	r'\[(.*,) | (.*) | (.*,)*\]'
    try:
        temp = t.value
        temp.strip(" ")
        templist = temp.split(",")
