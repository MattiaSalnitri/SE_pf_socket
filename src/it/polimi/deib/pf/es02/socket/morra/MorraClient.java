package it.polimi.deib.pf.es02.socket.morra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MorraClient {
	
	public static void main(String[] args) throws IOException
	{
		try (Socket socket = new Socket("127.0.0.1", 59090);) //open a socket
		{			
			//receives data
			InputStream input = socket.getInputStream();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			System.out.println("Client: start receiving");
			
			// reads welcome message
			String line = reader.readLine();    
			System.out.println(line);
			
			//send choice to server
			OutputStream output = socket.getOutputStream();
			//wrap the output writer in a printwriter
			PrintWriter writer = new PrintWriter(output, true);
			
			
			//set up input
			Scanner s=new Scanner(System.in);
			
			
			for (int i = 0; i<10; i++)
			{
				writer.println(s.nextLine().trim());
				
				//receive results
				line = reader.readLine();    
				System.out.println(line);
			}
			
			
			//close the socket
			socket.close();
		}
	}

}
