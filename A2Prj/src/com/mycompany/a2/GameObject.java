package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import java.util.Random;

public abstract class GameObject {

	//location
	private double locationXValue; //between 0.0 and 1024.0
	private double locationYValue; //between 0.0 and 768.0
	
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
		locationXValue = (double)rand.nextInt(1024);
		locationYValue = (double)rand.nextInt(768);
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
	
	//setter methods
	//location
	public void setLocationValue(double x, double y)
	{
		//verify within x bounds
		if (x >= 0.0 && x <= 1024.0)
		{
			locationXValue = x;
		} else if (x > 1024.0)
		{
			locationXValue = 1024.0;
		} else 
		{
			locationXValue = 0.0;
		}
		
		//verify within y bounds
		if (y >= 0.0 && y <= 768)
		{
			locationYValue = y;
		} else if (y > 768.0)
		{
			locationYValue = 768.0;
		} else
		{
			locationYValue = 0.0;
		}

	}
	//color
	public void setColor(int red, int green, int blue)
	{
		color = ColorUtil.rgb(red, green, blue);
	}
	
	public abstract String toString();

}
