package luxik.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import luxik.spring.logging.LogWriter;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		try {
			SpringApplication.run(Application.class, args); //start the spring application
		}//end try
		catch(Exception e) {
			LogWriter.WriteToLog(e); //write any exception to log
		}//end catch
	}//end main
}//end class
