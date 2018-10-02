package com.mycompany.a2;

import java.util.Vector;


public class SpaceCollection implements ICollection
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


	public IIterator getIterator() 
	{
		return new GameObjectIterator();
	}
	
	//return size of collection
	public int size()
	{
		return theCollection.size();
	}
	private class GameObjectIterator implements IIterator
	{
		private int currentElement;
		public GameObjectIterator()
		{
			currentElement= -1;
		}
		public GameObject getNext() 
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
		
		public Object get(int location) {
			// TODO Auto-generated method stub
			return theCollection.get(location);
		}
		public int size() {
			// TODO Auto-generated method stub
			return theCollection.size();
		}
		public Object elementAt(int location) {
			// TODO Auto-generated method stub
			return theCollection.elementAt(location);
		}	
	}	
}
