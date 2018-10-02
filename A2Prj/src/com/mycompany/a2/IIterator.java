package com.mycompany.a2;

public interface IIterator {
	boolean hasNext();
	Object getNext();
	Object get(int i);
	int size();
	Object elementAt(int i);
}
