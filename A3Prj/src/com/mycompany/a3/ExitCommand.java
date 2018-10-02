package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;

public class ExitCommand extends Command{
	
	
	ExitCommand(){
		super("Exit");		
	}
	@Override
	public void actionPerformed(ActionEvent event)
	{
		Boolean desireToQuit = Dialog.show("Confirm quit", "Are you sure you want to quit?", "Ok", "Cancel");
		System.out.println("Quit command invoked.");
		if (desireToQuit)
		{
			//instead of System.exit(0), this helps to quit the application
			Display.getInstance().exitApplication();
		}
	}
}