package com.NautS.GameState;

//import com.NautS.Audio.Jukebox;
import com.NautS.Main.GameScreen;

public class GameStateManager {
	
	private GameState[] gameStates;
	private int currentState;
	
	private StatPauseState pauseState;
	private boolean paused;
	
	public static final int NUMGAMESTATES = 3;
	public static final int MENUSTATE = 0;
	public static final int SIMSTATE = 1;
	public static final int STATPAUSESTATE = 2;
	
	public GameStateManager() {
		
//		JukeBox.init();
		
		gameStates = new GameState[NUMGAMESTATES];
		
		currentState = MENUSTATE;
		loadState(currentState);
		
	}
	
	private void loadState(int state) {
		if(state == MENUSTATE)
			gameStates[state] = new MenuState(this);
		if(state == SIMSTATE)
			gameStates[state] = new SimState(this);
//		if(state == STATPAUSESTATE)
//			gameStates[state] = new StatPauseState(this);
	}
	
	private void unloadState(int state) {
		gameStates[state] = null;
	}
	
	public void setState(int state) {
		unloadState(currentState);
		currentState = state;
		loadState(currentState);
	}
	
	public void setPaused(boolean b) { paused = b; }
	
	public void update() {
//		if(paused) {
//			pauseState.update();
//			return;
//		}
//		if(gameStates[currentState] != null) gameStates[currentState].update();
	}
	
	public void draw(java.awt.Graphics2D g) {
//		if(paused) {
//			pauseState.draw(g);
//			return;
//		}
		if(gameStates[currentState] !=null) gameStates[currentState].draw(g);
		else {
			g.setColor(java.awt.Color.WHITE);
			g.fillRect(0, 0, GameScreen.WIDTH, GameScreen.HEIGHT);
		}
	}
	
	public void keyPressed(int k) {
		gameStates[currentState].keyPressed(k);
	}
	
	public void keyReleased(int k) {
		gameStates[currentState].keyReleased(k);
	}

}