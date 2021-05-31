package com.mycompany.a3;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Random;
import java.util.Vector;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Display;

public class GameWorld extends Observable {
	
	Random random = new Random();
	
	private GameObjectCollection gameArray;
	
	private Sound spiderCollisionSound;
	private Sound foodStationCollisionSound;
	private Sound flagCollisionSound;
	private BGSound backgroundSound;
	
	private int clock = 0;
	private int lives = 3;
	private int timeElapsed = 0;
	private boolean sound;
	
	private boolean positionFlag;
	private boolean position;
	
	private static int height = 1000;
	private static int width = 1000;
	
	private int spiderCount;
	private int foodStationCount;
	
	private int antColor = 1;
	
	private boolean quit = false;
	
	public void init() {
		gameArray = new GameObjectCollection();
		
		gameArray.add(Ant.getAnt(this));
		
		gameArray.add(new Flag(500, 250, 1, this));
		gameArray.add(new Flag(257, 299, 2, this));
		gameArray.add(new Flag(900, 300, 3, this));
		gameArray.add(new Flag(1012, 1022, 4, this));
		gameArray.add(new Flag(993, 899, 5, this));
		gameArray.add(new Flag(765, 58, 6, this));
		gameArray.add(new Flag(1657, 681, 7, this));
		gameArray.add(new Flag(243, 899, 8, this));
		gameArray.add(new Flag(489, 1001, 9, this));
		
		/*
		 * These for loops will create '10' to '13' spiders
		 * and '5' to '8' food stations in the gameArray.
		 * 
		 * We want to ensure that the sound is always turned
		 * off initially, and to notify our observers that 
		 * the game world has been reinitialized.
		 */
		spiderCount = random.nextInt(3) + 10;
		
		for(int i = 0; i < spiderCount; i++) {
			gameArray.add(new Spider(this));
		}
		
		foodStationCount = random.nextInt(3) + 5;
		
		for(int i = 0; i < foodStationCount; i++) {
			gameArray.add(new FoodStation(this));
		}
		
		
		this.sound = this.getSound();
		this.setChanged();
		this.notifyObservers(this);
		
		position = false;
	}
	
	public boolean getQuit() {
		return quit;
	}
	
	public void setQuit(boolean quit) {
		this.quit = quit;
	} 
	
	public int getClock() {
		return this.clock;
	}
	
	public int getTimeInSeconds() {
		return timeElapsed;
	}
	
	public int getLives() {
		return this.lives;
	}
	
	public void setLives(int lives) {
		this.lives = lives;
	}
	
	public static int getHeight() {
		return height;
	}
	
	public static void setHeight(int gameHeight) {
		height = gameHeight;
	}
	
	public int getWidth() {
		return width;
	}
	
	public static void setWidth(int gameWidth) {
		width = gameWidth;
	}
	
	public boolean getPositionFlag() {
		return positionFlag;
	}
	
	public void setPositionFlag(boolean positionFlag) {
		this.positionFlag = positionFlag;
	}
	
	public Sound getSpiderSound() {
		return spiderCollisionSound;
	}
	
	public Sound getFoodStationSound() {
		return foodStationCollisionSound;
	}
	
	public Sound getFlagSound() {
		return flagCollisionSound;
	}
	
	public BGSound getBackgroundSound() {
		return backgroundSound;
	}
	
	public int getAntColor() {
		return antColor;
	}
	
	public void setAntColor(int antColor) {
		this.antColor = antColor;
	}
	
	/*
	 * The following get(Something)() methods are called from
	 * the ScoreView class in order to display the Ant's statistics
	 * in a graphical representation.  They reference the Ant class's
	 * getters for various components.
	 */
	public int getFlag() {
		IIterator iterator = gameArray.getIterator();
		while(iterator.hasNext()) {
			GameObject gameObject = (GameObject) iterator.getNext();
			if(gameObject instanceof Ant) {
				return ((Ant) gameObject).getLastFlagReached();
			}
		}
		return 0;
	}
	
	public int getFood() {
		IIterator iterator = gameArray.getIterator();
		while(iterator.hasNext()) {
			GameObject gameObject = (GameObject) iterator.getNext();
			if(gameObject instanceof Ant) {
				return ((Ant) gameObject).getFoodLevel();
			}
		}
		return 0;
	}
	
	public int getHealth() {
		IIterator iterator = gameArray.getIterator();
		while(iterator.hasNext()) {
			GameObject gameObject = (GameObject) iterator.getNext();
			if(gameObject instanceof Ant) {
				return ((Ant) gameObject).getHealthLevel();
			}
		}
		return 0;
	}
	
	public boolean getSound() {
		return sound;
	}
	
	public void setSound(boolean sound) {
		this.sound = sound;
		
		this.setChanged();
		this.notifyObservers(this);
	}
	
	public GameObjectCollection getCollection() {
		return gameArray;
	}
	
	/*
	 * This accelerate() method, which is performed
	 * when the user types 'a' (or presses on the 'Accelerate' button), 
	 * will check to see if the ant's speed is already equal to or greater
	 * than the new speed of the ant (which changes whenever its health level changes).  
	 * If not, then a corresponding message will be printed out.
	 * 
	 * Then, we will make sure to call the Ant class method
	 * accelerate(), which handles the acceleration logic which is
	 * explained in the Ant class.
	 */
	public void accelerate() {
		IIterator iterator = gameArray.getIterator();
		while(iterator.hasNext()) {
			GameObject gameObject = (GameObject) iterator.getNext();
			if(gameObject instanceof Ant) {
				((Ant) gameObject).accelerate();
			}
		}
		this.setChanged();
		this.notifyObservers(this);
	}
	
	/*
	 * This brake() method is called whenever the user types
	 * 'b' (or presses on the 'Break' button).  If the ant's speed
	 * is not already '0', then a corresponding message will be 
	 * displayed, and the Ant class method brake() will be executed.
	 */
	public void brake() {
		IIterator iterator = gameArray.getIterator();
		while(iterator.hasNext()) {
			GameObject gameObject = (GameObject) iterator.getNext();
			if(gameObject instanceof Ant) {
				((Ant) gameObject).brake();
			}
		}
		this.setChanged();
		this.notifyObservers(this);
	}
	
	/*
	 * Whenever the user types 'l' ("ell") (or presses on the 'Left' 
	 * button), the left() method will be called, and we will execute 
	 * the left() method in the Ant class, which is implemented from
	 * the ISteerable interface.
	 */
	public void left() {
		IIterator iterator = gameArray.getIterator();
		while(iterator.hasNext()) {
			GameObject gameObject = (GameObject) iterator.getNext();
			if(gameObject instanceof Ant) {
				((Ant) gameObject).left();
			}
		}
		this.setChanged();
		this.notifyObservers(this);
	}
	
	/*
	 * Whenever the user types 'r' (or presses on the 'Right' 
	 * button), the right() method will be called, and we will 
	 * execute the right() method in the Ant class, which is 
	 * implemented from the ISteerable interface.
	 */
	public void right() {
		IIterator iterator = gameArray.getIterator();
		while(iterator.hasNext()) {
			GameObject gameObject = (GameObject) iterator.getNext();
			if(gameObject instanceof Ant) {
				((Ant) gameObject).right();
			}
		}
		this.setChanged();
		this.notifyObservers(this);
	}
	
	/*
	 * This select() method will be called from the MapView
	 * class, which is only called if the game is paused.  This method
	 * will first make sure that position is true (the position button 
	 * must be clicked) and the game object is of type 'Fixed'.  If both of
	 * those conditions are true, and the game object is selected, we will
	 * determine the new locations for x and y, and set the new location
	 * to be that point. We then will make sure that the postion variable is 
	 * set to false.
	 * 
	 * If we happen to click within the selected object, we will make sure that
	 * that selected object is still selected.  Otherwise, setSelected will be
	 * false.
	 */
	public void select(Point newPoint, Point oldPoint) {
		IIterator iterator = this.getCollection().getIterator();
		while(iterator.hasNext()) {
			GameObject gameObject = iterator.getNext(); 
			if(gameObject instanceof Fixed) {
				if(position && ((Fixed) gameObject).isSelected()) {
					int locX = (int) (newPoint.getX() - oldPoint.getX());
					int locY = (int) (newPoint.getY() - oldPoint.getY());
					
					((Fixed) gameObject).setLocation(locX, locY);
					((Fixed) gameObject).setSelected(false);
					position = false;
				} else if(((Fixed) gameObject).contains(newPoint, oldPoint)) {
					((Fixed) gameObject).setSelected(true);
				} else { 
					((Fixed) gameObject).setSelected(false);
				}
			}
		}
		this.setChanged();
		this.notifyObservers(this);
	}
	
	/*
	 * This method will be called in the Game class to make sure
	 * that any selected object will be de-selected when the game is
	 * un-paused.
	 */
	public void checkSelected() {
		IIterator iterator = this.getCollection().getIterator();
		while(iterator.hasNext()) {
			GameObject gameObject = iterator.getNext();
			if(gameObject instanceof Fixed) {
				if(((Fixed) gameObject).isSelected()) {
					((Fixed) gameObject).setSelected(false);
				}
			}
		}
		this.setChanged();
		this.notifyObservers(this);
	}
	
	/*
	 * When the run() method is invoked in Game, this tick method 
	 * will be called.  All spiders will call the
	 * moveForBoundary() method as well as the move() method.
	 * Also, the ant will call the move() method (since the Ant class
	 * extends Movable).
	 * 
	 * The ant's food level will be reduced by the amount of
	 * the foodConsumptionRate.  If the ant's food level is
	 * '0', the game is stopped and the player loses a life.
	 * The clock also increases by '1'.
	 * 
	 * If the amount of lives is equal to '0', the game is over
	 * and we exit the game.  Otherwise, the game is re-initialized.
	 */
	public void tick() {
		clock++;
		System.out.println("Clock is now: " + clock);
		
		IIterator iterator = gameArray.getIterator();
		while(iterator.hasNext()) {
			GameObject gameObject = iterator.getNext();
			if(gameObject instanceof Movable) {
				((Movable) gameObject).move();
			}
			
			IIterator iterator1 = gameArray.getIterator();
			while(iterator1.hasNext()) {
				GameObject gameObject1 = iterator1.getNext();
				if(gameObject1 instanceof Spider) {
					((Spider) gameObject1).moveForBoundary();
				}
			}
		}
		
		IIterator iterator2 = gameArray.getIterator();
		while(iterator2.hasNext()) {
			GameObject gameObject2 = iterator2.getNext();
			if(gameObject2 instanceof Ant) {
				((Ant) gameObject2).decreaseFoodLevel();
				
				if(((Ant) gameObject2).getFoodLevel() == 0) {
					((Ant) gameObject2).setSpeed(0);
					
					this.setLives(this.getLives() - 1);
					
					System.out.println("_____________");
						System.out.println("Game stopped...");
						System.out.println("Player lives left: " + this.getLives());
					System.out.println("_____________");
					
					if(this.getLives() == 0) {
						System.out.println("Game over, you failed!");
						Display.getInstance().exitApplication();
					} else {
						((Ant) gameObject2).antDeath();
						this.init();
					}
				}
				
				if(((Ant) gameObject2).getLastFlagReached() == 9) {
					System.out.println("Game over, you win!  Total time: " + getClock());
					Display.getInstance().exitApplication();
				}
			}
		}
		collisionCheck();
		
		this.setChanged();
		this.notifyObservers(this);
	}
	
	/*
	 * This method will check to see if more than two objects
	 * are colliding at the same time.  We will create a vector for
	 * the first gameObject, and another vector for second gameObject.
	 * If the gameObject collides with the second gameObject, we will make sure
	 * that the gameObject vector does not already contain the second gameObject.
	 * If it does not, then we will add the second gameObject to the first gameObject's
	 * vector, and handle the collision.  We will perform the same logic for the second
	 * gameObject.
	 * 
	 * It is important to note that this handleCollision() method will only be 
	 * performed if the two objects have not collided before.
	 * 
	 * Once we are done with our check, we will remove the references from
	 * both of our vectors.
	 */
	public void collisionCheck() {
		IIterator iterator = gameArray.getIterator();
		while(iterator.hasNext()) {
			GameObject gameObject = iterator.getNext();
			IIterator iterator2 = gameArray.getIterator();
			while(iterator2.hasNext()) {
				GameObject gameObject2 = iterator2.getNext();
				if(gameObject != gameObject2) {
					if(gameObject.collidesWith(gameObject2)) {
						if(!(gameObject.getObjectCollisions().contains(gameObject2))) {
							gameObject.getObjectCollisions().add(gameObject2);
							gameObject.handleCollision(gameObject2);
						} 
						
						if(!(gameObject2.getObjectCollisions().contains(gameObject))) {
							gameObject2.getObjectCollisions().add(gameObject);
						}
					}
					
					gameObject.getObjectCollisions().remove(gameObject2);
					gameObject2.getObjectCollisions().remove(gameObject);
				}
			}
		}
		this.setChanged();
		this.notifyObservers(this);
	}
	
	/*
	 * This createSounds() method will instantiate all of our
	 * sounds that will be used in our game.  This method will be
	 * called when we first start up the game.
	 */
	public void createSounds() {
		spiderCollisionSound = new Sound("spiderCollision.wav");
		foodStationCollisionSound = new Sound("foodStationCollision.wav");
		flagCollisionSound = new Sound("success.wav");
		backgroundSound = new BGSound("backgroundSound.wav");
	}
	
	public void togglePosition() {
		position = !position;
	}
	
	public void turnOnBackgroundSound() {
		if(getSound()) {
			backgroundSound.play();
		}
	}
	
	public void turnOffBackgroundSound() {
		backgroundSound.pause();
	}
	
	/*
	 * The map() method will show the current locations,
	 * colors, and other statistics of all the game objects
	 * currently in the game world.
	 * 
	 * This map() method will be called each and every time the 
	 * game world changes in any way.  Therefore, a textual based
	 * representation of the game will always be displayed in the 
	 * console.
	 */
	public void map() {
		System.out.println("__________________________________________");
		
		IIterator iterator = gameArray.getIterator();
		while(iterator.hasNext()) {
			GameObject gameObject = (GameObject) iterator.getNext();
			if(gameObject instanceof GameObject) {
				System.out.println(gameObject.toString());
			}
		}
		
		System.out.println("__________________________________________");
	}
}