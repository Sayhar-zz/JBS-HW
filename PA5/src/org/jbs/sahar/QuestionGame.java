package org.jbs.sahar;

import static android.provider.BaseColumns._ID;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;



public class QuestionGame {
	private static final String TAG = "questiongame";
	private Node now;
	private Node root;
	private boolean askLoseQuestion = false;
	private Context ctx;

	
	public QuestionGame(String msg, Context ctx){
		
		this.ctx = ctx;
		root = getRootNode(msg);
		now = root;
		
	}
	
	public String getNext(){
		Log.d(TAG, "entering getNext()");
		return now.isLeaf()?"Is it a " + now.text: now.text;
		
	
	}
	
	//return a true if the game is over, false if it continues
	public boolean guessResult(boolean r){
		if(r == true && now.getY() == null){
			Log.v("QuestionGame", "now.y == null");
			return true; /*You win*/
		}
		else if(r == true && now.getY() != null){
			now = now.getY();
			return false;
		}
		else if (r == false && now.getN() == null){
			askLoseQuestion = true;
			return true; /*You lose*/
		}
		else if(r == false){
			now = now.getN();
			return false;
		}
		else{
			System.out.println( "This shoudl never happen.");
			return false;
			
		}
	}	
	
	public String win(){
		return "I win";
	}
	
	public String lose(){
		return "I lose. What were you thinking of? What question is true for it, but not a " + now.text + "?";
		
	}
	
	public void learn(String a, String q){
		//a = animal, q = question to get at it.
		Node y = now.newY(a); 
			//new Node(a);
		Node n = now.newN(now.text);
		now.setText(q);
		//now.n = n;
		//now.y = y;
	}
	
	public boolean lost(){
		return askLoseQuestion?true:false;
	}
	
	public void newGame(){
		now = root;
		askLoseQuestion = false;
	}

	public Node getRootNode(String msg){
		
		Log.v(TAG, "get node from ID 0");
		
		NodeData d = new NodeData(ctx);
		SQLiteDatabase db = d.getWritableDatabase();
		Log.v(TAG, "successfully read from db");
		String[] args = {"0"};
		Cursor c = db.rawQuery("Select * from NODES where "+ _ID + " =?", args);
		
		if (c.getCount() == 0){ //if the db is empty
			boolean isdone = c.moveToNext();
			Log.d(TAG, "this should be false if cursor was truly empty" + isdone);
			c.close();
			ContentValues values = new ContentValues();
		    values.put("TEXT", msg);
		    values.put(_ID, 0);
		   // Log.d(TAG, "the db right now: " + printDB(db.rawQuery( "Select * from nodes", null)) );
		    db.beginTransaction();
		    db.execSQL("insert into nodes (" + _ID + ", text) values (0, '" + msg+"')");
		    //db.insertOrThrow("NODES", null, values);
		    db.setTransactionSuccessful();
		    db.endTransaction();
		    c = db.rawQuery("Select * from NODES where " + _ID + "=?", args);
		    
		}
		c.moveToNext();
		String retrievedmsg = c.getString(1);
		c.close();
		db.close();
		return new Node(retrievedmsg, "0", ctx);
	}
	private String printDB(Cursor c){
		String s = "";
		c.moveToFirst();
		s += c.getString(0);
		s += " | ";
		s += c.getString(1);
		s += " | ";
		s += c.getString(2);
		s += " | ";
		s += c.getString(3);
		s += "\n";
		while (c.moveToNext())
		{
			s += c.getString(0);
			s += " | ";
			s += c.getString(1);
			s += " | ";
			s += c.getString(2);
			s += " | ";
			s += c.getString(3);
			s += "\n";
		}
		return s;
	}
}
