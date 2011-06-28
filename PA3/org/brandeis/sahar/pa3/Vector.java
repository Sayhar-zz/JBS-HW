package org.brandeis.sahar.pa3;

/**
 * Vector is an object that represents the velocity of a disk.
 * It has 3 variables = dX, dY, and t.
 * dX and dY represent the change in the X and Y direction, respectively, per t seconds.
 * The default constructor always sets t to 1 millisecond; 
 * @author sahar
 *
 */
public class Vector {
	//I see no need to worry about security here, so instead of getters and setters I am making the variables public.
	public double dX;
	public double dY;
	public double t;
	
	/**
	 * The constructor takes in a dx and dy over a given interval, and normalizes them to the dX and dY of a standard 1 millisecond interval.
	 * @param dx How far in the x direction the object moves per interval seconds.
	 * @param dy How far in the y direction the object moves per interval seconds.
	 * @param interval see dx and dy
	 */
	public Vector (double dx, double dy, double interval){
		this.dX = dx / interval;
		this.dY = dy / interval;
		t = 1;
	}
	
}
