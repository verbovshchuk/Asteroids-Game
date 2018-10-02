package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.List;
import com.codename1.ui.events.ActionEvent;

public class HelpCommand extends Command{
	

	public HelpCommand() 
	{
		super("Help");
	}
	@Override
	public void actionPerformed(ActionEvent event)
	{
		Command okButton = new Command("Ok");

		String lMessage = new String("a: turn left");
		String rMessage = new String("d: turn right");
		String uMessage = new String("w: increase speed");
		String dMessage = new String("s: decrease speed");
		String sMessage = new String("f: fire missile");
		String jMessage = new String("j: jump through hyperspace");
		String xMessage = new String("x: exits game");

		List<String> myList = new List<String>();
		
		myList.addItem(lMessage);
		myList.addItem(rMessage);
		myList.addItem(uMessage);
		myList.addItem(dMessage);
		myList.addItem(sMessage);
		myList.addItem(jMessage);
		myList.addItem(xMessage);

	
		Dialog.show("Help", myList, okButton);
	}
}
