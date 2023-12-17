package com.NautS.GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

//import com.NautS.Audio.JukeBox;
//import com.NautS.Entity.PlayerSave;
//import com.NautS.Handlers.Keys;
import com.NautS.Main.Game;
import com.NautS.Main.GameScreen;
import com.NautS.TileMap.Background;

public class MenuState extends GameState {
	
	private int bump;
	private int bpm = 92;
	public GameScreen gs;
	public Background bg;
	private String title;
	
	private int eventCount = 0;
	private Color selected;
	
	private static int currentChoice = 0;
	private String[] options = {
		"Play",
		"Quit"
	};
	
	private Font font;
	private Font font2;
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
		
		try {
			bg = new Background("/Backgrounds/menuBG.gif");
			font = new Font("Russian", Font.PLAIN, 48);
			font2 = new Font("Russian", Font.PLAIN, 15);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setCurrentChoice(int c) {
		int choice = c;
		currentChoice = choice;
	}
	
	public void init() {
	}
	
	public void draw(Graphics2D g) {
		bg.draw(g);
		
		eventCount++;
		
		g.setFont(font);
		for(int i = 0; i <options.length; i++) {
			if (i == currentChoice) {
				if(eventCount < bpm/2) {
					selected = Color.yellow;
					bump = -3;
				}
				else if (eventCount <= bpm){
					selected = Color.RED;
					bump = 5;
				} else if (eventCount > bpm) {
					eventCount = 0;
					bump = 2;
				}
				g.setColor(selected);
			}
			else {
				g.setColor(Color.GRAY);
			}
			g.drawString(options[i], 128 + (i*200), (((i-1)^i)*bump)+(4*720)/5);
		}
	}
	
	public void update() {}
	
	private void select() {
		if(currentChoice == 0) {
			Game.stopMusic();
			gsm.setState(GameStateManager.SIMSTATE);
		}
		if(currentChoice == 1) {
			System.exit(0);
		}
	}
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER){
			Game.bop();
			select();
		}
		if(k == KeyEvent.VK_S || k == KeyEvent.VK_DOWN || k == KeyEvent.VK_D || k == KeyEvent.VK_RIGHT || k == KeyEvent.VK_A || k == KeyEvent.VK_LEFT || k == KeyEvent.VK_W || k == KeyEvent.VK_UP) {
			currentChoice++;
			Game.bop();
			if(currentChoice == options.length){
				currentChoice = 0;
			}
		}
		if(k== KeyEvent.VK_SPACE) {
			Game.music();
		}
	}
	
	public void keyReleased(int k) {}

}
