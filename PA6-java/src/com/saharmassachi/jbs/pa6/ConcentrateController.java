package com.saharmassachi.jbs.pa6;

import java.util.ArrayList;
import java.util.Scanner;


public class ConcentrateController {
	
	private ConcentrateGame g;
	private Scanner scan = new Scanner(System.in);

	
	public String nextPlayer(){
		return g.getNextPlayer().getName();
	}
	
	public int askInt(String q){
		print(q);
		String answer = scan.next();
		try{
			int r = Integer.parseInt(answer);
			return r;
		}
		catch(NumberFormatException e){
			print("I don't understand. Answer must be an integer");
			return askInt(q);
		}
	}
	
	public void play(){
		init();
		playgame();
	}
	
	
	public void playgame(){
		while(!g.isGameOver()){
			print(g.toString());
			move();
		}
		Player p = g.endGame();
		print(p + " won with " + p.getScore() + " points.");
	}
	
	public void init(){
		g = new ConcentrateGame();
		g.setPlayers(initPlayers(askInt("How many players in this game?")));
		
		int w = askInt("How wide should this board be?");
		int h = askInt("How tall should this board be?");
		int size = w*h;
		
		ArrayList<ConcentrateCard> listA = cardGen(size, new cardGenAlgorithm(){
			public String gen(int i){
				return Integer.toString(i+1);
			}
		});
		
		ArrayList<ConcentrateCard> listB = cardGen(size, new cardGenAlgorithm(){
			public String gen(int i){
				return String.valueOf( (char) (i + 65) ) ;
			}
		});
		
		g.initGame(w, h, listA, listB);	
		
	}
	
	public void move(){
		print(g.getNextPlayer().getName() + " It's your turn");
		print("Choose a card on Board A:");
		int aw = askInt("Across: (Starting from 0)");
		int ah= askInt("Down: (Starting from 0)");
		print(g.getA().getWH(aw, ah));
		print("Now choose a card on Board A:");
		int bw = askInt("Across: (Starting from 0)");
		int bh = askInt("Down: (Starting from 0)");
		print(g.getB().getWH(bw, bh));
		
		String result = g.move(aw, ah, bw, bh );
		print(result);
		
		
	}
	
	public ArrayList<ConcentrateCard> cardGen(int size, cardGenAlgorithm alg){
		ArrayList<ConcentrateCard> list = new ArrayList<ConcentrateCard>();
		for (int i = 0; i < size; i++){
			ConcentrateCard c = new ConcentrateCard(alg.gen(i));
			list.add(c);
		}
		return list;
	}
	
	/**
	 * Main method starts the game.
	 * @param 
	 */
	public static void main(String[] args) {
		ConcentrateController c = new ConcentrateController();
		c.play();
	}
	
	private static void print(Object x){
		System.out.println(x);
	}
	
	public static Player[] initPlayers(int numPlayers){
		Player[] players = new Player[numPlayers];
		for(int i = 0; i < numPlayers; i++){
			players[i] = new Player(askName());
		}
		return players;
	}
	
	public static String askName(){
		Scanner scan = new Scanner(System.in);
		print("Making new player. What is her name?");
		String name = scan.next();
		return name;
	}

	

	
	private class cardGenAlgorithm{
		public String gen(int i){
			return Integer.toString(i+1);
		}
	}
	
}

	

