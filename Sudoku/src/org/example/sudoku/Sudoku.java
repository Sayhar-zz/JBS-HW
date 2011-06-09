package org.example.sudoku;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;


public class Sudoku extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	  setContentView(R.layout.main);
	  System.out.println("created");
	  View continueButton = findViewById(R.id.continue_button);
	  continueButton.setOnClickListener(this);
	  View newButton = findViewById(R.id.new_button);
	  newButton.setOnClickListener(this);
	  View aboutButton = findViewById(R.id.about_button);
	  aboutButton.setOnClickListener(this);
	  View exitButton = findViewById(R.id.exit_button);
	  exitButton.setOnClickListener(this);
	    
  }
	
	public void onClick(View v){
		switch(v.getId()) {
		case R.id.about_button:
			System.out.println("onclick!");
			Intent i = new Intent(this, About.class);
			startActivity(i);
			break;
		case R.id.new_button:
			openNewGameDialog();
			break;
		case R.id.exit_button: 
			finish();
			break;
		//implement other buttons here
		}	
	}
	
	private static final String TAG = "Sudoku";
	
	
	private void openNewGameDialog() {
		//holy shit this is one confusing command. 
		//like, all this is ONE command. 
		//book does not explain what it does. Angry/confused.
		new AlertDialog.Builder(this)
		.setTitle(R.string.new_game_title)
		.setItems(R.array.difficulty,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int i) {
						//WHERE DOES i come from? //MAGIC
							startGame(i);
					}
		}).show();
		
		
	}
	
	private void startGame(int i){
		
		Log.d(TAG, "clicked on " + i);
		//game starts here
	}
		
		
		
		

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		System.out.println("onCreateOptionsMenu");
		
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		System.out.println("hello?");
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
			System.out.println("entering onOptionsItemsSelected");
			switch (item.getItemId()) {
			case R.id.settings:
				System.out.println("New Intent launched");
				startActivity(new Intent(this, Prefs.class));
				return true;
			//more items go here
			}
			return false;
	}
}