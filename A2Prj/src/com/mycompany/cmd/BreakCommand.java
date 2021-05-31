package com.mycompany.cmd;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class BreakCommand extends Command {

	private GameWorld gw;
	
	public BreakCommand(GameWorld gw) {
		super("Break");
		this.gw = gw;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.brake();
	}
}
