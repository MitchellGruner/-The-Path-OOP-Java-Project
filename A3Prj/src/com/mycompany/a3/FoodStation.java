package com.mycompany.a3;
import java.util.Random;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class FoodStation extends Fixed {
	
	Random random = new Random();
	
	private GameWorld gw;
	private int capacity;
	
	public FoodStation(GameWorld gw) {
		super(gw);
		this.setSize(random.nextInt(100) + 25);
		this.setColor(ColorUtil.GREEN);
		this.capacity = this.getSize();
		this.gw = gw;
	}

	public int getCapacity() {
		return capacity;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		int px = (int) pPtrRelPrnt.getX(); 
		int py = (int) pPtrRelPrnt.getY(); 
		
		int xLoc = (int) (pCmpRelPrnt.getX() + this.getX());
		int yLoc = (int) (pCmpRelPrnt.getY() + this.getY());
		
	
		if ((px >= xLoc) && (px <= xLoc + this.getSize()) && (py >= yLoc) && (py <= yLoc + this.getSize())) {
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * Here is the draw() method for the 'FoodStation'
	 * class.  We first set the color to be the food station's
	 * color, which is green, and then we find the updated
	 * X and Y location.  If the food station is selected, the 
	 * food station will be 'hollow', with the updated X and Y
	 * locations, as well as its size, as parameters.  If the food
	 * station is not selected, it will be shown as a solid object.
	 */
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.getColor());
		
		int capacityX = (int) ((this.getX() + 5) + (pCmpRelPrnt.getX() - 4));
		int capacityY = (int) ((this.getY() + 5) + (pCmpRelPrnt.getY() - 4));
		
		int xLoc = (int) (pCmpRelPrnt.getX() + this.getX());
		int yLoc = (int) (pCmpRelPrnt.getY() + this.getY());
		
		String capacityNumber = String.valueOf(this.getCapacity());
		
		if(isSelected()) {
			g.drawRect(xLoc, yLoc, this.getSize(), this.getSize());
		} else {
			g.fillRect(xLoc, yLoc, this.getSize(), this.getSize());
		}
		
		g.setColor(ColorUtil.WHITE);
		g.drawString(capacityNumber, capacityX, capacityY);
	}
	
	/*
	 * Whenever the 'ant' collides with a 'food station', this
	 * handleCollision() function will be called.  We first must
	 * check if 'this' is an instance of 'FoodStation' and that
	 * gameObject is an instance of 'Ant'.  If the food station's
	 * capacity is not zero, we will add the food station's capacity
	 * level to the ant's food level, and will play the food station
	 * sound if the sound is turned on.
	 * 
	 * We will then set that particular food station's capacity level
	 * to zero, and fade the color.
	 */
	@Override
	public void handleCollision(GameObject gameObject) {
		if(this instanceof FoodStation && gameObject instanceof Ant) {
			if(this.getCapacity() != 0) {
				((Ant) gameObject).setFoodLevel((((Ant) gameObject).getFoodLevel()) + this.getCapacity());
				
				System.out.println("Food Station Capacity: " + this.getCapacity());
				System.out.println("Ant's food level is now: " + ((Ant) gameObject).getFoodLevel());
				
				if(gw.getSound()) {
					gw.getFoodStationSound().play();
				}
				
				this.setCapacity(0);
				this.setColor(ColorUtil.rgb(150, 255, 150));
			}
		}
	}

	/*
	 * This toString() method will be used whenever we
	 * display our map by using the map() method in 
	 * the GameWorld class.
	 */
	@Override
	public String toString() {
		return ("FoodStation: loc=" + this.getX() + "," + this.getY() + 
				" color=[" + ColorUtil.red(this.getColor()) + "," + ColorUtil.green(this.getColor()) + "," + ColorUtil.blue(this.getColor()) + "]" +
				" size=" + this.getSize() + " capacity=" + this.getCapacity());
	}
}
