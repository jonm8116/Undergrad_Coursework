class Node:
    def __init__(self):
        print("init node")

    def evaluate(self):
        return 0

    def execute(self):
        return 0

class NumberNode(Node):
    def __init__(self, v):
        print("IN NUMBER NODE " + str(v))
        if('.' in v):
            self.value = float(v)
        else:
            self.value = int(v)

    def evaluate(self):
        print("EVALUATE NUM")
        return self.value

class BopNode(Node):
    def __init__(self, op, v1, v2):
        self.v1 = v1
        self.v2 = v2
        self.op = op

    def evaluate(self):
        print("arg1 " + str(self.v1.evaluate()) + " arg2 " + str(self.v2.value))
        if (self.op == '+'):
            if(type(self.v1) is StrNode and type(self.v2) is StrNode): 
                print("nice guy")
                str1 = str(self.v1.value) 
                str2 = str(self.v2.value)
                #str1 = str1[1:len(str1)-1]
                #str2 = str2[1:len(str2)-1]
                temp = str1 + str2
                temp.replace("\'", "")
                StrNode(temp) 
                newstr = "\'" + temp + "\'"
                return newstr   
            return self.v1.evaluate() + self.v2.evaluate()
        elif (self.op == '-'):
            return self.v1.evaluate() - self.v2.evaluate()
        elif (self.op == '*'):
            return self.v1.evaluate() * self.v2.evaluate()
        elif (self.op == '/'):
            return self.v1.evaluate() / self.v2.evaluate() 
        elif (self.op == '>'):
            return self.v1.evaluate() > self.v2.evaluate()
        elif (self.op == '<'):
            return self.v1.evaluate() < self.v2.evaluate()
        elif (self.op == '%'):
            return self.v1.evaluate() % self.v2.evaluate()
        elif (self.op == '**'):
            return self.v1.evaluate() ** self.v2.evaluate()
        elif (self.op == '>='):
            return self.v1.evaluate() >= self.v2.evaluate()
        elif (self.op == '<='):
            return self.v1.evaluate() <= self.v2.evaluate() 
        elif (self.op == '=='):
            return self.v1.evaluate() == self.v2.evaluate() 
        elif (self.op == 'and'):
            return self.v1.evaluate() and self.v2.evaluate()
        elif (self.op == 'or'):
            return self.v1.evaluate() or self.v2.evaluate()

class PrintNode(Node):
    def __init__(self, v):
        self.value = v
        print("YOU DUM PROGRAM")
        print("self.value = " + str(self.value))

    def execute(self):
        print("BLAHHHH")
        self.value = self.value.evaluate()

#ADDED NODES BELOW
class StatementNode(Node):
    def __init__(self, v):
        self.value = v

    def execute(self):
        self.value = self.value.evaluate()
        print(self.value)

class StrNode(Node):
    def __init__(self, v):
        temp = str(v)
        self.value = temp.replace("\"","")
        #self.type="STR"

    def evaluate(self):
        return self.value

class IndexNode(Node):
    def __init__(self, arr, index):
        print("ARR: " + str(arr))
        self.arr = arr
        self.index = index
        print("INDEX: " + str(self.index))
         
    def evaluate(self):
        return self.arr[self.index].value

class ArrayNode(Node):
    def __init__(self, arr):
        templist = [] 
        i=0
        for item in arr:
           templist.append(arr[i])
           i+=1
        self.value = templist
            
    def evaluate(self):
        return self.value 

class BooleanNode(Node):
    def __init__(self, op, arg1, arg2):
        self.op = op
        self.arg1 = arg1
        self.arg2 = arg2

    def evaluate(self):
        if(self.op == 'and'):
            return self.arg1 and self.arg2
        

#class ArrayIndexNode(Node):
#    def __init__(self, arr):
#        templist=[] 
#        templist = arr.split("]")
#        actualarr = templist[0]
#        index = templist[1]
#        print(str(actualarr))
#        print(str(index))

#    def evaluate(self):
#        print("working")

#ADDED NODES ABOVE ^

class BlockNode(Node):
    def __init__(self, s):
        self.sl = [s]

    def execute(self):
        for statement in self.sl:
            statement.execute()

tokens = (
    'PRINT','LPAREN', 'RPAREN', 'SEMI',
    'NUMBER',
    #ADD STRING
    'STR', 'LSQUARE', 'RSQUARE', 'COMMA',
    'PLUS','MINUS','TIMES','DIVIDE',
    #ADDED OPERATOR TOKENS
    'GREATER', 'LESS', 'MOD', 'EXPONENT',
    'GREATEQ', 'LESSEQ', 'EQUALS', 'NOT',
    'AND', 'OR', 'NEWLINE'
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
t_LSQUARE = r'\['
t_RSQUARE = r'\]'
#ADDED TOKENS
t_COMMA = r','
#ADDED OPERATORS
t_GREATER = r'>'
t_LESS = r'<'
t_MOD = r'%'
t_EXPONENT = r'\*\*'
t_GREATEQ = r'>='
t_LESSEQ = r'<='
t_EQUALS = r'=='
t_NOT = r'not'
t_AND = r'and'
t_OR = r'or'
t_NEWLINE = r'\n'

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
    r'\".*?\"|\'.*?\''
    try:
        t.value = StrNode(t.value)
    #t.evaluate()
    except ValueError:
        t.value = ""
        print("String error")

    return t

#def t_ARRAY(t):
#    r'\[(((-?\d*(\d\.|\.\d)\d*)|\d+|(\".*?\")|(\'.*?\')),)*((-?\d*(\d\.|\.\d)\d*)|\d+|(\".*?\")|(\'.*?\'))\]|\[\]'
#    try:
#        temp = t.value
#        temp.strip(" ")
#        templist = temp.split(",")
#        t.value = ArrayNode(templist)
#
#    except ValueError:
#        t.value = []
#        print("Error")

#def t_INDEX(t):
#    r'\[(((-?\d*(\d\.|\.\d)\d*)|\d+|(\".*?\")|(\'.*?\')),)*((-?\d*(\d\.|\.\d)\d*)|\d+|(\".*?\")\[\d+\]|(\'.*?\'))\]\[\d+\]'
#    try:
#        print("I MADE IT")
#        temp = t.value
#        temp.strip(" ")
#        #templist = temp.spilt(",")
#        t.value = ArrayIndexNode(templist)

#    except ValueError:
#        t.value = []
#        print("Error")

# Ignored characters
t_ignore = " \t"

def t_error(t):
    print("Syntax error at '%s'" % t.value)

# Build the lexer
import ply.lex as lex
lex.lex()

# Parsing rules
precedence = (
    ('left','PLUS','MINUS'),
    ('left','TIMES','DIVIDE')
    )

#def p_block(t):
#    """
#    block : LBRACE inblock RBRACE
#    """
#    t[0] = t[2]

def p_inblock(t):
    """
    inblock : smt inblock
    """i
    t[0] = t[2]
    t[0].sl.insert(0,t[1])

def p_inblock2(t):
    """
    inblock : smt
    """
    t[0] = BlockNode(t[1])

def p_smt(t):
    """
    smt : print_smt
    """
    t[0] = t[1]

#ORIGINAL GRAMMAR:
# print_smt : PRINT LPAREN expression RPAREN SEMI | PRINT LPAREN word RPAREN SEMI
  
def p_print_smt(t):
    """
    print_smt : PRINT LPAREN expression RPAREN SEMI
              | PRINT LPAREN word RPAREN SEMI
              | PRINT LPAREN index RPAREN SEMI
    """
    print("AT PRINTNODE")
    t[0] = PrintNode(t[3])

#def p_statement(t):
#    '''
#    statement : expression
#    '''
#    t[0] = StatementNode(t[1])

def p_expression_binop(t):
    '''expression : expression PLUS expression
                  | expression MINUS expression
                  | expression TIMES expression
                  | expression DIVIDE expression
                  | expression GREATER expression
                  | expression LESS expression
                  | expression MOD expression
                  | expression EXPONENT expression
                  | expression GREATEQ expression
                  | expression LESSEQ expression
                  | expression NOT expression
                  | expression AND expression
                  | expression OR expression
                  | expression EQUALS expression
                  '''
    #print("BOPNODE " + str(t[2]))
    t[0] = BopNode(t[2], t[1], t[3])

def p_expression_factor(t):
    '''expression : factor'''
    #print("EXPR : FACTOR")
    t[0] = t[1]

def p_factor_number(t):
    '''factor : NUMBER'''
    print("FOUND A FACTOR")
    t[0] = t[1]
    print("NUM is -> " + str(t[1].value))

#ADDED
def p_word_plus_word(t):
    '''words : word PLUS word'''
    t[0] = str(t[1]) + str(t[3])

def p_words_str(t):
    '''words : STR '''
    t[0] = t[1]

def p_expression_word(t):
    '''expression : word'''
    t[0] = t[1]

def p_word_str(t):
    '''
    word : STR
    '''
    t[0] = t[1]

#def p_list_index(t):
#    '''
#    list : INDEX
#    '''

#DEFINE GRAMMAR RULES FOR LIST
def p_array_list(t):
    '''
    array : LSQUARE list RSQUARE
    '''
    print("FOUND ARRAY")
    print("after adding t[1] " + str(t[2][0].value))
    t[0] = t[2]
    print("array found " + str(t[0]))

def p_list_element(t):
    '''
    list : element COMMA list
         | element list 
    '''
    print("LIST : ELEM COMMA LIST")
    t[0] = t[3]
    print("LIST ITSELF " + str(t[0][0].value))
    t[0].insert(0, t[1])    

def p_list_element_terminal(t):
    '''
    list : element
    '''
    #print("LIST : ELEMENT \n")
    #print("before adding t[1] " + str(t[0]))
    if(t[0] is None):
        t[0] = []
        t[0].insert(0, t[1])
    #print("after adding t[1] " + str(t[0][0].value))

#NEED TO INCLUDE LIST BUT INFINITE RECURSION @#%$@#%
def p_element(t):
    '''
    element : expression 
    '''
    #print("ELEMENT : EXPR")
    t[0] = t[1]
    #print("element expression is " + str(t[1]))

def p_index(t):
    '''
    index : array LSQUARE expression RSQUARE    
    '''
    print("INDEX : ARRAY ....")
    print("expression is " + str(t[3]))
    t[0] = IndexNode(t[1], t[3].value)
    print("here " + str(t[0]))

def p_index_expression(t):
    '''
    expression : index
    '''
    t[0] = ((IndexNode)(t[1])).evaluate()

def p_error(t):
    print("Syntax error at '%s'" % t.value)

import ply.yacc as yacc
yacc.yacc()

import sys

if (len(sys.argv) != 2):
    sys.exit("invalid arguments")
fd = open(sys.argv[1], 'r')
code = ""
for line in fd:
    code += line.strip()

# = "{ print(1+2); print(2*3); }"

try:
    print(code)
    lex.input(code)
    while True:
        token = lex.token()
        if not token: break
        #print(token)
    ast = yacc.parse(code)
    ast.execute()
except Exception as e:
    print(str(e))
    #logger.error(str(e))
    print("ERROR")
