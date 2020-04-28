package Socket;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import util.CommandTransfer;

public class MessagesThread extends Thread {

	private Client client;
	private JTextPane chat_txt;

	public MessagesThread(Client client, JTextPane chat_txt) {
		this.client = client;
		this.chat_txt = chat_txt;
	}

	@Override
	public void run() {
		while(true) {
			    CommandTransfer msg = client.getData();
				SimpleDateFormat format = new SimpleDateFormat("dd-M-yyyy hh:mm");
				String dateTime = format.format(new Date());
				String message = "\n\n" + (String) msg.getSender() + " Sent: " + dateTime + "\n\n" 
				                   + (String) msg.getData() + "\n\n";
				StyledDocument doc = chat_txt.getStyledDocument();
				Style style = chat_txt.addStyle("yep", null);
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

