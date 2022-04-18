Listener v 1.2
--------------
- This application is a TCP based listener for a SENCOR meteo station

REQUIREMENTS
------------
OS: Linux or MS Windows (not tested on MAC)
Java JRE 1.8 or Higher

STARTUP
-------
- To run this application you first need to have a MySQL server with imported database from the ../SQL folder
- After importing the database, you need to configure this application:
	- meteoListener.conf = configure on which address and port this application runs
	- meteoDatabase.conf = configure MySQL database server details for sucessfull connection
- After configuring just run the application using command prompt:
	- java -jar Listener.jar

RUNTIME
-------
- If the application is not showing any errors, it has loaded it's config and started correctly
- Now you need to configure your SENCOR meteo station (if you have one)
	- If you don't have a SENCOR meteo station you can use the ../Simulator/meteo_simulator.py program to simulate it





CREDITS
-------
© Jakub Titěra 2022