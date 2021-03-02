package it.polimi.deib.pf.es02.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class FirstSocketServer {
	
    public static void main(String[] args) throws IOException, InterruptedException 
    {
        try (ServerSocket listener = new ServerSocket(59090)) 
        {
            System.out.println("The date server is running...");
            while (true) //keep answering requests
            {
            	System.out.println("Server: start listening");
                try (Socket socket = listener.accept())
                {
                	System.out.println("start wait");
                	TimeUnit.SECONDS.sleep(20);
                	
                	PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println(new Date().toString());
                	//out.println("Hello client, I'm the server!");
                	System.out.println("Data sent");
                }
            }
        }
    }

}
