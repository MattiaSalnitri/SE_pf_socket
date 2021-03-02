package it.polimi.deib.pf.es02.socket.morra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class MorraServer {

	
	private Outcome choiceSubmitted(Choice userChoice){
		Choice computerChoice = Choice.getRandomChoice(new Random());
		//System.out.println("Ho scelto " + computerChoice + "!");
		return userChoice.resultAgainst(computerChoice);
	}
	
	private void gameLogic() throws IOException 
	{
        try (ServerSocket listener = new ServerSocket(59090)) 
        {
            System.out.println("The Morra server is up and running...");
            
        	System.out.println("Server: start listening");
            try (Socket socket = listener.accept())//accepts connections ; try with resources
            {

            	//set up a output stream to client
            	PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println("Inizia gioco, scegli un opzione tra CARTA, SASSO e FORBICE");
                
                for (int i = 0; i<10; i++)
                {
                	
                	//set up an in put stream from client
	                InputStream input = socket.getInputStream();
	                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
	                
	                //wait and read choice of user
	    			String line = reader.readLine();    
	    			
	    			//parse the result 
	    			try{
	    				Choice choice = Choice.parseInput(line);
	    				//System.out.println("Hai scelto " + choice + "!");
	    				
	    				//send output 
	    				out.println(choiceSubmitted(choice));
	    			}
	    			catch(IllegalArgumentException e)
	    			{
	    				out.println("Scelta non valida!");
	    			}
                }
            }
            finally {
            	//remember to close the stream
				listener.close();
			}
            
            
        }
	}
	
	
    public static void main(String[] args) throws IOException, InterruptedException 
    {
    	MorraServer ms = new MorraServer();
    	ms.gameLogic();

    }

}
