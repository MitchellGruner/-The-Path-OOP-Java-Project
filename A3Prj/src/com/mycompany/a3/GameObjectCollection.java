package com.mycompany.a3;
import java.util.Vector;

public class GameObjectCollection implements ICollection {

	private Vector<GameObject> gameArray;
	
	public GameObjectCollection() {
		gameArray = new Vector<GameObject>();
	}

	@Override
	public void add(GameObject gameObject) {
		gameArray.add(gameObject);
	}
	
	@Override
	public void remove(GameObject gameObject) {
		gameArray.remove(gameObject);
	}

	/*
	 * In order to create an iterator, we need to
	 * first make sure it is of type 'IIterator'.
	 * 
	 * We then will create the 'hasNext()' and 'getNext()'
	 * methods, which will give us the ability to traverse
	 * throughout the collection.
	 */
	@Override
	public IIterator getIterator() {
		return new GameObjectCollectionIterator();
	}

	private class GameObjectCollectionIterator implements IIterator {

		private int currElementIndex;
		
		public GameObjectCollectionIterator() {
			currElementIndex = -1;
		}
		
		@Override
		public boolean hasNext() {	
			if(gameArray.size() <= 0) 
				return false;
			
			if(currElementIndex == gameArray.size() - 1)
				return false;
			return true;
		}

		@Override
		public GameObject getNext() {
			currElementIndex++;
			return(gameArray.elementAt(currElementIndex));
		}

		@Override
		public void remove(GameObject gameObject) {
			gameArray.remove(gameObject);
		}
	}
}
