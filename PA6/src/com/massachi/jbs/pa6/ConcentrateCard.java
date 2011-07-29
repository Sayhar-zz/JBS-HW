package com.massachi.jbs.pa6;
/**
 * Basic Card for the Concentration game
 * @author sahar
 *
 */
public class ConcentrateCard {
	private String text;
	private boolean discovered;
	private boolean tmpview = false; //should we temporarily show this card (after it is chosen by a player)? After a while it will be flipped down again.
	
	public ConcentrateCard(String msg){
		text = msg;
		discovered = false;
	}
	
	public String getText(){
		return text;
	}
	
	public String toString(){
		return "Card: " + text;
	}
	
	public void setDiscovered(boolean d){
		discovered = d;
	}
	
	public String getVisibleText(){
		if(discovered || tmpview) {return text;} else{return "X"; }
	}
	
	public void showOn(){
		tmpview = true;
	}
	
	public void showOff(){
		tmpview = false;
	}
}
