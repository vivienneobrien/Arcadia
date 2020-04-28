package UI;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Controller.GameController;
import Controller.GameController2;
import util.LabelWithBG;
import MusicPlayer.MusicPlayer;
import util.GameArea;
import util.MyButton;
import util.SwingUtil;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

/**
 * 单人模式下的界面
 *
 */
public class OnlinePanel extends JPanel {
	private GameController controller;
	private GameController2 controller2;	
    private LabelWithBG newbackground;
	private GameArea mainWindow,mainWindow2;
	private MyButton btnMusic;
	private MyButton btnResume;
	private MyButton btnPause;
	private JLabel lblScroe,lblLevel,lblbg2,lblLevel2,lblScroe2,lblNewLabel;
	private Image background;
	
	
	public void setController(GameController gameController){
		this.controller=gameController;
	}
	public void setController2(GameController2 controller2){
		this.controller2=controller2;
	}
	
	/**
	 * Create the panel.
	 */
	public OnlinePanel() {
		setLayout(null);

		newbackground = new LabelWithBG(1500, 460,"src/image/bg2.png");
		
		mainWindow = new GameArea(10, 10, 236, 405);
		mainWindow.setBounds(25, 10, 236, 405);
		add(mainWindow);
		
		JLabel lblMusic= new JLabel("Music");
		lblMusic.setForeground(Color.LIGHT_GRAY);
		lblMusic.setFont(new Font("Arial", Font.BOLD, 16));
		lblMusic.setBounds(270, 278, 54, 15);
		add(lblMusic);
		
		JLabel lblLabei_1 = new JLabel("Level");
		lblLabei_1.setForeground(Color.LIGHT_GRAY);
		lblLabei_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblLabei_1.setBounds(285, 131, 54, 15);
		add(lblLabei_1);
		
		lblLevel = new JLabel("0");
		lblLevel.setFont(new Font("Arial", Font.PLAIN, 20));
		lblLevel.setForeground(Color.WHITE);
		lblLevel.setBounds(298, 161, 54, 15);
		add(lblLevel);
		
		JLabel label_1 = new JLabel("Score");
		label_1.setForeground(Color.LIGHT_GRAY);
		label_1.setFont(new Font("Arial", Font.BOLD, 16));
		label_1.setBounds(285, 206, 54, 15);
		add(label_1);
		
		lblScroe = new JLabel("0");
		lblScroe.setFont(new Font("Arial", Font.PLAIN, 20));
		lblScroe.setForeground(Color.WHITE);
		lblScroe.setBounds(298, 237, 54, 15);
		add(lblScroe);
		
		JLabel label_3 = new JLabel("Next shape");
		label_3.setForeground(Color.LIGHT_GRAY);
		label_3.setFont(new Font("Arial", Font.BOLD, 16));
		label_3.setBounds(267, 41, 86, 15);
		add(label_3);
		
		// resume and pause
		btnResume = new MyButton("src/image/button_clear.png","Resume",86,52);
		btnResume.setForeground(Color.LIGHT_GRAY);
		btnResume.setFont(new Font("Arial", Font.BOLD, 20));
		btnResume.setBounds(271, 325, 86, 23);
		btnResume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.keyResume();
				if(!MusicPlayer.isRunning()){
					MusicPlayer.bgmPlay();
				}
			}
		});
		add(btnResume);
		
		btnPause = new MyButton("src/image/button_clear.png","Pause",86,52);
		btnPause.setFont(new Font("Arial", Font.BOLD, 20));
		btnPause.setForeground(Color.LIGHT_GRAY);
		btnPause.setBounds(271, 363, 86, 28);
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.keyPause();
				if(MusicPlayer.isRunning()){
					MusicPlayer.bgmStop();
				}
			}
		});
		add(btnPause);
				
		btnMusic = new MyButton("src/image/musicOn.png","",30, 25);
		btnMusic.setBounds(323, 274, 30, 25);
		btnMusic.setBorderPainted(false);
		btnMusic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(MusicPlayer.isturnOn()){
					MusicPlayer.setturnOn(false);
					MusicPlayer.bgmStop();
					btnMusic.setNewImage("src/image/musicOff.png");
				}else{
					MusicPlayer.setturnOn(true);
					MusicPlayer.bgmPlay();
					btnMusic.setNewImage("src/image/musicOn.png");
				}
			}
		});
		add(btnMusic);
		
		
		
		
		//第二部分
		mainWindow2 = new GameArea(505, 10, 233, 405);
		mainWindow2.setBounds(505, 10, 233, 405);
		add(mainWindow2);
		
		JLabel lblLabei_1_2 = new JLabel("Level");
		lblLabei_1_2.setForeground(Color.LIGHT_GRAY);
		lblLabei_1_2.setFont(new Font("Arial", Font.BOLD, 16));
		lblLabei_1_2.setBounds(415, 131, 54, 15);
		add(lblLabei_1_2);
		
		lblLevel2 = new JLabel("0");
		lblLevel2.setFont(new Font("Arial", Font.PLAIN, 20));
		lblLevel2.setForeground(Color.WHITE);
		lblLevel2.setBounds(428, 161, 54, 15);
		add(lblLevel2);
		
		JLabel label_1_2 = new JLabel("Score");
		label_1_2.setForeground(Color.LIGHT_GRAY);
		label_1_2.setFont(new Font("Arial", Font.BOLD, 16));
		label_1_2.setBounds(415, 206, 54, 15);
		add(label_1_2);
		
		lblScroe2 = new JLabel("0");
		lblScroe2.setFont(new Font("Arial", Font.PLAIN, 20));
		lblScroe2.setForeground(Color.WHITE);
		lblScroe2.setBounds(428, 237, 54, 15);
		add(lblScroe2);
		
		JLabel label_3_2 = new JLabel("Next shape");
		label_3_2.setForeground(Color.LIGHT_GRAY);
		label_3_2.setFont(new Font("Arial", Font.BOLD, 16));
		label_3_2.setBounds(397, 41, 86, 15);
		add(label_3_2);
		
		
		
		mainWindow.setFocusable(false);
		btnResume.setFocusable(false);
		btnPause.setFocusable(false);
		btnMusic.setFocusable(false);
		
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(371, 57, 2, 259);
		ImageIcon icon1=new ImageIcon("src/image/white.png");
        icon1.setImage(icon1.getImage().getScaledInstance((int)(2*0.9),(int)(259*0.9),Image.SCALE_DEFAULT)); 
        lblNewLabel.setIcon(icon1);
		add(lblNewLabel);
		
		

		
	}
	

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		newbackground.draw(g);	
		mainWindow.creatwindow(g);
		mainWindow2.creatwindow(g);
		try{		
			lblLevel.setText(Integer.toString(controller.getGf().level));
			lblScroe.setText(Integer.toString(controller.getGf().score));
			
			lblLevel2.setText(Integer.toString(controller2.getGf2().level));
			lblScroe2.setText(Integer.toString(controller2.getGf2().score));
			
			
			controller.getCurShape().draw(g, 12, 32);
			controller.getNextShape().draw(g, 198, 95);
			controller.getGf().drawcanvas(g,0);
			
			
			controller2.getCurShape2().draw(g, 506, 32);
			controller2.getNextShape2().draw(g, 335, 95);
			controller2.getGf2().drawcanvas(g,1);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}