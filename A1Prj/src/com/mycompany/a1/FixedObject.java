package com.mycompany.a1;

public abstract class FixedObject extends GameObject
{
	private static int iD = 0; //unique id
	
	//create object and set colors for fixed objects (space stations)
	public FixedObject(int r, int g, int b)
	{
		super(r,g,b);
		iD++; //id goes up by one every time fixed object is created, making it unique
	}
	@Override //prevent fixed location object from moving
	public void setLocationValue(double x, double y)
	{
		System.out.println("Error: Cannot move fixed object.");
	}
	public void setID(int i) //enforce constraint
	{
		System.out.println("Error: Cannot change ID.");
	}
	public int getID()
	{
		return iD;
	}
}
