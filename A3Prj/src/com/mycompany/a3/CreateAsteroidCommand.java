package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CreateAsteroidCommand extends Command {
	private GameWorld gw;
	
	//constructor, pass in gameworld object
	public CreateAsteroidCommand(GameWorld gw)
	{
		super("Add Asteroid");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent event)
	{
		gw.createAsteroid();
	}
}
