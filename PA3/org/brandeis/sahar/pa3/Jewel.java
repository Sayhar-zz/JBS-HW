package org.brandeis.sahar.pa3;

public class Jewel extends Block {

	/**
	 * Jewels bahave much like blocks, and thus have the same constructor and variables as blocks.
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param s Side length
	 */
	public Jewel (double x, double y, double s){
		super(x,y,s);
	}

	
	 
	
	/**
	 * Is this a jewel? Yes it is
	 * @return true 
	 */
	@Override
	public boolean isJewel(){
		return true;
	}
}
