/**
 * 
 */
package org.brandeis.sahar.pa3;

import org.brandeis.sahar.pa3.Vector;

/**
 * @author sahar
 *
 */
public abstract class GameObject {
// always have a location

	protected double X;
	protected double Y;
	protected boolean isStuck;
	protected Vector velocity;
	
	public GameObject(double x, double y){
		X = x; Y = y;
		velocity = new Vector (1,1,0);
	}
	
	//Setters
	
	/**
	 * Set the object to the "stuck" state. That means the velocity should also be 0 in all directions.
	 */
	public void Stick(){
		isStuck = true;
		velocity = new Vector (0,0,1);
	}
	
	/**
	 * Move the object to the new position
	 * @param newX The new X coordinate
	 * @param newY The new Y coordinate
	 * @return An array of the two old coordinates.
	 */
	public double[] move(double newX, double newY){
		double oldX, oldY;
		oldX = X; oldY = Y;
		X=newX;
		Y=newY;
		double[] toReturn = {oldX, oldY};
		return toReturn;
	}
	
	/**
	 * setVelocity takes in the Vector representation of the new velocity, and returns the Vector representation of the old velocity.
	 * @param v The Vector representation of the new velocity
	 * @return The Vector representation of the old velocity.
	 */
	public Vector setVelocity(Vector v){
		Vector old = velocity;
		velocity = v;
		return old;
	}
	
	//Boring old getters
	/**
	 * getX() returns the X coordinate of the object
	 */
	public double getX(){
		return X;
	}
	/**
	 * getY() returns the Y coordinate of the object
	 */
	public double getY(){
		return Y;
	}
	
	/**
	 *  Is the object currently "frozen"? (If so, velocity will be 0 in both directions)
	 * @return whether or not the object is stuck .
	 */
	public boolean isStuck(){
		return isStuck;
	}
	
	/**
	 * Returns the vector representation of the velocity of the object.
	 * @return the Vector representation of the velocity of the object. 
	 */
	public Vector getVelocity(){
		return velocity;
	}
	
	/**
	 * Is this object actually also a Jewel?
	 * @return The jewel-like status of this object.
	 */
	public boolean isJewel(){
		return false;
	}
}
