package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Socket.OpenService;
import util.SwingUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class StartServer extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JLabel background,logo;
	private JButton btnStartServer, btnEndServer;

	//---------------------------------------
	// Creates a window for server activation
	//---------------------------------------
	public StartServer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		logo = new JLabel("");
		logo.setIcon(SwingUtil.createAutoAdjustIcon("src/image/logof1.png", true));
		logo.setBounds(141, 35, 123, 60);
		contentPane.add(logo);
		
		btnStartServer = new JButton("Start Server");
		btnStartServer.setBounds(38, 140, 132, 42);
		contentPane.add(btnStartServer);
		btnStartServer.addActionListener(this);
		
		btnEndServer = new JButton("End Server");
		btnEndServer.setBounds(214, 140, 132, 42);
		contentPane.add(btnEndServer);
		btnEndServer.addActionListener(this);
		
		background = new JLabel("");
		background.setIcon(SwingUtil.createAutoAdjustIcon("src/image/bg1.jpg", false));
		background.setBounds(0, 0, 384, 261);
		contentPane.add(background);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		// Clicking the 'start server' button initiates
		// a new server Thread
		if (e.getSource() == btnStartServer) {
		    new startServerThread().start();
			JOptionPane.showMessageDialog(null, "Server started successfully !!!");
			return;
		}
		if (e.getSource() == btnEndServer) {
			System.exit(0);
		}
	}

	/**
	 * Nested class which initiates a server thread.
	 */
	class startServerThread extends Thread {
		@Override
		public void run() {
			OpenService s = new OpenService();
			s.startService();
		}

	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartServer frame = new StartServer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
}
	
	
	
	
	
	

