package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

import java.util.Random;

public abstract class GameObject {

	//location
	private double locationXValue; 
	private double locationYValue; 
	private static double gameWidth = GameWorld.getMapWidth();
	private static double gameHeight = GameWorld.getMapHeight();
			
	
	private int color;
	
	//constructors
	public GameObject(double x, double y, int r, int g, int b) //manual location set
	{
		locationXValue = x;
		locationYValue = y;
		color = ColorUtil.rgb(r, g, b);
	}
	public GameObject(int r, int g, int b) //random location set
	{
		Random rand = new Random();
		//setLocationValue((double)rand.nextInt((int)gameWidth-20)+10, (double)rand.nextInt((int)gameHeight-20)+10);
		locationXValue = (double)rand.nextInt((int)gameWidth-20)+10;
		locationYValue = (double)rand.nextInt((int)gameHeight-20)+10;
		color = ColorUtil.rgb(r, g, b);
		
	}
	
	//getter methods
	//location
	public double getLocationXValue()
	{
		return locationXValue;
	}
	public double getLocationYValue()
	{
		return locationYValue;
	}
	//color
	public int getRedColor()
	{
		return ColorUtil.red(color);
	}
	public int getGreenColor()
	{
		return ColorUtil.green(color);
	}
	public int getBlueColor()
	{
		return ColorUtil.blue(color);
	}
	public int getColor()
	{
		return color;
	}
	
	//setter methods
	//location
	public void setLocationValue(double x, double y)
	{
		//verify within x bounds
		if (x >= 0.0 && x <= Game.getMapWidth())
		{
			locationXValue = x;
		} else if (x > Game.getMapWidth())
		{
			locationXValue = Game.getMapWidth();
		} else 
		{
			locationXValue = 0.0;
		}
		
		//verify within y bounds
		if (y >= 0.0 && y <= Game.getMapHeight())
		{
			locationYValue = y;
		} else if (y > Game.getMapHeight())
		{
			locationYValue = Game.getMapHeight();
		} else
		{
			locationYValue = 0.0;
		}

	}
	public void moveLocation(double x, double y)
	{
		locationXValue = x;
		locationYValue = y;
	}
	//public abstract void draw(Graphics g);
	//color
	public void setColor(int red, int green, int blue)
	{
		color = ColorUtil.rgb(red, green, blue);
	}
	
	public abstract String toString();
	public static double getGameWidth() {
		return gameWidth;
	}
	public void setGameWidth(double gameWidth) {}
	public static double getGameHeight() {
		return gameHeight;
	}
	public void setGameHeight(double gameHeight) {}
	
	public abstract int getHeight();
	public abstract int getWidth();
}
