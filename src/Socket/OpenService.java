package Socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class OpenService {
	public void startService() {
		try {
			ServerSocket ss = new ServerSocket(8081);
			Socket socket = null;
			// The loop listens for client connections, 
			//instantiating one thread for each client connection
			while (true) {
				socket = ss.accept();
				ServerThread thread = new ServerThread(socket);
				thread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
