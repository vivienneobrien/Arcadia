package Socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import util.CommandTransfer;

public class Client {
	
	private int port = 8081; 
	
	// is localhost a catch all for current IP address
	private String address = "localhost";
	private Socket socket;

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	// Maybe we want to open the socket in the resource block here?
	public Client() { // client object
		try {
			socket = new Socket(address, port);
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "Server not open");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Server not open");
		}
	}

    //Send data (encapsulated in a commandTransfer object)
	public void sendData(CommandTransfer msg) {
		ObjectOutputStream oos = null;
		try {
			if (socket == null)
				return;
			
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(msg);
		} catch (UnknownHostException e1) {
			JOptionPane.showMessageDialog(null, "Server not open");
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Server not open");
		}
	}

	// Receive data to the server
	public CommandTransfer getData() {
		ObjectInputStream ois = null;
		CommandTransfer msg = null;
		if (socket == null)
			return null;
		try {
			ois = new ObjectInputStream(socket.getInputStream());
			msg = (CommandTransfer) ois.readObject();
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		}
		return msg;
	}

}
