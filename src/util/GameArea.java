package util;

import javax.swing.*;
import java.awt.*;

/**
 * ��ʾÿһ������λ�úʹ�С
 * ��������򡢵ȼ��򡢿�ʼ����ͣ�Լ���ߴ�����Ϸ����
 * 
 */
public class GameArea extends JLabel{

	private static final long serialVersionUID = 3144082323765440087L;

	private static final int SIZE = 7;
	private static final Image Canvas=new ImageIcon("src/image/canvas.png").getImage();
	private static final int Canvas_Width = Canvas.getWidth(null);
	private static final int Canvas_Height = Canvas.getHeight(null);
	
	public int x;
	public int y;
	public int h;
	public int w;
	
	/**
	 * ֻ�д���û�����ݵĵ���������캯��
	 */
	public GameArea(int x, int y, int w, int h){
		this.x=x-5;
		this.y=y;
		this.w=w;
		this.h=h;
	}
	/**
	 * �������ֵı���������ȼ��ģ����������������ΪҪ��ͼ
	 */
	
	/*
	public GameWindow(int x, int y, int w, int h,String strUrl){
		this(x,y,w,h);
        
        // ���ð�ť����ͼ
        ImageIcon icon=new ImageIcon(strUrl);
        icon.setImage(icon.getImage().getScaledInstance((int)(w*0.4),(int)(h*0.6),Image.SCALE_DEFAULT)); 
        setIcon(icon);
        
        // ���ý������
        setFocusable(true);
	}
	*/
	
	
	/**
	 * ��������
	 */
	public void creatwindow(Graphics g){
		//����
		g.drawImage(Canvas, x, y, x+SIZE, y+SIZE, 0, 0, SIZE, SIZE, null);
		//����
		g.drawImage(Canvas, x+SIZE, y, x+w-SIZE, y+SIZE, SIZE, 0, Canvas_Width-SIZE, SIZE, null);
		//����
		g.drawImage(Canvas, x+w-SIZE, y, x+w, y+SIZE, Canvas_Width-SIZE, 0, Canvas_Width, SIZE, null);
		//����
		g.drawImage(Canvas, x, y+SIZE, x+SIZE, y+h-SIZE, 0, SIZE, SIZE, Canvas_Height-SIZE, null);	
		//��
		g.drawImage(Canvas, x+SIZE, y+SIZE, x+w-SIZE, y+h-SIZE, SIZE, SIZE, Canvas_Width-SIZE, Canvas_Height-SIZE, null);			
		//����
		g.drawImage(Canvas, x+w-SIZE, y+SIZE, x+w, y+h-SIZE, Canvas_Width-SIZE, SIZE, Canvas_Width,Canvas_Height-SIZE, null);
		//����
		g.drawImage(Canvas, x, y+h-SIZE, x+SIZE, y+h, 0, Canvas_Height-SIZE, SIZE, Canvas_Height, null);
		//����
		g.drawImage(Canvas, x+SIZE, y+h-SIZE, x+w-SIZE, y+h, SIZE, Canvas_Height-SIZE, Canvas_Width-SIZE, Canvas_Height, null);
		//����
		g.drawImage(Canvas, x+w-SIZE, y+h-SIZE, x+w, y+h, Canvas_Width-SIZE, Canvas_Height-SIZE, Canvas_Width, Canvas_Height, null);		
	}
	
	
}
