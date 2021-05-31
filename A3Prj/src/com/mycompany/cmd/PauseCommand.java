package com.mycompany.cmd;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.Game;
import com.mycompany.a3.GameWorld;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox; 

public class PauseCommand extends Command {

	private GameWorld gw;
	private Game g;
	
	private int counter = 0;
	
	public PauseCommand(GameWorld gw, Game g) {
		super("Pause");
		this.gw = gw;
		this.g = g;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		g.pausedGame();
		
		if(counter % 2 == 0) {
			g.setPauseFlag(true);
		} else {
			g.setPauseFlag(false);
		}
		
		counter++;
	}
}
