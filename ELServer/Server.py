from DataBase import *
from ELSocket import *

udpSock = ELSocket(21445)
db = DataBase()

while True:
    print 'waiting for connection!'
    data, address = udpSock.receive()
    datas = data.split()
    if datas[0] == 'register':
        feedInfo = db.regitser(datas[1:])
    elif datas[0] == 'login':
        feedInfo = db.login(datas[1:])
    elif datas[0] == 'update':
        feedInfo = db.update(datas[1:])
    elif datas[0] == 'topUp':
        feedInfo = db.topUp(datas[1:])
    else:
        feedInfo = "wrong message"
    udpSock.sendto(feedInfo, address)
    print data
    try:
        log = open('log.txt', 'a')
        log.write(str(address) + ' ' + data + ' ' +  feedInfo + '\n')
    except Exception as e:
        print 'log Error!'
        print e
    finally:
        log.close()

