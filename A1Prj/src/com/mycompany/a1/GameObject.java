package com.mycompany.a1;
import com.codename1.charts.models.Point;
import java.util.Random;
import com.codename1.charts.util.ColorUtil;

public abstract class GameObject {
	
	Random random = new Random();
	
	private int size;
	private Point location;
	private int color;
	
	private float x;
	private float y;
	
	public GameObject() {
		/*
		 * This will create a random location between '0' and '1000'
		 * (the size of the GameWorld).
		 */
		x = random.nextInt(1000) + 0;
		y = random.nextInt(1000) + 0;
		
		this.location = new Point(x, y);
	}
	
	/*
	 * Getters and setters.
	 */
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public void setLocation(float x, float y) {
		this.location = new Point(x,y);
	}
	
	public void setLocation(int x, int y) {
		this.setX(x);
		this.setY(y);
	}
	
	public int getColor() {
		return color;
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	public float getX() {
		return x;
	}
	
	public void setX(float x) {
		/*
		 * We use 'Math.round' to make sure that there will
		 * only be '1' decimal place for each value.
		 */
		float roundX = (float) (Math.round(x * 10.0) / 10.0);
		this.x = roundX;
	}
	
	public float getY() {
		return y;
	}
	
	public void setY(float y) {
		/*
		 * We use 'Math.round' to make sure that there will
		 * only be '1' decimal place for each value.
		 */
		float roundY = (float) (Math.round(y * 10.0) / 10.0);
		this.y = roundY;
	}

	/*
	 * We need this abstract method move() when we call
	 * the gameArray.get(i).move() method ins the GameWorld
	 * class.
	 */
	protected abstract void move();
}
