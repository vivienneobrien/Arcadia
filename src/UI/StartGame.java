package UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;


import Controller.GameController;
import Controller.KeyController;
import MusicPlayer.MusicPlayer;
import Socket.Client;
import util.MyButton;
import util.SwingUtil;

import javax.swing.JLabel;


public class StartGame extends JFrame implements ActionListener{
	private JPanel contentPane;
	private JLabel lblbg;
	private MyButton btnOffline,btnOnline;
	private String username;
	private Client client;
	/**
	 * Create the frame.
	 */
	public StartGame(String username,Client client) {
		this.username = username;
		this.client = client;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		MusicPlayer.bgmPlay();
		setTitle("Tetris");	
		setSize(470,410);

		
		//No window stretching
		setResizable(false);
		setLocationRelativeTo(null);		
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnOffline = new MyButton("image/button_clear.png","Single Player",123,50);
		btnOffline.setFont(new Font("Arial", Font.BOLD, 16));
		btnOffline.setForeground(Color.BLACK);
		btnOffline.addActionListener(this);
		btnOffline.setBounds(175, 230, 123, 28);
		contentPane.add(btnOffline);
		
		btnOnline = new MyButton("image/button_clear.png","Double Player",123,50);
		btnOnline.setFont(new Font("Arial", Font.BOLD, 16));
		btnOnline.setForeground(Color.BLACK);
		btnOnline.addActionListener(this);
		btnOnline.setBounds(175, 300, 123, 28);
		contentPane.add(btnOnline);
		
		lblbg = new JLabel("");
		lblbg.setVerticalAlignment(SwingConstants.TOP);
		lblbg.setIcon(SwingUtil.createAutoAdjustIcon("src/image/background3.png", false));
		lblbg.setBounds(0, 0, 470, 410);
		contentPane.add(lblbg);	

		
	}
	

	
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnOffline) {
			setVisible(false);
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Offline frame = new Offline(username, client);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		if (e.getSource() == btnOnline) {
			setVisible(false);
			MusicPlayer.bgmStop();
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						GameLobby frame = new GameLobby(username, client);
						frame.setVisible(true);
						
				//		Online frame = new Online();
					//	frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		
		
		
		
		
		requestFocus();		
	}
	
	/*
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartGame frame = new StartGame(owner, friend,  client);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

}
