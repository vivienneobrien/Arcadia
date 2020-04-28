package util;

import javax.swing.*;
import java.awt.*;

/**
 * 表示每一个框框的位置和大小
 * 比如分数框、等级框、开始、暂停以及左边大块的游戏界面
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
	 * 只有窗体没有内容的调用这个构造函数
	 */
	public GameArea(int x, int y, int w, int h){
		this.x=x-5;
		this.y=y;
		this.w=w;
		this.h=h;
	}
	/**
	 * 里面有字的比如分数、等级的，调用这个函数，因为要绘图
	 */
	
	/*
	public GameWindow(int x, int y, int w, int h,String strUrl){
		this(x,y,w,h);
        
        // 设置按钮背景图
        ImageIcon icon=new ImageIcon(strUrl);
        icon.setImage(icon.getImage().getScaledInstance((int)(w*0.4),(int)(h*0.6),Image.SCALE_DEFAULT)); 
        setIcon(icon);
        
        // 设置焦点控制
        setFocusable(true);
	}
	*/
	
	
	/**
	 * 创建窗体
	 */
	public void creatwindow(Graphics g){
		//左上
		g.drawImage(Canvas, x, y, x+SIZE, y+SIZE, 0, 0, SIZE, SIZE, null);
		//中上
		g.drawImage(Canvas, x+SIZE, y, x+w-SIZE, y+SIZE, SIZE, 0, Canvas_Width-SIZE, SIZE, null);
		//右上
		g.drawImage(Canvas, x+w-SIZE, y, x+w, y+SIZE, Canvas_Width-SIZE, 0, Canvas_Width, SIZE, null);
		//左中
		g.drawImage(Canvas, x, y+SIZE, x+SIZE, y+h-SIZE, 0, SIZE, SIZE, Canvas_Height-SIZE, null);	
		//中
		g.drawImage(Canvas, x+SIZE, y+SIZE, x+w-SIZE, y+h-SIZE, SIZE, SIZE, Canvas_Width-SIZE, Canvas_Height-SIZE, null);			
		//右中
		g.drawImage(Canvas, x+w-SIZE, y+SIZE, x+w, y+h-SIZE, Canvas_Width-SIZE, SIZE, Canvas_Width,Canvas_Height-SIZE, null);
		//左下
		g.drawImage(Canvas, x, y+h-SIZE, x+SIZE, y+h, 0, Canvas_Height-SIZE, SIZE, Canvas_Height, null);
		//中下
		g.drawImage(Canvas, x+SIZE, y+h-SIZE, x+w-SIZE, y+h, SIZE, Canvas_Height-SIZE, Canvas_Width-SIZE, Canvas_Height, null);
		//右下
		g.drawImage(Canvas, x+w-SIZE, y+h-SIZE, x+w, y+h, Canvas_Width-SIZE, Canvas_Height-SIZE, Canvas_Width, Canvas_Height, null);		
	}
	
	
}
