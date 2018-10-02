package com.mycompany.a2;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class LoadMissilesCommand extends Command 
{
	private GameWorld gw;
	
	public LoadMissilesCommand(GameWorld gw)
	{
		super("Load Missiles");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent event)
	{
		gw.reloadMissiles();
	}
}
