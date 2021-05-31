package com.mycompany.cmd;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class AboutCommand extends Command {

	private GameWorld gw;
	
	public AboutCommand(GameWorld gw) {
		super("About");
		this.gw = gw;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		Dialog.show("About", "'ThePath Game'\n"
				+ "Mitchell Gruner\n"
				+ "CSc 133 Spring 2021\n"
				+ "Assignment #2\n"
				+ "Professor Gordon", "Ok", null);
	}
}
