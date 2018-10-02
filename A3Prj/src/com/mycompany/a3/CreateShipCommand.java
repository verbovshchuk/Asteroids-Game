package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CreateShipCommand extends Command 
{
	private GameWorld gw;
	
	public CreateShipCommand(GameWorld gw)
	{
		super("Add Ship");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent event)
	{
		gw.createShip();
	}
}
