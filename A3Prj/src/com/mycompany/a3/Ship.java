package com.mycompany.a3;

import java.util.Vector;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Ship extends MoveableObject implements ISteerable, IDrawable, ICollider
{
	private int missileCount;
	private int inverseX;
	private int inverseY;
	private boolean readyToRemove;
	private static final int height = 50;
	private static final int width = 50;
	private int mapHeight;
	private int mapWidth;
	private Vector<ICollider> collisionVector;
	public Ship (int x, int y)
	{
		super(x/2, y/2, 0, 0, 0);
		mapHeight = y;
		mapWidth = x;
		super.setSpeed(0);
		super.setDirection(0);	
		setMissileCount(10);
		inverseX = 1;
		inverseY = 1;
		collisionVector = new Vector<ICollider>();
		readyToRemove = false;
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
		super.setLocationValue(mapWidth/2, mapHeight/2);
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
	public void draw(Graphics g, Point pr) {
		// TODO Auto-generated method stub
		g.setColor(this.getColor());
		int x = (int) (pr.getX() + super.getLocationXValue());
		int y = (int) (pr.getY() + super.getLocationYValue());

		g.fillRect(x, y, width, height);
		g.drawRect(x, y, width, height);

	}
	public void move(int T, Point pCmpRelPrnt)
	{
		double theta = 90 - this.getDirection();
		double time = (double)T;
		theta = Math.toRadians(theta);
		
		double deltax = Math.cos(theta)*this.getSpeed()*20*(time/1000)*inverseX;
		double deltay = Math.sin(theta)*this.getSpeed()*20*(time/1000)*inverseY;
		
		double x = this.getLocationXValue();
		double y = this.getLocationYValue();
		x = x + deltax;
		y = y + deltay;
		
		this.setLocationValue(x, y);
		if(   (x+50 >= pCmpRelPrnt.getX()) || (x-50 < 0))
			inverseX = -inverseX;
		if(   (y+50 >= pCmpRelPrnt.getY()) || (y-50 < 0))
			inverseY = -inverseY;
	}
	public int handleCollision(ICollider other) {
		// TODO Auto-generated method stub
		if(collisionVector.contains(other))
			return 0;
		collisionVector.add(other);
		if(other instanceof Asteroid)
			return 1;
		else if(other instanceof SpaceStation)
		{
			if(((SpaceStation)other).isBlinking() == true)
				reloadMissiles();
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
