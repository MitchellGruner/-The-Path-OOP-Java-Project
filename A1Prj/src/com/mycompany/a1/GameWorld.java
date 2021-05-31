package com.mycompany.a1;
import java.util.ArrayList;
import java.util.Random;

import com.codename1.charts.util.ColorUtil;

public class GameWorld {
	
	Random random = new Random();
	
	/*
	 * Here is our game object array titled
	 * 'gameArray' which will hold all objects
	 * that will be present in the game.
	 */
	private ArrayList<GameObject> gameArray;
	
	private Ant ant;
	
	/*
	 * These keep track of picking our random spider
	 * to eliminate from the game world, and how many
	 * spiders will be in the game world.
	 */
	private int spiderCount;
	private int spiderTracker;
	private int spiderIndex;
	
	/*
	 * These also track how many food stations are in the
	 * game world, and also keeps track of the capacity of
	 * a randomly selected food station.
	 */
	private int foodStationCount;
	private int foodStationTracker = 0;
	private int foodStationIndex;
	
	private int lives = 3;
	private int clock = 0;
	
	/*
	 * These colors will be multiplied by some value to make
	 * the respective colors look faded.
	 */
	private int antColor = 1;
	private int foodColor = 1;
	
	private boolean quit = false;
	
	public void init() {
		/*
		 * Instantiating the array.
		 */
		gameArray = new ArrayList<GameObject>();
		
		/*
		 * This will add '9' flags to the gameArray.
		 * The first and second values correspond to
		 * the 'x' and 'y' values respectively, and the 
		 * third value is the sequence number.
		 * 
		 * I chose the locations of all flags.
		 */
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
		 *  This will create exactly one ant initially located
		 *  at (500, 250).
		 */
		ant = new Ant(500, 250);
		gameArray.add(ant);
		
		/*
		 * This will create '2' to '5' spiders in the 
		 * game world.
		 */
		spiderCount = random.nextInt(4) + 2;
		
		for(int i = 0; i < spiderCount; i++) {
			gameArray.add(new Spider());
		}
		
		/*
		 * This will create '2' to '5' food stations
		 * in the game world.
		 */
		foodStationCount = random.nextInt(4) + 2;
		
		for(int i = 0; i < foodStationCount; i++) {
			gameArray.add(new FoodStation());
		}
	}
	
	/*
	 * These getters and setters are here to perform
	 * the 'quit game' logic.
	 */
	public boolean getQuit() {
		return quit;
	}
	
	public void setQuit(boolean quit) {
		this.quit = quit;
	} 
	
	/*
	 * This accelerate() method, which is performed
	 * when the user types 'a', will check to see if
	 * the ant's speed is already equal to or greater
	 * than the new speed of the ant (which changes whenever its health level changes).  
	 * If not, then a corresponding message will be printed out.
	 * 
	 * Then, we will make sure to call the Ant class method
	 * accelerate(), which handles the acceleration logic which is
	 * explained in the Ant class.
	 */
	public void accelerate() {
		if(!(ant.getSpeed() >= ant.getNewSpeed())) {
			System.out.println("The ant accelerates...");
		}
		
		for(int i = 0; i < gameArray.size(); i++) {
			if(gameArray.get(i) instanceof Ant) {
				((Ant) gameArray.get(i)).accelerate();
			}
		}
	}
	
	/*
	 * This brake() method is called whenever the user types
	 * 'b' in the text area.  If the ant's speed is not already
	 * '0', then a corresponding message will be displayed, and the 
	 * Ant class method brake() will be executed.
	 */
	public void brake() {
		if(ant.getSpeed() != 0) {
			System.out.println("The ant breaks...");
		}
		
		for(int i = 0; i < gameArray.size(); i++) {
			if(gameArray.get(i) instanceof Ant) {
				((Ant) gameArray.get(i)).brake();
			}
		}
	}
	
	/*
	 * Whenever the user types 'l' ("ell"), the left() method
	 * will be called, and we will execute the left()
	 * method in the Ant class, which is implemented from
	 * the ISteerable interface.
	 */
	public void left() {
		for(int i = 0; i < gameArray.size(); i++) {
			if(gameArray.get(i) instanceof Ant) {
				((Ant) gameArray.get(i)).left();
			}
		}
	}
	
	/*
	 * Whenever the user types 'r', the right() method
	 * will be called, and we will execute the right()
	 * method in the Ant class, which is implemented from
	 * the ISteerable interface.
	 */
	public void right() {
		for(int i = 0; i < gameArray.size(); i++) {
			if(gameArray.get(i) instanceof Ant) {
				((Ant) gameArray.get(i)).right();
			}
		}
	}
	
	/*
	 * This collisionFlag() method cuts down on '9'
	 * different methods in that it takes a parameter,
	 * and checks to see whether that number is exactly
	 * '1' greater than the lastFlagReached parameter.
	 * 
	 * If the lastFlagReached ever equals '9', then the game
	 * is over, and we exit the game.
	 */
	public void collisionFlag(int x) {
		/*
		 *  In order to win the game, the ant must reach all of
		 *  the bases in sequential order.
		 */
		if((ant.getLastFlagReached() + 1) == x) {
			ant.setLastFlagReached(ant.getLastFlagReached() + 1);
			System.out.println("Ant has now reached Flag #" + ant.getLastFlagReached() + "!");
		} else {
			System.out.println("The ant has collided with Flag #" + x + " but must reach Flag #" + (ant.getLastFlagReached() + 1) + "...");
		}
		
		if(ant.getLastFlagReached() == 9) {
			System.out.println("Game over, you win!  Total time: " + clock);
			System.exit(0);
		}
	}
	
	/*
	 * This method will pick a food station randomly, make sure
	 * that it has a positive value for capacity, and will set the ant's 
	 * food level to its current food level plus the food station's capacity.
	 * 
	 * The foodStationTracker variable initially is set to '0'.  After looping
	 * through the gameArray and locating all instances of 'FoodStation',
	 * foodStationTracker is incremented by '1'.  This tells us how many 
	 * food stations are in the gameArray, which invariably tells us
	 * the bounds that we will be restricted to when finding a random index.
	 * 
	 * The foodStationIndex variable keeps track of a random index from '0'
	 * up to the foodStationTracker variable (whatever random index it may be).
	 * This variable is set to '0' once we found our random index (since
	 * this variable would keep incrementing every time we call this function).
	 * 
	 * 'j + foodStationIndex' keeps track of the exact index (in our gameArray)
	 *  where this randomly located food station is located.
	 */
	public void collisionFood() {
		System.out.println("The ant has collided with a food station...");
		
		for(int i = 0; i < gameArray.size(); i++) {
			if(gameArray.get(i) instanceof FoodStation) {
				foodStationTracker++;
			}
		}
		
		foodStationIndex = random.nextInt(foodStationTracker);
		foodStationTracker = 0;
		
		for(int i = 0; i < gameArray.size(); i++) {
			if(gameArray.get(i) instanceof Ant) {
				for(int j = 0; j < gameArray.size(); j++) {
					if(gameArray.get(j) instanceof FoodStation) {
						if((j + foodStationIndex) < gameArray.size()) {
							if(((FoodStation) gameArray.get(j + foodStationIndex)).getCapacity() != 0) {
								if(gameArray.get(j + foodStationIndex) instanceof FoodStation) {
									ant.setFoodLevel(ant.getFoodLevel() + ((FoodStation) gameArray.get(j + foodStationIndex)).getCapacity());
									
									System.out.println("Food Station Capacity: " + ((FoodStation) gameArray.get(j + foodStationIndex)).getCapacity());
									System.out.println("Ant's food level is now: " + ant.getFoodLevel());
									
									/*
									 *  Reduces the capacity of the food station to zero.
									 */
									((FoodStation) gameArray.get(j + foodStationIndex)).setCapacity(0);
									
									/*
									 *  Fades the color of the food station.
									 */
									((FoodStation) gameArray.get(j + foodStationIndex)).setColor(ColorUtil.rgb((150 * foodColor), 255, (150 * foodColor)));
								} 
					
								break;
							}
						}
					}
				}
			}
		}
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
	 * We fade the color of the ant, remove a random spider
	 * from the game, and add a new spider to the game.
	 * Picking a random spider in this method has the same logic 
	 * as our collisionFood() method.
	 */
	public void collisionSpider() {
		System.out.println("Spider has collided with the ant...");
		
		for(int i = 0; i < gameArray.size(); i++) {
			if(gameArray.get(i) instanceof Spider) {
				spiderTracker++;
			}
		}
		
		spiderIndex = random.nextInt(spiderTracker);
		spiderTracker = 0;
		
		for(int i = 0; i < gameArray.size(); i++) {
			if(gameArray.get(i) instanceof Ant) {
				for(int n = 0; n < gameArray.size(); n++) {
					if(gameArray.get(n) instanceof Spider) {
						if((n + spiderIndex) < gameArray.size()) {
							if(ant.getHealthLevel() > 0) {
								/*
								 *  Collisions with spiders decrease the health level of the ant by one.
								 */
								ant.setHealthLevel(ant.getHealthLevel() - 1);
								System.out.println("Health level of ant: " + ant.getHealthLevel());
								
								ant.collisionWithSpider();
								
								if(ant.getHealthLevel() == 0) {
									ant.setSpeed(0);
									
									System.out.println("_____________");
										System.out.println("Game stopped...");
										System.out.println("Player lives left: " + (--lives));
									System.out.println("_____________");
									
									if(lives == 0) {
										System.out.println("Game over, you failed!");
										System.exit(0);
									} else {
										init();
									}
								}
								
								ant.setColor(ColorUtil.rgb(255, (8 * antColor), (8 * antColor)));
								antColor++;
								
								/*
								 * Sometimes, the gameArray won't remove a spider,
								 * but will remove a food station.  If it finds an
								 * index that is not of type 'Spider', it will force
								 * it to loop through all the Spider instances and
								 * delete the first spider it comes across.
								 * 
								 * This prevents the gameArray from removing a food station.
								 */
								if(gameArray.get(n + spiderIndex) instanceof Spider) {
									gameArray.remove(n + spiderIndex);
									gameArray.add(n + spiderIndex, new Spider());
								} else {
									for(int j = 0; j < gameArray.size(); j++) {
										if(gameArray.get(j) instanceof Spider) {
											gameArray.remove(j);
											gameArray.add(j, new Spider());
											
											break;
										}
									}
								}
								
								break;
							}
						}
					}
				}
			}	
		}
	}
	
	/*
	 * When the user types 't', the tick() method will
	 * be initiated.  All spiders will call the
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
		System.out.println("Game clock has ticked...");
		
		for(int i = 0; i < gameArray.size(); i++) {
			if(gameArray.get(i) instanceof Movable) {
				gameArray.get(i).move();
			}
			
			if(gameArray.get(i) instanceof Spider) {
				((Spider) gameArray.get(i)).moveForBoundary();
			}
		}
		
		/*
		 * The ant's food level is reduced by the amount indicated by its foodConsumptionRate.
		 */
		ant.setFoodLevel(ant.getFoodLevel() - ant.getFoodConsumptionRate());
		
		if(ant.getFoodLevel() <= 0) {
			ant.setFoodLevel(0);
			ant.setSpeed(0);
	
			
			System.out.println("_____________");
				System.out.println("Game stopped...");
				System.out.println("Player lives left: " + (--lives));
			System.out.println("_____________");
		
			if(lives == 0) {
				System.out.println("Game over, you failed!");
				System.exit(0);
			} else {
				init();
			}
		}
		
		/*
		 *  The elapsed time "game clock" is incremented by one.
		 */
		clock++;
		
		System.out.println("Clock is now: " + clock);
	}
	
	/*
	 * This method will be initiated when 'd' is typed in.
	 * This shows current stats for the user, such as the ant's 
	 * food level, and health level (among other things).
	 */
	public void display() {
		System.out.println("__________________________________________");
		
		System.out.println("The number of lives left: " + lives);
		System.out.println("The current clock value: " + clock);
		System.out.println("The highest flag value the ant has reached sequentially so far: " + ant.getLastFlagReached());
		System.out.println("The ant's current food level: " + ant.getFoodLevel());
		System.out.println("The ant's health level: " + ant.getHealthLevel());
		
		System.out.println("Speed of ant: " + ant.getSpeed());
		
		System.out.println("__________________________________________");
	}
	
	/*
	 * The map() method will show the current locations,
	 * colors, and other statistics of all the game objects
	 * currently in the game world.
	 */
	public void map() {
		System.out.println("__________________________________________");
		
		for(int i = 0; i < gameArray.size(); i++) {
			System.out.println(gameArray.get(i).toString());
		}
		
		System.out.println("__________________________________________");
	}
}