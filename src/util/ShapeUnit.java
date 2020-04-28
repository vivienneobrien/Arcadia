package util;

import javax.swing.*;
import java.awt.*;


public class ShapeUnit {

	public static final int Square_W = 20;
	
	public static final int Square_H = 20;
		
	public int[] x = new int[4];
	
	public int[] y = new int[4];
	
	public int color;
	
	public Image Square_color = new ImageIcon("src/image/square.png").getImage();
		
	public ShapeUnit(int color){
		this.color = color;
		switch(color){
			case 1: 
				this.x[0]=5 ;this.x[1]=4 ;this.x[2]=6 ;this.x[3]=7 ;	//长条形
				this.y[0]=-1;this.y[1]=-1;this.y[2]=-1;this.y[3]=-1; 
				break;
			case 2: 
				this.x[0]=5 ;this.x[1]=4 ;this.x[2]=6 ;this.x[3]=5 ;	//土字形
				this.y[0]=-1;this.y[1]=-1;this.y[2]=-1;this.y[3]=0; 
				break;
			case 3: 
				this.x[0]=5 ;this.x[1]=4 ;this.x[2]=5 ;this.x[3]=4 ;	//田字形
				this.y[0]=-1;this.y[1]=-1;this.y[2]=0 ;this.y[3]=0 ; 		
				break;
			case 4: 
				this.x[0]=5 ;this.x[1]=4 ;this.x[2]=6 ;this.x[3]=6 ;	//右L字形
				this.y[0]=-1;this.y[1]=-1;this.y[2]=-1;this.y[3]=0; 
				break;
			case 5: 
				this.x[0]=5 ;this.x[1]=4 ;this.x[2]=6 ;this.x[3]=4;	    //左L字形
				this.y[0]=-1;this.y[1]=-1;this.y[2]=-1;this.y[3]=0; 
				break;
			case 6: 
				this.x[0]=5 ;this.x[1]=6 ;this.x[2]=5 ;this.x[3]=4;	     //Z字形
				this.y[0]=-1;this.y[1]=-1;this.y[2]=0;this.y[3]=0; 
				break;
			case 7: 
				this.x[0]=5 ;this.x[1]=4 ;this.x[2]=5 ;this.x[3]=6;	     //反Z字形
				this.y[0]=-1;this.y[1]=-1;this.y[2]=0;this.y[3]=0; 
				break;
		}
	}
	
	public void drawoneunit(Graphics g,int pos_x,int pos_y,int x,int y){
		g.drawImage(Square_color,
				pos_x+x*Square_W , pos_y+y*Square_H, pos_x+(x+1)*Square_W , pos_y+(y+1)*Square_H,
				color*Square_W, 0 , (color+1)*Square_W, Square_H, null);

	}

	public void draw(Graphics g,int pos_x,int pos_y){
		for(int i=0;i<4;i++){
			drawoneunit(g,pos_x,pos_y,x[i],y[i]);
		}
	}

	public void change(){
			int tmpx=0;
			if(color != 3 && tmpx!=-1)
				for(int i=0;i<4;i++){
					tmpx=this.x[i];
					this.x[i]=this.y[i]-this.y[0]+this.x[0];
					this.y[i]=this.x[0]-tmpx+this.y[0];
				}		
		}	
	
	public void down(){
			for(int i=0;i<4;i++)
				this.y[i] = this.y[i] + 1;

	}	
	
	public void left(){
		
		for(int i=0;i<4;i++)
			this.x[i] = this.x[i] - 1;

	}
	
	public void right(){

		for(int i=0;i<4;i++)
			this.x[i] = this.x[i] + 1;

	}


	public void setColor(int color) {
		this.color = color;
	}
}
