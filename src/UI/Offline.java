package UI;
import javax.swing.JFrame;
import Controller.GameController_singleplayer;
import Controller.KeyController2;
import Socket.Client;


public class Offline extends JFrame{
	private Client client;
	private String owner;
	
	public Offline(String owner,Client client){
		this.owner = owner;
		this.client = client;
		setTitle("Tetris-SinglePlayer");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(415,470);
		setVisible(true);
		OfflinePanel offlinePanel=new OfflinePanel();
		setContentPane(offlinePanel);
		GameController_singleplayer.Controller=new GameController_singleplayer(offlinePanel);
		GameController_singleplayer.Controller.gameStart();	
		offlinePanel.setController(GameController_singleplayer.Controller);
		addKeyListener(new KeyController2(GameController_singleplayer.Controller));	
	}
}	
	
	
	
	
	
