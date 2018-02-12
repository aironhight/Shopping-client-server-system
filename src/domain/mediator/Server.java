package domain.mediator;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * The class containing the server and its PORT
 * @version 1.0
 */

public class Server implements Runnable{

	private Model model;
	private final int PORT = 6789;
	
	
	/**
	 * Constructor containing 1 argument
	 * @param model setting the private field
	 */
	public Server(Model model)
	{
		this.model = model;
	}
		
	/**
	 * Run method of a thread later created @ServerMain
	 */
	@Override
	public void run() 
	{
		//Connection socket
		try
		{
			ServerSocket welcomeSocket = new ServerSocket(PORT);
			System.out.println("Starting the server..");
			while(true)
			{
				System.out.println("Server waiting for client");
				Socket socket = welcomeSocket.accept();
				ServerCommunication con = new ServerCommunication(model,socket);
				Thread connection = new Thread(con);
				connection.start();
			}
		}
		catch(IOException e)
		{
			System.out.println("Exception in connection to server: "
		               + e.getMessage());
		}
		
	}
}
