package com.mycompany.a1;

import java.util.Vector;
import java.util.Iterator;


public class SpaceCollection 
{

	private Vector <GameObject> theCollection; //create a SpaceCollection object of vectors
	
	public SpaceCollection()
	{
		theCollection = new Vector<GameObject>();	
		}

	public boolean remove(GameObject obj)
	{
		return theCollection.remove(obj);
	}
	//add object into collection
	public void add(GameObject newObject)
	{
		theCollection.addElement(newObject);
	}


	public Iterator<GameObject> getIterator() 
	{
		return new GameObjectIterator();
	}
	
	//return size of collection
	public int size()
	{
		return theCollection.size();
	}
	private class GameObjectIterator implements Iterator <GameObject>
	{
		private int currentElement;
		public GameObjectIterator()
		{
			currentElement= -1;
		}
		public GameObject next() 
		{			
			currentElement ++;
			return(theCollection.elementAt(currentElement));
			//returns null;
		}
		public boolean hasNext() 
		{
			if (theCollection.size()<= 0)
			{
				return false;
			}
			if (currentElement == theCollection.size()-1)
			{
				return false;
			}
			return true;
		//returns false;
		}
		
		public void remove() 
		{
		// TODO Auto-generated method stub		
		}	
	}	
}
