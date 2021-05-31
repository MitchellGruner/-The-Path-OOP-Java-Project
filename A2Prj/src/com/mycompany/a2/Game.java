package com.mycompany.a2;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.cmd.AboutCommand;
import com.mycompany.cmd.AccelerateCommand;
import com.mycompany.cmd.BreakCommand;
import com.mycompany.cmd.ExitCommand;
import com.mycompany.cmd.FlagCommand;
import com.mycompany.cmd.LeftCommand;
import com.mycompany.cmd.RightCommand;
import com.mycompany.cmd.SoundCommand;
import com.mycompany.cmd.SpiderCommand;
import com.mycompany.cmd.TickCommand;
import com.mycompany.cmd.FoodStationCommand;
import com.mycompany.cmd.HelpCommand;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;

public class Game extends Form {
	
	private GameWorld gw;
	private ScoreView sv;
	private MapView mp;
	
	public Game() {
		this.setLayout(new BorderLayout());
		
		gw = new GameWorld();
		sv = new ScoreView();
		mp = new MapView();
		
		gw.addObserver(sv);
		gw.addObserver(mp);
		
		northRegion();
		eastRegion();
		southRegion();
		westRegion();
		centerRegion();
		
		gw.init();
		this.show();
	}
	
	/*
	 * This northRegion() method holds all of the information
	 * that is located in the top-most container.  This container
	 * holds the hamburger menu, the title, the help link, and
	 * the ScoreView graphical attributes.
	 */
	private void northRegion() {
		this.addComponent(BorderLayout.NORTH, sv);
		
		Toolbar toolBar = new Toolbar();
		setToolbar(toolBar);
		toolBar.setTitle("ThePath Game");
		
		CheckBox soundCheckBox = new CheckBox("Side Menu Item Check");
		soundCheckBox.getAllStyles().setBgTransparency(255);
		soundCheckBox.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		
		SoundCommand soundCommand = new SoundCommand(gw);
		soundCheckBox.setCommand(soundCommand);
		toolBar.addComponentToSideMenu(soundCheckBox);
		
		AccelerateCommand accelerateCommand = new AccelerateCommand(gw);
		toolBar.addCommandToLeftSideMenu(accelerateCommand);
		
		AboutCommand aboutCommand = new AboutCommand(gw);
		toolBar.addCommandToLeftSideMenu(aboutCommand);
		
		HelpCommand helpCommand = new HelpCommand(gw);
		toolBar.addCommandToRightBar(helpCommand);
		
		ExitCommand exitCommand = new ExitCommand();
		toolBar.addCommandToLeftSideMenu(exitCommand);
	}
	
	/*
	 * This westRegion() method holds the 'accelerate' and 'left'
	 * buttons that are respectively key-binded.  The container has
	 * the width of the largest button, which is 'accelerate' in our
	 * case.
	 */
	private void westRegion() {
		Container westContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		
		westContainer.getAllStyles().setPadding(Component.TOP, 50);
		westContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		
		AccelerateCommand accelerateCommand = new AccelerateCommand(gw);
		Button accelerateButton = new Button(accelerateCommand);
		
		LeftCommand leftCommand = new LeftCommand(gw);
		Button leftButton = new Button(leftCommand);
		
		westContainer.add(accelerateButton);
		buttonStyling(accelerateButton, true, false);
		
		westContainer.add(leftButton);
		buttonStyling(leftButton, false, false);
		
		accelerateButton.setCommand(accelerateCommand);
		addKeyListener('a', accelerateCommand);
		
		leftButton.setCommand(leftCommand);
		addKeyListener('l', leftCommand);
		
		add(BorderLayout.WEST, westContainer);
	}

	/*
	 * This eastRegion() method also holds the following two 
	 * key-binded buttons: 'break' and 'right'.  Similar to the
	 * westRegion() method, the largest button dictates the width
	 * of the container.
	 */
	private void eastRegion() {
		Container eastContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		
		eastContainer.getAllStyles().setPadding(Component.TOP, 50);
		eastContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		
		BreakCommand breakCommand = new BreakCommand(gw);
		Button breakButton = new Button(breakCommand);
		
		RightCommand rightCommand = new RightCommand(gw);
		Button rightButton = new Button(rightCommand);
		
		eastContainer.add(breakButton);
		buttonStyling(breakButton, true, false);
		
		eastContainer.add(rightButton);
		buttonStyling(rightButton, false, false);
		
		breakButton.setCommand(breakCommand);
		addKeyListener('b', breakCommand);
		
		rightButton.setCommand(rightCommand);
		addKeyListener('r', rightCommand);
		
		add(BorderLayout.EAST, eastContainer);
	}
	
	/*
	 * Finally, the southRegion() method holds the following buttons:
	 * 'Collide With Flag', 'Collide With Spider', 'Collide With Food
	 * Stations', and 'Tick'.
	 * 
	 * The 'Collide With Flag' button is not key-binded, and it accepts
	 * user input in the form of numbers from '1' to '9'.  It then calls
	 * a method in GameWorld to check if that number passed is exactly
	 * one greater than the highest flag reached by the Ant.  The 'Collide
	 * With Spider', 'Collide With Food Stations', and 'Tick' buttons are 
	 * key-binded, and they call methods in the GameWorld class.
	 */
	private void southRegion() {
		Container southContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
		
		southContainer.getAllStyles().setPadding(Component.TOP, 50);
		southContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		
		FlagCommand flagCommand = new FlagCommand(gw);
		Button flagButton = new Button(flagCommand);
		
		SpiderCommand spiderCommand = new SpiderCommand(gw);
		Button spiderButton = new Button(spiderCommand);
		
		FoodStationCommand foodStationCommand = new FoodStationCommand(gw);
		Button foodStationButton = new Button(foodStationCommand);
		
		TickCommand tickCommand = new TickCommand(gw);
		Button tickButton = new Button(tickCommand);
		
		southContainer.add(flagButton);
		buttonStyling(flagButton, false, true);
		
		southContainer.add(spiderButton);
		buttonStyling(spiderButton, false, false);
		
		southContainer.add(foodStationButton);
		buttonStyling(foodStationButton, false, false);
		
		southContainer.add(tickButton);
		buttonStyling(tickButton, false, false);
		
		spiderButton.setCommand(spiderCommand);
		addKeyListener('g', spiderCommand);
		
		foodStationButton.setCommand(foodStationCommand);
		addKeyListener('f', foodStationCommand);
		
		tickButton.setCommand(tickCommand);
		addKeyListener('t', tickCommand);
		
		add(BorderLayout.SOUTH, southContainer);
	}
	
	/*
	 * The centerRegion() method holds the MapView class,
	 * which only has a red border for now.
	 */
	private void centerRegion() {
		this.addComponent(BorderLayout.CENTER, mp);
	}
	
	/*
	 * This method takes care of the appropriate styling for
	 * the buttons.  There are parameters present in order to 
	 * reuse code and only use one method.
	 */
	private Button buttonStyling(Button button, boolean top, boolean left) {
		if(top) {
			button.getAllStyles().setMarginTop(100);	
		} else if(left) {
			button.getAllStyles().setMarginLeft(500);
		}
		
		button.getAllStyles().setPadding(5, 5, 2, 2);
		button.getAllStyles().setBgTransparency(255);
		button.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		button.getAllStyles().setFgColor(ColorUtil.WHITE);
		button.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		
		return button;
	}
}
