package util;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class LabelWithBG  extends JLabel{
	
	private Image background;	
	public LabelWithBG(int width,int height,String address){
        ImageIcon icon=new ImageIcon(address);
        background=icon.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT);
	}
	public void draw(Graphics g){
		g.drawImage(background, 0, 0, null);
	}
}
