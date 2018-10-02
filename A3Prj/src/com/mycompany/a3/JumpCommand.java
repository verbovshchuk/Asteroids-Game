package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class JumpCommand extends Command 
{
	private GameWorld gw;
	
	public JumpCommand(GameWorld gw)
	{
		super("Jump");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent event)
	{
		gw.jump();
	}
}
