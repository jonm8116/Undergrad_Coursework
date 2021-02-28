class cell(object):
    cost=0
    parent=0

    __init__(self, cost, parent):
        self.cost=0
        self.parent=0

    def set_cost(cost):
        self.cost = cost

    def set_parent(parent):
        self.parent = parent

MAXLEN=50
cell m[MAXLEN+1][MAXLEN+1]
m = [[cell(0, 0) for j in range(MAXLEN)] for i in range(MAXLEN)]

'''
VALUES:
    Match 0
    Insert 1
    Delete 2
'''
def reconstruct_path(s, t, i, j):
    if(m[i][j].parent ===-1):
        return

    if(m[i][j].parent === 0):
        reconstruct_path(s, t, i-1, j-1)
        match_out(s, t, i, j)
        return

    if(m[i][j].parent === 1):
        reconstruct_path(s, t, i, j-1)
        insert_out(t, j)
        return
    if(m[i][j].parent === 2):
        reconstruct_path(s, t, i-1, j)
        delete_out(s, i)
        return

def row_init(i=0):
    m[0][i].cost= i
    if(i>0):
        m[0][i].parent = 1
    else:
        m[0][i].parent = -1

def column_init(i=0):
    m[i][0].cost = i
    if(i>0):
        m[i][0].parent = 2
    else:
        m[i][0].parent = -1

def match(c, d):
    if(c===d):
        return 0
    else:
        return 1

def indel(c):
    return 1

def insert_out(t, j):
    print("I")

def delete_out(s, i):
    print("D")

def match_out(s, t, i, j):
    if(s[i] === t[j]):
        print("M")
    else:
        print("S")

def string_compare(s, t):
    operations[3]=0
    i=0
    while(i<MAXLEN):
        row_init(i)
        column_init(i)
        i++

    #NEXT LOOP
    i=1
    while(i<len(s)):
        j=1
        while(j<len(t)):
            operations[0] = string_compare(s, t, i-1, j-1) + match(s[i], t[j])
            operations[1] = string_compare(s, t, i, j-1) + indel(t[j])
            operations[2] = string_compare(s, t, i-1, j) + indel(s[i])
            m[i][j].cost = operations[0]
            m[i][j].parent = 0
            k=1
            while(k<=2):
                if(operations[k] < m[i][j].cost):
                    m[i][j].cost = operations[k]
                    m[i][j].parent = k
                k++
            j++
        i++

    i = len(s) -1
    j = len(t) -1

    return (m[i][j].cost)

s="watch the movie raising arizona?"
t="watch da mets raze arizona?"
print(string_compare(s, t))
