package com.mycompany.a3;
import com.codename1.charts.models.Point;
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
import com.codename1.ui.util.UITimer;
import com.mycompany.cmd.AboutCommand;
import com.mycompany.cmd.AccelerateCommand;
import com.mycompany.cmd.BreakCommand;
import com.mycompany.cmd.ExitCommand;
import com.mycompany.cmd.FlagCommand;
import com.mycompany.cmd.LeftCommand;
import com.mycompany.cmd.PauseCommand;
import com.mycompany.cmd.PositionCommand;
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

public class Game extends Form implements Runnable {
	
	private GameWorld gw;
	private ScoreView sv;
	private MapView mp;
	
	private UITimer timer;
	private int timerTime = 20;
	
	private boolean pauseFlag = false;
	
	private Toolbar toolBar;
	private CheckBox soundCheckBox;
	private SoundCommand soundCommand;
	private AccelerateCommand accelerateCommand;
	private AboutCommand aboutCommand;
	private HelpCommand helpCommand;
	private ExitCommand exitCommand;
	
	private Container westContainer;
	private Button accelerateButton;
	private LeftCommand leftCommand;
	private Button leftButton;
	
	private Container eastContainer;
	private BreakCommand breakCommand;
	private Button breakButton;
	private RightCommand rightCommand;
	private Button rightButton;
	
	private Container southContainer;
	private PositionCommand positionCommand;
	private Button positionButton;
	private PauseCommand pauseCommand;
	private Button pauseButton;
	
	public Game() {
		this.setLayout(new BorderLayout());
		
		gw = new GameWorld();
		sv = new ScoreView();
		mp = new MapView(gw, this);
		
		gw.addObserver(sv);
		gw.addObserver(mp);
		
		northRegion();
		eastRegion();
		southRegion();
		westRegion();
		centerRegion();
		
		gw.init();
		this.show();
		gw.createSounds();
	
		GameWorld.setWidth(mp.getWidth());
		GameWorld.setHeight(mp.getHeight());
		
		mp.setMapViewOrigin(new Point(mp.getX(), mp.getY()));
		MapView.setMapViewWidth(mp.getWidth());
		MapView.setMapViewHeight(mp.getHeight());
		
		timer = new UITimer(this);
		timer.schedule(timerTime, true, this);
	}
	
	public boolean getPauseFlag() {
		return pauseFlag;
	}
	
	public void setPauseFlag(boolean pauseFlag) {
		this.pauseFlag = pauseFlag;
	}
	
	/*
	 * This northRegion() method holds all of the information
	 * that is located in the top-most container.  This container
	 * holds the hamburger menu, the title, the help link, and
	 * the ScoreView graphical attributes.
	 */
	private void northRegion() {
		this.addComponent(BorderLayout.NORTH, sv);
		
		toolBar = new Toolbar();
		setToolbar(toolBar);
		toolBar.setTitle("ThePath Game");
		
		soundCheckBox = new CheckBox("Side Menu Item Check");
		soundCheckBox.getAllStyles().setBgTransparency(255);
		soundCheckBox.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		
		soundCommand = new SoundCommand(gw);
		soundCheckBox.setCommand(soundCommand);
		toolBar.addComponentToSideMenu(soundCheckBox);
		
		accelerateCommand = new AccelerateCommand(gw);
		toolBar.addCommandToLeftSideMenu(accelerateCommand);
		
		aboutCommand = new AboutCommand(gw);
		toolBar.addCommandToLeftSideMenu(aboutCommand);
		
		helpCommand = new HelpCommand(gw);
		toolBar.addCommandToRightBar(helpCommand);
		
		exitCommand = new ExitCommand();
		toolBar.addCommandToLeftSideMenu(exitCommand);
	}
	
	/*
	 * This westRegion() method holds the 'accelerate' and 'left'
	 * buttons that are respectively key-binded.  The container has
	 * the width of the largest button, which is 'accelerate' in our
	 * case.
	 */
	private void westRegion() {
		westContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		
		westContainer.getAllStyles().setPadding(Component.TOP, 50);
		westContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		
		accelerateCommand = new AccelerateCommand(gw);
		accelerateButton = new Button(accelerateCommand);
		
		leftCommand = new LeftCommand(gw);
		leftButton = new Button(leftCommand);
		
		westContainer.add(accelerateButton);
		buttonStyling(accelerateButton, true, false, true);
		
		westContainer.add(leftButton);
		buttonStyling(leftButton, false, false, true);
		
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
		eastContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		
		eastContainer.getAllStyles().setPadding(Component.TOP, 50);
		eastContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		
		breakCommand = new BreakCommand(gw);
		breakButton = new Button(breakCommand);
		
		rightCommand = new RightCommand(gw);
		rightButton = new Button(rightCommand);
		
		eastContainer.add(breakButton);
		buttonStyling(breakButton, true, false, true);
		
		eastContainer.add(rightButton);
		buttonStyling(rightButton, false, false, true);
		
		breakButton.setCommand(breakCommand);
		addKeyListener('b', breakCommand);
		
		rightButton.setCommand(rightCommand);
		addKeyListener('r', rightCommand);
		
		add(BorderLayout.EAST, eastContainer);
	}
	
	/*
	 * Finally, the southRegion() method holds the following buttons:
	 * 'Position' and 'Play/Pause'.  The Position button will only be
	 * invoked when pressed when the game is in 'pause' mode.  The Position
	 * button will only be effective if a fixed game object is selected first -
	 * there is no effect on the game if the Position button is selected
	 * before an object is selected.
	 * 
	 * The Play button will display Pause if selected, and all key bindings and
	 * respective buttons will be disabled if the game is in pause mode.  All
	 * game objects will stop moving and the ScoreView information will also 'pause'.  
	 */
	private void southRegion() {
		southContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
		
		southContainer.getAllStyles().setPadding(Component.TOP, 50);
		southContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		
		positionCommand = new PositionCommand(gw);
		positionButton = new Button(positionCommand);
		
		pauseCommand = new PauseCommand(gw, this);
		pauseButton = new Button(pauseCommand);
		
		southContainer.add(positionButton);
		buttonStyling(positionButton, false, true, false);
		
		southContainer.add(pauseButton);
		buttonStyling(pauseButton, false, false, true);
		
		add(BorderLayout.SOUTH, southContainer);
	}
	
	/*
	 * The centerRegion() method holds the MapView class,
	 * which only has a red border for now.
	 */
	private void centerRegion() {
		this.addComponent(BorderLayout.CENTER, mp);
	}
	
	@Override
	public void run() {
		gw.tick();
	}
	
	public void pausedGame() {
		if(!this.getPauseFlag()) {
			timer.cancel();
			pauseButton.setText("Play");
			gw.turnOffBackgroundSound();
			
			removeKeyListener('a', accelerateCommand);
			removeKeyListener('b', breakCommand);
			removeKeyListener('l', leftCommand);
			removeKeyListener('r', rightCommand);
			
			accelerateButton.getDisabledStyle().setBgColor(ColorUtil.BLUE);
			breakButton.getDisabledStyle().setBgColor(ColorUtil.BLUE);
			leftButton.getDisabledStyle().setBgColor(ColorUtil.BLUE);
			rightButton.getDisabledStyle().setBgColor(ColorUtil.BLUE);
			
			accelerateCommand.setEnabled(false);
			breakCommand.setEnabled(false);
			leftCommand.setEnabled(false);
			rightCommand.setEnabled(false);
			
			soundCheckBox.setEnabled(false);
			
			accelerateButton.setEnabled(false);
			breakButton.setEnabled(false);
			leftButton.setEnabled(false);
			rightButton.setEnabled(false);
			
			positionCommand.setEnabled(true);
			positionButton.setEnabled(true);
			
			setPauseFlag(true);
		} else {
			timer.schedule(timerTime, true, this);
			pauseButton.setText("Pause");
			gw.turnOnBackgroundSound();
			
			addKeyListener('a', accelerateCommand);
			addKeyListener('b', breakCommand);
			addKeyListener('l', leftCommand);
			addKeyListener('r', rightCommand);
			
			accelerateCommand.setEnabled(true);
			breakCommand.setEnabled(true);
			leftCommand.setEnabled(true);
			rightCommand.setEnabled(true);
			
			soundCheckBox.setEnabled(true);
			
			accelerateButton.setEnabled(true);
			breakButton.setEnabled(true);
			leftButton.setEnabled(true);
			rightButton.setEnabled(true);
			
			positionCommand.setEnabled(false);
			positionButton.setEnabled(false);
			
			gw.checkSelected();
			
			setPauseFlag(false);
		}
	}
	
	/*
	 * This method takes care of the appropriate styling for
	 * the buttons.  There are parameters present in order to 
	 * reuse code and only use one method.
	 */
	private Button buttonStyling(Button button, boolean top, boolean left, boolean backgroundColor) {
		if(top) {
			button.getAllStyles().setMarginTop(100);	
		} else if(left) {
			button.getAllStyles().setMarginLeft(900);
		}
		
		if(backgroundColor) {
			button.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
			button.getAllStyles().setFgColor(ColorUtil.WHITE);
		} else {
			button.getUnselectedStyle().setBgColor(ColorUtil.WHITE);
			button.getAllStyles().setFgColor(ColorUtil.BLUE);
		}
		
		button.getAllStyles().setPadding(5, 5, 2, 2);
		button.getAllStyles().setBgTransparency(255);
		button.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		
		return button;
	}
}
