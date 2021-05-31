package com.mycompany.a3;
import java.util.Random;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
import com.codename1.ui.util.UITimer;

public class Spider extends Movable {
	
	private GameWorld gw;
	private int movementFromTimer = 20;

	Random random = new Random();
	
	public Spider(GameWorld gw) {
		super(gw);
		this.setSize(random.nextInt(100) + 25);
		this.setColor(ColorUtil.BLACK);
		this.setSpeed(random.nextInt(5) + 5);
		this.setHeading(random.nextInt(359) + 0);
		this.gw = gw;
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
	 * Here is the draw() method for the Spider class.  We will
	 * first make sure that the color is black, since all spiders are
	 * black, and then we will create two variables which will store
	 * the updated locations.  We then will have two arrays of type int that will
	 * take in this new location, the new location minus twenty, the new location
	 * plus twenty, and then the new location as its points; this will create
	 * an upside-down triangle.  The same process is performed for the
	 * second array.
	 * 
	 * We then will draw the polygon with these two arrays, as well as the
	 * number of points which are present (3).
	 */
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.getColor());
		
		int locationX = (int) (this.getX() + pCmpRelPrnt.getX());
		int locationY = (int) (this.getY() + pCmpRelPrnt.getY());
		
		int[] pointsX = {locationX, (locationX - 20), (locationX + 20), locationX};
		int[] pointsY = {(locationY + 30), (locationY - 30), (locationY - 30), (locationY + 30)};
		int numberPoints = 3;
		
		g.drawPolygon(pointsX, pointsY, numberPoints);
	}
	
	/*
	 * Whenever the 'ant' collides with the 'spider', this handleCollision
	 * method will be invoked.  We will remove the spider from the game world,
	 * add a new one with random attributes, and fade the color of the ant.
	 * 
	 * We will also play the spiderSound if the sound is turned on.  The health
	 * level of the ant is decremented by one, and the collisionWithSpider() method
	 * will be invoked.  If the health level of the ant is 0, the ant loses
	 * a life.  If the ant's lives remaining is zero, the game is over - otherwise, the 
	 * game is reinitialized and the antDeath() method is called.
	 */
	@Override
	public void handleCollision(GameObject gameObject) {
		if(this instanceof Spider && gameObject instanceof Ant) {
			this.getGameWorld().getCollection().remove(this);
			this.getGameWorld().getCollection().add(new Spider(gw));
			
			((Ant) gameObject).setColor(ColorUtil.rgb(255, (8 * gw.getAntColor()), (8 * gw.getAntColor())));
			gw.setAntColor(gw.getAntColor() + 1);
			
			if(gw.getSound()) {
				gw.getSpiderSound().play();	
			}
			
			((Ant) gameObject).setHealthLevel(((Ant) gameObject).getHealthLevel() - 1);
			((Ant) gameObject).collisionWithSpider();
			
			if(((Ant) gameObject).getHealthLevel() == 0) {
				this.getGameWorld().setLives(this.getGameWorld().getLives() - 1);
				
				System.out.println("_____________");
					System.out.println("Game stopped...");
					System.out.println("Player lives left: " + this.getGameWorld().getLives());
				System.out.println("_____________");
				
				if(this.getGameWorld().getLives() == 0) {
					System.out.println("Game over, you failed!");
					Display.getInstance().exitApplication();
				} else {
					((Ant) gameObject).antDeath();
					this.getGameWorld().init();
				}
			}
		}
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
		return ("Spider: loc=" + this.getX() + "," + this.getY() + 
				" color=[" + ColorUtil.red(this.getColor()) + "," + ColorUtil.green(this.getColor()) + "," + ColorUtil.blue(this.getColor()) + "]" +
				" heading=" + this.getHeading() + " speed=" + this.getSpeed() + " size=" + this.getSize());
	}
}
