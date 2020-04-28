package Socket;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import Controller.GameController;
import Controller.GameController2;
import Socket.Client;
import UI.OnlinePanel;
import util.CommandTransfer;


/**
 * �ͻ����߳��ࡣһ������������Ϊ�俪��һ���߳� 
 * I/O�������շ���˷��͵�����
 */
public class GameClientThread extends Thread {
	private Client client;//�ͻ��˶���
	private boolean isOnline = true;
	private OnlinePanel onlinePanel;
	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public GameClientThread(Client client, OnlinePanel onlinePanel) {
		this.client = client;
		this.onlinePanel = onlinePanel;
	}
	
	public GameClientThread(Client client) {
		this.client = client;
	}
	
	public static boolean isNum(String str){
        return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
    }	
	
	@Override
	public void run() {
		while (isOnline) {
			//I/O����  ���շ���˷��͵�����
			CommandTransfer msg = client.getData();
			if (msg != null) {
				/*
				 * �������˴������ݳɹ���������Ϣ
				 * ���򵯳��Է������ߵĶԻ���
				 */
				if (msg.isFlag()) {
					String mess = (String) msg.getData();
					if(isNum(mess)) {
	                    int color=Integer.parseInt(mess);
	                    if(GameController2.GameController2.getCurShape2()==null){
	                    	GameController2.GameController2.setCurShape2(color);
	                    }else{
	                    	GameController2.GameController2.setCurShape2(GameController2.GameController2.getNextShape2().color);
	                    	GameController2.GameController2.setNextShape2(color);
	                    }
	                }
	                switch (mess){
	                    case "up":
	                    	GameController2.GameController2.rectUp();
	                        break;
	                    case "down":
	                    	GameController2.GameController2.rectDown();
	                        break;
	                    case "left":
	                    	GameController2.GameController2.rectLeft();
	                        break;
	                    case "right":
	                    	GameController2.GameController2.rectRight();
	                        break;
	                    case "isput":
	                    	GameController2.GameController2.isPut();
	                        break;
	                    case "ispop":
	                    	GameController2.GameController2.ispop();
	                        break;
	                    case "gameover":
	                    	GameController2.GameController2.gameover();
	                        break;
	                    case "keyPause":
	                        GameController.Controller.pause();
	                        break;
	                    case "keyResume":
	                        GameController.Controller.resume();
	                        break;
	                }
					
						
	
				} else {
				//	GameController.Controller.pause();
					JOptionPane.showMessageDialog(onlinePanel, msg.getResult());
					
				}

			}
		}
	}
}