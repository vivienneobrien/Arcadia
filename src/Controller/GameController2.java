package Controller;

import util.ShapeUnit;
import Controller.Gamification;
import Socket.Client;
import UI.OnlinePanel;

import javax.swing.*;

/**
 * ��Ӧ��ս����������ʾ��ս�����
 * Created by Fang 
 */
public class GameController2  {

	//��һ�黭����������
		 public static GameController2 GameController2;

		    // ���ɽ���
		    private OnlinePanel panel;
		    private Gamification gf2=new Gamification();
		   

		    public Gamification getGf2() {
		        return gf2;
		    }

		    // ��ǰͼ������һ��ͼ��
		    private ShapeUnit curShape2;
		    private ShapeUnit nextShape2;

		    public void setCurShape2(int color){
		        curShape2 =new ShapeUnit(color);
		    }

		    public void setNextShape2(int color){
		    	nextShape2 =new ShapeUnit(color);
		    }
		    public ShapeUnit getCurShape2() {
		        return curShape2;
		    }

		    public ShapeUnit getNextShape2() {
		        return nextShape2;
		    }

		    
		    
		    public GameController2(Client client, OnlinePanel panel) {
		        this.panel=panel;
		        curShape2 =new ShapeUnit(1);
		        nextShape2 =new ShapeUnit(2);
		    }
		  
		    

		    // ͼ�ο���
		    public void rectUp() {
		        curShape2.change();
		        panel.repaint();
		    }
		    public void rectDown() {
		        curShape2.down();
		        panel.repaint();
		    }

		    public void rectLeft() {
		        curShape2.left();
		        panel.repaint();

		    }

		    public void rectRight() {
		        curShape2.right();
		        panel.repaint();
		    }

		    // ͼ��ֹͣ����ȥ
		    public void isPut(){
		    	gf2.isput(curShape2);
		        curShape2.setColor(0);
		    }
		    public void ispop(){
		    	gf2.ispop();
		    }
		    
		    public void gameover(){
		        // �ӵ���һ�������������ͣ��һ��controller��Ȼ��ȽϷ���
		        int remoteScore= GameController.Controller.gameover();
		        int myScore=gf2.score;
		        String str=Integer.toString(myScore)+"��"+Integer.toString(remoteScore)+",";
		        // ����ȷֵ��ж���GameController�е��෴
		        if(myScore<remoteScore){
		            // WIN
		            JOptionPane.showMessageDialog(panel, str + "��Ӯ��");
		        }else if(myScore>remoteScore){
		            // LOSE
		            JOptionPane.showMessageDialog(panel,str+"������");
		        }else{
		            // pingju
		            JOptionPane.showMessageDialog(panel,str+"����һ��ƽ��");
		        }
		    }


}

