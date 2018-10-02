package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class SoundCommand extends Command 
{
	private GameWorld gw;
	
	public SoundCommand(GameWorld gw)
	{
		super("Sound");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent event)
	{
		if (gw.getSound() == true)
		{
			gw.setSound(false);
			System.out.println("Sound turned off");
		} else
		{
			gw.setSound(true);
			System.out.println("Sound turned on.");
		}
	}
}
