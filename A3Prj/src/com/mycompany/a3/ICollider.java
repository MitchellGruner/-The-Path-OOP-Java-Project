package com.mycompany.a3;

public interface ICollider {
	
	boolean collidesWith(GameObject gameObject2); 
	void handleCollision(GameObject otherObject); 
}
