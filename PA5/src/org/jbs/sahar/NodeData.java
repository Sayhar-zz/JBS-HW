package org.jbs.sahar;

import static android.provider.BaseColumns._ID;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NodeData extends SQLiteOpenHelper {
   private static final String DATABASE_NAME = "events.db";
   private static final int DATABASE_VERSION = 1;

   /** Create a helper object for the Events database */
   public NodeData(Context ctx) { 
      super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
   }

   @Override
   public void onCreate(SQLiteDatabase db) { 
      db.execSQL("CREATE TABLE NODES"  + " (" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + "TEXT"
            + " TEXT NOT NULL, " + "Y" + " INTEGER, " + "N" + " INTEGER);");
   }

   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, 
         int newVersion) {
      db.execSQL("DROP TABLE IF EXISTS " + "NODES");
      onCreate(db);
   }
}
