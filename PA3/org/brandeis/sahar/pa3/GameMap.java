package org.brandeis.sahar.pa3;

import java.util.ArrayList;
import org.brandeis.sahar.pa3.GameObject;
import org.brandeis.sahar.pa3.Disk;
import org.brandeis.sahar.pa3.Block;
import org.brandeis.sahar.pa3.Jewel;

/**
 * The GameMap is a container for all objects in the game. It also holds the physics variables, and checks to see if objects have collided.
 * @author sahar
 *
 */
public class GameMap {
	
	public double g = -.0098;
	public int dt = 100; //all times are in milliseconds. 
	//dt of 100 means that the game goes in steps of 100 milliseconds at a time.
	
	public ArrayList<Disk> disks = new ArrayList<Disk>();
	public ArrayList<Block> blocks = new ArrayList<Block>();
	public ArrayList<Jewel> jewels = new ArrayList<Jewel>();
	
	public GameMap(){	}
	
	/**
	 * Constructor given already-constructed ArrayLists of disks, blocks, and jewels
	 * @param d ArrayList of disks
	 * @param b ArrayList of blocks
	 * @param j Arraylist of Jewels.
	 */
	public GameMap(ArrayList<Disk> d, ArrayList<Block> b, ArrayList<Jewel> j ){
		disks = d;
		blocks = b;
		jewels = j;
	}
	
	/*
	 * Variables are public so we don't need these
	 * 
	 * public void addDisk(Disk d){
		disks.add(d);
	}
	
	public void addBlock(Block b){
		blocks.add(b);
	}
	
	public void addJewel(Jewel j){
		jewels.add(j);
	}*/
	
	/**
	 * Returns one ArrayList of GameObjects with all disks, blocks, and jewels in the game inside.
	 * @return  one ArrayList of GameObjects with all disks, blocks, and jewels in the game inside.
	 */
	public ArrayList<GameObject> getAll(){
		ArrayList<GameObject> go = new ArrayList<GameObject>();
		if (!disks.isEmpty()) {
			go.addAll(disks);
		}
		if (!blocks.isEmpty()) { 
				go.addAll(blocks);
		}
		if (!jewels.isEmpty()) {
				go.addAll(jewels);
		}
		//Why all the if statements? Avoiding exceptions
		return go;
	}
	
	//TODO methods to check collisions
	public GameObject checkAndStick(Disk disk){
		//check one disk against every other element. 
		
		
		for (Jewel j : jewels){
			if(collides(disk,j)){
				d.Stick();
				return j;
			}
		}
		
		for (Block b : blocks){
			if(collides(disk,b)){
				disk.Stick();
				return b;
			}
		}
		
		if(!disks.remove(disk)) {
			//ERROR. YOu should be able to remove yourself from this list. If not, I need to rethink things.
			System.out.println("ERROR ERROR Disk can't be removed from elts ArrayList in GameMap. ERROR");
			throw new Exception("ERROR ERROR Disk can't be removed from elts ArrayList in GameMap. ERROR");
		}
		
		for (Disk d : disks){
			if(collides(disk,d)){
				disk.Stick();
				disks.add(disk);
				return d;
			}
		}
		
		disks.add(disk);
		
		
		return null;
	}
	
	public boolean collides (Disk d, Block b){
		//TODO This
		
	}
}
