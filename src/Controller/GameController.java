package Controller;

import util.CommandTransfer;
import util.ShapeUnit;
import UI.OnlinePanel;
import Controller.Gamification;
import MusicPlayer.MusicPlayer;
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
public class GameController {

	public static GameController Controller;

	private JPanel panel;
	private Timer timer;
	private boolean isRunning =false;
	private Gamification gf;
	private ShapeUnit curShape;
	private ShapeUnit nextShape;
	private Client client;
	private String owner;
	private String friend;
	
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
						// ����ͣ��Ϸ
							isRunning = false;	
							CommandTransfer msg = new CommandTransfer();
							String op = "gameover";
							msg.setCmd("gameoperate");
							msg.setSender(owner);
							msg.setReceiver(friend);
							msg.setData(op);
							client.sendData(msg);
						
							int myScore = gf.score;
							int remoteScore = GameController2.GameController2.getGf2().score;

							String str = Integer.toString(myScore) + "��" + Integer.toString(remoteScore) + ",";
							if (myScore > remoteScore) {
								// WIN
								JOptionPane.showMessageDialog(panel, str + "��Ӯ��");
							} else if (myScore < remoteScore) {
								// LOSE
								JOptionPane.showMessageDialog(panel, str + "������");
							} else {
								// pingju
								JOptionPane.showMessageDialog(panel, str + "����һ��ƽ��");
							}
						
						return;
					}
					
					Random random = new Random();
					// �Ѿ��������˵���˼
					CommandTransfer msg = new CommandTransfer();
					String op = "isput";
					msg.setCmd("gameoperate");
					msg.setSender(owner);
					msg.setReceiver(friend);
					msg.setData(op);
					client.sendData(msg);
					
					curShape.setColor(0);
					int temp=random.nextInt(7)+1;
					curShape = new ShapeUnit(nextShape.color);
					nextShape = new ShapeUnit(temp);
					
					
					CommandTransfer msg2 = new CommandTransfer();
					String op2 = Integer.toString(temp);
					msg2.setCmd("gameoperate");
					msg2.setSender(owner);
					msg2.setReceiver(friend);
					msg2.setData(op2);
					client.sendData(msg2);
					
					
					if(gf.ispop()){
						// ��ȥһ��
						CommandTransfer msg3 = new CommandTransfer();
						String op3 = "ispop";
						msg3.setCmd("gameoperate");
						msg3.setSender(owner);
						msg3.setReceiver(friend);
						msg3.setData(op3);
						client.sendData(msg3);
					}
                }
				else{
                	curShape.down();
                	CommandTransfer msg = new CommandTransfer();
					String op = "down";
					msg.setCmd("gameoperate");
					msg.setSender(owner);
					msg.setReceiver(friend);
					msg.setData(op);
					client.sendData(msg);
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
	
	
	
	
	public GameController(JPanel panel){
		this.panel=panel;
	}

	public GameController(String owner, String friend,Client client,OnlinePanel panel) {
		this.client = client;
		this.owner = owner;
		this.friend = friend;
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
		CommandTransfer msg = new CommandTransfer();
		String op = "up";
		msg.setCmd("gameoperate");
		msg.setSender(owner);
		msg.setReceiver(friend);
		msg.setData(op);
		client.sendData(msg);		
		panel.repaint();
		
	}

	public void keyDown() {
		if(!isRunning) return;
		if(gf.isput(curShape)) return;		
		curShape.down();			
		CommandTransfer msg = new CommandTransfer();
		String op = "down";
		msg.setCmd("gameoperate");
		msg.setSender(owner);
		msg.setReceiver(friend);
		msg.setData(op);
		client.sendData(msg);		
    	panel.repaint();

	}

	public void keyLeft() {
		if(!isRunning) return;
		if(gf.isleftside(curShape))	return;		
		curShape.left();
		CommandTransfer msg = new CommandTransfer();
		String op = "left";
		msg.setCmd("gameoperate");
		msg.setSender(owner);
		msg.setReceiver(friend);
		msg.setData(op);
		client.sendData(msg);
		panel.repaint();
				
	}

	public void keyRight() {
		if(!isRunning) return;
		if(gf.isrightside(curShape))	return;	
		curShape.right();	
		CommandTransfer msg = new CommandTransfer();
		String op = "right";
		msg.setCmd("gameoperate");
		msg.setSender(owner);
		msg.setReceiver(friend);
		msg.setData(op);
		client.sendData(msg);
		panel.repaint();
				
	}

	public void keyPause()  {
		CommandTransfer msg = new CommandTransfer();
		String op = "pause";
		msg.setCmd("gameoperate");
		msg.setSender(owner);
		msg.setReceiver(friend);
		msg.setData(op);
		client.sendData(msg);
		isRunning =false;		
	}

	public void keyResume() {
		CommandTransfer msg = new CommandTransfer();
		String op = "resume";
		msg.setCmd("gameoperate");
		msg.setSender(owner);
		msg.setReceiver(friend);
		msg.setData(op);
		client.sendData(msg);
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
