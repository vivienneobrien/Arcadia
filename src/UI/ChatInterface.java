package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map.Entry;

import javax.swing.Timer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import Socket.Client;
import Socket.MessagesThread;
import util.CommandTransfer;
import util.SocketList;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class ChatInterface extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private JButton btnSend, btnRefresh;
	private JTextPane messageWindow;
	private JPanel contentPane;
	private JTextArea textInput;
	private String user;
	private Client client;
	private ArrayList<String> recipients;
	private JPanel onlineUsers;
	private ArrayList<String> currentlyOnline;
	private boolean threadFlag;
	private Thread thread;
	
	public void setThreadFlag(boolean threadFlag) {
		this.threadFlag = threadFlag; 
	}
	
	public ChatInterface(String user, Client client) {
		this.user = user;
		this.client = client;
		this.threadFlag = true;
		this.recipients = new ArrayList<>();
		this.currentlyOnline = new ArrayList<>();
		thread = new MessageThread();
		thread.start();
		
		buildInterface();
	}
	
	public void buildInterface() {
		setSize(650, 500);
		//addWindowListener(new MyWindowListener());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		messageWindow = new JTextPane();

		messageWindow.setEditable(false);
		messageWindow.setBounds(10, 10, 375, 250);
		contentPane.add(messageWindow);
		
		JScrollPane chatViewScroll = new JScrollPane(messageWindow, 
	    		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
	    		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		chatViewScroll.setBounds(10, 10, 375, 250);
	    contentPane.add(chatViewScroll);
	
		textInput = new JTextArea();
		textInput.setBounds(10, 300, 375, 100);
		textInput.setRows(1000);
		textInput.setColumns(10);
		textInput.setWrapStyleWord(true);
        textInput.setLineWrap(true);
		btnSend = new JButton("Send");
		btnSend.setBounds(10, 400, 50, 50);
		btnSend.addActionListener(this);
		JPanel input = new JPanel(new FlowLayout());
		input.add(textInput);
		contentPane.add(btnSend);	
		contentPane.add(textInput);
		
	    JScrollPane chatScroll = new JScrollPane(textInput, 
		         JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		    	 JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    chatScroll.setBounds(10, 300, 375, 100);
	    contentPane.add(chatScroll);
	    
	    onlineUsers = new JPanel();
		onlineUsers.setBackground(Color.WHITE);
		onlineUsers.setBorder(new EmptyBorder(20, 20, 20, 20));
		onlineUsers.setBounds(400, 10, 200, 300);
		onlineUsers.setLayout(null);
				
		int x = 80;
		int y = 20;
		int height = 30;
		int width = 130;
		
		for (int i = 0; i < currentlyOnline.size(); i++) {
			JLabel onlineUser = new JLabel(currentlyOnline.get(i));
			onlineUser.setBounds(x, y + (height * i), width, height);
			onlineUser.setForeground(Color.BLUE);
			onlineUser.setBackground(Color.BLUE);
			onlineUser.addMouseListener(new MyMouseListener());
			onlineUsers.add(onlineUser);
		}
		contentPane.add(onlineUsers);
		
		//problem with this JScrollPane appearing...
//		JScrollPane onlineUserScroll = new JScrollPane(onlineUsers, 
//		         JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
//		    	 JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//	    onlineUserScroll.setBounds(400, 10, 200, 300);
//	    contentPane.add(onlineUserScroll);
	    
	    btnRefresh = new JButton("Refresh");
	    btnRefresh.setBounds(400, 350, 100, 50);
		btnRefresh.addActionListener(this);
		contentPane.add(btnRefresh);
	}
	

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSend) {
			SimpleDateFormat format = new SimpleDateFormat("dd-M-yyyy hh:mm");
			String dateTime = format.format(new Date());
			String message = "Sent: " + dateTime + "\n\n" + textInput.getText();
			StyledDocument doc = messageWindow.getStyledDocument();
			Style style = messageWindow.addStyle("yep", null);
			StyleConstants.setForeground(style, Color.BLACK);
			try {
				doc.insertString(doc.getLength(), message, style);
			} catch (BadLocationException e1) {
				System.out.println("Oops something went wrong in the chat interface!");
				e1.printStackTrace();
			}

			for (String friend : recipients) {
				CommandTransfer msg = new CommandTransfer();
				msg.setData(textInput.getText());
				msg.setCmd("message");
				msg.setReceiver(friend);
				msg.setSender(user);
			    client.sendData(msg);
			}
			textInput.setText("");	
		}
		
		if (e.getSource() == btnRefresh) {
			CommandTransfer OnlineUsers = new CommandTransfer();
			OnlineUsers.setCmd("getOnlineUsers");
			client.sendData(OnlineUsers);
		}
		
	}
	
	class MessageThread extends Thread {

		@SuppressWarnings("unchecked") // Precaution for cast to ArrayList
		@Override
		public void run() {
			while (threadFlag) {
				CommandTransfer msg = client.getData();
				if ("connectionRequest".equals(msg.getCmd())) {

					if (msg.isFlag()) {
						recipients.add(msg.getReceiver());
						JOptionPane.showMessageDialog(null, msg.getReceiver() + " was added to" + " the conversation!");
					} else {
						JOptionPane.showMessageDialog(null, msg.getResult());
					}
					
				} else if ("connectionConfirmed".equals(msg.getCmd())) {
					if (msg.isFlag()) {
						recipients.add(msg.getSender());
						JOptionPane.showMessageDialog(null,  msg.getSender() + "wants to say hello!");
					} else {
						JOptionPane.showMessageDialog(null, "Oops");
					}
				
				} else if ("getOnlineUsers".equals(msg.getCmd())) {
					currentlyOnline = (ArrayList<String>)msg.getData();
					onlineUsers.removeAll();
					onlineUsers.validate();
					onlineUsers.repaint();
					
					int x = 80;
					int y = 20;
					int height = 30;
					int width = 130;
					
					for (int i = 0; i < currentlyOnline.size(); i++) {
						JLabel onlineUser = new JLabel(currentlyOnline.get(i));
						onlineUser.setBounds(x, y + (height * i), width, height);
						onlineUser.setForeground(Color.BLUE);
						onlineUser.setBackground(Color.BLUE);
						onlineUser.addMouseListener(new MyMouseListener());
						onlineUsers.add(onlineUser);
					}
					//contentPane.add(onlineUsers);
					
				} else {
					SimpleDateFormat format = new SimpleDateFormat("dd-M-yyyy hh:mm");
					String dateTime = format.format(new Date());
					String message = "\n\n" + (String) msg.getSender() + " Sent: " + dateTime + "\n\n"
							+ (String) msg.getData() + "\n\n";

					StyledDocument doc = messageWindow.getStyledDocument();
					Style style = messageWindow.addStyle("yep", null);
					StyleConstants.setForeground(style, Color.GREEN);
					try {
						doc.insertString(doc.getLength(), message, style);
					} catch (BadLocationException e1) {
						System.out.println("Oops, something went wrong in the thread!");
						e1.printStackTrace();
					}
				}
			}
		}
	}
	
	class MyMouseListener extends MouseAdapter {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
				JLabel label = (JLabel) e.getSource();
				if (recipients.contains(label.getText())) {
					JOptionPane.showMessageDialog(null, "You're already connected with " + label.getText() );
				} else {
					CommandTransfer connectReq = new CommandTransfer();
					connectReq.setCmd("connectionRequest");
					connectReq.setReceiver(label.getText());
					connectReq.setSender(user);
					client.sendData(connectReq);
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			JLabel label = (JLabel) e.getSource();
			label.setOpaque(true);
			label.setBackground(new Color(255, 240, 230));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			JLabel label = (JLabel) e.getSource();
			label.setOpaque(false);
			label.setBackground(Color.WHITE);

		}
	}
	
	public class MyWindowListener implements WindowListener{

		@Override
		public void windowOpened(WindowEvent e) {
		}

		@Override
		public void windowClosing(WindowEvent e) {
			
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

	}
}

	
	

