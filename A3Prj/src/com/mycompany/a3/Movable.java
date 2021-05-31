package com.mycompany.a3;
import com.codename1.charts.models.Point;

public abstract class Movable extends GameObject {
	
	private GameWorld gw;

	private int heading;
	private int speed;
	
	private float conversion;
	private float deltaX;
	private float deltaY;
	
	public Movable(GameWorld gw) {
		super(gw);
		this.gw = gw;
	}
	
	public void setHeading(int heading) {
		if(heading >= 360) {
			heading -= 360;
		}
		
		if(heading <= 0) {
			heading += 360;
		}
		
		this.heading = heading;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	/*
	 * The move() method will update the movable game objects 
	 * according to their position and speed.  This method will
	 * be called when the game clock 'ticks'.  All game objects that
	 * invoke move() must use the elapsed clock tick value (20) to compute
	 * a new location.
	 * 
	 * "However, it is a requirement that each move() computes movement based 
	 * on the value of the elapsed time parameter passed in".
	 */
	public void move(int movementFromTimer) {
		conversion = (float) Math.toRadians(90 - this.getHeading());
		
		deltaX = (float) (this.getX() + Math.cos(conversion) * this.getSpeed());
		deltaY = (float) (this.getY() + Math.sin(conversion) * this.getSpeed());
		
		/*
		 * Functionality for the right wall.
		 * If the movable object hits the right wall, they will 'bounce back'
		 * (essentially, their heading will be opposite of what it previously
		 * was).
		 * 
		 * We also take into effect the movementFromTimer parameter, which was
		 * passed from the Spider and Ant class.
		 */
		if(this.getHeading() != 0 || this.getHeading() != 180) {
			if(((int) MapView.getMapViewOrigin().getX() + deltaX + movementFromTimer) >= MapView.getMapViewWidth() + (int) MapView.getMapViewOrigin().getX()) {
				this.setHeading(-this.getHeading());
			}
		}
		
		/*
		 * Functionality for the left wall.
		 * If the movable object hits the left wall, they will 'bounce back'
		 * (essentially, their heading will be opposite of what it previously
		 * was).
		 * 
		 * We also take into effect the movementFromTimer parameter (but only
		 * when it is an instance of the Spider class), which was
		 * passed from the Spider and Ant class.
		 */
		if(this.getHeading() != 0 || this.getHeading() != 180) {
			if(this instanceof Spider) {
				if((((int) MapView.getMapViewOrigin().getX()) + deltaX) <= ((int) MapView.getMapViewOrigin().getX() + movementFromTimer)) {
					this.setHeading(-this.getHeading());
				}
			} else if((((int) MapView.getMapViewOrigin().getX()) + deltaX) <= ((int) MapView.getMapViewOrigin().getX())){
				this.setHeading(-this.getHeading());
			}
		}
		
		/*
		 * Functionality for the bottom wall.
		 * If the movable object hits the bottom wall, they will 'bounce back'
		 * (essentially, their heading will be opposite of what it previously
		 * was).
		 * 
		 * We also take into effect the movementFromTimer parameter, which was
		 * passed from the Spider and Ant class.
		 */
		if(((int) MapView.getMapViewOrigin().getY() + deltaY + movementFromTimer) >= ((int) MapView.getMapViewOrigin().getY() + GameWorld.getHeight())) {
			this.setHeading((-this.getHeading() + 180) % 360);
		}
		
		if (this instanceof Spider) {
			if(((int) MapView.getMapViewOrigin().getY() + deltaY) <= ((int) MapView.getMapViewOrigin().getY() + movementFromTimer)) {
				setHeading((-this.getHeading() + 180) % 360);
			}
			
			/*
			 * Functionality for the top wall.
			 * If the movable object hits the top wall, they will 'bounce back'
			 * (essentially, their heading will be opposite of what it previously
			 * was).
			 */
		} else {
			if(((int) MapView.getMapViewOrigin().getY() + deltaY) <= ((int) MapView.getMapViewOrigin().getY())) {
				setHeading((-this.getHeading() + 180) % 360);
			}
		}
		
		/*
		 * We then will set the new location of the game object to the conversion,
		 * which is carried out above.
		 */
		this.setLocation(deltaX, deltaY);
	}
	
	public int getHeading() {
		return heading;
	}
}
