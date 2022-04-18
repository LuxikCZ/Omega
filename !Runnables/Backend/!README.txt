Meteo Backend v 1.0
-------------------
- This is a Java backend to the TypeScript application

REQUIREMENTS
------------
OS: Linux or MS Windows (not tested on MAC)
Java JRE 1.8 or Higher

CONFIGURATION
-------------
- Before trying to start the backend application, you need to configure it for successfull connection to the MySQL database
- To start configuring, open the "config" folder and the "application.properties" file located inside it
- You can configure:
	- spring.datasource.url=jdbc:mysql://<DATABASE_SERVER_IP_ADDRESS>:<DATABASE_SERVER_PORT>/<DATABASE_NAME>?useSSL=false
	- spring.datasource.username=<DATABASE_SERVER_USERNAME>
	- spring.datasource.password=<DATABASE_SERVER_PASSWORD>
	!!Leave the other configuration file properties as they are, otherwise the application will not work!!

STARTUP
-------
- To run this application, you first need to have a MySQL server started on you machine, and the provided database imported
	- Optionaly you can have the Listener and Simulator run in the background to have some active data
- After importing the database, and optionally running the Listener and Simulator apps, you can start the Backend by using the command prompt command:
	- java -jar spring-Meteo-backend-v1.0.jar





CREDITS
-------
© Jakub Titěra 2022