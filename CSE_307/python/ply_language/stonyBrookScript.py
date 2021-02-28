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
        #print("BOPNODE INIT")

    def evaluate(self):
        if(type(self.v1) is StrNode and type(self.v2) is StrNode and self.op
            =='+'):
            self.v1.value = self.v1.value.replace("\'", "")
            self.v2.value = self.v2.value.replace("\'", "")
            #print("v1 value : " + self.v1.value)
            #print("v2 value : " + self.v2.value)
            temp = self.v1.value + self.v2.value
            #temp = "\'" + temp + "\'"
            newNode = StrNode(temp)
            return newNode.value

        if (self.op == '+'):
            return self.v1.evaluate() + self.v2.evaluate()
        elif (self.op == '-'):
            return self.v1.evaluate() - self.v2.evaluate()
        elif (self.op == '*'):
            return self.v1.evaluate() * self.v2.evaluate()
        elif (self.op == '/'):
            return self.v1.evaluate() / self.v2.evaluate()
        elif(self.op == '>'):
            return self.v1.evaluate() > self.v2.evaluate()
        elif(self.op == '<'):
            return self.v1.evaluate() < self.v2.evaluate()
        elif(self.op == '%'):
            return self.v1.evaluate() % self.v2.evaluate()
        elif(self.op == '**'):
            #print("EXPONENT DISCOVERED")
            #print("v1 " + str(self.v1.evaluate()))
            #print("v2 " + str(self.v2.evaluate()))
            arg1 = self.v1.evaluate()
            arg2 = self.v2.evaluate()
            #print("exp " + str(2**2))
            #print("temp " + str(arg1**arg2))
            temp = arg1**arg2
            #print("temp " + str(temp))
            return temp

        elif(self.op == '>='):
            return self.v1.evaluate() >= self.v2.evaluate()
        elif(self.op == '<='):
            return self.v1.evaluate() >= self.v2.evaluate()
        elif(self.op == '=='):
            return self.v1.evaluate() == self.v2.evaluate()
        elif(self.op == 'and'):
            return self.v1.evaluate() and self.v2.evaluate()
        elif(self.op == 'or'):
            return self.v1.evaluate() or self.v2.evaluate()
        elif(self.op=='<>'):
            return self.v1.evaluate() != self.v2.evaluate()
        elif(self.op=='//'):
            temp = self.v1.evaluate() / self.v2.evaluate()
            return int(temp)

        elif(self.op == 'in'):
            if(type(self.v1) is StrNode and type(self.v2) is StrNode):
                #print("IN OPERATOR")
                #print("v1 is " + self.v1.value + " v2 is " + self.v2.value)

                return self.v1.value in self.v2.value
            return self.v1.evaluate() in self.v2.evaluate()

#MAKE SEPARATE NODE FOR IN OPERATOR
class InNode(Node):
    def __init__(self, op, v1, v2):
        self.op = op
        self.v1 = v1
        self.v2 = v2

    def evaluate(self):
        if(type(v2) is list):
            return self.v1.evaluate() in self.v2
        else:
            return self.v1.evaluate() in self.v2.evaluate()

class PrintNode(Node):
    def __init__(self, v):
        self.value = v

    def execute(self):
        self.value = self.value.evaluate()
        print(self.value)

class StrNode(Node):
    def __init__(self, v):
        temp = str(v)
        self.value = temp.replace("\"", "")
        self.value = "\'" + self.value + "\'"
        #self.type = "STR"

    def evaluate(self):
        return self.value

class IndexNode(Node):
    def __init__(self, arr, index):
        #print("ARR: " + str(arr))
        self.arr = arr
        self. index = index
        #print("INDEX: " + str(self.index))

    def evaluate(self):
        return self.arr[self.index].value

class BooleanNode(Node):
    def __init__(self, v1):
        self.v1 = v1

    def evaluate(self):
        return self.v1


class BlockNode(Node):
    def __init__(self, s):
        self.sl = [s]

    def execute(self):
        for statement in self.sl:
            statement.execute()

tokens = (
    'LPAREN', 'RPAREN', 'NUMBER', 'STR',
    'PLUS','MINUS','TIMES','DIVIDE',
    #ADDED TOKENS
    'LSQUARE', 'RSQUARE', 'COMMA',
    'GREATER', 'LESS', 'MOD', 'EXPONENT',
    'GREATEQ', 'LESSEQ', 'EQUALS', 'NOT',
    'AND', 'OR', 'IN', 'NOTEQUALTO',
    'FLOORDIVIDE', 'TRUE', 'FALSE'
    )

# Tokens
#t_LBRACE  = r'\{'
#t_RBRACE  = r'\}'
#t_PRINT    = 'print'
t_LPAREN  = r'\('
t_RPAREN  = r'\)'
#t_SEMI  = r';'
t_PLUS    = r'\+'
t_MINUS   = r'-'
t_TIMES   = r'\*'
t_DIVIDE  = r'/'
#ADDED TOKENS
t_LSQUARE = r'\['
t_RSQUARE = r'\]'
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
t_IN = r'in'
t_NOTEQUALTO = r'<>'
t_FLOORDIVIDE = r'//'
t_TRUE = r'True'
t_FALSE = r'False'

def t_NUMBER(t):
    r'-?\d*(\d\.|\.\d)\d* | \d+'
    try:
        t.value = NumberNode(t.value)
    except ValueError:
        #print("Integer value too large %d", t.value)
        t.value = 0
    return t

def t_STR(t):
    r'\".*?\"|\'.*?\''
    try:
        t.value = StrNode(t.value)
        #t.evaluate()
    except:
        t.value = ""
        #print("String error")

    return t

# Ignored characters
t_ignore = " \t"

def t_error(t):
    print("Syntax error at '%s'" % t.value)

# Build the lexer
import ply.lex as lex
lex.lex()

# Parsing rules
precedence = (
    ('left', 'OR'),
    ('left', 'AND'),
    ('left', 'NOT'),
    ('left', 'GREATER', 'LESS', 'LESSEQ', 'GREATEQ','EQUALS','NOTEQUALTO'),
    ('left', 'IN'),
    ('left','PLUS','MINUS'),
    ('left', 'MOD'),
    ('left', 'FLOORDIVIDE'),
    ('left','TIMES','DIVIDE'),
    ('right', 'EXPONENT'),
    ('left', 'LPAREN', 'RPAREN')
    )

def p_block(t):
    """
    block : LBRACE inblock RBRACE
    """
    t[0] = t[2]

def p_inblock(t):
    """
    inblock : smt inblock
    """
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

def p_print_expression(t):
    '''
    print_expr : expression
    '''
    t[0] = t[1]

def p_print_smt(t):
    """
    print_smt : expression 
    """
    #print("AT PRINTNODE")
    t[0] = PrintNode(t[1])

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
                  | expression AND expression
                  | expression OR expression
                  | expression EQUALS expression
                  | expression IN expression
                  | expression NOTEQUALTO expression
                  | expression FLOORDIVIDE expression
                  '''
    #print("BopNode : arg1 " + str(t[1]) + " arg2 " + str(t[3]))
    t[0] = BopNode(t[2], t[1], t[3])

def p_expression_factor(t):
    '''expression : factor'''
    #print("EXPRESSION : FACTOR")
    t[0] = t[1]

def p_factor_number(t):
    'factor : NUMBER'
    t[0] = t[1]

#ADDED GRAMMAR RULES
def p_expression_boolean(t):
    '''
    expression : boolean
    '''
    t[0] = t[1]

def p_boolean_value(t):
    '''
    boolean : TRUE
            | FALSE
    '''
    #print("BOOLEAN : TRUE | FALSE")
    t[0] = BooleanNode(t[1])

def p_word_plus_word(t):
    '''
    words : word PLUS word
    '''
    t[0] = str(t[1]) + str(t[3])


def p_word_str(t):
    '''
    word : STR

    '''
    t[0] = t[1]

def p_expression_word(t):
    '''
    expression : word
    '''
    t[0] = t[1]

#EXPRESSION PARANTHESES GRAMMAR
def p_expression_paranthesis(t):
    '''
    expression : LPAREN expression RPAREN
    '''
    t[0] = t[2]

def p_array_list(t):
    '''
    array : LSQUARE list RSQUARE
    '''
    #print("FOUND ARRAY")
    t[0] = t[2]

def p_value_in_list(t):
    '''
    invalue : expression IN list_ref
    '''
    #print("EXPR : EXPR IN LIST_REF")
    t[0] = InNode(t[2], t[1], t[3])

def p_list_ref(t):
    '''
    list_ref : list
    '''
    t[0] = t[1]

def p_list_element(t):
    '''
    list : element COMMA list
         | element list
    '''
    #print("LIST : ELEM COMMA LIST")
    t[0] = t[3]
    #print("LIST ITSELF " + str(t[0][0].value))
    t[0].insert(0, t[1])

def p_list_element_terminal(t):
    '''
    list : element
    '''
    if(t[0] is None):
        t[0] = []
        t[0].insert(0, t[1])
    #print("LIST : ELEM")

def p_element(t):
    '''
    element : expression
    '''
    t[0] = t[1]

def p_index(t):
    '''
    index : array LSQUARE expression RSQUARE
    '''
    #print("INDEX : ARRAY ...")
    #print("expression is " + str(t[3]))
    t[0] = IndexNode(t[1], t[3].value)
    #print("here " + str(t[0])

def p_index_expression(t):
    '''
    expression : index
    '''
    t[0] = (t[1])

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
    code = line.strip()
    try:
        lex.input(code)
        while True:
            token = lex.token()
            if not token: break
         #    print(token)
        ast = yacc.parse(code)
        ast.execute()
    except Exception:
        print("ERROR")
#code = "{ print(1+2); print(2*3); }"

#try:
#    lex.input(code)
#    while True:
#        token = lex.token()
#        if not token: break
    #    print(token)
#    ast = yacc.parse(code)
#    ast.execute()
#except Exception:
#    print("ERROR")
