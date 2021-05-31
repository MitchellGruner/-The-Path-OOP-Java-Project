package com.mycompany.cmd;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class RightCommand extends Command {

	private GameWorld gw;
	
	public RightCommand(GameWorld gw) {
		super("Right");
		this.gw = gw;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		System.out.println("Right command is invoked...");
		gw.right();
	}
}
