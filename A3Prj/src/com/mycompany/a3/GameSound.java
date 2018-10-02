package com.mycompany.a3;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class GameSound 
{
	private Media s;
	public GameSound (String fileName)
	{
		try{
			InputStream is = Display.getInstance().getResourceAsStream(getClass(),  "/" + fileName);
			s = MediaManager.createMedia(is,  "audio/wav");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void play()
	{
		s.setTime(0);
		s.play();
	}
	
}
