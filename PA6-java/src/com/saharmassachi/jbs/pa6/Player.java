package com.saharmassachi.jbs.pa6;

public class Player {

	public String name;
	public int points;
	
	public Player(String name){
		this.name = name;
		points = 0;
	}
	
	public String getName(){
		return name;
	}
	
	public void plusMatch(){
		points++;
	}
	
	public int getScore(){
		return points;
	}
	
}
