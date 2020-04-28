package util;

import java.net.Socket;
public class SocketThread {
	private Socket socket;
	private String username;

	public SocketThread() {
	}

	public SocketThread(Socket socket, String username) {
		super();
		this.socket = socket;
		this.username = username;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
