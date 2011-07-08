package com.saharmassachi.jbs.pa6;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Board Object for the "Bipartite Concentration Game"
 * @author sahar
 *
 */
public class ConcentrateBoard {
	private ConcentrateCard[][] board;
	
	public ConcentrateBoard(int width, int height){
		board = new ConcentrateCard[width][height];
	}
	
	/**
	 * Given an ArrayList of cards = size of the board, then randomly place them on the board.
	 * @param a
	 */
	public void initBoard(ArrayList<ConcentrateCard> a){
		Collections.shuffle(a);
		int i = 0;
		int j = 0;
		for (ConcentrateCard c : a){
			board[i][j] = c;
			//increment j. If j carries over to 0, then increment i instead.
			j = (j<getHeight() -1)?j+1:0;
			i = (j==0)?i+1:i;
		}
	}
	
	
	public int getHeight(){
		return board[0].length;
	}
	
	public int getWidth(){
		return board.length;
	}
	
	/**
	 * 
	 * @return int i = the number of cards this board should hold.
	 */
	public int getSize(){
		return getHeight() * getWidth();
	}
	
	/**
	 * toString method - for debugging purposes:
	 */
	@Override
	public String toString(){
		StringBuilder s = new StringBuilder();
		for (int j = 0; j< getHeight(); j++){
			for (int i = 0; i < getWidth(); i++){
			
				s.append(board[i][j].getVisibleText());
				s.append(" | ");
			}
			s.append("\n");
		}
		return s.toString();
	}
	
	public ConcentrateCard getWH(int w, int h){
		return board[w][h];
	}
}
