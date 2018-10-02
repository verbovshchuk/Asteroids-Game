package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class IncreaseSpeedCommand extends Command 
{
	private GameWorld gw;
	
	public IncreaseSpeedCommand(GameWorld gw)
	{
		super("Increase");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent event)
	{
		gw.increaseSpeed();
	}
}
