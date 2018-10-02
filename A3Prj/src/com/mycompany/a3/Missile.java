package com.mycompany.a3;

import java.util.Vector;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Missile extends MoveableObject implements IDrawable, ISelectable, ICollider
{
	
	private int fuelLevel;
	private int maxFuel = 100;
	private boolean readyToRemove;
	private Vector<ICollider> collisionVector;
	private boolean isSelected;
	private int inverseX;
	private int inverseY;
	private static final int width = 10;
	private static final int height = 50;
	
	public Missile(double x, double y, int d, int s)
	{
		super(x, y, 150, 138, 7);
		super.setDirection(d);
		super.setSpeed(s+100);
		fuelLevel = maxFuel;
		readyToRemove = false;
		collisionVector = new Vector<ICollider>();
		isSelected = false;
		inverseX = 1;
		inverseY = 1;
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
	public boolean hasFuel()
	{
		if (fuelLevel == 0)
			return false;
		return true;
	}
	public void refuel()
	{
		fuelLevel = maxFuel;
	}
	public void setFuelLevel(int f)
	{
		if (f>maxFuel)
		{
			fuelLevel = maxFuel;
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

	public void draw(Graphics g, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		int myX = (int) (pCmpRelPrnt.getX() + this.getLocationXValue());
		int myY = (int) (pCmpRelPrnt.getY() + this.getLocationYValue());
		
		
		if(isSelected())
		{
			g.setColor(ColorUtil.YELLOW);
			//g.fillRect(myX - width/2, myY - height/2, width, height);
			g.drawRect(myX - width/2, myY - height/2, width, height);

		}
		else
		{
			g.setColor(this.getColor());
			g.fillRect(myX-width/2, myY-height/2, width, height);
			g.drawString(Integer.toString(getFuelLevel()), myX - width/2, myY - height/2);

		}
	}

	public boolean isSelected() {
		// TODO Auto-generated method stub
		return isSelected;
	}

	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		int px = (int)pPtrRelPrnt.getX();
		int py = (int)pPtrRelPrnt.getY();
		int xLoc = (int)(pCmpRelPrnt.getX() + (this.getLocationXValue()));
		int yLoc = (int)(pCmpRelPrnt.getY() + (this.getLocationYValue()));
		if((px >= xLoc-width/2) && (px <= xLoc + width/2)  && (py>=yLoc- height/2) && (py<= yLoc + height/2))
			return true; 
		else
			return false;
	}

	public void setSelected(boolean yesOrNo) {
		// TODO Auto-generated method stub
		isSelected = yesOrNo;

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

	public int handleCollision(ICollider other) {
		// TODO Auto-generated method stub
		if(collisionVector.contains(other))
			return 0;
		collisionVector.add(other);
		if(other instanceof Asteroid)
			return 3;
		return -1;	
		}

	public boolean collidesWith(ICollider obj) {
		boolean result = false;
		
		int r1 = (int)(this.getLocationXValue()) + width/2;
		int l1 = (int)(this.getLocationXValue()) - width/2;
		int t1 = (int)(this.getLocationYValue()) + height/2;
		int b1 = (int)(this.getLocationYValue()) - height/2;
		int r2 = (int)((GameObject)obj).getLocationXValue() + ((GameObject)obj).getWidth()/2;
		int l2 = (int)((GameObject)obj).getLocationXValue() - ((GameObject)obj).getWidth()/2;
		int t2 = (int)((GameObject)obj).getLocationYValue() + ((GameObject)obj).getHeight()/2;
		int b2 = (int)((GameObject)obj).getLocationYValue() - ((GameObject)obj).getHeight()/2;
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
	public void move(int T, Point pCmpRelPrnt)
	{
		double theta = 90 - this.getDirection();
		double time = (double)T;
		theta = Math.toRadians(theta);
		
		double deltax = Math.cos(theta)*this.getSpeed()*(time/1000)*inverseX;
		double deltay = Math.sin(theta)*this.getSpeed()*(time/1000)*inverseY;
		
		double x = this.getLocationXValue();
		double y = this.getLocationYValue();
		x = x + deltax;
		y = y + deltay;
		this.setLocationValue(x, y);
		if((x + width/2 >= pCmpRelPrnt.getX()) || (x - width/2 < 0))
			inverseX = -inverseX;
		if((y + height/2 >= pCmpRelPrnt.getY()) || (y - height/2 < 0))
			inverseY = -inverseY;
	}
}
