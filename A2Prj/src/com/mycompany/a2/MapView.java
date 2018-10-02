package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Container;

public class MapView extends Container implements Observer{
	
	public MapView() 
	{ 

	}


	public void update(Observable o, Object arg) {
        System.out.println( "Map Width: " + Game.getMapHeight() + "  Map Height: "
                + Game.getMapWidth() );
        //Cast the Observable objects as the GameWorld first to access variables
        IGameWorld gw = (IGameWorld)o;
        SpaceCollection go = gw.getGameObjects();
        IIterator gameIterator = go.getIterator();
        while ( gameIterator.hasNext())
        {
        	System.out.println(gameIterator.getNext());
        }
	}
}
