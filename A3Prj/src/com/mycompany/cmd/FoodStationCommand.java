package com.mycompany.cmd;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class FoodStationCommand extends Command {

	private GameWorld gw;
	
	public FoodStationCommand(GameWorld gw) {
		super("Collide with Food Stations");
		this.gw = gw;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		System.out.println("The ant has collided with a food station...");
//		gw.collisionFood();
	}
}
