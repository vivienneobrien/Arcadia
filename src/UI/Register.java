package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Socket.Client;
import util.User;
import util.CommandTransfer;
import util.SwingUtil;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Color;

/**
 * Create Account UI
 */
public class Register extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField text_username, text_email, text_last_name, text_first_name, text_biography;
	private JPasswordField passwordField, confirm_password;
	private JLabel lblUsername, lblPassword, lblConfirmPassword, lblEmail;
	private JButton btnRegister;
	private JLabel first_name;
	private JLabel last_name;
	private JLabel biographygraphy;
	private JLabel upload_photo;
	//----------------------------------------------------------------
	// Creates registration frame where the user enters their details:
	// username, password, gender, email, hobby
	// ---------------------------------------------------------------
	public Register() {
		
		// The main window
				setTitle("Register");
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setBounds(100, 100, 400, 600);
				contentPane = new JPanel();
				contentPane.setBackground(Color.BLACK);
				contentPane.setForeground(Color.WHITE);
				contentPane.setBorder(null);
				setContentPane(contentPane);
				contentPane.setLayout(null);

				JLabel lblCreateAccount = new JLabel("Create Account");
				lblCreateAccount.setFont(new Font("Calibri", Font.BOLD, 30));
				lblCreateAccount.setForeground(Color.WHITE);
				lblCreateAccount.setBackground(Color.BLACK);
				lblCreateAccount.setBounds(56, 66, 200, 67);
				contentPane.add(lblCreateAccount);
				
				// blue gradient circle design
				setResizable(false);
				upload_photo = new JLabel("");
				upload_photo.setIcon(new ImageIcon("resources/images/button.png"));
				upload_photo.setBounds(280, 17, 200, 200);
				contentPane.add(upload_photo);

				// first_name
				first_name = new JLabel("first_name");
				first_name.setForeground(Color.GRAY);
				first_name.setFont(new Font("Calibri", Font.BOLD, 20));
				first_name.setBounds(60, 180, 227, 33);
				contentPane.add(first_name);
				
				text_first_name = new JTextField();
				// x axis (left & right), y axis (up and down), length of text field, 20 = width of text field
				text_first_name.setBounds(186, 183, 150, 20);
				contentPane.add(text_first_name);

				// last_name
				last_name = new JLabel("last_name");
				last_name.setForeground(Color.GRAY);
				last_name.setFont(new Font("Calibri", Font.BOLD, 20));
				last_name.setBounds(60, 220, 227, 33);
				contentPane.add(last_name);
				
				text_last_name = new JTextField();
				text_last_name.setBounds(186, 223, 150, 20);
				contentPane.add(text_last_name);

				// username
				lblUsername = new JLabel("username");
				lblUsername.setForeground(Color.GRAY);
				lblUsername.setFont(new Font("Calibri", Font.BOLD, 20));
				lblUsername.setBounds(60, 260, 227, 33);
				contentPane.add(lblUsername);
				
				text_username = new JTextField();
				text_username.setBounds(186, 263, 150, 20);
				contentPane.add(text_username);
				
				// password
				lblPassword = new JLabel("password");
				lblPassword.setForeground(Color.GRAY);
				lblPassword.setFont(new Font("Calibri", Font.BOLD, 20));
				lblPassword.setBounds(60, 300, 227, 33);
				contentPane.add(lblPassword);
				
				passwordField = new JPasswordField();
				passwordField.setBounds(186, 303, 150, 20);
				contentPane.add(passwordField);

				// confirm password
				lblConfirmPassword = new JLabel("confirm_pw");
				lblConfirmPassword.setForeground(Color.GRAY);
				lblConfirmPassword.setFont(new Font("Calibri", Font.BOLD, 20));
				lblConfirmPassword.setBounds(60, 340, 227, 33);
				contentPane.add(lblConfirmPassword);
				
				confirm_password = new JPasswordField();
				confirm_password.setBounds(186, 343, 150, 20);
				contentPane.add(confirm_password);

				// email
				lblEmail = new JLabel("email");
				lblEmail.setForeground(Color.GRAY);
				lblEmail.setFont(new Font("Calibri", Font.BOLD, 20));
				lblEmail.setBounds(60, 380, 200, 33);
				contentPane.add(lblEmail);

				text_email = new JTextField();
				text_email.setColumns(10);
				text_email.setBounds(186, 383, 150, 20);
				contentPane.add(text_email);

				//biography
				biographygraphy = new JLabel("biography");
				biographygraphy.setForeground(Color.GRAY);
				biographygraphy.setFont(new Font("Calibri", Font.BOLD, 20));
				biographygraphy.setBounds(60, 420, 227, 33);
				contentPane.add(biographygraphy);

				text_biography = new JTextField();
				text_biography.setColumns(10);
				text_biography.setBounds(186, 423, 150, 20);
				
				contentPane.add(text_biography);
				
				// register
				btnRegister = new JButton("Register");
				btnRegister.setBackground(Color.BLACK);
				btnRegister.setForeground(Color.GRAY);
				btnRegister.setFont(new Font("Calibri", Font.BOLD, 14));
				btnRegister.setHorizontalAlignment(SwingConstants.LEFT);

				btnRegister.setBounds(60, 490, 179, 33);
				contentPane.add(btnRegister);
				btnRegister.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
				btnRegister.setContentAreaFilled(false);
				btnRegister.addActionListener(this);
				
	}

	/**
	 * Method initiated once the user enters their details
	 * and clicks the registration button.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegister) {
			
			// Gets the input from all the registration fields and saves them
			// to the corresponding variables.
			String first_name = text_first_name.getText().trim();
			String last_name = text_last_name.getText().trim();
			String username = text_username.getText().trim();
			String password = new String(passwordField.getPassword()).trim(); // Trim removed write space
			String confirmpassword = new String(confirm_password.getPassword()).trim();
			String email = text_email.getText().trim();
			String biography = text_biography.getText().trim();

			
			// Warning messages for unfilled text fields.
			if ("".equals(first_name) || first_name == null) {
				JOptionPane.showMessageDialog(null, "Please enter username");
				return;
			}
			
			if ("".equals(last_name) || last_name == null) {
				JOptionPane.showMessageDialog(null, "Please enter username");
				return;
			}
			
			if ("".equals(username) || username == null) {
				JOptionPane.showMessageDialog(null, "Please enter username");
				return;
			}
				
			if ("".equals(password) || password == null) {
				JOptionPane.showMessageDialog(this, "Please enter password", "Prompt", JOptionPane.WARNING_MESSAGE);
				return;
			}
			if ("".equals(confirmpassword) || confirmpassword == null) {
				JOptionPane.showMessageDialog(this, "Please enter your password again", "Prompt",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			if ("".equals(email) || email == null) {
				JOptionPane.showMessageDialog(this, "Please enter email", "Prompt", JOptionPane.WARNING_MESSAGE);
				return;
			}
			if ("".equals(biography) || biography == null) {
				JOptionPane.showMessageDialog(this, "Please enter 1 or 2 hobbies", "Prompt",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			if (password.equals(confirmpassword)) {
				
				// Instantiates user object with all input.
				User user = new User(first_name, last_name, username, password, email, biography);
				CommandTransfer msg = new CommandTransfer();
				
				
				// Set commandTransfer object to check registration.
				msg.setCmd("checkregister");
				msg.setData(user);
				msg.setReceiver(username);
				msg.setSender(username);
				
				Client client = new Client();
				// Sends the first command (check registration) 
				// to the server via the client.
				client.sendData(msg);
				msg = client.getData();
				//System.out.println(msg.isFlag());
				if (msg != null) {
					if (msg.isFlag() == false) { 
						
						
						//System.out.println(msg.isFlag());
						
						// Once check is complete user can be registered to the platform.
						// Do we have to reassign all these variables.
						msg.setCmd("register");
						
						// Sends the next command (registration) 
						client.sendData(msg);
						msg = client.getData();
						if (msg.isFlag() == true) {
							
							// Process is completed and registration window 
							// can now be discarded.
							this.dispose();
							JOptionPane.showMessageDialog(null, "Registration Successful!!", "Prompt",
									JOptionPane.WARNING_MESSAGE);
							
							// Display login interface
							contentPane.setVisible(false);
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
					} else {
						System.out.println(msg.isFlag());
						JOptionPane.showMessageDialog(null, "The account already exists!", "Prompt",
								JOptionPane.WARNING_MESSAGE);
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
			} else {
				JOptionPane.showMessageDialog(this, "Two different passwords, please re-enter", "Prompt",
						JOptionPane.WARNING_MESSAGE);
				lblConfirmPassword.setText("");
				this.setVisible(false);
			}
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
