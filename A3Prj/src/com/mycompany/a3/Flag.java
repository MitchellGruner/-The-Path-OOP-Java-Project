package com.mycompany.a3;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Display;
import com.codename1.ui.Graphics;

public class Flag extends Fixed {

	private int sequenceNumber;
	
	private GameWorld gw;
	
	public Flag(int locationX, int locationY, int sequenceNumber, GameWorld gw) {
		super(gw);
		this.setLocation(locationX, locationY);
		this.sequenceNumber = sequenceNumber;
		this.setLocation(locationX, locationY);
		this.setSize(10);
		this.setColor(ColorUtil.BLUE);
		this.gw = gw;
	}

	@Override
	public void setColor(int color) {
		/*
		 * We override the setColor() method in Flag because
		 * flags cannot change colors once they are created.
		 */
	}
	
	public int getSequenceNumber() {
		return sequenceNumber;
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
	 * Here is the draw() method for the 'flag' class.
	 * We will first set the color to be the flag's current
	 * color (blue), and then we will adjust the X and Y location
	 * of the flag.  We then will have two arrays of type int that will
	 * take in this new location, the new location minus twenty, the new location
	 * plus twenty, and then the new location as its points; this will create
	 * an upside-down triangle.  The same process is performed for the
	 * second array.
	 * 
	 * If the flag is selected, we will draw the polygon with
	 * the array of points, as well as how many points there
	 * will be (3).  If the flag is not selected, the flag will
	 * be filled.
	 */
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.getColor());
		
		int numberPositionX = (int) ((this.getX() - 6) + (pCmpRelPrnt.getX() - 4));
		int numberPositionY = (int) ((this.getY() - 6) + (pCmpRelPrnt.getY() - 4));
		
		int locationX = (int) (this.getX() + pCmpRelPrnt.getX());
		int locationY = (int) (this.getY() + pCmpRelPrnt.getY());
		
		int[] pointsX = {locationX, (locationX - 20), (locationX + 20), locationX};
		int[] pointsY = {(locationY + 30), (locationY - 30), (locationY - 30), (locationY + 30)};
		int numberPoints = 3;
		
		String sequenceNumber = String.valueOf(this.getSequenceNumber());
		
		if(isSelected()) {
			g.drawPolygon(pointsX, pointsY, numberPoints);
		} else {
			g.fillPolygon(pointsX, pointsY, numberPoints);
		}

		g.setColor(ColorUtil.WHITE);
		g.drawString(sequenceNumber, numberPositionX, numberPositionY);
	}
	
	/*
	 * Whenever the 'ant' collides with a 'flag', this
	 * handleCollision() function will be called.  We first
	 * check to see if 'this' is an instance of 'flag' and
	 * the gameObject is an instance of 'Ant'.  We then perform
	 * some calculations to see if the flag reached is
	 * exactly one greater than the ant's last base reached.
	 * If so, then the ant has reached the next base, and the
	 * flag sound is played (assuming the sound is turned on).
	 * 
	 * Nothing occurs if the ant has reached a base in the wrong
	 * order.
	 */
	@Override
	public void handleCollision(GameObject gameObject) {
		if(this instanceof Flag && gameObject instanceof Ant) {
			int y = ((Ant) gameObject).getLastFlagReached();
			
			if(this.getSequenceNumber() == (y + 1)){
				((Ant) gameObject).setLastFlagReached(y + 1);
				System.out.println("Ant has now reached Flag #" + (y + 1) + "!");
				
				if(gw.getSound()) {
					gw.getFlagSound().play();	
				}
			} else {
				System.out.println("The ant has collided with Flag #" + this.getSequenceNumber() + " but must reach Flag #" + (y + 1) + "...");
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
		return ("Flag: loc=" + this.getX() + "," + this.getY() + 
				" color=[" + ColorUtil.red(this.getColor()) + "," + ColorUtil.green(this.getColor()) + "," + ColorUtil.blue(this.getColor()) + "]" + 
			    " size=" + this.getSize() + " seqNum=" + sequenceNumber);
	}
}
