package com.mycompany.cmd;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;
import com.codename1.ui.CheckBox; 

public class SoundCommand extends Command {

	private GameWorld gw;
	
	public SoundCommand(GameWorld gw) {
		super("Sound");
		this.gw = gw;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if(((CheckBox) evt.getComponent()).isSelected()) {
			System.out.println("Sound is turned on...");
			gw.setSound(true);
		} else {
			System.out.println("Sound is turned off...");
			gw.setSound(false);
		}
	}
}
