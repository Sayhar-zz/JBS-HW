package org.brandeis.sahar.pa3;

public class Block extends GameObject {
	//Remember we inherit X and Y and velocity and isStuck
	private double side; //length of side

	/**
	 * Constructor. Defaults to being stuck, of course.
	 * X and Y coordinates specify the bottom left corner of the block
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param s Length of sides.
	 */
	public Block(double x, double y, double s) {
		super(x, y);
		side = s;
		isStuck = true;
	}
	
	/**
	 * Returns the length of one side of the block
	 * @return the length of one side of the block
	 */
	public double getSide(){
		return side;
	}
	
	/**
	 * Get the coordinate of the Top-Left point of the block.
	 * @return double[] representing the X,Y coordinate of this vertex
	 */
	public double[] getTL(){
		double [] toReturn = {X, Y + side };
		return toReturn;
	}
	
	/**
	 * Get the coordinate of the Bottom-Left point of the block.
	 * @return double[] representing the X,Y coordinate of this vertex
	 */
	public double[] getBL(){
		double[] toReturn = {X, Y};
		return toReturn;
	}
	
	/**
	 * Get the coordinate of the Bottom-Right point of the block.
	 * @return double[] representing the X,Y coordinate of this vertex
	 */
	public double[] getBR(){
		double[] toReturn = {X + side, Y};
		return toReturn;
	}
	
	/**
	 * Get the coordinate of the Top-Right point of the block.
	 * @return double[] representing the X,Y coordinate of this vertex
	 */
	public double[] getTR(){
		double[] toReturn = {X + side, Y + side};
		return toReturn;
	}
	
}
