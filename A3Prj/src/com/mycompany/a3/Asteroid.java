package com.mycompany.a3;

import java.util.Random;
import java.util.Vector;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Asteroid extends MoveableObject implements IDrawable, ICollider, ISelectable
{
	private int size;
	private boolean switchX = false;
	private boolean switchY = false;
	private int inverseX;
	private int inverseY;
	private boolean readyToRemove;
	private Vector<ICollider> collisionVector;
	private boolean isSelected;
	
	public Asteroid() //create asteroid. Location is randomly generated in the super class.
	{
		super(0,0,255);
		Random rand = new Random();
		size = rand.nextInt(70)+50;
		super.setDirection(rand.nextInt(360)); //random direction 0 and 359
		super.setSpeed(rand.nextInt(10)+1); //random speed between 1-10 generated 
		readyToRemove = false;
		inverseX = 1;
		inverseY = 1;
		collisionVector = new Vector<ICollider>();
		isSelected = false;
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

	public void draw(Graphics g, Point pr) 
	{
		g.setColor(this.getColor());
		int x = (int) (pr.getX() + super.getLocationXValue());
		int y = (int) (pr.getY()+super.getLocationYValue());
		
		if(isSelected())
		{
			g.setColor(ColorUtil.YELLOW);
			g.drawArc(x - size/2, y - size/2, this.getSize(), this.getSize(), 0, 360);
		} else
		{
			g.fillArc(x - size/2, y - size/2, this.getSize(), this.getSize(), 0, 360);
			g.drawArc(x - size/2, y - size/2, this.getSize(), this.getSize(), 0, 360);
		}
				
	}
	@Override
	public void move(int T, Point pCmpRelPrnt)
	{
		/*double oldX = super.getLocationXValue();
		double oldY = super.getLocationYValue();
		
		if ( (oldX + size >= super.getGameWidth()) || (oldX < 0) ) 
		{
			super.setDirection(getDirection() + 90);
			
		}
		if ( (oldY +size >= super.getGameHeight()) || (oldY < 0) )
		{
			super.setDirection(getDirection() + 90);
		}
		double theta = Math.toRadians(90 - super.getDirection());
		double deltaX = Math.round(Math.cos(theta) * 10.0 * super.getSpeed())/ 10.0; 
		double deltaY = Math.round(Math.sin(theta) * 10.0 * super.getSpeed())/ 10.0;
		
		double newX = Math.round((oldX + deltaX) * 10.0)/ 10.0; 
		double newY = Math.round((oldY + deltaY) * 10.0)/ 10.0; 
		
		super.setLocationValue(newX, newY); //update location*/

		double theta = 90 - this.getDirection();
		double time = (double)T;
		theta = Math.toRadians(theta);
		double deltax = Math.cos(theta)*this.getSpeed()*(time/20)*inverseX;
		double deltay = Math.sin(theta)*this.getSpeed()*(time/20)*inverseY;
		double x = this.getLocationXValue();
		double y = this.getLocationYValue();
		x = x + deltax;
		y = y + deltay;
		this.setLocationValue(x, y);
		if(   (x+size/2 >= pCmpRelPrnt.getX()) || (x-size/2 < 0))
			inverseX = -inverseX;
		if(   (y+size/2 >= pCmpRelPrnt.getY()) || (y-size/2 < 0))
			inverseY = -inverseY;

	}

	public boolean isSwitchX() {
		return switchX;
	}

	public void setSwitchX(boolean switchX) {
		this.switchX = switchX;
	}

	public boolean isSwitchY() {
		return switchY;
	}

	public void setSwitchY(boolean switchY) {
		this.switchY = switchY;
	}

	public int handleCollision(ICollider obj) {
		// TODO Auto-generated method stub
		if(collisionVector.contains(obj))
			return 0;
		collisionVector.add(obj);
		if(obj instanceof Ship)
		{
			return 1;
		}
		else if(obj instanceof Missile)
		{
			return 3;
		}
		else if (obj instanceof Asteroid)
		{
			return 4;
		}
		return -1;
	}

	public boolean collidesWith(ICollider obj) {
		boolean result = false;
		int r1 = (int)(this.getLocationXValue()) + size/2;
		int l1 = (int)(this.getLocationXValue()) - size/2;
		int t1 = (int)(this.getLocationYValue()) + size/2;
		int b1 = (int)(this.getLocationYValue()) - size/2;
		int r2 = (int)  ((GameObject)obj).getLocationXValue() + ((GameObject)obj).getWidth()/2;
		int l2 = (int)  ((GameObject)obj).getLocationXValue() - ((GameObject)obj).getWidth()/2;
		int t2 = (int)  ((GameObject)obj).getLocationYValue() + ((GameObject)obj).getWidth()/2;
		int b2 = (int)  ((GameObject)obj).getLocationYValue() - ((GameObject)obj).getWidth()/2;
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
	public int getWidth()
	{
		return size;
	}
	
	public int getHeight()
	{
		return size;
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
	public void setSelected(boolean yesOrNo)
	{
		isSelected = yesOrNo;
	}
	
	public boolean isSelected()
	{
		return isSelected;
	}
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt)
	{
		int px = (int)pPtrRelPrnt.getX();
		int py = (int)pPtrRelPrnt.getY();
		int xLoc = (int)(pCmpRelPrnt.getX() + this.getLocationXValue());
		int yLoc = (int)(pCmpRelPrnt.getY() + this.getLocationYValue());
		if((px >= xLoc-size/2) && (px <= xLoc + size/2)  && (py>=yLoc-size/2) && (py<= yLoc + size/2))
			return true; 
		else
			return false;
	}
}
