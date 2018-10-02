package com.mycompany.a1;

import java.util.Random;

public class Asteroid extends MoveableObject{
	private int size;
	
	public Asteroid() //create asteroid. Location is randomly generated in the super class.
	{
		super(0,0,255);
		Random rand = new Random();
		size = rand.nextInt(30);
		super.setDirection(rand.nextInt(360)); //random direction 0 and 359
		super.setSpeed(rand.nextInt(11)); //random speed between 0-10 generated 
	}
	
	//enforce constraints on asteroid
	@Override
	public void setSpeed(int s)
	{
		System.out.println("Error: Cannot change speed of asteroid.");
	}
	@Override
	public void setDirection(int d)
	{
		System.out.println("Error: Cannot change direction of asteroid.");
	}	
	
	public void setSize(int l)
	{
		System.out.println("Error: Cannot change size of asteroid.");
	}
	public int getSize()
	{
		return size;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ("Asteroid: loc=" + super.getLocationXValue() + "," + super.getLocationYValue() + " color=[" + super.getRedColor() + "," + super.getGreenColor() + "," + super.getBlueColor() + "] speed=" + super.getSpeed() + " dir=" + super.getDirection() + " size="+ getSize());
	}

}
