package com.massachi.jbs.pa6;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.os.AsyncTask;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;

public class PuzzleView extends View {
	private static final String TAG = "Sudoku";
	private float sizeMod = 3; //if sizeMod == 3, then we are dealing with 9 total tiles. if 9, 81 total tiles. 

	private float width;    // width of one tile
	private float height;   // height of one tile
	private int selX;       // X index of selection
	private int selY;       // Y index of selection
	private final Rect selRect = new Rect();
	private final Game game;
	protected boolean isA;
	private int lastOrientation = 0;
	private boolean flippedup = false;

	public PuzzleView(Context context, boolean which) {
		super(context);
		this.game = (Game) context;
		setFocusable(true);
		setFocusableInTouchMode(true);
		isA = false;
		if(which) isA = true;
		setWillNotDraw(false);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		width = w / sizeMod;
		height = h / sizeMod;
		getRect(selX, selY, selRect);
		Log.d(TAG, "onSizeChanged: width " + width + ", height "
				+ height);
		super.onSizeChanged(w, h, oldw, oldh);
	}

	private void getRect(int x, int y, Rect rect) {
		rect.set((int) (x * width), (int) (y * height), (int) (x
				* width + width), (int) (y * height + height));
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// Draw the background...
		Paint background = new Paint();
		if(isA){
			background.setColor(getResources().getColor(
					R.color.puzzleA_background));}
		else{
			background.setColor(getResources().getColor(
					R.color.puzzleB_background));
		}
		canvas.drawRect(0, 0, getWidth(), getHeight(), background);

		// Draw the board... 
		gridDraw(canvas);
		// Draw the text
		drawCards(canvas);  
		// Draw the selection
		drawSelection(canvas);
	}

	private void gridDraw(Canvas canvas){
		// Define colors for the grid lines
		Paint dark = new Paint();
		dark.setColor(getResources().getColor(R.color.puzzle_dark));

		Paint hilite = new Paint();
		hilite.setColor(getResources().getColor(R.color.puzzle_hilite));

		Paint light = new Paint();
		light.setColor(getResources().getColor(R.color.puzzle_light));


		// Draw the major grid lines
		for (int i = 0; i < 3; i++) {

			canvas.drawLine(0, i * height, getWidth(), i * height,
					dark);
			canvas.drawLine(0, i * height + 1, getWidth(), i * height
					+ 1, hilite);
			canvas.drawLine(i * width, 0, i * width, getHeight(), dark);
			canvas.drawLine(i * width + 1, 0, i * width + 1,
					getHeight(), hilite);
		}


	}

	private void drawCards(Canvas canvas){
		// Define color and style for marks
		//TODO here we draw cards - for reference.
		Paint foreground = new Paint(Paint.ANTI_ALIAS_FLAG);
		foregroundSet(foreground);

		// Draw the number in the center of the tile
		FontMetrics fm = foreground.getFontMetrics();
		// Centering in X: use alignment (and X at midpoint)
		float x = width / 2;
		// Centering in Y: measure ascent/descent first
		float y = height / 2 - (fm.ascent + fm.descent) / 2;
		for (int i = 0; i < sizeMod; i++) {
			for (int j = 0; j < sizeMod; j++) {
				try {
					canvas.drawText(this.game.getTileString(i, j, this), i
							* width + x, j * height + y, foreground);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					canvas.drawText("ERROR", i * width + x, j * height + y, foreground);
				}
			}
		}
	}

	private void drawSelection(Canvas canvas){
		Paint selected = new Paint();
		selected.setColor(getResources().getColor(
				R.color.puzzle_selected));
		canvas.drawRect(selRect, selected);
	}


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.d(TAG, "onKeyDown: keycode=" + keyCode + ", event="
				+ event);
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_UP:
			select(selX, selY - 1);
			break;
		case KeyEvent.KEYCODE_DPAD_DOWN:
			select(selX, selY + 1);
			break;
		case KeyEvent.KEYCODE_DPAD_LEFT:
			select(selX - 1, selY);
			break;
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			select(selX + 1, selY);
			break;

		case KeyEvent.KEYCODE_ENTER:
		case KeyEvent.KEYCODE_DPAD_CENTER:
			pickCard();
			break;


		default:
			return super.onKeyDown(keyCode, event);
		}
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() != MotionEvent.ACTION_DOWN)
			return super.onTouchEvent(event);

		select((int) (event.getX() / width),
				(int) (event.getY() / height));

		pickCard();
		Log.d(TAG, "onTouchEvent: x " + selX + ", y " + selY);
		return true;
	}


	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

		// Checks if orientation changed
		if (lastOrientation != newConfig.orientation) {
			lastOrientation = newConfig.orientation;
			//do stuff if you like. I don't think you need to do anything.
		} 
	}



	private void select(int x, int y) {
		invalidate(selRect);
		selX = Math.min(Math.max(x, 0), 8);
		selY = Math.min(Math.max(y, 0), 8);
		getRect(selX, selY, selRect);
		invalidate(selRect);
	}

	private void pickCard(){
		if(flippedup) {
			flippedup = false;
			game.flipDown(selX, selY, this);
			game.showKeypadOrError(selX, selY, this);
		}
		else {
			showCard();
		}		
	}
	
	private void showCard(){
			game.flipUp(selX, selY, this);
			invalidate();
			flippedup = true;
			new AsyncTask(){
				@Override
				protected Object doInBackground(Object... params) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return params;
				}
				
				@Override
				protected void onPostExecute(Object result){
					pickCard();
				}
			}.execute(null);
	}

	private void foregroundSet(Paint foreground){

		foreground.setColor(getResources().getColor(
				R.color.puzzle_foreground));
		foreground.setStyle(Style.FILL);
		foreground.setTextSize(height * 0.75f);
		foreground.setTextScaleX(width / height);
		foreground.setTextAlign(Paint.Align.CENTER);
	}
}




