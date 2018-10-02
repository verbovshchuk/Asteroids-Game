package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CreateStationCommand extends Command 
{
	private GameWorld gw;
	
	public CreateStationCommand(GameWorld gw)
	{
		super("Add Station");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent event)
	{
		gw.createSpaceStation();
	}
}
