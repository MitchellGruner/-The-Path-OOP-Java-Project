package com.mycompany.a3;

public interface ICollection {

	/*
	 * Here is the interface that is called in order
	 * to create a working iterator.
	 */
	public void add(GameObject gameObject);
	public void remove(GameObject gameObject);
	public IIterator getIterator();
}
