import MySQLdb
from Config import *

class DataBase(object):
    def __init__(self):
        self.db = MySQLdb.connect(host=HOST, port=PORT, user=USER, passwd=PASSWROD, db=DB)
        self.cur = self.db.cursor()

    def regitser(self, datas):
        find = "select * from userInfo where uid='%s'" % datas[0]
        count = self.cur.execute(find)
        if count >= 1:
            return "has registered!"
        else:
            registerSqli = "insert into userInfo values ('%s', '%s', '%s', %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)" % (\
                datas[0], datas[1], datas[2], datas[3], datas[4], datas[5], datas[6], datas[7], datas[8], datas[9],\
                datas[10], datas[11], datas[12], datas[13], datas[14], datas[15])
            count = self.cur.execute(registerSqli)
            if count == 1:
                self.db.commit()
                return "success"
            return "fail"

    def update(self, datas):
        updateSqli = "replace into userInfo values ('%s', '%s', '%s', %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)" % (\
            datas[0], datas[1], datas[2], datas[3], datas[4], datas[5], datas[6], datas[7], datas[8], datas[9], \
            datas[10], datas[11], datas[12], datas[13], datas[14], datas[15])
        count = self.cur.execute(updateSqli)
        if count == 1:
            self.db.commit()
            return 'success'
        else:
            return 'fail'

    def login(self, datas):
        hasRegister = "select * from userInfo where uid='%s'" % datas[0]
        count1 = self.cur.execute(hasRegister)
        if count1 <= 0:
            return 'has not registered'
        else:
            wrongPassword = "select * from userInfo where uid='%s' and password='%s'" % (datas[0], datas[1])
            count2 = self.cur.execute(wrongPassword)
            if count2 <= 0:
                return 'wrong password'
            else:
                infos = self.cur.fetchall()
                user = infos[0][0] + " "+ infos[0][1] + ' ' + infos[0][2] + ' ' + str(infos[0][3]) + ' ' + str(infos[0][4]) + ' ' + str(infos[0][5]) + ' ' + str(infos[0][6]) + ' ' + str(infos[0][7]) + ' ' + str(infos[0][8]) + ' ' + str(infos[0][9])\
                    + ' ' + str(infos[0][10]) + ' ' + str(infos[0][11]) + ' ' + str(infos[0][12]) + ' ' + str(infos[0][13]) + ' ' + str(infos[0][14]) + ' ' + str(infos[0][15])
                return user

    def topUp(self, datas):
        pass
