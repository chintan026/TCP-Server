import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	public static void main(String[] args) {
		System.out.println("Main Thread - " + Thread.currentThread().getId());
		ExecutorService executor = Executors.newFixedThreadPool(8); 
		
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		int port = 3000;

		try {
			serverSocket = new ServerSocket(port);
			serverSocket.setReuseAddress(true);

			while (true) {
				clientSocket = serverSocket.accept();
				System.out.println("connected");
				executor.execute(new HandleConnection(clientSocket));
			}

		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
		} finally {
			try {
				if (clientSocket != null) {
					clientSocket.close();
				}
			} catch (IOException e) {
				System.out.println("IOException: " + e.getMessage());
			}
		}
	}
}
