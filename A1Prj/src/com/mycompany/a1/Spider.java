package com.mycompany.a1;
import java.util.Random;

import com.codename1.charts.util.ColorUtil;

public class Spider extends Movable {

	Random random = new Random();
	
	public Spider() {
		this.setSize(random.nextInt(40) + 10);
		this.setColor(ColorUtil.BLACK);
		this.setSpeed(random.nextInt(5) + 5);
		this.setHeading(random.nextInt(359) + 0);
	}
	
	/*
	 * When the game clock 'ticks', the spider will not only
	 * implement the move() method (since Spider extends a movable
	 * class), but will also call the moveForBoundary() method, which
	 * will randomly decrement or increment the heading of the spider
	 * by five degrees, so that they will not run in a straight line.
	 */
	public void moveForBoundary() {
		this.setHeading(this.getHeading() + (random.nextInt(10) - 5));
		
		if((this.getX() >= 1000) || (this.getY() >= 1000)) {
			this.setHeading(this.getHeading() - (random.nextInt(10) - 5));
		} else if ((this.getX() <= 0) || (this.getY() <= 0)) {
			this.setHeading(this.getHeading() + (random.nextInt(10) - 5));
		}
	}
	
	@Override
	public void setColor(int color) {
		/*
		 *  Spiders are not allowed to change color once they are created.
		 */
	}

	/*
	 * This toString() method will be used whenever we
	 * display our map by using the map() method in 
	 * the GameWorld class.
	 */
	@Override
	public String toString() {
		return ("Spider: loc=" + this.getX() + "," + this.getY() + 
				" color=[" + ColorUtil.red(this.getColor()) + "," + ColorUtil.green(this.getColor()) + "," + ColorUtil.blue(this.getColor()) + "]" +
				" heading=" + this.getHeading() + " speed=" + this.getSpeed() + " size=" + this.getSize());
	}
}
