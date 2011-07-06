package org.jbs.sahar;



import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.TextView;

public class Prompt extends Activity implements OnClickListener, OnKeyListener {
    /** Called when the activity is first created. */
    
	private QuestionGame game;
	private boolean gameover;
	
	@Override
    public void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);
		game = new QuestionGame("dog", this);
        setContentView(R.layout.main);
        View yesButton = findViewById(R.id.yes);
        View noButton = findViewById(R.id.no);
        
        yesButton.setOnClickListener(this);
        noButton.setOnClickListener(this);
        //Finds the more_textbox view
		
        EditText animalField = (EditText)findViewById(R.id.animal_textbox);
		animalField.setOnKeyListener(this);
		
        EditText questionField = (EditText)findViewById(R.id.question_textbox);
		questionField.setOnKeyListener(this);
		
		View submitButton = findViewById(R.id.entertext);
		View newgameButton = findViewById(R.id.newgame);
		submitButton.setOnClickListener(this);
		newgameButton.setOnClickListener(this);
		

        run();
    }
    
    private void run(){
    	TextView t = (TextView) findViewById(R.id.textprompt);
    	
    	if (gameover){
    		if(game.lost()) {
    			t.setText(game.lose());
    			t.invalidate();
    			return;
    		}
    		t.setText(game.win());
    		t.invalidate();
    		return;
    	}  	
    	t.setText(game.getNext());
    	t.invalidate();
    	
    }
    
    public void onClick(View v) {
		

		switch(v.getId()) {		
		case R.id.yes:	
			gameover = game.guessResult(true);
			run();
			break;
		case R.id.no:
			gameover = game.guessResult(false);
			run();
			break;
		case R.id.entertext:
			String animal = ((TextView) findViewById(R.id.animal_textbox)).getText().toString();
			String question = ((TextView) findViewById(R.id.question_textbox)).getText().toString();
			game.learn(animal, question);
			onClick(findViewById(R.id.newgame));
			break;
		case R.id.newgame:
			TextView a = ((TextView) findViewById(R.id.animal_textbox));
			TextView b = ((TextView) findViewById(R.id.question_textbox));
			a.setText("");
			b.setText("");
			game.newGame();
			gameover=false;
			run();
			break;
		}
	}
 
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event){
    	if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
    		 	      (keyCode == KeyEvent.KEYCODE_ENTER)){
    		//EditText t = (EditText) v;
    		this.onClick(findViewById(R.id.entertext));
    	}
    	return false;
    }
   
 

}