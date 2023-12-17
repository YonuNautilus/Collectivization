package com.NautS.Main;

import javax.swing.JFrame;
import javax.swing.SortingFocusTraversalPolicy;

import kuusisto.tinysound.Music;
import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;

public class Game {
	
	private static Music song;
	private static Music song2;
	private static Music bop;
	private static Music upBlip;
	private static Music downBlip;
	
	public static void main(String[] args) {
		
		TinySound.init();
		song = TinySound.loadMusic("/Music/NatAnthem.wav");
		song.play(true);
		song.loop();
		song2 = TinySound.loadMusic("/Music/NatAnthemLoop.wav");
		upBlip = TinySound.loadMusic("/Music/upBlip.wav");
		downBlip = TinySound.loadMusic("/Music/downBlip.wav");
		bop = TinySound.loadMusic("/Music/bop.wav");
		JFrame window = new JFrame ("Collectivization");
		window.setContentPane(new GameScreen());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setLocation(0, 0);
		window.setVisible(true);
	}
	
	public static void music() {
		if(song.playing()) {
			song.pause();
		} else {
			song.resume();
		}
	}
	
	public static void stopMusic() {
		song.stop();
	}
	
	public static void upBlip(){
		upBlip.playing();
	}
	
	public static void downBlip(){
		downBlip.playing();
	}
	
	public static void bop(){
		bop.playing();
	}
	
}
