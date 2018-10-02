package com.mycompany.a1;
import java.util.Random;

public class SpaceStation extends FixedObject
{
	private int blinkRate;
	private boolean blinking = true;
	
	public SpaceStation()
	{
		super(255,0,255);
		Random rand = new Random();
		blinkRate = rand.nextInt(11); //random blink rate from 0-10
	}
	//returns whether the station is blinking
	public boolean isBlinking()
	{
		return blinking;
	}
	//turn blinking on or off
	public void blinkSwitch()
	{
		if (blinking)
		{
			blinking = false;
		} else
		{
			blinking = true;
		}
	}
	public int getBlinkRate()
	{
		return blinkRate;
	}
	public void setBlinkRate(int b)
	{
		System.out.println("Error: Cannot change blink rate.");
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ("Station: loc=" + super.getLocationXValue() + "," + super.getLocationYValue() + " color=[" + super.getRedColor() + "," + super.getGreenColor() + "," + super.getBlueColor() + "] rate="+ getBlinkRate());
	}
}
