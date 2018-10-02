package com.mycompany.a2;

public interface IGameWorld {
	public int getMissiles();
	public int getClock();
	public int getScore();
	public boolean getSound();
	public void printMap();
	public SpaceCollection getGameObjects();
	public int getLives();
}
