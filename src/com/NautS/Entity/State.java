package com.NautS.Entity;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import com.NautS.GameState.SimState;

public class State {
	
	protected int popSize;
	protected double productivity;
	protected double happiness = 50;
	protected int pointsToNewCit;
	protected int newCitPoints = 0;
	protected int grainAdd = 0;
	protected int grainGain;
	protected String name;
	
	protected BufferedImage sprite;
	
	public State() {}
	
	public void cycle() {
		int popSize2 = popSize;
		double productivity2 = productivity;
		double happiness2 = happiness;
		int pointsToNewCit2 = pointsToNewCit;
		int newCitPoints2 = newCitPoints;
		int grainAdd2 = grainAdd;
		
		popSize += calcPopSize(newCitPoints2, pointsToNewCit2, popSize2);
		pointsToNewCit = calcPointsToNewCit(popSize2, happiness2);
		productivity = calcProductivity(happiness2, popSize2);
		happiness = calcHappiness(grainAdd2, popSize2);
		newCitPoints = calcNewCitPoints(popSize2, grainAdd2);
		grainAdd = 0;
	}
	
	public void addGrain(int i){
		grainAdd+=i;
	}
	
	public double mod(double d, int g) {
		double a = (d-(d%g));
		return a;
	}
	
	public double mod2(int d, double g) {
		double a = (d-(d%g));
		return a;
	}
	
	public int calcPopSize(int points, int needed, int pop){
		int v = 0;
		if (points>needed) {
			v = pop/100;
			newCitPoints=0;
		} else {v = 0;}
		return v;
	}
	
	public double calcHappiness(int g, int p){
		double j;
		if(g==0){
			j = 0;
		}else{
			j = ((100*(mod2(p,(g*100000)))/p));
		}
		return j;
	}
	
	public int calcPointsToNewCit(int p, double h) {
		int ptnc = (int)(((h/100)*(p/10000)));
		return ptnc;
	}
	
	public int calcNewCitPoints(int p, int g) {
		int points =(g/(p/100));
		return points;
	}
	
	public double calcProductivity(double h, int pop) {
		double bonus = 0;
		bonus = (.1*(Math.floor((h-50)/5)))*(mod(pop,10000));
		double j = ((mod(pop,10000))/pop)*100 + bonus*100;
		return j;
	}
	
	public void update() {
		
	}
	
	//getters
	public String getName() {return name;}
	public int getPopSize() {return popSize;}
	public double getHappiness() {return happiness;}
	public double getProductivity() {return productivity;}
	public double getPointsToNewCit() {return pointsToNewCit;}
	public double getNewCitPoints() {return newCitPoints;}
	public int getGrainAdd() {return grainAdd;}
	
	//setters
	public void setPopSize(double p) {popSize = (int)p;}
	public void setHappiness(double h) {happiness = h;}
	public void setProductivity(double p) {productivity = p;}
	public void setPointsToNewCit(double p) {pointsToNewCit = (int)p;}
	public void setNewCitPoints(double n) {newCitPoints = (int)n;}
	public void setGrainAdd(int g){grainAdd = g;}
	
	public void draw(Graphics2D g){
		g.drawImage(sprite, 0, 0, null);
	}

	public void init(){}
	
	public static class Rus extends State {
		public Rus() {}
		public void init() {
			name = "Russia";
			popSize = 67475000;
			happiness = 50;
			pointsToNewCit = calcPointsToNewCit(popSize, happiness);
			productivity = 100;
			newCitPoints = 0;
			grainAdd = 0;
		}
		public void update(){}
	}
	
	public static class Ukr extends State {
		public Ukr() {}
		public void init() {
			name = "Ukraine";
			popSize = 23430000;
			happiness = 50;
			pointsToNewCit = calcPointsToNewCit(popSize, happiness);
			productivity = 100;
			newCitPoints = 0;
		}
		public void update(){}
	}
	
	public static class Mol extends State {
		public Mol() {}
		public void init() {
			name = "Moldova";
			popSize = 1935000;
			happiness = 50;
			pointsToNewCit = calcPointsToNewCit(popSize, happiness);
			productivity = 100;
			newCitPoints = 0;
		}
		public void update(){}
	}
	
	public static class Bel extends State {
		public Bel() {}
		public void init() {
			name = "Belarus";
			popSize = 6927000;
			happiness = 50;
			pointsToNewCit = calcPointsToNewCit(popSize, happiness);
			productivity = 100;
			newCitPoints = 0;
		}
		public void update(){}
	}
	
	public static class Lit extends State {
		public Lit() {}
		public void init() {
			name = "Lithuania";
			popSize = 3154000;
			happiness = 50;
			pointsToNewCit = calcPointsToNewCit(popSize, happiness);
			productivity = 100;
			newCitPoints = 0;
		}
		public void update(){}
	}
	
	public static class Lat extends State {
		public Lat() {}
		public void init() {
			name = "Latvia";
			popSize = 1929000;
			happiness = 50;
			pointsToNewCit = calcPointsToNewCit(popSize, happiness);
			productivity = 100;
			newCitPoints = 0;
		}
		public void update(){}
	}
	
	public static class Est extends State {
		public Est() {}
		public void init() {
			name = "Estonia";
			popSize = 900000;
			happiness = 50;
			pointsToNewCit = calcPointsToNewCit(popSize, happiness);
			productivity = 100;
			newCitPoints = 0;
		}
		public void update(){}
	}
	
	public static class Geo extends State {
		public Geo() {}
		public void init() {
			name = "Georgia";
			popSize = 2109000;
			happiness = 50;
			pointsToNewCit = calcPointsToNewCit(popSize, happiness);
			productivity = 100;
			newCitPoints = 0;
		}
		public void update(){}
	}
	
	public static class Arm extends State {
		public Arm() {}
		public void init() {
			name = "Armenia";
			popSize = 830000;
			happiness = 50;
			pointsToNewCit = calcPointsToNewCit(popSize, happiness);
			productivity = 100;
			newCitPoints = 0;
		}
		public void update(){}
	}
	
	public static class Aze extends State {
		public Aze() {}
		public void init() {
			name = "Azerbaijan";
			popSize = 1705000;
			happiness = 50;
			pointsToNewCit = calcPointsToNewCit(popSize, happiness);
			productivity = 100;
			newCitPoints = 0;
		}
		public void update(){}
	}
	
	public static class Kaz extends State {
		public Kaz() {}
		public void init() {
			name = "Kazakhstan";
			popSize = 4000000;
			happiness = 50;
			pointsToNewCit = calcPointsToNewCit(popSize, happiness);
			productivity = 100;
			newCitPoints = 0;
		}
		public void update(){}
	}
	
	public static class Tur extends State {
		public Tur() {}
		public void init() {
			name = "Turkmenistan";
			popSize = 350000;
			happiness = 50;
			pointsToNewCit = calcPointsToNewCit(popSize, happiness);
			productivity = 100;
			newCitPoints = 0;
		}
		public void update(){}
	}
	
	public static class Uzb extends State {
		public Uzb() {}
		public void init() {
			name = "Uzbekistan";
			popSize = 2000000;
			happiness = 50;
			pointsToNewCit = calcPointsToNewCit(popSize, happiness);
			productivity = 100;
			newCitPoints = 0;
		}
		public void update(){}
	}
	
	public static class Kyr extends State {
		public Kyr() {}
		public void init() {
			name = "Kyrgyzstan";
			popSize = 750000;
			happiness = 50;
			pointsToNewCit = calcPointsToNewCit(popSize, happiness);
			productivity = 100;
			newCitPoints = 0;
		}
		public void update(){}
	}
	
	public static class Taj extends State {
		public Taj() {}
		public void init() {
			name = "Tajikistan";
			popSize = 646000;
			happiness = 50;
			pointsToNewCit = calcPointsToNewCit(popSize, happiness);
			productivity = 100;
			newCitPoints = 0;
		}
		public void update(){}
	}
	
}
