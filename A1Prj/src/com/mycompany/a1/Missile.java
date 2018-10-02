package com.mycompany.a1;

public class Missile extends MoveableObject
{
	
	private int fuelLevel;
	
	public Missile(double x, double y, int d, int s)
	{
		super(x, y, 255, 255, 255);
		super.setDirection(d);
		super.setSpeed(s+1);
		setFuelLevel(10);
	}
	
	@Override
	public void setSpeed(int s) //enforce speed constraint
	{
		System.out.println("Error: Cannot change missile speed.");
	}
	
	@Override
	public void setDirection(int d) //enforce direction constraint
	{
		System.out.println("Error: Cannot change missile direction.");
	}
	
	public int getFuelLevel()
	{
		return fuelLevel;
	}
	public void setFuelLevel(int f)
	{
		if (f>10)
		{
			fuelLevel = 10;
		} else if (f < 0)
		{
			fuelLevel = 0;
		} else 
		{
			fuelLevel = f;
		}
	}
	public void lowerFuel()
	{
		fuelLevel--;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ("Missile: loc=" + super.getLocationXValue() + "," + super.getLocationYValue() + " color=[" + super.getRedColor() + "," + super.getGreenColor() + "," + super.getBlueColor() + "] speed=" + super.getSpeed() + " dir=" + super.getDirection() + " fuel="+ getFuelLevel());
	}
}
