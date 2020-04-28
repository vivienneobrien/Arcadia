package Controller;

import util.CommandTransfer;
import util.ShapeUnit;
import UI.OnlinePanel;
//import UI.OnlinePanel;
import Controller.Gamification;
import MusicPlayer.MusicPlayer;
import Socket.GameClientThread;
import Socket.Client;
import UI.OfflinePanel;

import javax.swing.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * ��Ϸ���������������������ƶ�����ͣ��Ϸ��
 *
 */
public class GameController_singleplayer {

	public static GameController_singleplayer Controller;

	private JPanel panel;
	private Timer timer;
	private boolean isRunning =false;
	private Gamification gf;
	private ShapeUnit curShape;
	private ShapeUnit nextShape;
	private Client client;
	private String owner;
	private String friend;
	private GameClientThread gameClientThread;
	
	public Gamification getGf() {
		return gf;
	}
	
	public ShapeUnit getCurShape() {
		return curShape;
	}
	
	public void setCurShape(ShapeUnit curShape) {
		this.curShape = curShape;
	}

	public ShapeUnit getNextShape() {
		return nextShape;
	}

	public void setNextShape(ShapeUnit nextShape) {
		this.nextShape = nextShape;
	}

	
	private class GameTask extends TimerTask {
		private int speed = 5;
        public void run() {

			if(!isRunning){
				return ;
			}

			// speed������ʱ��������
        	if(speed <= 0){
				if(gf.isput(curShape)){
					if(gf.gameover()) {
						System.out.println("begin to end game");
						// ����ͣ��Ϸ
							isRunning = false;	
							int myScore = gf.score;
							JOptionPane.showMessageDialog(panel, "game over!"+"Your Score:"+Integer.toString(myScore));
						
						return;
					}
					Random random = new Random();
					// �Ѿ��������˵���˼
				
					
					
					
					curShape.setColor(0);
					int temp=random.nextInt(7)+1;
					curShape = new ShapeUnit(nextShape.color);
					nextShape = new ShapeUnit(temp);

					if(gf.ispop()){
						// ��ȥһ��
						
						
						
					}
                }
				else{
                	curShape.down();
                	
                	
                	
                	
                	
                	
                	
                	
				}
				// ���û�з���������down
                panel.repaint();
                speed=10-gf.level;
        	}
        	else{
				speed--;
			}
        }
    }
	
    /*
	public GameController(GameClientThread thread,OnlinePanel panel) {
		this.gameClientThread=thread;
		this.panel=(OnlinePanel)panel;
	}
	*/
	
	public GameController_singleplayer(JPanel panel){
		this.panel=panel;
	}

	
	public GameController_singleplayer(String owner, String friend,Client client,GameClientThread gameClientThread,OnlinePanel panel) {
		this.client = client;
		this.owner = owner;
		this.friend = friend;
		this.gameClientThread = gameClientThread;
		this.panel=(OnlinePanel)panel;
	}
	
	
	

	/**
	 * ������Ϸ
	 */
	public void gameStart(){

		gf = new Gamification();
		this.curShape = new ShapeUnit(1);
		this.nextShape = new ShapeUnit(2);
		
		isRunning =true;
		timer = new Timer();
		timer.schedule(new GameTask(), 100,30);
	}

	public void keyUp() {
		if(!isRunning) return;
		if(!gf.ifcanChange(curShape)) return;
		curShape.change();		
			
		panel.repaint();
		
	}

	public void keyDown() {
		if(!isRunning) return;
		if(gf.isput(curShape)) return;		
		curShape.down();			
			
    	panel.repaint();

	}

	public void keyLeft() {
		if(!isRunning) return;
		if(gf.isleftside(curShape))	return;		
		curShape.left();
		
		panel.repaint();
				
	}

	public void keyRight() {
		if(!isRunning) return;
		if(gf.isrightside(curShape))	return;	
		curShape.right();	
		
		panel.repaint();
				
	}

	public void keyPause()  {
		
		isRunning =false;		
	}

	public void keyResume() {
	
		isRunning =true;		
	}


	/**
	 * ����Զ�̵���ָͣ��
	 */
	public void pause()  {
		isRunning =false;
	}

	/**
	 * ����Զ�̵Ļָ�ָ��
	 */
	public void resume() {
		isRunning =true;
	}
	
	/**
	 * 
	 * ������һ���Ѿ�gameouver�ˣ����ҲҪͣһ��
	 */
	public int gameover(){
		System.out.print("gameover");
		isRunning=false;
		return gf.score;
	}

	// �Ƿ��������У��������еĻ�����true
	public boolean isRunning() {
		return isRunning;
	}

}
