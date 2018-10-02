package com.mycompany.a2;

import java.util.Vector;

import com.codename1.ui.Dialog;
import com.codename1.ui.Display;

import java.util.Observable;
import java.util.Observer;

public class GameWorld extends Observable implements IGameWorld
{
	
	private int shipAmount;
	private int score;
	private int lives;
	private int clock;
	private boolean sound;
	
	private Ship player;
	private SpaceCollection gameObjectCollection; //for object storage
	private GameWorldProxy myProxy;
	private Vector<Observer> myObserverList = new Vector<Observer>();

	public void init()
	{
		gameObjectCollection = new SpaceCollection(); //clear list of game objects
		score = 0;
		lives = 3;
		clock = 0;
		shipAmount = 0;
		updateWorld();
	}
	
	public GameWorld() //initialize gameobject collection to store gameobjects in
	{
		gameObjectCollection = new SpaceCollection();
		this.init();
	}
	public void createShip()
	{
		
		if (shipAmount == 0) //if no ships, then create a ship
		{
			player = new Ship();
			gameObjectCollection.add(player);
			shipAmount++; //add ship to collection and increment ship counter
			System.out.println("Ship created.");
			//System.out.println(player.toString()); //for testing
		} else 
		{
			System.out.println("Error: can only have one ship in the game.");
		}
		updateWorld();
	}
	public void createAsteroid() //create asteroid object and add to collection
	{
		Asteroid asteroid = new Asteroid();
		gameObjectCollection.add(asteroid);
		System.out.println("Asteroid created.");
		//System.out.println(asteroid.toString()); for testing
		updateWorld();
	}
	public void createMissile()
	{
		if (shipAmount == 0)
		{
			System.out.println("Error: No ships found to fire missile from.");
		} else 
		{
			//get location of ship because missile fires from ship
			double xCoord = player.getLocationXValue(); 
			double yCoord = player.getLocationYValue();
			int direction = player.getDirection();
			int speed = player.getSpeed();
			if(getMissiles() > 0)
			{
				player.fireMissile();
				Missile missile = new Missile(xCoord, yCoord, direction, speed);
				gameObjectCollection.add(missile);
				System.out.println("Missile fired.");
			} else {
				System.out.println("Error: Ship is out of missiles. None fired.");
			}
		}
		updateWorld();
	}
	public void createSpaceStation()
	{
		//create space station and add to collection
		SpaceStation station = new SpaceStation();
		gameObjectCollection.add(station);
		System.out.println("Space Station created.");
		//System.out.println(station.toString());	 for testing
		updateWorld();
	}

	public void steerLeft()
	{
		if (shipAmount <= 0)
		{
			System.out.println("Error: Cannot steer, no ship in game.");
		} else
		{
			player.steerLeft();
			System.out.println("Steered left.");
			//System.out.println(player.toString()); //for testing
		}
		updateWorld();
	}
	public void steerRight()
	{
		if (shipAmount <= 0)
		{
			System.out.println("Error: Cannot steer, no ship in game.");
		} else
		{
			player.steerRight();
			System.out.println("Steered right.");
			//System.out.println(player.toString()); //for testing
		}
		updateWorld();
	}
	public void jump()
	{
		if (shipAmount <= 0)
		{
			System.out.println("Error: Cannot jump, no ship in game.");
		} else
		{
			player.defaultLocation();
			System.out.println("Jumped to default location.");
			//System.out.println(player.toString()); for testing
		}
		updateWorld();
	}
	public void clockTick()
	{
		//iterate through collection and perform required action updates in game
		IIterator aIterator = gameObjectCollection.getIterator();
		while(aIterator.hasNext())
		{
			GameObject item = (GameObject) aIterator.getNext();
			if (item instanceof MoveableObject)
				{
					MoveableObject move = (MoveableObject)item; //update location of moveable objects
					move.move();
				}
			if(item instanceof Missile)
			{
				((Missile) item).lowerFuel(); //lower missile fuel level
				if (((Missile) item).getFuelLevel() == 0)//when missile runs out of fuel, destroy
				{
					gameObjectCollection.remove(item);
				}
			}
			if(item instanceof SpaceStation)
			{
				int rate = ((SpaceStation) item).getBlinkRate(); //toggle space station blinking
				if (clock % rate == 0)
				{
					((SpaceStation) item).blinkSwitch();
				}
			}
		}
		clock++; //incremement clock counter
		System.out.println("Clock ticked");
		updateWorld();	
	}
	public void increaseSpeed()
	{
		if (shipAmount == 0)
		{
			System.out.println("Error: No ship to increase speed.");
		} else 
		{
			player.increaseSpeed();
			System.out.println("Speed increased.");
			//System.out.println(player.toString()); //for testing

		}
		updateWorld();
	}
	public void decreaseSpeed()
	{
		if (shipAmount == 0)
		{
			System.out.println("Error: No ship to decrease speed.");
		} else 
			{
				player.decreaseSpeed();
				System.out.println("Speed decreased.");
				//System.out.println(player.toString()); //for testing

			}
		updateWorld();
	}
	public void reloadMissiles()
	{
		if (shipAmount <= 0)
		{
			System.out.println("Error: Cannot reload, no ship in game.");
		} else
		{
			player.reloadMissiles(); //reload missiles to 10
			System.out.println("Missiles reloaded.");
			//System.out.println(player.toString()); for testing
		}
		updateWorld();
	}
	public void shipCrashed()
	{
		int asteroidAmount = countAsteroids();
		
		if (shipAmount <= 0 || asteroidAmount <= 0)
		{
			System.out.println("Error: Either no ship or asteroid in game, cannot crash.");
		} else
		{
			deleteAnAsteroid();
			//delete ship
			gameObjectCollection.remove(player);
			shipAmount--;
			lives--;
			System.out.println("Ship crashed into an asteroid.");
			if (lives <= 0)
			{
				endGame(); //method that either quits or restarts game
			}
		}
		updateWorld();
	}
	public void killAsteroid()
	{
		int missileCount = player.getMissileCount();
		int asteroidAmount = countAsteroids();
		
		if (asteroidAmount <= 0 || missileCount <= 0)
		{
			System.out.println("Error: either no asteroids or missiles in the game.");
		} else
		{
			deleteAnAsteroid();
			deleteAMissile();
			score++;
			missileCount--;
			player.setMissileCount(missileCount);
			System.out.println("A missile struck and killed an asteroid.");
			//killed asteroid with a missile, score goes up
		}
		updateWorld();
	}
	public void asteroidsCollided()
	{
		int asteroidAmount = countAsteroids();
		if (asteroidAmount < 2)
		{
			System.out.println("Error: less than 2 asteroids in the game.");
		} else
		{
			deleteAnAsteroid();
			deleteAnAsteroid();
			System.out.println("Two asteroids collided and exterminated each other.");
		}
		updateWorld();
	}
	public void deleteAnAsteroid()
	{
		IIterator aIterator = gameObjectCollection.getIterator();
		while (aIterator.hasNext()) 
		{ //remove 1 asteroid from collection
			GameObject item = (GameObject) aIterator.getNext();
			if(item instanceof Asteroid) {
				gameObjectCollection.remove(item);
				break;
			}
		}	
		updateWorld();
	}
	public void deleteAMissile()
	{
		IIterator aIterator = gameObjectCollection.getIterator();
		while (aIterator.hasNext()) 
		{ //remove 1 missile from collection
			GameObject item = (GameObject) aIterator.getNext();
			if(item instanceof Missile) {
				gameObjectCollection.remove(item);
				break;
			}
		}
		updateWorld();
	}
	public void printDisplay()
	{
		System.out.println("Score: " + score); //print score
		System.out.println("Missile Count: " + player.getMissileCount());
		System.out.println("Elapsed Time: " + clock);
	}
	
	public void printMap()
	{
		System.out.println("Displaying Map:");
        System.out.println( "Map Height: " + Game.getMapHeight() + "  Map Width: " + Game.getMapWidth() );
		//This method will display all the location, size, color, speed, direction
		IIterator aIterator = gameObjectCollection.getIterator();
		while(aIterator.hasNext())
		{
			GameObject item = (GameObject) aIterator.getNext();
			System.out.println(item.toString());
			
		}
	}
	//end game or start over?
	public void endGame()
	{
		Boolean quitOrRestart = Dialog.show("Game Over", "Score: " + score + "  Time: " + clock + "\nDo you want to quit or restart?", "Restart", "Quit");
		System.out.println("Quit command invoked.");
		if (quitOrRestart)
		{
			this.init();
		} else
		{
			Display.getInstance().exitApplication();
		}
		
		System.out.println("Game Over, do you want to quit or restart?");
	}
	//count the number of asteroids in the game
	public int countAsteroids()
	{
		//iterate through game object collection and add to counter whenever an asteroid is found
		int asteroidCounter = 0;
		IIterator aIterator = gameObjectCollection.getIterator();
		while(aIterator.hasNext())
		{
			GameObject item = (GameObject) aIterator.getNext();
			if(item instanceof Asteroid) 
			{
				asteroidCounter++;
			}
			
		}
		return asteroidCounter;
	}
	public void updateWorld()
	{
		//setChanged();
		notifyObservers();
	}
	//getter method for sound value
	public boolean getSound()
	{
		return sound;
	}
	//setter method for sound 
	public void setSound(boolean b)
	{
		this.sound = b;
		this.updateWorld();
	}
	
	public int getClock()
	{
		return clock;
	}
	public int getLives()
	{
		return lives;
	}
	public int getScore()
	{
		return score;
	}
	public int getMissiles()
	{
		int missiles = 0; //set to 0 before ship is created to prevent null pointer exception in points view
		if (shipAmount > 0) //otherwise, list amount of missiles ship has
		{
			missiles = player.getMissileCount();
		}
		return missiles;
	}
	public SpaceCollection getGameObjects()
	{
		return gameObjectCollection;
	}
	
	public void addObserver(Observer o)
	{
		myObserverList.add(o);
	}
	public void notifyObservers()
	{
		this.setChanged();
		myProxy = new GameWorldProxy(this);
		for(Observer o : myObserverList)
		{
			o.update((Observable)myProxy, null);
		}
	}
}
