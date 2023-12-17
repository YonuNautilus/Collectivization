package com.NautS.GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.NautS.Main.Game;
import com.NautS.Main.GameScreen;
import com.NautS.TileMap.*;

import kuusisto.tinysound.Music;
import kuusisto.tinysound.TinySound;

import com.NautS.Entity.*;
import com.NautS.Entity.State.*;

public class SimState extends GameState {
	
	public int grain = 100;
	
	private Background bg;
	private ArrayList<State> states;
	private ArrayList<String> stateStats;
	
	private static int currentChoice = 0;
	
	private Font font;
	private Font font2;
	
	private Integer[] sel = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14};
	private String[] options = {
			"1. Russia",
			"2. Ukraine",
			"3. Moldova",
			"4. Belarus",
			"5. Lithuania",
			"6. Latvia",
			"7. Estonia",
			"8. Georgia",
			"9. Armenia",
			"10. Azerbaijan",
			"11. Kazakhstan",
			"12. Turkmenistan",
			"13. Uzbekistan",
			"14. Kyrgystan",
			"15. Tajikistan"
	};
	
	private String[] stats = {
			"State:",
			"Population:",
			"Pop. Growth:",
			"Happiness:",
			"Productivity:",
			"Food per turn"
	};
	
	private BufferedImage image;
	private String[] stateStatsTemp;

	private Music song2;
	
	public SimState(GameStateManager gsm) {
		super(gsm);
		init();
	}
	
	public void init() {
		
		TinySound.init();
		song2 = TinySound.loadMusic("/Music/NatAnthemLoop.wav");
		song2.play(true);
		song2.loop();
		
		bg = new Background("/Map/map.gif");
		states = new ArrayList<State>();
		stateStats = new ArrayList<String>();
		
		
		font = new Font("RUSKOF Light", Font.PLAIN, 40);
		font2 = new Font("RUSKOF Light", Font.PLAIN, 25);
		
		Rus rus; rus = new Rus(); states.add(rus);			
		Ukr ukr; ukr = new Ukr(); states.add(ukr);
		Mol mol; mol = new Mol(); states.add(mol);
		Bel bel; bel = new Bel(); states.add(bel);
		Lit lit; lit = new Lit(); states.add(lit);
		Lat lat; lat = new Lat(); states.add(lat);
		Est est; est = new Est(); states.add(est);
		Geo geo; geo = new Geo(); states.add(geo);
		Arm arm; arm = new Arm(); states.add(arm);
		Aze aze; aze = new Aze(); states.add(aze);
		Kaz kaz; kaz = new Kaz(); states.add(kaz);
		Tur tur; tur = new Tur(); states.add(tur);
		Uzb uzb; uzb = new Uzb(); states.add(uzb);
		Kyr kyr; kyr = new Kyr(); states.add(kyr);
		Taj taj; taj = new Taj(); states.add(taj);
		
		for(int i = 0; i<states.size(); i++){
			states.get(i).init();
		}
		
		makeStats(states.get(currentChoice));
	}
	
	public void makeStats(State e) {
		stateStats.clear();
		stateStats.add(e.getName());
		stateStats.add(Integer.toString(e.getPopSize()));
		stateStats.add(Double.toString(e.getNewCitPoints())
				+"/"+
				Double.toString(e.getPointsToNewCit()));
		stateStats.add(Double.toString(e.getHappiness()));
		stateStats.add(Double.toString(e.getProductivity()));
		stateStats.add("+"+Integer.toString(e.getGrainAdd()));
	}
	
	public void update() {
		bg.update();
	}
	
	public void draw(Graphics2D g) {
		
		g.setColor(Color.GRAY);
		g.drawRect(0, 0, GameScreen.WIDTH, GameScreen.HEIGHT);
		
		bg.draw(g);
		
		try{image = ImageIO.read(
				getClass().getResourceAsStream("/Map/"+
		Integer.toString(currentChoice+1)+".gif"));
		} catch(Exception e){
			e.printStackTrace();
		}
		g.drawImage(image, 0, 0, null);
		
		//draw stats text
		g.setColor(Color.RED);
		g.setFont(font);
		
		g.drawString("Total Foods:", GameScreen.WIDTH-500, GameScreen.HEIGHT/2);
		g.drawString(Integer.toString(grain), GameScreen.WIDTH-250, GameScreen.HEIGHT/2);
			
		for(int i = 0; i < stats.length; i++){
			g.drawString(stats[i], GameScreen.WIDTH - 500, 50+50*i);
		};
		for(int j = 0; j < stateStats.size(); j ++){
			g.drawString(stateStats.get(j), GameScreen.WIDTH-250, 50+50*j);
		};
		
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				g.setColor(new Color(46,139,87));
				g.setFont(new Font("RUSKOF Light", Font.PLAIN, 40));
			} else {
				g.setFont(font2);
				g.setColor(Color.RED);
			}
			g.drawString(options[i], 1300, 650+25*i);
		}
	}
	
	public static void setCurrentChoice(int c){
		int choice = c;
		currentChoice = choice;
	}
	
	public static int getCurrentChoice(){return currentChoice;}


	public void keyPressed(int k) {

		if(k == KeyEvent.VK_UP){
			currentChoice--;
			if(currentChoice == -1){
				currentChoice = options.length-1;
			}
		}
		if(k == KeyEvent.VK_DOWN){
			currentChoice++;
			if(currentChoice == options.length){
				currentChoice=0;
			}
		}
		if(k == KeyEvent.VK_ENTER || k == KeyEvent.VK_SPACE){
			if(grain != 0){
				return;
			}else{
				for(int i = 0; i<states.size(); i++){
					states.get(i).cycle();
				}
			}
		}
		if(k == KeyEvent.VK_W){
			if(grain <= 0){
				states.get(currentChoice).addGrain(0);
			} else {
				states.get(currentChoice).addGrain(1);
				grain-=1;
			}	
		}
		if(k == KeyEvent.VK_S){
			if(0 == states.get(currentChoice).getGrainAdd()){
				states.get(currentChoice).addGrain(0);
			} else {
				states.get(currentChoice).addGrain(-1);
				grain+=1;
			}	
		}
		if(k == KeyEvent.VK_ESCAPE){
			System.exit(0);
		}
		makeStats(states.get(currentChoice));
	}

	public void keyReleased(int k) {}

}