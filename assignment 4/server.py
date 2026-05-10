import socket
import datetime
import threading
import time

client_data = {}
clients = []


def startReceivingClockTime(conn, addr):
    while True:
        try:
            clock_time = conn.recv(1024).decode()
            clock_time = datetime.datetime.fromisoformat(clock_time)
            client_data[conn] = clock_time
            print("Client Data updated with:", addr)

        except:
            clients.remove(conn)
            break


def synchronizeClocks():
    while True:
        if len(client_data) > 0:
            avg = sum([t.timestamp() for t in client_data.values()]) / len(client_data)
            avg_time = datetime.datetime.fromtimestamp(avg)

            for conn in client_data:
                conn.send(str(avg_time).encode())

            print("Recent time sent successfully")

        time.sleep(5)


server = socket.socket()
server.bind(("localhost", 9999))
server.listen(5)

print("Server started...")

threading.Thread(target=synchronizeClocks).start()

while True:
    conn, addr = server.accept()

    print("New synchronization cycle started.")

    clients.append(conn)

    threading.Thread(
        target=startReceivingClockTime,
        args=(conn, addr)
    ).start()
