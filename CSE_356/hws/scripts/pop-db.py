import csv
import MySQLdb
from flask import Flask, request, jsonify
import json

conn = MySQLdb.connect(host="localhost", user="user", passwd="password", db="hw8")
cursor = conn.cursor()

app = Flask(__name__)

@app.route('/hw8')
def getAssist():
    club = request.args.get('club')
    pos = request.args.get('pos')
    print(club)
    print(pos)   
 
    query = 'SELECT player, a, amin, pos, club FROM assists WHERE club=%s and pos=%'
    cursor.execute("SELECT player, a, gs, amin, pos, club, gp FROM assists WHERE club=%s and pos LIKE %s", (club,"%"+pos+"%"))

    

    data = cursor.fetchall()
    maxAssist = 0.0
    maxItem = ''
    count = 0.0
    sumA = 0.0 

    for item in data:

        count +=1.0 
        sumA = sumA + float(item[1])

        if(item[1] > maxAssist):
            maxAssist = item[1]
            maxItem = item
        if(item[1] == maxAssist):
            if(maxAssist == 0):
                name = 'z'
                for elem in data:
                    name = min(name, item[0])
                    if(name == item[0]):
                        maxItem = item                    

                #maxAssist = item[1]
                #maxItem = item          

            print('item')
            print(item)
            print('max item')
            print(maxItem)
            if(item[2] > maxItem[2]):
                maxItem = item
            elif(item[2] == maxItem[2]):
                name = min(item[0],maxItem[0])
                if(name == item[0]):
                    maxItem = item
            else:
                continue

    avg = sumA/count
    print('sumA ' + str(sumA))
    print('count ' + str(count))
    print('avg is ' + str(avg))
    print(maxItem)
    #avg = maxItem[6]/maxItem[1]
    return jsonify(
        club = maxItem[5],
        pos = maxItem[4],
        max_assists = maxItem[1],
        player = maxItem[0],
        avg_assists = avg

    )


    #return response

    #return 'k' 

def loadData():
    csv_data = csv.reader(file('assists.csv'))
    counter=1

    # cursor.execute('INSERT INTO assists(player,club,pos,gp,gs,a,gwa,hma,rda,amin) VALUES (%s, %s, %s, %d, %d, %d, %d, %d, %d, %s)', row)

    # select player, a, amin, pos, club from assists where club='ORL' and pos='M';

    for row in csv_data:
        print(row)
        if(counter == 1 or 'Stat' in row[0]):
            counter+=1 
            continue
        cursor.execute('INSERT INTO assists VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s)', row)
        counter +=1 

    conn.commit()
    cursor.close()

#loadData()

if __name__ == '__main__':
    app.run(port=3000)
