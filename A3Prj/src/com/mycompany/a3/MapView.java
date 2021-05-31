package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;

public class MapView extends Container implements Observer {

	private GameWorld gw;
	private Game g;
	private static Point mapOrigin;
	
	private int counter = 0;
	private static int height;
	private static int width;
	
	public MapView(GameWorld gw, Game g) {
		this.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.rgb(255, 0, 0)));
		this.setLayout(new BorderLayout());
		this.getAllStyles().setBgColor(ColorUtil.WHITE);
		this.getAllStyles().setBgTransparency(255);
		this.gw = gw;
		this.g = g;
	}
	
	public static int getMapViewWidth() { 
		return width; 
	}
	
	public static int getMapViewHeight() { 
		return height; 
	}
	
	public static void setMapViewWidth(int width) { 
		MapView.width = width; 
	}
	
	public static void setMapViewHeight(int height) { 
		MapView.height = height; 
	}
	
	public void setMapViewOrigin(Point p) { 
		MapView.mapOrigin = p; 
	}
	
	public static Point getMapViewOrigin() { 
		return mapOrigin; 
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
		gw = (GameWorld) data;
		
		if(counter >= 1)
			gw.map();
		
		counter++;
		
		this.repaint();
	}

	/*
	 * This paint() method will draw the game objects
	 * (that implement IDrawable) in the middle of the display.
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Point pCmpRelPrnt = new Point(getX(), getY());
		
		IIterator iterator = gw.getCollection().getIterator();
		while(iterator.hasNext()) {
			GameObject gameObject = iterator.getNext();
			if(gameObject instanceof IDrawable) {
				((IDrawable) gameObject).draw(g, pCmpRelPrnt);
			}
		}
	}
	
	/*
	 * This pointerPressed() method will reference the
	 * select() method in GameWorld to handle the logic
	 * if a Fixed object is selected with the Position command.
	 * 
	 * This select() method will only be referenced if the Game
	 * is paused.
	 */
	public void pointerPressed(int x, int y) {
		x -= getParent().getAbsoluteX();
		y -= getParent().getAbsoluteY();
		
		Point pPtrRelPrnt = new Point(x, y); 
		Point pCmpRelPrnt = new Point(getX(), getY());
		
		if(g.getPauseFlag()) {
			gw.select(pPtrRelPrnt, pCmpRelPrnt);
		}
	}
}
