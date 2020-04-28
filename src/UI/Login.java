package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import Socket.Client;
import UI.Register;
import util.CommandTransfer;
import util.SwingUtil;
import util.User;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Panel;
import javax.swing.UIManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
/**
 * Create login UI
 */
public class Login extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField text_name;
	private JPasswordField text_pwd;
	private JLabel lblPassword, lblUsername;
	private JButton regist, login, forgot_password;
	private JCheckBox checkBox;
//	private JLabel gradientEclipse;


	//-------------------------------------
	// Creates the frame for the login box. 
	//-------------------------------------
	public Login() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Calibri", Font.BOLD, 30));
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setBackground(Color.BLACK);
		lblLogin.setBounds(56, 66, 144, 67);
		contentPane.add(lblLogin);

		lblUsername = new JLabel("username");
		lblUsername.setForeground(Color.GRAY);
		lblUsername.setFont(new Font("Calibri", Font.BOLD, 26));
		lblUsername.setBounds(60, 141, 227, 33);
		contentPane.add(lblUsername);

		text_name = new JTextField();
		text_name.setFont(new Font("Calibri", Font.BOLD, 18));
		text_name.setForeground(new Color(0, 0, 0));
		text_name.setColumns(10);
		text_name.setBounds(60, 185, 250, 75);
		contentPane.add(text_name);

		lblPassword = new JLabel("password");
		lblPassword.setForeground(new Color(90, 90, 90));
		lblPassword.setFont(new Font("Calibri", Font.BOLD, 26));
		lblPassword.setBounds(60, 273, 227, 33);
		contentPane.add(lblPassword);

		text_pwd = new JPasswordField();
		text_pwd.setFont(new Font("Calibri", Font.BOLD, 18));
		text_pwd.setForeground(Color.GRAY);
		text_pwd.setColumns(10);
		text_pwd.setBounds(60, 310, 250, 75);
		contentPane.add(text_pwd);

		checkBox = new JCheckBox("Keep me signed in");
		checkBox.setBounds(60, 400, 200, 23);
		contentPane.add(checkBox);
		checkBox.setForeground(Color.GRAY);
		checkBox.setFont(new Font("Calibri", Font.BOLD, 14));

		// create an account
		regist = new JButton("Create an account");
		regist.setForeground(Color.GRAY);
		regist.setBackground(Color.BLACK);
		regist.setFont(new Font("Calibri", Font.BOLD, 14));
		regist.setHorizontalAlignment(SwingConstants.LEFT);
		regist.setBounds(70, 439, 179, 33);
		contentPane.add(regist);
		regist.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		regist.setContentAreaFilled(false);
		regist.addActionListener(this);

		// forgot password
		forgot_password = new JButton("Forgot password");
		forgot_password.setForeground(Color.GRAY);
		forgot_password.setBackground(Color.BLACK);
		forgot_password.setFont(new Font("Calibri", Font.BOLD, 14));
		forgot_password.setHorizontalAlignment(SwingConstants.LEFT);
		forgot_password.setBounds(70, 459, 179, 33);
		contentPane.add(forgot_password);
		forgot_password.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		forgot_password.setContentAreaFilled(false);
		forgot_password.addActionListener(this);

		// log in to your account
		login = new JButton("Login");
		login.setBackground(Color.BLACK);
		login.setForeground(Color.GRAY);
		login.setFont(new Font("Calibri", Font.BOLD, 14));
		login.setHorizontalAlignment(SwingConstants.LEFT);

		login.setBounds(70, 479, 179, 33);
		contentPane.add(login);
		login.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		login.setContentAreaFilled(false);
		login.addActionListener(this);



	}
		
	
	/**
	 * Method registers users actions with the interface
	 * and acts accordingly.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		// User clicks the login button.
		if (e.getSource() == login) {
			String username = text_name.getText().trim();
			String password = new String(text_pwd.getPassword()).trim();
			if ("".equals(username) || username == null) {
				JOptionPane.showMessageDialog(null, "Please enter Username����");
				return;
			}
			
			// The following two cases handles login attempts with an empty
			// text field (username or password)
			if ("".equals(password) || password == null) {
				JOptionPane.showMessageDialog(null, "Please enter password����");
				return;
			}
			
			// User details are wrapped in a commandTransfer object
			// object and sent to a server thread for processing.
			User user = new User(username, password);
			CommandTransfer loginAttempt = new CommandTransfer();
			loginAttempt.setCmd("login");
			loginAttempt.setData(user);
			loginAttempt.setReceiver(username);
			loginAttempt.setSender(username);
			
			Client client = new Client();
			client.sendData(loginAttempt);
			loginAttempt = client.getData();
			
			if(loginAttempt != null) {
				if (loginAttempt.isFlag()) {
					this.dispose();
					JOptionPane.showMessageDialog(null,  loginAttempt.getResult());
					
					// User login is successful; opens main interface panel.
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								MainInterface frame = new MainInterface(username, client);
								frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				} else {
					JOptionPane.showMessageDialog(null,  loginAttempt.getResult());
				}
			}
		}

		else if(e.getSource() == regist) {
			this.dispose();
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Register frame = new Register();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
}
