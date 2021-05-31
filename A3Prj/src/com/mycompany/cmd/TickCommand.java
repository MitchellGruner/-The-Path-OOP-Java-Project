package com.mycompany.cmd;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class TickCommand extends Command {

	private GameWorld gw;
	
	public TickCommand(GameWorld gw) {
		super("Tick");
		this.gw = gw;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		System.out.println("Game clock has ticked...");
		gw.tick();
	}
}
