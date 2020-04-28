package Controller;

import util.ShapeUnit;

import java.awt.*;

/**
 * ��Ϸ�����е���ײ��⡢���������
 * 
 */
public class Gamification {

	private boolean[][] gamecanvas;
	public int cancelline=0;
	public int score =0;
	public int level=0;
	
	public Gamification(){
		// 11X19�ķ��񣬶��ҳ�ʼ��Ϊδռ��
		this.gamecanvas = new boolean[11][19];
		for(int x=0;x<11;x++)
			for(int y=0;y<19;y++)
				gamecanvas[x][y]=false;
		
	}
	
	public boolean isleftside(ShapeUnit currentUnit){
		
		for(int i=0;i<4;i++){
			//�����ǽ���߷��鶼����
			if(currentUnit.x[i]==0 || gamecanvas[currentUnit.x[i]-1][currentUnit.y[i]])		
				return true;
		}
		return false;
	}
	
	public boolean isrightside(ShapeUnit currentUnit){
		
		for(int i=0;i<4;i++){
			//�ұ���ǽ���߷��鶼����
			if(currentUnit.x[i]==10  || gamecanvas[currentUnit.x[i]+1][currentUnit.y[i]])
				return true;
		}
		return false;
	}
	
	public boolean isput(ShapeUnit currentUnit){
		
		boolean isput = false;

		for(int i=0;i<4;i++)
			//������ǽ���߷���
			if( currentUnit.y[i]>=18 || gamecanvas[currentUnit.x[i]][currentUnit.y[i]+1] ){
				isput = true;
				break;
			}

		if(isput) {
			//���isput���Ž���ͼ
			for(int j=0;j<4;j++)
				gamecanvas[currentUnit.x[j]][currentUnit.y[j]]=true;
		}
		return isput;
	}

	public boolean ifcanChange(ShapeUnit currentUnit){
		
		for(int i=0;i<4;i++){
			int change_x = currentUnit.y[i]-currentUnit.y[0]+currentUnit.x[0] ;
			int change_y = currentUnit.x[0]-currentUnit.x[i]+currentUnit.y[0] ;
			if(  change_x<0 || change_y<0 || change_x>10 || gamecanvas[change_x][change_y])
				return false;
		}
		return true;
	}
	
	
	public boolean gameover(){
		
		//boolean game=false;
		for(int x=0;x<11;x++){
			if(gamecanvas[x][0] == true)
				return true;
		}
		return false;
		
	}

	/**
	 * �Ƿ�ɹ���ȥһ��
	 * @return
	 */
	public boolean ispop(){	
		boolean iscancel=false;
		int tmpline=0;						//�������
		for(int y=18;y>=0;y--)
			for(int x=0;x<11;x++){
				if(gamecanvas[x][y] != true)
					break;

				if(x==10){
					tmpline++;
					cancelline++;
					popLine(y);
					y++;
					iscancel=true;
				}
			}
		
		if(tmpline != 0){
			//�����͵ȼ�����
			score +=tmpline*tmpline;
			if(score > (2+2*level*level))
				level++;
		}
		return iscancel;
	}
	
	public void popLine(int line){
		for(int y=line;y>0;y--)
			for(int x=0;x<11;x++){
				gamecanvas[x][y] = gamecanvas[x][y-1];
			}		
	}
	

	/**
	 * ���Ƶ׶��Ѿ����µķ���
	 * @param g
	 * @param mode
	 */
	public void drawcanvas(Graphics g,int mode){
		ShapeUnit su = new ShapeUnit(0);
		for(int x=0;x<11;x++)
			for(int y=0;y<19;y++)
				if(gamecanvas[x][y]==true){
					if(mode==1){
						su.drawoneunit(g, 506, 32, x, y);
					}else {
						su.drawoneunit(g, 12, 32, x, y);
					}
				}

	}

}
