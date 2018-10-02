package com.mycompany.a1;


public abstract class MoveableObject extends GameObject
{
	//moveable attributes
	private int speed;
	private int direction;
	
	public MoveableObject(double x, double y, int r, int g, int b) //constructor for manually assigned location (for ships and missiles)
	{
		super(x, y, r, g, b);
	}
	public MoveableObject(int r, int g, int b) // randomly assigned location (for asteroid)
	{
		super(r, g, b);
	}
	
	//getters for attributes
	public int getSpeed()
	{
		return speed;
	}
	public int getDirection()
	{
		return direction;
	}
	
	//setters for attributes
	public void setSpeed(int s)
	{
		if (s < 0)
		{
			speed = 0;
		}
		else if (s > 10)
		{
			speed = 10;
		} else 
		{
			speed = s;
		}
	}
	public void setDirection(int d)
	{
		if(d < 360 && d >= 0)//value must be between 0 and 359
		{
			direction = d;
		}
		else if(d >= 360.0)//if direction is greater than 359 
		{
			direction = d%360;
		}
		else//if direction is less than 0 
		{
			direction = d%360+360;
		}
	}
	
	public void move()
	{ //use move calculation and round
		double oldX = super.getLocationXValue();
		double oldY = super.getLocationYValue();
		
		double theta = Math.toRadians(90 - direction);
		double deltaX = Math.round(Math.cos(theta) * 10.0 * speed)/ 10.0; 
		double deltaY = Math.round(Math.sin(theta) * 10.0 * speed)/ 10.0;
		
		double newX = Math.round((oldX + deltaX) * 10.0)/ 10.0; 
		double newY = Math.round((oldY + deltaY) * 10.0)/ 10.0; 
		super.setLocationValue(newX, newY); //update location
	}
}
