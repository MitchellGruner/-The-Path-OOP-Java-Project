package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;

public class Flag extends Fixed {

	private int sequenceNumber;
	
	public Flag(int locationX, int locationY, int sequenceNumber) {
		this.setLocation(locationX, locationY);
		this.sequenceNumber = sequenceNumber;
		this.setLocation(locationX, locationY);
		this.setSize(10);
		this.setColor(ColorUtil.BLUE);
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

	/*
	 * This toString() method will be used whenever we
	 * display our map by using the map() method in 
	 * the GameWorld class.
	 */
	@Override
	public String toString() {
		return ("Flag: loc=" + this.getX() + "," + this.getY() + 
				" color= [" + ColorUtil.red(this.getColor()) + "," + ColorUtil.green(this.getColor()) + "," + ColorUtil.blue(this.getColor()) + "]" + 
			    " size=" + this.getSize() + " seqNum=" + sequenceNumber);
	}
}
