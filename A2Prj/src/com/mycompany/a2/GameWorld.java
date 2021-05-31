package com.mycompany.a2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Random;
import java.util.Vector;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Display;

public class GameWorld extends Observable {
	
	Random random = new Random();
	
	private GameObjectCollection gameArray;
	
	private int clock = 0;
	private int lives = 3;
	private boolean sound;
	
	private int spiderCount;
	private int foodStationCount;
	
	private int antColor = 1;
	private int foodColor = 1;
	
	private boolean quit = false;
	
	public void init() {
		gameArray = new GameObjectCollection();
		
		gameArray.add(Ant.getAnt());
		
		gameArray.add(new Flag(320, 350, 1));
		gameArray.add(new Flag(32, 874, 2));
		gameArray.add(new Flag(900, 300, 3));
		gameArray.add(new Flag(162, 88, 4));
		gameArray.add(new Flag(993, 64, 5));
		gameArray.add(new Flag(90, 740, 6));
		gameArray.add(new Flag(6, 681, 7));
		gameArray.add(new Flag(282, 2, 8));
		gameArray.add(new Flag(56, 480, 9));
		
		/*
		 * These for loops will create '10' to '13' spiders
		 * and '5' to '8' food stations in the gameArray.
		 * 
		 * We want to insure that the sound is always turned
		 * off initially, and to notify our observers that 
		 * the game world has been reinitialized.
		 */
		spiderCount = random.nextInt(3) + 10;
		
		for(int i = 0; i < spiderCount; i++) {
			gameArray.add(new Spider());
		}
		
		foodStationCount = random.nextInt(3) + 5;
		
		for(int i = 0; i < foodStationCount; i++) {
			gameArray.add(new FoodStation());
		}
		
		this.sound = false;
		this.setChanged();
		this.notifyObservers(this);
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
	
	public int getLives() {
		return this.lives;
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
	 * This collisionFlag() method cuts down on '9'
	 * different methods in that it takes a parameter,
	 * and checks to see whether that number is exactly
	 * '1' greater than the lastFlagReached parameter.
	 * 
	 * If the lastFlagReached ever equals '9', then the game
	 * is over, and we exit the game.
	 * 
	 * The FlagCommand references this method - the user will
	 * type in a number from '1' to '9', and that will be passed
	 * to this parameter.
	 * 
	 * In order to win the game, the ant must reach all of
	 * the bases in sequential order.
	 */
	public void collisionFlag(int x) {
		IIterator iterator = gameArray.getIterator();
		while(iterator.hasNext()) {
			GameObject gameObject = (GameObject) iterator.getNext();
			if(gameObject instanceof Ant) {
				int y = ((Ant) gameObject).getLastFlagReached();
				
				if((y + 1) == x){
					((Ant) gameObject).setLastFlagReached(y + 1);
					System.out.println("Ant has now reached Flag #" + (y + 1) + "!");
				} else {
					System.out.println("The ant has collided with Flag #" + x + " but must reach Flag #" + (y + 1) + "...");
				}
				
				if(((Ant) gameObject).getLastFlagReached() == 9) {
					System.out.println("Game over, you win!  Total time: " + clock);
					Display.getInstance().exitApplication();
				}
			}
		}
		this.setChanged();
		this.notifyObservers(this);
	}
	
	/*
	 * This collisionFood() method will have the iterator
	 * go through the collection of game objects and select a
	 * non-empty food station.  The Ant will then set it's food
	 * level to whatever it's food level was before PLUS the food
	 * station's capacity level.  The food station will then have
	 * a capacity of '0', and will be faded in color.
	 */
	public void collisionFood() {
		IIterator iterator = gameArray.getIterator();
		while(iterator.hasNext()) {
			GameObject gameObject = (GameObject) iterator.getNext();
			if(gameObject instanceof Ant) {
				IIterator iterator2 = gameArray.getIterator();
				while(iterator2.hasNext()) {
					GameObject gameObject2 = (GameObject) iterator2.getNext();
					if(gameObject2 instanceof FoodStation) {
						if(((FoodStation) gameObject2).getCapacity() != 0) {
							((Ant) gameObject).setFoodLevel((((Ant) gameObject).getFoodLevel()) + (((FoodStation) gameObject2).getCapacity()));
							
							System.out.println("Food Station Capacity: " + ((FoodStation) gameObject2).getCapacity());
							System.out.println("Ant's food level is now: " + ((Ant) gameObject).getFoodLevel());
							
							((FoodStation) gameObject2).setCapacity(0);
							((FoodStation) gameObject2).setColor(ColorUtil.rgb((150 * foodColor), 255, (150 * foodColor)));
							
							break;
						}
					}
					
				}
			}
		}
		this.setChanged();
		this.notifyObservers(this);
	}
	
	/*
	 * Colliding with a spider will decrease the health level
	 * of the ant by '1', and call the collisionWithSpider() 
	 * Ant class method (which is detailed in the Ant class).
	 * 
	 * If the ant's health level is '0', the ant's speed is set
	 * to '0', the game is stopped, and the player loses a life.
	 * 
	 * If the player's lives equals '0', the game is over, and we
	 * exit the game.  Otherwise, the game is re-initialized.
	 * 
	 * We fade the color of the ant, remove a spider
	 * from the game, and add a new spider to the game.
	 */
	public void collisionSpider() {
		IIterator iterator = gameArray.getIterator();
		while(iterator.hasNext()) {
			GameObject gameObject = (GameObject) iterator.getNext();
			if(gameObject instanceof Ant) {
				IIterator iterator2 = gameArray.getIterator();
				while(iterator2.hasNext()) {
					GameObject gameObject2 = (GameObject) iterator2.getNext();
					if(gameObject2 instanceof Spider) {	
						gameArray.remove((Spider) gameObject2);
						gameArray.add(new Spider());
						
						((Ant) gameObject).setColor(ColorUtil.rgb(255, (8 * antColor), (8 * antColor)));
						antColor++;
						
						if(((Ant) gameObject).getHealthLevel() > 1) {
							((Ant) gameObject).setHealthLevel(((Ant) gameObject).getHealthLevel() - 1);
							
							System.out.println("Health level of ant: " + ((Ant) gameObject).getHealthLevel());
							((Ant) gameObject).collisionWithSpider();
							break;
						} else {
							((Ant) gameObject).setSpeed(0);
								
							System.out.println("_____________");
								System.out.println("Game stopped...");
								System.out.println("Player lives left: " + (--lives));
							System.out.println("_____________");
							
							if(lives == 0) {
								System.out.println("Game over, you failed!");
								Display.getInstance().exitApplication();
							} else {
								((Ant) gameObject).antDeath();
								init();
							}
							break;
						}
					}
				}
			}
		}
		this.setChanged();
		this.notifyObservers(this);
	}
	
	/*
	 * When the user types 't' (or presses on the 'Tick' 
	 * button), the tick() method will be initiated.  All spiders will call the
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
				
				if(((Ant) gameObject2).getFoodLevel() <= 0) {
					((Ant) gameObject2).setFoodLevel(0);
					((Ant) gameObject2).setSpeed(0);
					
					System.out.println("_____________");
						System.out.println("Game stopped...");
						System.out.println("Player lives left: " + (--lives));
					System.out.println("_____________");
					
					if(lives == 0) {
						System.out.println("Game over, you failed!");
						Display.getInstance().exitApplication();
					} else {
						IIterator iterator3 = gameArray.getIterator();
						while(iterator3.hasNext()) {
							GameObject gameObject3 = iterator3.getNext();
							if(gameObject3 instanceof Ant) {
								((Ant) gameObject3).antDeath();
								break;
							}
						}
						init();
					}
				} else {
					break;
				}
			}
		}
		this.setChanged();
		this.notifyObservers(this);
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