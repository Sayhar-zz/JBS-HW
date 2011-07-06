package org.jbs.sahar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import static android.provider.BaseColumns._ID;


public class Node {
	private static final String TAG = "Node";
	private Node y ;
	private Node n ;
	public String text;
	private NodeData data;
	private String id;
	private Context ctx;
	public Node(String msg, String id, Context ctx){
		text = msg;
		y = null;
		n = null;
		this.id = id;
		this.ctx = ctx;
		data = new NodeData(ctx);
	}
	
	public boolean isLeaf(){
		Log.d(TAG, "entering isLeaf()");
		if(getY() == null  && getN() == null){
			return true;
		}
		else{
			return false;
		}
	}

	public Node getY(){
		if (y == null) {
			y = getX(2);
		}
		return y;
	}
	
	public Node getN(){
		if (n == null){
			n = getX(3);
		}
		return n;
	}
	
	public Node getX(int yesorno){
		SQLiteDatabase db = data.getReadableDatabase();
		String[] args = {id};
		Cursor c = db.rawQuery("Select * from NODES where " + _ID + "=" + id,null);
		c.moveToFirst();
		String newID = c.getString(yesorno);
		c.close();
		db.close();
		if (newID == null) {
			
			return null;
		}
		return getNodeFromID(newID, ctx);
	}
	
	public Node getNodeFromID(String id2fetch, Context ctx){
		Log.v(TAG, "get node from ID: " +id2fetch);
		String[] args = {id2fetch};
		//NodeData d = new NodeData(ctx);
		
		SQLiteDatabase db = data.getReadableDatabase();
		Log.v(TAG, "successfully read from db");
		Cursor c = db.rawQuery("Select * from NODES where " + _ID + "=?", args);
		c.moveToNext();
		String msg = c.getString(1);
		c.close();
		db.close();
		return new Node(msg, id2fetch, ctx);
	}
	
	public Node newY(String newText){
		return newX(true, newText);
	}
	
	public Node newN(String newText){
		return newX(false, newText);
	}
	
	public Node newX(boolean isY, String newText){
		String YorN = isY?"Y":"N";
		SQLiteDatabase db = data.getWritableDatabase();
		ContentValues values = new ContentValues();
	    values.put("TEXT", newText);
	    long newid = db.insertOrThrow("nodes", null, values);
	    values = new ContentValues();
	    values.put(YorN, newid);
	    String[] args = {id};
	   	db.update("NODES", values, _ID+ "=?", args);
	    db.close();
	    return new Node(newText, String.valueOf(newid), ctx);
		
	}
	
	public void setText(String newText){
		this.text = newText;
		SQLiteDatabase db = data.getWritableDatabase();
		ContentValues values = new ContentValues();
	    values.put("TEXT", newText);
	    String[] args = {id};
	    db.update("NODES", values, _ID +"=?", args);
	    db.close();
	}
	
	
}

