package UI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import Controller.GameController;
import Controller.KeyController;
import Socket.Client;
import Socket.GameClientThread;
import UI.OnlinePanel;
import UI.ChatInterface.MyMouseListener;
import util.CommandTransfer;

import javax.swing.JButton;
import java.awt.BorderLayout;

/**
 * �����б����
 */
public class GameLobby extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JScrollPane jsp;
	private String owner;
	private JTabbedPane jtp;
	private Client client;
	private JButton refreshBtn;
	private ArrayList<String> currentlyOnline;
	private JPanel contentPane, onlineUsers;

public GameLobby(String owner, Client client) {
	this.owner = owner;
	this.client = client;
	currentlyOnline = new ArrayList<>();
	// hardcoded
	currentlyOnline.add("Joe");
	currentlyOnline.add("Boris");
	init();

}

	private void init() {
		setSize(320, 405);
		setLocation(1100, 100);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(null);
		
		onlineUsers = new JPanel();
		onlineUsers.setBackground(Color.WHITE);
		onlineUsers.setBounds(5, 5, 300, 400);
		
		
		int x = 150;
		int y = 20;
		int height = 30;
		int width = 130;
		
		for (int i = 0; i < currentlyOnline.size(); i++) {
			JLabel onlineUser = new JLabel(currentlyOnline.get(i));
			onlineUser.setBounds(x, y + (height * i), width, height);
			onlineUser.setForeground(Color.BLACK);
			onlineUser.setBackground(Color.BLUE);
			onlineUser.addMouseListener(new MyMouseListener());
		    contentPane.add(onlineUser);
		}
		setContentPane(contentPane);
		
		refreshBtn = new JButton("refresh");
		refreshBtn.setBounds(100, 250, 100, 50);
		refreshBtn.addActionListener(this);
		contentPane.add(refreshBtn);
		
	}

	class MyMouseListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
				JLabel label = (JLabel) e.getSource();
				/*
				 * CommandTranser msg = new CommandTranser(); msg.setCmd("Connection");
				 * msg.setData(label.getText()); msg.setReceiver(label.getText());
				 * msg.setSender(label.getText()); // ÊµÀý»¯¿Í»§¶Ë ²¢ÇÒ·¢ËÍÊý¾Ý Õâ¸öclient¿Í»§¶Ë
				 * Ö±µ½½ø³ÌËÀÍö ·ñÔòÒ»Ö±´æÔÚ Client c = new Client(); c.sendData(msg); msg =
				 * c.getData(); if (msg != null)
				 * JOptionPane.showMessageDialog(null,msg.getResult()); else
				 */
				new Online(owner, label.getText(), client);

				// String port2=JOptionPane.showInputDialog("ÇëÊäÈë·¿¼äºÅ:");
				// Client.Init(Integer.parseInt(port2));
				// System.out.println("Á¬½Ó³É¹¦");
				/*
				 * JFrame frame = new JFrame(); OnlinePanel onlinePanel2=new OnlinePanel();
				 * GameController.Controller=new
				 * GameController(Client.getExchangeThread(),onlinePanel2);
				 * GameController.remoteController=new GameController(onlinePanel2);
				 * onlinePanel2.setController(GameController.Controller);
				 * onlinePanel2.setRemoteController(GameController.remoteController);
				 * frame.setContentPane(onlinePanel2); frame.addKeyListener(new
				 * KeyController(GameController.Controller)); // ±ØÐëË¢ÐÂÒ»ÏÂ½çÃæ²Å¿ÉÒÔ,
				 * frame.setTitle("double player"); frame.setSize(760,470);
				 * GameController.Controller.gameStart(); frame.setVisible(true); break;
				 * 
				 */
			}
		}

		// Èç¹ûÊó±ê½øÈëÎÒµÄºÃÓÑÁÐ±í ±³¾°É«±äÉ«
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			JLabel label = (JLabel) e.getSource();
			label.setOpaque(true);
			label.setBackground(new Color(255, 240, 230));
		}

		// Èç¹ûÊó±êÍË³öÎÒµÄºÃÓÑÁÐ±í ±³¾°É«±äÉ«
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			JLabel label = (JLabel) e.getSource();
			label.setOpaque(false);
			label.setBackground(Color.WHITE);
		}

		class LobbyListener extends Thread {

			@SuppressWarnings("unchecked")
			public void run() {
				while (true) {
					CommandTransfer msg = client.getData();
					currentlyOnline = (ArrayList<String>) msg.getData();
					onlineUsers.repaint();
				}
			}
		}
}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == refreshBtn) {
		}
		
	}
}