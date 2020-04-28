package Socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import util.User;
import Socket.DatabaseProtocols;
import util.CommandTransfer;
import util.SocketList;
import util.SocketThread;
import java.util.Map.Entry;

/**
 *  Class represents a single connection to the server.
 */
public class ServerThread extends Thread {
	private Socket socket;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		
		/**
		 * Some of these conditionals are possibly redundant.
		 */
		// Thread is constantly listening for activity from the client.
		while (socket != null) {
			try {
				ois = new ObjectInputStream(socket.getInputStream());
				
				// Process the information sent by the client
				CommandTransfer msg = (CommandTransfer) ois.readObject();
				msg = execute(msg);
				
				if ("connectionRequest".equals(msg.getCmd())) {
					if (msg.isFlag()) {
						oos = new ObjectOutputStream(socket.getOutputStream());
						oos.writeObject(msg);
						msg.setCmd("connectionConfirmed");
						oos = new ObjectOutputStream(
								SocketList.getSocket(msg.getReceiver()).getOutputStream());
					} else {
						msg.setResult(msg.getReceiver() + " is currently offline...");
					}

				}
				
				if ("getOnlineUsers".equals(msg.getCmd())) {
					oos = new ObjectOutputStream(socket.getOutputStream());
				}
				
				if ("message".equals(msg.getCmd())) {
					if (msg.isFlag()) {
						oos = new ObjectOutputStream(
								SocketList.getSocket(msg.getReceiver()).getOutputStream());
						        
					} else {
						oos = new ObjectOutputStream(socket.getOutputStream());
						System.out.println("user offline");
					}
				}

				if ("login".equals(msg.getCmd())) {
					oos = new ObjectOutputStream(socket.getOutputStream());
				}
				if ("checkregister".equals(msg.getCmd())) {
					//System.out.println("Verify Successfully ");
					oos = new ObjectOutputStream(socket.getOutputStream());
				}
				if ("register".equals(msg.getCmd())) {
					System.out.println("Registered Successfully");
					oos = new ObjectOutputStream(socket.getOutputStream());
				}
				
				if ("gameoperate".equals(msg.getCmd())) {
					if (msg.isFlag()) {
						oos = new ObjectOutputStream(SocketList.getSocket(msg.getReceiver()).getOutputStream());}
					else {
						oos = new ObjectOutputStream(socket.getOutputStream());
					}
				}
				
//				if ("loggingOff".equals(msg.getCmd())) {
//					if (msg.isFlag()) {
//						oos = new ObjectOutputStream(socket.getOutputStream());
//					}
//				}
				
				oos.writeObject(msg);
			} catch (IOException e) {
				socket = null;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}

	}


	private CommandTransfer execute(CommandTransfer msg) {
		
		if ("connectionRequest".equals(msg.getCmd())) {
			if (SocketList.containsKey(msg.getReceiver())) {
				msg.setFlag(true);
			} else {
				msg.setFlag(false);
			}
		}
		
		if ("getOnlineUsers".equals(msg.getCmd())) {
			msg.setData(SocketList.getUsernames());
		}
		
		if ("message".equals(msg.getCmd())) {
			if (SocketList.containsKey(msg.getReceiver())){
				msg.setFlag(true);
				msg.setResult("Connected with " + msg.getReceiver());
			} else {
				msg.setFlag(false);
				msg.setResult("Couldn't connect with " + msg.getReceiver());
			}
		}
		
		// If it's a registration verification request
		if ("checkregister".equals(msg.getCmd())) {
			DatabaseProtocols userService = new DatabaseProtocols();
			User user = (User) msg.getData();
			userService.checkregistUser(user);
			msg.setFlag(userService.checkregistUser(user));
		
			if(msg.isFlag()) {
				msg.setResult("The user already exists!");
			} else {
				System.out.println("Registration approved!");
			}
		}	
		// If it is a registration request
		if ("register".equals(msg.getCmd())) {
			DatabaseProtocols userService = new DatabaseProtocols();
			User user = (User) msg.getData();
			userService.registUser(user);
			msg.setFlag(true);
			msg.setResult("Registration successful");
		}
		
		// If it's a login request
		if ("login".equals(msg.getCmd())) {
			DatabaseProtocols userService = new DatabaseProtocols();
			User user = (User) msg.getData();
			msg.setFlag(userService.checkUser(user));
			/*
			 * If the login is successful, add the client to the map set 
			 * of active users on the server.
			 */
			if (msg.isFlag()) {
				// Add this thread to the map collection where the connection was successful
				SocketThread socketThread = new SocketThread();
				socketThread.setUsername(msg.getSender());
				socketThread.setSocket(socket);
				SocketList.addSocket(socketThread);
				msg.setResult("login successful");
			} else {
				msg.setResult("Account password input error!");
			}
		}
		
		if ("gameoperate".equals(msg.getCmd())) {
			if (SocketList.getSocket(msg.getReceiver()) != null) {
				msg.setFlag(true);
			} else {
				msg.setFlag(false);
				msg.setResult("another player not online");
			}
		}
		
		if ("loggingOff".equals(msg.getCmd())) {
			SocketList.removeUser(msg.getSender());
			msg.setFlag(SocketList.containsKey(msg.getSender()));
		}

		return msg;
	}
}
