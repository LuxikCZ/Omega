Meteo Simulator v 0.1
---------------------
- This is a small application that generates data that look realistic and sends them in the same format as the SENCOR meteo station

!DISCLAIMER!
- This application DOES NOT measure any real data it just GENERATES them RANDOMLY 

REQUIREMENTS
------------
OS: Linux or MS Windows (not tested on MAC)
Python 3.8 or Higher

STARTUP
-------
- In the command prompt use the following command:
	- py meteo_simulator.py
	- if the above does not work, you either do not have python installed, or you can try the following:
	- python meteo_simulator.py  OR  python3 meteo_simulator.py
- If the command above is successfull, the program asks you to provide a HOST IP address and HOST PORT number
	- Here you provide the values you configured in the meteoListener.conf for the Listener.jar

RUNTIME
-------
- After the startup, the program runs automatically and sends new data every 15 seconds. There is no other user input required.





CREDITS
-------
© Jakub Titěra 2022