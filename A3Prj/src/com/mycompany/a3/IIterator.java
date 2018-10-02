package com.mycompany.a3;

import com.mycompany.a3.IIterator;

public interface IIterator {
	boolean hasNext();
	Object getNext();
	Object get(int i);
	int size();
	Object elementAt(int i);
	void remove();

}
