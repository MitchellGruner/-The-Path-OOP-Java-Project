package com.mycompany.cmd;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.Fixed;
import com.mycompany.a3.GameObject;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.IIterator;

public class PositionCommand extends Command {

	private GameWorld gw;
	
	public PositionCommand(GameWorld gw) {
		super("Position");
		this.gw = gw;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		IIterator iterator = gw.getCollection().getIterator();
		while(iterator.hasNext()) {
			GameObject gameObject = iterator.getNext();
			if(gameObject instanceof Fixed) {
				if(((Fixed) gameObject).isSelected()) {
					gw.togglePosition();
					break;
				}
			}
		}
	}
}
