package Controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import MusicPlayer.MusicPlayer;

public class KeyController2 extends KeyAdapter{

	private GameController_singleplayer gameController;
	public KeyController2(GameController_singleplayer gameController){
		this.gameController = gameController;
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(MusicPlayer.isturnOn())
			MusicPlayer.movePlay();
		
		if(e.getKeyCode()==KeyEvent.VK_A) {
			this.gameController.keyResume();
			if(!MusicPlayer.isRunning()){
				MusicPlayer.bgmPlay();
			}
			return;
		}

		if(gameController.isRunning()) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					this.gameController.keyUp();
					break;
				case KeyEvent.VK_DOWN:
					this.gameController.keyDown();
					break;
				case KeyEvent.VK_LEFT:
					this.gameController.keyLeft();
					break;
				case KeyEvent.VK_RIGHT:
					this.gameController.keyRight();
					break;
				case KeyEvent.VK_SPACE:
					this.gameController.keyPause();
					if(MusicPlayer.isRunning()){
						MusicPlayer.bgmStop();
					}
					break;
				default:
					break;
			}
		}



	}
	
}
