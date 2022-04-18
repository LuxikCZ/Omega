package tcp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import logging.LogWriter;
/**
 * 
 * @author luxik
 *
 */
public class TCPListener implements Runnable {

	private ServerSocket serverSocket;

	public TCPListener(int port, String ipAddress) throws IOException {
		serverSocket = new ServerSocket(port, 128, InetAddress.getByName(ipAddress));
	}//end method
	
	@Override
	public void run() {
		while (true) {
			Socket clientSocket;
			try {
				clientSocket = serverSocket.accept();
				Runnable connectionHandler = new ConnectionHandler(clientSocket);
				new Thread(connectionHandler).start(); //for each client a new thread is opened
			}//end try
			catch (IOException e){
				LogWriter.WriteToLog(e);
			}//end catch
		}//end while
	}//end method
}//end class
