package com.mycompany.a2;

public class Ship extends MoveableObject implements ISteerable 
{
	private int missileCount;
	private final static double DEFAULTX = 512.0;
	private final static double DEFAULTY = 384.0;
	public Ship ()
	{
		super(DEFAULTX, DEFAULTY, 0, 0, 0);
		super.setSpeed(0);
		super.setDirection(0);	
		setMissileCount(10);
	}
	public void increaseSpeed()
	{
		super.setSpeed(getSpeed()+1);
	}
	public void decreaseSpeed()
	{
		super.setSpeed(getSpeed()-1);
	}
	public int getMissileCount()
	{
		return missileCount;
	}
	public void setMissileCount(int m)
	{
		missileCount = m;
	}
	public void reloadMissiles()
	{
		setMissileCount(10);
	}
	public void fireMissile()
	{
		if (missileCount > 0 && missileCount <= 10) //ship has missiles, so decrement
		{
			missileCount--;
		} else {}
	}
	public void defaultLocation() //jump to the middle of the screen
	{
		super.setLocationValue(DEFAULTX, DEFAULTY);
	}
	public void steerLeft() //move by 5 degrees
	{
		super.setDirection(super.getDirection()- 5);
	}
	public void steerRight()
	{
		super.setDirection(super.getDirection() + 5);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ("Ship: loc=" + super.getLocationXValue() + "," + super.getLocationYValue() + " color=[" + super.getRedColor() + "," + super.getGreenColor() + "," + super.getBlueColor() + "] speed=" + super.getSpeed() + " dir=" + super.getDirection() + " missiles="+ getMissileCount());
	}
}
