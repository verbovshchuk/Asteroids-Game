package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class ExterminateCommand extends Command 
{
	private GameWorld gw;
	
	public ExterminateCommand(GameWorld gw)
	{
		super("Exterminate");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent event)
	{
		gw.asteroidsCollided();
	}
}
