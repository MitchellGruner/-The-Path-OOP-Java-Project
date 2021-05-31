package com.mycompany.a3;
import com.codename1.charts.models.Point;

import java.util.Random;
import java.util.Vector;
import com.codename1.charts.util.ColorUtil;

public abstract class GameObject implements IDrawable, ICollider {
	
	Random random = new Random();
	
	private GameWorld gw;
	private Point location;
	
	private int size;
	private int color;
	
	private int upperBoundX = 1500;
	private int lowerBoundX = 100;
	private int upperBoundY = 1000;
	private int lowerBoundY = 100;
	
	private Vector<GameObject> collisionVector;
	
	public GameObject(GameWorld gw) {

		float x = new Random().nextInt(upperBoundX - lowerBoundX) + lowerBoundX; 
		float y = new Random().nextInt(upperBoundY - lowerBoundY) + lowerBoundY; 
		
		this.location = new Point(x, y);
		this.gw = gw;
		collisionVector = new Vector<GameObject>();
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public void setLocation(float x, float y) {
		this.location = new Point(x, y);
	}
	
	public void setLocation(int x, int y) {
		this.setX(x);
		this.setY(y);
	}
	
	public Point getLocation() {
		return this.location;
	}
	
	public int getColor() {
		return color;
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	public float getX() {
		return location.getX();
	}
	
	public void setX(float x) {
		location.setX(x);
	}
	
	public float getY() {
		return location.getY();
	}
	
	public void setY(float y) {
		location.setY(y);
	}

	protected abstract void move();
	
	/*
	 * Here is the collidesWith() function that was
	 * outlined in the class notes.
	 */
	@Override
	public boolean collidesWith(GameObject gameObject) {
		boolean result = false;
		
		int thisCenterX = (int) (this.getX() + (gameObject.size / 2)); 
		int thisCenterY = (int) (this.getY() + (gameObject.size / 2));
		
		int otherCenterX = (int) (gameObject.getX() + (gameObject.size / 2));
		int otherCenterY = (int) (gameObject.getY() + (gameObject.size / 2));
		
		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterY - otherCenterY;
		
		int distBetweenCentersSqr = ((dx * dx) + (dy * dy));
		
		int thisRadius = gameObject.size / 2;
		int otherRadius = gameObject.size / 2;
		
		int radiiSqr = (thisRadius * thisRadius + 2 * thisRadius * otherRadius + otherRadius * otherRadius);
		
		if (distBetweenCentersSqr <= radiiSqr) { 
			result = true ; 
		}
		
		return result ;
	}
	
	/*
	 * This function will allow us to 'communicate'
	 * with the GameWorld from other classes.
	 */
	public GameWorld getGameWorld() {
		return gw;
	}
	
	/*
	 * We will use this function in our GameWorld when we
	 * need to check for multiple collisions at one time.
	 */
	public Vector<GameObject> getObjectCollisions () {
		return collisionVector;
	}
}
