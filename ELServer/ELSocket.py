from socket import *

class ELSocket(socket):
    def __init__(self, port, bufsize=1024, host=''):
        self.host = host
        self.port = port
        self.bufsize = bufsize
        socket.__init__(self, AF_INET, SOCK_DGRAM)
        self.bind((self.host, self.port))

    def receive(self):
        return self.recvfrom(self.bufsize)