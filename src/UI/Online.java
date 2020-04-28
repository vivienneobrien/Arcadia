package UI;
import javax.swing.JFrame;
import Controller.GameController;
import Controller.GameController2;
import Controller.KeyController;
import Socket.Client;
import Socket.GameClientThread;



public class Online extends JFrame{
	
	private Client client;
	private String owner;
	private String friend;
	private GameClientThread thread;
	
	public Online(String owner, String friend, Client client){
		this.owner = owner;
		this.friend = friend;
		this.client = client;
		setTitle(owner + "vs" + friend +" Tetris-Multiplayer");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(760,470);
		setVisible(true);
		OnlinePanel onlinePanel=new OnlinePanel();
		GameController.Controller=new GameController(owner,friend,client,onlinePanel);
		GameController2.GameController2=new GameController2(client,onlinePanel);
		onlinePanel.setController(GameController.Controller);
		onlinePanel.setController2(GameController2.GameController2);
		setContentPane(onlinePanel);
		addKeyListener(new KeyController(GameController.Controller));
		GameController.Controller.gameStart();
		thread = new GameClientThread(client, onlinePanel);
		thread.start();
	}
}	
