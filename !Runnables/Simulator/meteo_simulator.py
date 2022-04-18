import socket
from random import uniform
from random import randint
from time import sleep

while True:
    try:
        HOST = str(input("Host IP:"))
        PORT = int(input("Host Port:"))
        break
    except:
        print("Please insert valid IP and Port")

# GET /weatherstation/updateweatherstation.php?ID=Pokus&PASSWORD=Zkouska&action=updateraww&realtime=1&rtfreq=5&dateutc=now&
# baromin=29.84&
# tempf=43.7&
# dewptf=32.3&
# humidity=64&
# windspeedmph=2.0&
# windgustmph=2.0&
# winddir=55&
# rainin=0.0&
# dailyrainin=0.0&
# solarradiation=48.58&
# UV=0.0&
# indoortempf=71.7&
# indoorhumidity=52 HTTP/1.1

print("\nMeteo Station simulator started.")
print("Sending data to " + HOST + ":" + str(PORT))

while True:
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
        sock.connect((HOST, PORT))
        baromin = uniform(20.00, 40.00)
        tempf = uniform(10.00, 70.00)
        dewptf = uniform(10.00, 50.00)
        humidity = uniform(50.00, 90.00)
        windspeedmph = uniform(0.00, 8.00)
        windgustmph = uniform(0.00, 8.00)
        winddir = randint(0, 360)
        rainin = uniform(0.00, 30.00)
        dailyrainin = uniform(0.00, 30.00)
        solarradiation = uniform(00.00, 500.00)
        UV = uniform(0.00, 5.00)
        indoortempf = uniform(60.00, 80.00)
        indoorhumidity = uniform(50.00, 80.00)

        output = "GET /weatherstation/updateweatherstation.php?ID=Pokus&PASSWORD=Zkouska&action=updateraww&realtime=1&rtfreq=5&dateutc=now&"
        output += "baromin=" + str(baromin)
        output += "&tempf=" + str(tempf)
        output += "&dewptf=" + str(dewptf)
        output += "&humidity=" + str(humidity)
        output += "&windspeedmph=" + str(windspeedmph)
        output += "&windgustmph=" + str(windgustmph)
        output += "&winddir=" + str(winddir)
        output += "&rainin=" + str(rainin)
        output += "&dailyrainin=" + str(dailyrainin)
        output += "&solarradiation=" + str(solarradiation)
        output += "&UV=" + str(UV)
        output += "&indoortempf=" + str(indoortempf)
        output += "&indoorhumidity=" + str(indoorhumidity)
        output += " HTTP/1.1"

        print("\nData Sent: \n" + output + "\n")
        sock.send(output.encode())

    print("Waiting 15 seconds before sending another.")
    sleep(15)


        