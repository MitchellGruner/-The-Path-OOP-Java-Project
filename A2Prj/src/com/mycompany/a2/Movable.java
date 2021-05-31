package com.mycompany.a2;
import com.codename1.charts.models.Point;

public abstract class Movable extends GameObject {
	
	private int heading;
	private int speed;
	
	private float conversion;
	private float deltaX;
	private float deltaY;
	
	/*
	 * The move() method will update the movable game objects 
	 * according to their position and speed.  This method will
	 * be called when the game clock 'ticks'.
	 */
	public void move() {
		conversion = (float) ((90 - this.getHeading()) * (Math.PI / 180)) ;
		
		deltaX = (float) (this.getX() + (Math.cos(conversion) * this.getSpeed()));
		deltaY = (float) (this.getY() + (Math.sin(conversion) * this.getSpeed()));
		
		this.setX(deltaX);
		this.setY(deltaY);
	}
	
	public int getHeading() {
		return heading;
	}
	
	public void setHeading(int heading) {
		this.heading = heading;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
