package org.example.sudoku;

//hello

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class About extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		
	}
	
	
	//let's see if we can also let you call the settings menu from the about screen as well.
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		System.out.println("onCreateOptionsMenu");
		
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		//You CAN! Good for sahar!
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
