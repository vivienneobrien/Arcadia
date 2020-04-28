package util;


import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import util.SocketThread;
import java.util.Map.Entry;

/**
 * A data structure used to contain all active connections to a given session. 
 */

public class SocketList {
    public static HashMap<String, Socket> map = new HashMap<String, Socket>();
	
	// SocketThread into the map
	public static void addSocket(SocketThread socketThread){
		map.put(socketThread.getUsername(), socketThread.getSocket());
	}
	
	public static void removeUser(String username) {
		map.remove(username);
	}
	
	public static boolean containsKey(String username) {
		return map.containsKey(username);
	}
	
	// Each active connection is identified by a username.
	public static Socket getSocket(String username){
		return map.get(username);
	}
	
	public static ArrayList<String> getUsernames() {
		ArrayList<String> currentlyOnline = new ArrayList<>();
		for (Entry<String, Socket> entry : SocketList.map.entrySet()) {
			currentlyOnline.add(entry.getKey());
		}
		return currentlyOnline;
	}
}