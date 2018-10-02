/* Pavel Verbovshchuk
 * CSC 133
 * A2Prj
 * Second version of asteroids game. Gui added and error checking and gameplay refined.
 */

package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;



import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;

public class Game extends Form
{
	private GameWorld gw;
	private MapView mv;
	private PointsView pv;
	private static int mapWidth;
	private static int mapHeight;
	
	
	public Game() 
	{
		gw = new GameWorld();//create world "observable"
		mv = new MapView(); //create an "Observer" for the map
		pv = new PointsView(); //create an "observer" for the points
		gw.addObserver(mv); //register map observer
		gw.addObserver(pv); //register points observer
		
		//the outermost layout  will be border layout
		setLayout(new BorderLayout());
		
		//create toolbar
		Toolbar myToolbar = new Toolbar();
		setToolbar(myToolbar);
		
		Command newCommand = new Command("New"); 
		myToolbar.addCommandToSideMenu(newCommand);
		Command saveCommand = new Command("Save"); 
		myToolbar.addCommandToSideMenu(saveCommand);
		Command undoCommand = new Command("Undo"); 
		myToolbar.addCommandToSideMenu(undoCommand);
		
		//sound checkbox in side menu for sound on/off
		CheckBox soundCheck = new CheckBox("Sound");
		soundCheck.getAllStyles().setBgTransparency(255);
		soundCheck.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		soundCheck.getAllStyles().setPadding(TOP, 2);
		soundCheck.getAllStyles().setPadding(BOTTOM, 2);
		SoundCommand mySoundCommand = new SoundCommand(gw); 
		soundCheck.setCommand(mySoundCommand);
		myToolbar.addComponentToSideMenu(soundCheck);
		
		//about command in side menu bar, displays information 
		AboutCommand aboutCommand = new AboutCommand(); 
		myToolbar.addCommandToSideMenu(aboutCommand);
		
		//quit command in side menu bar, same as the one in the command menu
		ExitCommand quitNow = new ExitCommand();
		myToolbar.addCommandToSideMenu(quitNow);
		
	
		//toolbar
		myToolbar.setTitle("Asteroids Game");
		//help menu
		HelpCommand helpMenu = new HelpCommand();
		myToolbar.addCommandToRightBar(helpMenu);
		
		//north container (pointsview)
		Container northContainer = new Container();
		northContainer.setLayout(new FlowLayout());
		northContainer = FlowLayout.encloseCenter(pv);
		this.add(BorderLayout.NORTH,northContainer);
		
		//center container (mapview)		
		Container centerContainer = new Container();
		centerContainer.getAllStyles().setBorder(Border.createLineBorder( 4, ColorUtil.BLACK));
		centerContainer.getAllStyles().setBgTransparency(255);
		centerContainer.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		centerContainer.add(mv);
		this.add(BorderLayout.CENTER, centerContainer);
				
		//left Container with the BoxLayout positioned on the west 
		Container leftContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		//create buttons
		Label commands = new Label("Commands:");
		Button addAsteroid = new Button("Add Asteroid");
		Button addStation = new Button("Add Station");
		Button addShip = new Button("Add Ship");
		Button increase = new Button("Increase");
		Button decrease = new Button("Decrease");
		Button left = new Button("Left");
		Button right = new Button("Right");
		Button fire = new Button("Fire");
		Button jump = new Button("Jump");
		Button loadMissiles = new Button("Load Missiles");
		Button killAsteroid = new Button("Kill Asteroid");
		Button crashShip = new Button("Crash Ship");
		Button exterminate = new Button("Exterminate");
		Button tick = new Button("Tick");
		Button quit = new Button("Quit");

		//styling
		addAsteroid.getUnselectedStyle().setBgColor(ColorUtil.GRAY);
		addAsteroid.getUnselectedStyle().setFgColor(ColorUtil.BLACK);
		addAsteroid.getAllStyles().setPadding(TOP, 2);
		addAsteroid.getAllStyles().setPadding(BOTTOM, 2);
		addAsteroid.getAllStyles().setPadding(LEFT, 5);
		addAsteroid.getAllStyles().setPadding(RIGHT, 5);
		addAsteroid.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		
		addStation.getUnselectedStyle().setBgColor(ColorUtil.GRAY);
		addStation.getUnselectedStyle().setFgColor(ColorUtil.BLACK);
		addStation.getAllStyles().setPadding(TOP, 2);
		addStation.getAllStyles().setPadding(BOTTOM, 2);
		addStation.getAllStyles().setPadding(LEFT, 5);
		addStation.getAllStyles().setPadding(RIGHT, 5);
		addStation.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));		
		
		addShip.getUnselectedStyle().setBgColor(ColorUtil.GRAY);
		addShip.getUnselectedStyle().setFgColor(ColorUtil.BLACK);
		addShip.getAllStyles().setPadding(TOP, 2);
		addShip.getAllStyles().setPadding(BOTTOM, 2);
		addShip.getAllStyles().setPadding(LEFT, 5);
		addShip.getAllStyles().setPadding(RIGHT, 5);
		addShip.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		
		increase.getUnselectedStyle().setBgColor(ColorUtil.GRAY);
		increase.getUnselectedStyle().setFgColor(ColorUtil.BLACK);
		increase.getAllStyles().setPadding(TOP, 2);
		increase.getAllStyles().setPadding(BOTTOM, 2);
		increase.getAllStyles().setPadding(LEFT, 5);
		increase.getAllStyles().setPadding(RIGHT, 5);
		increase.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		
		decrease.getUnselectedStyle().setBgColor(ColorUtil.GRAY);
		decrease.getUnselectedStyle().setFgColor(ColorUtil.BLACK);
		decrease.getAllStyles().setPadding(TOP, 2);
		decrease.getAllStyles().setPadding(BOTTOM, 2);
		decrease.getAllStyles().setPadding(LEFT, 5);
		decrease.getAllStyles().setPadding(RIGHT, 5);
		decrease.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));

		left.getUnselectedStyle().setBgColor(ColorUtil.GRAY);
		left.getUnselectedStyle().setFgColor(ColorUtil.BLACK);
		left.getAllStyles().setPadding(TOP, 2);
		left.getAllStyles().setPadding(BOTTOM, 2);
		left.getAllStyles().setPadding(LEFT, 5);
		left.getAllStyles().setPadding(RIGHT, 5);
		left.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		
		right.getUnselectedStyle().setBgColor(ColorUtil.GRAY);
		right.getUnselectedStyle().setFgColor(ColorUtil.BLACK);
		right.getAllStyles().setPadding(TOP, 2);
		right.getAllStyles().setPadding(BOTTOM, 2);
		right.getAllStyles().setPadding(LEFT, 5);
		right.getAllStyles().setPadding(RIGHT, 5);
		right.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		
		fire.getUnselectedStyle().setBgColor(ColorUtil.GRAY);
		fire.getUnselectedStyle().setFgColor(ColorUtil.BLACK);
		fire.getAllStyles().setPadding(TOP, 2);
		fire.getAllStyles().setPadding(BOTTOM, 2);
		fire.getAllStyles().setPadding(LEFT, 5);
		fire.getAllStyles().setPadding(RIGHT, 5);
		fire.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		
		jump.getUnselectedStyle().setBgColor(ColorUtil.GRAY);
		jump.getUnselectedStyle().setFgColor(ColorUtil.BLACK);
		jump.getAllStyles().setPadding(TOP, 2);
		jump.getAllStyles().setPadding(BOTTOM, 2);
		jump.getAllStyles().setPadding(LEFT, 5);
		jump.getAllStyles().setPadding(RIGHT, 5);
		jump.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		
		loadMissiles.getUnselectedStyle().setBgColor(ColorUtil.GRAY);
		loadMissiles.getUnselectedStyle().setFgColor(ColorUtil.BLACK);
		loadMissiles.getAllStyles().setPadding(TOP, 2);
		loadMissiles.getAllStyles().setPadding(BOTTOM, 2);
		loadMissiles.getAllStyles().setPadding(LEFT, 5);
		loadMissiles.getAllStyles().setPadding(RIGHT, 5);
		loadMissiles.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		
		killAsteroid.getUnselectedStyle().setBgColor(ColorUtil.GRAY);
		killAsteroid.getUnselectedStyle().setFgColor(ColorUtil.BLACK);
		killAsteroid.getAllStyles().setPadding(TOP, 2);
		killAsteroid.getAllStyles().setPadding(BOTTOM, 2);
		killAsteroid.getAllStyles().setPadding(LEFT, 5);
		killAsteroid.getAllStyles().setPadding(RIGHT, 5);
		killAsteroid.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		
		crashShip.getUnselectedStyle().setBgColor(ColorUtil.GRAY);
		crashShip.getUnselectedStyle().setFgColor(ColorUtil.BLACK);
		crashShip.getAllStyles().setPadding(TOP, 2);
		crashShip.getAllStyles().setPadding(BOTTOM, 2);
		crashShip.getAllStyles().setPadding(LEFT, 5);
		crashShip.getAllStyles().setPadding(RIGHT, 5);
		crashShip.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		
		exterminate.getUnselectedStyle().setBgColor(ColorUtil.GRAY);
		exterminate.getUnselectedStyle().setFgColor(ColorUtil.BLACK);
		exterminate.getAllStyles().setPadding(TOP, 2);
		exterminate.getAllStyles().setPadding(BOTTOM, 2);
		exterminate.getAllStyles().setPadding(LEFT, 5);
		exterminate.getAllStyles().setPadding(RIGHT, 5);
		exterminate.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		
		tick.getUnselectedStyle().setBgColor(ColorUtil.GRAY);
		tick.getUnselectedStyle().setFgColor(ColorUtil.BLACK);
		tick.getAllStyles().setPadding(TOP, 2);
		tick.getAllStyles().setPadding(BOTTOM, 2);
		tick.getAllStyles().setPadding(LEFT, 5);
		tick.getAllStyles().setPadding(RIGHT, 5);
		tick.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		
		quit.getUnselectedStyle().setBgColor(ColorUtil.GRAY);
		quit.getUnselectedStyle().setFgColor(ColorUtil.BLACK);
		quit.getAllStyles().setPadding(TOP, 2);
		quit.getAllStyles().setPadding(BOTTOM, 2);
		quit.getAllStyles().setPadding(LEFT, 5);
		quit.getAllStyles().setPadding(RIGHT, 5);
		quit.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		
		//add command functions to the buttons
		CreateAsteroidCommand myAsteroidCreator = new CreateAsteroidCommand(gw);
		addAsteroid.setCommand(myAsteroidCreator);
		
		CreateStationCommand myStationCreator = new CreateStationCommand(gw);
		addStation.setCommand(myStationCreator);
		
		CreateShipCommand myShipCreator = new CreateShipCommand(gw);
		addShip.setCommand(myShipCreator);
		
		IncreaseSpeedCommand mySpeedIncrease = new IncreaseSpeedCommand(gw);
		increase.setCommand(mySpeedIncrease);
		addKeyListener('w', mySpeedIncrease);
		
		DecreaseSpeedCommand mySpeedDecrease = new DecreaseSpeedCommand(gw);
		decrease.setCommand(mySpeedDecrease);
		addKeyListener('s', mySpeedDecrease);
		
		LeftTurnCommand myLeftTurn = new LeftTurnCommand(gw);
		left.setCommand(myLeftTurn);
		addKeyListener('a', myLeftTurn);
		
		RightTurnCommand myRightTurn = new RightTurnCommand(gw);
		right.setCommand(myRightTurn);
		addKeyListener('d', myRightTurn);
		
		FireMissileCommand myMissileFire = new FireMissileCommand(gw);
		fire.setCommand(myMissileFire);
		addKeyListener('f', myMissileFire);
		
		JumpCommand myJump = new JumpCommand(gw);
		jump.setCommand(myJump);
		addKeyListener('j', myJump);
		
		LoadMissilesCommand myMissileLoader = new LoadMissilesCommand(gw);
		loadMissiles.setCommand(myMissileLoader);
		
		KillAsteroidCommand myAsteroidKiller = new KillAsteroidCommand(gw);
		killAsteroid.setCommand(myAsteroidKiller);
		
		CrashShipCommand myCrashedShip = new CrashShipCommand(gw);
		crashShip.setCommand(myCrashedShip);
		
		ExterminateCommand myExterminated = new ExterminateCommand(gw);
		exterminate.setCommand(myExterminated);
		
		TickCommand myTicks = new TickCommand(gw);
		tick.setCommand(myTicks);
		
		quit.setCommand(quitNow);
		addKeyListener('x', quitNow);
		
		//start adding components at a location 50 pixels below the upper border of the container
		leftContainer.getAllStyles().setPadding(Component.TOP, 10);
		leftContainer.add(commands);
		leftContainer.add(addAsteroid);
		leftContainer.add(addStation);
		leftContainer.add(addShip);
		leftContainer.add(increase);
		leftContainer.add(decrease);
		leftContainer.add(left);
		leftContainer.add(right);
		leftContainer.add(fire);
		leftContainer.add(jump);
		leftContainer.add(loadMissiles);
		leftContainer.add(killAsteroid);
		leftContainer.add(crashShip);
		leftContainer.add(exterminate);
		leftContainer.add(tick);
		leftContainer.add(quit);

		leftContainer.getAllStyles().setBorder(Border.createLineBorder(4,
														ColorUtil.BLACK));
		add(BorderLayout.WEST,leftContainer);	
		//right Container with the GridLayout positioned on the east 
		Container rightContainer = new Container(new GridLayout(4,1));
		//...[add similar components that exists on the left container]
		add(BorderLayout.EAST,rightContainer);
		Container bottomContainer = new Container();
		add(BorderLayout.SOUTH, bottomContainer);
		this.show(); //display
		mapWidth = centerContainer.getWidth();
		mapHeight = centerContainer.getHeight();
		gw.updateWorld(); //update world

	}
	
	//Static method to get the width of the map
	public static int getMapWidth()
	{
		return mapWidth;
	}
	
	//Static method to get the height of the map
	public static int getMapHeight()
	{
		return mapHeight;
	}

}
