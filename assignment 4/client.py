import socket 
import datetime 
import time
client = socket.socket()
client.connect(("localhost", 9999))
while True:
    now = datetime.datetime.now()
    client.send(str(now).encode())
    updated_time = client.recv(1024).decode()
    print("Synchronized time at the client is:", updated_time)
    time.sleep(5)
