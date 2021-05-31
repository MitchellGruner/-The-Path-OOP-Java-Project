package com.mycompany.a3;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Ant extends Movable implements ISteerable {
	
	private GameWorld gw;
	
	private int maximumSpeed = 10;
	private int foodLevel = 2500;
	private int foodConsumptionRate = 1;
	private int healthLevel = 10;
	private int lastFlagReached = 1;
	private int movementFromTimer = 20;
	
	private double newSpeed;
	
	/* 
	 * Code to ensure that only one Ant
	 * is in the game world at one time.  This
	 * fulfills the requirements of the Singleton
	 * Design Pattern.
	 */
	private static Ant ant;
	
	private Ant(GameWorld gw) {
		super(gw);
		this.setSpeed(10);
		this.setNewSpeed(this.getSpeed());
		this.setSize(100); 
		this.setLocation(500, 250);
		this.setHeading(0);
		this.setColor(ColorUtil.rgb(255, 0, 0));
		this.gw = gw;
	}
	
	/*
	 * Singleton Pattern as mentioned above.
	 * Checks to see if 'ant' has been instantiated.
	 * If not, a new ant instance will be created;
	 * otherwise, no code will be executed.
	 */
	public static Ant getAnt(GameWorld gw) {
		if(ant == null)
			ant = new Ant(gw);
		return ant;
	}

	/*
	 * If the ant's speed is greater than or equal to the maximum speed of 
	 * the ant, then we will make sure that the ant's speed will not exceed 
	 * the maximum speed (by retrieving the newSpeed value, which is determined
	 * by the health level of the ant - this ensures that we cannot explicitly
	 * set the maximum speed value).
	 * 
	 * Otherwise, the ant's speed will increase by '1'.  We will then output
	 * a message that will display the ant's current speed.
	 */
	public void accelerate() {
		if(this.getSpeed() >= this.getNewSpeed()) {
			this.setSpeed((int) this.getNewSpeed());
			System.out.println("Ant cannot exceed maximum speed...");
		} else {
			this.setSpeed(this.getSpeed() + 1);
			System.out.println("The ant accelerates...  Current speed: " + this.getSpeed());
		}
	}
	
	/*
	 * As long as the ant's speed does not equal '0', we will check
	 * to see if the ant's speed is greater than or equal to the maximum speed
	 * of the ant.  If this is the case, we will make sure that the ant's speed 
	 * will not exceed the maximum speed (by setting the ant's speed to 
	 * the new speed value, which takes into consideration the health level of the 
	 * ant and its maximum speed).
	 * 
	 * We will then set the speed of the ant to whatever the current speed is minus '1',
	 * and will print out a corresponding message.
	 * 
	 * If the ant's speed does equal '0', we will print a message to the screen
	 * stating that the ant's speed is already '0'.
	 */
	public void brake() {
		if(this.getSpeed() != 0) {
			if(this.getSpeed() >= this.getNewSpeed()) {
				this.setSpeed((int) this.getNewSpeed());
			}
			
			this.setSpeed(this.getSpeed() - 1);
			System.out.println("The ant breaks...  Current speed: " + this.getSpeed());
		} else {
			System.out.println("Ant's speed is already zero...");
		}
	}
	
	/*
	 * This method overrides the method that is found in the ISteerable interface.
	 * This method sets the heading of the ant, and converts between '360' degrees and
	 * '0' degrees.  Since '360' degrees and '0' degrees is the same, we can intuitively
	 * switch between these two numbers.
	 */
	@Override
	public void left() {
		if(this.getHeading() == 0) {
			this.setHeading(360);
			this.setHeading(this.getHeading() - 5);
			System.out.println("Ant's heading is now " + this.getHeading() + " degrees...");
		} else {
			this.setHeading(this.getHeading() - 5);
			System.out.println("Ant's heading is now " + this.getHeading() + " degrees...");
		}
	}

	/*
	 * This method also overrides the method in our interface, and has the same 'logic'
	 * as the left() method, in that it switches between '360' degrees and '0' degrees.
	 * If the heading is either '0' or '360', we want to make sure we set it to '0', so
	 * that it never exceeds '360' degrees (in the sense that we shouldn't have a degree
	 * value that is '365' degrees or more).
	 * 
	 * Both the left() and right() method print corresponding messages out to the screen.
	 */
	@Override
	public void right() {
		if(this.getHeading() == 0 || this.getHeading() == 360) {
			this.setHeading(0);
			this.setHeading(this.getHeading() + 5);
			System.out.println("Ant's heading is now " + this.getHeading() + " degrees...");
		} else {
			this.setHeading(this.getHeading() + 5);
			System.out.println("Ant's heading is now " + this.getHeading() + " degrees...");
		}
	}
	
	/*
	 * This method is called in the collisionSpider() method
	 * in the GameWorld class.  We want to make sure that the maximum
	 * speed of the ant is equal to the current health value of the ant.
	 */
	public void collisionWithSpider() {
		this.newSpeed = (((double) this.getHealthLevel()) / ((double) this.getMaximumSpeed()));
		this.newSpeed *= 10;
		this.setNewSpeed(this.newSpeed);
		
		if(this.getSpeed() >= this.getNewSpeed()) {
			this.setSpeed((int) this.getNewSpeed());
		}
	}
	
	public double getNewSpeed() {
		return newSpeed;
	}
	
	public void setNewSpeed(double newSpeed) {
		this.newSpeed = newSpeed;
	}
	
	public int getMaximumSpeed() {
		return maximumSpeed;
	}
	
	public int getFoodLevel() {
		return foodLevel;
	}
	
	public void setFoodLevel(int foodLevel) {
		this.foodLevel = foodLevel;
	}
	
	public int getFoodConsumptionRate() {
		return foodConsumptionRate;
	}
	
	public int getLastFlagReached() {
		return lastFlagReached;
	}
	
	public void setLastFlagReached(int lastFlagReached) {
		this.lastFlagReached = lastFlagReached;
	}
	
	public int getHealthLevel() {
		return healthLevel;
	}
	
	public void setHealthLevel(int healthLevel) {
		this.healthLevel = healthLevel;
		this.setNewSpeed(healthLevel);
	}
	
	/*
	 * Decreases the food level by the consumption
	 * rate every time this method is called.
	 */
	public void decreaseFoodLevel() {
		this.setFoodLevel(this.getFoodLevel() - this.getFoodConsumptionRate());
	}
	
	/*
	 * Here is the draw() method for the 'ant' class.
	 * We will first set the color to be the ant's current
	 * color (red), and then we will fill the circle (fillArc())
	 * with the updated X and Y locations and it's size.  
	 */
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.getColor());
		
		int locationX = (int) (this.getX() + pCmpRelPrnt.getX());
		int locationY = (int) (this.getY() + pCmpRelPrnt.getY());
		
		g.fillArc(locationX, locationY, 2 * (this.getSize() / 2), 2 * (this.getSize() / 2), 0, 360);
		g.setColor(ColorUtil.BLACK);
	}
	
	/*
	 * For when the ant loses a life in the game.
	 * Since we are working with the Singleton design
	 * pattern, we cannot instantiate a new ant.  Therefore,
	 * we must have a way to instantiate the ant.
	 */
	public void antDeath() {
		this.setSpeed(10);
		this.setNewSpeed(this.getSpeed());
		this.setFoodLevel(2500);
		this.setHealthLevel(10);
		this.setLastFlagReached(1);
		this.setSize(100);
		this.setLocation(500, 250);
		this.setHeading(0);
	}
	
	@Override
	public void handleCollision(GameObject otherObject) {
			
	}
	
	@Override
	protected void move() {
		super.move(movementFromTimer);
	}

	/*
	 * This toString() method will be used whenever we
	 * display our map by using the map() method in 
	 * the GameWorld class.
	 */
	@Override
	public String toString() {
		return ("Ant: loc=" + this.getX() + "," + this.getY() + 
				" color=[" + ColorUtil.red(this.getColor()) + "," + ColorUtil.green(this.getColor()) + "," + ColorUtil.blue(this.getColor()) + "]" +
				" heading=" + this.getHeading() + " speed=" + this.getSpeed() + " size=" + this.getSize() + " maxSpeed=" + this.getNewSpeed() + 
				" foodConsumptionRate=" + this.getFoodConsumptionRate());
				
	}
}