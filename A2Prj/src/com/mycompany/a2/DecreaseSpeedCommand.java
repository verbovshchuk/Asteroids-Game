package com.mycompany.a2;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class DecreaseSpeedCommand extends Command 
{
	private GameWorld gw;
	
	public DecreaseSpeedCommand(GameWorld gw)
	{
		super("Decrease");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent event)
	{
		gw.decreaseSpeed();
	}
}
