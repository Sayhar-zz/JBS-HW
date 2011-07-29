package com.massachi.jbs.pa6;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent; 
import android.util.Log;
import android.view.View; 
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


public class Intro extends Activity implements OnClickListener {
	private static final String TAG = "intro screen";


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.main);

		// Set up click listeners for all the buttons
		initButtons();
		boolean bExists = (bundle != null);

		if(bExists && bundle.containsKey("winning")){
			Toast t = Toast.makeText(this, bundle.getString("winning"), Toast.LENGTH_LONG);
			t.show();
		}
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.about_button:
			Intent i = new Intent(this, About.class);
			startActivity(i);
			break;
			// More buttons go here (if any) ...
		case R.id.new_button:
			startGame(1);
			break;
		case R.id.exit_button:
			finish();
			break;
		}
	}

	public void initButtons(){
		
		View newButton = findViewById(R.id.new_button);
		newButton.setOnClickListener(this);
		View aboutButton = findViewById(R.id.about_button);
		aboutButton.setOnClickListener(this);
		View exitButton = findViewById(R.id.exit_button);
		exitButton.setOnClickListener(this);
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.settings:
			startActivity(new Intent(this, Prefs.class));
			return true;
			// More items go here (if any) ...
		}
		return false;
	}
	*/


	private void startGame(int i) {
		Log.d(TAG, "clicked on " + i);
		Intent intent = new Intent(this, Game.class);
		startActivity(intent);
		// Start game here...
	}

}