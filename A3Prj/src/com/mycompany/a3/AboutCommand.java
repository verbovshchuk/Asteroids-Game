package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;

public class AboutCommand extends Command{
	private Command okButton = new Command("Ok");
	public AboutCommand(){
		super("About");
	}
	@Override 
	public void actionPerformed(ActionEvent event)
	{
		Label aboutLabel = new Label("Pavel Verbovshchuk, CSC 133, Assignment 2");
		Dialog.show("About", aboutLabel, okButton);
		System.out.println("About command invoked.");
	}
}
