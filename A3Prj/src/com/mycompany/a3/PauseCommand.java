package com.mycompany.a3;

import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.Command;

public class PauseCommand extends Command
{
	private Game thisGame;
	
	public PauseCommand(Game theGame)
	{
		super("Pause");
		thisGame = theGame;

	}
	
	public void actionPerformed(ActionEvent e)
	{
		thisGame.pause();
	}

}
