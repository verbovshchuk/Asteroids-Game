package com.mycompany.a3;

import com.mycompany.a3.IIterator;

public interface IGameWorld {
	public int getMissiles();
	public int getClock();
	public int getScore();
	public boolean getSound();
	public void printMap();
	public SpaceCollection getGameObjects();
	public int getLives();
	public IIterator getIterator();
	public boolean getPaused();
}
