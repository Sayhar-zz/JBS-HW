package com.massachi.jbs.pa6;

import java.util.ArrayList;


import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.widget.ViewSwitcher;


public class Game extends Activity{
	
	private static final String TAG = "Game";
	private ConcentrateGame g;
	private PuzzleView A;
	private PuzzleView B;
	private int Ax;
	private int Ay;
	private ViewSwitcher switcher;
	private MediaPlayer mp;
	private MediaPlayer sp;
	
	@Override
	   protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      Log.d(TAG, "onCreate");
	      A = new PuzzleView(this, true);
	      B = new PuzzleView(this, false);
	      switcher = new ViewSwitcher(this);
	      switcher.addView(A);
	      switcher.addView(B);
	      setContentView(switcher);
	      setVolumeControlStream(AudioManager.STREAM_MUSIC);
	      
	      //two media players - a sound effect one and a soundtrack one.
	      //first we do just the sound effect one:
	      
	      if(mp != null) mp.release();
	      mp = MediaPlayer.create(this, R.raw.background);
	      mp.setLooping(true);
	      mp.start();
	      
	      g = new ConcentrateGame();
	      //TODO - change to multiple players later
	      Player p = new Player("Player");
	      Player[] players = {p};
	      g.setPlayers(players);
	      
	      int size = 9;
	      //todo - let player choose this
	      
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
			
			g.initGame(3, 3, listA, listB);
			//eventually these 3's will not be hardcoded in.
		
	   }
	
	protected String getTileString(int x, int y, PuzzleView p) throws Exception { 
		ConcentrateCard v = getTile(x, y, p);
		return v.getVisibleText();
	}
	
	protected String getTrueTileString(int x, int y, PuzzleView p) throws Exception { 
		ConcentrateCard v = getTile(x, y, p);
		return v.getText();
	}

	public String nextPlayer(){
		return g.getNextPlayer().getName();
	}
	
	public ArrayList<ConcentrateCard> cardGen(int size, cardGenAlgorithm alg){
		ArrayList<ConcentrateCard> list = new ArrayList<ConcentrateCard>();
		for (int i = 0; i < size; i++){
			ConcentrateCard c = new ConcentrateCard(alg.gen(i));
			list.add(c);
		}
		return list;
	}
	
	private class cardGenAlgorithm{
		public String gen(int i){
			return Integer.toString(i+1);
		}
	}
	
	protected void flipUp(int x, int y, PuzzleView p){
		try {
			ConcentrateCard c = getTile(x, y, p);
			c.showOn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void flipDown(int x, int y, PuzzleView p){
		try {
			getTile(x, y, p).showOff();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private ConcentrateCard getTile(int x, int y, PuzzleView p) throws Exception {
		if (p.isA){
			return g.getA().getWH(x, y);
		}
		else if (!p.isA) {
			return g.getB().getWH(x, y);
		}
		else throw new Exception("PuzzleView is not recognized!");
	}
	
	protected void showKeypadOrError(int x, int y, PuzzleView P){
		//TODO this is where the game logic resides
		
		if (P.isA){
			Ax = x;
			Ay = y;
		
			switcher.showNext();
		}
		else{ //B was selected.
			int resId = R.raw.nomatch;
			boolean match = g.move(Ax, Ay, x, y);
			
			if (sp != null){	
				sp.release();}
			if(match){	
				resId = R.raw.match;}
			
			sp = MediaPlayer.create(this, resId);
			sp.start();
			
			if(g.isGameOver()){
				Player p = g.endGame();
				String wins = p.name + " won with " + p.getScore() + " points.";
				Intent i = new Intent(this, Intro.class);
				i.putExtra("winning", wins);
				startActivity(i);
				
			}
			//Toast t = Toast.makeText(this, result, Toast.LENGTH_SHORT);
			//t.show();
			switcher.showPrevious();
		}
		
	}
	
	@Override protected void onResume() {
		super.onResume(); 
		mp.start();
		}
		
	@Override protected void onPause() {
		super.onPause(); 
		mp.pause();
		}
	
	
}
