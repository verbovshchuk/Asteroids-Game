package com.mycompany.a3;

import java.util.Observable;

public class GameWorldProxy extends Observable implements IGameWorld 
{
	private GameWorld realGameWorld;
		
	public GameWorldProxy(GameWorld gw)
	{
		realGameWorld = gw;
	}
		
	public int getMissiles()
	{
		return realGameWorld.getMissiles();
	}
	
	public int getClock()
	{
		return realGameWorld.getClock();
	}
		
	public int getScore()
	{
		return realGameWorld.getScore();
	}
		
	public boolean getSound()
	{
		return realGameWorld.getSound();
	}
		
	public void printMap()
	{
		realGameWorld.printMap();
	}
	
	public SpaceCollection getGameObjects() 
	{
		return realGameWorld.getGameObjects();
	}
	public int getLives()
	{
		return realGameWorld.getLives();
	}
	public IIterator getIterator() 
	{
		return realGameWorld.getIterator();
	}
	public boolean getPaused()
	{
		return realGameWorld.getPaused();
	}
		

}
