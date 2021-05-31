package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

public class ScoreView extends Container implements Observer {
	
	private Label time;
	private Label lives;
	private Label flag;
	private Label food;
	private Label health;
	private Label sound;
	
	public ScoreView() {
		this.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		this.getAllStyles().setMarginLeft(180);
		setTime();
		setLivesLeft();
		setLastFlag();
		setFoodLevel();
		setHealthLevel();
		setSound();
	}
	
	/*
	 * These methods will update the fields in the ScoreView
	 * graphical representation area by populating the labels
	 * with whatever the most up-to-date statistics are.
	 * 
	 * The secondary labels ('time', 'lives', 'flag', 'food',
	 * 'health', and 'sound') are all binded by these up-to-date
	 * statistics in the update() method.  They are populated with
	 * the Ant's current statistics, since they all set their text 
	 * to whatever the methods in the GameWorld class happen to be.
	 */
	public void setTime() {
		Label timeLabel = new Label("Time: ");
		timeLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		time = new Label("0");
		time.getAllStyles().setFgColor(ColorUtil.BLUE);
		time.getAllStyles().setPadding(RIGHT, 5);
		
		this.add(timeLabel);
		this.add(time);
	}
	
	public void setLivesLeft() {
		Label livesLabel = new Label("Lives Left: ");
		livesLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		lives = new Label("0");
		lives.getAllStyles().setFgColor(ColorUtil.BLUE);
		lives.getAllStyles().setPadding(RIGHT, 5);
		
		this.add(livesLabel);
		this.add(lives);
	}
	
	public void setLastFlag() {
		Label flagLabel = new Label("Last Flag Reached: ");
		flagLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		flag = new Label("0");
		flag.getAllStyles().setFgColor(ColorUtil.BLUE);
		flag.getAllStyles().setPadding(RIGHT, 5);
		
		this.add(flagLabel);
		this.add(flag);
	}
	
	public void setFoodLevel() {
		Label foodLabel = new Label("Food Level: ");
		foodLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		food = new Label("0");
		food.getAllStyles().setFgColor(ColorUtil.BLUE);
		food.getAllStyles().setPadding(RIGHT, 5);
		
		this.add(foodLabel);
		this.add(food);
	}
	
	public void setHealthLevel() {
		Label healthLabel = new Label("Health Level: ");
		healthLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		health = new Label("0");
		health.getAllStyles().setFgColor(ColorUtil.BLUE);
		health.getAllStyles().setPadding(RIGHT, 5);
		
		this.add(healthLabel);
		this.add(health);
	}
	
	public void setSound() {
		Label soundLabel = new Label("Sound: ");
		soundLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		sound = new Label("OFF");
		sound.getAllStyles().setFgColor(ColorUtil.BLUE);
		sound.getAllStyles().setPadding(RIGHT, 5);
		
		this.add(soundLabel);
		this.add(sound);
	}
	
	/*
	 * All of these methods can be found in the GameWorld class.
	 */
	@Override
	public void update(Observable observable, Object data) {
		GameWorld gw = (GameWorld) data;
		
		this.time.setText(Double.toString(gw.getClock()));
		this.lives.setText(Integer.toString(gw.getLives()));
		this.flag.setText(Integer.toString(gw.getFlag()));
		this.food.setText(Double.toString(gw.getFood()));
		this.health.setText(Integer.toString(gw.getHealth()));
		
		if(gw.getSound()) {
			this.sound.setText("ON");
		} else {
			this.sound.setText("OFF");
		}
	}
}
