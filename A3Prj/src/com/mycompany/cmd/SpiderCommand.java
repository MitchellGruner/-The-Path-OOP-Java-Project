package com.mycompany.cmd;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class SpiderCommand extends Command {

	private GameWorld gw;
	
	public SpiderCommand(GameWorld gw) {
		super("Collide with Spider");
		this.gw = gw;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		System.out.println("Spider has collided with the ant...");
	}
}
