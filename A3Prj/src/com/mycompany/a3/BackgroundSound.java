package com.mycompany.a3;

import java.io.InputStream;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class BackgroundSound implements Runnable
{
	private Media s;
	public BackgroundSound (String fileName)
	{
		try{
			InputStream is = Display.getInstance().getResourceAsStream(getClass(),  "/" + fileName);
			s = MediaManager.createMedia(is,  "audio/wav", this);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void pause() 
	{
		s.pause();
	}
	
	public void play()
	{
		s.play();
	}
	
	public void run()
	{
		s.setTime(0);
		s.play();
	}
}
