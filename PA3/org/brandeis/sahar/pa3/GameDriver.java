package org.brandeis.sahar.pa3;

import java.util.ArrayList;

import org.brandeis.sahar.pa3.GameObject;
import org.brandeis.sahar.pa3.Disk;
import org.brandeis.sahar.pa3.Vector;
import org.brandeis.sahar.pa3.Block;
import org.brandeis.sahar.pa3.Jewel;
import org.brandeis.sahar.pa3.GameMap;

//Mind you - all times are in milliseconds;
/**
 * The GameDriver class is responsible for moving objects (given directions to do so by the controller), freezes objects if appropriate, etc. 
 * It's the one the that calls setters, that implements logic, etc.
 */
public class GameDriver {
	private GameMap map;
	private int gameLength; //in milliseconds
	private boolean levelDone; //is the game done.
	private int gameSoFar = 0;
	private int disksLeft;
	private int jewelsLeft;
	
	public GameDriver(int time, int disks, int jewels){
		map = new GameMap();
		gameLength = time;
		disksLeft = disks;
		jewelsLeft = jewels;
	}
	
	/**
	 * Given a length of time since the last update, move everything appropriately
	 */
	public void update(int plusTime){
		//FLOW OF THIS METHOD:
		//is plustime > map.dt? 
			//if so, update(dt)
					 //return update(X - dt);
		//am I out of time?
		//am I out of disks?
		//For each disk that is not stuck.
			//Update position 
				//check to see if frozen.
				//Check t see if it hit the jewel
			//Update velocity after moving
		//Update time elapsed
		//---------------------------------
		
		
		//is plusTime too big a timeSlice? Fix it! With tail recursion (right?)
		if(plusTime > map.dt){
			update(map.dt);
			update(plusTime - map.dt);
			return;
		}
		
		//am I out of time?
		if (gameSoFar >= gameLength) endGame();
		
		//am I out of disks?
		if(disksLeft <= 0) endGame();
		
		//Is there a disk that is not stuck?
		ArrayList<Disk> freeDisks = new ArrayList<Disk>();
		for (Disk d : map.disks){
			if (!d.isStuck()) freeDisks.add(d);
		}
		
		//for every disk that is not stuck, update its position, then update velocity
		for (Disk d: freeDisks){
			d.move( 
					moveX(d, plusTime), 
					moveY(d, plusTime, map.g))  ;
			
			//did it hit something? Did it hit the jewel?
			GameObject o = map.checkAndStick(d);
			//if we hit a jewel:
			if ((o != null) && o.isJewel()){
				//decrement the amount of jewels and turn this jewel into a block
				jewelsLeft--;
				double x = o.getX();
				double y = o.getY();
				double s = ((Jewel) o).getSide();
				
				map.jewels.remove(o);
				map.blocks.add(new Block(x, y, s));
			}
				
		}
		
		//for every disk that I moved that did not hit something - update velocity
		for (Disk d: freeDisks){
			updateVelocityX(d, plusTime);
			updateVelocityY(d, plusTime, map.g);
		}
		
		
		//Update time elapsed
		gameSoFar += plusTime;
		
	}
	
	private void endGame(){
		//TODO This
		
	}
	
	private double moveX(Disk d, int time){
		//x = x0 + v0t + (1/2)at^2
		double x0 = d.getX();
		double v0 = d.velocity.dX;
		double t = time;
		//NO acceleration in the x direction, so (1/2)at^2 = 0
		double x = x0 + (v0 * t);
		return x;
	}
	
	private double moveY(Disk d, int time, double a){
		//y = y0 + v0t + (1/2)at^2
		double y0 = d.getY();
		double v0 = d.velocity.dY;
		double t = time;
		
		double y = y0 + (v0 * t) + (.5 * a * (t*t));
		return y;
	}
	
	private void updateVelocityX(Disk d, int time){
		//v = v0 + at
		//-------------
		//double v0 = d.velocity.dX*d.velocity.t;
		//return v0;
		//^don't need that
		return;
	}
	
	private void updateVelocityY(Disk d, int time, double a){
		//v = v0 + at
		//-------------
		double v0 = d.velocity.dY/d.velocity.t;
		double v = v0 + (a*time);
		d.velocity.dY = v;
	}
	
	public static void main(String[] args){
		//TODO this
		
		
	}
}
