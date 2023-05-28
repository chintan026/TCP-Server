import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class HandleConnection implements Runnable {

	private Socket socket;

	HandleConnection(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		System.out.println("Running in Thread - " + Thread.currentThread().getId());
		try {

			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

			String message;
			while ((message = reader.readLine()) != null) {
				writer.println(message);

			}
		} catch (IOException e) {
			System.out.println("connection closed by client !");
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
