package UI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Map.Entry;
import java.io.*;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import Socket.Client;
import util.CommandTransfer;
import util.SocketList;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JButton;
import java.awt.Font;

public class MainInterface extends JFrame implements ActionListener {	
	
	private JPanel contentPane;
	private String owner;
	private String username;
	private JButton chat, tetris, snake, logOff;
	private JLabel background;
	private JButton simone_says;
	
    // The users current login session
    private Client activeSession;

	public MainInterface(String owner,Client user) {
		this.owner = owner;
		this.activeSession = user;
		

		buildInterface();
		
	}
	/**
	 * Create the frame.
	 */
	public void buildInterface() {
		
		setTitle("Hi, " + owner);
		setSize(1200, 800); // viv changed
		setLocation(1100, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		chat = new JButton("Chat_");
		chat.setFont(new Font("Calibri", Font.BOLD, 24));
		chat.setBackground(Color.BLACK);
		chat.setForeground(Color.WHITE);
		chat.setBounds(978, 413, 117, 51);
		contentPane.add(chat);
		chat.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		chat.setContentAreaFilled(false);
		chat.addActionListener(this);

		JLabel lblUsername = new JLabel();
		lblUsername.setForeground(Color.BLACK);
		lblUsername.setFont(new Font("Calibri", Font.BOLD, 16));
		lblUsername.setBounds(780, 146, 150, 31);
		contentPane.add(lblUsername);

		JLabel arcadia_ = new JLabel("Arcadia_");
		arcadia_.setFont(new Font("Calibri", Font.BOLD, 24));
		arcadia_.setBackground(Color.BLACK);
		arcadia_.setForeground(Color.WHITE);
		arcadia_.setBounds(42, 38, 111, 33);
		contentPane.add(arcadia_);

		tetris = new JButton("tetris");
		tetris.setIcon(new ImageIcon("src/image/tetris.jpg"));
		tetris.setBounds(118, 270, 208, 296);
		tetris.addActionListener(this);
		contentPane.add(tetris);

		simone_says = new JButton("simone_says");
		simone_says.setIcon(new ImageIcon("src/image/simone_says.jpg"));
		simone_says.setBounds(360, 270, 208, 296);
		contentPane.add(simone_says);

		snake = new JButton("snake");
		snake.setIcon(new ImageIcon("src/image/snake.jpg"));
		snake.setBounds(600, 270, 208, 296);
		contentPane.add(snake);

		background = new JLabel("background");
		background.setVerticalAlignment(SwingConstants.TOP);
		background.setForeground(Color.WHITE);
		background.setIcon(new ImageIcon("src/image/last_try.png"));
		background.setBackground(Color.RED);
		background.setBounds(6, 0, 1287, 721);
		contentPane.add(background);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == chat) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						ChatInterface frame = new ChatInterface(owner, activeSession);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		
		if (e.getSource() == tetris) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						StartGame frame = new StartGame(username, activeSession);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		
//		if (e.getSource() == Snake) {
//			
//			//setVisible(false);
//			EventQueue.invokeLater(new Runnable() {
//				@Override
//				public void run() {
//					SnakeGame frame = new SnakeGame();
//				//	frame.setVisible(true);
//				}
//			});
//
//		}
		
		if (e.getSource() == logOff) {
			CommandTransfer msg = new CommandTransfer();
			msg.setCmd("loggingOff");
			msg.setSender(owner);
			activeSession.sendData(msg);
			this.dispose();
		}
		
	}
				
}

