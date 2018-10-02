package com.mycompany.a2;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class LeftTurnCommand extends Command 
{
	private GameWorld gw;
	
	public LeftTurnCommand(GameWorld gw)
	{
		super("Left");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent event)
	{
		gw.steerLeft();
	}
}
