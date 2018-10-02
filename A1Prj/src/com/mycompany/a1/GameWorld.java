package com.mycompany.a1;

import java.util.Vector;
import java.util.Random;
import java.util.Iterator;

public class GameWorld {
	
	//boundaries
	private final double leftBoundX = 0.0;
	private final double rightBoundX = 1024.0;
	private final double upperBoundY = 768.0;
	private final double lowerBoundY = 0.0 ;
	private int shipAmount = 0;
	
	private int score = 0;
	private int lives = 3;
	private int clock = 0;

	private Ship player;
	private SpaceCollection gameObjectCollection; //for object storage
	
	public void init()
	{
	
	}
	
	public GameWorld() //initialize gameobject collection to store gameobjects in
	{
		gameObjectCollection = new SpaceCollection();
	}
	public void createShip()
	{
		
		if (shipAmount == 0) //if no ships, then create a ship
		{
			player = new Ship();
			gameObjectCollection.add(player);
			shipAmount++; //add ship to collection and increment ship counter
			//System.out.println(player.toString()); for testing
		} else 
		{
			System.out.println("Error: can only have one ship in the game.");
		}

	}
	public void createAsteroid() //create asteroid object and add to collection
	{
		Asteroid asteroid = new Asteroid();
		gameObjectCollection.add(asteroid);
		//System.out.println(asteroid.toString()); for testing
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
			if(player.getMissileCount() > 0)
			{
				player.fireMissile();
				Missile missile = new Missile(xCoord, yCoord, direction, speed);
				gameObjectCollection.add(missile);
			} else {
				System.out.println("Error: Ship is out of missiles. None fired.");
			}
		}
		
	}
	public void createSpaceStation()
	{
		//create space station and add to collection
		SpaceStation station = new SpaceStation();
		gameObjectCollection.add(station);
		//System.out.println(station.toString());	 for testing
	}

	public void steerLeft()
	{
		player.steerLeft();
		// System.out.println(player.toString()); for testing
	}
	public void steerRight()
	{
		player.steerRight();
		//System.out.println(player.toString()); for testing
	}
	public void jump()
	{
		player.defaultLocation();
		//System.out.println(player.toString()); for testing
	}
	public void clockTick()
	{
		//iterate through collection and perform required action updates in game
		Iterator<GameObject> aIterator = gameObjectCollection.getIterator();
		Object curObject = new Object();
		
		while(aIterator.hasNext())
		{
			GameObject item = aIterator.next();
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
		System.out.println("Clock has ticked");
			
	}
	public void increaseSpeed()
	{
		if (shipAmount == 0)
		{
			System.out.println("Error: No ship to increase speed.");
		} else 
		{
			player.increaseSpeed();
			//System.out.println(player.toString()); for testing

		}
	}
	public void decreaseSpeed()
	{
		if (shipAmount == 0)
		{
			System.out.println("Error: No ship to decrease speed.");
		} else 
			{
				player.decreaseSpeed();
				//System.out.println(player.toString()); for testing

			}
	}
	public void reloadMissiles()
	{
		player.reloadMissiles(); //reload missiles to 10
		//System.out.println(player.toString()); for testing
	}
	public void shipCrashed()
	{
		deleteAnAsteroid();
		//delete ship
		gameObjectCollection.remove(player);
		shipAmount--;
		lives--;
		if (lives <= 0)
		{
			System.out.println("Game Over, press q to quit");
		}
	}
	public void killAsteroid()
	{
		deleteAnAsteroid();
		deleteAMissile();
		score++;
		//killed asteroid with a missile, score goes up
	}
	public void asteroidsCollided()
	{
		deleteAnAsteroid();
		deleteAnAsteroid();
		
	}
	public void deleteAnAsteroid()
	{
		Iterator<GameObject> aIterator = gameObjectCollection.getIterator();
		while (aIterator.hasNext()) 
		{ //remove 1 asteroid from collection
			GameObject item = aIterator.next();
			if(item instanceof Asteroid) {
				gameObjectCollection.remove(item);
				break;
			}
		}	
	}
	public void deleteAMissile()
	{
		Iterator<GameObject> aIterator = gameObjectCollection.getIterator();
		while (aIterator.hasNext()) 
		{ //remove 1 missile from collection
			GameObject item = aIterator.next();
			if(item instanceof Missile) {
				gameObjectCollection.remove(item);
				break;
			}
		}
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
		
		//This method will display all the location, size, color, speed, direction
		Iterator<GameObject> aIterator = gameObjectCollection.getIterator();
		while(aIterator.hasNext())
		{
			{
			GameObject item = aIterator.next();
			System.out.println(item.toString());
			}
			
		}
	}
	public void quitAlert()
	{
		System.out.println("Are you sure you want to quit?");
	}
	public void quit()
	{
		System.exit(0);
	}
}
