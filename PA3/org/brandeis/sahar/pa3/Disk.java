package org.brandeis.sahar.pa3;

public class Disk extends GameObject {
	//Remember we inherit X and Y and isStuck and velocity;
	
	
	private double radius;
	
	/**
	 * Creates a new disk at point x,y of radius r. Not stuck, but velocity starts out at 0.
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param r Radius
	 */
	public Disk(double x, double y, double r) {
		super(x, y);
		radius = r;
		isStuck = false;
	}
	
	/**
	 * Returns the radius 
	 * @return The radius of the disk
	 */
	public double getR(){
		return radius;
	}
	
	
}
