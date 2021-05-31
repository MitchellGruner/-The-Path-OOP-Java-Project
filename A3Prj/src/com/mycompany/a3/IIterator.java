package com.mycompany.a3;

public interface IIterator {
	
	/*
	 * This interface is useful to make sure
	 * that the hasNext() and getNext() methods
	 * are properly set-up.
	 */
	public boolean hasNext();
	public GameObject getNext();
	public void remove(GameObject gameObject);
}
