package com.mycompany.a2;
import java.util.Random;

import com.codename1.charts.util.ColorUtil;

public class FoodStation extends Fixed {
	
	Random random = new Random();
	
	private int capacity;
	
	public FoodStation() {
		this.setSize(random.nextInt(40) + 10);
		this.setColor(ColorUtil.GREEN);
		this.capacity = this.getSize();
	}

	@Override
	public void setLocation(float x, float y) {
		/*
		 * We override the setLocation() method in FoodStation because
		 * food stations cannot change locations once they are created.
		 */
	}
	
	@Override
	public void setLocation(int x, int y) {
		/*
		 * We override the setLocation() method in FoodStation because
		 * food stations cannot change locations once they are created.
		 */
	}

	public int getCapacity() {
		return capacity;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
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
