package com.saharmassachi.jbs.pa6;
/**
 * Basic Card for the Concentration game
 * @author sahar
 *
 */
public class ConcentrateCard {
	private String text;
	private boolean discovered;
	
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
		if(discovered) {return text;} else{return "X"; }
	}
}
