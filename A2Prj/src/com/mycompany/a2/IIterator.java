package com.mycompany.a2;

public interface IIterator {
	
	/*
	 * This interface is useful to make sure
	 * that the hasNext() and getNext() methods
	 * are properly set-up.
	 */
	public boolean hasNext();
	public GameObject getNext();
}