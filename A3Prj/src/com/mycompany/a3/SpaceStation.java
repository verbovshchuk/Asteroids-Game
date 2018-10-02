package com.mycompany.a3;
import java.util.Random;
import java.util.Vector;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class SpaceStation extends FixedObject implements IDrawable, ICollider
{
	private int blinkRate;
	private boolean blinking = true;
	private static final int width = 10;
	public static final int height = 50;
	private boolean readyToRemove;
	private Vector<ICollider> collisionVector;
	
	public SpaceStation()
	{
		super(255,0,255);
		Random rand = new Random();
		blinkRate = rand.nextInt(100)+30; //random blink rate from 0-10
		readyToRemove = false;
		collisionVector = new Vector<ICollider>();
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
	public void draw(Graphics g, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		g.setColor(this.getColor());
		int x = (int) (pCmpRelPrnt.getX() + super.getLocationXValue());
		int y = (int) (pCmpRelPrnt.getY() + super.getLocationYValue());
		
		if(isBlinking())
		{
			g.fillRect(x, y, 10, 50);
			g.drawRect(x, y, 10, 50);
		} else
		{
			g.drawRect(x, y, 10, 50);
		}
		
	}
	public int handleCollision(ICollider other) {
		// TODO Auto-generated method stub
		if(collisionVector.contains(other))
			return 0;
		collisionVector.add(other);
		if(other instanceof Ship)
		{
			if(isBlinking())
				((Ship)other).reloadMissiles();
			return 2;
		}
		return -1;
	}
	public boolean collidesWith(ICollider obj) {
		// TODO Auto-generated method stub
		boolean result = false;
		
		int r1 = (int)(this.getLocationXValue()) + width/2;
		int l1 = (int)(this.getLocationXValue()) - width/2;
		int t1 = (int)(this.getLocationYValue()) + height/2;
		int b1 = (int)(this.getLocationYValue()) - height/2;
		int r2 = (int)((GameObject)obj).getLocationXValue() + ((GameObject)obj).getWidth()/2;
		int l2 = (int)((GameObject)obj).getLocationXValue() - ((GameObject)obj).getWidth()/2;
		int t2 = (int)((GameObject)obj).getLocationYValue() + ((GameObject)obj).getWidth()/2;
		int b2 = (int)((GameObject)obj).getLocationYValue() - ((GameObject)obj).getWidth()/2;
		
		int x = 0;
		int y = 0;
		
		if(r1 < l2 || l1 > r2)
			x = 1;
		if(t2 <b1 || t1 < b2)
			y = 1;
		if (x + y == 0)
			result = true;
		
		return result;
	}
	public void setDeath() {
		// TODO Auto-generated method stub
		readyToRemove = true;

	}
	public boolean isReadyToRemove() {
		// TODO Auto-generated method stub
		return readyToRemove;
	}
	public boolean checkVector(ICollider other) {
		// TODO Auto-generated method stub
		if(collisionVector.isEmpty())
			return false;
		return collisionVector.contains(other);
	}
	public void addToVector(ICollider other) {
		// TODO Auto-generated method stub
		collisionVector.add(other);

	}
	public void clearCollisions() {
		// TODO Auto-generated method stub
		if(collisionVector.size() > 0)
		{
			for(int i = 0; i < collisionVector.size(); i++  )
			{
				if(this.collidesWith(collisionVector.elementAt(i))==false)
					collisionVector.remove(i);
			}
		}
	}
	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return height;
	}
	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return width;
	}
}
