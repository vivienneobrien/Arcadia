package MusicPlayer;

import java.applet.AudioClip;
import java.io.*;
import java.applet.Applet;
import java.net.MalformedURLException;
import java.net.URL;

public class MusicPlayer {

    private static boolean running =false;
    private static boolean turnOn =true;
    private static String bgmUrl="src/music/SOUND_BGM.wav";
    private static String actionUrl="src/music/action.wav";
    
    private static AudioClip bgmAudioClip;
    private static AudioClip actionAudioClip;
    
    //Play BGM
    public static void bgmPlay(){
    	if(!turnOn)
    		return;
    	if(bgmAudioClip==null){
    		try {
                URL cb;
                File file = new File(bgmUrl);
                cb = file.toURI().toURL();
                bgmAudioClip = Applet.newAudioClip(cb);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
    	}
    	try{
    		bgmAudioClip.loop();
    		running =true;
    	}catch(Exception exception){
    		exception.printStackTrace();
    	}
    	
    }
    
    //Stop BGM
    public static void bgmStop(){
    	try{
            bgmAudioClip.stop();
            running =false;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    //Play music when move
    public static void movePlay(){
    	if(!turnOn)
    		return;
    	if(actionAudioClip==null){
    		try {
                URL cb;
                File file = new File(actionUrl);
                cb = file.toURI().toURL();
                actionAudioClip = Applet.newAudioClip(cb);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
    	}
    	try{
    		actionAudioClip.play();
    	}catch(Exception exception){
    		exception.printStackTrace();
    	}
    }
    

    public static boolean isRunning(){
        return running;
    }

    public static boolean isturnOn(){
    	return turnOn;
    }

    public static void setturnOn(Boolean turn){
    	turnOn=turn;
    }
    
}

