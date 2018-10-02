package com.mycompany.a2;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class RightTurnCommand extends Command 
{
	private GameWorld gw;
	
	public RightTurnCommand(GameWorld gw)
	{
		super("Right");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent event)
	{
		gw.steerRight();
	}
}
