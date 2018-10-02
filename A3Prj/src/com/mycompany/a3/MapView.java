package com.mycompany.a3;
import com.codename1.ui.geom.Point;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;

import java.util.Observer;
import java.util.Observable;

public class MapView extends Container implements Observer{
	
	private SpaceCollection game;
	private IGameWorld myGW = new GameWorld();

	public MapView()
	{
		
	}

	public void update(Observable o, Object data) {
		// TODO Auto-generated method stub
		System.out.println("Map Width: " + Game.getMapWidth()+" Map Height: "+ Game.getMapHeight());
		IGameWorld gw = (IGameWorld) o;
		game = gw.getGameObjects();

		if(o instanceof IGameWorld)
		{
			// save reference to proxy
			myGW = (IGameWorld)o;
			myGW.printMap();
			repaint();
		}
		
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		int x = getX();
		int y = getY();
		
		Point p = new Point(this.getX(),this.getY());
		//Point center = new Point(Game.getMapHeight()/2, Game.getMapHeight()/2);
		IIterator gi = game.getIterator();
		while(gi.hasNext())
		{
			Object o = gi.getNext();
			if(o instanceof Asteroid)
			{
				((Asteroid)o).draw(g, p);
			}
			if(o instanceof Ship)
			{
				((Ship)o).draw(g, p);
			}
			if(o instanceof Missile)
			{
				((Missile)o).draw(g, p);
			}
			if(o instanceof SpaceStation)
			{
				((SpaceStation)o).draw(g, p);
			}
		}
	}
	
	public void pointerPressed(int x, int y)
	{
		if(myGW.getPaused())
		{
			x = x - getParent().getAbsoluteX();
			y = y - getParent().getAbsoluteY();
			
			Point pPtrRelPrnt = new Point(x,y);
			Point pCmpRelPrnt = new Point(getX(), getY());
			
			IIterator anIterator = myGW.getIterator();
			if(anIterator != null)
			{
				while(anIterator.hasNext())
				{
					GameObject obj = (GameObject)anIterator.getNext();
					if(obj instanceof ISelectable)
					{
						if(((ISelectable)obj).contains(pPtrRelPrnt,  pCmpRelPrnt))
							((ISelectable) obj).setSelected(true);
						else
							((ISelectable)obj).setSelected(false);
					}
				}
			}
		}
		repaint();
	}
	
	public Point getSize()
	{
		Point pCmpRelPrnt = new Point(getX(), getY());
		return pCmpRelPrnt;
	}
	
}
