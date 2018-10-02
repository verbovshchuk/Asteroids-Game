package com.mycompany.a2;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CrashShipCommand extends Command 
{
	private GameWorld gw;
	
	public CrashShipCommand(GameWorld gw)
	{
		super("Crash Ship");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent event)
	{
		gw.shipCrashed();
	}
}
