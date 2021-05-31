package com.mycompany.cmd;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class HelpCommand extends Command {

	private GameWorld gw;
	
	public HelpCommand(GameWorld gw) {
		super("Help");
		this.gw = gw;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		Dialog.show("Help",
				  "'a' - Accelerate\n"
				+ "'b' - Break\n"
				+ "'l' - Turn Left\n"
				+ "'r' - Turn Right", "Ok", null);
	}
}
