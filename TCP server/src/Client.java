import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args)
	{
		Socket socket = new Socket();
		Scanner sc = new Scanner(System.in);
		try {
			socket.connect(new InetSocketAddress(3000));
			System.out.println("Connected ..");
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
			
			String messageToSend = null,messageToRead = null;
	          while(true)
	          {
	        	  messageToSend = sc.nextLine();
	        	  writer.println(messageToSend);
		          if(messageToSend.equals("exit"))
		        	  break;
		          messageToRead = reader.readLine();
		          System.out.println(messageToRead);
		          
		       }
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			sc.close();
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
