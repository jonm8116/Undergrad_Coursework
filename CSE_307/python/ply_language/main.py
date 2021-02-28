#Name: Jonathan Mathai Id: 110320715

#GLOBAL DICTIONARY FOR VARIABLES
globaldict = {}

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
        #print(BOPNODE INIT")
        #print("bopnode op is" + str(op) + " v1 " + str(v1) + " v2 "+str(v2.evaluate()))

    def evaluate(self):
        #print("bopnode evaluate method")
        if(type(self.v1) is StrNode and type(self.v2) is StrNode and self.op
            =='+'):
            #print(v1 value : " + self.v1.value)
            #print(v2 value : " + self.v2.value)
            temp = self.v1.value + self.v2.value
            newNode = StrNode(temp)
            return newNode.value

        if (self.op == '+'):
            #print(self.op == + ")
            if(type(self.v1) is str):
                if(not self.v1.isdigit()):
                    self.v1 = globaldict[str(self.v1)]
                    #print(self.v1 is " + str(self.v1))
                    #print(self.v2.isdigit() call "+ str(self.v2))
                    #print(racing here")
            if(type(self.v2) is str):
                if(not self.v2.isdigit()):
                    #print(in second if")
                    self.v2 = globaldict[str(self.v2)]

            #print(before result ")
            #ONE MORE CHECK
            #print(self.v1.evaluate() -> " + str(self.v1.evaluate()))
            #print(self.v2.evaluate() -> " + str(self.v2.evaluate()))
            result = self.v1.evaluate() + self.v2.evaluate()
            #print(this is result " + str(result))
            return result

        elif (self.op == '-'):
            return self.v1.evaluate() - self.v2.evaluate()
        elif (self.op == '*'):
            return self.v1.evaluate() * self.v2.evaluate()
        elif (self.op == '/'):
            return self.v1.evaluate() / self.v2.evaluate()
        elif(self.op == '>'):
            #print("self.op -> " + str(self.op))
            #print("self.v1 -> " + str(globaldict[str(self.v1)]))
            #print("self.v2 -> " + str(globaldict[str(self.v2)]))
            #print("self.v1 -> " + str(self.v1) + " self.v2 -> " + str(self.v2.evaluate()))
            temp = self.v1.evaluate() > self.v2.evaluate()
            #print(finised with value " + str(temp))
            return temp


        elif(self.op == '<'):
            return self.v1.evaluate() < self.v2.evaluate()
        elif(self.op == '%'):
            return self.v1.evaluate() % self.v2.evaluate()
        elif(self.op == '**'):
            #print(EXPONENT DISCOVERED")
            #print(v1 " + str(self.v1.evaluate()))
            #print(v2 " + str(self.v2.evaluate()))
            arg1 = self.v1.evaluate()
            arg2 = self.v2.evaluate()
            #print(exp " + str(2**2))
            #print(temp " + str(arg1**arg2))
            temp = arg1**arg2
            #print(temp " + str(temp))
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
            #print("OR STATEMENT")
            #print("self.v1-> " + str(self.v1))
            #print("self.v2-> " + str(self.v2))
            return self.v1.evaluate() or self.v2.evaluate()
        elif(self.op=='<>'):
            return self.v1.evaluate() != self.v2.evaluate()
        elif(self.op=='//'):
            temp = self.v1.evaluate() / self.v2.evaluate()
            return int(temp)

        elif(self.op == 'in'):
            if(type(self.v1) is StrNode and type(self.v2) is StrNode):
                #print(IN OPERATOR")
                #print(v1 is " + self.v1.value + " v2 is " + self.v2.value)

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

class listNode(Node):
    def __init__(self, arr):
        #print(ARR: " + str(arr))
        self.arr = arr

    def evaluate(self):
        return self.arr

class PrintNode(Node):
    def __init__(self, v):
        self.value = v

    def execute(self):
        #print("execute printnode")
        #print(var value -> " + str(self.value.name))
        print(self.value.evaluate())

class StrNode(Node):
    def __init__(self, v):
        temp = str(v).replace("'","").replace('"','')
        self.value = temp
        #print(self.value -> " + self.value)

    def evaluate(self):
        return self.value

class IndexNode(Node):
    def __init__(self, arr, index):
        #print(ARR: " + str(arr))
        self.arr = arr
        self. index = index
        #print(INDEX: " + str(self.index))

    def evaluate(self):
        return self.arr.evaluate()[self.index.evaluate()]

class BooleanNode(Node):
    def __init__(self, v1):
        self.v1 = v1

    def evaluate(self):
        return self.v1

class VariableNode(Node):
    def __init__(self, name, value):
        #print("Variable Node initialize method, name-> " + str(name))
        self.name = str(name)
        self.value = value
        #print("var node value " + str(self.value))

    def evaluate(self):
        #print("Variable Node evaluate method")
        #print("globaldict is -> "+ str(globaldict))
        #print("self.name->" + str(globaldict[self.name]))
        return globaldict[self.name]

    def execute(self):
        #print("Variable Node execute method")
        globaldict[self.name] = self.value.evaluate()

class ifNode(Node):
    def __init__(self, booleanexpression, block):
        self.boolexpr = booleanexpression
        self.block = block
        #print("IFNODE initialize")

    def execute(self):
        #print("IFNODE EXECUTE METHOD")
        if(self.boolexpr.evaluate()):
            self.block.execute()

class ifElseNode(Node):
    def __init__(self, ifcond, ifblock, elseblock):
        self.ifcond = ifcond
        self.ifblock = ifblock
        self.elseblock = elseblock
        #print("Initialize ifElseNode")
        #print(ifcond ->" + str(self.ifcond.evaluate()))
        # print("ifblock ->" + str(self.ifblock.execute()))
        # print("elseblock ->" + str(self.elseblock))

    def execute(self):
        #print("ifElseNode execute method")
        if(self.ifcond.evaluate()):
            self.ifblock.execute()
        else:
            self.elseblock.execute()

class whileNode(Node):
    def __init__(self, cond, block):
        self.cond = cond
        self.block = block

    def execute(self):
        while(self.cond.evaluate()):
            #print(self.op " + str(self.cond.op) + " self.v1 " + str(self.cond.v1) + " self.v2 " + str(self.cond.v2))
            self.block.execute()

class BlockNode(Node):
    def __init__(self, s):
        self.sl = [s]

    def execute(self):
        if(self.sl[0] is None):
            return
        for statement in self.sl:
            #print(statements in blocknode " + str(statement))
            statement.execute()
#RESERVED KEY WORDS FAM
reserved = {
    'print' : 'PRINT',
    'if' : 'IF',
    'else' : 'ELSE',
    'while' : 'WHILE',
    'True' : 'TRUE',
    'False' : 'FALSE',
    'and' : 'AND',
    'or' : 'OR',
    # 'not' : 'NOT',
    'in' : 'IN'
}


tokens = [
    'LPAREN', 'RPAREN', 'NUMBER', 'STR',
    'PLUS','MINUS','TIMES','DIVIDE',
    #ADDED TOKENS
    'LSQUARE', 'RSQUARE', 'COMMA',
    'GREATER', 'LESS', 'MOD', 'EXPONENT',
    'GREATEQ', 'LESSEQ', 'EQUALS',
    'NOTEQUALTO','FLOORDIVIDE', 'VAR',
    'VARASSIGN', 'LBRACE', 'RBRACE',
    'SEMI'
    ] + list(reserved.values())

# Tokens
t_LBRACE  = r'\{'
t_RBRACE  = r'\}'
#t_PRINT     = r'print'
t_LPAREN  = r'\('
t_RPAREN  = r'\)'
t_SEMI  = r';'
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
t_NOTEQUALTO = r'<>'
t_FLOORDIVIDE = r'//'
#HW5 tokens
t_VARASSIGN = r'='

#RESERVED WORD TOKENS
def t_VAR(t):
    r'[a-zA-Z][a-zA-Z0-9_]*'
    #print("TOKEN VALUE " + t.value)
    #print(str(list(reserved.keys())))
    #if(t.value not in list(reserved.keys())):
    t.type = reserved.get(t.value, 'VAR')
    t.value = VariableNode(t.value, None)
    return t

#RESERVED WORD TOKENS

def t_NUMBER(t):
    r'-?\d*(\d\.|\.\d)\d* | \d+'
    try:
        t.value = NumberNode(t.value)
    except ValueError:
        #print(Integer value too large %d", t.value)
        t.value = 0
    return t

def t_STR(t):
    r'\".*?\"|\'.*?\''
    try:
        t.value = StrNode(t.value)
        #t.evaluate()
    except:
        t.value = ""
        #print(String error")

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
    # ('left', 'NOT'),
    ('left', 'GREATER', 'LESS', 'LESSEQ', 'GREATEQ','EQUALS','NOTEQUALTO'),
    ('left', 'IN'),
    ('left','PLUS','MINUS'),
    ('left', 'MOD'),
    ('left', 'FLOORDIVIDE'),
    ('left','TIMES','DIVIDE'),
    ('right', 'EXPONENT'),
    ('left', 'LPAREN', 'RPAREN')
    )

#DEFINE EMPTY GRAMMAR RULES
# def p_empty(t):
#     '''empty:'''
#     t[0] = BlockNode(None)

def p_block(t):
    """
    block : LBRACE inblock RBRACE
    """
    #print(block : lbrace inblock rbrace")
    t[0] = t[2]

def p_block_empty(t):
    '''
    block : LBRACE RBRACE
    '''
    t[0] = BlockNode(None)

def p_inblock(t):
    """
    inblock : smt inblock
            | block inblock
    """
    #print(inblock : smt inblock")
    #print('smt ->' +str(t[1]) + " inblock(t[2]) ->"+str(t[2]))
    t[0] = t[2]
    t[0].sl.insert(0,t[1])
    #print(t[1] is " + str(t[1]))
    #print(inblock is " + str(t[0].sl))

def p_inblock2(t):
    """
    inblock : smt
            | block
    """
    #print(inblock : smt")
    #print('smt ->' +str(t[1]) + " inblock(t[2]) ->"+str(t[0]))
    #print(")
    t[0] = BlockNode(t[1])
    #print(t[0].sl " + str(t[0].sl))

def p_smt(t):
    """
    smt : print_smt
    """
    #print(smt : print_smt")
    t[0] = t[1]

def p_smt_assign(t):
    """
    smt : assign
    """
    #print(smt : assign")
    t[0] = t[1]

def p_smt_boolean(t):
    """
    smt : boolean
    """
    #print(smt : boolean")
    t[0] = t[1]

def p_print_smt(t):
    """
    print_smt : PRINT LPAREN expression RPAREN SEMI
    """
    #print("print_smt : PRINT lparen expr rparen semi")
    #print("var is -> " + str(t[3]))
    t[0] = PrintNode(t[3])

def p_print_smt_var(t):
    '''
    print_smt : PRINT LPAREN VAR RPAREN SEMI
    '''
    #print(print_smt : PRINT LPAREN VAR RPAREN SEMI")
    #print(var is -> " + str(t[3]))
    #variable = globaldict[str(t[3])]
    #print(variable is " + str(globaldict))
    t[0] = PrintNode(t[3])

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
     # | VAR PLUS expression
     # | expression PLUS VAR
     # | VAR PLUS VAR
     # | VAR GREATER expression
     # | VAR GREATEQ expression
     # | expression GREATER VAR
     # | expression GREATEQ VAR
     # | VAR GREATER VAR
     # | VAR GREATEQ VAR
     # | VAR LESS expression
     # | VAR LESSEQ expression
     # | VAR LESS VAR
     # | VAR LESSEQ VAR
     # | expression LESS VAR
     # | expression LESSEQ VAR
     # | VAR EQUALS expression
     # | VAR EQUALS VAR
     # | expression EQUALS VAR
    #print("BopNode : arg1 " + str(t[1]) + " arg2 " + str(t[3]) + " op " + str(t[2]))
    #print("bopnode op type -> " + str(type(t[2])))
    if(type(t[2]) is VariableNode):
        #print("op value is -> " + str(t[2].name))
        t[2] = t[2].name
    t[0] = BopNode(t[2], t[1], t[3])

def p_expression_factor(t):
    '''expression : factor'''
    #print(EXPRESSION : FACTOR")
    t[0] = t[1]

def p_factor_number(t):
    'factor : NUMBER'
    #print(factor : number")
    t[0] = t[1]

#ADDED GRAMMAR RULES
def p_expression_boolean(t):
    '''
    expression : boolean
    '''
    #print(expr : boolean")
    t[0] = t[1]

def p_boolean_value(t):
    '''
    boolean : TRUE
            | FALSE
    '''
    #print(BOOLEAN : TRUE | FALSE")
    t[0] = BooleanNode(t[1])


def p_word_str(t):
    '''
    word : STR
    '''
    #print(word : str")
    t[0] = t[1]

def p_expression_word(t):
    '''
    expression : word
    '''
    #print(expression : word")
    t[0] = t[1]

#EXPRESSION PARANTHESES GRAMMAR
def p_expression_paranthesis(t):
    '''
    expression : LPAREN expression RPAREN
    '''
    #print(expression : lparen expr rparen")
    t[0] = t[2]

def p_array_list(t):
    '''
    array : LSQUARE list RSQUARE
    '''
    #print(FOUND ARRAY")
    t[0] = t[2]

# def p_value_in_list(t):
#     '''
#     invalue : expression IN list_ref
#     '''
#     #print(EXPR : EXPR IN LIST_REF")
#     t[0] = InNode(t[2], t[1], t[3])
#
# def p_list_ref(t):
#     '''
#     list_ref : list
#     '''
#     t[0] = t[1]

def p_list_element(t):
    '''
    list : element COMMA list
         | element list
    '''
    #print(LIST : ELEM COMMA LIST")
    t[0] = t[3]
    #print(LIST ITSELF " + str(t[0][0].value))
    t[0].insert(0, t[1].evaluate())

def p_list_element_terminal(t):
    '''
    list : element
    '''
    if(t[0] is None):
        t[0] = []
        t[0].insert(0, t[1].evaluate())
    #print(LIST : ELEM")

def p_element(t):
    '''
    element : expression
    '''
    #print(element : expr")
    t[0] = t[1]

def p_index(t):
    '''
    index : array LSQUARE expression RSQUARE
    '''
    #print(INDEX : ARRAY lsquare expr rsquare")
    #print(expression is " + str(t[3]))
    t[0] = IndexNode(t[1], t[3].value)
    #print(here " + str(t[0])

def p_index_expression(t):
    '''
    expression : index
    '''
    #print(expression : index")
    t[0] = (t[1])

#HW5 GRAMMAR RULES ADDED
def p_assignment(t):
    '''
    assign : VAR VARASSIGN expression SEMI
    '''
    #ADD VARNAME, VALUE original is just add value
    #print("assign : VAR VARASSIGN expression SEMI")
    #print(t[3] is " + str(t[3]))
    # if(type(t[3]) is BopNode):
    #     t[0] = VariableNode(t[3].evaluate())
    # else:
    #     t[0] = VariableNode(t[3].value)
    #print("t[1].value is -> " + str(t[1].value))
    t[1] = VariableNode(t[1].name,t[3])
    #print("t[1].value is -> " + str(t[1].value))
    t[0] = t[1]

    #t[1] = VariableNode(t[1],t[3])
    #print("past assignment")
    #print(t[1].evaluate() ->" + str(t[1].evaluate()))
    # globaldict[str(t[1])] = t[0]
    #print(str(globaldict[str(t[1])]))

def p_expression_variable(t):
    '''
    expression : variable
    '''
    #print("expression : variable")
    #print("variable is -> " + str(t[1].name))
    #print("variable.val is -> " + str(t[1].value))
    t[0] = t[1]

def p_variable_to_var(t):
    '''
    variable : VAR
    '''
    #print("variable : VAR")
    #print("VAR is -> " + str(t[1].name))
    t[0] = t[1]

# def p_var_expression(t):
#     '''
#     expression : var
#     '''
#     t[0] = t[1]

def p_assignment_list(t):
    '''
    assign : VAR VARASSIGN array SEMI
    '''
    #ADD VARNAME, VALUE
    #t[0] = VariableNode(t[1].name, t[3])
    #globaldict[str(t[1])] = t[0]
    #print("t[1].value is -> " + str(t[1].value))
    list = listNode(t[3])
    t[1] = VariableNode(t[1].name,list)
    #print("t[1].value is -> " + str(t[1].value))
    t[0] = t[1]

def p_list_eval(t):
    '''
    listeval : VAR LSQUARE expression RSQUARE
    '''
    #rint("listeval : VAR LSQUARE expr RSQUARE")
    # print("t[1].evl ->" +str(t[1].evaluate()) + "[t]3.eve"+str(t[3].evaluate()))
    t[0] = IndexNode(t[1], t[3])
    #print("listeval : VAR LSQUARE expr RSQUARE")


def p_list_eval_expr(t):
    '''
    expression : listeval
    '''
    t[0] = t[1]

def p_if_smt(t):
    '''
    ifsmt : IF LPAREN expression RPAREN block
    '''
    #print("ifsmt : IF LPAREN expr RPAREN block")
    #print("expr -> " + str(t[3]))
    t[0] = ifNode(t[3],t[5])

def p_if_else_smt(t):
    '''
    ifelsesmt : IF LPAREN expression RPAREN block ELSE block
    '''
    #print("ifelsesmt : IF LPAREN expr RPAREN block ELSE block")
    #print("expr -> " + str(t[3]))
    t[0] = ifElseNode(t[3],t[5],t[7])

def p_if_else_smt_block(t):
    '''
    smt : ifelsesmt
    '''
    #print(smt : ifelsesmt")
    t[0] = t[1]

def p_if_smt_block(t):
    '''
    smt : ifsmt
    '''
    #print(EXPR : IFSMT")
    t[0] = t[1]

def p_while_smt(t):
    '''
    whilesmt : WHILE LPAREN expression RPAREN block
    '''
    t[0] = whileNode(t[3],t[5])

def p_while_smt_block(t):
    '''
    smt : whilesmt
    '''
    t[0] = t[1]

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

try:
   lex.input(code)
   while True:
       token = lex.token()
       #print(token)
       if not token:
           break
       #print(token)
   ast = yacc.parse(code)
   ast.execute()
except Exception:
   print("ERROR")
