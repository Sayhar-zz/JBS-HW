package com.saharmassachi.jbs.pa6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Random;

public class ConcentrateGame {
	private ConcentrateBoard A;
	private ConcentrateBoard B;
	private HashMap<ConcentrateCard,ConcentrateCard> matcher;
	private Player[] players;
	private int nextUp;
	private int matchsuccess = 0;
	
	public void setPlayers(Player[] p){
		players = p;
	}
	
	public void initGame(int w, int h, ArrayList<ConcentrateCard> listA, ArrayList<ConcentrateCard> listB){
		initBoards(w,h);
		initMatch(listA, listB);
		deal(listA, listB);
		setFirstPlayer();
		
	}
	
	private void setFirstPlayer(){
		Random generator = new Random();
		int x = generator.nextInt(players.length);
		nextUp = x;
	}
	
	public Player getNextPlayer(){
		return players[nextUp];
	}
	
	private void initMatch(ArrayList<ConcentrateCard> listA, ArrayList<ConcentrateCard> listB){
		matcher = new HashMap<ConcentrateCard,ConcentrateCard>();
		ListIterator<ConcentrateCard> iterA = listA.listIterator();
		ListIterator<ConcentrateCard> iterB = listB.listIterator();
		while(iterB.hasNext()){
			matcher.put(iterA.next(), iterB.next());
		}
		
	}
	
	private void initBoards(int w, int h){
		A = new ConcentrateBoard(w,h);
		B = new ConcentrateBoard(w,h);
	}
	
	private void deal(ArrayList<ConcentrateCard> listA, ArrayList<ConcentrateCard> listB){
		A.initBoard(listA);
		B.initBoard(listB);
	}
	
	public String toString(){
		String a = "Board A:\n";
		a+= A.toString();
		String b = "\n\nBoard B:\n";
		b += B.toString();
		return a + b;
	}
	
	public boolean isGameOver(){
		return (matchsuccess>=matcher.size())? true: false;
		//if we've discovered all the matches, return. Else, return false;
		
	}
	public boolean matchCards(ConcentrateCard a, ConcentrateCard b){
		
		int matches = 0;
		if (matcher.get(a) == b) matches++;
		if (matcher.get(b) == a) matches++;
		//^ You have to test both, because they could be in the wrong order;
		
		if(matches == 0 ) return false;
		if(matches == 1) return true;
		else {System.out.println("ERROR: Double matching in the matchCards in ConcentrateGame. This should never happen"); return true;}
	}
	
	public String move(int Aw, int Ah, int Bw, int Bh){
		Player oldP = players[nextUp];
		incrementPlayer();
		ConcentrateCard aCard = A.getWH(Aw, Ah);
		ConcentrateCard bCard = B.getWH(Bw, Bh);
		if (matchCards(aCard, bCard)){
			matchsuccess++;
			aCard.setDiscovered(true);
			bCard.setDiscovered(true);
			oldP.plusMatch();
			return "Match!";
		}
		else{
			return "Nope.";
		}
	}
	
	//temporarily public.
	public ConcentrateBoard getA(){
		return A;
	}
	
	public ConcentrateBoard getB(){
		return B;
	}
	
	private void incrementPlayer(){
		nextUp++;
		if (nextUp >= players.length) {
			nextUp =0;
		}
		
	}
	
	//cycle through the players. See which has the most points.
	//in case of tie, winner is randomly slected among the tied
	public Player endGame(){
		Player[] copy = players.clone();
		Arrays.asList(copy);
		Player winningplayer = copy[0];
		
		int winningscore = winningplayer.getScore();
		for (int i = 0; i<copy.length; i++){
			if(copy[i].getScore() > winningscore){
				winningplayer = copy[i];
				winningscore = winningplayer.getScore();
			}
		}
		return winningplayer;
	}
}
