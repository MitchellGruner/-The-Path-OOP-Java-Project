package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

public abstract class Fixed extends GameObject implements ISelectable {
	
	private GameWorld gw;
	private boolean isSelected;
	
	public Fixed(GameWorld gw) {
		super(gw);
		this.gw = gw;
	}
	
	@Override
	protected void move() {
		/*
		 * Fixed game objects do not call the 'move' method.
		 */
	}
	
	@Override
	public void setSelected(boolean b) {
		this.isSelected = b;
	}
	
	@Override
	public boolean isSelected() {
		return isSelected;
	}
	
	@Override
	public void setLocation(float x, float y) {
		if(isSelected()) {
			super.setLocation(x, y);
		}
	}
	
	public abstract void draw(Graphics g, Point pCmpRelPrnt);
	
	public abstract boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt);
}
