package com.mycompany.cmd;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class LeftCommand extends Command {

	private GameWorld gw;
	
	public LeftCommand(GameWorld gw) {
		super("Left");
		this.gw = gw;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		System.out.println("Left command is invoked...");
		gw.left();
	}
}
