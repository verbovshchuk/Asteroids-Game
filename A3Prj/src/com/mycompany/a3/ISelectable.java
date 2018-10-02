package com.mycompany.a3;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public interface ISelectable 
{
	public boolean isSelected();
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt);
	public void draw(Graphics g, Point pCmpRelPrnt);
	public void setSelected(boolean yesNo);

	
}
