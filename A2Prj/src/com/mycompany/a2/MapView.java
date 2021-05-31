package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer {

	private int counter = 0;
	
	public MapView() {
		this.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.rgb(255, 0, 0)));
		this.setLayout(new BorderLayout());
		this.setWidth(1000);
		this.setHeight(1000);
	}
	
	/*
	 * Since there is a run-time error the first time update()
	 * is called, we need to make sure that this method is called
	 * every time except for the first time (that is why we created
	 * a counter).  We want to display the map() every time the game
	 * world is updated.
	 */
	@Override
	public void update(Observable observable, Object data) {
		GameWorld gw = (GameWorld) data;
		
		if(counter >= 1)
			gw.map();
		
		counter++;
	}
}
