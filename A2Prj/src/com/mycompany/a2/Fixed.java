package com.mycompany.a2;
import com.codename1.charts.models.Point;

public abstract class Fixed extends GameObject {
	
	@Override
	protected void move() {
		/*
		 * Fixed game objects do not call the 'move' method.
		 */
	}

	@Override
	public void setLocation(float x, float y) {
		/*
		 *  Fixed objects are not allowed to change location once they are created.
		 */
	}

	@Override
	public void setLocation(int x, int y) {
		/*
		 *  Fixed objects are not allowed to change location once they are created.
		 */
	}
	
	
}
