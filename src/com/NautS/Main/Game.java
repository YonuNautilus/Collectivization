package com.NautS.Main;

import javax.swing.JFrame;
import javax.swing.SortingFocusTraversalPolicy;

public class Game {
	
	
	public static void main(String[] args) {
		
		JFrame window = new JFrame ("Collectivization");
		window.setContentPane(new GameScreen());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setLocation(0, 0);
		window.setVisible(true);
	}
}
