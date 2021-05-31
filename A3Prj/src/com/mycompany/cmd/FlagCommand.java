package com.mycompany.cmd;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class FlagCommand extends Command {

	private GameWorld gw;
	
	public FlagCommand(GameWorld gw) {
		super("Collide With Flag");
		this.gw = gw;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		TextField textField = new TextField();
		Command okCommand = new Command("Ok");
		
		Dialog.show("Enter Flag Number: ", textField, okCommand);
		
		try {
			int conversion = Integer.parseInt(textField.getText().toString());
			
			if(conversion > 0 && conversion <= 9) {
//				gw.collisionFlag(conversion);
			} else {
				Dialog.show("Error", "Please enter a valid number (1-9)...", okCommand);
			}
		} catch (NumberFormatException e) {
			Dialog.show("Error", "An integer must be entered...", okCommand);
		}
	}
}
